package it.unisa.libra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.controller.ModificaProfiloServlet;
import it.unisa.libra.model.dao.ISegreteriaDao;
import it.unisa.libra.model.dao.IUtenteDao;

public class ModificaProfiloServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private ISegreteriaDao segreteriaDao;
  @Mock
  private RequestDispatcher dispatcher;
  @Mock
  private ServletContext context;
  @Mock
  private ServletConfig config;
  @Mock
  private Segreteria segreteria;
  @Mock
  private IUtenteDao utenteDao;
  @Mock
  private Utente utente;
  @Mock
  private PrintWriter responseWriter;
  @InjectMocks
  private ModificaProfiloServlet servlet;

  
  String email = "example@test.it";
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    servlet.init(config);
	  }
	
	@After
	public void terDown() throws Exception {	  
	}
/*	
	@Test
    public void getRuoloFail() throws Exception {
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(email);
      when(session.getAttribute("utenteRuolo")).thenReturn("");
      servlet.doPost(request, response);
       verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

	 @Test
	 public void lunghezzaTelefonoError() throws ServletException, IOException {
	   when(request.getSession()).thenReturn(session);
	   when(session.getAttribute("utenteEmail")).thenReturn(email);
	   when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
	   when(segreteriaDao.findById(Segreteria.class, email)).thenReturn(segreteria);
	   when(segreteria.getUtente()).thenReturn(utente);
	   when(request.getParameter("Indirizzo")).thenReturn("");
	   when(request.getParameter("Telefono")).thenReturn("");
	   when(request.getParameter("Ricevimento")).thenReturn("");
	   servlet.doPost(request, response);
	   verify(request).setAttribute("erroreModifica",
          "L'indirizzo deve essere compreso tra i 2 e i 100 caratteri");
	 }
*/
}
