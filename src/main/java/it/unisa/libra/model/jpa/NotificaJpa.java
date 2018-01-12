package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Notifica;
import it.unisa.libra.model.dao.INotificaDao;
import it.unisa.libra.util.Loggable;
import javax.ejb.Stateless;

@Stateless
@Loggable
public class NotificaJpa extends GenericJpa<Notifica, Integer> implements INotificaDao {
}
