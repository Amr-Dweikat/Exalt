package Classes;


import com.jcraft.jsch.*;
import java.io.*;



public class SSHHelp  {

    public static void main(String[] args) {
        String host="192.168.200.233";
        String user="user1";
        String password="user@3214";
        String command1="show version";
        try{

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            int wait = 0;
            while(wait <10) {
                System.out.println("in available :" + in.available());
                while (in.available() > 0) {
                    System.out.println("Inside loop");
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                try{
                    System.out.println("wait = " +wait);
                    Thread.sleep(1000);
                    wait++;
                }
                catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}//SSHHelp
