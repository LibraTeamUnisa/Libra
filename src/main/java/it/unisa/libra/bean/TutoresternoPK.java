package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class TutoresternoPK implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private String aziendaEmail;

  private String ambito;

  public TutoresternoPK() {}

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
    if (this == other) {
      return true;
    }
    if (!(other instanceof TutoresternoPK)) {
      return false;
    }
    TutoresternoPK castOther = (TutoresternoPK) other;
    return this.aziendaEmail.equals(castOther.aziendaEmail) && this.ambito.equals(castOther.ambito);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.aziendaEmail.hashCode();
    hash = hash * prime + this.ambito.hashCode();

    return hash;
  }
}
