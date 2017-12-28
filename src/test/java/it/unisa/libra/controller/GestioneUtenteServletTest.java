package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

public class GestioneUtenteServletTest {


	@Mock
	private IUtenteDao utenteDao;
	@Mock
	private Utente utente;

	GestioneUtenteServlet servlet = new GestioneUtenteServlet();

	@Mock
	private HttpSession session;
	HttpServletRequest request;
	HttpServletResponse response;
	PrintWriter responseWriter;

	@Before
	public void setUp() throws Exception {
		servlet.init();
		session= mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		responseWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(responseWriter);
		utenteDao = mock(IUtenteDao.class);
	}

	@After
	public void tearDown() throws Exception{
		request = null;
		response=null;
		session = null;
		utenteDao = null;
	}

	@Test
	public void verificaNullTest() throws Exception{
		try {
			IUtenteDao utenteDao = mock(IUtenteDao.class);
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("utenteEmail")).thenReturn("mail@mail.it");
			when(request.getParameter("password")).thenReturn("funziona");
			when(request.getParameter("action")).thenReturn(null);
			//servlet.setUtenteDao(utenteDao);
			servlet.doPost(request, response);
			verify(responseWriter).write("errore");
			// devo aggiungere la condizione per il reindirizzamento
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Test
	public void verificaTrueTest() {
		try {
			Utente utente = new Utente();
			String mail = "mail@mail.it";
			String pwd =  "passwordok";
			utente.setEmail(mail);
			utente.setPassword(pwd);
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("utenteEmail")).thenReturn(mail);
			when(utenteDao.findById(Utente.class, mail)).thenReturn(utente);
			when(request.getParameter("password")).thenReturn(pwd);
			when(request.getParameter("action")).thenReturn("verifica");
			when(request.getParameter("pwn1")).thenReturn("funziona1");
			when(request.getParameter("pwn2")).thenReturn("funziona2");
			when(response.getWriter()).thenReturn(responseWriter);
			//servlet.controllaPassword(utente, pwd);
			//servlet.setUtenteDao(utenteDao);
			servlet.doPost(request, response);
			verify(responseWriter).write("true");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void verificaFailTest() {
		try {
			Utente utente = new Utente();
			String mail = "mail@mail.it";
			String pwd =  "passwordok";
			String pwd2 = "passworderrata";
			utente.setEmail(mail);
			utente.setPassword(pwd2);
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("utenteEmail")).thenReturn(mail);
			when(utenteDao.findById(Utente.class, mail)).thenReturn(utente);
			when(request.getParameter("password")).thenReturn(pwd);
			when(request.getParameter("action")).thenReturn("verifica");
			when(request.getParameter("pwn1")).thenReturn("funziona1");
			when(request.getParameter("pwn2")).thenReturn("funziona2");
			when(response.getWriter()).thenReturn(responseWriter);
			//servlet.controllaPassword(utente, pwd);
			//servlet.setUtenteDao(utenteDao);
			servlet.doPost(request, response);
			verify(responseWriter).write("false");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void verificaFinitoTest() {
		try {
			Utente utente = new Utente();
			String mail = "mail@mail.it";
			String pwd =  "passwordok";
			String pwd2 = "password";
			String pwd3 = "password";
			utente.setEmail(mail);
			utente.setPassword(pwd);
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("utenteEmail")).thenReturn(mail);
			when(utenteDao.findById(Utente.class, mail)).thenReturn(utente);
			when(request.getParameter("password")).thenReturn(pwd);
			when(request.getParameter("action")).thenReturn("cambia");
			when(request.getParameter("pwn1")).thenReturn(pwd2);
			when(request.getParameter("pwn2")).thenReturn(pwd3);
			when(response.getWriter()).thenReturn(responseWriter);
			//servlet.setUtenteDao(utenteDao);
			//servlet.aggiornaPassword(utente, pwd2, pwd3);
			//utenteDao.merge(utente);
			servlet.doPost(request, response);
			verify(responseWriter).write("finito");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void verificaErroreTest2() {
		try {
			Utente utente = new Utente();
			String mail = "mail@mail.it";
			String pwd =  "passwordok";
			String pwd2 = "password";
			String pwd3 = "passwordsbagliata";
			utente.setEmail(mail);
			utente.setPassword(pwd);
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("utenteEmail")).thenReturn(mail);
			when(utenteDao.findById(Utente.class, mail)).thenReturn(utente);
			when(request.getParameter("password")).thenReturn(pwd);
			when(request.getParameter("action")).thenReturn("cambia");
			when(request.getParameter("pwn1")).thenReturn(pwd2);
			when(request.getParameter("pwn2")).thenReturn(pwd3);
			when(response.getWriter()).thenReturn(responseWriter);
			//servlet.setUtenteDao(utenteDao);
			//servlet.aggiornaPassword(utente, pwd2, pwd3);
			//utenteDao.merge(utente);
			servlet.doPost(request, response);
			verify(responseWriter).write("errore");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
