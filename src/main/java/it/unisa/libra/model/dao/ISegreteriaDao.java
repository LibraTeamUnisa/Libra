package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Segreteria;

public interface ISegreteriaDao extends IGenericDao<Segreteria, String> {
  Segreteria findByEmail(Class<Segreteria> entityClass, String email);
}
