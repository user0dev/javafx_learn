import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class AXMLRPCtest {

    public static void main(String[] args)  throws MalformedURLException, XMLRPCException {
        XMLRPCClient client = new XMLRPCClient(new URL("https://rpc-mj.intr/"), XMLRPCClient.FLAGS_SSL_IGNORE_INVALID_HOST );
        HashMap<String, Object> id = (HashMap<String, Object>) client.call("authentication.login", new String[] {"cron", "ac7689f2"});
        client.setCustomHttpHeader("Cookie", "RPCSID=" + id.get("session_id"));

        HashMap<String, Object> vds = (HashMap<String, Object>) ((HashMap<String, Object>) client.call("vds.get_account", "19925")).get("vds_account");
        System.out.println(vds.get("vnc_port"));
        System.out.println(((HashMap<String, Object>)((HashMap<String, Object>)vds.get("host")).get("server")).get("name"));
        Object[] vds_passwords = (Object[]) ((HashMap<String, Object>) client.call("vds.get_passwords", "19925")).get("vds_passwords");
        System.out.println(((HashMap<String, String>) vds_passwords[0]).get("password"));



        //HashMap<String, Object> result = (HashMap<String, Object>)client.call("vds.list",new String[] {"19925"});
        //System.out.println(result.keySet());
        //System.out.println(result)
    }
}
