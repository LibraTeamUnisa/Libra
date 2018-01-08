package it.unisa.libra.controller;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IGruppoDao;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.model.dao.ITutorInternoDao;
import it.unisa.libra.model.dao.IUtenteDao;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Giandomenico Solimando, Luca Izzo
 * @version 1.1
 */
@WebServlet(name = "GestioneUtenteServlet", urlPatterns = "/gestioneUtenteServlet")
public class GestioneUtenteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private IUtenteDao utenteDao;

  @EJB
  private IPresidenteDao presidenteDao;

  @EJB
  private ITutorInternoDao tutorInternoDao;

  @EJB
  private IAziendaDao aziendaDao;

  @EJB
  private IGruppoDao gruppoDao;

  /** Default constructor. */
  public GestioneUtenteServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
    return;
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Utente utente = new Utente();
    Gruppo gruppo = new Gruppo();
    try {

      String ruolo = request.getParameter("ruolo");
      if ((ruolo == null) || (ruolo.length() == 0)) {
        response.getWriter().write(BADREQUEST_MESS);
      }


      gruppo.setRuolo(ruolo);
      utente.setGruppo(gruppo);
      utente.setImgProfilo(" ");

      String emailutente = request.getParameter("email");
      String password = request.getParameter("password");
      String indirizzo = request.getParameter("indirizzo");
      String telefono = request.getParameter("telefono");



      if ((emailutente == null) || (emailutente.length() == 0) || (password == null)
          || (password.length() == 0) || (indirizzo == null) || (indirizzo.length() == 0)
          || (telefono == null) || (telefono.length() == 0)) {
        response.getWriter().write(BADREQUEST_MESS);
      }


      if (ruolo.equalsIgnoreCase("Presidente")) {
        Presidente pres = new Presidente();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date dataDiNascita = null;
        try {
          dataDiNascita = parser.parse(request.getParameter("data"));
        } catch (Exception e) {
          response.getWriter().write(BADREQUEST_MESS);
        }
        if (dataDiNascita == null) {
          response.getWriter().write(BADREQUEST_MESS);
        }

        String linksito = request.getParameter("linkSito");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String giorniDiRic = request.getParameter("giornoDiRicevimento");
        String ufficio = request.getParameter("ufficio");

        if ((linksito == null) || (nome == null) || (cognome == null) || (giorniDiRic == null)
            || (ufficio == null)) {
          response.getWriter().write(BADREQUEST_MESS);
        }
        utente.setEmail(emailutente);
        utente.setPassword(password);
        utente.setIndirizzo(indirizzo);
        utente.setTelefono(telefono);
        pres.setDataDiNascita(dataDiNascita);
        pres.setUtenteEmail(emailutente);
        pres.setLinkSito(linksito);
        pres.setNome(nome);
        pres.setCognome(cognome);
        pres.setGiorniDiRicevimento(giorniDiRic);
        pres.setUfficio(ufficio);

        pres.setUtente(utente);
        utenteDao.persist(utente);

        response.getWriter().write(SUCCESS_MESS);

      } else if (ruolo.equalsIgnoreCase("TutorInterno")) {
        TutorInterno tut = new TutorInterno();

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date dataDiNascita = null;
        try {
          dataDiNascita = parser.parse(request.getParameter("data"));


        } catch (Exception e) {
          response.getWriter().write(BADREQUEST_MESS);
        }
        if (dataDiNascita == null) {
          response.getWriter().write(BADREQUEST_MESS);
        }


        String linksito = request.getParameter("linkSito");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");

        if ((linksito == null) || (nome == null) || (cognome == null)) {
          response.getWriter().write(BADREQUEST_MESS + "cazzoo");
        }

        utente.setEmail(emailutente);
        utente.setPassword(password);
        utente.setIndirizzo(indirizzo);
        utente.setTelefono(telefono);

        tut.setNome(nome);
        tut.setUtenteEmail(emailutente);
        tut.setCognome(cognome);
        tut.setLinkSito(linksito);

        tut.setUtente(utente);
        utente.setTutorInterno(tut);
        utenteDao.persist(utente);
        response.getWriter().write(SUCCESS_MESS);

      }

      else if (ruolo.equalsIgnoreCase("Azienda")) {

        Azienda azienda = new Azienda();

        String partitaIva = request.getParameter("partitaIva");
        String nome = request.getParameter("nome");
        String Sede = request.getParameter("Sede");

        if ((partitaIva == null) || (nome == null) || (Sede == null)) {
          response.getWriter().write(BADREQUEST_MESS);
        }

        utente.setEmail(emailutente);
        utente.setPassword(password);
        utente.setIndirizzo(indirizzo);
        utente.setTelefono(telefono);

        azienda.setNome(nome);
        azienda.setUtenteEmail(emailutente);
        azienda.setPartitaIVA(partitaIva);
        azienda.setSede(Sede);

        azienda.setUtente(utente);
        utente.setAzienda(azienda);
        utenteDao.persist(utente);
        response.getWriter().write(SUCCESS_MESS);
      }
    } catch (Exception ex) {
      response.getWriter().write(BADREQUEST_MESS);
    }
  }

  private static final String BADREQUEST_MESS = "L'operazione richiesta non e' valida.";
  private static final String SUCCESS_MESS = "ok";
}
