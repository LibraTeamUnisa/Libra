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

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.StringEscapeUtils;

/** Servlet implementation class AutenticazioneServlet */
@WebServlet(name = "RecuperoPasswordServlet", urlPatterns = "/recupero")
public class RecuperoPasswordServlet extends HttpServlet 
{
	@Inject
	private IUtenteDao userDao;
	
	private static final long serialVersionUID   = 1L;
	
    private static final String  EMAIL_PATTERN   = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    
    private static final String  EMAIL_REFERENCE = "unisalibra@gmail.com";
    private static final String  EMAIL_PASSWORD  = "libra12345_";
    private static final String  EMAIL_NOREPLY   = "noreply@libra.it";
    private static final String  SMTP_SERVER     = "smtp.gmail.com";
    private static final Integer SMTP_PORT       = 465;
    
    private static final String  MSG_HEADER      ="<h1>La tua password &egrave stata recuperata!</h1>";
    private static final String  MSG_FOOTER      ="<h4><i>Lo staff di Libra ci tiene a te ed ai tuoi dati!</i></h4>";
    
  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
  {
	  String email=request.getParameter("email");
	  
	  try
	  {
		  if(checkEmail(email))
		  {
			  Utente passLessUser=userDao.findById(new Utente(), email);
			  sendEmail(email, "Piattaforma Libra - Recupero Password", MSG_HEADER+"<br><p>La password del tuo account &egrave <b>"+StringEscapeUtils.escapeHtml(passLessUser.getPassword())+"</b></p><br><br>"+MSG_FOOTER);
			  response.setStatus(HttpServletResponse.SC_OK);
			  response.getWriter().write("L'email è stata inviata all'indirizzo specificato");
			  response.getWriter().flush();
		  }
		  else
		  {
			  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			  response.getWriter().write("L'email inserita non è valida.");
			  response.getWriter().flush();
		  }
	  }
	  catch(Exception ex)
	  {
		  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		  response.getWriter().write("Impossibile inviare l'email per il recupero della password");
		  response.getWriter().flush();
	  }
  }

  /** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException
  {
	  doGet(request, response);
  }
  
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
  
  private boolean checkEmail(String email)
  {
	  return email!=null&&Pattern.matches(EMAIL_PATTERN, email)&&userDao.findById(new Utente(),email)!=null;
  }
}
