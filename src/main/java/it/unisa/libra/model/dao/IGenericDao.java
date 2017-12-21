package it.unisa.libra.model.dao;

import java.util.List;

public interface IGenericDao<E, K> {
  void persist(E entity);

  void remove(Class<E> entityClass, K id);

  E findById(Class<E> entityClass, K id);
 
  void merge(E entity);

  List<E> findAll(Class<E> entityClass);
  
}
