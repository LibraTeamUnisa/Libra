package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.model.dao.IGruppoDao;
import javax.ejb.Stateless;

@Stateless
public class GruppoJpa extends GenericJpa<Gruppo, String> implements IGruppoDao {
}
