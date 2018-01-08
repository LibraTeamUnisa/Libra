package it.unisa.libra.controller;
	
	
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	
	import javax.ejb.EJB;
	import java.io.IOException;
	
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import it.unisa.libra.bean.Azienda;
	import it.unisa.libra.bean.Gruppo;
	import it.unisa.libra.bean.Presidente;
	import it.unisa.libra.bean.TutorInterno;
	import it.unisa.libra.bean.Utente;
	import it.unisa.libra.model.dao.IAziendaDao;
	import it.unisa.libra.model.dao.IGenericDao;
	import it.unisa.libra.model.dao.IGruppoDao;
	import it.unisa.libra.model.dao.IPresidenteDao;
	import it.unisa.libra.model.dao.ITutorInternoDao;
	import it.unisa.libra.model.dao.IUtenteDao;
	
	/** Servlet implementation class AutenticazioneServlet */
	@WebServlet("/GestioneUtenteServlet")
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
	  }
	
	  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    
		   PrintWriter out = response.getWriter();
		   Utente utente = new Utente();
		   Presidente pres = new Presidente();
		   TutorInterno tut = new TutorInterno();
		   Azienda azienda = new Azienda();
		   Gruppo gruppo = new Gruppo();
		   
		   
		    try{
		    	
		    	String ruolo = request.getParameter("ruolo");
		    	gruppo.setRuolo(ruolo);
		    	utente.setGruppo(gruppo);
		    	utente.setImgProfilo("  "); // serve il path della foto sul db
		    	utente.setEmail(request.getParameter("email"));  
	    		utente.setPassword(request.getParameter("password"));
	    		utente.setIndirizzo(request.getParameter("indirizzo"));
	    		utente.setTelefono(request.getParameter("telefono"));
	    		
	 		
		    	if(ruolo.equals("Presidente")) {
		    		//pres.setUtente(utente);
		    		String data = request.getParameter("bday");
		    		
		    		
		    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    		Date result =  df.parse(data);
		    		pres.setUtenteEmail(request.getParameter("email"));
		    		pres.setDataDiNascita(result);
		    		pres.setLinkSito(request.getParameter("webSite"));
		    		pres.setNome(request.getParameter("nome"));
		    		pres.setCognome(request.getParameter("cognome"));
		    		pres.setGiorniDiRicevimento(request.getParameter("giornoDiRicevimento"));
		    		pres.setUfficio(request.getParameter("ufficio"));
		    		
		    		utenteDao.persist(utente);
		    		presidenteDao.persist(pres);
		    		
		    	}
		    	else if(ruolo.equals("TutorInterno")) {
		    		
		    		//tut.setUtente(utente);
		    		String data = request.getParameter("bday");
		    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    		Date result =  df.parse(data);
		    		
		    		tut.setUtenteEmail(request.getParameter("email"));
		    		tut.setDataDiNascita(result);
		    		tut.setLinkSito(request.getParameter("website"));
		    		tut.setNome(request.getParameter("Nome"));
		    		tut.setCognome(request.getParameter("cognome"));
		    		
		    		utenteDao.persist(utente);
		    		tutorInternoDao.persist(tut);
				}
				
		    	else if(ruolo.equals("Azienda")) {
		    		
		    		//azienda.setUtente(utente);
		    	
		    		azienda.setUtenteEmail(request.getParameter("email"));
		    		azienda.setNome(request.getParameter("Nome"));
		    		azienda.setPartitaIVA(request.getParameter("partitaIva"));
		    		azienda.setSede(request.getParameter("website"));
		    		
		    		utenteDao.persist(utente);
		    		aziendaDao.persist(azienda);
	
		    	}
		    	
		    	
	
			}
			catch(Exception e){
				out.print("L'aggiunta utente è fallita!");
			}
		    
		    //System.out.println("Utente aggiunto con successo!");
		    
		    //out.println("alert('Utente aggiunto con successo! Stai per essere reindirizzato alla dashboard!');");
		    response.sendRedirect("dashboardSegreteria.jsp"); 
		  }
		}