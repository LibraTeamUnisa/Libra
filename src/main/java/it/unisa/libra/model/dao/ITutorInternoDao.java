package it.unisa.libra.model.dao;

import it.unisa.libra.bean.TutorInterno;

public interface ITutorInternoDao extends IGenericDao<TutorInterno, String> {
  TutorInterno findByEmail(Class<TutorInterno> entityClass, String email);

}
