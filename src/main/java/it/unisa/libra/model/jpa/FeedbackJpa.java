package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import it.unisa.libra.model.dao.IFeedbackDao;

@Stateless
public class FeedbackJpa extends GenericJpa<Feedback, FeedbackPK> implements IFeedbackDao {
}
