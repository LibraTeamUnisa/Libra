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



/**
 * Consente di effettuare le operazioni di login e logout per un utente registrato.
 * 
 */
@WebServlet(name = "AutenticazioneServlet", urlPatterns = "/autenticazione")
public class AutenticazioneServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IUtenteDao utenteDao;

  /** Default constructor. */
  public AutenticazioneServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }


  /**
   * Gestisce la richiesta di un utente di effettuare il login se vengono inserite le credenziali
   * corrette, altrimenti rendirizza alla schermata di login mostrando un errore. Gestisce la
   * richiesta di logout di un utente.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    if ((request.getParameter("email") != null) && (request.getParameter("password") != null)) {
      String mail = request.getParameter("email");
      String pwd = request.getParameter("password");
      Utente utente = utenteDao.getUtente(mail, pwd);
      if (utente != null) {
        session.setAttribute("utenteMail", utente.getEmail());
        session.setAttribute("utenteRuolo", utente.getGruppo().getRuolo());

        // reindirizza alla dashboard.jsp corretta in base alla tipologia di utente.
        String dashboard = "/Libra/dashboard".concat(utente.getGruppo().getRuolo()).concat(".jsp");
        response.getWriter().write(dashboard);

      } else {
        response.getWriter().write("false");
      }


      // di seguito effettua il logout
    } else {
      session.invalidate();
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
      dispatcher.forward(request, response);
    }
  }
}
