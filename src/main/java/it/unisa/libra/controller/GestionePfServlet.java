package it.unisa.libra.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.Actions;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "GestionePfServlet", urlPatterns = "/gestionePF")

public class GestionePfServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @EJB
  private IProgettoFormativoDao pfDao;

  /** Default constructor. */
  public GestionePfServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	 if (request.getParameter(Actions.ACTION).equals(Actions.MODIFICA_STATO_TIROCINIO)) {
		 ProgettoFormativo pf = pfDao.findById(ProgettoFormativo.class, Integer.parseInt(request.getParameter("id")));
		 pf.setStato(Integer.parseInt(request.getParameter("stato")));
		 pfDao.persist(pf);
		 response.getWriter().write("true");
	 } else {
		 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	      response.getWriter().write("Azione non valida!");
	      response.getWriter().flush();
	 }
  }
}
