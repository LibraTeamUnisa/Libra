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
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IFeedbackDao;

public class GestioneFeedbackStudenteServletTest {

  @Mock
  private IAziendaDao aziendaDao;
  @Mock
  private Azienda azienda;
  @Mock
  private IFeedbackDao feedbackDao;
  @Mock
  private Feedback feedback;

  GestioneFeedbackStudenteServlet servlet = new GestioneFeedbackStudenteServlet();

  @Mock
  private HttpSession session;
  HttpServletRequest request;
  HttpServletResponse response;
  PrintWriter responseWriter;

  @Before
  public void setUp() throws Exception {
    servlet.init();
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    responseWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(responseWriter);
    aziendaDao = mock(IAziendaDao.class);
    feedbackDao = mock(IFeedbackDao.class);
  }

  @After
  public void tearDown() throws Exception {
    request = null;
    response = null;
    session = null;
    aziendaDao = null;
    feedbackDao = null;
  }



  @Test
  public void verificaSalvatoTest() throws Exception {
    Azienda azienda = new Azienda();
    Utente utente = new Utente();

    String mail = "prova@prova.it";
    String password = "provapass";
    utente.setEmail(mail);
    utente.setPassword(password);
    azienda.setUtente(utente);

    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("utenteEmail")).thenReturn(mail);
    when(aziendaDao.findById(Azienda.class, mail)).thenReturn(azienda);
    when(request.getParameter("val1")).thenReturn("1");
    when(request.getParameter("val2")).thenReturn("3");
    when(request.getParameter("val3")).thenReturn("5");
    when(request.getParameter("val4")).thenReturn("2");
    when(request.getParameter("val5")).thenReturn("4");
    when(request.getParameter("val6")).thenReturn("4");
    when(request.getParameter("val7")).thenReturn("5");
    when(request.getParameter("val8")).thenReturn("3");
    when(request.getParameter("val9")).thenReturn("4");
    when(request.getParameter("val10")).thenReturn("2");
    when(request.getParameter("val11")).thenReturn("3");
    when(request.getParameter("valNote")).thenReturn("Bella");
    when(request.getParameter("idProgettoFormativo")).thenReturn("1");
    when(response.getWriter()).thenReturn(responseWriter);

    servlet.setFeedbackDao(feedbackDao);
    servlet.setFeedback("1", 1, 1);

    servlet.setFeedback("3", 2, 1);
    servlet.setFeedback("5", 3, 1);
    servlet.setFeedback("2", 4, 1);
    servlet.setFeedback("4", 5, 1);
    servlet.setFeedback("4", 6, 1);
    servlet.setFeedback("5", 7, 1);
    servlet.setFeedback("3", 8, 1);
    servlet.setFeedback("4", 9, 1);
    servlet.setFeedback("2", 10, 1);
    servlet.setFeedback("3", 11, 1);
    servlet.setFeedback("Bella", 12, 1);
    servlet.doPost(request, response);
    servlet.doGet(request, response);

    verify(responseWriter).write("salvato");

  }

}
