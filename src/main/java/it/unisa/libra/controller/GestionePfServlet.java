package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo; 
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;
import it.unisa.libra.util.JsonUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class AutenticazioneServlet. */
@WebServlet(name = "GestionePfServlet", urlPatterns = {"/gestionePfServlet"})
public class GestionePfServlet extends HttpServlet {

  @Inject
  IProgettoFormativoDao progettoFormativoDao;

  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public GestionePfServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (CheckUtils.validAction(request)) {
      if (request.getParameter(Actions.ACTION).equals(Actions.PF_TOP_AZIENDE)) {
        String pastDays = request.getParameter("pastDays");
        String limit = request.getParameter("limit");
        String status = request.getParameter("status");

        Map<String, String> mapTop =
            progettoFormativoDao.getTopAziendeFromNumStudenti(pastDays, limit, status);
        response.getWriter().write(JsonUtils.parseMapToJson(mapTop));
      } else if (request.getParameter(Actions.ACTION).equals(Actions.PF_NUM_TIROCINI_COMPLETATI)) {
        Long numTirocini = progettoFormativoDao.getNumTirociniCompletati();
        if (numTirocini != null) {
          response.getWriter().write(numTirocini.toString());
        }
      } else if (request.getParameter(Actions.ACTION).equals(Actions.PF_COUNT_BY_AZIENDA)) {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String limit = request.getParameter("limit");
        String status = request.getParameter("status");
        String ragSoc = request.getParameter("ragSoc");

        List<Map<String, String>> list = progettoFormativoDao.countByAziendaAndDate(
            CheckUtils.parseDateWithPattern(fromDate, "yyyy-MM-dd"),
            CheckUtils.parseDateWithPattern(toDate, "yyyy-MM-dd"), limit, status, ragSoc);
        if (list != null) {
          response.getWriter().write(JsonUtils.parseListToJson(list));
        }
      } else if (request.getParameter(Actions.ACTION).equals(Actions.PF_TABELLA_VALUTAZIONI)) {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String status = request.getParameter("status");
        String ragSoc = request.getParameter("ragSoc");

        List<Map<String, String>> list = progettoFormativoDao.getTabellaValutazioni(
            CheckUtils.parseDateWithPattern(fromDate, "yyyy-MM-dd"),
            CheckUtils.parseDateWithPattern(toDate, "yyyy-MM-dd"), status, ragSoc);
        if (list != null) {
          response.getWriter().write(JsonUtils.parseListToJson(list));
        }
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Azione non valida!");
        response.getWriter().flush();
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Azione non valida!");
      response.getWriter().flush();
    }
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getParameter(Actions.ACTION).equals(Actions.MODIFICA_STATO_TIROCINIO)) {
      ProgettoFormativo pf = progettoFormativoDao.findById(ProgettoFormativo.class,
          Integer.parseInt(request.getParameter("id")));
      pf.setStato(Integer.parseInt(request.getParameter("stato")));
      progettoFormativoDao.persist(pf);
      response.getWriter().write("true");
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Azione non valida!");
      response.getWriter().flush();
    }
  }
}
