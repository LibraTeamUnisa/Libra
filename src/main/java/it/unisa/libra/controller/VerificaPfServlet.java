package it.unisa.libra.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;

/**
 * Servlet implementation class VerificaPfServlet. Questa servlet gestisce la
 * verifica di un progetto formativo da parte del tutor interno e del presidente
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
	public VerificaPfServlet() {
	}

	/**
	 * Questa servlet non fornisce alcun servizio tramite GET.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	/**
	 * Imposta lo stato del progetto formativo in rifiutato con eventuale
	 * motivazione.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("pf_id"));
		} catch (NumberFormatException e) {
			response.getWriter().println("Parse Integer error");
			return;
		}
		String ruolo = (String) request.getSession().getAttribute("utenteRuolo");
		String email = (String) request.getSession().getAttribute("utenteEmail");
		String motivazioneRifiuto = request.getParameter("motivazione");
		ProgettoFormativo pf = progettoformativoDao.findById(ProgettoFormativo.class, id);

		
		if (pf == null) {
			response.getWriter().println("Progetto Formativo inesistente.");
			return;
		}
		if (ruolo.equals("Presidente")) {
			if (pf.getStato() == 3) {// In attesa della firma del presidente
				pf.setStato(6);
				pf.setMotivazioneRifiuto(motivazioneRifiuto);
			} else {
				response.getWriter().println("Operazione non consentita.");
				return;
			}
		} else if (ruolo.equals("TutorInterno")) {
			if (pf.getTutorInterno().getUtenteEmail().equals(email) && pf.getStato() == 2) {// In attesa della firma del																			// tutor interno
				pf.setStato(6);
				pf.setMotivazioneRifiuto(motivazioneRifiuto);
			} else {
				response.getWriter().println("Operazione non consentita.");
				return;
			}
		} else {
			response.getWriter().println("Non hai l'autorizzazione necessaria.");
			return;
		}
		progettoformativoDao.persist(pf);
		response.getWriter().println("ok");
		return;
	}
}
