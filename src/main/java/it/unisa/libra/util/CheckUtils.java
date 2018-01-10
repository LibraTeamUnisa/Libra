package it.unisa.libra.util;

import com.mysql.jdbc.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Consente di verificare la correttezza sintattica e lessicografica delle informazioni critiche.
 * 
 * @author Mauro Vitale, Emanuele Rinaldi
 * @version 1.1
 */
public class CheckUtils {
  /**
   * Espressione regolare per la verifica dell'email.
   */
  public static final String EMAIL_REGEX = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
  /**
   * Espressione regolare per la verifica di un numero telefonico italiano.
   */
  public static final String TELEPHONE_REGEX = "^[0-9]{8,11}$";

  /**
   * Effettua il controllo della validita' di un indirizzo di posta elettronica.
   * 
   * @param email Indica l'email da controllare
   * @return Restituisce true nel caso in cui l'email sia valida,false altrimenti
   */
  public static boolean checkEmail(String email) {
    return email != null && Pattern.matches(EMAIL_REGEX, email);
  }

  /**
   * Effettua il controllo della validita' di un numero telefonico.
   * 
   * @param telephone Indica il numero da verificare
   * @return Restituisce true nel caso in cui il numero sia valido,false altrimenti
   */
  public static boolean checkTelephone(String telephone) {
    return telephone != null && Pattern.matches(TELEPHONE_REGEX, telephone);
  }

  /**
   * Effettua la conversione ed il controllo di validita' di una data.
   * 
   * @param date Indica la data da verificare
   * @return Restituisce un'oggetto che incapsula le informazioni della data nel caso in cui questa
   *         sia valida, null altrimenti.
   */
  public static Date parseDate(String date) {
    return parseDateWithPattern(date, "dd/MM/yyyy");
  }

  /**
   * Effettua la conversione ed il controllo di validita' di una data tramite un pattern
   * specificato.
   * 
   * @param date Indica la data da convertire
   * @param pattern Indica il pattern da utilizzare per la conversione
   * @return Restituisce un <code>Date</code> rappresentante la data convertita dalla stringa.
   */
  public static Date parseDateWithPattern(String date, String pattern) {
    DateFormat format = new SimpleDateFormat(pattern, Locale.ITALY);
    try {
      return format.parse(date);
    } catch (Exception ex) {
      return null;
    }
  }

  /**
   * Verifica che l'informazione specificata non sia vuota.
   * 
   * @param str Indica la stringa da controllare
   * @return Restituisce true nel caso in cui la stringa sia non vuota, false nel caso in cui la
   *         stringa e' vuota o non e' stata specificata
   */
  public static boolean checkEmptiness(String str) {
    return str != null && !str.trim().equals("");
  }

  /**
   * Verifica che la collezione specificata non sia null o vuota.
   * 
   * @param collection Indica la collezione da controllare
   * @return Restituisce false nel caso in cui la collezione sia non vuota, true nel caso in cui la
   *         collezione � vuota o non � stata specificata
   */
  @SuppressWarnings("rawtypes")
  public static boolean isNullOrEmpty(Collection collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * Verifica che la mappa specificata non sia null o vuota.
   * 
   * @param map Indica la mappa da controllare
   * @return Restituisce false nel caso in cui la mappa sia non vuota, true nel caso in cui la mappa
   *         e' vuota o non e' stata specificata
   */
  @SuppressWarnings("rawtypes")
  public static boolean isNullOrEmpty(Map map) {
    return map == null || map.isEmpty();
  }

  /**
   * Verifica che la richiesta inviata alla servlet non sia null e il parametro <code>action</code>
   * sia non vuoto.
   * 
   * @param request Indica la request da controllare
   * @return Restituisce true nel caso in cui la request sia non vuota, false nel caso in cui la
   *         request e' vuota o non e' stata specificata
   */
  public static boolean validAction(HttpServletRequest request) {
    return (request != null && !StringUtils.isNullOrEmpty(request.getParameter(Actions.ACTION)));
  }
}
