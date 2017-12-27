package it.unisa.libra.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Permesso;
import it.unisa.libra.model.dao.IPermessoDao;

public class PermessiServletTest {

  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private HttpSession session;
  @Mock
  private IPermessoDao permessoDao;
  @InjectMocks
  private PermessiServlet servlet;
  @Mock
  private PrintWriter responseWriter;
  @Mock
  private List<Permesso> listPerm;
  @Mock
  private List<Gruppo> gruppi;

  private Permesso ricevuti, noFeedback, conFirma, anonimi;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    Gruppo g = new Gruppo();
    g.setRuolo("Ruolo");
    gruppi.add(g);

    conFirma = new Permesso();
    conFirma.setAbilitazione(true);
    conFirma.setTipo("conFirma");
    conFirma.setGruppi(gruppi);
    ricevuti = new Permesso();
    ricevuti.setAbilitazione(true);
    ricevuti.setTipo("ricevuti");
    ricevuti.setGruppi(gruppi);
    gruppi.remove(0);
    anonimi = new Permesso();
    anonimi.setAbilitazione(true);
    anonimi.setTipo("anonimi");
    anonimi.setGruppi(gruppi);
    noFeedback = new Permesso();
    noFeedback.setAbilitazione(true);
    noFeedback.setTipo("noFeedback");
    noFeedback.setGruppi(gruppi);

    listPerm = Arrays.asList(noFeedback, anonimi, ricevuti, conFirma);

    when(request.getSession()).thenReturn(session);
    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testTrue() throws Exception {
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(permessoDao.findAll(Permesso.class)).thenReturn(listPerm);
   
    when(request.getParameter(("checkbox").concat(Mockito.anyString()))).thenReturn("true");
    when(request.getParameter(("checkbox").concat("Segreteria"))).thenReturn("false");
    
    when(request.getParameter(("radio").concat("Studente"))).thenReturn("conFirmaStudente");
    when(request.getParameter(("radio").concat("Azienda"))).thenReturn("anonimiAzienda");
    when(request.getParameter(("radio").concat("TutorInterno"))).thenReturn("anonimiTutorInterno");
    when(request.getParameter(("radio").concat("Presidente"))).thenReturn("noFeedbackPresidente");
    when(request.getParameter(("radio").concat("Segreteria"))).thenReturn("conFirmaSegreteria");
    
    servlet.doPost(request, response);

    verify(responseWriter).write("true");
  }

  @Test
  public void testErrore() throws Exception {
    when(session.getAttribute("utenteRuolo")).thenReturn("Segreteria");
    when(permessoDao.findAll(Permesso.class)).thenReturn(listPerm);
    when(request.getParameter(("checkbox").concat(Mockito.anyString()))).thenReturn("undefined");

    servlet.doPost(request, response);

    verify(responseWriter).write("errore");
  }

  @Test
  public void testNonConsentito() throws Exception {
    when(session.getAttribute("utenteRuolo")).thenReturn("");
    servlet.doPost(request, response);
    verify(responseWriter).write("non consentito");
  }

}
