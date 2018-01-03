package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Studente; 
import it.unisa.libra.model.dao.IStudenteDao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
public class StudenteJpa extends GenericJpa<Studente, String> implements IStudenteDao {

	@Override
	public List<Studente> listaOrdinataPerCognome() {
		TypedQuery<Studente> query = (TypedQuery<Studente>) entityManager.createNamedQuery("Studente.findAllSurnameOrdered",Studente.class);
		return query.getResultList();
	}

	@Override
	public int contaOccorrenze() {
		int count = ((Number)entityManager.createNamedQuery("Studente.count").getSingleResult()).intValue();
		return count;
	}
	
}

