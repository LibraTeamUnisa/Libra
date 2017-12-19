package it.unisa.libra.model.dao;

import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;

public interface IProgettoFormativoDao extends IGenericDao<ProgettoFormativo, Integer> {
  /**
   * Questo metodo permette di ottenere l'ultimo progetto formativo di uno studente, in ordine
   * cronologico, in base alla data di invio del documento.
   * 
   * @param studente Studente di cui si vuole ottenere l'ultimo progetto formativo
   * @return Ultimo progetto formativo dello studente
   */
  public ProgettoFormativo getLastProgettoFormativoByStudente(Studente studente);
}
