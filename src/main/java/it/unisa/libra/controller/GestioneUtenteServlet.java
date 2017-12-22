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

import it.unisa.libra.bean.ProgettoFormativo;
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
	  
	  String ruolo = (String) request.getSession().getAttribute("utenteRuolo");
	  
	  if (request.getParameter(Actions.ACTION).equals(Actions.DETTAGLIO_STUDENTE)) {
		  if (ruolo != null && (ruolo.equals("Segreteria") || ruolo.equals("Presidente") || ruolo.equals("TutorInterno"))) {
				  dettaglioStudente(request, response);
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
	  
  }
  
  /*
   * Metodo che consente la visualizzazione del dettaglio di uno studente.
   */
  private void dettaglioStudente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String mailStudente = request.getParameter("email-studente");
	  if (mailStudente != null && studenteDao.findById(Studente.class, mailStudente) != null) {
		  Studente s = studenteDao.findById(Studente.class, mailStudente);
		  ProgettoFormativo pf = null;
		  if (!s.getProgettiFormativi().isEmpty()) {
			  pf = s.getProgettiFormativi().get(s.getProgettiFormativi().size()-1);
		  }
		  request.setAttribute("studente", s);
		  request.setAttribute("progettoFormativo", pf);
		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dettaglioStudente.jsp");
		  dispatcher.forward(request, response);
	  } else {
		  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	      response.getWriter().write("E-mail studente mancante o errata.");
	      response.getWriter().flush();
	  }
  }
}
