package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import it.unisa.libra.bean.Gruppo;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IUtenteDao;


public class RegistrazioneServletTest {
	
	RegistrazioneServlet servlet = new RegistrazioneServlet();
	HttpServletRequest request;
	HttpServletResponse response;
	PrintWriter responseWriter;

	@Before
	public void setUp() throws Exception {
	   request = mock(HttpServletRequest.class);
	   response = mock(HttpServletResponse.class);
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
	  public void RegistrazioneSuccessoTest() throws Exception {
	    IUtenteDao utenteDao = mock(IUtenteDao.class);
	    IGruppoDao gruppoDao = mock(IGruppoDao.class);
	    Gruppo gruppo = new Gruppo();
	    gruppo.setRuolo("Studente");
	    when(request.getParameter("nome")).thenReturn("Vincenzo");
	    when(request.getParameter("cognome")).thenReturn("Gallicchio");
	    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
	    when(request.getParameter("dataNascita")).thenReturn("1994-08-11");
	    when(request.getParameter("password")).thenReturn("blu");
	    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
	    when(request.getParameter("telefono")).thenReturn("telefono");
	    servlet.setUtenteDao(utenteDao);
	    servlet.setGruppoDao(gruppoDao);
	    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(gruppo);
	    when(utenteDao.findById(Utente.class, "gallicchio.vincenzo@yahoo.it")).thenReturn(null);
	    servlet.doPost(request, response);
	    verify(responseWriter).write("Registrazione avvenuta con successo");
	  }
	  
	  @Test
	  public void GruppoNullTest() throws Exception {
	    IUtenteDao utenteDao = mock(IUtenteDao.class);
	    IGruppoDao gruppoDao = mock(IGruppoDao.class);
	    when(request.getParameter("nome")).thenReturn("Vincenzo");
	    when(request.getParameter("cognome")).thenReturn("Gallicchio");
	    when(request.getParameter("email")).thenReturn("gallicchio.vincenzo@yahoo.it");
	    when(request.getParameter("dataNascita")).thenReturn("1994-08-11");
	    when(request.getParameter("password")).thenReturn("blu");
	    when(request.getParameter("indirizzo")).thenReturn("indirizzo");
	    when(request.getParameter("telefono")).thenReturn("telefono");
	    when(gruppoDao.findById(Gruppo.class, "Studente")).thenReturn(null);
	    servlet.setUtenteDao(utenteDao);
	    servlet.setGruppoDao(gruppoDao);
	    servlet.doPost(request, response);
	    verify(responseWriter).write("Al momento non è possibile registrarsi al sistema");
	  }
	  
	  @Test
	  public void UtenteGiaPresente() throws Exception {
	    IUtenteDao utenteDao = mock(IUtenteDao.class);
	    IGruppoDao gruppoDao = mock(IGruppoDao.class);
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
	    when(utenteDao.findById(Utente.class, "gallicchio.vincenzo@yahoo.it")).thenReturn(new Utente());
	    servlet.setUtenteDao(utenteDao);
	    servlet.setGruppoDao(gruppoDao);
	    servlet.doPost(request, response);
	    verify(responseWriter).write("Utente già presente nel sistema");
	  }

	  @Test
	  public void parseException() throws Exception {
	    IUtenteDao utenteDao = mock(IUtenteDao.class);
	    IGruppoDao gruppoDao = mock(IGruppoDao.class);
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
	    servlet.setUtenteDao(utenteDao);
	    servlet.setGruppoDao(gruppoDao);
	    servlet.doPost(request, response);
	    verify(responseWriter).write("Errore durante il parse della data");
	  }

	

	


	  

	  
}
