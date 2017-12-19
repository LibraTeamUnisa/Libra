package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.model.dao.IProgettoFormativoDao;

@Stateless
public class ProgettoFormativoJpa extends GenericJpa<ProgettoFormativo, Integer>
    implements IProgettoFormativoDao {
}
