package it.unisa.libra.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Domanda;
import it.unisa.libra.model.dao.IDomandaDao;

@Stateless
public class DomandaJpa extends GenericJpa<Domanda, Integer> implements IDomandaDao {

	@Override
	public List<Domanda> findByType(String type) {
		return entityManager.createNamedQuery("Domanda.findByType", Domanda.class).setParameter("tipoDomanda", type).getResultList();
	}
}
