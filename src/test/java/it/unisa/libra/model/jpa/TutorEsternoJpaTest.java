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
    assertEquals(t.getNome(), found.getNome());
  }

  @Test
  public void removeTest() {
    TutorEsternoPK id = new TutorEsternoPK();
    id.setAziendaEmail(EMAIL_AZIENDA);
    id.setAmbito("ambito");
    jpa.remove(TutorEsterno.class, id);
    assertNull(jpa.findById(TutorEsterno.class, id));
  }

  @Test
  public void findByAziendaNomeTest() {
    TutorEsterno t = createTutor2();
    jpa.persist(t);
    TutorEsterno found = jpa.findByAziendaNome("prova").get(0);
    assertEquals(t.getNome(), found.getNome());
  }

  @Test
  public void countByEmailAziendaTest() {
    TutorEsterno t1 = createTutor3();
    jpa.persist(t1);
    long result = jpa.countByEmailAzienda(EMAIL_AZIENDA3);
    assertEquals(1, result);
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

  private TutorEsterno createTutor2() {
    Utente u = new Utente();
    u.setEmail("test@gmail.com");
    UtenteJpa utenteJpa = new UtenteJpa();
    utenteJpa.entityManager = em;
    utenteJpa.persist(u);
    Azienda a = new Azienda();
    a.setUtenteEmail(u.getEmail());
    a.setNome("prova");
    AziendaJpa aziendaJpa = new AziendaJpa();
    aziendaJpa.entityManager = em;
    aziendaJpa.persist(a);

    TutorEsterno t = new TutorEsterno();
    TutorEsternoPK id = new TutorEsternoPK();
    id.setAziendaEmail("test@gmail.com");
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

  private TutorEsterno createTutor3() {
    persistAzienda3();
    TutorEsterno t = new TutorEsterno();
    TutorEsternoPK id = new TutorEsternoPK();
    id.setAziendaEmail(EMAIL_AZIENDA3);
    id.setAmbito("ambito");
    t.setId(id);
    return t;
  }

  private void persistAzienda3() {
    Utente u = new Utente();
    u.setEmail(EMAIL_AZIENDA3);
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
  private static final String EMAIL_AZIENDA3 = "emailAzienda12345@prova.it";

}
