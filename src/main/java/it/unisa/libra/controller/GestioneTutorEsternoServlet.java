package it.unisa.libra.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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
import it.unisa.libra.util.CheckUtils;

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

  /** gestore della persistenza per l'entit� Azienda. **/
  @EJB
  private IAziendaDao aziendaDao;

  /** gestore della persistenza per l'entit� TutorEsterno. **/
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
    
    //if(request.getSession().getAttribute("user")==null)
      //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    
    System.out.println("AAAAA");
    
    String action = request.getParameter("action");
    if (action == null) {
      // bad request
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    if (action.equals("aggiungi")) {
      aggiungi(request, response);
      return;
    } else if (action.equals(Actions.UPDATE)) {System.out.println("AAAAA");
      aggiorna(request, response);
    } else if (action.equals("rimuovi")) {
      doGet(request, response);
      return;
    } else {
      // bad request
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
  }

  private void aggiungi(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // di sicuro esiste ed � l'email di un'azienda grazie ai filtri
    String emailAzienda = (String) request.getSession().getAttribute("email");
    // recupero l'azienda
    Azienda azienda = aziendaDao.findById(Azienda.class, emailAzienda);
    if (azienda == null) {
      // l'utente azienda � stato eliminato dalla segreteria durante
      // questa esecuzione
      response.sendError(422);
      return;
    }
    String ambito = request.getParameter("ambito");
    TutorEsternoPK idTutor = new TutorEsternoPK();
    idTutor.setAziendaEmail(emailAzienda);
    idTutor.setAmbito(ambito);
    if (tutorDao.findById(TutorEsterno.class, idTutor) != null) {
      // primary key duplicata
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "Non � stato possibile aggiungere il tutor. Esiste gi� un tutor responsabile dell'ambito "
              + ambito);
      return;
    }
    // creo il tutor da aggiungere
    // i parametri sono corretti (lo assicura il check nella view)
    TutorEsterno tutor = new TutorEsterno();
    tutor.setId(idTutor);
    tutor.setNome(request.getParameter("nome"));
    tutor.setCognome(request.getParameter("cognome"));
    // la data di nascita del tutor � un datetime nel db
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
    request.setAttribute("message", "L'aggiunta del tutor � avvenuta con successo.");
    request.getServletContext().getRequestDispatcher("dashboardAzienda.jsp").forward(request, response);
    return;
  }
  
  /**
   * Consente di gestire la richiesta di modifica delle informazioni di un tutor associato
   * ad un'azienda e restituisce il codice 200 nel caso in cui l'operazione sia andata a buon
   * fine o, in caso contrario, il codice 400, a fronte dalle specifica di parametri non validi.
   * @param request Indica l'oggetto HttpServletRequest generato dal container contenente la richiesta
   *                inviata dal client
   * @param response Indica l'oggetto HttpServletResponse generato dal container contenente la risposta
   *                 inviata al client
   * @throws IOException Lanciata nel caso in cui non � possibile ottenere il writer associato all'oggetto 
   *                     HttpServletResponse
   */
  private void aggiorna(HttpServletRequest request,HttpServletResponse response) 
          throws IOException
  {
    String idTutor=request.getParameter("idTutor");
    String idAzienda=request.getParameter("idAzienda");
    if(idAzienda!=null&&idTutor!=null) {
      TutorEsternoPK tutorKey=new TutorEsternoPK();
      tutorKey.setAmbito(idTutor);
      tutorKey.setAziendaEmail(idAzienda);
      TutorEsterno tutor=tutorDao.findById(TutorEsterno.class, tutorKey);
      if(tutor!=null) {
        String nome=request.getParameter("nome");
        String cognome=request.getParameter("cognome");
        String telefono=request.getParameter("telefono");
        String data=request.getParameter("dataDiNascita");
        String indirizzo=request.getParameter("indirizzo");
        String ambito=request.getParameter("ambito");
        Integer count=0;
        
        if(CheckUtils.checkEmptiness(nome)) {
          tutor.setNome(nome);
          count++;
        }
        if(CheckUtils.checkEmptiness(cognome)) {
          tutor.setCognome(cognome);
          count++;
        }
        if(CheckUtils.checkTelephone(telefono)) {
          tutor.setTelefono(telefono);
        }
        if(CheckUtils.checkDate(data)!=null) {
          tutor.setDataDiNascita(CheckUtils.checkDate(data));
          count++;
        }
        if(CheckUtils.checkEmptiness(indirizzo)) {
          tutor.setIndirizzo(indirizzo);
          count++;
        }
        tutorKey.setAmbito(ambito);
        if(CheckUtils.checkEmptiness(ambito)&&tutorDao.findById(TutorEsterno.class, tutorKey)==null) {
          tutor.setId(tutorKey);
          System.out.println("Ci Sono");
          count++;
        }
        
        if(count==0) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          response.getWriter().write("Le informazioni specificate non sono corrette");
        }
        else {
          response.setStatus(HttpServletResponse.SC_OK);
          response.getWriter().write("Operazione terminata. Aggionati "+count+" campi");
        }
        
        response.getWriter().flush();
      }
      else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Il tutor specificato non � registrato a Libra");
        response.getWriter().flush();
      }
    }
  }
  

  /**
   * Aggiunge un tutor esterno all'azienda indicata.
   * 
   * @param azienda l'azienda a cui aggiungere il tutor
   * @param tutor il tutor da aggiungere all'azienda
   */
  private void aggiungiTutor(Azienda azienda, TutorEsterno tutor) {
    System.out.println("ciao");
    // azienda.addTutorEsterno(tutor);
    // aziendaDao.persist(azienda);
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
