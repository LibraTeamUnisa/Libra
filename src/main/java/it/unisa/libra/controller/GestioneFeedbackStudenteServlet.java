package it.unisa.libra.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IFeedbackDao;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "FeedbackStudenteServlet", urlPatterns = "/inviaFeedbackStudente")
public class GestioneFeedbackStudenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IFeedbackDao feedbackDao;


  /** Default constructor. */
  public GestioneFeedbackStudenteServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // doGet(request, response);
    response.setContentType("text/html");

    String val1 = request.getParameter("val1");
    String val2 = request.getParameter("val2");
    String val3 = request.getParameter("val3");
    String val4 = request.getParameter("val4");
    String val5 = request.getParameter("val5");
    String val6 = request.getParameter("val6");
    String val7 = request.getParameter("val7");
    String val8 = request.getParameter("val8");
    String val9 = request.getParameter("val9");
    String val10 = request.getParameter("val10");
    String val11 = request.getParameter("val11");
    String valNote = request.getParameter("valNote");
    int idProgettoFormativo = Integer.parseInt(request.getParameter("idProgettoFormativo"));

    setFeedback(val1, 1, idProgettoFormativo);
    setFeedback(val2, 2, idProgettoFormativo);
    setFeedback(val3, 3, idProgettoFormativo);
    setFeedback(val4, 4, idProgettoFormativo);
    setFeedback(val5, 5, idProgettoFormativo);
    setFeedback(val6, 6, idProgettoFormativo);
    setFeedback(val7, 7, idProgettoFormativo);
    setFeedback(val8, 8, idProgettoFormativo);
    setFeedback(val9, 9, idProgettoFormativo);
    setFeedback(val10, 10, idProgettoFormativo);
    setFeedback(val11, 11, idProgettoFormativo);
    setFeedback(valNote, 12, idProgettoFormativo);

    response.getWriter().write("salvato");
  }

  public void setFeedback(String val, int idDomanda, int idPF) {
    Feedback f = new Feedback();
    FeedbackPK fpk = new FeedbackPK();
    fpk.setDomandaID(idDomanda);
    fpk.setProgettoFormativoID(idPF);
    f.setId(fpk);
    f.setValutazione(val);
    feedbackDao.persist(f);
  }
}
