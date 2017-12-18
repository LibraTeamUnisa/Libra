package it.unisa.libra.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TutorEsternoPKTest {

  private TutorEsternoPK tepk;

  @Test
  public void getterSetterTest() {
    tepk = new TutorEsternoPK();
    assertNotNull(tepk);
    tepk.setAmbito("ambito");
    assertEquals("ambito", tepk.getAmbito());
    tepk.setAziendaEmail("aziendaEmail");
    assertEquals("aziendaEmail", tepk.getAziendaEmail());
  }

 
  public void equalsTest() {
    tepk = new TutorEsternoPK();
    assertFalse(tepk.equals(null));
    assertTrue(tepk.equals(tepk));
    Object o = new Object();
    assertFalse(tepk.equals(o));
    tepk.setAmbito("ambito");
    tepk.setAziendaEmail(null);
    TutorEsternoPK other = new TutorEsternoPK();
    other.setAmbito("ambito");
    other.setAziendaEmail(null);
    assertTrue(tepk.equals(other));
  }

}
