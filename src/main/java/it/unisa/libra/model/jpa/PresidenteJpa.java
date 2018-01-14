package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Presidente;
import it.unisa.libra.model.dao.IPresidenteDao;
import it.unisa.libra.util.Loggable;
import javax.ejb.Stateless;

@Stateless
@Loggable
public class PresidenteJpa extends GenericJpa<Presidente, String> implements IPresidenteDao {
}
