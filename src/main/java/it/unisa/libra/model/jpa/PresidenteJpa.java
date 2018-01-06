package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Presidente;
import it.unisa.libra.model.dao.IPresidenteDao;

@Stateless
public class PresidenteJpa extends GenericJpa<Presidente, String> implements IPresidenteDao {
}
