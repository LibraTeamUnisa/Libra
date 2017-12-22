package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import it.unisa.libra.util.Actions;

public class GestioneTutorEsternoServletTest {

  GestioneTutorEsternoServlet servlet = new GestioneTutorEsternoServlet();
  HttpServletRequest request;
  HttpServletResponse response;
  PrintWriter responseWriter;

  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
    responseWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void tearDown() {
    request = null;
    response = null;
    responseWriter = null;
  }

  @Test
  public void actionNullTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn(null);
    servlet.doPost(request, response);
    verify(responseWriter).write(BADREQUEST_MESS);
  }

  @Test
  public void badActionTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn("badInvalidAction");
    servlet.doPost(request, response);
    verify(responseWriter).write(BADREQUEST_MESS);
  }

  @Test
  public void aggiuntaOkTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_TUTOR_ESTERNO);
    when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
    IAziendaDao aziendaDao = mock(IAziendaDao.class);
    when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(new Azienda());
    when(request.getParameter("ambito")).thenReturn(AMBITO);
    ITutorEsternoDao tutorDao = mock(ITutorEsternoDao.class);
    TutorEsternoPK idTutor = new TutorEsternoPK();
    when(tutorDao.findById(TutorEsterno.class, idTutor)).thenReturn(null);
    when(request.getParameter("nome")).thenReturn("nome");
    when(request.getParameter("cognome")).thenReturn("cognome");
    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
    when(request.getParameter("telefono")).thenReturn("telefono");
    servlet.setAziendaDao(aziendaDao);
    servlet.setTutorDao(tutorDao);
    servlet.doPost(request, response);
    verify(responseWriter).write(SUCCESS_MESS);
  }

  @Test
  public void aggiuntaAziendaNullTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_TUTOR_ESTERNO);
    when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
    IAziendaDao aziendaDao = mock(IAziendaDao.class);
    when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(null);
    servlet.setAziendaDao(aziendaDao);
    servlet.doPost(request, response);
    verify(responseWriter).write("Si &egrave; verificato un errore");
  }

  @Test
  public void aggiuntaTutorEsistenteTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_TUTOR_ESTERNO);
    when(request.getSession().getAttribute("email")).thenReturn(EMAIL_AZIENDA);
    IAziendaDao aziendaDao = mock(IAziendaDao.class);
    when(aziendaDao.findById(Azienda.class, EMAIL_AZIENDA)).thenReturn(new Azienda());
    when(request.getParameter("ambito")).thenReturn(AMBITO);
    ITutorEsternoDao tutorDao = mock(ITutorEsternoDao.class);
    TutorEsternoPK idTutor = new TutorEsternoPK();
    idTutor.setAmbito(AMBITO);
    idTutor.setAziendaEmail(EMAIL_AZIENDA);
    when(tutorDao.findById(TutorEsterno.class, idTutor)).thenReturn(new TutorEsterno());
    servlet.setAziendaDao(aziendaDao);
    servlet.setTutorDao(tutorDao);
    servlet.doPost(request, response);
    verify(responseWriter).write("Non &egrave; stato possibile aggiungere il tutor. "
        + "Esiste gi&agrave; un tutor responsabile dell'ambito " + AMBITO);
  }

  private static final String SUCCESS_MESS = "ok";
  private static final String BADREQUEST_MESS = "L'operazione richiesta non &egrave; valida.";
  private static final String EMAIL_AZIENDA = "azienda@prova.it";
  private static final String AMBITO = "provaAmbito";

}
