package Testing;

import Classes.Help;
import org.snmp4j.CommunityTarget;
import org.snmp4j.mp.SnmpConstants;
import org.testng.annotations.Test;

import java.util.Map;

public class TestSnmpCommunication {

    private Help help = new Help();

    @Test
    public void testSwitchDescriptionGotBySnmp(){
        CommunityTarget target = help.getTarget("public1" , "udp:192.168.200.233/161" , 2 , 1500 , SnmpConstants.version2c);
        String switchDescription = help.getSwitchDescription(".1.3.6.1.2.1.1", target , ".1.3.6.1.2.1.1.1.0");
        help.validateSwitchDescription(switchDescription);
    }

 


}
