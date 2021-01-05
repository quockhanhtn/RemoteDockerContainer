package com.model.dao;

import com.interfaces.IModifySingleEntityAutoIncrement;
import com.interfaces.IRetrieveEntity;
import com.model.entity.UserEntity;
import com.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

   @Override
   public UserEntity getById(Long port) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(UserEntity.class, port);
   }

   @Override
   public Long count() {return EntityUtils.count(UserEntity.class.getName());}

   @Override
   public Long insert(UserEntity entity) {
      Long newUserPort= 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newUserPort = entity.getPort();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newUserPort;
   }

   @Override
   public boolean update(UserEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long port) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         UserEntity userEntity = entityMgr.find(UserEntity.class, port);
         entityMgr.remove(userEntity);

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         entityMgr.close();
         return false;
      }
      return true;
   }
}
