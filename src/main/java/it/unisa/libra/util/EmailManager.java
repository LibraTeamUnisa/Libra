package it.unisa.libra.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Definisce un manager di email che si occupa delle impostazioni delle proprietà e dell'invio di un
 * messaggio di posta elettronica ad un server SMTP che provvede ad effettuare il delivery ad un
 * destinatario opportunamente specificato.
 * 
 * @author Mauro Vitale
 * @version 1.0
 */
public class EmailManager {
  private String smtpServer;
  private Integer smtpPort;
  private String accessEmail;
  private String accessPassword;
  private String senderEmail;
  private String senderName;

  /**
   * Effettua la creazione di un oggetto EmailManager secondo i valori specificati.
   * 
   * @param smtpServer Indica il server SMTP a cui inoltrare le richieste di invio delle email
   * @param smtpPort Indica la porta SSL a cui inoltrare le richieste di invio delle email
   * @param senderEmail Indica l'email del mittente
   */
  public EmailManager(String smtpServer, Integer smtpPort, String senderEmail, String senderName) {
    this.smtpServer = smtpServer;
    this.smtpPort = smtpPort;
    this.senderEmail = senderEmail;
    this.senderName = senderName;
  }

  /**
   * Consente di specificare una nuova email per l'autenticazione presso il server SMTP specificato
   * 
   * @param newEmail Indica la nuova email per l'autenticazione
   */
  public void setAccessEmail(String newEmail) {
    accessEmail = newEmail;
  }

  /**
   * Consente di specificare una nuova password per l'autenticazione presso il server SMTP
   * specificato
   * 
   * @param newPassword Indica la nuova password per l'autenticazione
   */
  public void setAccessPassword(String newPassword) {
    accessPassword = newPassword;
  }

  /**
   * Imposta le proprietà necessarie del messaggion necessarie per effettuarne l'invio.
   * 
   * @param smtpHost Indica il server SMTP a cui effettuare il delivery del messaggio
   * @param port Indica la porta SSL a cui recapitare il messaggio
   * @param authEnabled Indica se è necessario usare l'autenticazione
   * @return Restituisce un oggetto che incapsula le proprietà impostate
   */
  public Properties setUpProperties(Boolean authEnabled) {
    Properties props = new Properties();

    props.put("mail.smtp.host", smtpServer);
    props.put("mail.smtp.port", "" + smtpPort);
    props.put("mail.smtp.auth", "" + authEnabled);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.port", "" + smtpPort);
    props.put("mail.smtp.ssl.trust", smtpServer);

    return props;
  }

  /**
   * Effettua la creazione dell'oggetto necessario per l'autenticazione presso un server SMTP che la
   * richiede.
   * 
   * @return Restituisce l'oggetto che consente di effettuare l'autenticazione presso il server SMTP
   *         specificato.
   */
  public Authenticator getAuthenticator() {
    return new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(accessEmail, accessPassword);
      }
    };
  }

  /**
   * Effettua la creazione, secondo le proprietà impostate, del messaggio di posta elettronica da
   * inviare ad una specifica destinazione.
   * 
   * @param props Indica le proprietà di delivery del messaggio da creare
   * @param auth Indica l'autenticazione,se richiesta, presso il server SMTP
   * @param from Indica il mittente del messaggio
   * @param to Indica il destinatario del messaggio
   * @param subject Indica l'oggetto del messaggio
   * @param body Indica il corpo del messaggio
   * @return Restituisce l'oggetto messaggio creato secondo i parametri specificati
   * @throws MessagingException Viene lanciata nel caso in cui non è possibilie strutturare e
   *         formattare il messaggio da inviare.
   * @throws UnsupportedEncodingException Viene lanciata nel caso in cui non è possibile effettuare
   *         il parse dell'indirizzo di posta elettronica di destinazione.
   */
  public MimeMessage setUpMessage(Properties props, Authenticator auth, String to, String subject,
      String body) throws MessagingException, UnsupportedEncodingException {
    Session session = Session.getInstance(props, auth);

    MimeMessage message = new MimeMessage(session);
    message.addHeader("Content-type", "text/HTML; charset=UTF-8");
    message.addHeader("format", "flowed");
    message.addHeader("Content-Transfer-Encoding", "8bit");
    message.setFrom(new InternetAddress(senderEmail, senderName));
    message.setReplyTo(InternetAddress.parse(senderEmail, false));
    message.setSubject(subject, "UTF-8");
    message.setContent(body, "text/html; charset=utf-8");
    message.setSentDate(new Date());
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

    return message;
  }

  /**
   * Effettua l'invio di una email opportunamente formattata ad un indirizzo di posta elettronica.
   * 
   * @param to Indica il destinatario dell'email
   * @param subject Indica l'oggetto dell'email
   * @param body Indica il messaggio contenuto nell'email
   * @throws UnsupportedEncodingException Viene lanciata nel caso in cui non è possibile effettuare
   *         il parse dell' indirizzo di posta elettronica di destinazione.
   * @throws MessagingException Viene lanciata nel caso in cui non è possibilie strutturare e
   *         formattare il messaggio da inviare.
   */
  public void sendEmail(MimeMessage message)
      throws UnsupportedEncodingException, MessagingException {
    Transport.send(message);
  }
}
