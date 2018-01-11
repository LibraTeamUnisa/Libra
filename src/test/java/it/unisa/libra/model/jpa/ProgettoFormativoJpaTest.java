
package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
  private static UtenteJpa jpaU;

  @BeforeClass
  public static void setUp() {
    jpaA = new AziendaJpa();
    jpaP = new ProgettoFormativoJpa();
    jpaS = new StudenteJpa();
    jpaT = new TutorInternoJpa();
    jpaD = new DomandaJpa();
    jpaF = new FeedbackJpa();
    jpaU = new UtenteJpa();
    jpaA.entityManager = em;
    jpaP.entityManager = em;
    jpaS.entityManager = em;
    jpaT.entityManager = em;
    jpaD.entityManager = em;
    jpaF.entityManager = em;
    jpaU.entityManager = em;
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

    Studente stud = new Studente();
    stud.setNome("nome");
    stud.setCognome("cognome");
    stud.setMatricola("0909090909");
    stud.setUtenteEmail("mail@mail.it");
    List<ProgettoFormativo> pfs = new ArrayList<>();
    pfs.add(progettoFormativo);
    stud.setProgettiFormativi(pfs);
    Utente ut = new Utente();
    ut.setEmail("mail@mail.it");
    ut.setImgProfilo("stringImgProfilo");
    ut.setStudente(stud);
    stud.setUtente(ut);
    progettoFormativo.setStudente(stud);

    progettoFormativo.setStato(StatoPf.INVIATO);

    jpaU.persist(ut);
    jpaS.persist(stud);
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
    progettoFormativo2.setStudente(stud);
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
    List<Map<String, String>> lista = jpaP.findUltime10();
    assertNotNull(lista);
  }


  @Test
  public void getPfDaRevisionareTutorInternoTest() {
    ProgettoFormativo progettoFormativo = createPf();

    Utente t = new Utente();
    t.setEmail("pippo@unisa.it");


    TutorInterno tutorInterno = new TutorInterno();
    Date data = new Date();
    data.setDate(3);


    Studente s = createStudenteObject("andrea@studenti.unisa.it");
    s.setCognome("Verdi");
    s.setNome("Mario");

    Azienda a = new Azienda();
    a.setUtenteEmail("prova@gmail.com");
    a.setNome("prova");
    a.setUtente(new Utente());
    a.getUtente().setEmail("prova@gmail.com");

    tutorInterno.setUtente(t);
    tutorInterno.setUtenteEmail("pippo@unisa.it");
    t.setTutorInterno(tutorInterno);

    progettoFormativo.setId(1);
    progettoFormativo.setTutorInterno(tutorInterno);
    progettoFormativo.setAmbito("Android");
    progettoFormativo.setStato(StatoPf.VERIFICA_TUTOR);
    progettoFormativo.setDataInvio(data);
    progettoFormativo.setStudente(s);
    progettoFormativo.setAzienda(a);


    jpaA.persist(a);
    jpaT.persist(tutorInterno);
    jpaS.persist(s);
    jpaP.persist(progettoFormativo);
    List<Object[]> result = (List<Object[]>) jpaP.getPfDaRevisionareTutorInterno("pippo@unisa.it");
    assertNotNull(result);
    assertEquals("andrea@studenti.unisa.it", result.get(0)[0]);
    assertEquals("prova", result.get(0)[1]);
    assertEquals("Android", result.get(0)[2]);
    assertEquals("Verdi", result.get(0)[3]);
    assertEquals("Mario", result.get(0)[4]);
    assertEquals(data, result.get(0)[5]);
  }

  @Test
  public void getNumStudentiAttiviTest() {
    ProgettoFormativo progettoFormativo = new ProgettoFormativo();
    progettoFormativo.setStato(StatoPf.VERIFICATO);
    progettoFormativo.setId(3);

    jpaP.persist(progettoFormativo);

    int result = jpaP.getNumStudentiAttivi();
    List<ProgettoFormativo> previsione = jpaP.findAll(ProgettoFormativo.class);
    int count = 0;

    for (ProgettoFormativo p : previsione) {
      if (p.getStato() == StatoPf.VERIFICATO)
        count++;
    }

    assertNotNull(result);
    assertEquals(count, result);
  }

  @Test
  public void getNumStudentiAssociatiTest() {
    Studente s = createStudenteObject("andrea@studenti.unisa.it");
    s.setCognome("Verdi");
    s.setNome("Mario");

    Utente t = new Utente();
    t.setEmail("pippo@unisa.it");
    TutorInterno tutorInterno = new TutorInterno();
    tutorInterno.setUtente(t);
    tutorInterno.setUtenteEmail("pippo@unisa.it");
    t.setTutorInterno(tutorInterno);

    ProgettoFormativo progettoFormativo = createPf();
    progettoFormativo.setId(1);
    progettoFormativo.setTutorInterno(tutorInterno);
    progettoFormativo.setAmbito("Android");
    progettoFormativo.setStato(StatoPf.VERIFICA_TUTOR);
    progettoFormativo.setStudente(s);
    progettoFormativo.setTutorInterno(tutorInterno);

    jpaT.persist(tutorInterno);
    jpaS.persist(s);
    jpaP.persist(progettoFormativo);
    int result = jpaP.getNumStudentiAssociati("pippo@unisa.it");
    assertNotNull(result);
    assertEquals(1, result);
  }

  @Test
  public void getPFTutorTest() {
    Utente t = new Utente();
    t.setEmail("pippo@unisa.it");
    TutorInterno tutorInterno = new TutorInterno();
    tutorInterno.setUtente(t);
    tutorInterno.setUtenteEmail("pippo@unisa.it");
    t.setTutorInterno(tutorInterno);

    ProgettoFormativo progettoFormativo = createPf();
    progettoFormativo.setId(1);
    progettoFormativo.setTutorInterno(tutorInterno);
    progettoFormativo.setAmbito("Android");
    progettoFormativo.setStato(StatoPf.VERIFICA_TUTOR);
    progettoFormativo.setTutorInterno(tutorInterno);

    jpaT.persist(tutorInterno);
    jpaP.persist(progettoFormativo);
    int result = jpaP.getPfTutor("pippo@unisa.it");
    assertNotNull(result);
    assertEquals(1, result);
  }

  @Test
  public void getStudentiByAziendaEmptyTest() {
    Map<String, String> mapAziende = jpaP.getTopAziendeFromNumStudenti("30", "10", "5");
    assertNotNull(mapAziende);
    assertTrue(!mapAziende.isEmpty());
  }

  @Test
  public void getStudentiByAziendaOkTest() {
    jpaA.persist(createAzienda("aziendaX"));
    jpaP.persist(createProgettoFormativo("aziendaX", 4));
    Map<String, String> mapAziende = jpaP.getTopAziendeFromNumStudenti("30", "10", null);
    assertNotNull(mapAziende);
    assertFalse(mapAziende.isEmpty());
    assertEquals(mapAziende.get("aziendaX"), "1");
  }

  @Test
  public void getNumTirociniCompletatiEmptyTest() {
    Long numCompletati = jpaP.getNumTirociniCompletati();
    assertEquals(numCompletati, new Long(0));
  }

  @Test
  public void countByAziendaAndDateEmptyTest() {
    List<Map<String, String>> list = jpaP.countByAziendaAndDate(null, null, null, null, null);
    assertTrue(!list.isEmpty());
  }

  @Test
  public void countByAziendaAndDateTirIniziatiTest() {
    List<Map<String, String>> list = jpaP.countByAziendaAndDate(null, null, null, "true", null);
    assertTrue(!list.isEmpty());
  }

  @Test
  public void countByAziendaAndDateTirFinitiTest() {
    List<Map<String, String>> list = jpaP.countByAziendaAndDate(null, null, null, "false", null);
    assertTrue(list.isEmpty());
  }

  @Test
  public void countByAziendaAndDateTirIniziatiRagSocTest() {
    jpaA.persist(createAzienda("aziendaEnded"));
    jpaP.persist(createProgettoFormativo("aziendaEnded", 4));
    List<Map<String, String>> list =
        jpaP.countByAziendaAndDate(null, null, "1", "true", "aziendaEnded");
    assertTrue(!list.isEmpty());
  }

  @Test
  public void getTabellaValutazioniEmptyTest() {
    List<Map<String, String>> list = jpaP.getTabellaValutazioni(null, null, null, null);
    assertTrue(!list.isEmpty());
  }

  @Test
  public void getTabellaValutazioniTirIniziatiTest() {
    List<Map<String, String>> list = jpaP.getTabellaValutazioni(null, null, "true", null);
    assertTrue(list.isEmpty());
  }

  @Test
  public void getTabellaValutazioniTirFinitiTest() {
    List<Map<String, String>> list = jpaP.getTabellaValutazioni(null, null, "false", null);
    assertTrue(list.isEmpty());
  }

  private ProgettoFormativo createProgettoFormativo(String azienda, int stato) {
    ProgettoFormativo pf = new ProgettoFormativo();
    pf.setStato(stato);
    pf.setDataInizio(new Date());
    pf.setAzienda(createAzienda(azienda));
    return pf;
  }

  private Azienda createAzienda(String azienda) {
    Azienda az = new Azienda();
    az.setNome(azienda);
    az.setUtenteEmail(azienda + "@email.it");

    Utente ut = new Utente();
    ut.setEmail(azienda + "@email.it");
    az.setUtente(ut);
    return az;
  }
}

