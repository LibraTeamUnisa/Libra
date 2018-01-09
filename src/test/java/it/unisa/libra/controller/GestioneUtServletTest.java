
package it.unisa.libra.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import it.unisa.libra.bean.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GestioneUtServletTest {

	// Attributi della classe
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraTestPU");
	private EntityManager em;
	private EntityTransaction tx;

	@Before // Prima di testare i metodi
	public void initEntityManager() throws Exception {
		em = emf.createEntityManager(); // Istanzio EntityManager utilizzando EntityManagerFactory
		tx = em.getTransaction(); // Stessa cosa con EntityTransaction
	}

	@After // Dopo aver testato i metodi
	public void closeEntityManager() throws Exception {
		if (em != null)
			em.close();
	}

	@Test
	public void shouldCreateUser() throws Exception { // Dovrebbe creare un utente

		// Crea un istanza di Utente

		Gruppo gruppo = new Gruppo();
		gruppo.setRuolo("Tester");

		Utente utente = new Utente();
		utente.setEmail("lucaizzo@gmail.com");
		utente.setPassword("luca");
		utente.setIndirizzo("Via delle pelurie 71");
		utente.setTelefono("3366654321");
		utente.setImgProfilo("alessandro.jpg");
		utente.setGruppo(gruppo);

		em.getTransaction().begin();
		em.persist(gruppo);
		em.persist(utente);
		em.getTransaction().commit();

		// Controlla se i valori sono != null

		assertNotNull("ruolo should not be null", gruppo.getRuolo());
		assertNotNull("email should not be null", utente.getEmail());
		assertNotNull("password should not be null", utente.getPassword());
		assertNotNull("telefono should not be null", utente.getTelefono());
		assertNotNull("indirizzo should not be null", utente.getIndirizzo());
		assertNotNull("Img Profilo should not be null", utente.getImgProfilo());
		assertNotNull("gruppo should not be null", utente.getGruppo());

		// Cerca l'utente all'interno del DB

		Utente ut = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = '" + utente.getEmail() + "'")
				.getSingleResult();
		assertEquals("lucaizzo@gmail.com", utente.getEmail());

	}

	public void shouldCreatePresident() throws Exception { // Dovrebbe creare un presidente

		// Crea un istanza di Presidente

		Gruppo gruppo = new Gruppo();
		gruppo.setRuolo("Tester");

		Utente utente = new Utente();
		utente.setEmail("lucaizzo@gmail.com");
		utente.setPassword("luca");
		utente.setIndirizzo("Via delle pelurie 71");
		utente.setTelefono("3366654321");
		utente.setImgProfilo("alessandro.jpg");
		utente.setGruppo(gruppo);

		Presidente pres = new Presidente();
		String data = "bday";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result = df.parse(data);
		pres.setDataDiNascita(result);
		pres.setCognome("Izzo");
		pres.setLinkSito("etherium@crypto.it");
		pres.setNome("Luca");
		pres.setUfficio("ufficio");
		pres.setGiorniDiRicevimento("giovedi");
		pres.setUtenteEmail("lucaizzo@gmail.com");

		em.getTransaction().begin();
		em.persist(gruppo);
		em.persist(utente);
		em.persist(pres);
		em.getTransaction().commit();

		// Controlla se i valori sono != null

		assertNotNull("ruolo should not be null", gruppo.getRuolo());
		assertNotNull("email should not be null", utente.getEmail());
		assertNotNull("password should not be null", utente.getPassword());
		assertNotNull("telefono should not be null", utente.getTelefono());
		assertNotNull("indirizzo should not be null", utente.getIndirizzo());
		assertNotNull("Img Profilo should not be null", utente.getImgProfilo());
		assertNotNull("gruppo should not be null", utente.getGruppo());

		assertNotNull("utenteEmail should not be null", pres.getUtenteEmail());
		assertNotNull("dob should not be null", pres.getDataDiNascita());
		assertNotNull("nome should not be null", pres.getNome());
		assertNotNull("Cognome should not be null", pres.getCognome());
		assertNotNull("Linksito should not be null", pres.getLinkSito());
		assertNotNull("ufficio should not be null", pres.getUfficio());
		assertNotNull("gdr should not be null", pres.getGiorniDiRicevimento());

		// Cerca l'utente all'interno del DB

		Utente ut = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = " + utente.getEmail())
				.getSingleResult();
		assertEquals("lucaizzo@gmail.com", utente.getEmail());

		// Cerca il presidente all'interno del DB

		Presidente pr = (Presidente) em.createQuery("SELECT p FROM Presidente p WHERE p.cognome = " + pres.getCognome())
				.getSingleResult();
		assertEquals("Izzo", pres.getCognome());

	}

	public void shouldCreateTutorEsterno() throws Exception { // Dovrebbe creare un TutorEsterno

		// Crea un istanza di TutorEsterno

		Gruppo gruppo = new Gruppo();
		gruppo.setRuolo("Tester");

		Utente utente = new Utente();
		utente.setEmail("lucaizzo@gmail.com");
		utente.setPassword("luca");
		utente.setIndirizzo("Via delle pelurie 71");
		utente.setTelefono("3366654321");
		utente.setImgProfilo("alessandro.jpg");
		utente.setGruppo(gruppo);

		TutorEsterno tut = new TutorEsterno();
		String data = "bday";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result = df.parse(data);
		tut.setDataDiNascita(result);
		tut.setCognome("Izzo");
		tut.setNome("Luca");

		em.getTransaction().begin();
		em.persist(gruppo);
		em.persist(utente);
		em.persist(tut);
		em.getTransaction().commit();

		// Controlla se i valori sono != null

		assertNotNull("ruolo should not be null", gruppo.getRuolo());
		assertNotNull("email should not be null", utente.getEmail());
		assertNotNull("password should not be null", utente.getPassword());
		assertNotNull("telefono should not be null", utente.getTelefono());
		assertNotNull("indirizzo should not be null", utente.getIndirizzo());
		assertNotNull("Img Profilo should not be null", utente.getImgProfilo());
		assertNotNull("gruppo should not be null", utente.getGruppo());

		assertNotNull("dob should not be null", tut.getDataDiNascita());
		assertNotNull("nome should not be null", tut.getNome());
		assertNotNull("Cognome should not be null", tut.getCognome());

		// Cerca l'utente all'interno del DB

		Utente ut = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = " + utente.getEmail())
				.getSingleResult();
		assertEquals("lucaizzo@gmail.com", utente.getEmail());

		// Cerca il TutorEsterno all'interno del DB

		TutorEsterno tutu = (TutorEsterno) em
				.createQuery("SELECT t FROM TutorEsterno t WHERE t.cognome = " + tut.getCognome()).getSingleResult();
		assertEquals("Izzo", tut.getCognome());

	}

	public void shouldCreateAzienda() throws Exception { // Dovrebbe creare un'Azienda

		// Crea un istanza di Azienda

		Gruppo gruppo = new Gruppo();
		gruppo.setRuolo("Tester");

		Utente utente = new Utente();
		utente.setEmail("lucaizzo@gmail.com");
		utente.setPassword("luca");
		utente.setIndirizzo("Via delle pelurie 71");
		utente.setTelefono("3366654321");
		utente.setImgProfilo("alessandro.jpg");
		utente.setGruppo(gruppo);

		Azienda azd = new Azienda();

		azd.setNome("Luca");
		azd.setPartitaIVA("889123123210");
		azd.setUtenteEmail("lucaizzo@gmail.com");
		azd.setSede("website");

		em.getTransaction().begin();
		em.persist(gruppo);
		em.persist(utente);
		em.persist(azd);
		em.getTransaction().commit();

		// Controlla se i valori sono != null

		assertNotNull("ruolo should not be null", gruppo.getRuolo());
		assertNotNull("email should not be null", utente.getEmail());
		assertNotNull("password should not be null", utente.getPassword());
		assertNotNull("telefono should not be null", utente.getTelefono());
		assertNotNull("indirizzo should not be null", utente.getIndirizzo());
		assertNotNull("Img Profilo should not be null", utente.getImgProfilo());
		assertNotNull("gruppo should not be null", utente.getGruppo());

		assertNotNull("iva should not be null", azd.getPartitaIVA());
		assertNotNull("nome should not be null", azd.getNome());
		assertNotNull("utenteEmail should not be null", azd.getUtenteEmail());
		assertNotNull("sito should not be null", azd.getSede());

		// Cerca l'utente all'interno del DB

		Utente ut = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.email = " + utente.getEmail())
				.getSingleResult();
		assertEquals("lucaizzo@gmail.com", utente.getEmail());

		// Cerca l'azienda all'interno del DB

		Azienda az = (Azienda) em.createQuery("SELECT a FROM Azienda a WHERE a.nome = " + azd.getNome())
				.getSingleResult();
		assertEquals("Izzo", azd.getNome());

	}

}
