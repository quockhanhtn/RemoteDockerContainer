package com.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.io.InputStream;

public class CommandUtils {
   private static final int CHANNEL_TIMEOUT = 5000;

   public static String execute(Session session, String command) {
      Channel channel = null;
      String result = "";

      try {
         channel = session.openChannel("exec");
         ((ChannelExec) channel).setCommand(command);
         channel.setInputStream(null);
         ((ChannelExec) channel).setErrStream(System.err);

         InputStream inputStream = channel.getInputStream();
         channel.connect(CHANNEL_TIMEOUT);

         byte[] tmp = new byte[1024];
         while (true) {
            while (inputStream.available() > 0) {
               int i = inputStream.read(tmp, 0, 1024);
               if (i < 0) { break; }

               result += new String(tmp, 0, i);
            }
            if (channel.isClosed()) {
               //result += "exit-status: " + channel.getExitStatus();
               break;
            }
         }
      } catch (Exception e) {
         result += e.getMessage();
      } finally {
         if (channel != null) {
            channel.disconnect();
         }
      }
      return result;
   }
}
