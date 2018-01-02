package it.unisa.libra.model.jpa;

import java.util.List;
import javax.ejb.Stateless;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IFeedbackDao;

@Stateless
public class FeedbackJpa extends GenericJpa<Feedback, FeedbackPK> implements IFeedbackDao {

	@Override
	public List<Feedback> findByType(int idPF, String type) {
		return entityManager.createNamedQuery("Feedback.findByType", Feedback.class)
				.setParameter("idProgettoFormativo", idPF)
				.setParameter("tipoDomanda", type).getResultList();
	}

}
