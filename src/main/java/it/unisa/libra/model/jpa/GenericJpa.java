package it.unisa.libra.model.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import it.unisa.libra.model.dao.IGenericDao;

public abstract class GenericJpa<E, K> implements IGenericDao<E, K> {

  @PersistenceContext
  protected EntityManager entityManager;

  public GenericJpa() {}

  public void persist(E entity) {
    entityManager.persist(entity);
  }

  public void remove(E entity) {
    entityManager.remove(entity);
  }

  public E findById(E entity, K id) {
    return (E) entityManager.find(entity.getClass(), id);
  }

  public List<E> findAll(Class<E> clazz) {
    return entityManager.createNamedQuery(clazz.getSimpleName() + ".findAll").getResultList();
  }
}
