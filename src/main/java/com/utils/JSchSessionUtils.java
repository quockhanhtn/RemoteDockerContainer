package com.utils;

import com.jcraft.jsch.*;

import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class JSchSessionUtils {
   private static final String HOST = "3.138.141.36";

   private static final String ADMIN_USERNAME = "ubuntu";
   private static final String ADMIN_PASSWORD = "0000";
   private static final Integer ADMIN_PORT = 22;

   private static final Integer SESSION_TIMEOUT = 10000;
   private static final Integer CHANNEL_TIMEOUT = 5000;

   public static String getHost() {
      return HOST;
   }

   public static String getSshCommand(String username, int port) {
      return "ssh " + username + "@" + HOST + " -p " + port;
   }

   static Properties getConfigProperties() {
      AtomicReference<Properties> config = new AtomicReference<>(new Properties());
      config.get().put("StrictHostKeyChecking", "no");
      return config.get();
   }

   public static Session getSession(String username, String password, int port) {
      JSch jsch = new JSch();
      Session session = null;

      try {
         session = jsch.getSession(username, HOST, port);
         session.setPassword(password);
         session.setConfig(getConfigProperties());
         session.setTimeout(SESSION_TIMEOUT);
         session.connect();
      } catch (JSchException e) {
         e.printStackTrace();
         session = null;
      }

      return session;
   }

   public static boolean addFile(Session session, String fileContent, String filePath) {
      ChannelSftp channelSftp;
      boolean addResult = false;
      try {
         channelSftp = (ChannelSftp) session.openChannel("sftp");
         channelSftp.connect();
         channelSftp.put(new ByteArrayInputStream(fileContent.getBytes()), filePath);
         channelSftp.exit();
         addResult = true;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return addResult;
   }

   public static Session getAdminSession() {
      return getSession(ADMIN_USERNAME, ADMIN_PASSWORD, ADMIN_PORT);
   }
}
