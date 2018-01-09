package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "CaricaPpfServlet", urlPatterns = "/carica")
@MultipartConfig

public class CaricaPpfServlet extends HttpServlet {

  @EJB 
  private IProgettoFormativoDao propostaDao;
  
  @EJB 
  private ITutorInternoDao tutorInternoDao;
  
  @EJB
  private IUtenteDao utenteDao;
  
  List<ProgettoFormativo> listaProposte = new ArrayList<ProgettoFormativo>();
  ProgettoFormativo proposta = new ProgettoFormativo();
  
  private static final long serialVersionUID = 1L;

  public CaricaPpfServlet() {}

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
	  
    String email = (String)request.getSession().getAttribute("utenteEmail");
    String ruolo = (String)request.getSession().getAttribute("utenteRuolo");
    Utente user = utenteDao.findById(Utente.class, email);
    File path = new File(FileUtils.BASE_PATH + FileUtils.PATH_PDF_PROGETTOF);
    
    if(!path.exists()) {
    	path.mkdirs();
    }
    
    /* sezione servlet eseguita solo se l'utente che ha inviato la proposta 
     * possiede il ruolo di Azienda */
    if (ruolo.contains("Azienda")) {
      String p = System.getProperty("user.dir");
      
      
      /* copio il contenuto del file caricato in un nuovo file */
      //InputStream filestream = filePart.getInputStream();
      //Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

      String note = (String)request.getParameter("note");
      String ambito = (String)request.getParameter("ambito");
      String ambitoControl = (String)request.getParameter("tutorEsterno");
      
      /* verifico che l'ambito della proposta corrisponda all'ambito del tutor esterno */
      if ((ambito != null) && (ambitoControl != null)) {
        if (!ambito.contains(ambitoControl)) {
          PrintWriter out = response.getWriter();
          out.println("<script type=\"text/javascript\">");
          out.println("alert('Il campo ambito non è corretto');");
          out.println("location='caricaPpf.jsp';");
          out.println("</script>");
          return;
        }
      }
      int idProposta = Integer.parseInt(request.getParameter("studente")); 

      proposta = propostaDao.findById(ProgettoFormativo.class, idProposta);

      proposta.setAmbito(ambito);
      proposta.setNote(note);
      //proposta.setDocumento(file.getPath());
      proposta.setStato(1);

      Date today = new Date();
      proposta.setDataInvio(today);

      /* memorizzo la proposta nel database */
      propostaDao.persist(proposta); 
      
      response.getWriter().write("ok");

      /* invio un messaggio di conferma alla jsp */
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('La proposta è stata caricata ed inviata');");
      out.println("location='caricaPpf.jsp';");
      out.println("</script>");
    }

    if (ruolo.contains("Studente")) {    
      /* verifico che la proposta individuata sia conforme per essere inviata */
      String filePath = null;
      listaProposte = propostaDao.findAll(ProgettoFormativo.class);
      for (int i = 0; i < listaProposte.size(); i++) {
        ProgettoFormativo p = listaProposte.get(i);
        if (p.getStudente().getUtenteEmail().contains(email) && (p.getStato() == 1)) {
          filePath = p.getDocumento();
          proposta = p;
        }
      }
      if (filePath == null) {   /* revisione effettuata */
        response.getWriter().write("errore");
        return;
      }

      File file = new File(filePath); 

      /* copio il contenuto del file caricato in un nuovo file */
      //InputStream filestream = filePart.getInputStream();
      //Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

      String note = (String)request.getParameter("note");
      String tutorInternoMail = request.getParameter("tutorInterno");
      TutorInterno tutorInterno = tutorInternoDao.findById(TutorInterno.class, tutorInternoMail);
      
      proposta.setNote(note);
      proposta.setTutorInterno(tutorInterno);
      proposta.setStato(2);

      /* memorizzo la proposta nel database */
      propostaDao.persist(proposta); 

      response.getWriter().write("ok");
      
      /* invio un messaggio di conferma alla jsp */
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('La proposta è stata caricata ed inviata');");
      out.println("location='caricaPpf.jsp';");
      out.println("</script>");
    }

    if (ruolo.contains("TutorInterno")) {

      String filePath = null;
      int idProposta = Integer.parseInt(request.getParameter("id"));
      ProgettoFormativo progetto = propostaDao.findById(ProgettoFormativo.class,idProposta);
      if (progetto.getStato() == 2) {
        filePath = progetto.getDocumento();
      }
      if (filePath == null) {   /* revisione effettuata */
        response.getWriter().write("errore");
        return;
      }

      File file = new File(filePath);

      /* copio il contenuto del file caricato in un nuovo file */
      //InputStream filestream = filePart.getInputStream();
      //Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      progetto.setStato(3); 

      propostaDao.persist(progetto);

      response.getWriter().write("ok");
      
      /* invio un messaggio di conferma alla jsp */
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('La proposta è stata caricata ed inviata');");
      out.println("location='caricaPpf.jsp';");
      out.println("</script>");
    }

    if (ruolo.contains("Presidente")) {

      String filePath = null;
      int idProposta = Integer.parseInt(request.getParameter("id"));
      ProgettoFormativo progetto = propostaDao.findById(ProgettoFormativo.class,idProposta);

      /* verifico che la proposta individuata sia conforme per essere inviata */
      if (progetto.getStato() == 3) {
          filePath = progetto.getDocumento();
      }
      if (filePath == null) {   /* revisione effettuata */
        response.getWriter().write("errore");
        return;
      }

      File file = new File(filePath);

      /* copio il contenuto del file caricato in un nuovo file */
      //InputStream filestream = filePart.getInputStream();
      //Files.copy(filestream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      progetto.setStato(4);

      /* memorizzo la proposta nel database */
      propostaDao.persist(progetto);

      response.getWriter().write("ok");
      
      /* invio un messaggio di conferma alla jsp */
      PrintWriter out = response.getWriter();
      out.println("<script type=\"text/javascript\">");
      out.println("alert('La proposta è stata caricata ed inviata');");
      out.println("location='caricaPpf.jsp';");
      out.println("</script>");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
  /*
   * 
   * @param dao IUtenteDao
   * @param dao2 IProgettoFormativoDao
   */
  public void setUtenteDao(IUtenteDao dao, IProgettoFormativoDao dao2) {
    this.utenteDao = dao;
    this.propostaDao = dao2;
  }
  
  /*
   * 
   * @param dao IUtenteDao
   * @param dao2 IProgettoFormativoDao
   * @param dao3 ITutorInternoDao
   */
  public void setUtenteDao(IUtenteDao dao, IProgettoFormativoDao dao2, ITutorInternoDao dao3) {
    this.utenteDao = dao;
    this.propostaDao = dao2;
    this.tutorInternoDao = dao3;
  }
}