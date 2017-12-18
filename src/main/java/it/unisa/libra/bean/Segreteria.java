package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the segreteria database table.
 * 
 */
@Entity
@NamedQuery(name="Segreteria.findAll", query="SELECT s FROM Segreteria s")
public class Segreteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String utenteEmail;

	private String giorniDiRicevimento;

	//bi-directional one-to-one association to Utente
	@OneToOne
	@JoinColumn(name="utenteEmail")
	private Utente utente;

	public Segreteria() {
	}

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