package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the studente database table.
 * 
 */
@Entity
@NamedQuery(name = "Studente.findAll", query = "SELECT s FROM Studente s")
public class Studente implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String matricola;

  private String nome;

  // bi-directional many-to-one association to Progettoformativo
  @OneToMany(mappedBy = "studente")
  private List<Progettoformativo> progettoformativos;

  // bi-directional many-to-many association to Azienda
  @ManyToMany
  @JoinTable(name = "preferenza", joinColumns = {@JoinColumn(name = "studenteEmail")},
      inverseJoinColumns = {@JoinColumn(name = "aziendaEmail")})
  private List<Azienda> aziendas;

  // bi-directional one-to-one association to Utente
  @OneToOne
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public Studente() {}

  public String getUtenteEmail() {
    return this.utenteEmail;
  }

  public void setUtenteEmail(String utenteEmail) {
    this.utenteEmail = utenteEmail;
  }

  public String getCognome() {
    return this.cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public Date getDataDiNascita() {
    return this.dataDiNascita;
  }

  public void setDataDiNascita(Date dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  public String getMatricola() {
    return this.matricola;
  }

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Progettoformativo> getProgettoformativos() {
    return this.progettoformativos;
  }

  public void setProgettoformativos(List<Progettoformativo> progettoformativos) {
    this.progettoformativos = progettoformativos;
  }

  public Progettoformativo addProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().add(progettoformativo);
    progettoformativo.setStudente(this);

    return progettoformativo;
  }

  public Progettoformativo removeProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().remove(progettoformativo);
    progettoformativo.setStudente(null);

    return progettoformativo;
  }

  public List<Azienda> getAziendas() {
    return this.aziendas;
  }

  public void setAziendas(List<Azienda> aziendas) {
    this.aziendas = aziendas;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
