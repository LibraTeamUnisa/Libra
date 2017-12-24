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

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "ModificaProfiloServlet", urlPatterns = "/modificaProfilo")
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
  private boolean flag = true;
  private String email /* = "stefano@unisa.it" */;
  private String ruolo /* = "Segreteria" */;

  /** Default constructor. */
  public ModificaProfiloServlet() {}

  /**
   * @throws IOException
   * @throws ServletException
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession session = request.getSession();
    String email = (String) session.getAttribute("utenteEmail");
    String ruolo = (String) session.getAttribute("utenteRuolo");
      nuovoNumeroDiTelefono = request.getParameter("numeroTelefono");
      if (ruolo.equals("Segreteria")) {
        modificaSegreteria(request, response);
      } else if (ruolo.equals("Studente")) {
        modificaStudente(request, response);
      } else if (ruolo.equals("TutorInterno")) {
        modificaTutorInterno(request, response);
      } else if (ruolo.equals("Presidente")) {
        modificaPresidente(request, response);
      } else if (ruolo.equals("Azienda")) {
        modificaAzienda(request, response);
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("false");
      }
      response.sendRedirect("profilo.jsp");
    }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  /* Modifica i dati dell'azienda */

  private void modificaAzienda(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Azienda azienda = (Azienda) aziendadao.findById(Azienda.class, email);
    Utente user = azienda.getUtente();
    nuovoIndirizzo = request.getParameter("sede");
    /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
    if (nuovoIndirizzo.equals("")) {
      nuovoIndirizzo = azienda.getSede();
    }
    if (nuovoNumeroDiTelefono.equals("")) {
      nuovoNumeroDiTelefono = user.getTelefono();
    }
    controllaErrori(request, response);
    user.setTelefono(nuovoNumeroDiTelefono);
    azienda.setSede(nuovoIndirizzo);
    if (flag == true) {
      aziendadao.persist(azienda);
    }
  }

  /* Modifica i dati del Presidente */

  private void modificaPresidente(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Presidente presidente = (Presidente) presidentedao.findById(Presidente.class, email);
    Utente user = presidente.getUtente();
    nuovoIndirizzo = request.getParameter("indirizzo");
    nuovoSito = request.getParameter("sito");
    nuovoUfficio = request.getParameter("ufficio");
    nuovoRicevimento = request.getParameter("ricevimento");
    /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
    if (nuovoIndirizzo.equals("")) {
      nuovoIndirizzo = user.getIndirizzo();
    }
    if (nuovoNumeroDiTelefono.equals("")) {
      nuovoNumeroDiTelefono = user.getTelefono();
    }
    if (nuovoSito.equals("")) {
      nuovoSito = presidente.getLinkSito();
    }
    if (nuovoUfficio.equals("")) {
      nuovoUfficio = presidente.getUfficio();
    }
    if (nuovoRicevimento.equals("")) {
      nuovoRicevimento = presidente.getGiorniDiRicevimento();
    }
    controllaErrori(request, response);
    user.setIndirizzo(nuovoIndirizzo);
    user.setTelefono(nuovoNumeroDiTelefono);
    presidente.setLinkSito(nuovoSito);
    presidente.setUfficio(nuovoUfficio);
    presidente.setGiorniDiRicevimento(nuovoRicevimento);
    if (flag == true) {
      presidentedao.persist(presidente);
    }
  }

  /* Modifica i dati del tutor interno */

  private void modificaTutorInterno(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    TutorInterno tutorInterno = (TutorInterno) tutorinternodao.findById(TutorInterno.class, email);
    Utente user = tutorInterno.getUtente();
    nuovoIndirizzo = request.getParameter("indirizzo");
    nuovoSito = request.getParameter("sito");
    /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
    if (nuovoIndirizzo.equals("")) {
      nuovoIndirizzo = user.getIndirizzo();
    }
    if (nuovoNumeroDiTelefono.equals("")) {
      nuovoNumeroDiTelefono = user.getTelefono();
    }
    if (nuovoSito.equals("")) {
      nuovoSito = tutorInterno.getLinkSito();
    }
    controllaErrori(request, response);
    user.setIndirizzo(nuovoIndirizzo);
    user.setTelefono(nuovoNumeroDiTelefono);
    tutorInterno.setLinkSito(nuovoSito);
    if (flag == true) {
      tutorinternodao.persist(tutorInterno);
    }
  }

  /* Modifica i dati dello studente */

  private void modificaStudente(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Studente studente = (Studente) studenteDao.findById(Studente.class, email);
    Utente user = studente.getUtente();
    nuovoIndirizzo = request.getParameter("indirizzo");
    /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
    if (nuovoIndirizzo.equals("")) {
      nuovoIndirizzo = user.getIndirizzo();
    }
    if (nuovoNumeroDiTelefono.equals("")) {
      nuovoNumeroDiTelefono = user.getTelefono();
    }
    controllaErrori(request, response);
    user.setIndirizzo(nuovoIndirizzo);
    user.setTelefono(nuovoNumeroDiTelefono);
    if (flag == true) {
      studenteDao.persist(studente);
    }
  }

  /* Modifica i dati dell'utente Segreteria */

  private void modificaSegreteria(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Segreteria segreteria = (Segreteria) segreteriadao.findById(Segreteria.class, email);
    Utente user = segreteria.getUtente();
    nuovoIndirizzo = request.getParameter("indirizzo");
    nuovoRicevimento = request.getParameter("ricevimento");
    /* Se non è stato inserito alcun valore, viene preso quello già assegnato */
    if (nuovoIndirizzo.equals("")) {
      nuovoIndirizzo = user.getIndirizzo();
    }
    if (nuovoNumeroDiTelefono.equals("")) {
      nuovoNumeroDiTelefono = user.getTelefono();
    }
    if (nuovoRicevimento.equals("")) {
      nuovoRicevimento = segreteria.getGiorniDiRicevimento();
    }
    controllaErrori(request, response);
    user.setIndirizzo(nuovoIndirizzo);
    user.setTelefono(nuovoNumeroDiTelefono);
    segreteria.setGiorniDiRicevimento(nuovoRicevimento);
    if (flag == true) {
      segreteriadao.persist(segreteria);
    }
  }


  /* Controlli relativi al test plan */
  private void controllaErrori(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (nuovoIndirizzo.length() < 2 || nuovoIndirizzo.length() > 100) {
      flag = false;
      request.setAttribute("erroreModifica",
          "L'indirizzo deve essere compreso tra i 2 e i 100 caratteri");
      RequestDispatcher erroreModifica =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      erroreModifica.forward(request, response);
    } else if (nuovoNumeroDiTelefono.length() != 10) {
      flag = false;
      request.setAttribute("erroreModifica",
          "Il numero di telefono deve essere composto da esattamente 10 caratteri");
      RequestDispatcher erroreModifica =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      erroreModifica.forward(request, response);
    } else if (!isNumeric(nuovoNumeroDiTelefono)) {
      flag = false;
      request.setAttribute("erroreModifica",
          "Il numero di telefono deve contenere esclusivamente numeri");
      RequestDispatcher erroreModifica =
          request.getServletContext().getRequestDispatcher("/modificaProfilo.jsp");
      erroreModifica.forward(request, response);
    }
  }

  private static boolean isNumeric(String str) {
    try {
      long l = Long.parseLong(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
