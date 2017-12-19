package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Presidente;

public interface IPresidenteDao extends IGenericDao<Presidente, String> {
  Presidente findByEmail(Class<Presidente> entityClass, String email);

}
