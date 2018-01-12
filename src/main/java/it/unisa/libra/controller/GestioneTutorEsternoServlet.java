package it.unisa.libra.controller;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.CheckUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestioneTutorEsternoServlet. Controller class che gestisce le
 * operazioni di aggiunta, modifica e rimozione di un tutor esterno associato ad un'azienda.
 * 
 * @author Giulia Sellitto, Mauro Vitale
 * @version 1.0
 */
@WebServlet(name = "GestioneTutorEsternoServlet", urlPatterns = "/gestioneTutorEsternoServlet")
public class GestioneTutorEsternoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** gestore della persistenza per l'entita' Azienda. **/
  @EJB
  private IAziendaDao aziendaDao;

  /** gestore della persistenza per l'entita' TutorEsterno. **/
  @EJB
  private ITutorEsternoDao tutorDao;

  /** Default constructor. */
  public GestioneTutorEsternoServlet() {}

  /**
   * Questa servlet non fornisce servizi tramite il metodo doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // response.getWriter().write(BADREQUEST_MESS);
    doPost(request, response);
    return;
  }

  /**
   * Gestisce l'aggiunta, la modifica e la rimozione del tutor aziendale.
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
      aggiorna(request, response);
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
    // di sicuro esiste ed e' l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("utenteEmail");
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda e' stato eliminato dalla segreteria durante questa esecuzione
      // errorCode 422
      response.getWriter().write("Si e' verificato un errore");
      return;
    }
    String ambito = request.getParameter("ambito");
    if (ambito == null) {
      // bad request
      response.getWriter().write(BADREQUEST_MESS + " Compila tutti i campi!");
      return;
    }
    TutorEsternoPK idTutor = new TutorEsternoPK();
    idTutor.setAziendaEmail(emailAzienda);
    idTutor.setAmbito(ambito);
    if (tutorDao.findById(TutorEsterno.class, idTutor) != null) {
      // primary key duplicata
      response.getWriter().write("Non e' stato possibile aggiungere il tutor. "
          + "Esiste gia' un tutor responsabile dell'ambito " + ambito);
      return;
    }
    // recupero i parametri dalla request e controllo che siano validi
    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cognome");
    String indirizzo = request.getParameter("indirizzo");
    String telefono = request.getParameter("telefono");
    if (nome == null || cognome == null || indirizzo == null || telefono == null) {
      // bad request
      response.getWriter().write(BADREQUEST_MESS + " Compila tutti i campi!");
      return;
    }
    if (!ambito.matches(REGEX_AMBITO) || !nome.matches(REGEX_NOME)
        || !cognome.matches(REGEX_COGNOME) || !indirizzo.matches(REGEX_INDIRIZZO)
        || !telefono.matches(REGEX_TELEFONO)) {
      // bad request
      response.getWriter().write(BADREQUEST_MESS + " Rispetta il formato richiesto!");
      return;
    }
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date dataDiNascita;
    try {
      dataDiNascita = parser.parse(request.getParameter("dataDiNascita"));
    } catch (ParseException e) {
      response.getWriter().write(BADREQUEST_MESS + " Rispetta il formato richiesto!");
      return;
    }
    if (!CheckUtils.notInFuture(dataDiNascita)) {
      response.getWriter().write(BADREQUEST_MESS + " Davvero il tuo tutor viaggia nel tempo?");
      return;
    }
    if (!CheckUtils.isMaggiorenne(dataDiNascita)) {
      response.getWriter().write(BADREQUEST_MESS + " UniSa non tollera il lavoro minorile.");
      return;
    }
    // creo il tutor da aggiungere
    TutorEsterno tutor = new TutorEsterno();
    tutor.setId(idTutor);
    tutor.setNome(nome);
    tutor.setCognome(cognome);
    tutor.setDataDiNascita(dataDiNascita);
    tutor.setIndirizzo(indirizzo);
    tutor.setTelefono(telefono);
    // aggiungo il tutor all'azienda
    tutorDao.persist(tutor);
    // end
    response.getWriter().write(SUCCESS_MESS);
    return;
  }

  /**
   * Consente di gestire la richiesta di modifica delle informazioni di un tutor associato ad
   * un'azienda e restituisce il codice 200 nel caso in cui l'operazione sia andata a buon fine o,
   * in caso contrario, il codice 400, a fronte dalle specifica di parametri non validi.
   * 
   * @param request Indica l'oggetto HttpServletRequest generato dal container contenente la
   *        richiesta inviata dal client
   * @param response Indica l'oggetto HttpServletResponse generato dal container contenente la
   *        risposta inviata al client
   * @throws IOException Lanciata nel caso in cui non e' possibile ottenere il writer associato
   *         all'oggetto HttpServletResponse
   */
  private void aggiorna(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String idTutor = request.getParameter("idTutor");
    String idAzienda = request.getParameter("idAzienda");
    if (CheckUtils.checkEmptiness(idTutor) && CheckUtils.checkEmail(idAzienda)) {

      TutorEsternoPK tutorKey = new TutorEsternoPK();
      tutorKey.setAmbito(idTutor);
      tutorKey.setAziendaEmail(idAzienda);

      TutorEsterno tutor = tutorDao.findById(TutorEsterno.class, tutorKey);
      if (tutor != null) {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("telefono");
        String data = request.getParameter("dataDiNascita");
        String indirizzo = request.getParameter("indirizzo");
        String ambito = request.getParameter("ambito");
        Integer count = 0;
        boolean toRemove=false;
        if (CheckUtils.checkEmptiness(ambito)) {
          TutorEsternoPK newKey = new TutorEsternoPK();
          newKey.setAziendaEmail(tutorKey.getAziendaEmail());
          newKey.setAmbito(ambito);
          boolean notSet = tutorDao.findById(TutorEsterno.class, newKey) == null;

          if (notSet) {
            tutor.setId(newKey);
            count++;
            toRemove=true;
          } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Non puoi assegnare il tutor all'ambito specificato");
            response.getWriter().flush();
            return;
          }
        }
        if (CheckUtils.checkEmptiness(nome)) {
          tutor.setNome(nome);
          count++;
        }
        if (CheckUtils.checkEmptiness(cognome)) {
          tutor.setCognome(cognome);
          count++;
        }
        if (CheckUtils.checkTelephone(telefono)) {
          tutor.setTelefono(telefono);
          count++;
        }

        boolean isParsable = CheckUtils.parseDate(data) != null
            || CheckUtils.parseDateWithPattern(data, "yyyy-MM-dd") != null;
        
        if (isParsable) {
          Date newDate = CheckUtils.parseDateWithPattern(data, "yyyy-MM-dd") != null
              ? CheckUtils.parseDateWithPattern(data, "yyyy-MM-dd") : CheckUtils.parseDate(data);
          if(CheckUtils.notInFuture(newDate)&&CheckUtils.isMaggiorenne(newDate)) {
            tutor.setDataDiNascita(newDate);
            count++;
          } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Hai specificato una data non valida");
            response.getWriter().flush();
            return;
          }
        }
        if (CheckUtils.checkEmptiness(indirizzo)) {
          tutor.setIndirizzo(indirizzo);
          count++;
        }

        if (count == 0) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          response.getWriter().write("Le informazioni specificate non sono corrette");
        } else {
          
          if(toRemove) {
            tutorDao.remove(TutorEsterno.class, tutorKey);
          }
          
          tutorDao.persist(tutor);
          response.setStatus(HttpServletResponse.SC_OK);
          response.getWriter().write("Operazione terminata. Aggiornati " + count + " campi");
        }

        response.getWriter().flush();

      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Il tutor specificato non e' registrato a Libra");
        response.getWriter().flush();
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Non hai specificato correttamente il tutor da modificare");
      response.getWriter().flush();
    }
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
    // di sicuro esiste ed e' l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("utenteEmail");
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda e' stato eliminato dalla segreteria durante questa esecuzione
      // errorCode 422
      response.getWriter().write("Si e' verificato un errore");
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
  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";

  /** messaggio restituito in caso di successo dell'operazione. **/
  private static final String SUCCESS_MESS = "L'operazione e' avvenuta correttamente.";

  private static final String REGEX_AMBITO = "[a-zA-Z]+[a-zA-z '0-9\\(\\)\\-]*[a-zA-Z0-9\\)]+";
  private static final String REGEX_NOME = "[a-zA-Z]+[a-zA-z ']*[a-zA-Z]+";
  private static final String REGEX_COGNOME = "[a-zA-Z]+[a-zA-z ']*[a-zA-Z]+";
  private static final String REGEX_INDIRIZZO = "[a-zA-Z]+[a-zA-Z, 0-9\\(\\)]*[a-zA-Z\\)]+";
  private static final String REGEX_TELEFONO = "[0-9]{8,11}";


}
