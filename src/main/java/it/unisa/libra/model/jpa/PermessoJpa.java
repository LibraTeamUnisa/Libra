package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Permesso;
import it.unisa.libra.model.dao.IPermessoDao;
import it.unisa.libra.util.Loggable;
import javax.ejb.Stateless;

@Stateless
@Loggable
public class PermessoJpa extends GenericJpa<Permesso, String> implements IPermessoDao {
}
