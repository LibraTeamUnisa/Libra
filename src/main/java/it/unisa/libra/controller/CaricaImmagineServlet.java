package it.unisa.libra.controller;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/*
 * Servlet implementation class CaricaImmagineServlet
 * 
 * 
 * /** Consente di effettuare il caricamento dell'immagine.
 */
@WebServlet(name = "CaricaImmagineServlet", urlPatterns = "/caricaImmagine")
@MultipartConfig
public class CaricaImmagineServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IUtenteDao utenteDao;

  /*
   * @see HttpServlet#HttpServlet()
   */
  public CaricaImmagineServlet() {}

  /*
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    String email = (String) session.getAttribute("utenteEmail");
    Utente user = utenteDao.findById(Utente.class, email);
    File path = new File(FileUtils.BASE_PATH + FileUtils.PATH_IMG_PROFILO);
    if (!path.exists()) {
      path.mkdirs();
    }
    if (request.getParameter("action") != null && request.getParameter("action").equals("carica")) {
      response.getWriter()
          .write(FileUtils.readBase64FromFile(FileUtils.PATH_IMG_PROFILO, email + ".png"));
    } else {
      String file = request.getParameter("file");
      if (FileUtils.saveBase64ToFile(FileUtils.PATH_IMG_PROFILO, email + ".png", file)) {
        response.getWriter().write("Salvataggio riuscito con successo");
      }
      user.setImgProfilo(FileUtils.PATH_IMG_PROFILO + email + ".png");
      utenteDao.persist(user);
    }
  }

  /*
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
