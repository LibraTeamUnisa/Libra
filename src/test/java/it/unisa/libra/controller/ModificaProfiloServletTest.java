package it.unisa.libra.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.libra.controller.ModificaProfiloServlet;
import it.unisa.libra.util.Actions;
/**
public class ModificaProfiloServletTest {

	private ModificaProfiloServlet servlet = new ModificaProfiloServlet();
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
	  public void testModificaStudente() throws ServletException, IOException {
		 when(request.getParameter(Actions.UTENTE_RUOLO)).thenReturn(null);
		 servlet.doPost(request, response);
	 }
}
*/
