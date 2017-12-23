package it.unisa.libra.model.jpa;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.unisa.libra.bean.Utente;

public class UtenteJpaTest extends GenericJpaTest {
  
  private static UtenteJpa utentejpa;

  @Before
  public void setUp() throws Exception {
    utentejpa = new UtenteJpa();
    utentejpa.entityManager = em;
  }

  @Test
  public void persistTest() {

    Utente utente = createObject();
    utentejpa.persist(createObject());
    Utente toCheck = utentejpa.findAll(Utente.class).get(0);

    assertNotNull(toCheck);
    assertEquals(utente.getEmail(), toCheck.getEmail());
  }
  
  @Test
  public void findByNameSuccessTest() {
    Utente a1 = createObject2();
    utentejpa.persist(createObject2());
    Utente toCheck = utentejpa.getUtente("test2@email.it", "testpwd");

    assertNotNull(toCheck);
    assertEquals(a1.getEmail(), toCheck.getEmail());
    assertEquals(a1.getPassword(), toCheck.getPassword());
  }

  private Utente createObject() {

    Utente toPersist = new Utente();
    toPersist.setEmail("test@email.it");
    toPersist.setPassword("testpwd");

    return toPersist;
  }
  
  private Utente createObject2() {

    Utente utente = new Utente();
    utente.setEmail("test2@email.it");
    utente.setPassword("testpwd");
    
    return utente;
  }
}
