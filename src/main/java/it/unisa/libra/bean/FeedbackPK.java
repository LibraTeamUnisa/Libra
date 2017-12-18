package it.unisa.libra.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the feedback database table.
 * 
 */
@Embeddable
public class FeedbackPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int progettoFormativoID;

	@Column(insertable=false, updatable=false)
	private int domandaID;

	public FeedbackPK() {
	}
	public int getProgettoFormativoID() {
		return this.progettoFormativoID;
	}
	public void setProgettoFormativoID(int progettoFormativoID) {
		this.progettoFormativoID = progettoFormativoID;
	}
	public int getDomandaID() {
		return this.domandaID;
	}
	public void setDomandaID(int domandaID) {
		this.domandaID = domandaID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FeedbackPK)) {
			return false;
		}
		FeedbackPK castOther = (FeedbackPK)other;
		return 
			(this.progettoFormativoID == castOther.progettoFormativoID)
			&& (this.domandaID == castOther.domandaID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.progettoFormativoID;
		hash = hash * prime + this.domandaID;
		
		return hash;
	}
}