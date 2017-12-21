package it.unisa.libra.model.dao;

import java.util.List;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;

public interface ITutorEsternoDao extends IGenericDao<TutorEsterno, TutorEsternoPK> {
  List<TutorEsterno> findByEmailAzienda(String emailAzienda);
}
