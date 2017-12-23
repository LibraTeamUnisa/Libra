package it.unisa.libra.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class TutorEsternoTest {

  @Test
  public void getterSetterTest() {
    TutorEsterno t = new TutorEsterno();
    assertNotNull(t);
    t.setCognome(COGNOME);
    assertEquals(COGNOME, t.getCognome());
    t.setNome(NOME);
    assertEquals(NOME, t.getNome());
    t.setIndirizzo(INDIRIZZO);
    assertEquals(INDIRIZZO, t.getIndirizzo());
    t.setTelefono(TELEFONO);
    assertEquals(TELEFONO, t.getTelefono());
    TutorEsternoPK pk = new TutorEsternoPK();
    pk.setAmbito(AMBITO);
    pk.setAziendaEmail(AZIENDA_EMAIL);
    t.setId(pk);
    assertEquals(pk, t.getId());
    Azienda a = new Azienda();
    t.setAzienda(a);
    assertEquals(a, t.getAzienda());
    a = new Azienda();
    assertNotEquals(a, t.getAzienda());
  }

  private static final String COGNOME = "cognome";
  private static final String NOME = "nome";
  private static final String INDIRIZZO = "via Roma,7";
  private static final String TELEFONO = "1234567890";
  private static final String AMBITO = "ambito";
  private static final String AZIENDA_EMAIL = "azienda@email.it";

}
