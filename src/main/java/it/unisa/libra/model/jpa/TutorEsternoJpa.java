package it.unisa.libra.model.jpa;
	
	
	import java.util.List;
	
	import javax.ejb.Stateless;
	import javax.persistence.TypedQuery;
	import it.unisa.libra.bean.TutorEsterno;
	import it.unisa.libra.bean.TutorEsternoPK;
	import it.unisa.libra.model.dao.ITutorEsternoDao;
	
	@Stateless
	public class TutorEsternoJpa extends GenericJpa<TutorEsterno, TutorEsternoPK>
	    implements ITutorEsternoDao {
	
	  @Override
	  public List<TutorEsterno> findByEmailAzienda(String emailAzienda) {
	    return entityManager
	        .createQuery("SELECT t FROM TutorEsterno t WHERE aziendaEmail = :x", TutorEsterno.class)
	        .setParameter("x", emailAzienda).getResultList();
	  }
	  
	  @Override
	  public List<TutorEsterno> findByAziendaNome(String nome) {
	    TypedQuery <TutorEsterno> query = entityManager.createNamedQuery("TutorEsterno.findByAziendaNome", TutorEsterno.class);
	    query.setParameter("nomeAzienda", nome);
	    
	    if (query.getResultList().isEmpty()){
	      System.out.println(query.getResultList());
	      return null;
	    } else {
	      return query.getResultList();
	    }
	   }
	
	}