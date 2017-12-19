package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.model.dao.IDomandaDao;

@Stateless
public class DomandaJpa extends GenericJpa<Domanda, Integer> implements IDomandaDao {
}
