package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity

@NamedQueries({@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),



    @NamedQuery(name = "Report.countAll", query = "SELECT count(r) FROM Report r")})

public class Report implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private ReportPK id;

  private String testo;

  // bi-directional many-to-one association to ProgettoFormativo
  @ManyToOne
  @JoinColumn(name = "progettoFormativoID", insertable = false, updatable = false)
  private ProgettoFormativo progettoFormativo;

  public Report() {}

  public ReportPK getId() {
    return this.id;
  }

  public void setId(ReportPK id) {
    this.id = id;
  }

  public String getTesto() {
    return this.testo;
  }

  public void setTesto(String testo) {
    this.testo = testo;
  }

  public ProgettoFormativo getProgettoFormativo() {
    return this.progettoFormativo;
  }

  public void setProgettoFormativo(ProgettoFormativo progettoFormativo) {
    this.progettoFormativo = progettoFormativo;
  }

}
