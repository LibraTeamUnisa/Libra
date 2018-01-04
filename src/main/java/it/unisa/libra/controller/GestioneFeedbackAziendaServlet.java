package it.unisa.libra.controller;

import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IDomandaDao;
import it.unisa.libra.model.dao.IFeedbackDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;

import java.io.IOException; 

import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "GestioneFeedbackAziendaServlet", urlPatterns = "gestioneFeedbackAzienda")
public class GestioneFeedbackAziendaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Inject
  private IDomandaDao domandaDao;
  @Inject
  private IFeedbackDao feedbackDao;
  @Inject
  private IProgettoFormativoDao pfDao;

  /** Default constructor. */
  public GestioneFeedbackAziendaServlet() {}

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Domanda> domande = domandaDao.findByType("Studente");
    String idPf = request.getParameter("idProgettoFormativo");
    for (Domanda d : domande) {
      persistFeedback(request, d, Integer.parseInt(idPf));
    }
    RequestDispatcher dispatcher =
        request.getServletContext().getRequestDispatcher("/questionarioValutaAzienda.jsp");
    dispatcher.forward(request, response);
  }



  /**
   * Questo metodo ha la funzione di persistere un oggetto Feedback. In Particolare controlla se la
   * domanda è di tipo valutativa o se riguarda una descrizione in merito all'esperienza.
   * 
   * @param: HttpSerlvletRequest request: oggetto request della serlvet.
   * @param: Domanda domanda: oggetto contenente l'id necessario ad associare la valutazione alla
   *         relativa domanda.
   * @param: int idPF: intero rappresentante l'id del progetto formativo che si sta valutando.
   */
  public void persistFeedback(HttpServletRequest request, Domanda domanda, int idPf) {
    Feedback f = new Feedback();
    FeedbackPK feedbackPk = new FeedbackPK();
    feedbackPk.setDomandaID(domanda.getId());
    feedbackPk.setProgettoFormativoID(idPf);
    f.setId(feedbackPk);
    String value = "";
    if (domanda.getTesto().equals("Note")) {
      value = request.getParameter("note");
    } else {
      value = request.getParameter("" + domanda.getId());
    }
    f.setValutazione(value);
    f.setProgettoFormativo(pfDao.findById(ProgettoFormativo.class, idPf));
    feedbackDao.persist(f);
  }


  public void setDomandaDao(IDomandaDao dao) {
    this.domandaDao = dao;
  }

  public void setProgettoFormativoDao(IProgettoFormativoDao dao) {
    this.pfDao = dao;
  }

  public void setFeedbackDao(IFeedbackDao dao) {
    this.feedbackDao = dao;
  }

}
