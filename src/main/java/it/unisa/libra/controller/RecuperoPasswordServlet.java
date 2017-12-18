package it.unisa.libra.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import it.unisa.libra.util.EmailManager;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Consente di effettuare l'operazione di recupero password di un utente
 * registrato: quest'ultimo riceverà la password smarrita tramite una email
 * ricevuta all'indirizzo di posta elettronica con cui l'utente ha effettuato
 * la registrazione al sistema.
 * @author  Mauro Vitale
 * @version 1.0
 */
@WebServlet(name = "RecuperoPasswordServlet", urlPatterns = "/recupero")
public class RecuperoPasswordServlet extends HttpServlet {
	@Inject
	protected IUtenteDao userDao;
	
	private static final long serialVersionUID   = 1L;
	
  private static final String  EMAIL_PATTERN   = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
  
  private static final String  ACCESS_EMAIL     = "unisalibra@gmail.com";
  private static final String  ACCESS_PASSWORD  = "libra12345_";
  private static final String  EMAIL_NOREPLY    = "noreply@libra.it";
  private static final String  NAME_NOREPLY     = "NoReply-Libra";
  private static final String  SMTP_SERVER      = "smtp.gmail.com";
  private static final Integer SMTP_PORT        = 465;
    
  private static final String  MSG_HEADER       = "<h1>La tua password &egrave stata recuperata!</h1>";
  private static final String  MSG_FOOTER       = "<h4><i>Lo staff di Libra ci tiene a te ed ai tuoi dati!</i></h4>";
    
  /** 
   * Gestisce la richiesta di un utente che richiede il recupero della password restituendo
   * un oggetto HttpServletResponse con codice d'errore 400 nel caso in cui la mail specificata 
   * dall'utente non sia valida per il sistema altrimenti l'oggetto conterrà il codice 200 che 
   * denoterà la buona riuscita dell operazione; in quest'ultimo caso il sistema ùprovvede ad 
   * inviare una email opportunamente formattata all'indirizzo di posta elettronica di 
   * registrazione dell'utente.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		    throws ServletException, IOException
  {
	  String email=request.getParameter("email");
	  
	  try 
	  {
	    if (checkEmail(email)) {
	      Utente passLessUser=userDao.findById(new Utente(),email);
	      EmailManager eManager=new EmailManager(SMTP_SERVER,SMTP_PORT,EMAIL_NOREPLY,NAME_NOREPLY);
	      eManager.setAccessEmail(ACCESS_EMAIL);
	      eManager.setAccessPassword(ACCESS_PASSWORD);
	      Authenticator auth=eManager.getAuthenticator();
	      Properties props=eManager.setUpProperties(true);
	      MimeMessage message=eManager.setUpMessage(props, auth, email, "Piattaforma Libra - Recupero Password", 
	          MSG_HEADER+"<br><p>La password del tuo account &egrave <b>"+StringEscapeUtils.
	          escapeHtml(passLessUser.getPassword())+"</b></p><br><br>"+MSG_FOOTER);
	      eManager.sendEmail(message);
	      
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("L'email è stata inviata all'indirizzo specificato.");
        response.getWriter().flush();
    } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("L'email inserita non è valida.");
        response.getWriter().flush();
    }
    } catch(Exception ex) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("Impossibile inviare l'email per il recupero della password");
      response.getWriter().flush();
    }
  }

  /** 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException
  {
	  doGet(request, response);
  }
  
  /**
   * Effettua la validazione dell'email ricevuta
   * @param email Indica l'email da validare
   * @return Restituisce true nel caso in cui si tratti di una email vailda,false altrimenti.
   */
  private boolean checkEmail(String email)
  {
	  if(email==null) {
	    return false;
	} else {
      return Pattern.matches(EMAIL_PATTERN, email)&&userDao.findById(new Utente(),email)!=null;
	}
  }
}
