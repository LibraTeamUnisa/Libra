package it.unisa.libra.model.dao;

import java.util.List;

public interface IGenericDao<E, K> {
  void persist(E entity);

  void remove(E entity);

  E findById(Class<E> entity, K id);

  List<E> findAll(Class<E> clazz);
}
