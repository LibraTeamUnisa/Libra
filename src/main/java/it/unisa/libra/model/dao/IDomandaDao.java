package it.unisa.libra.model.dao;

import it.unisa.libra.bean.Domanda;
import java.util.List;

public interface IDomandaDao extends IGenericDao<Domanda, Integer> {

  List<Domanda> findByType(String type);
}
