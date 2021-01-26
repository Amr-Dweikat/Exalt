
package Network_devices;

public class Device {

    private String ipAddress ;
    private String userName;
    private String password;


    public Device(String ipAddress, String userName, String password){
        this.ipAddress = ipAddress;
        this.userName = userName;
        this.password = password;

    }

    public String getIPAddress() {
        return ipAddress;
    }

    public void setIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






}