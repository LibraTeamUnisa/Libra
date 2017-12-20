package it.unisa.libra.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.jpa.StudenteJpa;
import it.unisa.libra.util.Actions;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "GestioneUtenteServlet", urlPatterns = "/dettaglioStudente")
public class GestioneUtenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @EJB
  private IStudenteDao studenteDao;

  /** Default constructor. */
  public GestioneUtenteServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
	  if (request.getSession().getAttribute("utenteRuolo") != null) {
		  if (request.getParameter(Actions.ACTION).equals(Actions.DETTAGLIO_STUDENTE)) {
			  dettaglioStudente(request, response);
		  }
	  } else {
		  
	  }
	  
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
  }
  
  private void dettaglioStudente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if (request.getParameter("email-studente") != null) {
		  Studente s = studenteDao.findById(Studente.class, request.getParameter("email-studente"));
		  request.setAttribute("studente", s);
		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo.jsp");
		  dispatcher.forward(request, response);
	  }
  }
}
