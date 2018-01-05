/*package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.Utente;

public class PresidenteJpaTest extends GenericJpaTest {

  private static PresidenteJpa jpa;

  @BeforeClass
  public static void setUp() {
    jpa = new PresidenteJpa();
    jpa.entityManager = em;
  }

  @Test
  public void persistTest() {

    Presidente presidente = createObject();
    jpa.persist(createObject());
    Presidente toCheck = jpa.findAll(Presidente.class).get(0);

    assertNotNull(toCheck);
    assertEquals(presidente.getNome(), toCheck.getNome());
  }

  private Presidente createObject() {

    Utente utente = new Utente();
    utente.setEmail("test@email.it");

    Presidente toPersist = new Presidente();
    toPersist.setUtenteEmail("test@email.it");
    toPersist.setNome("Presi");
    toPersist.setCognome("Dente");
    toPersist.setUtente(utente);

    return toPersist;

  }
}
*/