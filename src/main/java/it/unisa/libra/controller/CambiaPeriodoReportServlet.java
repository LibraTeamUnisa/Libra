package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class CambiaPeriodoReportServlet.
 * 
 * @author Mauro Vitale
 * @version 1.0
 */
@WebServlet(name = "CambiaPeriodoReportServlet", urlPatterns = "/periodoReport")
public class CambiaPeriodoReportServlet extends HttpServlet {
  @Inject
  protected IProgettoFormativoDao pfDao;
  @Inject
  protected ITutorInternoDao tutorDao;

  private Integer idPf;
  private Integer periodo;

  private static final long serialVersionUID = 1L;

  /**
   * doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String periodoStr = request.getParameter("periodo");
    String idPfStr = request.getParameter("pf");
    String userMail = (String) request.getSession().getAttribute("utenteEmail");
    try {
      idPf = Integer.parseInt(idPfStr);
      periodo = Integer.parseInt(periodoStr);
      boolean isValid = (periodo > 0) && (periodo <= 30);
      boolean isTutor = tutorDao.findById(TutorInterno.class, userMail) != null;
      ProgettoFormativo pf = pfDao.findById(ProgettoFormativo.class, idPf);

      if (isValid && isTutor && pf != null) {
        pf.setPeriodoReport(periodo.intValue());
        // Persist necessario per il salvataggio
        pfDao.persist(pf);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Aggiornamento effettuato correttamente");
        response.getWriter().flush();
      } else {
        int status = isTutor == false ? HttpServletResponse.SC_UNAUTHORIZED
            : HttpServletResponse.SC_BAD_REQUEST;
        response.setStatus(status);
        response.getWriter().write("I dati specificati non sono validi");
        response.getWriter().flush();
      }
    } catch (NumberFormatException ex) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("I dati devono essere degli interi");
      response.getWriter().flush();
    }
  }

  /**
   * doGet.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
