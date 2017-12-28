package it.unisa.libra.model.dao;

import java.util.List;

import it.unisa.libra.bean.Studente;

public interface IStudenteDao extends IGenericDao<Studente, String> {
	public List<Studente> listaOrdinataPerCognome();
}
