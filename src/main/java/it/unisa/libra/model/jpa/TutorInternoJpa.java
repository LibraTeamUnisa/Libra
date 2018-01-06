package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.model.dao.ITutorInternoDao;
import javax.ejb.Stateless;

@Stateless
public class TutorInternoJpa extends GenericJpa<TutorInterno, String> implements ITutorInternoDao {
}
