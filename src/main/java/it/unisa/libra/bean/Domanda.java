package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the domanda database table.
 * 
 */
@Entity
@NamedQuery(name = "Domanda.findAll", query = "SELECT d FROM Domanda d")
public class Domanda implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String testo;

  private String tipo;

  // bi-directional many-to-one association to Feedback
  @OneToMany(mappedBy = "domanda")
  private List<Feedback> feedbacks;

  public Domanda() {}

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTesto() {
    return this.testo;
  }

  public void setTesto(String testo) {
    this.testo = testo;
  }

  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public List<Feedback> getFeedbacks() {
    return this.feedbacks;
  }

  public void setFeedbacks(List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public Feedback addFeedback(Feedback feedback) {
    getFeedbacks().add(feedback);
    feedback.setDomanda(this);

    return feedback;
  }

  public Feedback removeFeedback(Feedback feedback) {
    getFeedbacks().remove(feedback);
    feedback.setDomanda(null);

    return feedback;
  }

}
