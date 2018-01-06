package it.unisa.libra.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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

public class ReportStudenteServletTest {

  private IUtenteDao utenteDao;
  private IStudenteDao studenteDao;
  private IReportDao reportDao;
  private IProgettoFormativoDao pfDao;
  ReportStudenteServlet servlet = new ReportStudenteServlet();
  private Studente studente = new Studente();
  private ReportPK rep = new ReportPK(); // chiave primaria per il report
  private Date data = new Date();
  private Utente utente = new Utente();
  private Report report = new Report();
  private ProgettoFormativo pf = new ProgettoFormativo();

  private HttpSession session;
  HttpServletRequest request;
  HttpServletResponse response;
  PrintWriter responseWriter;

  @Before
  public void setUp() throws Exception {
    // servlet= mock(ReportStudenteServlet.class);
    // servlet.init();
    studenteDao = mock(IStudenteDao.class);
    pfDao = mock(IProgettoFormativoDao.class);
    session = mock(HttpSession.class);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    responseWriter = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(responseWriter);
    utenteDao = mock(IUtenteDao.class);
    reportDao = mock(IReportDao.class);

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

      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(null);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.aggiungiReport(request, response);
      verify(responseWriter).write("errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void verificaPfNull() throws Exception {
    try {

      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(null);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.aggiungiReport(request, response);
      verify(responseWriter).write("errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void studentePfOk() throws Exception {
    try {

      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      IReportDao reportDao = mock(IReportDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
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
      servlet.setReportDao(reportDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.aggiungiReport(request, response);
      verify(responseWriter).write("finito");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void studentePfOkErrore() throws Exception {
    try {
      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      IReportDao reportDao = mock(IReportDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.AGGIUNGI_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
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
      servlet.setReportDao(reportDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.aggiungiReport(request, response);
      verify(responseWriter).write("finito");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // TEST PER LA MODIFICA

  @Test
  public void modificaFailUtente() throws Exception {
    try {

      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(null);
      servlet.setReportDao(reportDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.modificaReport(request, response);
      verify(responseWriter).write("Si e' verificato un errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaFailPf() throws Exception {
    try {

      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(null);
      servlet.setReportDao(reportDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.modificaReport(request, response);
      verify(responseWriter).write("Si e' verificato un errore");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaFailData() throws Exception {
    try {
      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      IReportDao reportDao = mock(IReportDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("data")).thenReturn(null);
      servlet.setReportDao(reportDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.modificaReport(request, response);
      verify(responseWriter).write(BADREQUEST_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaTryBad() throws Exception {
    try {
      String dateReport = "121212";
      IReportDao reportDao = mock(IReportDao.class);
      IUtenteDao utenteDao = mock(IUtenteDao.class);
      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
      String emailStudente = "email@email.it";
      when(request.getSession()).thenReturn(session);
      when(session.getAttribute("utenteEmail")).thenReturn(emailStudente);
      when(studenteDao.findById(Studente.class, emailStudente)).thenReturn(studente);
      when(pfDao.getLastProgettoFormativoByStudente(studente)).thenReturn(pf);
      when(request.getParameter("data")).thenReturn(dateReport);
      Long.parseLong(dateReport);
      servlet.setReportDao(reportDao);
      servlet.setUtenteDao(utenteDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.modificaReport(request, response);
      verify(responseWriter).write(BADREQUEST_MESS);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void modificaTry2() throws Exception {
    try {
      String dateReport = "121212";
      IReportDao reportDao = mock(IReportDao.class);
      IUtenteDao utenteDao = mock(IUtenteDao.class);
      IStudenteDao studenteDao = mock(IStudenteDao.class);
      IProgettoFormativoDao pfDao = mock(IProgettoFormativoDao.class);
      when(request.getParameter(Actions.ACTION)).thenReturn(Actions.MODIFICA_REPORT);
      ReportStudenteServlet rst = mock(ReportStudenteServlet.class);
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

      servlet.setReportDao(reportDao);
      servlet.setUtenteDao(utenteDao);
      servlet.setStudenteDao(studenteDao);
      servlet.setProgettoFormativoDao(pfDao);
      servlet.doPost(request, response);
      rst.modificaReport(request, response);
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

  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
  private static final String SUCCESS_MESS = "ok";

}
