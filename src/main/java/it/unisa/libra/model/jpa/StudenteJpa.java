package it.unisa.libra.model.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IStudenteDao;

@Stateless
public class StudenteJpa extends GenericJpa<Studente, String> implements IStudenteDao {
	
	@Override
	public long countByAzienda(Azienda azienda) {
		CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Metamodel metaModel = super.entityManager.getMetamodel();
		EntityType<Studente> Studente_ = metaModel.entity(Studente.class);
		EntityType<ProgettoFormativo> ProgettoFormativo_ = metaModel.entity(ProgettoFormativo.class);
		Root<Studente> stud = criteriaQuery.from(Studente.class);
		Join<Studente, ProgettoFormativo> pf = (Join<Studente, ProgettoFormativo>) stud
				.join(Studente_.getList("progettiFormativi"));
		criteriaQuery.select(criteriaBuilder.countDistinct(stud.get("utenteEmail")));
		ParameterExpression<Azienda> aziendaParam = criteriaBuilder.parameter(Azienda.class);
		criteriaQuery
				.where(criteriaBuilder.equal(pf.get(ProgettoFormativo_.getSingularAttribute("azienda")), aziendaParam));
		TypedQuery<Long> q = super.entityManager.createQuery(criteriaQuery);
		q.setParameter(aziendaParam, azienda);
		return q.getSingleResult();
	}
	
	@Override
	public List<Studente> listaOrdinataPerCognome() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Studente> cq = cb.createQuery(Studente.class);
		Root<Studente> root = cq.from(Studente.class);
		Join<Studente, Utente> join = root.join("utente");
		
		cq.multiselect(root.get("matricola"), root.get("nome"), root.get("cognome"), root.get("utenteEmail"),
				join.get("imgProfilo"));
		
		cq.orderBy(cb.asc(root.get("cognome")));
		
		Query query = entityManager.createQuery(cq);
		
		List<Studente> results = query.getResultList();
		
		return results;
		
	}
	
	@Override
	public int contaOccorrenze() {
		int count = ((Number) entityManager.createNamedQuery("Studente.count").getSingleResult()).intValue();
		return count;
	}
}
