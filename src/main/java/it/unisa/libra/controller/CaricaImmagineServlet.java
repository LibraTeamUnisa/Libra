package it.unisa.libra.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;

/**
 * Servlet implementation class CaricaImmagineServlet
 */
@WebServlet(name = "CaricaImmagineServlet", urlPatterns = "/caricaImmagine")
@MultipartConfig
public class CaricaImmagineServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IUtenteDao utenteDao;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CaricaImmagineServlet() {}

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getParameter("action") != null && request.getParameter("action").equals("carica")) {
      response.getWriter()
          .write(FileUtils.readBase64FromFile(FileUtils.PATH_IMG_PROFILO + "prova", "prova.png"));
    } else {
      String file = request.getParameter("file");
      if (FileUtils.saveBase64ToFile(FileUtils.PATH_IMG_PROFILO + "prova", "prova.png", file))
        response.getWriter().write("Salvataggio riuscito con successo");
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
}
