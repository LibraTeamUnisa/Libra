package it.unisa.libra.controller;

import it.unisa.libra.model.dao.IReportDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "ReportStudenteServlet", urlPatterns = "/reportStudenteServlet")
public class ReportStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Inject
  private IReportDao reportDao;

  /** Default constructor. */
  public ReportStudenteServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (CheckUtils.validAction(request)) {
      if (request.getParameter(Actions.ACTION).equals(Actions.RS_NUM_REPORTS)) {
        Long numReports = reportDao.getNumReports();
        if (numReports != null) {
          response.getWriter().write(numReports.toString());
        }
      }
    }

  }


  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
