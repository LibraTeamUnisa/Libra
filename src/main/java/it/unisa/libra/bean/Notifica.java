package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the notifica database table.
 * 
 */
@Entity
@NamedQuery(name = "Notifica.findAll", query = "SELECT n FROM Notifica n")
public class Notifica implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataRicezione;

  private String descrizione;

  private int progettoFormativoID;

  private byte visualizzata;

  // bi-directional one-to-one association to Progettoformativo
  @OneToOne
  @JoinColumn(name = "id")
  private Progettoformativo progettoformativo;

  // bi-directional many-to-one association to Utente
  @ManyToOne
  @JoinColumn(name = "utenteEmail")
  private Utente utente;

  public Notifica() {}

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDataRicezione() {
    return this.dataRicezione;
  }

  public void setDataRicezione(Date dataRicezione) {
    this.dataRicezione = dataRicezione;
  }

  public String getDescrizione() {
    return this.descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public int getProgettoFormativoID() {
    return this.progettoFormativoID;
  }

  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public byte getVisualizzata() {
    return this.visualizzata;
  }

  public void setVisualizzata(byte visualizzata) {
    this.visualizzata = visualizzata;
  }

  public Progettoformativo getProgettoformativo() {
    return this.progettoformativo;
  }

  public void setProgettoformativo(Progettoformativo progettoformativo) {
    this.progettoformativo = progettoformativo;
  }

  public Utente getUtente() {
    return this.utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

}
