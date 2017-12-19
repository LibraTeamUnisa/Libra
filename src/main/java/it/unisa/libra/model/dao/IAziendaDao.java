package it.unisa.libra.model.dao;



import java.util.List;

import it.unisa.libra.bean.Azienda;

public interface IAziendaDao extends IGenericDao<Azienda, String> {
	
	List<Azienda> findAll();
}
