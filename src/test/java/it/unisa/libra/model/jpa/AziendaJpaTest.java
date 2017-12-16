package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Utente;

public class AziendaJpaTest extends GenericJpaTest {

	private static UtenteJpa utenteJpa;

	@BeforeClass
	public static void setUp() {
		utenteJpa = new UtenteJpa();
		utenteJpa.entityManager = em;
	}

	@Test
	public void persistTest() {

		Utente utAzienda = new Utente();
		utAzienda.setEmail("azienda@email.it");

		Azienda toPersist = new Azienda();
		toPersist.setUtenteEmail("azienda@email.it");
		toPersist.setNome("RagioneSociale");

		utAzienda.setAzienda(toPersist);

		utenteJpa.persist(utAzienda);

		Azienda toCheck = em.find(Azienda.class, "azienda@email.it");

		assertNotNull(toCheck);
		assertEquals(toPersist.getNome(), toCheck.getNome());
	}
}
