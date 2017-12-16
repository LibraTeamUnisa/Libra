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

public abstract class GenericJpaTest {

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
}
