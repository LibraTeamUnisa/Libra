package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.JspPagesIndex;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

public class AutenticazioneServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private AutenticazioneServlet servlet;
  @Mock
  private IUtenteDao utenteDao;
  @Mock
  private PrintWriter responseWriter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginTest() throws Exception {
    Utente utente = new Utente();
    String mail = "emailok";
    String pwd = "passwordok";

    utente.setEmail(mail);
    utente.setPassword(pwd);
    Gruppo g = new Gruppo();
    g.setRuolo("Ruolo");
    utente.setGruppo(g);

    when(request.getContextPath()).thenReturn("/Libra");

    String dashboard = request.getContextPath().concat("/dashboard").concat(utente.getGruppo().getRuolo()).concat(".jsp");

    when(request.getParameter("email")).thenReturn(mail);
    when(request.getParameter("password")).thenReturn(pwd);
    when(utenteDao.getUtente(mail, pwd)).thenReturn(utente);

    servlet.doPost(request, response);
    
    when(response.getWriter()).thenReturn(responseWriter);
    
    verify(responseWriter).write(dashboard);

  }

  @Test
  public void loginBadRequestTest() throws Exception {
    when(request.getParameter("email")).thenReturn(null);
    when(request.getParameter("password")).thenReturn(null);

    when(response.getWriter()).thenReturn(responseWriter);

    servlet.doPost(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    verify(response.getWriter()).write("false");
  }

  @Test
  public void loginNoUtenteTest() throws Exception {
    Utente utente = new Utente();
    String mail = "emailok";
    String pwd = "passwordok";

    utente.setEmail(mail);
    utente.setPassword(pwd);

    when(request.getParameter("email")).thenReturn(mail);
    when(request.getParameter("password")).thenReturn(pwd);

    when(utenteDao.getUtente(mail, pwd)).thenReturn(null);

    when(response.getWriter()).thenReturn(responseWriter);
    
    servlet.doPost(request, response);

    verify(responseWriter).write("false");
  }

  @Test
  public void logoutTest() throws Exception {

    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.LOGOUT);
    when(request.getSession()).thenReturn(session);

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    when(response.getWriter()).thenReturn(pw);

    new AutenticazioneServlet().doGet(request, response);

    verify(response).sendRedirect(request.getContextPath() + JspPagesIndex.HOME);
  }

  @Test
  public void invaliActionTest() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn("");
    when(request.getSession()).thenReturn(session);

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    when(response.getWriter()).thenReturn(pw);

    new AutenticazioneServlet().doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);

  }
}
