/**
 * Created by user0dev on 22.09.17.
 */


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.python.util.PythonInterpreter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


public class BackupList {

    public class Job {
        public Date starttime;
        public String level;
        public long files;
        public long bytes;
        public int id;
        public String status;

        public Job(int id, Date starttime, String level, String status, long files, long bytes) {
            this.starttime = starttime;
            this.level = level;
            this.files = files;
            this.bytes = bytes;
            this.id = id;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "starttime=" + starttime +
                    ", level='" + level + '\'' +
                    ", files=" + files +
                    ", bytes=" + bytes +
                    ", id=" + id +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    public static final SimpleDateFormat OUT_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final String USAGE = "Usage: backuplist server_name";

    protected static final String COMMAND_INIT = "import bareos.bsock\n" +
            "directorconsole=bareos.bsock.DirectorConsole(address='%s', port=%d, password=bareos.bsock.Password('%s'))\n" +
            "directorconsole.call('.api json')";
    protected static final String COMMAND_LIST = "directorconsole.call('list job=backup-%s.intr')";

    public static final SimpleDateFormat FORMAT_BAREOS = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    protected PythonInterpreter pi = null;

    public BackupList(String address, String password, int port) {
        Properties props = new Properties();
        props.setProperty("python.path", "/usr/lib/python2.7/dist-packages:scripts");
        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);
        pi = new PythonInterpreter();
        pi.exec(String.format(COMMAND_INIT, address, port, password));
    }

    public BackupList(String address, String password) {
        this(address, password, 9101);
    }

    public void close() {
        if (pi != null) {
            pi.close();
            pi = null;
        }
    }
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public String listJson(String server) {
        return (pi.eval(String.format(COMMAND_LIST, server))).toString();
    }

    public Job[] listJobs(String server) throws ParseException {
        String json = listJson(server);
        JsonParser parser = new  JsonParser();
        JsonElement element = parser.parse(json);
        JsonObject rootObject = element.getAsJsonObject();
        JsonObject resultField = rootObject.getAsJsonObject("result");
        JsonArray jobsArray = resultField.getAsJsonArray("jobs");
        Job[] jobs = new Job[jobsArray.size()];
        for (int i = 0; i < jobsArray.size(); i++) {
            JsonObject job = jobsArray.get(i).getAsJsonObject();
            Date starttime = null;
            String stTime = job.get("starttime").getAsString();
            if (!stTime.isEmpty()) {
                starttime = FORMAT_BAREOS.parse(stTime);
            } else {
                starttime = new Date(0);

            }
            jobs[i] = new Job(job.get("jobid").getAsInt(), starttime, job.get("level").getAsString(), job.get("jobstatus").getAsString(),
                    job.get("jobfiles").getAsLong(), job.get("jobbytes").getAsLong());

        }
        return jobs;
    }

    protected boolean isJobCorrect(Job job) {
        return ("T".equals(job.status) || "W".equals(job.status)) && job.bytes > 0 && job.files > 0;
    }

    public Date[] listFiltred(String server) throws ParseException {
        Job[] jobs = listJobs(server);
        ArrayList<Date> filtredJobs = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        boolean fullExists = false;
        mainLoop:
        for(Job job : jobs) {
            if ("F".equals(job.level) || "D".equals(job.level)) {
                if ("F".equals(job.level)) {
                    fullExists =  isJobCorrect(job);
                }
                if ("D".equals(job.level) && (!fullExists || !isJobCorrect(job))) {
                    continue;
                }
                for (Date date : filtredJobs) {
                    if (f.format(date).equals(f.format(job.starttime))) {
                        continue mainLoop;
                    }
                }
                filtredJobs.add(job.starttime);
            }

        }
        return filtredJobs.toArray(new Date[filtredJobs.size()]);

    }

    public static void main(String[] args) throws ParseException, IOException {

        if (args.length != 1 || args[0].trim().isEmpty()) {
            System.err.println(USAGE);
            System.exit(1);
        }
        BackupList backuplist = null;
        try {
            LoadConfig.load();
            backuplist = new BackupList(LoadConfig.getAddress(), LoadConfig.getPassword(), LoadConfig.getPort());
            Date[] listDates = backuplist.listFiltred(args[0].trim());
            if (listDates == null) {
                System.err.println("Error getting list of dates");
                System.exit(1);
            } else if (listDates.length == 0) {
                System.out.println("There aren't backups");
            } else {
                for (Date date : listDates) {
                    System.out.println(OUT_FORMAT.format(date));
                }
            }
        } finally {
            if (backuplist != null) {
                backuplist.close();
            }

        }
    }
}
