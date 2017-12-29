package it.unisa.libra.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
/**
 * Consente di verificare la correttezza sintattica e lessicografica delle informazioni
 * critiche.
 * @author Mauro Vitale
 * @version 1.0
 */
public class CheckUtils 
{
  /**
   * Espressione regolare per la verifica dell'email
   */
  public static final String EMAIL_REGEX="[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
  /**
   * Espressione regolare per la verifica di un numero telefonico italiano
   */
  public static final String TELEPHONE_REGEX="^[0-9]{8,11}$";
  
  /**
   * Effettua il controllo della validità di un indirizzo di posta elettronica
   * @param email Indica l'email da controllare
   * @return Restituisce true nel caso in cui l'email sia valida,false altrimenti
   */
  public static boolean checkEmail(String email)
  {
    return email!=null&&Pattern.matches(EMAIL_REGEX, email);
  }
  
  /**
   * Effettua il controllo della validità di un numero telefonico
   * @param telephone Indica il numero da verificare
   * @return Restituisce true nel caso in cui il numero sia valido,false altrimenti
   */
  public static boolean checkTelephone(String telephone)
  {
    return telephone!=null&&Pattern.matches(TELEPHONE_REGEX, telephone);
  }
  
  /**
   * Effettua la conversione ed il controllo di validità di una data
   * @param date Indica la data da verificare
   * @return Restituisce un'oggetto che incapsula le informazioni della data nel caso 
   *         in cui questa sia valida, null altrimenti.
   */
  public static Date checkDate(String date)
  {
    DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
    try {
      return format.parse(date);
    } catch(Exception ex) {
      return null;
    }
  }
  
  /**
   * Verifica che l'informazione specificata non sia vuota
   * @param str Indica la stringa da controllare
   * @return  Restituisce true nel caso in cui la stringa sia non vuota, false nel caso
   *          in cui la stringa è vuota o non è stata specificata
   */
  public static boolean checkEmptiness(String str)
  {
    return str!=null&&!str.trim().equals("");
  }
}
