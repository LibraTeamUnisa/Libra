package it.unisa.libra.model.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.CheckUtils;

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
  public Map<String, String> getTopAziendeFromNumStudenti(String pastDays, String limit,
      String status) {
    pastDays = CheckUtils.checkEmptiness(pastDays) ? pastDays : "30";

    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.DATE, -Integer.parseInt(pastDays));
    return getTopAziendeFromNumStudenti(minDate.getTime(), new Date(), limit, status);
  }

  @Override
  public Map<String, String> getTopAziendeFromNumStudenti(Date fromDate, Date toDate, String limit,
      String status) {
    Map<String, String> results = new HashMap<>();

    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.DATE, -30);

    fromDate = fromDate == null ? minDate.getTime() : fromDate;
    toDate = toDate == null ? new Date() : toDate;
    limit = CheckUtils.checkEmptiness(limit) ? limit : "3";
    status = CheckUtils.checkEmptiness(status) ? status : "4";

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
    Root<ProgettoFormativo> pf = cq.from(ProgettoFormativo.class);
    Join<ProgettoFormativo, Azienda> join = pf.join("azienda");

    Expression<Long> numTirocini = cb.count(join.get("utenteEmail"));

    cq.multiselect(numTirocini, join.get("nome"));

    cq.where(cb.equal(pf.<Integer>get("stato"), status));

    cq.where(cb.and(cb.greaterThanOrEqualTo(pf.<Date>get("dataInizio"), minDate.getTime())),
        cb.lessThanOrEqualTo(pf.<Date>get("dataInizio"), new Date()));

    cq.groupBy(join.get("nome"));
    cq.orderBy(cb.desc(numTirocini));

    List<Object[]> resultList =
        entityManager.createQuery(cq).setMaxResults(Integer.parseInt(limit)).getResultList();

    // Place results in map
    for (Object[] borderTypes : resultList) {
      results.put((String) borderTypes[1], ((Long) borderTypes[0]).toString());
    }
    return results;
  }

  @Override
  public Long getNumTirociniCompletati() {
    TypedQuery<Long> query =
        entityManager.createNamedQuery("ProgettoFormativo.countAllCompletati", Long.class);
    return query.getSingleResult();
  }

  public List<Map<String, String>> countByAziendaAndDate(Date fromDate, Date toDate, String limit,
      String status, String ragSoc) {
    List<Map<String, String>> resultList = new ArrayList<>();

    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.YEAR, -1);

    fromDate = fromDate == null ? minDate.getTime() : fromDate;
    toDate = toDate == null ? new Date() : toDate;
    limit = CheckUtils.checkEmptiness(limit) ? limit : null;
    status = CheckUtils.checkEmptiness(status) ? status : null;

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
    Root<ProgettoFormativo> pf = cq.from(ProgettoFormativo.class);
    Join<ProgettoFormativo, Azienda> join = pf.join("azienda");
    List<Predicate> listPred = new ArrayList<>();

    Expression<String> month =
        cb.concat(cb.function("MONTH", String.class, pf.get("dataInizio")), " ");
    month = cb.concat(month, cb.function("YEAR", String.class, pf.get("dataInizio")));

    Expression<Long> numTirocini = cb.count(pf.get("id"));

    cq.multiselect(join.get("nome"), month, numTirocini);

    Boolean tirIniziati = stringToBoolean(status);
    if (tirIniziati != null) {
      if (tirIniziati) {
        listPred.add(cb.isNull(pf.<Integer>get("dataFine")));
      } else {
        listPred.add(cb.isNotNull(pf.<Integer>get("dataFine")));
      }
    }

    String[] listRagSoc = getAziendeFromString(ragSoc);
    if (listRagSoc != null && listRagSoc.length > 0) {
      Predicate[] listPreds = new Predicate[listRagSoc.length];
      for (int i = 0; i < listRagSoc.length; i++) {
        listPreds[i] = (cb.like(join.<String>get("nome"), listRagSoc[i] + "%"));
      }
      listPred.add(cb.or(listPreds));
    }

    listPred.add(cb.greaterThanOrEqualTo(pf.<Date>get("dataInizio"), fromDate));
    listPred.add(cb.lessThanOrEqualTo(pf.<Date>get("dataInizio"), toDate));

    Predicate[] preds = new Predicate[listPred.size() - 1];
    cq.where(cb.and(listPred.toArray(preds)));

    cq.groupBy(pf.get("azienda"), month);
    cq.orderBy(cb.asc(join.get("nome")), cb.asc(pf.get("dataInizio")));

    Query query = entityManager.createQuery(cq);

    if (limit != null) {
      query.setMaxResults(Integer.parseInt(limit));
    }

    List<Object[]> result = query.getResultList();

    // Place results in map
    for (Object[] borderTypes : result) {
      Map<String, String> map = new HashMap<>();
      map.put("ragioneSociale", (String) borderTypes[0]);
      map.put("meseInizio", (String) borderTypes[1]);
      map.put("numStudenti", ((Long) borderTypes[2]).toString());
      resultList.add(map);
    }

    return resultList;
  }

  private String[] getAziendeFromString(String aziende) {
    if (!CheckUtils.checkEmptiness(aziende)) {
      return null;
    }

    return aziende.split(" ");
  }

  private Boolean stringToBoolean(String str) {
    if (!CheckUtils.checkEmptiness(str)) {
      return null;
    }
    return Boolean.parseBoolean(str);
  }
}
