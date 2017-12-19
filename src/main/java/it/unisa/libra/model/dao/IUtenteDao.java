package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Utente;

public interface IUtenteDao extends IGenericDao<Utente, String> {
  Utente findByEmail(Class<Utente> entityClass, String email);

}
