package it.unisa.libra.model.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.TutorEsterno;

@Stateless
public class ProgettoFormativoJpa extends GenericJpa<ProgettoFormativo, Integer>
    implements IProgettoFormativoDao {

  @Override
  public ProgettoFormativo getLastProgettoFormativoByStudente(Studente studente) {
    TypedQuery<ProgettoFormativo> query =
        entityManager.createNamedQuery("ProgettoFormativo.findByStudente", ProgettoFormativo.class);
    query.setParameter("studente", studente);

    if (query.getResultList().isEmpty()) {
      return null;
    } else {
      return query.getResultList().get(0);
    }
  }

  @Override
  public List<ProgettoFormativo> getProgettiFormativiByAzienda(String nome) {
    TypedQuery<ProgettoFormativo> query = entityManager
        .createNamedQuery("ProgettoFormativo.findByAziendaNome", ProgettoFormativo.class);
    query.setParameter("nomeAzienda", nome);

    if (query.getResultList().isEmpty()) {
      System.out.println(query.getResultList());
      return null;
    } else {
      return query.getResultList();
    }
  }

  @Override
  public ProgettoFormativo getLastProgettoFormativoByStudenteAssociato(Studente studente,
      String tutorInterno) {
    TypedQuery<ProgettoFormativo> query = entityManager
        .createNamedQuery("ProgettoFormativo.findByStudenteAssociato", ProgettoFormativo.class);
    query.setParameter("studente", studente);
    query.setParameter("tutorinterno", tutorInterno);

    if (query.getResultList().isEmpty()) {
      return null;
    } else {
      return query.getResultList().get(0);
    }
  }
}
