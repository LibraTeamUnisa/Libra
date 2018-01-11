package it.unisa.libra.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the progettoformativo database table.
 * 
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "ProgettoFormativo.findAll", query = "SELECT p FROM ProgettoFormativo p"),
    @NamedQuery(name = "ProgettoFormativo.findByStudente",
        query = "SELECT p FROM ProgettoFormativo p WHERE p.studente=:studente ORDER BY p.id DESC"),
    @NamedQuery(name = "ProgettoFormativo.findByStudenteAssociato",
        query = "SELECT p FROM ProgettoFormativo p WHERE p.studente=:studente AND p.tutorInterno.utenteEmail=:tutorinterno ORDER BY p.id DESC"),
    @NamedQuery(name = "ProgettoFormativo.findByAziendaNome",
        query = "SELECT p FROM ProgettoFormativo p WHERE p.azienda.nome=:nomeAzienda"),

    @NamedQuery(name = "ProgettoFormativo.countAllCompletati",
        query = "SELECT count(p) FROM ProgettoFormativo p WHERE p.dataFine IS NOT NULL"),
    @NamedQuery(name = "ProgettoFormativo.findStudenteByAzienda",
        query = "SELECT p.studente FROM ProgettoFormativo p WHERE p.azienda=:azienda"),
    @NamedQuery(name = "ProgettoFormativo.findInOrdineCronologico",
        query = "SELECT p FROM ProgettoFormativo p WHERE p.dataInizio BETWEEN :anno AND :anno2 ORDER BY p.dataInizio DESC"),
    @NamedQuery(name = "ProgettoFormativo.findUltimeDieci",
        query = "SELECT a.nome, s.cognome, s.nome, t.cognome, t.nome, p.ambito, p.dataInizio FROM ProgettoFormativo p JOIN p.azienda a JOIN p.studente s JOIN p.tutorInterno t WHERE p.dataInizio <= :today AND (p.dataFine >=:today OR p.dataFine = null) ORDER BY p.dataInizio DESC"),     
    @NamedQuery(name = "ProgettoFormativo.findPFtutorInterno",
    query = "SELECT s.utenteEmail,a.nome, p.ambito, s.cognome, s.nome, p.dataInvio FROM ProgettoFormativo p JOIN p.studente s JOIN p.azienda a JOIN p.tutorInterno t WHERE t.utenteEmail = :tutorEmail AND p.stato=2"),
    @NamedQuery(name = "ProgettoFormativo.count",
        query = "SELECT COUNT(p) FROM ProgettoFormativo p"),
    @NamedQuery(name = "ProgettoFormativo.countAttivi",
    query = "SELECT COUNT(p) FROM ProgettoFormativo p  WHERE p.stato = 4"),
    @NamedQuery(name = "ProgettoFormativo.countStudentiAssociati",
    query = "SELECT COUNT(DISTINCT s.utenteEmail) FROM ProgettoFormativo p JOIN p.studente s JOIN p.tutorInterno t WHERE t.utenteEmail=:tutorEmail"),
    @NamedQuery(name = "ProgettoFormativo.countByTutorInterno",
    query = "SELECT COUNT(p) FROM ProgettoFormativo p JOIN p.tutorInterno t WHERE t.utenteEmail = :tutorEmail")})

public class ProgettoFormativo implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String ambito;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataFine;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataInizio;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataInvio;

  private String documento;

  private String motivazioneRifiuto;

  private String note;

  private int periodoReport;

  private int stato;

  // bi-directional many-to-one association to Feedback
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "progettoFormativo")
  private List<Feedback> feedbacks;

  // bi-directional one-to-one association to Notifica
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "progettoFormativo")
  private Notifica notifica;

  // bi-directional many-to-one association to Azienda
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "aziendaEmail")
  private Azienda azienda;

  // bi-directional many-to-one association to Studente
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "studenteEmail")
  private Studente studente;

  // bi-directional many-to-one association to TutorInterno
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tutorInternoEmail")
  private TutorInterno tutorInterno;

  // bi-directional many-to-one association to Report
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "progettoFormativo")
  private List<Report> reports;

  public ProgettoFormativo() {}
  
  public ProgettoFormativo(String ambito, int stato, String emailStudente, String nome, String cognome, String matricola) {
    this.ambito = ambito;
    this.stato = stato;
    this.studente = new Studente();
    this.studente.setUtenteEmail(emailStudente);
    this.studente.setNome(nome);
    this.studente.setCognome(cognome);
    this.studente.setMatricola(matricola);
  }

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

  public Date getDataInvio() {
    return this.dataInvio;
  }

  public void setDataInvio(Date dataInvio) {
    this.dataInvio = dataInvio;
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
    feedback.setProgettoFormativo(this);

    return feedback;
  }

  public Feedback removeFeedback(Feedback feedback) {
    getFeedbacks().remove(feedback);
    feedback.setProgettoFormativo(null);

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

  public TutorInterno getTutorInterno() {
    return this.tutorInterno;
  }

  public void setTutorInterno(TutorInterno tutorInterno) {
    this.tutorInterno = tutorInterno;
  }

  public List<Report> getReports() {
    return this.reports;
  }

  public void setReports(List<Report> reports) {
    this.reports = reports;
  }

  public Report addReport(Report report) {
    getReports().add(report);
    report.setProgettoFormativo(this);

    return report;
  }

  public Report removeReport(Report report) {
    getReports().remove(report);
    report.setProgettoFormativo(null);

    return report;
  }

}
