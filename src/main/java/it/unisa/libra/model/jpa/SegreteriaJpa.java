package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Segreteria;
import it.unisa.libra.model.dao.ISegreteriaDao;
import javax.ejb.Stateless;

@Stateless
public class SegreteriaJpa extends GenericJpa<Segreteria, String> implements ISegreteriaDao {
}
