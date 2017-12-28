package it.unisa.libra.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import it.unisa.libra.bean.Studente;
import it.unisa.libra.model.dao.IStudenteDao;

@Stateless
public class StudenteJpa extends GenericJpa<Studente, String> implements IStudenteDao {

	@Override
	public List<Studente> listaOrdinataPerCognome() {
		TypedQuery<Studente> query = (TypedQuery<Studente>) entityManager.createNamedQuery("Studente.findAllSurnameOrdered",Studente.class);
		return query.getResultList();
	}
	
}
