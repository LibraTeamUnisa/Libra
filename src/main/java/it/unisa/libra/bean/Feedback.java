package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
	@NamedQuery(name="Feedback.findByType", query="SELECT f FROM Feedback f WHERE f.progettoFormativo.id = :idProgettoFormativo AND f.domanda.tipo= :tipoDomanda ORDER BY f.domanda.id")
})
public class Feedback implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private FeedbackPK id;

  private String valutazione;

  // bi-directional many-to-one association to Domanda
  @ManyToOne
  @JoinColumn(name = "domandaID", insertable = false, updatable = false)
  private Domanda domanda;

  // bi-directional many-to-one association to ProgettoFormativo
  @ManyToOne
  @JoinColumn(name = "progettoFormativoID", insertable = false, updatable = false)
  private ProgettoFormativo progettoFormativo;

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

  public ProgettoFormativo getProgettoFormativo() {
    return this.progettoFormativo;
  }

  public void setProgettoFormativo(ProgettoFormativo progettoFormativo) {
    this.progettoFormativo = progettoFormativo;
  }

}
