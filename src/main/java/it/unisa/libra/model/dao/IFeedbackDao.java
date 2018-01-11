package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;
import java.util.List;

public interface IFeedbackDao extends IGenericDao<Feedback, FeedbackPK> {

  List<Feedback> findByType(int idPF, String type);
}
