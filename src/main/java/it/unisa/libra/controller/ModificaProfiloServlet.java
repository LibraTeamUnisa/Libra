package it.unisa.libra.controller;

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
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/* Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "ModificaProfiloServlet", urlPatterns = "/modificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /* Gestore della persistenza dello studente */
  @EJB
  private IStudenteDao studenteDao;

  /* Gestore della persistenza del tutor interno */
  @EJB
  private ITutorInternoDao tutorinternodao;

  /* Gestore della persistenza del presidente */
  @EJB
  private IPresidenteDao presidentedao;

  /* Gestore della persistenza dell'azienda */
  @EJB
  private IAziendaDao aziendadao;

  /* Gestore della persistenza della segreteria */
  @EJB
  private ISegreteriaDao segreteriadao;

  /* Variabili di istanza */
  private String email /* = "stefano@unisa.it" */;
  private String ruolo /* = "Segreteria" */;
  private String indirizzo;
  private String telefono;
  private String sito;
  private String ufficio;
  private String ricevimento;
  private String giorno1;
  private String giorno2;
  private String orario1;
  private String orario2;
  private boolean campoRicevimento = true;

  /** Default constructor. */
  public ModificaProfiloServlet() {}

  /*
   * @throws IOException
   * 
   * @throws ServletException
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession session = request.getSession();
    email = (String) session.getAttribute("utenteEmail");
    ruolo = (String) session.getAttribute("utenteRuolo");
    if (ruolo.equals(STUDENTE)) {
      modificaStudente(request, response);
    } else if (ruolo.equals(TUTOR_INTERNO)) {
      modificaTutorInterno(request, response);
    } else if (ruolo.equals(PRESIDENTE)) {
      modificaPresidente(request, response);
    } else if (ruolo.equals(SEGRETERIA)) {
      modificaSegreteria(request, response);
    } else if (ruolo.equals(AZIENDA)) {
      modificaAzienda(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("error");
    }
    response.sendRedirect("profilo.jsp");
  }

  /* Modifica Azienda */
  private void modificaAzienda(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Azienda azienda = aziendadao.findById(Azienda.class, email);
    Utente utente = azienda.getUtente();
    indirizzo = request.getParameter(SEDE);
    telefono = request.getParameter(TELEFONO);
    if (!telefono.equals("")) {
      utente.setTelefono(telefono);
    } else {
      telefono = utente.getTelefono();
    }
    if (!indirizzo.equals("")) {
      azienda.setSede(indirizzo);
    } else {
      indirizzo = azienda.getSede();
    }
    if (controllaParametri(request, response) == true) {
      aziendadao.persist(azienda);
    }
  }

  /* Modifica Segreteria */
  private void modificaSegreteria(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Segreteria segreteria = segreteriadao.findById(Segreteria.class, email);
    Utente utente = segreteria.getUtente();
    indirizzo = request.getParameter(INDIRIZZO);
    telefono = request.getParameter(TELEFONO);
    ufficio = request.getParameter(UFFICIO);
    giorno1 = request.getParameter(GIORNO1);
    giorno2 = request.getParameter(GIORNO2);
    orario1 = request.getParameter(ORARIO1);
    orario2 = request.getParameter(ORARIO2);
    if (orario1.equals("") || orario2.equals("")) {
      ricevimento = "";
      campoRicevimento = true;
    } else {
      ricevimento = "{" + giorno1 + ":" + orario1 + "," + giorno2 + ":" + orario2 + "}";
      campoRicevimento = false;
    }
    if (!telefono.equals("")) {
      utente.setTelefono(telefono);
    } else {
      telefono = utente.getTelefono();
    }
    if (!indirizzo.equals("")) {
      utente.setIndirizzo(indirizzo);
    } else {
      indirizzo = utente.getIndirizzo();
    }
    if (!ricevimento.equals("")) {
      segreteria.setGiorniDiRicevimento(ricevimento);
    } else {
      ricevimento = segreteria.getGiorniDiRicevimento();
    }
    if (controllaParametri(request, response) == true) {
      segreteriadao.persist(segreteria);
    }
  }

  /* Modifica Presidente */
  private void modificaPresidente(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Presidente presidente = (Presidente) presidentedao.findById(Presidente.class, email);
    Utente utente = presidente.getUtente();
    indirizzo = request.getParameter(INDIRIZZO);
    telefono = request.getParameter(TELEFONO);
    sito = request.getParameter(SITO);
    ufficio = request.getParameter(UFFICIO);
    giorno1 = request.getParameter(GIORNO1);
    giorno2 = request.getParameter(GIORNO2);
    orario1 = request.getParameter(ORARIO1);
    orario2 = request.getParameter(ORARIO2);
    if (orario1.equals("") || orario2.equals("")) {
      ricevimento = "";
      campoRicevimento = true;
    } else {
      ricevimento = "{" + giorno1 + ":" + orario1 + "," + giorno2 + ":" + orario2 + "}";
      campoRicevimento = false;
    }
    if (!telefono.equals("")) {
      utente.setTelefono(telefono);
    } else {
      telefono = utente.getTelefono();
    }
    if (!indirizzo.equals("")) {
      utente.setIndirizzo(indirizzo);
    } else {
      indirizzo = utente.getIndirizzo();
    }
    if (!sito.equals("")) {
      presidente.setLinkSito(sito);
    } else {
      sito = presidente.getLinkSito();
    }
    if (!ufficio.equals("")) {
      presidente.setUfficio(ufficio);
    } else {
      ufficio = presidente.getUfficio();
    }
    if (!ricevimento.equals("")) {
      presidente.setGiorniDiRicevimento(ricevimento);
    } else {
      ricevimento = presidente.getGiorniDiRicevimento();
    }
    if (controllaParametri(request, response) == true) {
      presidentedao.persist(presidente);
    }
  }

  /* Modifica Tutor Interno */
  private void modificaTutorInterno(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    TutorInterno tutor = tutorinternodao.findById(TutorInterno.class, email);
    Utente utente = tutor.getUtente();
    indirizzo = request.getParameter(INDIRIZZO);
    telefono = request.getParameter(TELEFONO);
    sito = request.getParameter(SITO);
    if (!telefono.equals("")) {
      utente.setTelefono(telefono);
    } else {
      telefono = utente.getTelefono();
    }
    if (!indirizzo.equals("")) {
      utente.setIndirizzo(indirizzo);
    } else {
      indirizzo = utente.getIndirizzo();
    }
    if (!sito.equals("")) {
      tutor.setLinkSito(sito);
    } else {
      sito = tutor.getLinkSito();
    }
    if (controllaParametri(request, response) == true) {
      tutorinternodao.persist(tutor);
    }
  }

  /* Modifica Studente */
  private void modificaStudente(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Studente studente = studenteDao.findById(Studente.class, email);
    Utente utente = studente.getUtente();
    indirizzo = request.getParameter(INDIRIZZO);
    telefono = request.getParameter(TELEFONO);
    if (!telefono.equals("")) {
      utente.setTelefono(telefono);
    } else {
      telefono = utente.getTelefono();
    }
    if (!indirizzo.equals("")) {
      utente.setIndirizzo(indirizzo);
    } else {
      indirizzo = utente.getIndirizzo();
    }
    if (controllaParametri(request, response) == true) {
      studenteDao.persist(studente);
    }
  }

  /* Controlla i parametri in base al test plan */
  private boolean controllaParametri(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (indirizzo.length() < 2 || indirizzo.length() > 100) {
      request.setAttribute("erroreLunghezzaIndirizzo", "Lunghezza indirizzo non consentita");
      RequestDispatcher lenght =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      lenght.forward(request, response);
      return false;
    } else if (telefono.length() < 9 || telefono.length() > 10) {
      request.setAttribute("erroreLunghezzaTelefono", "Lunghezza telefono non consentita");
      RequestDispatcher lenght =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      lenght.forward(request, response);
      return false;
    } else if (!isNumeric(telefono)) {
      request.setAttribute("erroreFormatoTelefono", "Puoi inserire solo numeri");
      RequestDispatcher format =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      format.forward(request, response);
      return false;
    } else if (ruolo.equals(PRESIDENTE) || ruolo.equals(SEGRETERIA)) {
      if (giorno1.equals(giorno2) && campoRicevimento == false) {
        request.setAttribute("ErroreRicevimento", "Impostare due giorni di ricevimento diversi");
        RequestDispatcher format =
            request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
        format.forward(request, response);
        return false;
      }
    }
    return true;
  }

  /* Controlla che la stringa passata sia composta di soli numeri */
  private static boolean isNumeric(String str) {
    try {
      @SuppressWarnings("unused")
      long l = Long.parseLong(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  /* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  /* MACRO */
  private static final String STUDENTE = "Studente";
  private static final String TUTOR_INTERNO = "TutorInterno";
  private static final String PRESIDENTE = "Presidente";
  private static final String SEGRETERIA = "Segreteria";
  private static final String AZIENDA = "Azienda";

  private static final String INDIRIZZO = "indirizzo";
  private static final String TELEFONO = "numeroTelefono";
  private static final String SITO = "sito";
  private static final String UFFICIO = "ufficio";
  private static final String RICEVIMENTO = "ricevimento";
  private static final String SEDE = "sede";
  private static final String GIORNO1 = "giorno1";
  private static final String GIORNO2 = "giorno2";
  private static final String ORARIO1 = "fasciaOraria1";
  private static final String ORARIO2 = "fasciaOraria2";
}
