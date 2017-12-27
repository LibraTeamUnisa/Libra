package it.unisa.libra.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.model.dao.IAziendaDao;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.model.dao.IStudenteDao;



/**
 * Consente di effettuare una richiesta di progetto formativo ad un utente registrato con ruolo
 * studente
 */
@WebServlet("/RichiediPfServlet")
public class RichiediPfServlet extends HttpServlet {

  @EJB
  private IUtenteDao utenteDao;
  @EJB
  private IAziendaDao aziendaDao;
  @EJB
  private IProgettoFormativoDao pfDao;
  @EJB
  private IStudenteDao studenteDao;

  private static final long serialVersionUID = 1L;

  /** Default constructor. */
  public RichiediPfServlet() {}

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /** Gestisce le richieste di progetto formativo degli studenti */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();

    if (session.getAttribute("utenteEmail") != null) {
      String email = (String) session.getAttribute("utenteEmail");
      String aziendaEmail = request.getParameter("aziendaName");
      Utente utente = utenteDao.findById(Utente.class, email);

      if (utente != null) {
        Azienda azienda = aziendaDao.findByName(aziendaEmail);
        Studente studente = studenteDao.findById(Studente.class, email);

        String defaultString = "/";
        int deafuletInt = 0;

        ProgettoFormativo pf = new ProgettoFormativo();
        pf.setAzienda(azienda);
        pf.setStudente(studente);

        // Valori di default per i campi not null
        pf.setTutorInterno(null);
        pf.setAmbito(defaultString);
        pf.setDocumento(defaultString);
        pf.setStato(deafuletInt);
        pf.setPeriodoReport(deafuletInt);

        pfDao.persist(pf);

        response.sendRedirect(
            request.getContextPath() + "/profiloAziendale.jsp?nome=" + azienda.getNome() + "&stato=success");
      } else {
        response.getWriter().write("false");
      }

    } else {
      response.getWriter().write("false");
    }
  }
}
