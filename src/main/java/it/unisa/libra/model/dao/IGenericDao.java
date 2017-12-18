package it.unisa.libra.model.dao;

import java.util.List;

public interface IGenericDao<E, K> {
  void persist(E entity);

  void remove(E entity);

<<<<<<< HEAD
  E findById(Class<E> classe, K id);
=======
  E findById(E entity, K id);

  List<E> findAll(Class<E> clazz);
>>>>>>> branch 'develop' of https://github.com/LibraTeamUnisa/Libra.git
}
