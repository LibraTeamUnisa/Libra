package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the presidente database table.
 * 
 */
@Entity
@NamedQuery(name = "Presidente.findAll", query = "SELECT p FROM Presidente p")
public class Presidente implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String giorniDiRicevimento;

  private String linkSito;

  private String nome;

  private String ufficio;

  // bi-directional one-to-one association to Utente
  @OneToOne
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public Presidente() {}

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

  public String getGiorniDiRicevimento() {
    return this.giorniDiRicevimento;
  }

  public void setGiorniDiRicevimento(String giorniDiRicevimento) {
    this.giorniDiRicevimento = giorniDiRicevimento;
  }

  public String getLinkSito() {
    return this.linkSito;
  }

  public void setLinkSito(String linkSito) {
    this.linkSito = linkSito;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUfficio() {
    return this.ufficio;
  }

  public void setUfficio(String ufficio) {
    this.ufficio = ufficio;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
