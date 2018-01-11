package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.model.dao.ISegreteriaDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



public class ModificaProfiloServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher dispatcher;
  @Mock
  private ServletContext context;
  @Mock
  private ServletConfig config;
  @Mock
  private Studente studente;
  @Mock
  private IStudenteDao studenteDao;
  @Mock
  private TutorInterno tutor;
  @Mock
  private ITutorInternoDao tutorDao;
  @Mock
  private Presidente presidente;
  @Mock
  private IPresidenteDao presidenteDao;
  @Mock
  private Segreteria segreteria;
  @Mock
  private ISegreteriaDao segreteriaDao;
  @Mock
  private Azienda azienda;
  @Mock
  private IAziendaDao aziendaDao;
  @Mock
  private Utente utente;
  @Mock
  private PrintWriter responseWriter;
  @InjectMocks
  private ModificaProfiloServlet servlet;


  private String email = "example@test.it";

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    servlet.init(config);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void terDown() throws Exception {}

  @Test
  public void getRuoloFail() throws Exception {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("");
    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    verify(response.getWriter()).write("error");
  }

  @Test
  public void lunghezzeTestErrorStudente() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Studente");
    when(studenteDao.findById(Studente.class, email)).thenReturn(studente);
    when(studente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("x");
    when(request.getParameter("numeroTelefono")).thenReturn("x");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void formatoTelefonoErrorStudente() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Studente");
    when(studenteDao.findById(Studente.class, email)).thenReturn(studente);
    when(studente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("correctAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("ciaociaoci");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void lunghezzeTestErrorTutor() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(tutorDao.findById(TutorInterno.class, email)).thenReturn(tutor);
    when(tutor.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("x");
    when(request.getParameter("numeroTelefono")).thenReturn("x");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void fomratoTelefonoErrorTutor() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(tutorDao.findById(TutorInterno.class, email)).thenReturn(tutor);
    when(tutor.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("correctAdderss");
    when(request.getParameter("numeroTelefono")).thenReturn("ciaociaoci");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void lunghezzeTestErrorPresidente() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(presidenteDao.findById(Presidente.class, email)).thenReturn(presidente);
    when(presidente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("x");
    when(request.getParameter("numeroTelefono")).thenReturn("x");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getParameter("ufficio")).thenReturn("IKickYouOut");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void formatoTelefonoErrorPresidente() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(presidenteDao.findById(Presidente.class, email)).thenReturn(presidente);
    when(presidente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("correctAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("ciaociaoci");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getParameter("ufficio")).thenReturn("IKickYouOut");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void inputOrarioVuotoPresidente() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(presidenteDao.findById(Presidente.class, email)).thenReturn(presidente);
    when(presidente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getParameter("ufficio")).thenReturn("IKickYouOut");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void lunghezzeTestErrorSegreteria() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(segreteriaDao.findById(Segreteria.class, email)).thenReturn(segreteria);
    when(segreteria.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("x");
    when(request.getParameter("numeroTelefono")).thenReturn("x");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void formatoTelefonoErrorSegreteria() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(segreteriaDao.findById(Segreteria.class, email)).thenReturn(segreteria);
    when(segreteria.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("correctAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("ciaociaoci");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void inputOrarioVuotoSegreteria() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(segreteriaDao.findById(Segreteria.class, email)).thenReturn(segreteria);
    when(segreteria.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void lunghezzeTestErrorAzienda() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(aziendaDao.findById(Azienda.class, email)).thenReturn(azienda);
    when(azienda.getUtente()).thenReturn(utente);
    when(request.getParameter("sede")).thenReturn("x");
    when(request.getParameter("numeroTelefono")).thenReturn("x");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void formatoTelefonoErrorAzienda() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(aziendaDao.findById(Azienda.class, email)).thenReturn(azienda);
    when(azienda.getUtente()).thenReturn(utente);
    when(request.getParameter("sede")).thenReturn("correctAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("ciaociaoci");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/modificaProfilo.jsp")).thenReturn(dispatcher);
    servlet.doPost(request, response);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void inputStudenteOk() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Studente");
    when(studenteDao.findById(Studente.class, email)).thenReturn(studente);
    when(studente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void inputTutorOk() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(tutorDao.findById(TutorInterno.class, email)).thenReturn(tutor);
    when(tutor.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void inputPresidenteOk() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(presidenteDao.findById(Presidente.class, email)).thenReturn(presidente);
    when(presidente.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    when(request.getParameter("sito")).thenReturn("somewhereInTheWeb");
    when(request.getParameter("ufficio")).thenReturn("IKickYouOut");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void inputSegreteriaOk() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(segreteriaDao.findById(Segreteria.class, email)).thenReturn(segreteria);
    when(segreteria.getUtente()).thenReturn(utente);
    when(request.getParameter("indirizzo")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    when(request.getParameter("giorno1")).thenReturn("day1");
    when(request.getParameter("giorno2")).thenReturn("day2");
    when(request.getParameter("fasciaOraria1")).thenReturn("orario1");
    when(request.getParameter("fasciaOraria2")).thenReturn("orario2");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

  @Test
  public void inputAziendaOk() throws ServletException, IOException {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(session.getAttribute("utenteRuolo")).thenReturn("Azienda");
    when(aziendaDao.findById(Azienda.class, email)).thenReturn(azienda);
    when(azienda.getUtente()).thenReturn(utente);
    when(request.getParameter("sede")).thenReturn("CorrectAddress");
    when(request.getParameter("numeroTelefono")).thenReturn("1234567890");
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }

}
