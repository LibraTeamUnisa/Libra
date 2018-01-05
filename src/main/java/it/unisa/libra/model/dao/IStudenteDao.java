package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IGenericDao<Studente, String> {

  public List<Studente> listaOrdinataPerCognome();

  /**
   * Restituisce il numero di studenti che hanno interagito con l'azienda data attraverso la
   * richiesta di un progetto formativo. Se uno studente ha richiesto piu' di un progetto formativo,
   * viene contato una sola volta.
   * 
   * @param azienda l'azienda
   * @return il numero di studenti
   */
  public long countByAzienda(Azienda azienda);

}
