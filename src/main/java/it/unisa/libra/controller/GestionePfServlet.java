package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;
import it.unisa.libra.util.JsonUtils;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet implementation class GestionePfServlet. */
@WebServlet(name = "GestionePfServlet", urlPatterns = {"/gestionePfServlet"})

public class GestionePfServlet extends HttpServlet {

  @Inject
  IProgettoFormativoDao progettoFormativoDao;

  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public GestionePfServlet() {}

  /** 
   * doGet.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
   */
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
    }
  }

  /** 
   * doPost.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
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
