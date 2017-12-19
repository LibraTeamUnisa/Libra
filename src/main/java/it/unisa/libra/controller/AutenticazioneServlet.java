package it.unisa.libra.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.StringUtils;
import it.unisa.libra.util.Actions;
import it.unisa.libra.util.JspPagesIndex;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "AutenticazioneServlet", urlPatterns = "/autenticazione")
public class AutenticazioneServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public AutenticazioneServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
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

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  private boolean validAction(HttpServletRequest request, HttpServletResponse response) {
    return (request != null && !StringUtils.isNullOrEmpty(request.getParameter(Actions.ACTION)));
  }
}
