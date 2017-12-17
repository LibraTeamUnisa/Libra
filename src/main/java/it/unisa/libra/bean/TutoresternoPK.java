package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tutoresterno database table.
 * 
 */
@Embeddable
public class TutoresternoPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  /** email dell'azienda a cui il tutor esterno è associato. */
  @Column(insertable = false, updatable = false)
  private String aziendaEmail;

  /** l'ambito di cui il tutor esterno è responsabile. */
  private String ambito;

  /** Default constructor. */
  public TutoresternoPK() {}


  /**
   * Restituisce l'email dell'azienda a cui il tutor esterno è associato.
   * 
   * @return l'email dell'azienda
   */
  public String getAziendaEmail() {
    return this.aziendaEmail;
  }

  /**
   * Imposta l'email dell'azienda a cui il tutor esterno è associato.
   * 
   * @param aziendaEmail l'email dell'azienda
   */
  public void setAziendaEmail(String aziendaEmail) {
    this.aziendaEmail = aziendaEmail;
  }

  /**
   * Restituisce l'ambito di cui il tutor esterno è responsabile.
   * 
   * @return l'ambito del tutor esterno
   */
  public String getAmbito() {
    return this.ambito;
  }

  /**
   * Imposta l'ambito di cui il tutor esterno è responsabile.
   * 
   * @param ambito l'ambito del tutor esterno
   */
  public void setAmbito(String ambito) {
    this.ambito = ambito;
  }

  /**
   * Verifica se quest'oggetto è uguale a quello passato come parametro esplicito.
   * 
   * @see Object#equals(java.lang.Object)
   * @return true se other è uguale a this, false altrimenti
   * @param other l'oggetto da confrontare
   */
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (!(other instanceof TutoresternoPK)) {
      return false;
    }
    TutoresternoPK castOther = (TutoresternoPK) other;
    if (this.aziendaEmail != null) {
      if (!this.aziendaEmail.equals(castOther.aziendaEmail)) {
        return false;
      }
    } else if (castOther.aziendaEmail != null) {
      return false;
    }
    if (this.ambito != null) {
      return this.ambito.equals(castOther.ambito);
    }
    return (castOther.ambito == null);
  }

  /**
   * Restituisce un valore hash per quest'oggetto.
   * 
   * @see Object#hashCode(java.lang.Object)
   * @return il valore hash dell'oggetto
   */
  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.aziendaEmail.hashCode();
    hash = hash * prime + this.ambito.hashCode();

    return hash;
  }
}
