package it.unisa.libra.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Tutorinterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.model.dao.ISegreteriaDao;
import it.unisa.libra.model.dao.IStudenteDao;
import it.unisa.libra.model.dao.ITutorInternoDao;

/** Servlet implementation class AutenticazioneServlet */
public class ModificaProfiloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /** Gestore della persistenza dello studente */
  @EJB
  private IStudenteDao studenteDao;
  
  /** Gestore della persistenza del tutor interno */
  @EJB
  private ITutorInternoDao tutorinternodao;
  
  /** Gestore della persistenza del presidente */
  @EJB
  private IPresidenteDao presidentedao;
  
  /** Gestore della persistenza dell'azienda */
  @EJB
  private IAziendaDao aziendadao;
  
  /** Gestore della persistenza della segreteria */
  @EJB
  private ISegreteriaDao segreteriadao;
  
  private String newAddress = "";
  private String newTelephoneNumber = "";
  private String newSite = "";
  private String newOffice = "";
  private String newReception = "";

  /** Default constructor. */
  public ModificaProfiloServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession();
      String email = (String) session.getAttribute("utenteEmail");
      String ruolo = (String) session.getAttribute("utenteRuolo");
      newAddress = request.getParameter("address");
      newTelephoneNumber = request.getParameter("telephoneNumber");
      if(ruolo.equals("Studente")) {  
          Studente student = (Studente) studenteDao.findById(Studente.class, email);
          Utente user = student.getUtente();
          user.setIndirizzo(newAddress);
          user.setTelefono(newTelephoneNumber);
          studenteDao.merge(student);
      } else if (ruolo.equals("TutorInterno")) {
    	  newSite = request.getParameter("site");
    	  TutorInterno ti = (TutorInterno) tutorinternodao.findById(TutorInterno.class, email);
    	  Utente user = ti.getUtente();
    	  user.setIndirizzo(newAddress);
    	  user.setTelefono(newTelephoneNumber);
    	  ti.setLinkSito(newSite);
    	  tutorinternodao.merge(ti);
      } else if (ruolo.equals("Presidente")) {
    	  newSite = request.getParameter("site");
    	  newOffice = request.getParameter("office");
    	  newReception = request.getParameter("reception");
    	  Presidente president = (Presidente) presidentedao.findById(Presidente.class, email);
    	  Utente user = president.getUtente();
    	  user.setIndirizzo(newAddress);
    	  user.setTelefono(newTelephoneNumber);
    	  president.setLinkSito(newSite);
    	  president.setUfficio(newOffice);
    	  president.setGiorniDiRicevimento(newReception);
    	  presidentedao.merge(president);
      } else if (ruolo.equals("Segreteria")) {
    	  newReception = request.getParameter("reception");
    	  Segreteria secretary = (Segreteria) segreteriadao.findById(Segreteria.class, email);
    	  Utente user = secretary.getUtente();
    	  user.setIndirizzo(newAddress);
    	  user.setTelefono(newTelephoneNumber);
    	  secretary.setGiorniDiRicevimento(newReception);
    	  segreteriadao.merge(secretary);
      } else if (ruolo.equals("Azienda")) {
    	  Azienda company = (Azienda) aziendadao.findById(Azienda.class, email);
    	  Utente user = company.getUtente();
    	  user.setTelefono(newTelephoneNumber);
    	  company.setSede(newAddress);
    	  aziendadao.merge(company);
      }
      RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("profilo.jsp");  
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
