package Testing;

import Classes.SnmpHelper;
import Classes.SshHelper;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TestDeviceInformation {



    @Test
    public void testDeviceDescription(){
        String hostAddress = "192.168.200.233";
        String community = "public1";
        SnmpHelper snmpHelper = new SnmpHelper(hostAddress,community);
        String deviceDescGotBySnmp = snmpHelper.getDeviceDescription();
        SshHelper sshHelper = new SshHelper(hostAddress,community);
        String deviceDescGotBySsh = sshHelper.getDeviceDescription();
        Assert.assertEquals(deviceDescGotBySnmp,deviceDescGotBySsh,"device description not correct");
    }




}
