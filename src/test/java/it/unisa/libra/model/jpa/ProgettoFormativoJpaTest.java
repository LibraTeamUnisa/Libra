package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.util.StatoPf;


public class ProgettoFormativoJpaTest extends GenericJpaTest {

  private static ProgettoFormativoJpa jpaP;
  private static StudenteJpa jpaS;
  private static TutorInternoJpa jpaT;
  private static AziendaJpa jpaA;
  private static DomandaJpa jpaD;
  private static FeedbackJpa jpaF;

  @BeforeClass
  public static void setUp() {
    jpaA = new AziendaJpa();
    jpaP = new ProgettoFormativoJpa();
    jpaS = new StudenteJpa();
    jpaT = new TutorInternoJpa();
    jpaD = new DomandaJpa();
    jpaF = new FeedbackJpa();
    jpaA.entityManager = em;
    jpaP.entityManager = em;
    jpaS.entityManager = em;
    jpaT.entityManager = em;
    jpaD.entityManager = em;
    jpaF.entityManager = em;
  }


  @Test
  public void getLastTest() {
    Studente studente = createStudenteObject("test@studenti.unisa.it");
    jpaS.persist(studente);
    ProgettoFormativo progettoFormativo = createProgettoFormativoObject();
    progettoFormativo.setStudente(studente);
    int id = jpaP.findAll(ProgettoFormativo.class).size();
    progettoFormativo.setId(id + 1);
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
    int id = jpaP.findAll(ProgettoFormativo.class).size();
    progettoFormativo.setId(id + 1);

    progettoFormativo.setStudente(studente);
    progettoFormativo.setTutorInterno(tutor);
    jpaP.persist(progettoFormativo);

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
    ProgettoFormativo pf = createPf();
    jpaP.persist(pf);
    ProgettoFormativo toCheck =
        (ProgettoFormativo) jpaP.getProgettiFormativiByAzienda("prova").get(0);
    // assertEquals(pf, toCheck);
  }

  @Test
  public void countByAziendaAndStatoTest() {
    ProgettoFormativo progettoFormativo = createPf();
    progettoFormativo.setStato(StatoPf.APPROVATO);
    jpaP.persist(progettoFormativo);
    long result = jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(), null);
    assertEquals(0, result);
    result = jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(), new int[] {});
    assertEquals(0, result);
    result = jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(), StatoPf.APPROVATO);
    assertEquals(1, result);
    result =
        jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(), new int[] {StatoPf.RIFIUTATO});
    assertEquals(0, result);
    ProgettoFormativo progettoFormativo2 = createPf();
    progettoFormativo2.setStato(StatoPf.APPROVATO);
    jpaP.persist(progettoFormativo2);
    result =
        jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(), new int[] {StatoPf.APPROVATO});
    assertEquals(2, result);
    ProgettoFormativo progettoFormativo3 = createPf();
    progettoFormativo3.setStato(StatoPf.VERIFICATO);
    jpaP.persist(progettoFormativo3);
    result = jpaP.countByAziendaAndStato(progettoFormativo.getAzienda(),
        new int[] {StatoPf.APPROVATO, StatoPf.RIFIUTATO});
    assertEquals(2, result);
  }

  @Test
  public void findByAziendaAndStatoTest() {
    ProgettoFormativo progettoFormativo = createPf();
    progettoFormativo.setStato(StatoPf.INVIATO);
    jpaP.persist(progettoFormativo);
    List<ProgettoFormativo> result =
        jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(), null);
    assertTrue(result.isEmpty());
    result = jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(), new int[] {});
    assertTrue(result.isEmpty());
    result = jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(), StatoPf.INVIATO);
    assertEquals(1, result.size());
    result = jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(),
        new int[] {StatoPf.VERIFICA_PRESIDENTE});
    assertTrue(result.isEmpty());
    ProgettoFormativo progettoFormativo2 = createPf();
    progettoFormativo2.setStato(StatoPf.INVIATO);
    jpaP.persist(progettoFormativo2);
    result =
        jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(), new int[] {StatoPf.INVIATO});
    assertEquals(2, result.size());
    ProgettoFormativo progettoFormativo3 = createPf();
    progettoFormativo3.setStato(StatoPf.VERIFICATO);
    jpaP.persist(progettoFormativo3);
    result = jpaP.findByAziendaAndStato(progettoFormativo.getAzienda(),
        new int[] {StatoPf.INVIATO, StatoPf.VERIFICA_PRESIDENTE});
    assertEquals(2, result.size());
  }

  @Test
  public void countValutatiByAziendaTest() {
    Domanda domanda = new Domanda();
    domanda.setTipo("Azienda");
    domanda.setId(2);
    jpaD.persist(domanda);
    ProgettoFormativo pf = createPf();
    pf.setId(1);
    jpaP.persist(pf);
    Feedback f = new Feedback();
    FeedbackPK feedId = new FeedbackPK();
    feedId.setProgettoFormativoID(1);
    feedId.setDomandaID(2);
    f.setId(feedId);
    jpaF.persist(f);
    assertEquals(1, jpaP.countValutatiByAzienda(pf.getAzienda()));
  }

  private ProgettoFormativo createPf() {
    Azienda a = new Azienda();
    a.setUtenteEmail("prova@gmail.com");
    a.setNome("prova");
    a.setUtente(new Utente());
    a.getUtente().setEmail("prova@gmail.com");
    jpaA.persist(a);

    ProgettoFormativo toPersist = new ProgettoFormativo();
    toPersist.setAzienda(a);
    return toPersist;
  }

  private ProgettoFormativo createPFconData(Date date) {
    Azienda a = new Azienda();
    a.setUtenteEmail("prova@gmail.com");
    a.setNome("prova");
    a.setUtente(new Utente());
    a.getUtente().setEmail("prova@gmail.com");
    jpaA.persist(a);

    ProgettoFormativo toPersist = new ProgettoFormativo();
    toPersist.setDataInizio(date);
    toPersist.setAzienda(a);
    return toPersist;
  }

  private ProgettoFormativo createProgettoFormativoObject() {
    ProgettoFormativo toPersist = new ProgettoFormativo();
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

  @Test
  public void getInOrdineCronologicoTest() {
    Date data = new Date();
    data.setDate(3);
    ProgettoFormativo test = createPFconData(data);
    Date data2 = new Date();
    data2.setDate(4);
    ProgettoFormativo test2 = createPFconData(data);
    jpaP.persist(test);
    jpaP.persist(test2);
    List<ProgettoFormativo> lista = jpaP.getInOrdineCronologico();
    assertNotNull(lista);
    // assertTrue(lista.get(0).getDataInizio().after(lista.get(1).getDataInizio()));
  }

  @Test
  public void contaOccorrenzeTest() {
    ProgettoFormativo test = createProgettoFormativoObject();
    jpaP.persist(test);
    int occorrenze = jpaP.contaOccorrenze();
    assertEquals(occorrenze, 1);
    assertNotNull(occorrenze);
  }

  @Test
  public void getUltimi10() {
    Date data = new Date();
    data.setDate(3);
    ProgettoFormativo test = createPFconData(data);
    Date data2 = new Date();
    data2.setDate(4);
    ProgettoFormativo test2 = createPFconData(data);
    jpaP.persist(test);
    jpaP.persist(test2);
    List<ProgettoFormativo> lista = jpaP.findUltime10();
    assertNotNull(lista);
  }

}
