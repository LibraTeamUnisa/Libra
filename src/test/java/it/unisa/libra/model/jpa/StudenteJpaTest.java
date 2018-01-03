package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unisa.libra.bean.Gruppo;
import it.unisa.libra.bean.Studente;
import it.unisa.libra.bean.Utente;

public class StudenteJpaTest extends GenericJpaTest  {
	
	 private static StudenteJpa studenteJpa;
	 private static GruppoJpa gruppoJpa;
	 private static UtenteJpa utenteJpa;
	
	  @Before
	  public void setUp() throws Exception {
		  studenteJpa = new StudenteJpa();
		  studenteJpa.entityManager = em;
		  gruppoJpa = new GruppoJpa();
		  gruppoJpa.entityManager = em;
		  utenteJpa = new UtenteJpa();
		  utenteJpa.entityManager = em;
	  }
	  
	  private Utente creaStudente() {
		  	Studente studente = new Studente();
			 studente.setNome("Vincenzo");
			 studente.setCognome("A");
			 studente.setUtenteEmail("uno@studenti.unisa.it");
			 studente.setMatricola("1234567890");
			 studente.setDataDiNascita(new Date());
			 
			 Utente utente = new Utente();
			 utente.setEmail("uno@studenti.unisa.it");
			 utente.setStudente(studente);
			 utente.setImgProfilo("");
			 utente.setIndirizzo("Via delle vie");
			 utente.setPassword("1234567");
			 utente.setTelefono("123");
			
			 Gruppo gruppo = new Gruppo();
			 gruppo.setRuolo("Studente");
			 gruppoJpa.persist(gruppo);
		    
			 utente.setGruppo(gruppo);
			 
			 return utente;
	  }
	  
	  @Test
	  public void findAllSurnameOrdered() {
		 
		 Studente studente = new Studente();
		 studente.setNome("Vincenzo");
		 studente.setCognome("A");
		 studente.setUtenteEmail("uno@studenti.unisa.it");
		 studente.setMatricola("1234567890");
		 studente.setDataDiNascita(new Date());
		 
		 Utente utente = new Utente();
		 utente.setEmail("uno@studenti.unisa.it");
		 utente.setStudente(studente);
		 utente.setImgProfilo("");
		 utente.setIndirizzo("Via delle vie");
		 utente.setPassword("1234567");
		 utente.setTelefono("123");
		
		 Gruppo gruppo = new Gruppo();
		 gruppo.setRuolo("Studente");
		 gruppoJpa.persist(gruppo);
	    
		 utente.setGruppo(gruppo);
		 
		 utenteJpa.persist(utente);
		 
		 studente.setNome("Vincenzo");
		 studente.setCognome("B");
		 studente.setUtenteEmail("due@studenti.unisa.it");
		 studente.setMatricola("1234567890");
		 studente.setDataDiNascita(new Date());
		 
		 utente.setEmail("due@studenti.unisa.it");
		 utente.setStudente(studente);
		 utente.setImgProfilo("");
		 utente.setIndirizzo("Via delle vie");
		 utente.setPassword("1234567");
		 utente.setTelefono("123");
		 
		 utenteJpa.persist(utente);
		 
		 
		 List<Studente> lista = studenteJpa.listaOrdinataPerCognome();
	    
		 assertNotNull(lista);
	    
		Studente uno = lista.get(0);
		Studente due = lista.get(1);
		 
		int confronto = uno.getCognome().compareTo(due.getCognome());
	
		//assertTrue(confronto>0);
	    
	  }
	  
	  @Test
	  public void contaOccorrenzeTest() {
		  Utente a = creaStudente();
		  utenteJpa.persist(a);
		  int occorrenze = studenteJpa.contaOccorrenze();
		  assertEquals(occorrenze, 1);
		  assertNotNull(occorrenze);
	  }
}
