package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Utente;

public class AziendaJpaTest extends GenericJpaTest {
	
	private static AziendaJpa aziendaJpa;
	
	@BeforeClass
	public static void setUp() {
		aziendaJpa = new AziendaJpa();
		aziendaJpa.entityManager = em;
	}
	
	@Test
	public void persistTest() {
		
		Utente utAzienda = new Utente();
		utAzienda.setEmail("azienda@email.it");
		
		Azienda toPersist = new Azienda();
		toPersist.setUtenteEmail("azienda@email.it");
		toPersist.setNome("RagioneSociale");
		toPersist.setUtente(utAzienda);
		
		aziendaJpa.persist(toPersist);
		
		Azienda toCheck = aziendaJpa.findAll(Azienda.class).get(0);
		
		assertNotNull(toCheck);
		assertEquals(toPersist.getNome(), toCheck.getNome());
	}
}
