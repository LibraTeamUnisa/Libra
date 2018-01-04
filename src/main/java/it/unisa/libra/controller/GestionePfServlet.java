package it.unisa.libra.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;
import it.unisa.libra.util.JsonUtils;

/** Servlet implementation class AutenticazioneServlet */
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
      }
      if (request.getParameter(Actions.ACTION).equals(Actions.PF_NUM_TIROCINI_COMPLETATI)) {
        Long numTirocini = progettoFormativoDao.getNumTirociniCompletati();
        if (numTirocini != null) {
          response.getWriter().write(numTirocini.toString());
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
