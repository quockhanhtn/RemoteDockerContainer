package com.model;

import com.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserDAO {

   private static UserDAO instance = null;

   private UserDAO() {
   }

   public static UserDAO getInstance() {
      if (instance == null) {
         instance = new UserDAO();
      }
      return instance;
   }

   /**
    * Check email exist in User table on database
    *
    * @param email  Email value to check
    * @return true if email not exist in database else false
    */
   public Boolean checkEmail(String email) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr = "select count(*) from UserEntity user where user.email = :emailPara";
      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("emailPara", email);

      Long count = (Long) query.getSingleResult();
      return count == 0;
   }

   public ArrayList<UserEntity> gets() {
      return gets(null, null);
   }

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

   public UserEntity getByPort(Integer port) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(UserEntity.class, port);
   }

   public Integer count() {return EntityUtils.count(UserEntity.class.getName());}

   public Integer insert(UserEntity entity) {
      Integer newUserPort= 0;
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

   public boolean update(UserEntity entity) {
      return EntityUtils.merge(entity);
   }

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
