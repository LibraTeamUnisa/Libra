package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the azienda database table.
 * 
 */
@Entity
@NamedQuery(name = "Azienda.findAll", query = "SELECT a FROM Azienda a")
public class Azienda implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String nome;

  private String partitaIVA;

  private String sede;

  // bi-directional one-to-one association to Utente
  @OneToOne
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  // bi-directional many-to-one association to Progettoformativo
  @OneToMany(mappedBy = "azienda")
  private List<Progettoformativo> progettiFormativi;

  // bi-directional many-to-many association to Studente
  @ManyToMany(mappedBy = "aziendas")
  private List<Studente> studenti;

  // bi-directional many-to-one association to Tutoresterno
  @OneToMany(mappedBy = "azienda")
  private List<Tutoresterno> tutorEsterni;

  public Azienda() {
	  progettiFormativi = new ArrayList<Progettoformativo>();
	  studenti = new ArrayList<Studente>();
	  tutorEsterni = new ArrayList<Tutoresterno>();
  }

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

  public List<Progettoformativo> getProgettiFormativi() {
    return this.progettiFormativi;
  }

  public void setProgettiFormativi(List<Progettoformativo> progettiFormativi) {
    this.progettiFormativi = progettiFormativi;
  }

  public Progettoformativo addProgettoformativo(Progettoformativo progettoformativo) {
    getProgettiFormativi().add(progettoformativo);
    progettoformativo.setAzienda(this);

    return progettoformativo;
  }

  public Progettoformativo removeProgettoformativo(Progettoformativo progettoformativo) {
    getProgettiFormativi().remove(progettoformativo);
    progettoformativo.setAzienda(null);

    return progettoformativo;
  }

  public List<Studente> getStudentes() {
    return this.studenti;
  }

  public void setStudentes(List<Studente> studenti) {
    this.studenti = studenti;
  }

  public List<Tutoresterno> getTutorEsterni() {
    return this.tutorEsterni;
  }

  public void setTutorEsterni(List<Tutoresterno> tutoresterni) {
    this.tutorEsterni = tutoresterni;
  }

  public Tutoresterno addTutoresterno(Tutoresterno tutoresterno) {
    getTutorEsterni().add(tutoresterno);
    tutoresterno.setAzienda(this);

    return tutoresterno;
  }

  public Tutoresterno removeTutoresterno(Tutoresterno tutoresterno) {
    getTutorEsterni().remove(tutoresterno);
    tutoresterno.setAzienda(null);

    return tutoresterno;
  }

}
