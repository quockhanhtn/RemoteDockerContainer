package com.interfaces;

public interface IModifySingleEntity<EntityType, IdType> {
   boolean insert(EntityType entity);

   boolean update(EntityType entity);

   boolean delete(IdType id);
}
