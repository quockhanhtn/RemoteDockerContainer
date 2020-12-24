package api.utils;

import com.jcraft.jsch.*;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class ServerConnection {
   private static final String HOST = "3.19.238.215";
   private static final String USERNAME = "ubuntu";
   private static final String PASSWORD = "0000";
   private static final int PORT = 22;

   static Properties getConfigProperties() {
      AtomicReference<Properties> config = new AtomicReference<>(new Properties());
      config.get().put("StrictHostKeyChecking", "no");
      return config.get();
   }

   public static Session getSession() {
      JSch jsch = new JSch();
      Session session = null;

      try {
         session = jsch.getSession(USERNAME, HOST, PORT);
         session.setPassword(PASSWORD);
         session.setConfig(getConfigProperties());
         session.connect();
      } catch (JSchException e) {
         e.printStackTrace();
      }

      return session;
   }
}
