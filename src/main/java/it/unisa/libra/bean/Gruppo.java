package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Gruppo.findAll", query = "SELECT g FROM Gruppo g")
public class Gruppo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String ruolo;

  @ManyToMany(mappedBy = "gruppos")
  private List<Permesso> permessos;

  @OneToMany(mappedBy = "gruppo")
  private List<Utente> utentes;

  public Gruppo() {}

  public String getRuolo() {
    return this.ruolo;
  }

  public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
  }

  public List<Permesso> getPermessos() {
    return this.permessos;
  }

  public void setPermessos(List<Permesso> permessos) {
    this.permessos = permessos;
  }

  public List<Utente> getUtentes() {
    return this.utentes;
  }

  public void setUtentes(List<Utente> utentes) {
    this.utentes = utentes;
  }

  public Utente addUtente(Utente utente) {
    getUtentes().add(utente);
    utente.setGruppo(this);

    return utente;
  }

  public Utente removeUtente(Utente utente) {
    getUtentes().remove(utente);
    utente.setGruppo(null);

    return utente;
  }

}
