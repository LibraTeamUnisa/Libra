package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Permesso;
import it.unisa.libra.model.dao.IPermessoDao;

@Stateless
public class PermessoJpa extends GenericJpa<Permesso, String> implements IPermessoDao {
}
