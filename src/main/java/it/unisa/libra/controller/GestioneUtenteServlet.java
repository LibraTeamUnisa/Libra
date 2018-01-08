package it.unisa.libra.controller;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestioneUtenteServlet. Controller class che
 * gestisce le operazioni di aggiunta, modifica e rimozione di un Utente nel
 * Sistema.
 * 
 * @author Luca Izzo
 * @version 1.1
 */
@WebServlet("/GestioneUtenteServlet")
public class GestioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** gestore della persistenza per l'entita' Utente. **/
	@EJB
	private IUtenteDao utenteDao;

	/** gestore della persistenza per l'entita' Presidente. **/
	@EJB
	private IPresidenteDao presidenteDao;

	/** gestore della persistenza per l'entita' TutorInterno. **/
	@EJB
	private ITutorInternoDao tutorInternoDao;

	/** gestore della persistenza per l'entita' Azienda. **/
	@EJB
	private IAziendaDao aziendaDao;

	/** gestore della persistenza per l'entita' Gruppo. **/
	@EJB
	private IGruppoDao gruppoDao;

	/** Default constructor. */
	public GestioneUtenteServlet() {
	}

	/**
	 * Questa servlet non fornisce servizi tramite il metodo doGet.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		return;
	}

	/**
	 * Gestisce l'aggiunta, la modifica e la rimozione di un Utente.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = new Utente();
		Presidente pres = new Presidente();
		TutorInterno tut = new TutorInterno();
		Azienda azienda = new Azienda();
		Gruppo gruppo = new Gruppo();
		try {
			String ruolo = request.getParameter("ruolo");
			gruppo.setRuolo(ruolo);
			utente.setGruppo(gruppo);
			utente.setImgProfilo("  ");
			utente.setEmail(request.getParameter("email"));
			utente.setPassword(request.getParameter("password"));
			utente.setIndirizzo(request.getParameter("indirizzo"));
			utente.setTelefono(request.getParameter("telefono"));
			if (ruolo.equals("Presidente")) {
				String data = request.getParameter("bday");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date result = df.parse(data);
				pres.setUtenteEmail(request.getParameter("email"));
				pres.setDataDiNascita(result);
				pres.setLinkSito(request.getParameter("webSite"));
				pres.setNome(request.getParameter("nome"));
				pres.setCognome(request.getParameter("cognome"));
				pres.setGiorniDiRicevimento(request.getParameter("giornoDiRicevimento"));
				pres.setUfficio(request.getParameter("ufficio"));
				utenteDao.persist(utente);
				presidenteDao.persist(pres);
			} else if (ruolo.equals("TutorInterno")) {
				String data = request.getParameter("bday");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date result = df.parse(data);
				tut.setUtenteEmail(request.getParameter("email"));
				tut.setDataDiNascita(result);
				tut.setLinkSito(request.getParameter("website"));
				tut.setNome(request.getParameter("Nome"));
				tut.setCognome(request.getParameter("cognome"));
				utenteDao.persist(utente);
				tutorInternoDao.persist(tut);
			} else if (ruolo.equals("Azienda")) {
				azienda.setUtenteEmail(request.getParameter("email"));
				azienda.setNome(request.getParameter("Nome"));
				azienda.setPartitaIVA(request.getParameter("partitaIva"));
				azienda.setSede(request.getParameter("website"));
				utenteDao.persist(utente);
				aziendaDao.persist(azienda);
			}
		} catch (Exception e) {
			response.getWriter().write("L'aggiunta utente è fallita!");
		}
	}
}