package it.unisa.libra.controller;

import com.mysql.jdbc.StringUtils;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.JspPagesIndex;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

  /**
   * doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (validAction(request, response)) {
      if (request.getParameter(Actions.ACTION).equals(Actions.LOGOUT)) {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + JspPagesIndex.HOME);
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Azione non valida!");
      response.getWriter().flush();
    }
  }


  /**
   * Gestisce la richiesta di un utente di effettuare il login se vengono inserite le credenziali
   * corrette, altrimenti rendirizza alla schermata di login mostrando un errore.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    if ((request.getParameter("email") != null) && (request.getParameter("password") != null)) {
      String mail = request.getParameter("email");
      String pwd = request.getParameter("password");
      Utente utente = utenteDao.getUtente(mail, pwd);
      if (utente != null) {
        session.setAttribute("utenteEmail", utente.getEmail());
        session.setAttribute("utenteRuolo", utente.getGruppo().getRuolo());

        // reindirizza alla dashboard.jsp corretta in base alla tipologia di utente.
        String dashboard = request.getContextPath()
            + "/dashboard".concat(utente.getGruppo().getRuolo()).concat(".jsp");
        response.getWriter().write(dashboard);

      } else {
        response.getWriter().write("false");
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("false");
    }
  }

  private boolean validAction(HttpServletRequest request, HttpServletResponse response) {
    return (request != null && !StringUtils.isNullOrEmpty(request.getParameter(Actions.ACTION)));
  }
}
