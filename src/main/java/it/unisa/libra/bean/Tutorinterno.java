package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Tutorinterno.findAll", query = "SELECT t FROM Tutorinterno t")
public class Tutorinterno implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String linkSito;

  private String nome;

  @OneToMany(mappedBy = "tutorinterno")
  private List<Progettoformativo> progettoformativos;

  @OneToOne
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public Tutorinterno() {}

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

  public List<Progettoformativo> getProgettoformativos() {
    return this.progettoformativos;
  }

  public void setProgettoformativos(List<Progettoformativo> progettoformativos) {
    this.progettoformativos = progettoformativos;
  }

  public Progettoformativo addProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().add(progettoformativo);
    progettoformativo.setTutorinterno(this);

    return progettoformativo;
  }

  public Progettoformativo removeProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().remove(progettoformativo);
    progettoformativo.setTutorinterno(null);

    return progettoformativo;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
