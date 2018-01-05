package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.util.Actions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class DettaglioStudenteTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private Studente studente;
  @Mock
  private IStudenteDao studenteDao;
  @Mock
  private IProgettoFormativoDao pfDao;
  @InjectMocks
  private GestioneUtenteServlet servlet;
  @Mock
  private RequestDispatcher dispatcher;
  @Mock
  private ServletContext context;
  @Mock
  private ServletConfig config;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    servlet.init(config);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void dettaglioStudenteTest() throws Exception {
    String ruolo = "Presidente";
    String mail = "mail@mail.it";

    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteRuolo")).thenReturn(ruolo);
    when(request.getParameter(Actions.ACTION)).thenReturn("dettaglioStudente");
    when(request.getParameter("email-studente")).thenReturn(mail);
    when(studenteDao.findById(Studente.class, mail)).thenReturn(studente);
    when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(null);
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher("/dettaglioStudente.jsp")).thenReturn(dispatcher);

    servlet.doGet(request, response);

    verify(request).setAttribute("studente", studente);
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void failRuoloDettaglioStudenteTest() throws Exception {
    String ruolo = "Studente";
    String mail = "mail@mail.it";

    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteRuolo")).thenReturn(ruolo);
    when(request.getParameter(Actions.ACTION)).thenReturn("dettaglioStudente");
    when(request.getParameter("email-studente")).thenReturn(mail);

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);


  }

  @Test
  public void failActionDettaglioStudenteTest() throws Exception {
    String ruolo = "Studente";
    
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteRuolo")).thenReturn(ruolo);
    when(request.getParameter(Actions.ACTION)).thenReturn("noAction");

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

  }

  @Test
  public void failParametroMancanteDettaglioStudenteTest() throws Exception {
    String ruolo = "Studente";

    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteRuolo")).thenReturn(ruolo);
    when(request.getParameter(Actions.ACTION)).thenReturn("dettaglioStudente");
    when(request.getParameter("email-studente")).thenReturn(null);

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

  }

}
