package it.unisa.libra.controller;

import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IStudenteDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Questa classe permette allo studente di registrarsi.
 *
 * @author [Vincenzo Gallicchio]
 * @version [0.0]
 */

/** Servlet implementation class AutenticazioneServlet. **/
@WebServlet(name = "RegistrazioneServlet", urlPatterns = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

  @Inject
  private IStudenteDao studenteDao;
  @Inject
  private IGruppoDao gruppoDao;


  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public RegistrazioneServlet() {}

  /**
   * Override.
   * 
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
      response.setContentType("text/plain");
      response.getWriter().write("Errore durante il parse della data");
    }

    /*
     * Realizzazione di un oggetto di tipo studente
     */
    Studente studente = istanziaStudente(nome, cognome, email, matricola, data);

    /*
     * Realizzazione di utente, generalizzazione dello studente
     */
    Utente utente = istanziaUtente(email, studente, " ", indirizzo, password, telefono);
    studente.setUtente(utente);

    gruppo = gruppoDao.findById(Gruppo.class, "Studente");

    if (gruppo != null) {
      if (studenteDao.findById(Studente.class, email) == null) {
        studente.getUtente().setGruppo(gruppo);
        studenteDao.persist(studente);
        response.setContentType("text/plain");
        response.getWriter().write("Registrazione avvenuta con successo");
      } else {
        response.setContentType("text/plain");
        response.getWriter().write("Utente già presente nel sistema");
      }
    } else {
      response.setContentType("text/plain");
      response.getWriter().write("Al momento non è possibile registrarsi al sistema");
    }
  }

  /**
   * Override.
   * 
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

}
