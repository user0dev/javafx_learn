/**
 * Created by user0dev on 20.10.17.
 */



        import org.jfxvnc.net.rfb.VncConnection;
        import org.jfxvnc.net.rfb.codec.security.SecurityType;
        import org.jfxvnc.net.rfb.render.ProtocolConfiguration;

public class Vnc {

    public static void main(String[] args) throws Exception {

        VncConnection connector = new VncConnection();
        ProtocolConfiguration config = connector.getConfiguration();

        if (args != null && args.length >= 3) {
            config.securityProperty().set(SecurityType.VNC_Auth);
            config.hostProperty().set(args[0]);
            config.portProperty().set(Integer.parseInt(args[1]));
            config.passwordProperty().set(args[2]);
            config.sharedProperty().set(Boolean.TRUE);
        } else {
            System.err.println("arguments missing (host port password)");
            config.securityProperty().set(SecurityType.VNC_Auth);
            config.hostProperty().set("kvm6");
            config.portProperty().set(12189);
            config.passwordProperty().set("5va746bR");
            config.sharedProperty().set(Boolean.TRUE);
        }

        connector.connect().whenComplete((c, th) -> {
            if (th != null){
                th.printStackTrace();
            }
            c.disconnect();
        }).join();

    }
}