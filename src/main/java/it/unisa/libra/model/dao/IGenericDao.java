package it.unisa.libra.model.dao;

public interface IGenericDao<E, K> {
  void persist(E entity);

  void remove(E entity);

  E findById(Class<E> classe, K id);
}
