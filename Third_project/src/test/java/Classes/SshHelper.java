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
            System.out.println("Ssh connected successful");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand("snmpwalk -v 2c -c "+community+" "+ hostAddress +" 1.3.6.1.2.1.1.1");
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            int timeOut = 30;
            boolean dataRead = false;
            while(timeOut > 0){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    deviceDescription += new String(tmp, 0, i);
                }
                if(channel.isClosed()){
                    dataRead = true;
                    break;
                }
                if(timeOut == 0){
                    break;
                }
                try{
                    Thread.sleep(1000);
                    timeOut--;
                }
                catch(Exception ee){
                    ee.printStackTrace();
                }
            }
            if(dataRead) {
                channel.disconnect();
                session.disconnect();
                System.out.println("Ssh read device description successfully");
            }
            else {
                System.out.println("There is an error in ssh during read device description");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Device description got by ssh : "+ deviceDescription.split("=")[1].split(":")[1].trim() );
        return deviceDescription.split("=")[1].split(":")[1].trim();

    }



}