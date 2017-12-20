package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.Utente;

public class AziendaJpaTest extends GenericJpaTest {

  private static AziendaJpa jpa;

  @BeforeClass
  public static void setUp() {
    jpa = new AziendaJpa();
    jpa.entityManager = em;
  }
  
  @Test
  public void persistTest() {

    Azienda Azienda = createObject();
    jpa.persist(createObject());
    Azienda toCheck = jpa.findAll(Azienda.class).get(0);

    assertNotNull(toCheck);
    assertEquals(Azienda.getUtenteEmail(), toCheck.getUtenteEmail());
  }
  
  /*@Test
  public void findByNameSuccessTest(){
    Azienda a1 = createObject2();
    
    Azienda toCheck = jpa.findByName("test2");
    System.out.println(toCheck.toString());
    assertNotNull(toCheck);
    assertEquals(a1.getUtenteEmail(), toCheck.getUtenteEmail());
    assertEquals(a1.getNome(), toCheck.getNome());
  }
  */
  private Azienda createObject() {

    Utente utente = new Utente();
    utente.setEmail("test@email.it");

    Azienda toPersist = new Azienda();
    toPersist.setNome("test");
    toPersist.setUtenteEmail("test@email.it");
    toPersist.setUtente(utente);
    
    return toPersist;

  }
  
  private Azienda createObject2() {
    //PERSIST DI TUTTI GLI ELEMENTI CHE MI SERVONO
    /*Utente utente = new Utente();
    utente.setEmail("prova@gmail.com");
    Azienda azienda = new Azienda();
    azienda.setUtenteEmail("prova@gmail.com");
    azienda.setUtente(utente);
    azienda.setNome("prova");
    TutorEsternoPK pk = new TutorEsternoPK();
    pk.setAziendaEmail("prova@gmail.com");
    TutorEsterno tutor = new TutorEsterno();
    tutor.setAzienda(azienda);
    
    jpaT.persist(tutor);
    jpaU.persist(utente);
    jpa.persist(azienda);
    return azienda;
     */ return null;
  }
}
