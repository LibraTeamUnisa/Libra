package it.unisa.libra.model.dao;

import java.util.List;

import it.unisa.libra.bean.Domanda;

public interface IDomandaDao extends IGenericDao<Domanda, Integer> {
	
	List<Domanda> findByType(String type);
}
