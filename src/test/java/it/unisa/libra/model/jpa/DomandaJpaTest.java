package it.unisa.libra.model.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unisa.libra.bean.Domanda;

public class DomandaJpaTest extends GenericJpaTest {

	 private static DomandaJpa jpaD;
	
	 @BeforeClass
	 public static void setUp(){
	    jpaD = new DomandaJpa();
	    jpaD.entityManager = em;
	 }
	 
	 
	 @Test
	 public void persistTest() {
		 Domanda d= createObject();
		 jpaD.persist(d);
		 Domanda toCheck= jpaD.findAll(Domanda.class).get(0);
		 assertNotNull(toCheck);
		 assertEquals(d.getId(), toCheck.getId());
	 }
	 
	 
	 
	 
	 @Test
	 public void findByTypeOnSuccessTest() {
		 Domanda obj= createObject();
		 Domanda toCheck= jpaD.findByType("Studente").get(0);
		 assertNotNull(toCheck);
		 assertEquals(obj.getId(), toCheck.getId());
		 
		 
		 
	 }
	 
	 
	 private Domanda createObject() {
		 Domanda d= new Domanda();
		 d.setId(1);
		 d.setTesto("testo prima domanda da provare");
		 d.setTipo("Studente");
		 return d;
	 }
	 
	 
}
