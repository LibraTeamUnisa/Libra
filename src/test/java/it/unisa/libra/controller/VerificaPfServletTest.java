package it.unisa.libra.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VerificaPfServletTest {

  private VerificaPfServlet verificaPfServlet = new VerificaPfServlet();
  private HttpServletRequest request;
  private HttpServletResponse response;
  private PrintWriter printWriter;
  private IProgettoFormativoDao progettoformativoDao;

  /**
   * Il metodo inizializza gli oggetti necessari per il test.
   * @throws Exception Eccezione di IO nell'invocazione di getWriter() 
   */
  @Before
  public void setUp() throws Exception {
    request = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
    response = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);
    printWriter = mock(PrintWriter.class);
    progettoformativoDao = mock(IProgettoFormativoDao.class);
    when(response.getWriter()).thenReturn(printWriter);
  }

  /**
   * Vengono rimossi tutti i riferimenti degli oggetti al termine dei test.
   */
  @After
  public void tearDown() {
    request = null;
    response = null;
    printWriter = null;
  }

  @Test
  public void idNotIntegerTest() throws Exception {
    when(request.getParameter("pf_id")).thenReturn("A");

    verificaPfServlet.doPost(request, response);

    verify(printWriter).write(PARSE_ERROR_MSG);
  }

  @Test
  public void pfNotFoundTest() throws Exception {
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(null);
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(printWriter).write(PF_NOTFOUND_ERROR_MSG);
  }

  @Test
  public void pfPresidenteStatoFailTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(1);
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(printWriter).write(STATO_ERROR_MSG);
  }

  @Test
  public void pfTutorStatoFailTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class, RETURNS_DEEP_STUBS);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(1);
    when(pf.getTutorInterno().getUtenteEmail()).thenReturn("bianchi@unisa.it");
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(printWriter).write(STATO_ERROR_MSG);
  }

  @Test
  public void pfTutorNonAssociatoTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class, RETURNS_DEEP_STUBS);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(2);
    when(pf.getTutorInterno().getUtenteEmail()).thenReturn("rossi@unisa.it");
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(printWriter).write(TUTOR_ERROR_MSG);
  }

  @Test
  public void pfPresidenteOkTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Presidente");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(3);
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(pf).setStato(6);
    verify(pf).setMotivazioneRifiuto("unaMotivazione");
    verify(printWriter).write(SUCCESS_MSG);
  }

  @Test
  public void pfTutorOkTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class, RETURNS_DEEP_STUBS);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("TutorInterno");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(2);
    when(pf.getTutorInterno().getUtenteEmail()).thenReturn("bianchi@unisa.it");
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(pf).setStato(6);
    verify(pf).setMotivazioneRifiuto("unaMotivazione");
    verify(printWriter).write(SUCCESS_MSG);

  }

  @Test
  public void ruoloFailTest() throws Exception {
    ProgettoFormativo pf = mock(ProgettoFormativo.class, RETURNS_DEEP_STUBS);
    when(request.getParameter("pf_id")).thenReturn("1");
    when(request.getSession().getAttribute("utenteRuolo")).thenReturn("Studente");
    when(request.getSession().getAttribute("utenteEmail")).thenReturn("bianchi@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("unaMotivazione");
    when(progettoformativoDao.findById(ProgettoFormativo.class, 1)).thenReturn(pf);
    when(pf.getStato()).thenReturn(2);
    when(pf.getTutorInterno().getUtenteEmail()).thenReturn("bianchi@unisa.it");
    verificaPfServlet.setProgettoFormativoDao(progettoformativoDao);
    verificaPfServlet.doPost(request, response);
    verify(printWriter).write(RUOLO_ERROR_MSG);

  }

  @Test
  public void doGetTest() throws Exception {
    verificaPfServlet.doGet(request, response);
    verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  private static final String SUCCESS_MSG = "ok";
  private static final String STATO_ERROR_MSG = "Operazione non consentita";
  private static final String RUOLO_ERROR_MSG =
      "Non hai l'autorizzazione necessaria per effettuare questa operazione";
  private static final String TUTOR_ERROR_MSG = "Non sei associato a questo progetto formativo";
  private static final String PF_NOTFOUND_ERROR_MSG = "Progetto formativo non trovato";
  private static final String PARSE_ERROR_MSG = "Parametro errato";
}
