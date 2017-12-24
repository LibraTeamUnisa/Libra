package it.unisa.libra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.controller.ModificaProfiloServlet;

public class ModificaProfiloServletTest {

    @InjectMocks
	private ModificaProfiloServlet servlet = new ModificaProfiloServlet();
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private PrintWriter responseWriter;
	@Mock
	private HttpSession session;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    when(response.getWriter()).thenReturn(responseWriter);
	  }
	
	 @Test
	  public void ruoloBadRequestTest() throws Exception {
	    when(request.getSession().getAttribute("utenteRuolo")).thenReturn(null);
	    servlet.doPost(request, response);
	    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    verify(response.getWriter()).write("false");
	 }

	 
	 @Test
	 public void lunghezzaIndirizzoTestoError() throws ServletException, IOException {
	   when(request.getParameter("Indirizzo")).thenReturn(null);
	   servlet.doPost(request, response);
	   verify(request).setAttribute("erroreModifica", "too short");;	   
	 }
	 
	 @Test
	 public void lunghezzaTelefonoError() throws ServletException, IOException {
	   when(request.getParameter("Telefono")).thenReturn(null);
	   servlet.doPost(request, response);
	   verify(request).setAttribute("erroreModifica", "too short");
	 }
	 
	 @Test
	 public void formatoTelefonoError() throws ServletException, IOException {
	   when(request.getParameter("Telefono")).thenReturn("a string");
	   servlet.doPost(request, response);
	   verify(request).setAttribute("erroreModifica", "is not a number");
	 }
}
