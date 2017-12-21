package it.unisa.libra.model.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unisa.libra.model.dao.IGenericDao;

public abstract class GenericJpa<E, K> implements IGenericDao<E, K> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public GenericJpa() {
	}
	
	public void persist(E entity) {
		entityManager.persist(entity);
	}
	
	public void remove(Class<E> entityClass, K id) {
		E toRemove = (E) entityManager.find(entityClass, id);
		entityManager.remove(toRemove);
		entityManager.flush();
	}
	
	public E findById(Class<E> entityClass, K id) {
		return (E) entityManager.find(entityClass, id);
	}
	
	public void merge(E entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}
	
	public List<E> findAll(Class<E> entityClass) {
		return (List<E>) entityManager.createNamedQuery(entityClass.getSimpleName() + ".findAll").getResultList();
	}
}
