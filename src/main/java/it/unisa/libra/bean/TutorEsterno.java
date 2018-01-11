package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tutoresterno database table.
 * 
 */
@Entity

@NamedQueries({@NamedQuery(name = "TutorEsterno.findAll", query = "SELECT t FROM TutorEsterno t"),
    @NamedQuery(name = "TutorEsterno.findByAziendaNome",
        query = "SELECT t FROM TutorEsterno t WHERE t.azienda.nome=:nomeAzienda")})

public class TutorEsterno implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private TutorEsternoPK id;

  private String cognome;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataDiNascita;

  private String indirizzo;

  private String nome;

  private String telefono;

  // bi-directional many-to-one association to Azienda
  @ManyToOne
  @JoinColumn(name = "aziendaEmail", insertable = false, updatable = false)
  private Azienda azienda;

  public TutorEsterno() {}

  public TutorEsternoPK getId() {
    return this.id;
  }

  public void setId(TutorEsternoPK id) {
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
