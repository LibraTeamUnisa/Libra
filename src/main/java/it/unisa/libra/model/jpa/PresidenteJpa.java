package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Presidente;
import it.unisa.libra.model.dao.IPresidenteDao;
import javax.ejb.Stateless;

@Stateless
public class PresidenteJpa extends GenericJpa<Presidente, String> implements IPresidenteDao {
}
