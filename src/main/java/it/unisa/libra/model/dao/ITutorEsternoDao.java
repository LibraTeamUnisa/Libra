package it.unisa.libra.model.dao;

import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import java.util.List;

public interface ITutorEsternoDao extends IGenericDao<TutorEsterno, TutorEsternoPK> {

  List<TutorEsterno> findByEmailAzienda(String emailAzienda);

  List<TutorEsterno> findByAziendaNome(String nome);

}

