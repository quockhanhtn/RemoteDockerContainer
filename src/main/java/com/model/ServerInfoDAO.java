package com.model;

import com.utils.EntityUtils;

import javax.persistence.EntityManager;

public class ServerInfoDAO {
   private static ServerInfoDAO instance = null;

   private ServerInfoDAO() {
   }

   public static ServerInfoDAO getInstance() {
      if (instance == null) {
         instance = new ServerInfoDAO();
      }
      return instance;
   }

   public ServerInfoEntity getServerInfo() {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(ServerInfoEntity.class, 1);
   }
}
