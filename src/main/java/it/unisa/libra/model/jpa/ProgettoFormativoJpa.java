package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.util.CheckUtils;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
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

  public Map<String, String> getTopAziendeFromNumStudenti(Date fromDate, Date toDate, String limit,
      String status) {
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

    List<Object[]> resultList =
        entityManager.createQuery(cQ).setMaxResults(Integer.parseInt(limit)).getResultList();
    // Place results in map
    for (Object[] borderTypes : resultList) {
      results.put((String) borderTypes[1], ((Long) borderTypes[0]).toString());
    }

    return results;

  }

@Override
public List<ProgettoFormativo> getInOrdineCronologico() {
	TypedQuery<ProgettoFormativo> query = entityManager.createNamedQuery("ProgettoFormativo.findInOrdineCronologico", ProgettoFormativo.class);
	Date dataInizio = new Date();
	int anno = dataInizio.getYear()+1900;
	Date dataFine = new Date();
	try {
		dataInizio = new SimpleDateFormat("yyyy-MM-dd").parse(anno+"-01-01");
		dataFine = new SimpleDateFormat("yyyy-MM-dd").parse(anno+"-12-31");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	query.setParameter("anno", dataInizio);
	query.setParameter("anno2", dataFine);
	return query.getResultList();
}

@Override
public List<ProgettoFormativo> findUltime10() {
	TypedQuery<ProgettoFormativo> q = entityManager.createNamedQuery("ProgettoFormativo.findUltimeDieci", ProgettoFormativo.class);
	q.setParameter("today",new Date());
	return q.getResultList();
}

@Override
public int contaOccorrenze() {
	 int count = ((Number)entityManager.createNamedQuery("ProgettoFormativo.count").getSingleResult()).intValue();
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
      return 0l;
    }
    if (stati.length == 0) {
      return 0l;
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
    CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
    CriteriaQuery<ProgettoFormativo> criteriaQuery =
        criteriaBuilder.createQuery(ProgettoFormativo.class);
    Metamodel metaModel = super.entityManager.getMetamodel();
    EntityType<ProgettoFormativo> ProgettoFormativo_ = metaModel.entity(ProgettoFormativo.class);
    Root<ProgettoFormativo> pf = criteriaQuery.from(ProgettoFormativo.class);
    criteriaQuery.select(pf);
    ParameterExpression<Azienda> aziendaParam = criteriaBuilder.parameter(Azienda.class);
    if (stati == null) {
      return new ArrayList<ProgettoFormativo>();
    }
    if (stati.length == 0) {
      return new ArrayList<ProgettoFormativo>();
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
    criteriaQuery
        .orderBy(criteriaBuilder.desc(pf.get(ProgettoFormativo_.getSingularAttribute("id"))));
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

}
