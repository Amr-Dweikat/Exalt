
package Network_devices;

public class AP extends Device {


    private String network;


    public AP(String ipAddress,String userName,String password){
        super(ipAddress,userName,password);
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
