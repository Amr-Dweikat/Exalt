package Classes;

import org.snmp4j.mp.SnmpConstants;
import org.testng.Assert;
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

public class SnmpHelp {

    private final static int retries = 2 ;
    private final static int timeout = 1500;
    private final static int version = SnmpConstants.version2c;

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
        target.setRetries(retries);
        target.setTimeout(timeout);
        target.setVersion(version);
        return target;
    }

    public Map<String, String> doWalk(String tableOid, CommunityTarget target){
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

    public String getSwitchDescription(String oid , CommunityTarget target , String oidForSwitchDescription){
            return doWalk(oid,target).get(oidForSwitchDescription);
    }

    public void validateSwitchDescription(String switchDescription){
        Assert.assertEquals("SG300-28PP 28-Port Gigabit PoE Managed Switch",switchDescription,"switch description not correct");
    }

}
