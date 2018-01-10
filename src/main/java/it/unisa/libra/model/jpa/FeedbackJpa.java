package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IFeedbackDao;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class FeedbackJpa extends GenericJpa<Feedback, FeedbackPK> implements IFeedbackDao {

  @Override
  public List<Feedback> findByType(int idPF, String type) {
    return entityManager.createNamedQuery("Feedback.findByType", Feedback.class)
        .setParameter("idProgettoFormativo", idPF).setParameter("tipoDomanda", type)
        .getResultList();
  }

}
