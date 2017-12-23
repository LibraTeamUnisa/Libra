package it.unisa.libra.model.jpa;
<<<<<<< HEAD

=======


import java.util.List;
>>>>>>> branch 'develop' of https://github.com/LibraTeamUnisa/Libra.git
import javax.ejb.Stateless;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
<<<<<<< HEAD

=======
import it.unisa.libra.bean.Azienda;
>>>>>>> branch 'develop' of https://github.com/LibraTeamUnisa/Libra.git
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.ITutorEsternoDao;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class TutorEsternoJpa extends GenericJpa<TutorEsterno, TutorEsternoPK>
    implements ITutorEsternoDao {
<<<<<<< HEAD

=======

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

>>>>>>> branch 'develop' of https://github.com/LibraTeamUnisa/Libra.git
}
