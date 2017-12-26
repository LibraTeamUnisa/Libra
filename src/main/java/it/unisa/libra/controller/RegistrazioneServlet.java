package it.unisa.libra.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	  String dataNascita = request.getParameter("dataNascita");
	  String indirizzo = request.getParameter("indirizzo");
	  String telefono = request.getParameter("telefono");
	  Date data = null;
	  Gruppo gruppo = null;
	  
	  
	  try {  
		  data = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascita);
	  } catch (ParseException e) {
		  e.printStackTrace();
	  }
	  
	  /**
	   * Realizzazione di un oggetto di tipo studente
	   */
	  Studente studente = istanziaStudente(nome, cognome, email, matricola, data);

	  /**
	   * Realizzazione di utente, generalizzazione dello studente
	   */
	  Utente utente = istanziaUtente(email, studente, " ", indirizzo, password, telefono);
	  
	 
	  /**
	   * Realizzazione dell'oggetto gruppo di tipo "Studente"
	   */
	  
	  if((gruppo = gruppoDao.findById(Gruppo.class, "Studente"))!=null) {
	  		utente.setGruppo(gruppo);
	  }else {
		  response.setContentType("text/plain"); 
		  response.getWriter().write("Al momento non è possibile registrarsi al sistema");
	  }
	  
	  if(utenteDao.findById(Utente.class, email)==null) {
		  utenteDao.persist(utente);
		  response.setContentType("text/plain");
		  response.getWriter().write("Registrazione avvenuta con successo");
    	  }else{
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

  private Studente istanziaStudente(String nome, String cognome, String email, String matricola,
      Date dataNascita) {
    Studente studente = new Studente();
    studente.setNome(nome);
    studente.setCognome(cognome);
    studente.setUtenteEmail(email);
    studente.setMatricola(matricola);
    studente.setDataDiNascita(dataNascita);
    return studente;
  }

  private Utente istanziaUtente(String email, Studente studente, String img, String indirizzo,
      String password, String telefono) {
    Utente utente = new Utente();
    utente.setEmail(email);
    utente.setStudente(studente);
    utente.setImgProfilo(img);
    utente.setIndirizzo(indirizzo);
    utente.setPassword(password);
    utente.setTelefono(telefono);
    return utente;
  }

public void setUtenteDao(IUtenteDao utenteDao) {
	  this.utenteDao = utenteDao;
  }

public void setGruppoDao(IGruppoDao gruppoDao) {
	this.gruppoDao = gruppoDao;
  }
  
  


}
