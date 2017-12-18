package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String email;

	private String imgProfilo;

	private String indirizzo;

	private String password;

	private String telefono;

	//bi-directional one-to-one association to Azienda
	@OneToOne(mappedBy="utente")
	private Azienda azienda;

	//bi-directional many-to-one association to Notifica
	@OneToMany(mappedBy="utente")
	private List<Notifica> notificas;

	//bi-directional one-to-one association to Presidente
	@OneToOne(mappedBy="utente")
	private Presidente presidente;

	//bi-directional one-to-one association to Segreteria
	@OneToOne(mappedBy="utente")
	private Segreteria segreteria;

	//bi-directional one-to-one association to Studente
	@OneToOne(mappedBy="utente")
	private Studente studente;

	//bi-directional one-to-one association to Tutorinterno
	@OneToOne(mappedBy="utente")
	private Tutorinterno tutorinterno;

	//bi-directional many-to-one association to Gruppo
	@ManyToOne
	@JoinColumn(name="ruolo")
	private Gruppo gruppo;

	public Utente() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgProfilo() {
		return this.imgProfilo;
	}

	public void setImgProfilo(String imgProfilo) {
		this.imgProfilo = imgProfilo;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Notifica> getNotificas() {
		return this.notificas;
	}

	public void setNotificas(List<Notifica> notificas) {
		this.notificas = notificas;
	}

	public Notifica addNotifica(Notifica notifica) {
		getNotificas().add(notifica);
		notifica.setUtente(this);

		return notifica;
	}

	public Notifica removeNotifica(Notifica notifica) {
		getNotificas().remove(notifica);
		notifica.setUtente(null);

		return notifica;
	}

	public Presidente getPresidente() {
		return this.presidente;
	}

	public void setPresidente(Presidente presidente) {
		this.presidente = presidente;
	}

	public Segreteria getSegreteria() {
		return this.segreteria;
	}

	public void setSegreteria(Segreteria segreteria) {
		this.segreteria = segreteria;
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

	public Gruppo getGruppo() {
		return this.gruppo;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}

}