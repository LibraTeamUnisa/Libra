package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

  public List<Studente> getStudentiByAzienda(Azienda azienda);

  /**
   * Restituisce il numero di progetti formativi offerti dall'azienda data e che si trovano in uno
   * degli stati dati.
   * 
   * @param azienda l'azienda che offre i progetti formativi
   * @param stati gli stati in cui possono trovarsi i progetti formativi
   * @return il numero di progetti formativi
   */
  public Long countByAziendaAndStato(Azienda azienda, int... stati);

  /**
   * Restituisce tutti i progetti formativi offerti dall'azienda data che si trovano in uno degli
   * stati dati, ordinati per id in maniera decrescente.
   * 
   * @param azienda l'azienda che offre i progetti formativi
   * @param stati gli stati in cui possono trovarsi i progetti formativi
   * @return la lista dei progetti formativi
   */
  public List<ProgettoFormativo> findByAziendaAndStato(Azienda azienda, int... stati);

  /** Resituisce il numero di progetti formativi per cui l'azienda ha rilasciato un feedback.
   * 
   * @param azienda l'azienda
   * @return il numero di valutazioni espresse
   */
  public long countValutatiByAzienda(Azienda azienda);

  public Map<String, String> getTopAziendeFromNumStudenti(String pastDays, String limit, String status);
  
  public List<ProgettoFormativo> getInOrdineCronologico();
  
  public List<ProgettoFormativo> findUltime10();

  int contaOccorrenze();
  
}
