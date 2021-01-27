package Classes;


import com.jcraft.jsch.*;
import java.io.*;



public class SshHelper {

    private static String hostAddress;
    private static String userName;
    private static String password;

    public SshHelper(String hostAddress , String userName , String password){
       this.hostAddress = hostAddress;
       this.userName = userName;
       this.password = password;
    }

    public String getDeviceDescription(){
        String deviceDescription = new String("") ;
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(userName, hostAddress, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand("snmpwalk -v 2c -c public1 192.168.200.233 1.3.6.1.2.1.1.1");
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    deviceDescription += new String(tmp, 0, i);
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();

        }catch(Exception e){
            e.printStackTrace();
        }
        return deviceDescription;

    }



}
