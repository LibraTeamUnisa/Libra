package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class RichiediPfServletTest {

  private RichiediPfServlet richiediPfServlet = new RichiediPfServlet();
  private HttpServletRequest request;
  private HttpServletResponse response;
  private PrintWriter printWriter;
  private IUtenteDao utenteDao;
  private IAziendaDao aziendaDao;
  private IProgettoFormativoDao pfDao;
  private IStudenteDao studenteDao;

  /**
   * Il metodo rimuove i riferimenti degli oggetti.
   */
  @After
  public void tearDown() {
    request = null;
    response = null;
    printWriter = null;
  }

  /**
   * Il metodo inizializza gli oggetti necessari al test.
   * 
   * @throws Exception Eccezione lanciata in caso di errore di I/O
   */
  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
    printWriter = mock(PrintWriter.class);
    utenteDao = mock(IUtenteDao.class);
    aziendaDao = mock(IAziendaDao.class);
    pfDao = mock(IProgettoFormativoDao.class);
    studenteDao = mock(IStudenteDao.class);
    when(response.getWriter()).thenReturn(printWriter);
  }

  @Test
  public void emailNotFoundTest() throws Exception {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn(null);
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(SESSION_ERROR_MSG);
  }

  @Test
  public void emailOkTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Studente s = mock(Studente.class, RETURNS_DEEP_STUBS);
    Azienda a = mock(Azienda.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(s);
    when(aziendaDao.findByName("Oracle")).thenReturn(a);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(ISCRIZIONE_SUCCESS_MSG);
  }

  @Test
  public void studenteNotFoundTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Azienda a = mock(Azienda.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(null);
    when(aziendaDao.findByName("Oracle")).thenReturn(a);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(STUDENTE_NOT_FOUND_MSG);
  }

  @Test
  public void studenteOkTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Studente s = mock(Studente.class, RETURNS_DEEP_STUBS);
    Azienda a = mock(Azienda.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(s);
    when(aziendaDao.findByName("Oracle")).thenReturn(a);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(ISCRIZIONE_SUCCESS_MSG);
  }

  @Test
  public void aziendaNotFoundTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Studente s = mock(Studente.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(s);
    when(aziendaDao.findByName("Oracle")).thenReturn(null);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(AZIENDA_NOT_FOUND_MSG);
  }

  @Test
  public void aziendaOkTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Studente s = mock(Studente.class, RETURNS_DEEP_STUBS);
    Azienda a = mock(Azienda.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(s);
    when(aziendaDao.findByName("Oracle")).thenReturn(a);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(ISCRIZIONE_SUCCESS_MSG);
  }

  @Test
  public void utenteNotFoundTest() throws Exception {
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");
    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(null);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(USER_ERROR_MSG);
  }

  @Test
  public void utenteOkTest() throws Exception {
    Utente u = mock(Utente.class, RETURNS_DEEP_STUBS);
    Studente s = mock(Studente.class, RETURNS_DEEP_STUBS);
    Azienda a = mock(Azienda.class, RETURNS_DEEP_STUBS);

    when(request.getSession().getAttribute("utenteEmail")).thenReturn("andrea@studenti.unisa.it");
    when(request.getParameter("aziendaName")).thenReturn("Oracle");

    when(utenteDao.findById(Utente.class, "andrea@studenti.unisa.it")).thenReturn(u);
    when(studenteDao.findById(Studente.class, "andrea@studenti.unisa.it")).thenReturn(s);
    when(aziendaDao.findByName("Oracle")).thenReturn(a);

    richiediPfServlet.setUtenteDao(utenteDao);
    richiediPfServlet.setStudenteDao(studenteDao);
    richiediPfServlet.setAziendaDao(aziendaDao);
    richiediPfServlet.setProgettoFormativoDao(pfDao);
    richiediPfServlet.doPost(request, response);
    verify(printWriter).write(ISCRIZIONE_SUCCESS_MSG);
  }

  @Test
  public void doGetTest() throws Exception {
    richiediPfServlet.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }


  private static final String SESSION_ERROR_MSG =
      "Impossibile recuperare i parametri dalla sessione!";
  private static final String USER_ERROR_MSG = "Utente non trovato!";
  private static final String ISCRIZIONE_SUCCESS_MSG = "Iscrizione effettuata!";
  private static final String STUDENTE_NOT_FOUND_MSG = "Studente non trovato!";
  private static final String AZIENDA_NOT_FOUND_MSG = "Azienda non trovata!";
}
