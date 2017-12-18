package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;

import it.unisa.libra.bean.Utente;
import it.unisa.libra.model.dao.IUtenteDao;

@Stateless
public class UtenteJpa extends GenericJpa<Utente, String> implements IUtenteDao {
}
