package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.model.dao.ISegreteriaDao;

@Stateless
public class SegreteriaJpa extends GenericJpa<Segreteria, String> implements ISegreteriaDao {

  public Segreteria findByEmail(Class<Segreteria> entityClass, String email) {
    return entityManager.createNamedQuery("findByEmail", Segreteria.class).getSingleResult();
  }
}
