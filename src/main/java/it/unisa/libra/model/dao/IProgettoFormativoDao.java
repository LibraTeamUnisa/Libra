package it.unisa.libra.model.dao;

import java.util.List;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;

public interface IProgettoFormativoDao extends IGenericDao<ProgettoFormativo, Integer> {
  /**
   * Questo metodo permette di ottenere l'ultimo progetto formativo di uno studente, in ordine
   * cronologico, in base all'id del progetto formativo.
   * 
   * @param studente Studente di cui si vuole ottenere l'ultimo progetto formativo.
   * @return ultimo progetto formativo dello studente oppure null se lo studente non ha nessun
   *         progetto formativo.
   */
  public ProgettoFormativo getLastProgettoFormativoByStudente(Studente studente);

  /**
   * Questo metodo permette di ottenere l'ultimo progetto formativo di uno studente associato ad un
   * tutor interno.
   * 
   * @param studente studente di cui si vuole ottenere l'ultimo progetto formativo.
   * @param tutorInterno tutor di cui si vogliono conoscere i progetti formativi degli studenti
   *        associati.
   * @return ultimo progetto formativo del tutor interno e dello studente a lui associato oppure
   *         null se lo studente non è associato al tutor interno.
   */
  public ProgettoFormativo getLastProgettoFormativoByStudenteAssociato(Studente studente,
      String tutorInterno);
  public List<ProgettoFormativo> getProgettiFormativiByAzienda(String nome);
}
