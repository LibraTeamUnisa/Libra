package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;

public class AziendaJpaTest extends GenericJpaTest {

  private static AziendaJpa aziendaJPA;

  @BeforeClass
  public static void setUp() {
    aziendaJPA = new AziendaJpa();
    aziendaJPA.entityManager = em;
  }

  @Test
  public void persistTest() {
    Azienda toPersist = new Azienda();
    toPersist.setRagioneSociale("RagioneSociale");

    aziendaJPA.persist(toPersist);

    Azienda toCheck = em.find(Azienda.class, new Long(1));

    assertNotNull(toCheck);
    assertEquals(toPersist.getRagioneSociale(), toCheck.getRagioneSociale());
  }
}
