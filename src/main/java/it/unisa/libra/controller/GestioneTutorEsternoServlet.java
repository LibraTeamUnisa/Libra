package it.unisa.libra.controller;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import it.unisa.libra.util.Actions;

/**
 * Servlet implementation class GestioneTutorEsternoServlet. Controller class che gestisce le
 * operazioni di aggiunta, modifica e rimozione di un tutor esterno associato ad un'azienda.
 * 
 * @author Giulia Sellitto
 * @version 1.0
 */
@WebServlet(name = "GestioneTutorEsternoServlet", urlPatterns = "/gestioneTutorEsternoServlet")
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
    String action = request.getParameter(Actions.ACTION);
    if (action == null) {
      // bad request 400
      response.getWriter().write(BADREQUEST_MESS);
      return;
    } else if (action.equals(Actions.RIMUOVI_TUTOR_ESTERNO)) {
      rimuovi(request, response);
      return;
    } else {
      // bad request 400
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }
 }

  /**
   * Gestisce l'aggiunta e la modifica del tutor aziendale.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getParameter(Actions.ACTION);
    if (action == null) {
      // bad request 400
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }
    if (action.equals(Actions.AGGIUNGI_TUTOR_ESTERNO)) {
      aggiungi(request, response);
      return;
    } else if (action.equals(Actions.MODIFICA_TUTOR_ESTERNO)) {
      // Mauro
    } else if (action.equals(Actions.RIMUOVI_TUTOR_ESTERNO)) {
      doGet(request, response);
      return;
    } else {
      // bad request 400
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }
  }

  /**
   * Gestisce l'operazione di aggiunta del tutor esterno.
   * 
   * @param request parametro esplicito del metodo doPost
   * @param response parametro esplicito del metodo doPost
   * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *         response)
   * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request,
   *         HttpServletResponse response)
   */
  private void aggiungi(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // di sicuro esiste ed è l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("email");
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda è stato eliminato dalla segreteria durante questa esecuzione
      // errorCode 422
      response.getWriter().write("Si &egrave; verificato un errore");
      return;
    }
    String ambito = request.getParameter("ambito");
    TutorEsternoPK idTutor = new TutorEsternoPK();
    idTutor.setAziendaEmail(emailAzienda);
    idTutor.setAmbito(ambito);
    if (tutorDao.findById(TutorEsterno.class, idTutor) != null) {
      // primary key duplicata
      response.getWriter().write("Non &egrave; stato possibile aggiungere il tutor. "
          + "Esiste gi&agrave; un tutor responsabile dell'ambito " + ambito);
      return;
    }
    // creo il tutor da aggiungere
    // i parametri sono corretti (lo assicura il check nella view)
    TutorEsterno tutor = new TutorEsterno();
    tutor.setId(idTutor);
    tutor.setNome(request.getParameter("nome"));
    tutor.setCognome(request.getParameter("cognome"));
    Date dataDiNascita = new Date();
    tutor.setDataDiNascita(dataDiNascita);
    tutor.setIndirizzo(request.getParameter("indirizzo"));
    tutor.setTelefono(request.getParameter("telefono"));
    // aggiungo il tutor all'azienda
    tutorDao.persist(tutor);
    // end
    response.getWriter().write(SUCCESS_MESS);
    return;
  }

  
  /**
   * Gestisce l'operazione di rimozione del tutor esterno.
   * 
   * @param request parametro esplicito del metodo doPost
   * @param response parametro esplicito del metodo doPost
   * @throws IOException @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *         response)
   * @throws ServletException @see HttpServlet#doPost(HttpServletRequest request,
   *         HttpServletResponse response)
   */
  private void rimuovi(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // di sicuro esiste ed è l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("email");
    emailAzienda = "azienda@prova.it";
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda è stato eliminato dalla segreteria durante questa esecuzione
      // errorCode 422
      response.getWriter().write("Si &egrave; verificato un errore");
      return;
    }
    String ambito = request.getParameter("ambito");
    TutorEsternoPK idTutor = new TutorEsternoPK();
    idTutor.setAziendaEmail(emailAzienda);
    idTutor.setAmbito(ambito);
    TutorEsterno tutor = tutorDao.findById(TutorEsterno.class, idTutor);
    if (tutor == null) {
      // il tutor da eliminare non esiste
      // BAD REQUEST
      response.getWriter().write(BADREQUEST_MESS);
      return;
    }
    // rimuovo il tutor dal sistema
    tutorDao.remove(TutorEsterno.class, idTutor);
    // end
    response.getWriter().write(SUCCESS_MESS);
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

  /** messaggio di errore inviato in caso di bad request. **/
  private static final String BADREQUEST_MESS = "L'operazione richiesta non &egrave; valida.";

  /** messaggio restituito in caso di successo dell'operazione. **/
  private static final String SUCCESS_MESS = "ok";

}
