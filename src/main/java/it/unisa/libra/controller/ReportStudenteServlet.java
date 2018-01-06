package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Report;
import it.unisa.libra.bean.ReportPK;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IReportDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.Actions;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AutenticazioneServlet. Controller Class che gestisce l'aggiunta e la
 * modifica dei report periodici da parte dello Studente.
 * 
 * @author Giandomenico Solimando
 * @author Lucio Giordano
 * 
 * @version 1.0
 * 
 * 
 */
@WebServlet(name = "ReportStudenteServlet", urlPatterns = "/ReportStudenteServlet")

public class ReportStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** gestore della persistenza per l'entit� Report. **/
  @EJB
  private IReportDao reportDao;

  /** gestore della persistenza per l'entit� Progetto Formativo. **/
  @EJB
  private IProgettoFormativoDao progettoFormativoDao;

  /** gestore della persistenza per l'entit� Studente. **/
  @EJB
  private IStudenteDao studenteDao;

  @EJB
  private IUtenteDao utenteDao;

  /** Default constructor. */
  public ReportStudenteServlet() {}

  /*
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().write(BADREQUEST_MESS);
    return;
  }

  /*
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getParameter(Actions.ACTION);
    if (action == null) {
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }
    if (action.equals(Actions.MODIFICA_REPORT)) {
      modificaReport(request, response);
      return;

    }
    if (action.equals(Actions.AGGIUNGI_REPORT)) {
      aggiungiReport(request, response);
      // return;
    } else {
      response.getWriter().write("no");
      return;
    }
  }

  protected void modificaReport(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String emailStudente = (String) request.getSession().getAttribute("utenteEmail");
    Studente studente = studenteDao.findById(Studente.class, emailStudente);
    if (studente == null) {
      response.getWriter().write("Si e' verificato un errore");
      return;
    }

    ProgettoFormativo progettoFormativo =
        progettoFormativoDao.getLastProgettoFormativoByStudente(studente);
    if (progettoFormativo == null) {
      response.getWriter().write("Si e' verificato un errore");
      return;
    }

    String dataReport = (String) request.getParameter("data");
    if (dataReport == null) {
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }

    Long dateReportM = null;
    dateReportM = Long.parseLong(dataReport);

    String testoReport = (String) request.getParameter("testoReportModificato");
    if (testoReport == null) {
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }

    List<Report> reports = reportDao.findAll(Report.class);
    Iterator<Report> listReport = reports.iterator();

    while (listReport.hasNext()) {
      Report rep1 = (Report) listReport.next();
      Long l = rep1.getId().getData().getTime();
      if (Long.valueOf(l).compareTo(Long.valueOf(dateReportM)) == 0) {
        rep1.setTesto(testoReport);
        reportDao.persist(rep1);

        response.getWriter().write(SUCCESS_MESS);
        break;
      }
    }

  }

  protected void aggiungiReport(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String emailStudente = (String) request.getSession().getAttribute("utenteEmail");
    Studente studente = studenteDao.findById(Studente.class, emailStudente);
    ProgettoFormativo progettoFormativo =
        progettoFormativoDao.getLastProgettoFormativoByStudente(studente);
    if (studente == null || progettoFormativo == null) {
      response.getWriter().write("errore");
    } else {
      String testoNuovoReport = (String) request.getParameter("testoNuovoReport");
      Report report = new Report();
      ReportPK rep = new ReportPK(); // chiave primaria per il report
      Date data = new Date();
      rep.setData(data);
      rep.setProgettoFormativoID(progettoFormativo.getId());
      report.setTesto(testoNuovoReport);
      report.setId(rep);
      report.setProgettoFormativo(progettoFormativo);
      reportDao.persist(report);
      response.getWriter().write("finito");
    }

  }


  public void setUtenteDao(IUtenteDao utenteDao) {
    this.utenteDao = utenteDao;
  }


  public void setStudenteDao(IStudenteDao studenteDao) {
    this.studenteDao = studenteDao;
  }


  public void setProgettoFormativoDao(IProgettoFormativoDao progettoFormativoDao) {
    this.progettoFormativoDao = progettoFormativoDao;
  }

  public void setReportDao(IReportDao ReportDao) {
    this.reportDao = ReportDao;
  }


  /** messaggio di errore inviato in caso di bad request. **/
  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

  /** messaggio restituito in caso di successo dell'operazione. **/
  private static final String SUCCESS_MESS = "ok";
}
