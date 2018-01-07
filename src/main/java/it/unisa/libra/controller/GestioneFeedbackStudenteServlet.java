package it.unisa.libra.controller;

import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IFeedbackDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "FeedbackStudenteServlet", urlPatterns = "/inviaFeedbackStudente")
public class GestioneFeedbackStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IFeedbackDao feedbackDao;


  public GestioneFeedbackStudenteServlet() {}

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // doGet(request, response);
    response.setContentType("text/html");

    int idProgettoFormativo = Integer.parseInt(request.getParameter("idProgettoFormativo"));

    String val1 = request.getParameter("val1");
    setFeedback(val1, 1, idProgettoFormativo);
    String val2 = request.getParameter("val2");
    setFeedback(val2, 2, idProgettoFormativo);
    String val3 = request.getParameter("val3");
    setFeedback(val3, 3, idProgettoFormativo);
    String val4 = request.getParameter("val4");
    setFeedback(val4, 4, idProgettoFormativo);
    String val5 = request.getParameter("val5");
    setFeedback(val5, 5, idProgettoFormativo);
    String val6 = request.getParameter("val6");
    setFeedback(val6, 6, idProgettoFormativo);
    String val7 = request.getParameter("val7");
    setFeedback(val7, 7, idProgettoFormativo);
    String val8 = request.getParameter("val8");
    setFeedback(val8, 8, idProgettoFormativo);
    String val9 = request.getParameter("val9");
    setFeedback(val9, 9, idProgettoFormativo);
    String val10 = request.getParameter("val10");
    setFeedback(val10, 10, idProgettoFormativo);
    String val11 = request.getParameter("val11");
    setFeedback(val11, 11, idProgettoFormativo);
    String valNote = request.getParameter("valNote");
    setFeedback(valNote, 12, idProgettoFormativo);

    response.getWriter().write("salvato");
  }

  /**
   * Metodo che salva il feedback nel db.
   * 
   * @param val valore della valutazione
   * @param idDomanda id della domanda del questionario
   * @param idPf id del progetto formativo trattato
   */
  public void setFeedback(String val, int idDomanda, int idPf) {
    Feedback f = new Feedback();
    FeedbackPK fpk = new FeedbackPK();
    fpk.setDomandaID(idDomanda);
    fpk.setProgettoFormativoID(idPf);
    f.setId(fpk);
    f.setValutazione(val);
    feedbackDao.persist(f);
  }

  public void setFeedbackDao(IFeedbackDao feedbackDao) {
    this.feedbackDao = feedbackDao;
  }
}
