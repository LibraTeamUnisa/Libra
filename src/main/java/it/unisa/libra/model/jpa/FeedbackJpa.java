package it.unisa.libra.model.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IFeedbackDao;

@Stateless
public class FeedbackJpa extends GenericJpa<Feedback, FeedbackPK> implements IFeedbackDao {

  /*@Override
  public List<Feedback> getFeedbackByIdPF(int id) {
    Query query = entityManager.createQuery(
        "SELECT progettoFormativoID,domandaID,valutazione FROM feedback AS f , domanda AS d WHERE f.progettoFormativoID='"
            + id
            + "' AND f.domandaID='1' AND d.id=f.domandaID AND d.testo='Note' AND d.tipo='Azienda'");
    
    
    if (query.getResultList().isEmpty()) {
      return null;
    } else {
      return query.getResultList();
    }
  }*/
}
