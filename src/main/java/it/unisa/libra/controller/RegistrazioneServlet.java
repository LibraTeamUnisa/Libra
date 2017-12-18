package it.unisa.libra.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "RegistrazioneServlet", urlPatterns = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {

  @Inject
  private IUtenteDao utenteDao;

  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public RegistrazioneServlet() {}

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Azienda azienda = new Azienda();
    azienda.setUtenteEmail("superAzienda@email.it");
    azienda.setNome("RagioneSociale");
    azienda.setPartitaIVA("PartitaIva");
    azienda.setSede("sede");

    Utente utente = new Utente();
    utente.setEmail("superAzienda@email.it");
    utente.setAzienda(azienda);
    utente.setImgProfilo("aa");
    utente.setIndirizzo("indirizzo");
    utente.setPassword("password");
    utente.setTelefono("0101201203");

    Gruppo gruppo = new Gruppo();
    gruppo.setRuolo("Azienda");
    utente.setGruppo(gruppo);

    utenteDao.persist(utente);
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
