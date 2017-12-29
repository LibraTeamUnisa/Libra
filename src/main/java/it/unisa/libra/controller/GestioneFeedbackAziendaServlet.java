package it.unisa.libra.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IDomandaDao;
import it.unisa.libra.model.dao.IFeedbackDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;

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
	public GestioneFeedbackAziendaServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Domanda> domande = domandaDao.findByType("Studente");
		String idPF= request.getParameter("idProgettoFormativo");
		for (Domanda d : domande) {
			String input= null;
			if(d.getTesto().equals("Note")) {
				input = request.getParameter("note");
				persistFeedback(input, d.getId(), Integer.parseInt(idPF));
				continue;
			}
			
			input = request.getParameter(""+d.getId());
			persistFeedback(input, d.getId(), Integer.parseInt(idPF));
			
			
			
		}

		
		request.setAttribute("feedback_persisted", "persisted");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/questionarioValutaAzienda.jsp");
		dispatcher.forward(request, response);

	}

	private void persistFeedback(String value, int idDomanda, int idPF) {
		Feedback f = new Feedback();
		FeedbackPK feedback_pk = new FeedbackPK();
		feedback_pk.setDomandaID(idDomanda);
		feedback_pk.setProgettoFormativoID(idPF);
		f.setId(feedback_pk);
		f.setValutazione(value);
		f.setProgettoFormativo(pfDao.findById(ProgettoFormativo.class, idPF));
		feedbackDao.persist(f);
	}

}