import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by user0dev on 05.10.17.
 */
public class TestMustache {
    static class DateFormat {

    }

    public static final SimpleDateFormat OUT_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) throws IOException, ParseException {
        if (args.length != 1 || args[0].trim().isEmpty()) {
            System.err.println("Wrong argument");
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
            }
            String[] dates = new String[listDates.length];
            for (int i = 0; i < listDates.length; i++) {
                dates[i] = OUT_FORMAT.format(listDates[i]);
            }
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("phrase.mustache");
            StringWriter sw = new StringWriter();
            HashMap<String, Object> hash = new HashMap<>();
            hash.put("dates", dates);
            hash.put("count", dates.length);
            mustache.execute(sw, hash);
            System.out.println(sw);

        } finally {
            if (backuplist != null) {
                backuplist.close();
            }

        }

    }
}
