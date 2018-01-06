package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Report;
import it.unisa.libra.bean.ReportPK;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IReportDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.Actions;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReportStudenteServletTest {

  @Mock
  private IUtenteDao utenteDao;
  @Mock
  private IStudenteDao studenteDao;
  @Mock
  private IReportDao reportDao;
  @Mock
  private IProgettoFormativoDao pfDao;

  private Studente studente = new Studente();
  private ReportPK rep = new ReportPK(); // chiave primaria per il report
  private Date data = new Date();
  private Utente utente = new Utente();
  private Report report = new Report();
  private ProgettoFormativo pf = new ProgettoFormativo();

  @Mock
  private ServletConfig config;
  @InjectMocks
  private ReportStudenteServlet servlet;

  @Mock
  private HttpSession session;
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  PrintWriter responseWriter;
/*
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    servlet.init(config);

    when(response.getWriter()).thenReturn(responseWriter);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void verificaActionNull() throws Exception {
    try {
      when(request.getParameter(Actions.ACTION)).thenReturn(null);
      servlet.doPost(request, response);
      verify(responseWriter).write(BADREQUEST_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // TEST PER L'AGGIUNTA
  @Test
  public void verificaStudenteNull() throws Exception {
    try {

      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(null);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      servlet.doPost(request, response);
      verify(responseWriter).write("errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void verificaPfNull() throws Exception {
    try {

      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(null);
      servlet.doPost(request, response);
      verify(responseWriter).write("errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void studentePfOk() throws Exception {
    try {

      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("testoNuovoReport")).thenReturn("testo nuovo report");
      rep.setData(data);
      rep.setProgettoFormativoID(pf.getId());
      report.setTesto("testo nuovo report");
      report.setId(rep);
      report.setProgettoFormativo(pf);
      reportDao.persist(report);
      servlet.doPost(request, response);
      verify(responseWriter).write("finito");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void studentePfOkErrore() throws Exception {
    try {
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("testoNuovoReport")).thenReturn("testo nuovo report");
      rep.setData(data);
      rep.setProgettoFormativoID(pf.getId());
      report.setTesto("testo nuovo report");
      report.setId(rep);
      report.setProgettoFormativo(pf);
      reportDao.persist(report);
      servlet.doPost(request, response);
      verify(responseWriter).write("finito");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // TEST PER LA MODIFICA

  @Test
  public void modificaFailUtente() throws Exception {
    try {

      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(null);
      servlet.doPost(request, response);
      verify(responseWriter).write("Si e' verificato un errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaFailPf() throws Exception {
    try {

      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(null);
      servlet.doPost(request, response);
      verify(responseWriter).write("Si e' verificato un errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaFailData() throws Exception {
    try {
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("data")).thenReturn(null);
      servlet.doPost(request, response);
      verify(responseWriter).write(BADREQUEST_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaTryBad() throws Exception {
    try {
      String dateReport = "121212";
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("data")).thenReturn(dateReport);
      Long.parseLong(dateReport);
      servlet.doPost(request, response);
      verify(responseWriter).write(BADREQUEST_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaTry2() throws Exception {
    try {
      String dateReport = "121212";
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("data")).thenReturn(dateReport);
      Long dateReportM;
      dateReportM = Long.parseLong(dateReport);
      when(request.getParameter("testoReportModificato")).thenReturn("testo nuovo");

      List<Report> reports = new ArrayList<>();
      when(reportDao.findAll(Report.class)).thenReturn(reports);
      Date d = new Date(dateReportM);
      Report report = new Report();
      ReportPK rep = new ReportPK();
      rep.setData(d);
      report.setId(rep);

      reports.add(report);
      reports.iterator();

      servlet.doPost(request, response);
      verify(responseWriter).write(SUCCESS_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void verificaActionNo() throws Exception {
    when(request.getParameter(Actions.ACTION)).thenReturn("diverso");
    servlet.doPost(request, response);
    verify(responseWriter).write("no");

  }

  @Test
  public void successGetNumReportsTest() throws ServletException, IOException {
    when(request.getParameter(Actions.ACTION)).thenReturn(Actions.RS_NUM_REPORTS);
    when(reportDao.getNumReports()).thenReturn(new Long("10"));
    servlet.doGet(request, response);

    verify(responseWriter).write("10");
  }

  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
  private static final String SUCCESS_MESS = "ok";
*/
}
