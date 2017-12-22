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
import org.mockito.InjectMocks;
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
  private IUtenteDao utenteDao;
  @InjectMocks
  private AutenticazioneServlet servlet;
  @Mock
  private PrintWriter responseWriter;

  private Utente utente;
  private String mail;
  private String pwd;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    utente = new Utente();
    mail = "emailok";
    pwd = "passwordok";

    utente.setEmail(mail);
    utente.setPassword(pwd);

    when(response.getWriter()).thenReturn(responseWriter);
  }

  @Test
  public void loginTest() throws Exception {

    Gruppo g = new Gruppo();
    g.setRuolo("Ruolo");
    utente.setGruppo(g);

    when(request.getContextPath()).thenReturn("/Libra");

    String dashboard = request.getContextPath().concat("/dashboard")
        .concat(utente.getGruppo().getRuolo()).concat(".jsp");

    when(request.getParameter("email")).thenReturn(mail);
    when(request.getParameter("password")).thenReturn(pwd);
    when(utenteDao.getUtente(mail, pwd)).thenReturn(utente);

    when(request.getSession()).thenReturn(session);


    servlet.doPost(request, response);

    verify(responseWriter).write(dashboard);

  }

  @Test
  public void loginBadRequestTest() throws Exception {
    when(request.getParameter("email")).thenReturn(null);
    when(request.getParameter("password")).thenReturn(null);

    new AutenticazioneServlet().doPost(request, response);

    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    verify(response.getWriter()).write("false");
  }

  @Test
  public void loginNoUtenteTest() throws Exception {

    when(request.getParameter("email")).thenReturn(mail);
    when(request.getParameter("password")).thenReturn(pwd);

    when(utenteDao.getUtente(mail, pwd)).thenReturn(null);

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
