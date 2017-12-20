package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;
import it.unisa.libra.bean.Studente;

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
