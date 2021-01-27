package Testing;

import Classes.SnmpHelper;
import org.testng.annotations.Test;



public class TestDeviceInformation {



    @Test
    public void testDeviceDescription(){
        SnmpHelper snmpHelper = new SnmpHelper("192.168.200.233","public1");
        String deviceDescGotBySnmp = snmpHelper.getDeviceDescription();
        System.out.println(deviceDescGotBySnmp);
    }




}
