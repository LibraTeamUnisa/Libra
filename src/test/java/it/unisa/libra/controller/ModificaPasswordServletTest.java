package it.unisa.libra.controller;

import static org.junit.Assert.*;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import org.junit.Test;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

public class ModificaPasswordServletTest{
	/*
	 	  
	 String email=(String) request.getSession().getAttribute("utenteEmail");
	 			// Utente ruolo=(Utente) request.getSession(true).getAttribute("utenteRuolo");NON MI SERVE PIù
	 Utente utente =  utenteDao.findById(Utente.class, email);
	 * */
	
	
	ModificaPasswordServlet servlet = new ModificaPasswordServlet();
	HttpServletRequest request;
	HttpServletResponse response;
	PrintWriter responseWriter;
	
	
	@Test
	public void onSuccessTest() {
		try {
		HttpSession session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		response=mock(HttpServletResponse.class);
		responseWriter=mock(PrintWriter.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("utenteEmail")).thenReturn("ciao@coias");
		when(request.getParameter("password")).thenReturn("funziona");
		when(request.getParameter("pwn1")).thenReturn("funziona1");
		when(request.getParameter("pwn2")).thenReturn("funziona2");
		when(response.getWriter()).thenReturn(responseWriter);
		
		IUtenteDao utenteDao = mock(IUtenteDao.class);
		servlet.setUtenteDao(utenteDao);
		servlet.doPost(request, response);
	
//devo aggiungere la condizione per il reindirizzamento
		}catch(Exception ex) {
			ex.printStackTrace();
		
		}

}
}
