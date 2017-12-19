package it.unisa.libra.controller;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IUtenteDao;




/**
* Questa classe permette allo studente di registrarsi.
*
* @author [Vincenzo Gallicchio]
* @version [0.0]
*/

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "RegistrazioneServlet", urlPatterns = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

  @Inject
  private IUtenteDao utenteDao;
  @Inject
  private IGruppoDao gruppoDao;
  
  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public RegistrazioneServlet() {}

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  /**
	   * Parametri necessari alla registrazione presi dall'oggetto request.
	   */

	  String nome = request.getParameter("nome");
	  String cognome = request.getParameter("cognome");
	  String matricola = request.getParameter("matricola");
	  String email = request.getParameter("email");
	  String password = request.getParameter("password");
	  String[] dataNascita = request.getParameter("dataNascita").split("/");
	  String indirizzo = request.getParameter("indirizzo");
	  String telefono = request.getParameter("telefono");
	  
	  Date data = new Date();
	  data.setDate(Integer.parseInt(dataNascita[0]));
	  data.setMonth(Integer.parseInt(dataNascita[1])-1);
	  data.setYear(Integer.parseInt(dataNascita[2])-1900);
	  
	  
	  /**
	   * Realizzazione di un oggetto di tipo studente
	   */

	  Studente studente = new Studente();
	  studente.setNome(nome);
	  studente.setCognome(cognome);
	  studente.setUtenteEmail(email);
	  studente.setMatricola(matricola);
	  studente.setDataDiNascita(data);

	  /**
	   * Realizzazione di utente, generalizzazione dello studente
	   */

	  Utente utente = new Utente();
	  utente.setEmail(email);
	  utente.setStudente(studente);
	  utente.setImgProfilo(" ");
	  utente.setIndirizzo(indirizzo);
	  utente.setPassword(password);
	  utente.setTelefono(telefono);
	 
	  /**
	   * Realizzazione dell'oggetto gruppo di tipo "Studente"
	   */
	  
	  Gruppo gruppo = new Gruppo();
	  gruppo.setRuolo("Studente");
   
	 /**
	  * Scrittura su Database del gruppo.
	  * Si controlla se l'informazione non è presente.
	  */
	  if(gruppoDao.findById(Gruppo.class, "Studente")==null) {
		  gruppoDao.persist(gruppo);
	  }
	
	  utente.setGruppo(gruppo);
    	try {
    		utenteDao.persist(utente);
    		response.setContentType("text/plain");
    		response.getWriter().write("Registrazione avvenuta con successo");
    		response.sendRedirect("home.jsp");    	
    		}catch(EJBTransactionRolledbackException exception) {
    			response.setContentType("text/plain"); 
    			response.getWriter().write("Utente già presente nel sistema");
    	}
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
