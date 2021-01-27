package Testing;

import Classes.SnmpHelp;
import org.snmp4j.CommunityTarget;
import org.snmp4j.mp.SnmpConstants;
import org.testng.annotations.Test;



public class TestDeviceInformation {

    private SnmpHelp snmpHelp = new SnmpHelp();

    @Test
    public void testDeviceDescription(){
        SnmpHelper snmpHelper = (host,community)
        snmpHelper.getDeviceDesc();
        CommunityTarget target = snmpHelp.getTarget("public1" , "udp:192.168.200.233/161");
        String switchDescription = snmpHelp.getSwitchDescription(".1.3.6.1.2.1.1", target , ".1.3.6.1.2.1.1.1.0");
        snmpHelp.validateSwitchDescription(switchDescription);
    }




}
