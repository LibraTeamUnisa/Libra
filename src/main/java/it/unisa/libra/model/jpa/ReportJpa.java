package it.unisa.libra.model.jpa;

import it.unisa.libra.bean.Report;
import it.unisa.libra.bean.ReportPK;
import it.unisa.libra.model.dao.IReportDao;
import javax.ejb.Stateless;

@Stateless
public class ReportJpa extends GenericJpa<Report, ReportPK> implements IReportDao {
}
