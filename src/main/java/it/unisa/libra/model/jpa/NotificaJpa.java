package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Notifica;
import it.unisa.libra.model.dao.INotificaDao;
import javax.ejb.Stateless;

@Stateless
public class NotificaJpa extends GenericJpa<Notifica, Integer> implements INotificaDao {
}
