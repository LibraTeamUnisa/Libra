package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.model.dao.IAziendaDao;

@Stateless
public class AziendaJpa extends GenericJpa<Azienda, String> implements IAziendaDao {

  @Override
  public Azienda findByName(String nome) {
    TypedQuery <Azienda> query = entityManager.createNamedQuery("Azienda.findName", Azienda.class);
    query.setParameter("nomeAzienda", nome);
    
    if (query.getResultList().isEmpty()){
      System.out.println(query.getResultList());
      return null;
    } else {
      return query.getResultList().get(0);
    }
  }

@Override
public int contaOccorrenze() {
	int count = ((Number)entityManager.createNamedQuery("Azienda.occorrenze").getSingleResult()).intValue();
	 return count;
}
  
}
