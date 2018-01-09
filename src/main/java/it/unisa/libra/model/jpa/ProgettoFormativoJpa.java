package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.CheckUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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

  public List<Map<String, String>> getTabellaValutazioni(Date fromDate, Date toDate, String status,
      String ragSoc) {
    List<Map<String, String>> resultList = new ArrayList<>();

    Calendar minDate = Calendar.getInstance();
    minDate.add(Calendar.YEAR, -1);

    fromDate = fromDate == null ? minDate.getTime() : fromDate;
    toDate = toDate == null ? new Date() : toDate;
    status = CheckUtils.checkEmptiness(status) ? status : null;

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
    Root<ProgettoFormativo> pf = cq.from(ProgettoFormativo.class);
    Join<ProgettoFormativo, Azienda> join = pf.join("azienda");
    Join<ProgettoFormativo, Feedback> feedbackJoin = pf.join("feedbacks", JoinType.LEFT);
    List<Predicate> listPred = new ArrayList<>();

    Expression<Long> numTirocini = cb.count(pf.get("id"));
    Expression<Double> valutazioneMedia = cb.avg(feedbackJoin.<Double>get("valutazione"));

    cq.multiselect(join.get("nome"), numTirocini, valutazioneMedia, pf.get("ambito"));

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

    cq.groupBy(pf.get("azienda"), pf.get("ambito"));
    cq.orderBy(cb.desc(numTirocini));

    Query query = entityManager.createQuery(cq);

    List<Object[]> result = query.getResultList();

    // Place results in map
    for (Object[] borderTypes : result) {
      Map<String, String> map = new HashMap<>();
      map.put("Azienda", (String) borderTypes[0]);
      map.put("Studenti", ((Long) borderTypes[1]).toString());
      if (borderTypes[2] != null) {
        map.put("Feedback", ((Double) borderTypes[2]).toString());
      } else {
        map.put("Feedback", "-1");
      }
      map.put("Ambito", (String) borderTypes[3]);
      resultList.add(map);
    }

    return resultList;
  }

  @Override
  public List<ProgettoFormativo> getInOrdineCronologico() {
    TypedQuery<ProgettoFormativo> query = entityManager
        .createNamedQuery("ProgettoFormativo.findInOrdineCronologico", ProgettoFormativo.class);
    Date dataInizio = new Date();
    int anno = dataInizio.getYear() + 1900;
    Date dataFine = new Date();
    try {
      dataInizio = new SimpleDateFormat("yyyy-MM-dd").parse(anno + "-01-01");
      dataFine = new SimpleDateFormat("yyyy-MM-dd").parse(anno + "-12-31");
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    query.setParameter("anno", dataInizio);
    query.setParameter("anno2", dataFine);
    return query.getResultList();
  }

  @Override
  public List<Map<String, String>> findUltime10() {
    List<Map<String, String>> list = new ArrayList<>();
    Query q = entityManager.createNamedQuery("ProgettoFormativo.findUltimeDieci");
    q.setParameter("today", new Date());

    List<Object[]> result = q.setMaxResults(10).getResultList();
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    for (Object[] obj : result) {
      Map<String, String> map = new HashMap<>();
      map.put("azienda", (String) obj[0]);
      map.put("studente", ((String) obj[1]) + " " + ((String) obj[2]));
      map.put("tutor", ((String) obj[3]) + " " + ((String) obj[4]));
      map.put("ambito", (String) obj[5]);
      map.put("dataInizio", formatter.format((Date) obj[6]));
      list.add(map);
    }
    return list;
  }

  @Override
  public int contaOccorrenze() {
    int count =
        ((Number) entityManager.createNamedQuery("ProgettoFormativo.count").getSingleResult())
            .intValue();
    return count;
  }


  @Override
  public Long countByAziendaAndStato(Azienda azienda, int... stati) {
    CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    Metamodel metaModel = super.entityManager.getMetamodel();
    EntityType<ProgettoFormativo> ProgettoFormativo_ = metaModel.entity(ProgettoFormativo.class);
    Root<ProgettoFormativo> pf = criteriaQuery.from(ProgettoFormativo.class);
    criteriaQuery.select(
        criteriaBuilder.countDistinct(pf.get(ProgettoFormativo_.getSingularAttribute("id"))));
    ParameterExpression<Azienda> aziendaParam = criteriaBuilder.parameter(Azienda.class);
    if (stati == null) {
      return 0L;
    }
    if (stati.length == 0) {
      return 0L;
    }
    List<Predicate> predicates = new ArrayList<Predicate>();
    for (int s : stati) {
      Predicate p =
          criteriaBuilder.equal(pf.get(ProgettoFormativo_.getSingularAttribute("stato")), s);
      predicates.add(p);
    }
    Predicate predicateStato = criteriaBuilder.or(predicates.toArray(new Predicate[] {}));
    criteriaQuery
        .where(
            criteriaBuilder
                .and(
                    (criteriaBuilder.equal(
                        pf.get(ProgettoFormativo_.getSingularAttribute("azienda")), aziendaParam)),
                    predicateStato));
    TypedQuery<Long> q = super.entityManager.createQuery(criteriaQuery);
    q.setParameter(aziendaParam, azienda);
    return q.getSingleResult();
  }

  @Override
  public List<ProgettoFormativo> findByAziendaAndStato(Azienda azienda, int... stati) {
    
     if (stati == null) {
      return new ArrayList<ProgettoFormativo>();
    }
    if (stati.length == 0) {
      return new ArrayList<ProgettoFormativo>();
    }
    
    CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
    CriteriaQuery<ProgettoFormativo> criteriaQuery =
        criteriaBuilder.createQuery(ProgettoFormativo.class);
    
    Root<ProgettoFormativo> pf = criteriaQuery.from(ProgettoFormativo.class);
    
    Join<ProgettoFormativo, Studente> join = pf.join("studente");
    Join<Studente, Utente> joinUt = join.join("utente");
    
    criteriaQuery.multiselect(join.get("nome"), join.get("cognome"), join.get("matricola"), joinUt.get("imgProfilo"));
    
    ParameterExpression<Azienda> aziendaParam = criteriaBuilder.parameter(Azienda.class);
   
    List<Predicate> predicates = new ArrayList<Predicate>();
    for (int s : stati) {
      Predicate p =
          criteriaBuilder.equal(pf.get("stato"), s);
      predicates.add(p);
    }
    Predicate predicateStato = criteriaBuilder.or(predicates.toArray(new Predicate[] {}));
    criteriaQuery
        .where(
            criteriaBuilder
                .and(
                    (criteriaBuilder.equal(
                        pf.get("azienda"), aziendaParam)),
                    predicateStato));
    criteriaQuery
        .orderBy(criteriaBuilder.desc(pf.get("id")));
    TypedQuery<ProgettoFormativo> q = super.entityManager.createQuery(criteriaQuery);
    q.setParameter(aziendaParam, azienda);
    return q.getResultList();
  }

  @Override
  public long countValutatiByAzienda(Azienda azienda) {
    // count distinct progettoformativo from feedback where progettoformativo.azienda = az and
    // domanda.tipo = azienda)
    CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    Metamodel metaModel = super.entityManager.getMetamodel();
    EntityType<Feedback> Feedback_ = metaModel.entity(Feedback.class);
    EntityType<ProgettoFormativo> ProgettoFormativo_ = metaModel.entity(ProgettoFormativo.class);
    EntityType<Domanda> Domanda_ = metaModel.entity(Domanda.class);
    Root<Feedback> feed = criteriaQuery.from(Feedback.class);
    Path<ProgettoFormativo> pf = feed.get("progettoFormativo");
    Path<Domanda> domanda = feed.get("domanda");
    criteriaQuery.select(criteriaBuilder
        .countDistinct(feed.get(Feedback_.getSingularAttribute("progettoFormativo"))));
    ParameterExpression<Azienda> aziendaParam = criteriaBuilder.parameter(Azienda.class);
    criteriaQuery.where(criteriaBuilder.and(
        (criteriaBuilder.equal(pf.get(ProgettoFormativo_.getSingularAttribute("azienda")),
            aziendaParam)),
        criteriaBuilder.equal(domanda.get(Domanda_.getSingularAttribute("tipo")), "Azienda")));
    TypedQuery<Long> q = super.entityManager.createQuery(criteriaQuery);
    q.setParameter(aziendaParam, azienda);
    return q.getSingleResult();
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
