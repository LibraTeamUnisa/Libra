package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the progettoformativo database table.
 * 
 */
@Entity
@NamedQuery(name = "Progettoformativo.findAll", query = "SELECT p FROM Progettoformativo p")
public class Progettoformativo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private int id;

  private String ambito;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataFine;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataInizio;

  private String documento;

  private String motivazioneRifiuto;

  private String note;

  private int periodoReport;

  private int stato;

  // bi-directional many-to-one association to Feedback
  @OneToMany(mappedBy = "progettoformativo")
  private List<Feedback> feedbacks;

  // bi-directional one-to-one association to Notifica
  @OneToOne(mappedBy = "progettoformativo")
  private Notifica notifica;

  // bi-directional many-to-one association to Azienda
  @ManyToOne
  @JoinColumn(name = "aziendaEmail")
  private Azienda azienda;

  // bi-directional many-to-one association to Studente
  @ManyToOne
  @JoinColumn(name = "studenteEmail")
  private Studente studente;

  // bi-directional many-to-one association to Tutorinterno
  @ManyToOne
  @JoinColumn(name = "tutorInternoEmail")
  private Tutorinterno tutorinterno;

  // bi-directional many-to-one association to Report
  @OneToMany(mappedBy = "progettoformativo")
  private List<Report> reports;

  public Progettoformativo() {}

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAmbito() {
    return this.ambito;
  }

  public void setAmbito(String ambito) {
    this.ambito = ambito;
  }

  public Date getDataFine() {
    return this.dataFine;
  }

  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }

  public Date getDataInizio() {
    return this.dataInizio;
  }

  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  public String getDocumento() {
    return this.documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public String getMotivazioneRifiuto() {
    return this.motivazioneRifiuto;
  }

  public void setMotivazioneRifiuto(String motivazioneRifiuto) {
    this.motivazioneRifiuto = motivazioneRifiuto;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getPeriodoReport() {
    return this.periodoReport;
  }

  public void setPeriodoReport(int periodoReport) {
    this.periodoReport = periodoReport;
  }

  public int getStato() {
    return this.stato;
  }

  public void setStato(int stato) {
    this.stato = stato;
  }

  public List<Feedback> getFeedbacks() {
    return this.feedbacks;
  }

  public void setFeedbacks(List<Feedback> feedbacks) {
    this.feedbacks = feedbacks;
  }

  public Feedback addFeedback(Feedback feedback) {
    getFeedbacks().add(feedback);
    feedback.setProgettoformativo(this);

    return feedback;
  }

  public Feedback removeFeedback(Feedback feedback) {
    getFeedbacks().remove(feedback);
    feedback.setProgettoformativo(null);

    return feedback;
  }

  public Notifica getNotifica() {
    return this.notifica;
  }

  public void setNotifica(Notifica notifica) {
    this.notifica = notifica;
  }

  public Azienda getAzienda() {
    return this.azienda;
  }

  public void setAzienda(Azienda azienda) {
    this.azienda = azienda;
  }

  public Studente getStudente() {
    return this.studente;
  }

  public void setStudente(Studente studente) {
    this.studente = studente;
  }

  public Tutorinterno getTutorinterno() {
    return this.tutorinterno;
  }

  public void setTutorinterno(Tutorinterno tutorinterno) {
    this.tutorinterno = tutorinterno;
  }

  public List<Report> getReports() {
    return this.reports;
  }

  public void setReports(List<Report> reports) {
    this.reports = reports;
  }

  public Report addReport(Report report) {
    getReports().add(report);
    report.setProgettoformativo(this);

    return report;
  }

  public Report removeReport(Report report) {
    getReports().remove(report);
    report.setProgettoformativo(null);

    return report;
  }

}
