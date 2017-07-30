import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String PASSWORD = null;
    private static final String HOST = "78.108.93.26";
    private static final String USER = "user";
    public static void main(String[] args) {
	// write your code here
        try {
            ProcessBuilder ssh = new ProcessBuilder("ssh", USER + "@" + HOST);
            ssh.redirectInput(ProcessBuilder.Redirect.INHERIT);
            ssh.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            ssh.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process sshProc = ssh.start();
            //sshProc.waitFor(3, TimeUnit.SECONDS);
            OutputStream sshOut = sshProc.getOutputStream();
            try {
                sshOut.write(1);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                //System.out.println(e.getStackTrace().);
            }
            /*if (sshOut == null) {
                System.out.println("Output stream is null");
            } {
                System.out.println("Output stream is not null");
            }*/

            //System.out.println("call write");
            //writer.write("ls -al\n");
            //writer.flush();
            sshProc.waitFor();

        } catch (Exception e) {

        }
    }
}
