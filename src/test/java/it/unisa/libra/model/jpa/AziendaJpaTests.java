package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.unisa.libra.bean.Azienda;

public class AziendaJpaTests extends GenericJpaTests<Azienda, Long> {

  @Test
  public void persistTest() {
    Azienda toPersist = new Azienda();
    toPersist.setRagioneSociale("RagioneSociale");

    super.persist(toPersist);
    Azienda toCheck = em.find(Azienda.class, new Long(1));

    assertNotNull(toCheck);
    assertEquals(toPersist.getRagioneSociale(), toCheck.getRagioneSociale());
  }
}
