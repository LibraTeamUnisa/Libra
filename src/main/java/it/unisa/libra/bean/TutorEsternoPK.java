package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tutoresterno database table.
 * 
 */
@Embeddable
public class TutorEsternoPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private String aziendaEmail;

  private String ambito;

  public TutorEsternoPK() {}

  public String getAziendaEmail() {
    return this.aziendaEmail;
  }

  public void setAziendaEmail(String aziendaEmail) {
    this.aziendaEmail = aziendaEmail;
  }

  public String getAmbito() {
    return this.ambito;
  }

  public void setAmbito(String ambito) {
    this.ambito = ambito;
  }

  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (other.getClass() != this.getClass()) {
      return false;
    }
    TutorEsternoPK castOther = (TutorEsternoPK) other;
    if (this.aziendaEmail != null) {
      if (this.ambito != null) {
        return this.aziendaEmail.equals(castOther.aziendaEmail)
            && this.ambito.equals(castOther.ambito);
      } else
        return this.aziendaEmail.equals(castOther.aziendaEmail) && (castOther.ambito == null);
    } else {
      if (this.ambito != null) {
        return (castOther.aziendaEmail == null) && this.ambito.equals(castOther.ambito);
      } else
        return (castOther.aziendaEmail == null) && (castOther.ambito == null);
    }
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.aziendaEmail.hashCode();
    hash = hash * prime + this.ambito.hashCode();

    return hash;
  }
}
