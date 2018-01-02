package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

public class CaricaImmagineServletTest {

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
  private IUtenteDao utenteDao;
  @Mock
  private Utente utente;
  @Mock
  private PrintWriter responseWriter;
  @Mock
  private Part part;
  @Mock
  private File file;
  @Mock
  private InputStream is;
  @InjectMocks
  private CaricaImmagineServlet servlet;

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
  public void cambioImmagineOk() throws Exception {
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(email);
    when(utenteDao.findById(Utente.class, email)).thenReturn(utente);
    when(request.getPart("proPic")).thenReturn(part);
    when(part.getSubmittedFileName()).thenReturn("name");
    when(part.getInputStream()).thenReturn(is);
    servlet.doPost(request, response);
    verify(response).sendRedirect("profilo.jsp");
  }
}
