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

  private Utente createObject() {

    Utente toPersist = new Utente();
    toPersist.setEmail("test@email.it");

    return toPersist;

  }
}
