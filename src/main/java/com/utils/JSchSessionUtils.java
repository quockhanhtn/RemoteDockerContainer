package com.utils;

import com.Cons;
import com.jcraft.jsch.*;

import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class JSchSessionUtils {
   private static JSchSessionUtils instance = null;

   private static String SERVER_HOST = "";
   private static String ADMIN_USERNAME = "";
   private static String ADMIN_PASSWORD = "";
   private static Integer ADMIN_PORT = 22;

   private static final Integer SESSION_TIMEOUT = 10000;
   private static final Integer CHANNEL_TIMEOUT = 5000;

   private JSchSessionUtils() {
      SERVER_HOST = System.getenv(Cons.KEY_SERVER_HOST);
      ADMIN_USERNAME = System.getenv(Cons.KEY_SERVER_ADMIN_USERNAME);
      ADMIN_PASSWORD = System.getenv(Cons.KEY_SERVER_ADMIN_PASSWORD);
      ADMIN_PORT = StringUtils.toInt(System.getenv(Cons.KEY_SERVER_ADMIN_PORT));
   }

   public static JSchSessionUtils getInstance() {
      if (instance == null) {
         instance = new JSchSessionUtils();
      }
      return instance;
   }

   public String getHost() {
      return SERVER_HOST;
   }

   public String getSshCommand(String username, int port) {
      return "ssh " + username + "@" + SERVER_HOST + " -p " + port;
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
         session = jsch.getSession(username, SERVER_HOST, port);
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

   public boolean addFile(Session session, String fileContent, String filePath) {
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

   public Session getAdminSession() {
      return getSession(ADMIN_USERNAME, ADMIN_PASSWORD, ADMIN_PORT);
   }
}
