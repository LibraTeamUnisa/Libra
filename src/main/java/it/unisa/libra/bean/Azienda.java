package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Azienda.findAll", query = "SELECT a FROM Azienda a")
public class Azienda implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String nome;

  private String partitaIVA;

  private String sede;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  @OneToMany(mappedBy = "azienda")
  private List<Progettoformativo> progettoformativos;

  @ManyToMany(mappedBy = "aziendas")
  private List<Studente> studentes;

  @OneToMany(mappedBy = "azienda")
  private List<Tutoresterno> tutoresternos;

  public Azienda() {}

  public String getUtenteEmail() {
    return this.utenteEmail;
  }

  public void setUtenteEmail(String utenteEmail) {
    this.utenteEmail = utenteEmail;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getPartitaIVA() {
    return this.partitaIVA;
  }

  public void setPartitaIVA(String partitaIVA) {
    this.partitaIVA = partitaIVA;
  }

  public String getSede() {
    return this.sede;
  }

  public void setSede(String sede) {
    this.sede = sede;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

  public List<Progettoformativo> getProgettoformativos() {
    return this.progettoformativos;
  }

  public void setProgettoformativos(List<Progettoformativo> progettoformativos) {
    this.progettoformativos = progettoformativos;
  }

  public Progettoformativo addProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().add(progettoformativo);
    progettoformativo.setAzienda(this);

    return progettoformativo;
  }

  public Progettoformativo removeProgettoformativo(Progettoformativo progettoformativo) {
    getProgettoformativos().remove(progettoformativo);
    progettoformativo.setAzienda(null);

    return progettoformativo;
  }

  public List<Studente> getStudentes() {
    return this.studentes;
  }

  public void setStudentes(List<Studente> studentes) {
    this.studentes = studentes;
  }

  public List<Tutoresterno> getTutoresternos() {
    return this.tutoresternos;
  }

  public void setTutoresternos(List<Tutoresterno> tutoresternos) {
    this.tutoresternos = tutoresternos;
  }

  public Tutoresterno addTutoresterno(Tutoresterno tutoresterno) {
    getTutoresternos().add(tutoresterno);
    tutoresterno.setAzienda(this);

    return tutoresterno;
  }

  public Tutoresterno removeTutoresterno(Tutoresterno tutoresterno) {
    getTutoresternos().remove(tutoresterno);
    tutoresterno.setAzienda(null);

    return tutoresterno;
  }

}
