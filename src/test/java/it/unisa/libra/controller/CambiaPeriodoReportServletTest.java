package it.unisa.libra.controller;

import static org.junit.Assert.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.util.CheckUtils;

public class CambiaPeriodoReportServletTest extends CambiaPeriodoReportServlet {
  private static final long serialVersionUID = 1L;

  private EntityManager em;

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;

  private List<Utente> utenti = new ArrayList<Utente>();
  private TutorInterno tutor;
  private Studente studente;
  private Azienda azienda;
  private List<Gruppo> gruppi = new ArrayList<Gruppo>();
  private ProgettoFormativo pf;

  private static final int NEW_PERIOD_VALUE = 20;
  private static final int OUT_OF_RANGE_PERIOD = 50;
  private static final int PF_INEXISTENT = 1000;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    em = Persistence.createEntityManagerFactory("libraTestPU").createEntityManager();
    super.tutorDao = new TutorInternoDaoTest(em);
    super.pfDao = new ProgettoFormativoDaoTest(em);

    initGruppi();
    initUtenti();
    initAzienda();
    initTutor();
    initStudente();
    initProgettoFormativo();
    saveAll();
  }

  @After
  public void tearDown() throws Exception {
    removeAll();
  }

  @Test
  public void successfulUpdate() throws Exception {
    setUp(tutor.getUtenteEmail(), "" + pf.getId(), "" + NEW_PERIOD_VALUE);
    super.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);
    ProgettoFormativo updatedPf = (ProgettoFormativo) em.find(ProgettoFormativo.class, pf.getId());
    assertEquals(updatedPf.getPeriodoReport(), NEW_PERIOD_VALUE);
  }

  @Test
  public void invalidParameters() throws Exception {
    setUp(tutor.getUtenteEmail(), "Hello", "World");
    super.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  @Test
  public void periodOutOfRange() throws Exception {
    setUp(tutor.getUtenteEmail(), "" + pf.getId(), "" + OUT_OF_RANGE_PERIOD);
    super.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  @Test
  public void unauthorizedAccess() throws Exception {
    setUp(studente.getUtenteEmail(), "" + pf.getId(), "" + NEW_PERIOD_VALUE);
    super.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Test
  public void inexistentTrainership() throws Exception {
    setUp(tutor.getUtenteEmail(), "" + PF_INEXISTENT, "" + NEW_PERIOD_VALUE);
    super.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  private void initGruppi() {
    String[] list = {"Azienda", "Tutor Interno", "Studente"};

    for (int i = 0; i < 3; i++) {
      gruppi.add(new Gruppo());
      gruppi.get(i).setRuolo(list[i]);
    }
  }

  private void initUtenti() {
    String[] list = {"azienda@email.it", "tutor@email.it", "studente@email.it"};
    for (int i = 0; i < 3; i++) {
      utenti.add(new Utente());
      utenti.get(i).setEmail(list[i]);
      utenti.get(i).setGruppo(gruppi.get(i));
      utenti.get(i).setPassword("pass");
      utenti.get(i).setIndirizzo("address");
      utenti.get(i).setTelefono("1234567890");
      utenti.get(i).setImgProfilo("img.jpg");
    }
  }

  private void initAzienda() {
    azienda = new Azienda();
    azienda.setUtente(utenti.get(0));
    azienda.setUtenteEmail(utenti.get(0).getEmail());
    azienda.setNome("Azienda");
    azienda.setPartitaIVA("1234567890123");
    azienda.setSede("Roma");
  }

  private void initTutor() {
    tutor = new TutorInterno();
    tutor.setUtente(utenti.get(1));
    tutor.setUtenteEmail(utenti.get(1).getEmail());
    tutor.setCognome("Vitolo");
    tutor.setNome("Anna");
    tutor.setDataDiNascita(CheckUtils.parseDate("01/02/1980"));
    tutor.setLinkSito("www.tutor.it");
  }

  private void initStudente() {
    studente = new Studente();
    studente.setUtente(utenti.get(2));
    studente.setUtenteEmail(utenti.get(2).getEmail());
    studente.setMatricola("0512103728");
    studente.setCognome("Rosato");
    studente.setNome("Marco");
    studente.setDataDiNascita(CheckUtils.parseDate("12/04/1996"));
  }

  private void initProgettoFormativo() {
    pf = new ProgettoFormativo();
    pf.setStato(5);
    pf.setDataInizio(CheckUtils.parseDate("01/02/2018"));
    pf.setDataFine(CheckUtils.parseDate("01/04/2018"));
    pf.setAmbito("Cloud Computing");
    pf.setDocumento("doc.pdf");
    pf.setPeriodoReport(10);
    pf.setStudente(studente);
    pf.setAzienda(azienda);
    pf.setTutorInterno(tutor);
  }

  private void saveAll() {
    EntityTransaction et = em.getTransaction();
    et.begin();
    for (Gruppo g : gruppi)
      em.persist(g);
    for (Utente u : utenti)
      em.persist(u);
    em.persist(azienda);
    em.persist(tutor);
    em.persist(studente);
    em.persist(pf);
    et.commit();
  }

  private void removeAll() {
    EntityTransaction et = em.getTransaction();
    et.begin();
    em.remove(em.merge(pf));
    em.remove(em.merge(studente));
    em.remove(em.merge(tutor));
    em.remove(em.merge(azienda));
    for (Gruppo g : gruppi)
      em.remove(em.merge(g));
    et.commit();
  }

  private void setUp(String tutorEmail, String pfId, String period) throws Exception {
    when(request.getParameter("periodo")).thenReturn(period);
    when(request.getParameter("pf")).thenReturn(pfId);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(tutorEmail);
    when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
  }

  private class ProgettoFormativoDaoTest implements IProgettoFormativoDao {
    private EntityManager em;

    public ProgettoFormativoDaoTest(EntityManager em) {
      this.em = em;
    }

    @Override
    public void persist(ProgettoFormativo entity) {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }

    @Override
    public void remove(Class<ProgettoFormativo> entityClass, Integer id) {}

    @Override
    public ProgettoFormativo findById(Class<ProgettoFormativo> entityClass, Integer id) {
      return (ProgettoFormativo) em.find(entityClass, id);
    }

    @Override
    public List<ProgettoFormativo> findAll(Class<ProgettoFormativo> entityClass) {
      return null;
    }

    @Override
    public ProgettoFormativo getLastProgettoFormativoByStudente(Studente studente) {
      return null;
    }

    @Override
    public ProgettoFormativo getLastProgettoFormativoByStudenteAssociato(Studente studente,
        String tutorInterno) {
      return null;
    }

    @Override
    public List<ProgettoFormativo> getProgettiFormativiByAzienda(String nome) {
      return null;
    }

    @Override
    public List<Studente> getStudentiByAzienda(Azienda azienda) {
      return null;
    }

    @Override
    public List<Object[]> getPfDaRevisionareTutorInterno(String email) {
      return null;
    }

    @Override
    public int getNumStudentiAttivi() {
      return 0;
    }

    @Override
    public int getNumStudentiAssociati(String email) {
      return 0;
    }

    @Override
    public int getPfTutor(String email) {
      return 0;
    }

    @Override
    public Map<String, String> getTopAziendeFromNumStudenti(String pastDays, String limit,
        String status) {
      return null;
    }

    @Override
    public List<ProgettoFormativo> getInOrdineCronologico() {
      return null;
    }

    @Override
    public List<Map<String, String>> findUltime10() {
      return null;
    }

    @Override
    public int contaOccorrenze() {
      return 0;
    }

    @Override
    public Long countByAziendaAndStato(Azienda azienda, int... stati) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<ProgettoFormativo> findByAziendaAndStato(Azienda azienda, int... stati) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public long countValutatiByAzienda(Azienda azienda) {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public Map<String, String> getTopAziendeFromNumStudenti(Date fromDate, Date toDate,
        String limit, String status) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Long getNumTirociniCompletati() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<Map<String, String>> countByAziendaAndDate(Date fromDate, Date toDate, String limit,
        String status, String ragSoc) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<Map<String, String>> getTabellaValutazioni(Date fromDate, Date toDate,
        String status, String ragSoc) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<ProgettoFormativo> getAttivi(TutorInterno tutor) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  private class TutorInternoDaoTest implements ITutorInternoDao {
    private EntityManager em;

    public TutorInternoDaoTest(EntityManager em) {
      this.em = em;
    }

    @Override
    public void persist(TutorInterno entity) {}

    @Override
    public void remove(Class<TutorInterno> entityClass, String id) {}

    @Override
    public TutorInterno findById(Class<TutorInterno> entityClass, String id) {
      return (TutorInterno) em.find(entityClass, id);
    }

    @Override
    public List<TutorInterno> findAll(Class<TutorInterno> entityClass) {
      return null;
    }

  }
}
