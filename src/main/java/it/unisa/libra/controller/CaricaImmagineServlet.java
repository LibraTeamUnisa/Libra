package it.unisa.libra.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
  private IUtenteDao uenteDao;

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
    email = (String) session.getAttribute("emailUtente");
    Part filePart = request.getPart("proPic");
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
    InputStream fileContent = filePart.getInputStream();    
    String nome = email + ".png";   
    File file = new File("D:/" + nome);
    PrintStream ps = new PrintStream(file);  
    Scanner in = new Scanner(fileContent);
    while(in.hasNext())
    {
        ps.println(in.next());
    }
    in.close();
    ps.close();
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
