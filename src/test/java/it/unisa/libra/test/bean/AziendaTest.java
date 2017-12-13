package it.unisa.libra.test.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unisa.libra.bean.Azienda;

public class AziendaTest {

	@Test
	public void example() {
		Azienda a = new Azienda();
		String ragioneSociale = "Esempio";
		a.setRagioneSociale(ragioneSociale);
		assertEquals(a.getRagioneSociale(), ragioneSociale);
	}
}
