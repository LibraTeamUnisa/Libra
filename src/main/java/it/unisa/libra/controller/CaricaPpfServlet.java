package it.unisa.libra.controller;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.FileUtils;
import it.unisa.libra.util.StatoPf;

import java.io.File;
import java.io.IOException;
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
      throws ServletException, IOException {}

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    File path = new File(FileUtils.BASE_PATH + FileUtils.PATH_PDF_PROGETTOF);
    if (!path.exists()) {
      path.mkdirs();
    }
    String email = (String) request.getSession().getAttribute("utenteEmail");
    String ruolo = (String) request.getSession().getAttribute("utenteRuolo");
    String file = request.getParameter("file");

    /* AZIENDA */
    if (ruolo.contains("Azienda")) {
      int idProposta = Integer.parseInt(request.getParameter("id"));
      String nomeFile = idProposta + ".pdf";

      String note = (String) request.getParameter("note");
      String ambito = (String) request.getParameter("ambito");
      String ambitoControl = (String) request.getParameter("tutorEsterno");

      if ((ambito != null) && (ambitoControl != null)) {
        if (!ambito.contains(ambitoControl)) {
          response.getWriter().write("errore_ambito");
        }
      }

      if (FileUtils.saveBase64ToFile(FileUtils.PATH_PDF_PROGETTOF, nomeFile, file)) {
        proposta = propostaDao.findById(ProgettoFormativo.class, idProposta);

        proposta.setAmbito(ambito);
        proposta.setNote(note);
        proposta.setDocumento(nomeFile);
        proposta.setStato(StatoPf.INVIATO);

        Date today = new Date();
        proposta.setDataInvio(today);

        /* memorizzo la proposta nel database */
        propostaDao.persist(proposta);
        response.getWriter().write("ok");
      }
      else {
    	  response.getWriter().write("errore");
      }
    }

    /* STUDENTE */
    if (ruolo.contains("Studente")) {
      int idProposta = Integer.parseInt(request.getParameter("id"));
      proposta = propostaDao.findById(ProgettoFormativo.class, idProposta);

      String nomeFile = idProposta + ".pdf";

      String note = (String) request.getParameter("note");
      String tutorInternoMail = request.getParameter("tutorInterno");
      TutorInterno tutorInterno = tutorInternoDao.findById(TutorInterno.class, tutorInternoMail);

      if (FileUtils.saveBase64ToFile(FileUtils.PATH_PDF_PROGETTOF, nomeFile, file)) {

        proposta.setNote(note);
        proposta.setTutorInterno(tutorInterno);
        proposta.setStato(2);

        /* memorizzo la proposta nel database */
        propostaDao.persist(proposta);

        response.getWriter().write("ok");
      }
      else {
    	  response.getWriter().write("errore");
      }
    }

    /* TUTOR INTERNO */
    if (ruolo.contains("TutorInterno")) {
      int idProposta = Integer.parseInt(request.getParameter("id"));
      proposta = propostaDao.findById(ProgettoFormativo.class, idProposta);

      String nomeFile = idProposta + ".pdf";

      if (FileUtils.saveBase64ToFile(FileUtils.PATH_PDF_PROGETTOF, nomeFile, file)) {

        proposta.setStato(3);

        /* memorizzo la proposta nel database */
        propostaDao.persist(proposta);

        response.getWriter().write("ok");
      }
      else {
    	  response.getWriter().write("errore");
      }
    }

    /* PRESIDENTE */
    if (ruolo.contains("Presidente")) {
      int idProposta = Integer.parseInt(request.getParameter("id"));
      proposta = propostaDao.findById(ProgettoFormativo.class, idProposta);

      String nomeFile = idProposta + ".pdf";

      if (FileUtils.saveBase64ToFile(FileUtils.PATH_PDF_PROGETTOF, nomeFile, file)) {
        proposta.setStato(4);

        /* memorizzo la proposta nel database */
        propostaDao.persist(proposta);

        response.getWriter().write("ok");
      }
      else {
    	  response.getWriter().write("errore");
      }
    }
  }

  /*
   * 
   * @param dao IUtenteDao
   * 
   * @param dao2 IProgettoFormativoDao
   */
  public void setUtenteDao(IUtenteDao dao, IProgettoFormativoDao dao2) {
    this.utenteDao = dao;
    this.propostaDao = dao2;
  }

 /*
  * 
  * @param dao IUtenteDao
  * 
  * @param dao2 IProgettoFormativoDao
  * 
  * @param dao3 ITutorInternoDao
  */
  public void setUtenteDao(IUtenteDao dao, IProgettoFormativoDao dao2, ITutorInternoDao dao3) {
    this.utenteDao = dao;
    this.propostaDao = dao2;
    this.tutorInternoDao = dao3;
  }
}