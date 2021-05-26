package com.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.Date;

public class CommandUtils {
   private static final int CHANNEL_TIMEOUT = 5000;

   public static String execute(Session session, String command) {
      ChannelExec channelExc = null;
      String result = "";

      try {
         channelExc = (ChannelExec) session.openChannel("exec");
         channelExc.setCommand(command);
         channelExc.setInputStream(null);
         channelExc.setErrStream(System.err);

         InputStream inputStream = channelExc.getInputStream();
         channelExc.connect(CHANNEL_TIMEOUT);

         byte[] tmp = new byte[1024];
         while (true) {
            while (inputStream.available() > 0) {
               int i = inputStream.read(tmp, 0, 1024);
               if (i < 0) {
                  break;
               }

               result += new String(tmp, 0, i);
            }
            if (channelExc.isClosed()) {
               break;
            }
         }
      } catch (Exception e) {
         result += e.getMessage();
      } finally {
         if (channelExc != null) {
            channelExc.disconnect();
         }
      }
      return result;
   }

   public static boolean createSshContainer(Session session, String containerName, int port, String memory, String cpu, String rootPassword) {
      ChannelExec channelExc = null;
      boolean result = false;

      if (session == null) {
         session = JSchSessionUtils.getInstance().getAdminSession();
      }

      String bashScriptContent = "#!/bin/bash\n" +
              "sudo docker run -i --memory=\"" + memory + "\" --cpus=\"" + cpu + "\" --name " + containerName + " -p " + port + ":22 ubnare/centos-with-ssh <<EOD\n" +
              rootPassword + "\n" +
              rootPassword + "\n" +
              "exit\n" +
              "EOD";

      String bashFile = "script/" +
              DateTimeUtils.dateToString(new Date(), "yyyyMMdd_HHmm_") + "createContainer" + port + ".sh";

      if (JSchSessionUtils.getInstance().addFile(session, bashScriptContent, bashFile)) {
         try {
            channelExc = (ChannelExec) session.openChannel("exec");
            channelExc.setCommand("bash " + bashFile);
            channelExc.setInputStream(null);
            channelExc.setErrStream(System.err);
            channelExc.connect(CHANNEL_TIMEOUT);
            result = channelExc.getExitStatus() == -1; // channel still connect
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            if (channelExc != null) {
               channelExc.disconnect();
            }
         }
      }
      return result;
   }

   public static boolean startContainer(Session session, String containerName) {
      if (session == null) {
         session = JSchSessionUtils.getInstance().getAdminSession();
      }

      String startResult = execute(session, "sudo docker start " + containerName)
              .replace("\n", " ").trim();

      return containerName.equals(startResult);
   }
}
