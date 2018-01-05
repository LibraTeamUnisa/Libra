package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the report database table.
 * 
 */
@Embeddable
public class ReportPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date data;

  @Column(insertable = false, updatable = false)
  private int progettoFormativoID;

  public ReportPK() {}

  public java.util.Date getData() {
    return this.data;
  }

  public void setData(java.util.Date data) {
    this.data = data;
  }

  public int getProgettoFormativoID() {
    return this.progettoFormativoID;
  }

  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ReportPK)) {
      return false;
    }
    ReportPK castOther = (ReportPK) other;
    return this.data.equals(castOther.data)
        && (this.progettoFormativoID == castOther.progettoFormativoID);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.data.hashCode();
    hash = hash * prime + this.progettoFormativoID;

    return hash;
  }
}
