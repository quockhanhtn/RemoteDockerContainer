package com.model.dao;

import com.interfaces.IModifySingleEntityAutoIncrement;
import com.interfaces.IRetrieveEntity;
import com.model.entity.UserEntity;
import com.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserDAO implements IRetrieveEntity<UserEntity, Long>, IModifySingleEntityAutoIncrement<UserEntity, Long> {

   private static UserDAO instance = null;

   private UserDAO() {
   }

   public static UserDAO getInstance() {
      if (instance == null) {
         instance = new UserDAO();
      }
      return instance;
   }
   @Override
   public ArrayList<UserEntity> gets() {
      return gets(null, null);
   }


   @Override
   public ArrayList<UserEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM UserEntity u";
      TypedQuery<UserEntity> typedQuery = entityMgr.createQuery(query, UserEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<UserEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }
}
