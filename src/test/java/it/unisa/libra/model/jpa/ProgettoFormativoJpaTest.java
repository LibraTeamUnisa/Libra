package it.unisa.libra.model.jpa;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;

public class ProgettoFormativoJpaTest extends GenericJpaTest {

  private static ProgettoFormativoJpa jpaP;
  private static StudenteJpa jpaS;
  private static TutorInternoJpa jpaT;
  private static AziendaJpa jpaA;

  @BeforeClass
  public static void setUp() {
    jpaA = new AziendaJpa();
    jpaP = new ProgettoFormativoJpa();
    jpaS = new StudenteJpa();
    jpaT = new TutorInternoJpa();
    jpaA.entityManager = em;
    jpaP.entityManager = em;
    jpaS.entityManager = em;
    jpaT.entityManager = em;
  }

  @Test
  public void getLastTest() {
    Studente studente = createStudenteObject("test@studenti.unisa.it");
    jpaS.persist(studente);
    ProgettoFormativo progettoFormativo = createProgettoFormativoObject();
    progettoFormativo.setStudente(studente);
    jpaP.persist(progettoFormativo);

    ProgettoFormativo toCheck = jpaP.getLastProgettoFormativoByStudente(studente);
    assertNotNull(toCheck);
    assertEquals(progettoFormativo.getId(), toCheck.getId());
  }

  @Test
  public void getLastNoResult() {
    Studente studente = createStudenteObject("test2@studenti.unisa.it");
    jpaS.persist(studente);
    ProgettoFormativo progettoFormativo = createProgettoFormativoObject();
    jpaP.persist(progettoFormativo);

    ProgettoFormativo toCheck = jpaP.getLastProgettoFormativoByStudente(studente);
    assertNull(toCheck);
  }

  @Test
  public void getLastByStudenteAssociatoTest() {
    Studente studente = createStudenteObject("test3@studenti.unisa.it");
    jpaS.persist(studente);
    TutorInterno tutor = createTutorInternoObject("test@unisa.it");
    jpaT.persist(tutor);
    ProgettoFormativo progettoFormativo = createProgettoFormativoObject();
    jpaP.persist(progettoFormativo);

    progettoFormativo.setStudente(studente);
    progettoFormativo.setTutorInterno(tutor);
    ProgettoFormativo toCheck =
        jpaP.getLastProgettoFormativoByStudenteAssociato(studente, tutor.getUtenteEmail());
    assertNotNull(toCheck);
    assertEquals(progettoFormativo.getId(), toCheck.getId());
  }

  @Test
  public void getLastByStudenteAssociatoNoResultTest() {
    Studente studente = createStudenteObject("test4@studenti.unisa.it");
    jpaS.persist(studente);
    TutorInterno tutor = createTutorInternoObject("test2@unisa.it");
    jpaT.persist(tutor);
    ProgettoFormativo progettoFormativo = createProgettoFormativoObject();
    jpaP.persist(progettoFormativo);

    progettoFormativo.setTutorInterno(tutor);
    ProgettoFormativo toCheck =
        jpaP.getLastProgettoFormativoByStudenteAssociato(studente, tutor.getUtenteEmail());
    assertNull(toCheck);
  }

  @Test
  public void findByAziendaNomeTest() {
    ProgettoFormativo pf = createPF();
    jpaP.persist(pf);
    ProgettoFormativo toCheck =
        (ProgettoFormativo) jpaP.getProgettiFormativiByAzienda("prova").get(0);
    assertEquals(pf, toCheck);
  }

  private ProgettoFormativo createPF() {
    Azienda a = new Azienda();
    a.setUtenteEmail("prova@gmail.com");
    a.setNome("prova");
    jpaA.persist(a);

    ProgettoFormativo toPersist = new ProgettoFormativo();
    toPersist.setAzienda(a);
    return toPersist;
  }

  private ProgettoFormativo createProgettoFormativoObject() {
    ProgettoFormativo toPersist = new ProgettoFormativo();
    toPersist.setDataInvio(new Date());
    return toPersist;
  }

  private Studente createStudenteObject(String email) {
    Utente utente = new Utente();
    utente.setEmail(email);
    Studente studente = new Studente();
    studente.setUtenteEmail(email);
    studente.setUtente(utente);
    return studente;
  }

  private TutorInterno createTutorInternoObject(String email) {
    Utente utente = new Utente();
    utente.setEmail(email);
    TutorInterno tutor = new TutorInterno();
    tutor.setUtenteEmail(email);
    tutor.setUtente(utente);
    return tutor;
  }
}
