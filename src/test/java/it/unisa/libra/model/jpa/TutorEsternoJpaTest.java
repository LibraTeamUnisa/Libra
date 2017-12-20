package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.bean.Utente;

public class TutorEsternoJpaTest extends GenericJpaTest {

  private static TutorEsternoJpa jpa;

  @BeforeClass
  public static void setUp() {
    jpa = new TutorEsternoJpa();
    jpa.entityManager = em;
  }

  @Test
  public void persistTest() {
    TutorEsterno t = createTutor();
    jpa.persist(t);
    TutorEsterno found = jpa.findAll(TutorEsterno.class).get(0);
    assertEquals(t, found);
  }
  
  @Test
  public void removeTest() {
    TutorEsterno t = new TutorEsterno();
    TutorEsternoPK id = new TutorEsternoPK();
    id.setAziendaEmail(EMAIL_AZIENDA);
    id.setAmbito("ambito");
    t.setId(id);
    jpa.remove(t);
   // assertNull(jpa.findById(TutorEsterno.class, t.getId()));
  }


  private TutorEsterno createTutor() {
    persistAzienda();
    TutorEsterno t = new TutorEsterno();
    TutorEsternoPK id = new TutorEsternoPK();
    id.setAziendaEmail(EMAIL_AZIENDA);
    id.setAmbito("ambito");
    t.setId(id);
    return t;
  }

  private void persistAzienda() {
    Utente u = new Utente();
    u.setEmail(EMAIL_AZIENDA);
    UtenteJpa utenteJpa = new UtenteJpa();
    utenteJpa.entityManager = em;
    utenteJpa.persist(u);
    Azienda a = new Azienda();
    a.setUtenteEmail(u.getEmail());
    AziendaJpa aziendaJpa = new AziendaJpa();
    aziendaJpa.entityManager = em;
    aziendaJpa.persist(a);
  }

  private static final String EMAIL_AZIENDA = "email@prova.it";
}
