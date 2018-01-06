package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Notifica;
import it.unisa.libra.model.dao.INotificaDao;

@Stateless
public class NotificaJpa extends GenericJpa<Notifica, Integer> implements INotificaDao {
}
