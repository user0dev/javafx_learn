import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.StringHelpers;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by user0dev on 05.10.17.
 */
public class TestHandlebars {


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

            Handlebars handlebars = new Handlebars();
            //handlebars.registerHelper("dateFormat", S)
            StringHelpers.register(handlebars);
            //Template template = handlebars.compileInline("Now: {{#this}}{{dateFormat this \"dd.MM.yyyy\"}}{{/this}}");
            Template template = handlebars.compile("phrase");
//            HashMap<String, Object>
            System.out.println(template.apply(listDates));


        } finally {
            if (backuplist != null) {
                backuplist.close();
            }

        }

    }
}
