package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tutorinterno database table.
 * 
 */
@Entity

@NamedQuery(name = "TutorInterno.findAll", query = "SELECT t FROM TutorInterno t")

public class TutorInterno implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String linkSito;

  private String nome;

  // bi-directional many-to-one association to ProgettoFormativo
  @OneToMany(mappedBy = "tutorInterno",fetch=FetchType.LAZY)
  private List<ProgettoFormativo> progettiFormativi;

  // bi-directional one-to-one association to Utente
  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public TutorInterno() {}

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

  public List<ProgettoFormativo> getProgettiFormativi() {
    return this.progettiFormativi;
  }

  public void setProgettiFormativi(List<ProgettoFormativo> progettiFormativi) {
    this.progettiFormativi = progettiFormativi;
  }

  public ProgettoFormativo addProgettiFormativi(ProgettoFormativo progettiFormativi) {
    getProgettiFormativi().add(progettiFormativi);
    progettiFormativi.setTutorInterno(this);

    return progettiFormativi;
  }

  public ProgettoFormativo removeProgettiFormativi(ProgettoFormativo progettiFormativi) {
    getProgettiFormativi().remove(progettiFormativi);
    progettiFormativi.setTutorInterno(null);

    return progettiFormativi;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
