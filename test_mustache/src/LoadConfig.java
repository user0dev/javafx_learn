import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;


/**
 * Created by user0dev on 03.10.17.
 */
public class LoadConfig {
    protected static String address = null;
    protected static String password = null;
    protected static int port = 0;

    public static boolean load() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("/etc/bareos/bconsole.conf"));
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("=")) {
                String[] params = lines.get(i).split("=");
                if (params.length != 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
                    throw new ParseException("Config error in line " + i + ". Line: " + lines.get(i), i);
                }
                String option = params[0].trim().toLowerCase();
                String value = params[1].trim();
                if (value.charAt(0) == '\"') {
                    value = value.substring(1);
                    if (value.isEmpty() || value.charAt(value.length() - 1) != '\"') {
                        throw new ParseException("Config error in line " + i + ". Line: " + lines.get(i), i);
                    }
                    value = value.substring(0, value.length() - 1);
                }

                switch (option) {
                    case "dirport":
                        try {
                            port = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            ParseException pe = new ParseException(String.format("Config error in line %d. Value: %s isn't number", i, value) , i);
                            pe.initCause(e);
                            throw pe;
                        }
                        break;
                    case "address":
                        address = value;
                        break;
                    case "password":
                        password = value;
                        break;
                }
            }

        }
        if (address == null || password == null || port == 0) {
            throw new ParseException("Config error. Config don't contain necessary options", 0);
        }


        return true;
    }

    public static String getAddress() {
        return address;
    }

    public static String getPassword() {
        return password;
    }

    public static int getPort() {
        return port;
    }
}
