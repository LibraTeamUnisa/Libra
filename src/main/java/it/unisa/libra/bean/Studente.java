package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the studente database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Studente.findAll", query = "SELECT s FROM Studente s"),
@NamedQuery(name = "Studente.findByEmail", query = "SELECT s FROM Studente s WHERE s.utenteEmail = :email")
})
public class Studente implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String matricola;

  private String nome;

  // bi-directional many-to-many association to Azienda
  @ManyToMany
  @JoinTable(name = "preferenza", joinColumns = {@JoinColumn(name = "studenteEmail")},
      inverseJoinColumns = {@JoinColumn(name = "aziendaEmail")})
  private List<Azienda> aziende;

  // bi-directional many-to-one association to ProgettoFormativo
  @OneToMany(mappedBy = "studente")
  private List<ProgettoFormativo> progettiFormativi;

  // bi-directional one-to-one association to Utente
  @OneToOne(cascade = {CascadeType.ALL})
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

  public List<Azienda> getAziende() {
    return this.aziende;
  }

  public void setAziende(List<Azienda> aziende) {
    this.aziende = aziende;
  }

  public List<ProgettoFormativo> getProgettiFormativi() {
    return this.progettiFormativi;
  }

  public void setProgettiFormativi(List<ProgettoFormativo> progettiFormativi) {
    this.progettiFormativi = progettiFormativi;
  }

  public ProgettoFormativo addProgettiFormativi(ProgettoFormativo progettiFormativi) {
    getProgettiFormativi().add(progettiFormativi);
    progettiFormativi.setStudente(this);

    return progettiFormativi;
  }

  public ProgettoFormativo removeProgettiFormativi(ProgettoFormativo progettiFormativi) {
    getProgettiFormativi().remove(progettiFormativi);
    progettiFormativi.setStudente(null);

    return progettiFormativi;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
