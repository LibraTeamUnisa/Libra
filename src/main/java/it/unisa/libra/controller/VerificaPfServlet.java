package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerificaPfServlet. Questa servlet gestisce la verifica di un
 * progetto formativo da parte del tutor interno e del presidente
 * 
 * @author Vincenzo Caputo
 * @version 1.0
 */
@WebServlet(name = "VerificaPfServlet", urlPatterns = "/verificaProgettoFormativo")
public class VerificaPfServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Gestisce la persistenza dell'oggetto Progetto Formativo.
   */
  @EJB
  private IProgettoFormativoDao progettoformativoDao;

  /** Default constructor. */
  public VerificaPfServlet() {}

  /**
   * Questa servlet non fornisce alcun servizio tramite GET.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return;
  }

  /**
   * Imposta lo stato del progetto formativo in rifiutato con eventuale motivazione.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = 0;
    try {
      id = Integer.parseInt(request.getParameter("pf_id"));
    } catch (NumberFormatException e) {
      response.getWriter().write(PARSE_ERROR_MSG);
      return;
    }
    String ruolo = (String) request.getSession().getAttribute("utenteRuolo");
    String email = (String) request.getSession().getAttribute("utenteEmail");
    String motivazioneRifiuto = request.getParameter("motivazione");
    ProgettoFormativo pf = progettoformativoDao.findById(ProgettoFormativo.class, id);

    // Se il progetto formativo non è presente
    if (pf == null) {
      response.getWriter().write(PF_NOTFOUND_ERROR_MSG);
      return;
    }
    if (ruolo.equals("Presidente")) {
      // Il progetto formativo deve essere in attesa della firma del presidente per
      // poter essere rifiutato
      if (pf.getStato() == 3) {
        pf.setStato(6);
        pf.setMotivazioneRifiuto(motivazioneRifiuto);
      } else {
        response.getWriter().write(STATO_ERROR_MSG);
        return;
      }
    } else if (ruolo.equals("TutorInterno")) {
      // Il tutor interno deve essere associato al progetto formativo
      if (pf.getTutorInterno().getUtenteEmail().equals(email)) {
        // Il progetto formativo deve essere in attesa della firma del tutor interno
        if (pf.getStato() == 2) {
          pf.setStato(6);
          pf.setMotivazioneRifiuto(motivazioneRifiuto);
        } else {
          response.getWriter().write(STATO_ERROR_MSG);
          return;
        }
      } else {
        response.getWriter().write(TUTOR_ERROR_MSG);
        return;
      }
    } else {
      response.getWriter().write(RUOLO_ERROR_MSG);
      return;
    }
    progettoformativoDao.persist(pf);
    response.getWriter().write(SUCCESS_MSG);
    return;
  }

  /**
   * Questo metodo imposta il DAO della servlet.
   * 
   * @param dao Il dao che si occupa della gestione della persistenza del progetto formativo
   */
  public void setProgettoFormativoDao(IProgettoFormativoDao dao) {
    this.progettoformativoDao = dao;
  }

  /**
   * Messaggio restituito nel caso in cui l'operazione è stata completata con successo.
   */
  private static final String SUCCESS_MSG = "ok";
  /**
   * Messaggio restituito nel caso in cui lo stato del progetto formativo non è quello atteso.
   */
  private static final String STATO_ERROR_MSG = "Operazione non consentita";
  /**
   * Messaggio restituito nel caso in cui il l'utente non è un presidente o un tutor interno.
   */
  private static final String RUOLO_ERROR_MSG =
      "Non hai l'autorizzazione necessaria per effettuare questa operazione";
  /**
   * Messaggio restituito nel caso in cui il tutor interno non è associato al progetto formativo da
   * rifiutare.
   */
  private static final String TUTOR_ERROR_MSG = "Non sei associato a questo progetto formativo";
  /**
   * Messaggio restituito nel caso in cui il progetto formativo non viene trovato nel database.
   */
  private static final String PF_NOTFOUND_ERROR_MSG = "Progetto formativo non trovato";
  /**
   * Messaggio restituito nel caso in cui il parametro relativo all'id del progetto formativo non è
   * un intero.
   */
  private static final String PARSE_ERROR_MSG = "Parametro errato";
}
