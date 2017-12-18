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
    
    private static final String  EMAIL_REFERENCE = "unisalibra@gmail.com";
    private static final String  EMAIL_PASSWORD  = "libra12345_";
    private static final String  EMAIL_NOREPLY   = "noreply@libra.it";
    private static final String  SMTP_SERVER     = "smtp.gmail.com";
    private static final Integer SMTP_PORT       = 465;
    
    private static final String  MSG_HEADER      ="<h1>La tua password &egrave stata recuperata!</h1>";
    private static final String  MSG_FOOTER      ="<h4><i>Lo staff di Libra ci tiene a te ed ai tuoi dati!</i></h4>";
    
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
		    throws ServletException, IOException{
	  String email=request.getParameter("email");
	  
	  try {
		  if (checkEmail(email)) {
		    Utente passLessUser=userDao.findById(new Utente(), email);
			  sendEmail(email, "Piattaforma Libra - Recupero Password", MSG_HEADER+"<br><p>La password del tuo account &egrave <b>"+StringEscapeUtils.escapeHtml(passLessUser.getPassword())+"</b></p><br><br>"+MSG_FOOTER);
			  response.setStatus(HttpServletResponse.SC_OK);
			  response.getWriter().write("L'email è stata inviata all'indirizzo specificato");
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
   * Effettua l'invio di una email opportunamente formattata ad un indirizzo di posta elettronica.
   * @param to Indica il destinatario dell'email
   * @param subject Indica l'oggetto dell'email
   * @param body Indica il messaggio contenuto nell'email
   * @throws UnsupportedEncodingException Viene lanciata nel caso in cui non è possibile effettuare il parse dell'
   *                                      indirizzo di posta elettronica di destinazione.
   * @throws MessagingException Viene lanciata nel caso in cui non è possibilie strutturare e formattare il messaggio da inviare.
   */
  public void sendEmail(String to, String subject, String body) 
		 throws UnsupportedEncodingException, MessagingException
  {
	  Properties props=setUpProperties(SMTP_SERVER, SMTP_PORT,true);
	  Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(EMAIL_REFERENCE, EMAIL_PASSWORD);
			}
		};
	  
	  MimeMessage message=setUpMessage(props, auth, EMAIL_NOREPLY, to, subject, body);
	  Transport.send(message);
  }
  /**
   * Imposta le proprietà necessarie del messaggion necessarie per effettuarne l'invio.
   * @param smtpHost Indica il server SMTP a cui effettuare il delivery del messaggio
   * @param port Indica la porta SSL a cui recapitare il messaggio
   * @param authEnabled Indica se è necessario usare l'autenticazione
   * @return Restituisce un oggetto che incapsula le proprietà impostate
   */
  private Properties setUpProperties(String smtpHost,Integer port,Boolean authEnabled)
  {
	  Properties props = new Properties();
	  
	  props.put("mail.smtp.host", smtpHost);
	  props.put("mail.smtp.port", ""+port);
	  props.put("mail.smtp.auth", ""+authEnabled); 
	  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	  props.put("mail.smtp.socketFactory.port", ""+port);  
	  props.put("mail.smtp.ssl.trust", smtpHost);
	  
	  return props;
  }
  /**
   * Effettua la creazione, secondo le proprietà impostate, del messaggio di posta elettronica da inviare ad 
   * una specifica destinazione.
   * @param props Indica le proprietà di delivery del messaggio da creare
   * @param auth Indica l'autenticazione,se richiesta, presso il server SMTP
   * @param from Indica il mittente del messaggio
   * @param to Indica il destinatario del messaggio
   * @param subject Indica l'oggetto del messaggio
   * @param body Indica il corpo del messaggio
   * @return Restituisce l'oggetto messaggio creato secondo i parametri specificati
   * @throws MessagingException Viene lanciata nel caso in cui non è possibilie strutturare e formattare il messaggio da inviare.
   * @throws UnsupportedEncodingException Viene lanciata nel caso in cui non è possibile effettuare il parse dell'indirizzo
   *                                      di posta elettronica di destinazione.
   */
  private MimeMessage setUpMessage(Properties props,Authenticator auth,String from,String to,String subject,String body) 
		  throws MessagingException,UnsupportedEncodingException
  {
	  Session session = Session.getInstance(props, auth);
	  
	  MimeMessage message=new MimeMessage(session);
	  message.addHeader("Content-type", "text/HTML; charset=UTF-8");
	  message.addHeader("format", "flowed");
	  message.addHeader("Content-Transfer-Encoding", "8bit");
	  message.setFrom(new InternetAddress(from, "NoReply-Libra"));
	  message.setReplyTo(InternetAddress.parse(from, false));
	  message.setSubject(subject, "UTF-8");
	  message.setContent(body,"text/html; charset=utf-8");
	  message.setSentDate(new Date());
	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
	  
	  return message;
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
