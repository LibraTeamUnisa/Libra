package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")
public class Utente implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String email;

  private String imgProfilo;

  private String indirizzo;

  private String password;

  private String telefono;

  @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
  private Azienda azienda;

  @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
  private List<Notifica> notificas;

  @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
  private Presidente presidente;

  @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
  private Segreteria segreteria;

  @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
  private Studente studente;

  @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
  private Tutorinterno tutorinterno;

  @ManyToOne
  @JoinColumn(name = "ruolo")
  private Gruppo gruppo;

  public Utente() {}

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getImgProfilo() {
    return this.imgProfilo;
  }

  public void setImgProfilo(String imgProfilo) {
    this.imgProfilo = imgProfilo;
  }

  public String getIndirizzo() {
    return this.indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Azienda getAzienda() {
    return this.azienda;
  }

  public void setAzienda(Azienda azienda) {
    this.azienda = azienda;
  }

  public List<Notifica> getNotificas() {
    return this.notificas;
  }

  public void setNotificas(List<Notifica> notificas) {
    this.notificas = notificas;
  }

  public Notifica addNotifica(Notifica notifica) {
    getNotificas().add(notifica);
    notifica.setUtente(this);

    return notifica;
  }

  public Notifica removeNotifica(Notifica notifica) {
    getNotificas().remove(notifica);
    notifica.setUtente(null);

    return notifica;
  }

  public Presidente getPresidente() {
    return this.presidente;
  }

  public void setPresidente(Presidente presidente) {
    this.presidente = presidente;
  }

  public Segreteria getSegreteria() {
    return this.segreteria;
  }

  public void setSegreteria(Segreteria segreteria) {
    this.segreteria = segreteria;
  }

  public Studente getStudente() {
    return this.studente;
  }

  public void setStudente(Studente studente) {
    this.studente = studente;
  }

  public Tutorinterno getTutorinterno() {
    return this.tutorinterno;
  }

  public void setTutorinterno(Tutorinterno tutorinterno) {
    this.tutorinterno = tutorinterno;
  }

  public Gruppo getGruppo() {
    return this.gruppo;
  }

  public void setGruppo(Gruppo gruppo) {
    this.gruppo = gruppo;
  }

}
