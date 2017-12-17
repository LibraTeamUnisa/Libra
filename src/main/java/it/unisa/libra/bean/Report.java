package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
public class Report implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Temporal(TemporalType.TIMESTAMP)
  private Date data;

  private String testo;

  // bi-directional many-to-one association to Progettoformativo
  @ManyToOne
  @JoinColumn(name = "progettoFormativoID")
  private Progettoformativo progettoformativo;

  public Report() {}

  public Date getData() {
    return this.data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getTesto() {
    return this.testo;
  }

  public void setTesto(String testo) {
    this.testo = testo;
  }

  public Progettoformativo getProgettoformativo() {
    return this.progettoformativo;
  }

  public void setProgettoformativo(Progettoformativo progettoformativo) {
    this.progettoformativo = progettoformativo;
  }

}
