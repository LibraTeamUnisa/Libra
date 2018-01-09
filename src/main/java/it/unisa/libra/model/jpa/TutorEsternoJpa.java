package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Stateless
public class TutorEsternoJpa extends GenericJpa<TutorEsterno, TutorEsternoPK>
    implements ITutorEsternoDao {

  @Override
  public List<TutorEsterno> findByEmailAzienda(String emailAzienda) {
    return entityManager
        .createQuery("SELECT t FROM TutorEsterno t WHERE aziendaEmail = :x", TutorEsterno.class)
        .setParameter("x", emailAzienda).getResultList();
  }

  @Override
  public List<TutorEsterno> findByAziendaNome(String nome) {
    TypedQuery<TutorEsterno> query =
        entityManager.createNamedQuery("TutorEsterno.findByAziendaNome", TutorEsterno.class);
    query.setParameter("nomeAzienda", nome);

    if (query.getResultList().isEmpty()) {
      System.out.println(query.getResultList());
      return null;
    } else {
      return query.getResultList();
    }
  }

  @Override
  public long countByEmailAzienda(String emailAzienda) {
    CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    Root<TutorEsterno> tutor = criteriaQuery.from(TutorEsterno.class);
    criteriaQuery.select(criteriaBuilder.countDistinct(tutor.get("id").get("ambito")));
    ParameterExpression<String> emailAziendaParam = criteriaBuilder.parameter(String.class);
    criteriaQuery
        .where(criteriaBuilder.equal(tutor.get("id").get("aziendaEmail"), emailAziendaParam));
    TypedQuery<Long> q = super.entityManager.createQuery(criteriaQuery);
    q.setParameter(emailAziendaParam, emailAzienda);
    return q.getSingleResult();
  }

}
