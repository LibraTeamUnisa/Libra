package it.unisa.libra.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Permesso;
import it.unisa.libra.model.dao.IPermessoDao;

/**
 * Consente di modificare i permessi di visualizzazione feedback per gli uutenti da parte del
 * personale di segreteria
 */
@WebServlet(name = "PermessiServlet", urlPatterns = "/permessi")
public class PermessiServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IPermessoDao permessoDao;

  private List<Permesso> listPerm;
  private Permesso ricevuti, noFeedback, conFirma, anonimi;

  /** Default constructor. */
  public PermessiServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * Gestisce la richiesta di modifica dei permessi di visualizzazione feedback da parte del
   * personale di segreteria salvandole nel database e mostrando un messaggio di successo,
   * altrimenti mostra un messaggio di errore.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("utenteRuolo") != null
        && session.getAttribute("utenteRuolo").equals("Segreteria")) {

      listPerm = permessoDao.findAll(Permesso.class);

      for (Permesso p : listPerm) {
        if (p.getTipo().equals("conFirma")) {
          conFirma = p;
          continue;
        } else if (p.getTipo().equals("anonimi")) {
          anonimi = p;
          continue;
        } else if (p.getTipo().equals("noFeedback")) {
          noFeedback = p;
          continue;
        } else if (p.getTipo().equals("ricevuti")) {
          ricevuti = p;
        }
      }

      boolean b = true;

      b = b && setPermessiRicevuti(request, response, "Studente");
      b = b && setPermessiRicevuti(request, response, "TutorInterno");
      b = b && setPermessiRicevuti(request, response, "Presidente");
      b = b && setPermessiRicevuti(request, response, "Segreteria");
      permessoDao.persist(ricevuti);

      b = b && setPermessiPrivacy(request, response, "Studente");
      b = b && setPermessiPrivacy(request, response, "Azienda");
      b = b && setPermessiPrivacy(request, response, "TutorInterno");
      b = b && setPermessiPrivacy(request, response, "Presidente");
      b = b && setPermessiPrivacy(request, response, "Segreteria");
      permessoDao.persist(noFeedback);
      permessoDao.persist(conFirma);
      permessoDao.persist(anonimi);

      if (b) {
        response.getWriter().write("true");
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("errore");
      }

    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("non consentito");
    }
  }

  /**
   * Gestisce il salvataggio dei permessi di visualizzazione dei feedback con firma, anonimi o non
   * disponibili.
   * 
   * @return true se i permessi sono stati salvati.
   */
  private boolean setPermessiPrivacy(HttpServletRequest request, HttpServletResponse response,
      String utente) {
    Gruppo g = new Gruppo();

    g.setRuolo(utente);
    if (request.getParameter(("radio").concat(utente)).equals("conFirma".concat(utente))) {
      if (!conFirma.getGruppi().contains(g)) {
        conFirma.getGruppi().add(g);
        noFeedback.getGruppi().remove(g);
        anonimi.getGruppi().remove(g);
      }
    } else if (request.getParameter(("radio").concat(utente)).equals("anonimi".concat(utente))) {
      if (!anonimi.getGruppi().contains(g)) {
        anonimi.getGruppi().add(g);
        noFeedback.getGruppi().remove(g);
        conFirma.getGruppi().remove(g);
      }
    } else if (request.getParameter(("radio").concat(utente)).equals("noFeedback".concat(utente))) {
      if (!noFeedback.getGruppi().contains(g)) {
        noFeedback.getGruppi().add(g);
        anonimi.getGruppi().remove(g);
        conFirma.getGruppi().remove(g);
      }
    } else {
      return false;
    }
    return true;
  }

  /**
   * Gestisce il salvataggio dei permessi di visualizzazione dei feedback ricevuti.
   * 
   * @return true se i permessi sono stati salvati.
   */
  private boolean setPermessiRicevuti(HttpServletRequest request, HttpServletResponse response,
      String utente) {
    Gruppo g = new Gruppo();

    g.setRuolo(utente);
    if (request.getParameter(("checkbox").concat(utente)).equals("true")) {
      if (!ricevuti.getGruppi().contains(g)) {
        ricevuti.getGruppi().add(g);
      }
    } else if (request.getParameter(("checkbox").concat(utente)).equals("false")) {
      ricevuti.getGruppi().remove(g);
    } else {
      return false;
    }
    return true;
  }
}
