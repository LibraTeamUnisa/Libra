package it.unisa.libra.controller;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Consente di effettuare una richiesta di progetto formativo ad un utente registrato con ruolo
 * studente.
 */
@WebServlet(name = "richiediPfServlet", urlPatterns = "/richiediPfServlet")
public class RichiediPfServlet extends HttpServlet {

  @EJB
  private IUtenteDao utenteDao;
  @EJB
  private IAziendaDao aziendaDao;
  @EJB
  private IProgettoFormativoDao pfDao;
  @EJB
  private IStudenteDao studenteDao;

  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public RichiediPfServlet() {}

  /**
   * Questa servlet non fornisce alcun servizio tramite GET.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return;
  }

  /**
   * Gestisce le richieste di progetto formativo degli studenti.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();

    if (session.getAttribute("utenteEmail") != null) {
      String email = (String) session.getAttribute("utenteEmail");
      String aziendaEmail = request.getParameter("aziendaName");
      Utente utente = utenteDao.findById(Utente.class, email);

      if (utente != null) {
        Azienda azienda = aziendaDao.findByName(aziendaEmail);
        Studente studente = studenteDao.findById(Studente.class, email);

        String defaultString = "/";
        int deafuletInt = 0;

        if (azienda != null && studente != null) {
          ProgettoFormativo pf = new ProgettoFormativo();
          pf.setAzienda(azienda);
          pf.setStudente(studente);

          // Valori di default per i campi not null
          pf.setTutorInterno(null);
          pf.setAmbito(defaultString);
          pf.setDocumento(defaultString);
          pf.setStato(deafuletInt);
          pf.setPeriodoReport(deafuletInt);

          pfDao.persist(pf);
          response.getWriter().write(ISCRIZIONE_SUCCESS_MSG);
          
        } else if (studente == null) {
          response.getWriter().write(STUDENTE_NOT_FOUND_MSG);
        } else {
          response.getWriter().write(AZIENDA_NOT_FOUND_MSG);
        }

      } else {
        response.getWriter().write(USER_ERROR_MSG);
      }

    } else {
      response.getWriter().write(SESSION_ERROR_MSG);
    }
  }

  /**
   * Questo metodo imposta il DAO della servlet.
   * 
   * @param dao Il dao che si occupa della gestione della persistenza del progetto formativo
   */
  public void setProgettoFormativoDao(IProgettoFormativoDao dao) {
    this.pfDao = dao;
  }

  /**
   * Questo metodo imposta il DAO della servlet.
   * 
   * @param dao Il dao che si occupa della ricerca dell'utente
   */
  public void setUtenteDao(IUtenteDao dao) {
    this.utenteDao = dao;
  }

  /**
   * Questo metodo imposta il DAO della servlet.
   * 
   * @param dao Il dao che si occupa della ricerca dello studente
   */
  public void setStudenteDao(IStudenteDao dao) {
    this.studenteDao = dao;
  }

  /**
   * Questo metodo imposta il DAO della servlet.
   * 
   * @param dao Il dao che si occupa della ricerca dell'azienda
   */
  public void setAziendaDao(IAziendaDao dao) {
    this.aziendaDao = dao;
  }

  /**
   * Messaggio restituito nel caso in cui il parametro relativo all'email dello studente non viene
   * riconosciuto.
   */
  private static final String SESSION_ERROR_MSG =
      "Impossibile recuperare i parametri dalla sessione!";
  /**
   * Messaggio restituito nel caso in cui il parametro relativo all'email dello studente non viene
   * riconosciuto.
   */
  private static final String USER_ERROR_MSG = "Utente non trovato!";
  /**
   * Messaggio restituito nel caso in cui l'operazione è stata completata con successo.
   */
  private static final String ISCRIZIONE_SUCCESS_MSG = "Iscrizione effettuata!";
  /**
   * Messaggio restituito nel caso in cui la ricerca dello studente non ottiene risultato.
   */
  private static final String STUDENTE_NOT_FOUND_MSG = "Studente non trovato!";
  /**
   * Messaggio restituito nel caso in cui la ricerca dell'azienda non ottiene risultato.
   */
  private static final String AZIENDA_NOT_FOUND_MSG = "Azienda non trovata!";
}
