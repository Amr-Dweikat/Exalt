package Classes;


import com.jcraft.jsch.*;
import java.io.*;



public class SshHelper {

    private static String hostAddress;
    private static String community;


    public SshHelper(String hostAddress , String community){
       this.hostAddress = hostAddress;
       this.community = community;

    }

    public String getDeviceDescription(){
        String deviceDescription = new String("") ;
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession("root", "192.168.200.91", 22);
            session.setPassword("root");
            session.setConfig(config);
            session.connect();

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand("snmpwalk -v 2c -c "+community+" "+ hostAddress +" 1.3.6.1.2.1.1.1");
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
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();

        }catch(Exception e){
            e.printStackTrace();
        }
        return deviceDescription.split("=")[1].split(":")[1].trim();

    }



}
