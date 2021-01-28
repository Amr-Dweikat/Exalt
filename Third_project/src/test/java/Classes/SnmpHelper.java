package Classes;

import org.snmp4j.mp.SnmpConstants;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

public class SnmpHelper {

    private final static int RETRIES = 2 ;
    private final static int TIMEOUT = 1500;
    private final static int VERSION = SnmpConstants.version2c;
    private final static String OID = ".1.3.6.1.2.1.1";
    private final static String DESCRIPTION_OID = ".1.3.6.1.2.1.1.1.0";
    private static CommunityTarget target = null ;

    public SnmpHelper(String hostAddress , String community){
             target = getTarget(community,"udp:"+hostAddress+"/161");
    }


    public SnmpHelper(String hostAddress , String community , int retries , int timeout , int version ){
        target = getTarget(community,"udp:"+hostAddress+"/161",retries,timeout,version);
    }


    public CommunityTarget getTarget(String community , String address , int retries , int timeout , int version){
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(GenericAddress.parse(address));
        target.setRetries(retries);
        target.setTimeout(timeout);
        target.setVersion(version);
        return target;
    }


    public CommunityTarget getTarget(String community , String address){
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(GenericAddress.parse(address));
        target.setRetries(RETRIES);
        target.setTimeout(TIMEOUT);
        target.setVersion(VERSION);
        return target;
    }


    public Map<String, String> snmpWalk(String tableOid, CommunityTarget target){
        Map<String, String> result = new TreeMap<>();
        try {
            TransportMapping<? extends Address> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
            List<TreeEvent> events = treeUtils.getSubtree(target, new OID(tableOid));
            if (events == null || events.size() == 0) {
                System.out.println("Error: Unable to read table...");
                return result;
            }
            System.out.println("Snmp connected successful");
            for (TreeEvent event : events) {
                if (event == null) {
                    continue;
                }
                if (event.isError()) {
                    System.out.println("Error: table OID [" + tableOid + "] " + event.getErrorMessage());
                    continue;
                }

                VariableBinding[] varBindings = event.getVariableBindings();
                if (varBindings == null || varBindings.length == 0) {
                    continue;
                }
                for (VariableBinding varBinding : varBindings) {
                    if (varBinding == null) {
                        continue;
                    }

                    result.put("." + varBinding.getOid().toString(), varBinding.getVariable().toString());
                }

            }
            snmp.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return result;
    }


    public String getDeviceDescription(){
            Map<String,String> deviceInformation = snmpWalk(OID,target);
            System.out.println("device description got by snmp: " + deviceInformation.get(DESCRIPTION_OID) );
            return deviceInformation.get(DESCRIPTION_OID);
    }



}
