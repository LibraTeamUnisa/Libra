package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the gruppo database table.
 * 
 */
@Entity
@NamedQuery(name = "Gruppo.findAll", query = "SELECT g FROM Gruppo g")
public class Gruppo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String ruolo;

  // bi-directional many-to-many association to Permesso
  @ManyToMany(mappedBy = "gruppi")
  private List<Permesso> permessi;

  // bi-directional many-to-one association to Utente
  @OneToMany(mappedBy = "gruppo", cascade = {CascadeType.ALL})
  private List<Utente> utenti;

  public Gruppo() {}

  public String getRuolo() {
    return this.ruolo;
  }

  public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
  }

  public List<Permesso> getPermessi() {
    return this.permessi;
  }

  public void setPermessi(List<Permesso> permessi) {
    this.permessi = permessi;
  }

  public List<Utente> getUtenti() {
    return this.utenti;
  }

  public void setUtenti(List<Utente> utenti) {
    this.utenti = utenti;
  }

  public Utente addUtenti(Utente utenti) {
    getUtenti().add(utenti);
    utenti.setGruppo(this);

    return utenti;
  }

  public Utente removeUtenti(Utente utenti) {
    getUtenti().remove(utenti);
    utenti.setGruppo(null);

    return utenti;
  }

}
