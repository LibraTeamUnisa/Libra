package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
public class Feedback implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private FeedbackPK id;

  private String valutazione;

  // bi-directional many-to-one association to Domanda
  @ManyToOne
  @JoinColumn(name = "domandaID", insertable = false, updatable = false)
  private Domanda domanda;

  // bi-directional many-to-one association to Progettoformativo
  @ManyToOne
  @JoinColumn(name = "progettoFormativoID", insertable = false, updatable = false)
  private Progettoformativo progettoformativo;

  public Feedback() {}

  public FeedbackPK getId() {
    return this.id;
  }

  public void setId(FeedbackPK id) {
    this.id = id;
  }

  public String getValutazione() {
    return this.valutazione;
  }

  public void setValutazione(String valutazione) {
    this.valutazione = valutazione;
  }

  public Domanda getDomanda() {
    return this.domanda;
  }

  public void setDomanda(Domanda domanda) {
    this.domanda = domanda;
  }

  public Progettoformativo getProgettoformativo() {
    return this.progettoformativo;
  }

  public void setProgettoformativo(Progettoformativo progettoformativo) {
    this.progettoformativo = progettoformativo;
  }

}
