package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getUtente",
        query = "SELECT u FROM Utente u WHERE u.email = :mail AND u.password = :pwd"),
    @NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")})
public class Utente implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String email;

  private String imgProfilo;

  private String indirizzo;

  private String password;

  private String telefono;

  // bi-directional one-to-one association to Azienda
  @OneToOne(mappedBy = "utente", cascade = {CascadeType.ALL})
  private Azienda azienda;

  // bi-directional many-to-one association to Notifica
  @OneToMany(mappedBy = "utente")
  private List<Notifica> notifiche;

  // bi-directional one-to-one association to Presidente
  @OneToOne(mappedBy = "utente", cascade = {CascadeType.ALL})
  private Presidente presidente;

  // bi-directional one-to-one association to Segreteria
  @OneToOne(mappedBy = "utente", cascade = {CascadeType.ALL})
  private Segreteria segreteria;

  // bi-directional one-to-one association to Studente
  @OneToOne(mappedBy = "utente", cascade = {CascadeType.ALL})
  private Studente studente;

  // bi-directional one-to-one association to TutorInterno
  @OneToOne(mappedBy = "utente", cascade = {CascadeType.ALL})
  private TutorInterno tutorInterno;

  // bi-directional many-to-one association to Gruppo
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

  public List<Notifica> getNotifiche() {
    return this.notifiche;
  }

  public void setNotifiche(List<Notifica> notifiche) {
    this.notifiche = notifiche;
  }

  public Notifica addNotifiche(Notifica notifiche) {
    getNotifiche().add(notifiche);
    notifiche.setUtente(this);

    return notifiche;
  }

  public Notifica removeNotifiche(Notifica notifiche) {
    getNotifiche().remove(notifiche);
    notifiche.setUtente(null);

    return notifiche;
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

  public TutorInterno getTutorInterno() {
    return this.tutorInterno;
  }

  public void setTutorInterno(TutorInterno tutorInterno) {
    this.tutorInterno = tutorInterno;
  }

  public Gruppo getGruppo() {
    return this.gruppo;
  }

  public void setGruppo(Gruppo gruppo) {
    this.gruppo = gruppo;
  }

}
