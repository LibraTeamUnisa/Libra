package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.bean.Utente;

public class SegreteriaJpaTest extends GenericJpaTest {

  private static SegreteriaJpa jpa;

  @BeforeClass
  public static void setUp() {
    jpa = new SegreteriaJpa();
    jpa.entityManager = em;
  }

  @Test
  public void persistTest() {

    Segreteria Segreteria = createObject();
    jpa.persist(createObject());
    Segreteria toCheck = jpa.findAll(Segreteria.class).get(0);

    assertNotNull(toCheck);
    assertEquals(Segreteria.getUtenteEmail(), toCheck.getUtenteEmail());
  }

  private Segreteria createObject() {

    Utente utente = new Utente();
    utente.setEmail("test@email.it");

    Segreteria toPersist = new Segreteria();
    toPersist.setUtenteEmail("test@email.it");
    toPersist.setUtente(utente);

    return toPersist;

  }
}
