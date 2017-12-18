package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the permesso database table.
 * 
 */
@Entity
@NamedQuery(name = "Permesso.findAll", query = "SELECT p FROM Permesso p")
public class Permesso implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String tipo;

  private boolean abilitazione;

  // bi-directional many-to-many association to Gruppo
  @ManyToMany
  @JoinTable(name = "possesso", joinColumns = {@JoinColumn(name = "tipo")},
      inverseJoinColumns = {@JoinColumn(name = "ruolo")})
  private List<Gruppo> gruppi;

  public Permesso() {}

  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public boolean getAbilitazione() {
    return this.abilitazione;
  }

  public void setAbilitazione(boolean abilitazione) {
    this.abilitazione = abilitazione;
  }

  public List<Gruppo> getGruppi() {
    return this.gruppi;
  }

  public void setGruppi(List<Gruppo> gruppi) {
    this.gruppi = gruppi;
  }

}
