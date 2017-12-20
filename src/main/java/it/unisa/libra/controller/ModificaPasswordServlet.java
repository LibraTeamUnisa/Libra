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

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "ModificaPasswordServlet", urlPatterns = "/modificaPassword")
//il pattern mi serve per chiamarla dal js
public class ModificaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IUtenteDao utenteDao;

	/** Default constructor. */
	public ModificaPasswordServlet() {}
	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.getWriter().write("true");
		//      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		//  response.getWriter().append("Served at: ").append(provaPassword2).append(" ").append(provaPassword3);
		//}
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.getSession().setAttribute("utenteEmail", "alfredo@unisa.it");

		String email=(String) request.getSession().getAttribute("utenteEmail");
		
		// Utente ruolo=(Utente) request.getSession(true).getAttribute("utenteRuolo");NON MI SERVE PIù
		Utente utente =  utenteDao.findById(Utente.class, email);
		String action = request.getParameter("action");
		if(action == null) {	
			response.getWriter().write("errore");
		}
		else if(action.equals("verifica"))
		{
			String pass= request.getParameter("password");
			Boolean passCorretta = controllaPassword(utente, pass);
			if(passCorretta){ //le due password corrispondono quindi posso mostrare i campi per inserire la nuova password
				response.getWriter().write("true");
			}else if(!passCorretta) {
				response.getWriter().write("false"); //la pass non coincide
			}
		}else if(action.equals("cambia")) {
			String pw1 = request.getParameter("pwn1");
			String pw2 = request.getParameter("pwn2");
				if(aggiornaPassword(utente,pw1,pw2)) {
						response.getWriter().write("finito");
					}else {
						response.getWriter().write("errore");
				}
			}
		}


	protected Boolean controllaPassword(Utente utente, String pass) {
		String pwU=utente.getPassword();
		if(pwU.equals(pass)) {
			return true;
		}else {
			return false;
		}  
	}


	public void setUtenteDao(IUtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}
	

	protected Boolean aggiornaPassword(Utente utente, String pw1, String pw2) {

		if(pw1.equals(pw2)) {
			utente.setPassword(pw1);
			utenteDao.merge(utente);
			return true;
		}else {
			return false;
		}
	}
}
