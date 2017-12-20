package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityTransaction;
import org.hibernate.Hibernate;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.ITutorEsternoDao;

@Stateless
public class TutorEsternoJpa extends GenericJpa<TutorEsterno, TutorEsternoPK>
    implements ITutorEsternoDao {

 /* @Override
  public void remove(TutorEsterno entity) {
    entityManager.createQuery("DELETE FROM TutorEsterno t WHERE t.id = :chiave").setParameter("chiave", entity.getId());
  }*/
}
