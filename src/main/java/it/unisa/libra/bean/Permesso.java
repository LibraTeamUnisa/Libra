package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Permesso.findAll", query = "SELECT p FROM Permesso p")
public class Permesso implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String tipo;

  private boolean abilitazione;

  @ManyToMany
  @JoinTable(name = "possesso", joinColumns = {@JoinColumn(name = "tipo")},
      inverseJoinColumns = {@JoinColumn(name = "ruolo")})
  private List<Gruppo> gruppos;

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

  public List<Gruppo> getGruppos() {
    return this.gruppos;
  }

  public void setGruppos(List<Gruppo> gruppos) {
    this.gruppos = gruppos;
  }

}
