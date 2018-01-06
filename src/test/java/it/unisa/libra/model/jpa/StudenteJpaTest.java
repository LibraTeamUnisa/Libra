package it.unisa.libra.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.unisa.libra.bean.Azienda;
import it.unisa.libra.bean.ProgettoFormativo;
import it.unisa.libra.bean.Studente;

public class StudenteJpaTest extends GenericJpaTest {

  private static StudenteJpa studenteJpa;
  private static GruppoJpa gruppoJpa;
  private static UtenteJpa utenteJpa;
  private static AziendaJpa aziendaJpa;
  private static ProgettoFormativoJpa pfJpa;

  @Before
  public void setUp() throws Exception {
    studenteJpa = new StudenteJpa();
    studenteJpa.entityManager = em;
    gruppoJpa = new GruppoJpa();
    gruppoJpa.entityManager = em;
    utenteJpa = new UtenteJpa();
    utenteJpa.entityManager = em;
    aziendaJpa = new AziendaJpa();
    aziendaJpa.entityManager = em;
    pfJpa = new ProgettoFormativoJpa();
    pfJpa.entityManager = em;
  }

  @Test
  public void findAllSurnameOrdered() {
    Studente studente = new Studente();
    studente.setCognome("A");
    studente.setUtenteEmail("uno@studenti.unisa.it");
    studenteJpa.persist(studente);
    Studente studente2 = new Studente();
    studente2.setCognome("B");
    studente2.setUtenteEmail("due@studenti.unisa.it");
    studenteJpa.persist(studente2);
    List<Studente> lista = studenteJpa.listaOrdinataPerCognome();
    assertNotNull(lista);
    assertTrue(!lista.isEmpty());
    Studente uno = lista.get(0);
    Studente due = lista.get(1);
    int confronto = uno.getCognome().compareTo(due.getCognome());
    assertTrue(confronto < 0);
  }

  @Test
  public void countByAziendaTest() {
    Azienda azienda = new Azienda();
    azienda.setUtenteEmail("emailAziendaprova1@prova.libra.it");
    aziendaJpa.persist(azienda);
    Studente studente = new Studente();
    studente.setUtenteEmail("emailStudente1");
    studente.setCognome("C");
    studenteJpa.persist(studente);
    ProgettoFormativo pf = new ProgettoFormativo();
    pf.setAzienda(azienda);
    pf.setStudente(studente);
    pfJpa.persist(pf);
    long expected = 1;
    long result = studenteJpa.countByAzienda(azienda);
    assertEquals(expected, result);
  }
}
