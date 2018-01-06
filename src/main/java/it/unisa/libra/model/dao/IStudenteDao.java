package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Studente;
import java.util.List;

public interface IStudenteDao extends IGenericDao<Studente, String> {
  public List<Studente> listaOrdinataPerCognome();

  int contaOccorrenze();
}
