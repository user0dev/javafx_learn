/**
 * Created by user0dev on 02.08.17.
 */
import com.jcraft.jsch.*;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class TestJSCH implements SignalHandler{
    private static ChannelShell channel;

    @Override
    public void handle(Signal signal) {
        if (!channel.isConnected()) {
            System.exit(0);
        }
        try {
            channel.sendSignal("INT");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Signal.handle(new Signal("INT"), new TestJSCH());
        try {
            JSch jsch = new JSch();
            jsch.setKnownHosts(System.getProperty("user.home") + "/.ssh/known_hosts");
            jsch.addIdentity(System.getProperty("user.home") + "/.ssh/id_rsa");
            Session session = jsch.getSession("user", "myvps");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelShell)session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.setPtyType("vt102");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();

        } catch (JSchException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }
}
