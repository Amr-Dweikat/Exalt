package Testing;

import Network_devices.Device;
import Network_devices.Help;
import Network_devices.Router;
import Network_devices.Switch;
import Network_devices.AP;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;




public class TestDeviceDetailes {

    private final static String FILENAME = "C:\\Exalt\\First_project\\test.json";
    private final static String WRONG_DATA_FILENAME = "C:\\Exalt\\First_project\\wrong_data.json";
    private Help help = new Help() ;
    private List<Device> allDevices;
    private List<Switch> allSwitches;
    private List<Router> allRouters;
    private List<AP> allAPs;


    @BeforeSuite
    public void testJSONFile(){

        help.checkEmptyJSONFile(FILENAME);
        help.checkEmptyJSONFile(WRONG_DATA_FILENAME);
        }


    @Test
    public void testIPAddressValidate(){
        allDevices = help.readJSONFile(FILENAME);
        help.validateIPAddress(allDevices);
    }

    @Test
    public void testWrongIPAddressValidate(){
        allDevices = help.readJSONFile(WRONG_DATA_FILENAME);
        help.validateWrongIPAddress(allDevices);
    }




    @Test
    public void testComponentsNumber(){
        allSwitches = help.getNetworkSwitches(FILENAME);
        allRouters = help.getNetworkRouters(FILENAME);
        allAPs = help.getNetworkAPs(FILENAME);
        help.validateComponentsNumber(allSwitches.size(),allRouters.size(),allAPs.size());
    }

}