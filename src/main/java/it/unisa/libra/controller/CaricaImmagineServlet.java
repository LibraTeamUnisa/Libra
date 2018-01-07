package it.unisa.libra.controller;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



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

  /** Default constructor. */
  public CaricaImmagineServlet() {}

  /**
   * Questa servlet non fornisce alcun servizio tramite GET.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return;
  }

  /**
   * Gestisce il caricamento di un file in remoto.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    String email = (String) session.getAttribute("utenteEmail");
    Utente user = utenteDao.findById(Utente.class, email);

    Part filePart = request.getPart("proPic");
    String ext = filePart.getContentType().substring(6);

    // return number of milliseconds since January 1, 1970, 00:00:00 GMT
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    if (!filePart.getSubmittedFileName().equals("")) {
      String p = System.getProperty("user.dir");
      File file = new File(p + PATH2 + timestamp.getTime() + "." + ext);
      InputStream filestream = filePart.getInputStream();
      Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      user.setImgProfilo(p + PATH2 + timestamp.getTime() + "." + ext);
      utenteDao.persist(user);
      response.getWriter().write(IMG_SUCCESS);
      response.sendRedirect("profilo.jsp");
    } else {
      response.getWriter().write(IMG_ERROR);
      response.sendRedirect("errore.jsp");
    }

  }

  /**
   * Path necessaria per individuare la cartella dove caricare la nostra immagine.
   */
  // private static final String PATH = "../../Libra/data/Img/";
  /**
   * Path da salvare nel db per recuperare l'immagine salvata.
   */
  private static final String PATH2 = "/../../Libra/data/Img/";
  /**
   * Messaggio restituito nel caso in cui l'operazione fallisce.
   */
  private static final String IMG_ERROR = "Caricamento fallito!";
  /**
   * Messaggio restituito nel caso in cui l'operazione è stata completata con successo.
   */
  private static final String IMG_SUCCESS = "Caricamento riuscito!";
}


