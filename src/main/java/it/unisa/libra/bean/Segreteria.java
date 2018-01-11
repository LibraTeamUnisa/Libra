package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * The persistent class for the segreteria database table.
 * 
 */
@Entity

@NamedQuery(name = "Segreteria.findAll", query = "SELECT s FROM Segreteria s")
public class Segreteria implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String utenteEmail;

  private String giorniDiRicevimento;


  // bi-directional one-to-one association to Utente
  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
  @MapsId
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public Segreteria() {}

  public String getUtenteEmail() {
    return this.utenteEmail;
  }

  public void setUtenteEmail(String utenteEmail) {
    this.utenteEmail = utenteEmail;
  }

  public String getGiorniDiRicevimento() {
    return this.giorniDiRicevimento;
  }

  public void setGiorniDiRicevimento(String giorniDiRicevimento) {
    this.giorniDiRicevimento = giorniDiRicevimento;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
