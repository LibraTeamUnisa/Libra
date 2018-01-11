package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class UtenteJpa extends GenericJpa<Utente, String> implements IUtenteDao {

  /**
   * Permette di recuperare dal database l'utente registrato al sistema con mail e password.
   * 
   * @inheritDoc
   */
  @Override
  public Utente getUtente(String mail, String pwd) {
    TypedQuery<Utente> query = entityManager.createNamedQuery("getUtente", Utente.class);
    query.setParameter("mail", mail);
    query.setParameter("pwd", pwd);
    Utente u;
    try {
      u = query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
    return u;
  }
}
