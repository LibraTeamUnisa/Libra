package it.unisa.libra.model.jpa;


import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.CheckUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Stateless
public class ProgettoFormativoJpa extends GenericJpa<ProgettoFormativo, Integer>
    implements IProgettoFormativoDao {

  @Override
  public ProgettoFormativo getLastProgettoFormativoByStudente(Studente studente) {
    TypedQuery<ProgettoFormativo> query =
        entityManager.createNamedQuery("ProgettoFormativo.findByStudente", ProgettoFormativo.class);
    query.setParameter("studente", studente);

    if (query.getResultList().isEmpty()) {
      return null;
    } else {
      return query.getResultList().get(0);
    }
  }

  @Override
  public List<ProgettoFormativo> getProgettiFormativiByAzienda(String nome) {
    TypedQuery<ProgettoFormativo> query = entityManager
        .createNamedQuery("ProgettoFormativo.findByAziendaNome", ProgettoFormativo.class);
    query.setParameter("nomeAzienda", nome);

    if (query.getResultList().isEmpty()) {
      System.out.println(query.getResultList());
      return null;
    } else {
      return query.getResultList();
    }
  }

  @Override
  public List<Studente> getStudentiByAzienda(Azienda azienda) {
    TypedQuery<Studente> query =
        entityManager.createNamedQuery("ProgettoFormativo.findStudenteByAzienda", Studente.class);
    query.setParameter("azienda", azienda);

    if (query.getResultList().isEmpty()) {
      System.out.println(query.getResultList());
      return null;
    } else {
      return query.getResultList();
    }
  }

  @Override
  public ProgettoFormativo getLastProgettoFormativoByStudenteAssociato(Studente studente,
      String tutorInterno) {
    TypedQuery<ProgettoFormativo> query = entityManager
        .createNamedQuery("ProgettoFormativo.findByStudenteAssociato", ProgettoFormativo.class);
    query.setParameter("studente", studente);
    query.setParameter("tutorinterno", tutorInterno);

    if (query.getResultList().isEmpty()) {
      return null;
    } else {
      return query.getResultList().get(0);
    }
  }

  @Override
  public Map<String, String> getTopAziendeFromNumStudenti(String pastDays, String limit, String status) {
    pastDays = CheckUtils.checkEmptiness(pastDays) ? pastDays : "30";
    
    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.DATE, -Integer.parseInt(pastDays));
    return getTopAziendeFromNumStudenti(minDate.getTime(),new Date(),limit,status);
  }
  
  public Map<String, String> getTopAziendeFromNumStudenti(Date fromDate, Date toDate, String limit, String status) {
    Map<String, String> results = new HashMap<>();

    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.DATE, -30);

    fromDate = fromDate == null ? minDate.getTime() : fromDate;
    toDate = toDate == null ? new Date() : toDate;
    limit = CheckUtils.checkEmptiness(limit) ? limit : "3";
    status = CheckUtils.checkEmptiness(status) ? status : "4";

    CriteriaBuilder cB = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> cQ = cB.createQuery(Object[].class);
    Root<ProgettoFormativo> pF = cQ.from(ProgettoFormativo.class);
    Join<ProgettoFormativo, Azienda> join = pF.join("azienda");

    Expression<Long> numTirocini = cB.count(join.get("utenteEmail"));

    cQ.multiselect(numTirocini, join.get("nome"));

    cQ.where(cB.equal(pF.<Integer>get("stato"), status));

    cQ.where(cB.and(cB.greaterThanOrEqualTo(pF.<Date>get("dataInizio"), minDate.getTime())),
        cB.lessThanOrEqualTo(pF.<Date>get("dataInizio"), new Date()));

    cQ.groupBy(join.get("nome"));
    cQ.orderBy(cB.desc(numTirocini));

    List<Object[]> resultList = entityManager.createQuery(cQ).setMaxResults(Integer.parseInt(limit)).getResultList();
    // Place results in map
    for (Object[] borderTypes : resultList) {
      results.put((String) borderTypes[1], ((Long) borderTypes[0]).toString());
    }

    return results;

  }

 /*
@Override
public List<ProgettoFormativo> getInOrdineCronologico() {
	TypedQuery<ProgettoFormativo> query = entityManager.createNamedQuery("ProgettoFormativo.findInOrdineCronologico", ProgettoFormativo.class);
	return query.getResultList();
}

*/

}

