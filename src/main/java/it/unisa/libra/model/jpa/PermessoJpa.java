package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Permesso;
import it.unisa.libra.model.dao.IPermessoDao;
import javax.ejb.Stateless;

@Stateless
public class PermessoJpa extends GenericJpa<Permesso, String> implements IPermessoDao {
}
