package it.unisa.libra.model.jpa;

import javax.ejb.Stateless;
import it.unisa.libra.bean.Report;
import it.unisa.libra.bean.ReportPK;
import it.unisa.libra.model.dao.IReportDao;

@Stateless
public class ReportJpa extends GenericJpa<Report, ReportPK> implements IReportDao {
}
