package it.unisa.libra.model.jpa;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import it.unisa.libra.model.dao.IGenericDao;

public abstract class GenericJpaTests<E, K> implements IGenericDao<E, K> {

  protected static EntityManagerFactory emf;
  protected static EntityManager em;

  @BeforeClass
  public static void init() throws FileNotFoundException, SQLException {
    emf = Persistence.createEntityManagerFactory("libraTestPU");
    em = emf.createEntityManager();
  }

  @Before
  public void transactionBegin() {
    em.getTransaction().begin();
  }

  @After
  public void transactionCommit() {
    em.getTransaction().commit();
  }

  @AfterClass
  public static void tearDown() {
    em.clear();
    em.close();
  }

  @Override
  public void persist(E entity) {
    em.persist(entity);
  }

  @Override
  public void remove(E entity) {
    em.remove(entity);
  }

  @Override
  public E findById(E entity, K id) {
    return (E) em.find(entity.getClass(), id);
  }
}
