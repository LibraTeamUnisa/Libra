package it.unisa.libra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CheckUtilsTest {
  private static final String RIGHT_EMAIL = "m.vitale88@studenti.unisa.it";
  private static final String WRONG_EMAIL_1 = "stefanocirillo";
  private static final String WRONG_EMAIL_2 = "stefano cirillo";

  private static final String RIGHT_NUMBER = "089464781";
  private static final String WRONG_NUMBER = "1234567";

  private static final String RIGHT_DATE = "18/12/2017";
  private static final String WRONG_DATE = "123AB";

  private static final String RIGHT_STRING = "12345";
  private static final String WRONG_STRING = "     ";

  @Test
  public void testEmailChecking() {
    assertEquals(CheckUtils.checkEmail(RIGHT_EMAIL), true);
    assertEquals(CheckUtils.checkEmail(WRONG_EMAIL_1), false);
    assertEquals(CheckUtils.checkEmail(WRONG_EMAIL_2), false);
    assertEquals(CheckUtils.checkEmail(null), false);
  }

  @Test
  public void testTelephoneChecking() {
    assertEquals(CheckUtils.checkTelephone(RIGHT_NUMBER), true);
    assertEquals(CheckUtils.checkTelephone(WRONG_NUMBER), false);
    assertEquals(CheckUtils.checkTelephone(null), false);
  }

  @Test
  public void testDateChecking() {
    assertNotEquals(CheckUtils.parseDate(RIGHT_DATE), null);
    assertEquals(CheckUtils.parseDate(WRONG_DATE), null);
    assertEquals(CheckUtils.parseDate(null), null);
  }

  @Test
  public void testEmptinessChecking() {
    assertEquals(CheckUtils.checkEmptiness(RIGHT_STRING), true);
    assertEquals(CheckUtils.checkEmptiness(WRONG_STRING), false);
    assertEquals(CheckUtils.checkEmptiness(null), false);
  }

  @Test
  public void nullOrEmptyCollectionTest() {
    Collection<Object> coll = null;
    assertTrue(CheckUtils.isNullOrEmpty(coll));
    coll = new ArrayList<Object>();
    assertTrue(CheckUtils.isNullOrEmpty(coll));
    coll.add("something");
    assertFalse(CheckUtils.isNullOrEmpty(coll));
  }

  @Test
  public void nullOrEmptyMapTest() {
    Map<Object, Object> map = null;
    assertTrue(CheckUtils.isNullOrEmpty(map));
    map = new HashMap<Object, Object>();
    assertTrue(CheckUtils.isNullOrEmpty(map));
    map.put("something", "value");
    assertFalse(CheckUtils.isNullOrEmpty(map));
  }

  @Test
  public void notInFutureDateTest() throws ParseException {
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date data = parser.parse("9999-12-31");
    assertFalse(CheckUtils.notInFuture(data));
    data = parser.parse("1900-01-01");
    assertTrue(CheckUtils.notInFuture(data));
    assertFalse(CheckUtils.notInFuture(null));
  }

  @Test
  public void isMaggiorenneTest() throws ParseException {
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date data = parser.parse("9999-12-31");
    assertFalse(CheckUtils.isMaggiorenne(data));
    data = parser.parse("1900-01-01");
    assertTrue(CheckUtils.isMaggiorenne(data));
    assertFalse(CheckUtils.isMaggiorenne(null));
    data = new Date();
    assertFalse(CheckUtils.isMaggiorenne(data));
  }
}
