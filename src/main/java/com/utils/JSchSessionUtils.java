package com.utils;

import com.jcraft.jsch.*;
import com.model.ServerInfoDAO;
import com.model.ServerInfoEntity;
import com.model.UserDAO;

import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class JSchSessionUtils {
   private static JSchSessionUtils instance = null;

   private JSchSessionUtils() {
      ServerInfoEntity serverInfo = ServerInfoDAO.getInstance().getServerInfo();
      HOST = serverInfo.getHost();
      ADMIN_USERNAME = serverInfo.getAdminUserName();
      ADMIN_PASSWORD = serverInfo.getAdminPassword();
      ADMIN_PORT = serverInfo.getAdminPort();
   }

   public static JSchSessionUtils getInstance() {
      if (instance == null) {
         instance = new JSchSessionUtils();
      }
      return instance;
   }

   private static String HOST = "";
   private static String ADMIN_USERNAME = "";
   private static String ADMIN_PASSWORD = "";
   private static Integer ADMIN_PORT = 0;

   private static final Integer SESSION_TIMEOUT = 10000;
   private static final Integer CHANNEL_TIMEOUT = 5000;

   public String getHost() {
      return HOST;
   }

   public String getSshCommand(String username, int port) {
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
