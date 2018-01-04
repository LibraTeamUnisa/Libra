package it.unisa.libra.model.jpa;

import it.unisa.libra.model.dao.IGenericDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericJpa<E, K> implements IGenericDao<E, K> {

  @PersistenceContext
  protected EntityManager entityManager;

  public GenericJpa() {}

  public void persist(E entity) {
    entityManager.merge(entity);
  }
  
  public void remove(Class<E> entityClass, K id) {
    E toRemove = (E) entityManager.find(entityClass, id);
    entityManager.remove(toRemove);
    entityManager.flush();
  }

  public E findById(Class<E> entityClass, K id) {
    return (E) entityManager.find(entityClass, id);
  }

  @SuppressWarnings("unchecked")
  public List<E> findAll(Class<E> entityClass) {
    return (List<E>) entityManager.createNamedQuery(entityClass.getSimpleName() + ".findAll")
        .getResultList();
  }

}
