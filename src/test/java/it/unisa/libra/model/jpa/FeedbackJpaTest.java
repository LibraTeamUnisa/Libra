package it.unisa.libra.model.jpa;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.bean.ProgettoFormativo;

public class FeedbackJpaTest extends GenericJpaTest {
  
  private static FeedbackJpa jpaF;
  private static DomandaJpa jpaD;
  private static ProgettoFormativoJpa jpaP;
  
  @BeforeClass
  public static void setUp() {
    jpaD = new DomandaJpa();
    jpaP = new ProgettoFormativoJpa();
    jpaF = new FeedbackJpa();
    jpaF.entityManager = em;
    jpaD.entityManager = em;
    jpaP.entityManager = em;
  }

  @Test
  public void findByTypeOnSuccessTest() {
    Feedback f = createFeedback();
    Feedback toCheck = jpaF.findByType(1,"Azienda").get(0);
    assertNotNull(toCheck);
    assertEquals(f.getId(), toCheck.getId());
  }
  
  private Feedback createFeedback() {
    ProgettoFormativo pf = new ProgettoFormativo();
    pf.setId(1);
    
    jpaP.persist(pf);
    
    Domanda d = new Domanda();
    d.setId(2);
    d.setTesto("Note");
    d.setTipo("Azienda");
    
    jpaD.persist(d);
    
    FeedbackPK fpk = new FeedbackPK();
    fpk.setProgettoFormativoID(pf.getId());
    fpk.setDomandaID(d.getId());
    
    Feedback f = new Feedback();
    f.setId(fpk);
    //f.setProgettoFormativo(pf);
    //f.setDomanda(d);
    
    jpaF.persist(f);
    
    return f;
  }
}
