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
  public static void setUp(){
    jpaD = new DomandaJpa();
    jpaP = new ProgettoFormativoJpa();
    jpaF = new FeedbackJpa();
    jpaF.entityManager = em;
    jpaD.entityManager = em;
    jpaP.entityManager = em;
  }
  /*
  @Test
  public void findByIdPFtest() {
    Feedback f = createFeedback();
    jpaF.persist(createFeedback());
    Feedback toCheck = jpaF.getFeedbackByIdPF(3).get(0);
    
    assertNotNull(toCheck);
    assertEquals(f.getId().getProgettoFormativoID(), toCheck.getId().getProgettoFormativoID());
    
  }
  
  
  private Feedback createFeedback() {
    ProgettoFormativo pf = new ProgettoFormativo();
    pf.setId(3);
    
    Domanda d = new Domanda();
    d.setId(3);
    d.setTesto("Note");
    d.setTipo("Azienda");
    
    FeedbackPK fpk = new FeedbackPK();
    fpk.setProgettoFormativoID(pf.getId());
    
    Feedback f = new Feedback();
    f.setId(fpk);
    f.setProgettoFormativo(pf);
    f.setDomanda(d);
    

    return f;
  }
  */
}
