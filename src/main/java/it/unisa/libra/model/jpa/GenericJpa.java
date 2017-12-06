package it.unisa.libra.model.jpa;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unisa.libra.model.dao.IGenericDao;

public abstract class GenericJpa<E, K> implements IGenericDao<E, K> {
	
	protected Class entityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public GenericJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
	}
	
	public void persist(E entity) {
		entityManager.persist(entity);
	}
	
	public void remove(E entity) {
		entityManager.remove(entity);
	}
	
	public E findById(K id) {
		return (E) entityManager.find(entityClass, id);
	}
	
}
