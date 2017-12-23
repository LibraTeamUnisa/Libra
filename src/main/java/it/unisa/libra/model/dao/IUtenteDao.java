package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Utente;

public interface IUtenteDao extends IGenericDao<Utente, String> {

  /**
   * Permette di recuperare dal database l'utente registrato al sistema con mail e password fornite
   * come parametri.
   * 
   * @param mail mail di registrazione dell'utente.
   * @param pwd password di registrazione dell'utente.
   * @return utente se esiste, altrimenti null.
   */
  Utente getUtente(String mail, String pwd);
}
