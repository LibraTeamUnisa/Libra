package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.TutorEsterno;
import it.unisa.libra.bean.TutorEsternoPK;
import it.unisa.libra.model.dao.ITutorEsternoDao;

@Stateless
public class TutorEsternoJpa extends GenericJpa<TutorEsterno, TutorEsternoPK>
    implements ITutorEsternoDao {
}
