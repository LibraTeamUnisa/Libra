package it.unisa.libra.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Tutoresterno;
import it.unisa.libra.bean.TutoresternoPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.ITutorEsternoDao;

/**
 * Servlet implementation class GestioneTutorEsternoServlet. Controller class che gestisce le
 * operazioni di aggiunta, modifica e rimozione di un tutor esterno associato ad un'azienda.
 * 
 * @author Giulia Sellitto
 * @version 1.0
 */
@WebServlet(name = "GestioneTutorEsternoServlet", urlPatterns = "/gestioneTutorEsterno")
public class GestioneTutorEsternoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** gestore della persistenza per l'entità Azienda. **/
  @EJB
  private IAziendaDao aziendaDao;

  /** gestore della persistenza per l'entità TutorEsterno. **/
  @EJB
  private ITutorEsternoDao tutorDao;

  /** Default constructor. */
  public GestioneTutorEsternoServlet() {}

  /**
   * Gestisce la rimozione del tutor aziendale.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * Gestisce l'aggiunta e la modifica del tutor aziendale.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null) {
      // bad request
      response.sendError(400);
      return;
    }
    if (action.equals("aggiungi")) {
      aggiungi(request, response);
      return;
    } else if (action.equals("modifica")) {
      // Mauro
    } else if (action.equals("rimuovi")) {
      doGet(request, response);
      return;
    } else {
      // bad request
      response.sendError(400);
      return;
    }
  }

  private void aggiungi(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // di sicuro esiste ed è l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("email");
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda è stato eliminato dalla segreteria durante
      // questa esecuzione
      response.sendError(422);
      return;
    }
    String ambito = request.getParameter("ambito");
    TutoresternoPK idTutor = new TutoresternoPK();
    idTutor.setAziendaEmail(emailAzienda);
    idTutor.setAmbito(ambito);
    if (tutorDao.findById(Tutoresterno.class, idTutor) != null) {
      // primary key duplicata
      response.sendError(400,
          "Non è stato possibile aggiungere il tutor. Esiste già un tutor responsabile dell'ambito "
              + ambito);
      return;
    }
    // creo il tutor da aggiungere
    // i parametri sono corretti (lo assicura il check nella view)
    Tutoresterno tutor = new Tutoresterno();
    tutor.setId(idTutor);
    tutor.setNome(request.getParameter("nome"));
    tutor.setCognome(request.getParameter("cognome"));
    // la data di nascita del tutor è un datetime nel db
    String dataN = request.getParameter("dataDiNascita");
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dataDiNascita;
    try {
      dataDiNascita = format.parse(dataN);
    } catch (ParseException e) {
      dataDiNascita = new Date();
    }
    tutor.setDataDiNascita(dataDiNascita);
    tutor.setIndirizzo(request.getParameter("indirizzo"));
    tutor.setTelefono(request.getParameter("telefono"));
    // aggiungo il tutor all'azienda
    aggiungiTutor(azienda, tutor);
    // end
    request.setAttribute("message", "L'aggiunta del tutor è avvenuta con successo.");
    request.getRequestDispatcher("dashboardAzienda.jsp").forward(request, response);
    return;
  }

  /**
   * Aggiunge un tutor esterno all'azienda indicata.
   * 
   * @param azienda l'azienda a cui aggiungere il tutor
   * @param tutor il tutor da aggiungere all'azienda
   */
  private void aggiungiTutor(Azienda azienda, Tutoresterno tutor) {
    azienda.addTutoresterno(tutor);
    aziendaDao.persist(azienda);
    return;
  }

  /**
   * Aggiunge il dao alla servlet.
   * 
   * @param aziendaDao il dao da aggiungere
   **/
  public void setAziendaDao(IAziendaDao aziendaDao) {
    this.aziendaDao = aziendaDao;
  }

  /**
   * Aggiunge il dao alla servlet.
   * 
   * @param tutorDao il dao da aggiungere
   **/
  public void setTutorDao(ITutorEsternoDao tutorDao) {
    this.tutorDao = tutorDao;
  }

}
