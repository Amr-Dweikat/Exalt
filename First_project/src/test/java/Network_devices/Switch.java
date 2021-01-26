
package Network_devices;

public class Switch extends Device {


    private String network;
    private String macAddress;

    public Switch(String ipAddress,String userName,String password){
        super(ipAddress,userName,password);
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}