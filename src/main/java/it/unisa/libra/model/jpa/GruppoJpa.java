package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.model.dao.IGruppoDao;

@Stateless
public class GruppoJpa extends GenericJpa<Gruppo, String> implements IGruppoDao {
}
