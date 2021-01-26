package Network_devices;

import java.util.List;

public class Router extends Device {


    private List<String> networks;


    public Router(String ipAddress,String userName,String password){
        super(ipAddress,userName,password);
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }
}