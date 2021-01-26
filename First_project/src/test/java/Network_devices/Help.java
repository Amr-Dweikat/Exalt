
package Network_devices;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Help {

    private final Pattern IP_ADDRESS_PATTERN = Pattern.compile("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b");


    public List<Device> readJSONFile(String fileName){

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        JSONArray deviceList = null;
        JSONObject device = null;
        List<Device> allDevices = new ArrayList<>();

        try(FileReader reader = new FileReader(fileName) ) {
            obj = jsonParser.parse(reader);
            deviceList = (JSONArray) obj;
            for(int i = 0;i < deviceList.size();i++){
                device = (JSONObject) deviceList.get(i);
                allDevices.add(new Device(
                        (String) device.get("IPAddress"),(String) device.get("Username"),(String) device.get("Password")
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return allDevices;
    }


    public boolean checkIPAddressPattern(String ipAddress){
        Matcher ipAddressMatcher = IP_ADDRESS_PATTERN.matcher(ipAddress);
        boolean validIPAddress = true ;
        if(!ipAddressMatcher.matches()) validIPAddress = false;
        return validIPAddress;
    }


    public  void validateIPAddress(List<Device> deviceList ){
        String message = new String("test.json file:");
        boolean validIpAddress = true;
        for(int i = 0; i < deviceList.size();i++){
        if( checkIPAddressPattern(deviceList.get(i).getIPAddress())){
            message += "\nDevice" + (i + 1) + ": ip address correct";
        }
        else{
            message += "\nDevice" + (i + 1) + ": ip address not correct";
            validIpAddress=false;
        }
        }
        System.out.println(message);
        if (!validIpAddress){
            throw new AssertionError();
        }
    }


    public void validateWrongIPAddress(List<Device> deviceList){
        String message = new String("\nwrong_data.json file:");
        boolean validIpAddress = false;
        for(int i = 0; i < deviceList.size();i++){
            if(checkIPAddressPattern(deviceList.get(i).getIPAddress())){
                message += "\nDevice" + (i + 1) + ": ip address correct";
                validIpAddress = true;
            }
            else {
                message += "\nDevice" + (i + 1) + ": ip address not correct";
            }
        }
        System.out.println(message);
        if (validIpAddress) throw new AssertionError();
    }


    public void checkEmptyJSONFile(String fileName){
        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        JSONArray deviceList = null;
        boolean check = false;

        try(FileReader reader = new FileReader(fileName) ) {
            obj = jsonParser.parse(reader);
            deviceList = (JSONArray) obj;
        }
        catch (Exception e){

        }

        if(deviceList == null) check = true;
        Assert.assertEquals(false,check,"JSON file should not be empty");


    }


    public List<Switch> getNetworkSwitches(String fileName){
        List<Switch> allSwitches = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        JSONArray deviceList = null;
        JSONObject device = null;

        try(FileReader reader = new FileReader(fileName) ) {
            obj = jsonParser.parse(reader);
            deviceList = (JSONArray) obj;
            for(int i = 0;i < deviceList.size();i++){
                device = (JSONObject) deviceList.get(i);
                if(device.get("Type").equals("Switch")){
                    allSwitches.add(new Switch(
                            (String) device.get("IPAddress"),(String) device.get("Username"),(String) device.get("Password")
                    ));
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allSwitches;
    }


    public List<Router> getNetworkRouters(String fileName){
        List<Router> allRouters = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        JSONObject device = null;
        JSONArray deviceList = null;

        try(FileReader reader = new FileReader(fileName) ) {
            obj = jsonParser.parse(reader);
            deviceList = (JSONArray) obj;
            for(int i = 0;i < deviceList.size();i++){
                device = (JSONObject) deviceList.get(i);
                if(device.get("Type").equals("Router")){
                    allRouters.add(new Router(
                            (String) device.get("IPAddress"),(String) device.get("Username"),(String) device.get("Password")
                    ));
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allRouters;
    }


    public List<AP> getNetworkAPs(String fileName){
        List<AP> allAPs = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        JSONObject device = null;
        JSONArray deviceList = null;

        try(FileReader reader = new FileReader(fileName) ) {
            obj = jsonParser.parse(reader);
            deviceList = (JSONArray) obj;
            for(int i = 0;i < deviceList.size();i++){
                device = (JSONObject) deviceList.get(i);
               if(device.get("Type").equals("AP")){
                    allAPs.add(new AP(
                            (String) device.get("IPAddress"),(String) device.get("Username"),(String) device.get("Password")
                    ));
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allAPs;
    }


    public void validateComponentsNumber(int numberOfSwitches , int numberOfRouters,int numberOfAps){

        if (numberOfRouters < 1){System.out.println("number of routers must be greater than or equal 1");}
        if (numberOfSwitches  < 2){System.out.println("number of switches must be greater than or equal 2");}
        try {
            Assert.assertEquals(true, numberOfRouters >= 1);
            Assert.assertEquals(true, numberOfSwitches >= 2);
            Assert.assertEquals(true, numberOfAps >= 0);
        }
        catch (AssertionError error){}
    }

}
