package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.model.dao.IAziendaDao;

@Stateless
public class AziendaJpa extends GenericJpa<Azienda, String> implements IAziendaDao {

	@Override
	public Azienda findById(Class<Azienda> entity, String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
