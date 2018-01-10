package it.unisa.libra.controller;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.CheckUtils;
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
    File path = new File(FileUtils.BASE_PATH + FileUtils.PATH_IMG_PROFILO);
    if (!path.exists()) {
      path.mkdirs();
    }
    String action = request.getParameter("action"); // serve per mostrare
    String file = request.getParameter("file"); // serve per upload
    if (action != null && "mostra".equals(action)) {
      // mostra
      String email = request.getParameter("email");
      if (CheckUtils.checkEmptiness(email)) {
        // ok
        response.getWriter()
            .write(FileUtils.readBase64FromFile(FileUtils.PATH_IMG_PROFILO, email + ".png"));
      } else {
        // errore
        response.getWriter().write("errore");
      }
    } else if (file != null) {
      // upload
      String email = (String) request.getSession().getAttribute("utenteEmail");
      Utente user = utenteDao.findById(Utente.class, email);
      if (FileUtils.saveBase64ToFile(FileUtils.PATH_IMG_PROFILO, email + ".png", file)) {
        response.getWriter().write("Salvataggio riuscito con successo");
      }
      user.setImgProfilo(email + ".png");
      utenteDao.persist(user);
    } else {
      // errore
      response.getWriter().write("errore");
    }
  }

  public void setUtenteDao(IUtenteDao utenteDao) {
    this.utenteDao = utenteDao;
  }

  /*
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
