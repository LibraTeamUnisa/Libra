package it.unisa.libra.model.dao;

import java.util.List;
import it.unisa.libra.bean.Feedback;
import it.unisa.libra.bean.FeedbackPK;

public interface IFeedbackDao extends IGenericDao<Feedback, FeedbackPK> {
  //public List<Feedback> getFeedbackByIdPF(int id);
}
