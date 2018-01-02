package it.unisa.libra.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

/**
 * Servlet implementation class CaricaImmagineServlet
 */
@WebServlet(name = "CaricaImmagineServlet", urlPatterns = "/caricaImmagine")
@MultipartConfig
public class CaricaImmagineServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private String email;
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
    HttpSession session = request.getSession();
    String email = (String) session.getAttribute("utenteEmail");
    Utente user = utenteDao.findById(Utente.class, email);
    Part filePart = request.getPart("proPic");
    if (!filePart.getSubmittedFileName().equals("")) {
      File file = new File("C:/Users/Michele/Desktop/Libra/target/Libra/assets/images/users/"
          + filePart.getSubmittedFileName());
      InputStream filestream = filePart.getInputStream();
      Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      user.setImgProfilo("assets/images/users/" + filePart.getSubmittedFileName());
      utenteDao.persist(user);
    } else {
    }
    response.sendRedirect("profilo.jsp");
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
