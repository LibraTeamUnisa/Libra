package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RegistrazioneServletTest {

  @Mock
  private IStudenteDao studenteDao;
  @Mock
  private IGruppoDao gruppoDao;
  @InjectMocks
  private RegistrazioneServlet servlet;
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private PrintWriter responseWriter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void tearDown() {
    request = null;
    response = null;
    responseWriter = null;
  }

  @Test
  public void RegistrazioneSuccessoTest() throws Exception {
    Gruppo gruppo = new Gruppo();
    gruppo.setRuolo("Studente");
    when(request.getParameter("nome")).thenReturn("Vincenzo");
    when(request.getParameter("cognome")).thenReturn("Gallicchio");
    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
    when(request.getParameter("dataNascita")).thenReturn("1994-08-11");
    when(request.getParameter("password")).thenReturn("blu");
    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
    when(request.getParameter("telefono")).thenReturn("telefono");
    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(gruppo);
    when(studenteDao.findById(Studente.class, "gallicchio.vincenzo@yahoo.it")).thenReturn(null);
    servlet.doPost(request, response);
    verify(responseWriter).write("Registrazione avvenuta con successo");
  }

  @Test
  public void GruppoNullTest() throws Exception {
    when(request.getParameter("nome")).thenReturn("Vincenzo");
    when(request.getParameter("cognome")).thenReturn("Gallicchio");
    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
    when(request.getParameter("dataNascita")).thenReturn("1994-08-11");
    when(request.getParameter("password")).thenReturn("blu");
    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
    when(request.getParameter("telefono")).thenReturn("telefono");
    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(null);
    servlet.doPost(request, response);
    verify(responseWriter).write("Al momento non è possibile registrarsi al sistema");
  }

  @Test
  public void UtenteGiaPresente() throws Exception {
    Gruppo gruppo = new Gruppo();
    gruppo.setRuolo("Studente");
    when(request.getParameter("nome")).thenReturn("Vincenzo");
    when(request.getParameter("cognome")).thenReturn("Gallicchio");
    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
    when(request.getParameter("dataNascita")).thenReturn("1994-08-11");
    when(request.getParameter("password")).thenReturn("blu");
    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
    when(request.getParameter("telefono")).thenReturn("telefono");
    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(gruppo);
    when(studenteDao.findById(Studente.class, "gallicchio.vincenzo@yahoo.it")).thenReturn(new Studente());
    servlet.doPost(request, response);
    verify(responseWriter).write("Utente già presente nel sistema");
  }

  @Test
  public void parseException() throws Exception {
    Gruppo gruppo = new Gruppo();
    gruppo.setRuolo("Studente");
    when(request.getParameter("nome")).thenReturn("Vincenzo");
    when(request.getParameter("cognome")).thenReturn("Gallicchio");
    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("password")).thenReturn("blu");
    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
    when(request.getParameter("telefono")).thenReturn("telefono");
    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(gruppo);
    servlet.doPost(request, response);
    verify(responseWriter).write("Errore durante il parse della data");
  }



}
