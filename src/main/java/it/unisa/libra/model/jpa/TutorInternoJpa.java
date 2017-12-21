package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.TutorInterno;
import it.unisa.libra.model.dao.ITutorInternoDao;

@Stateless
public class TutorInternoJpa extends GenericJpa<TutorInterno, String> implements ITutorInternoDao {}
