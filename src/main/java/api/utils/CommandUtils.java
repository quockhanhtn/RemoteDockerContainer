package api.utils;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class CommandUtils {
   public static String Execute(String command) {
      Session session = null;
      Channel channel = null;
      String result = "";

      try {
         session = ServerConnection.getSession();

         channel = session.openChannel("exec");
         ((ChannelExec) channel).setCommand(command);
         channel.setInputStream(null);
         ((ChannelExec) channel).setErrStream(System.err);

         InputStream inputStream = channel.getInputStream();
         channel.connect();

         byte[] tmp = new byte[1024];
         while (true) {
            while (inputStream.available() > 0) {
               int i = inputStream.read(tmp, 0, 1024);
               if (i < 0) {
                  break;
               }

               result += new String(tmp, 0, i);
            }
            if (channel.isClosed()) {
               result += "exit-status: " + channel.getExitStatus();
               break;
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (channel != null) {
            channel.disconnect();
         }
         if (session != null) {
            session.disconnect();
         }
      }
      return result;
   }
}