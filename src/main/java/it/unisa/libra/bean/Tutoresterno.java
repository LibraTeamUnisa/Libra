package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Tutoresterno.findAll", query = "SELECT t FROM Tutoresterno t")
public class Tutoresterno implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private TutoresternoPK id;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String indirizzo;

  private String nome;

  private String telefono;

  @ManyToOne
  @JoinColumn(name = "aziendaEmail", insertable = false, updatable = false)
  private Azienda azienda;

  public Tutoresterno() {}

  public TutoresternoPK getId() {
    return this.id;
  }

  public void setId(TutoresternoPK id) {
    this.id = id;
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

  public String getIndirizzo() {
    return this.indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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

}
