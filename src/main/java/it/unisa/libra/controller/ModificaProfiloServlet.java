package it.unisa.libra.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antlr.StringUtils;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.model.dao.ISegreteriaDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.util.Actions;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name="ModificaProfiloServlet", urlPatterns="/modificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /** Gestore della persistenza dello studente */
  @EJB
  private IStudenteDao studenteDao;
  
  /** Gestore della persistenza del tutor interno */
  @EJB
  private ITutorInternoDao tutorinternodao;
  
  /** Gestore della persistenza del presidente */
  @EJB
  private IPresidenteDao presidentedao;
  
  /** Gestore della persistenza dell'azienda */
  @EJB
  private IAziendaDao aziendadao;
  
  /** Gestore della persistenza della segreteria */
  @EJB
  private ISegreteriaDao segreteriadao;
  
  private String nuovoIndirizzo;
  private String nuovoNumeroDiTelefono;
  private String nuovoSito;
  private String nuovoUfficio;
  private String nuovoRicevimento;
  private String email = "stefano@unisa.it";
  private String ruolo = "Segreteria";
  /** Default constructor. */
  public ModificaProfiloServlet() {}
  
  /** @throws IOException 
   * @throws ServletException 
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
/*      HttpSession session = request.getSession();
      String email = (String) session.getAttribute("utenteEmail");
      String ruolo = (String) session.getAttribute("utenteRuolo");
*/      nuovoIndirizzo = request.getParameter("indirizzo");
      nuovoNumeroDiTelefono = request.getParameter("numeroTelefono");
      if(ruolo.equals("Segreteria")) {
    	  modificaSegreteria(request, response);
      }
      response.sendRedirect("aaa.jsp");
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
  /* Modifica i dati dell'utente Segreteria */
  
  private void modificaSegreteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Segreteria segreteria = (Segreteria) segreteriadao.findById(Segreteria.class, email);
	  Utente user = segreteria.getUtente();
	  nuovoRicevimento = request.getParameter("ricevimento");
	  /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
	  if(nuovoIndirizzo.equals("")) {
		  nuovoIndirizzo = user.getIndirizzo();
	  }
	  if(nuovoNumeroDiTelefono.equals("")) {
		  nuovoNumeroDiTelefono = user.getTelefono();
	  }
	  if(nuovoRicevimento.equals("")) {
		  nuovoRicevimento = segreteria.getGiorniDiRicevimento();
	  }
	  /* Controlli relativi al test plan */
/*	  if (nuovoIndirizzo.length() < 2 || nuovoIndirizzo.length() > 100) {
		  request.setAttribute("erroreModifica", "L'indirizzo deve essere compreso tra i 2 e i 100 caratteri");
		  RequestDispatcher erroreModifica = request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
		  erroreModifica.forward(request, response);
	  } else if (nuovoNumeroDiTelefono.length() != 10) {
		  request.setAttribute("erroreModifica", "Il numero di telefono deve essere composto da esattamente 10 caratteri");
		  RequestDispatcher erroreModifica = request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
		  erroreModifica.forward(request, response);
	  } else if (!org.apache.commons.lang.StringUtils.isNumeric("nuovoNumeroDiTelefono")) {
		  request.setAttribute("erroreModifica", "Il numero di telefono deve contenere esclusivamente numeri");
		  RequestDispatcher erroreModifica = request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
		  erroreModifica.forward(request, response);
	  }
*/	  user.setIndirizzo(nuovoIndirizzo);
	  user.setTelefono(nuovoNumeroDiTelefono);
	  segreteria.setGiorniDiRicevimento(nuovoRicevimento);
	  segreteriadao.merge(segreteria);
	  
  }
}
