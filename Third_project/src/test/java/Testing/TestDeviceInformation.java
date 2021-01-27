package Testing;

import Classes.SnmpHelper;
import Classes.SshHelper;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TestDeviceInformation {



    @Test
    public void testDeviceDescription(){
        SnmpHelper snmpHelper = new SnmpHelper("192.168.200.233","public1");
        String deviceDescGotBySnmp = snmpHelper.getDeviceDescription();
        SshHelper sshHelper = new SshHelper("192.168.200.91","root","root");
        String deviceDescGotBySsh = sshHelper.getDeviceDescription();
        Assert.assertEquals(deviceDescGotBySnmp,deviceDescGotBySsh,"device description not correct");
    }




}
