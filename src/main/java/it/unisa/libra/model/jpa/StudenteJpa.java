package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Studente; 
import it.unisa.libra.model.dao.IStudenteDao;


import javax.ejb.Stateless;

@Stateless
public class StudenteJpa extends GenericJpa<Studente, String> implements IStudenteDao {}
