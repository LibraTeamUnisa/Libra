package it.unisa.libra.util;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class CheckUtilsTest 
{
  private static final String RIGHT_EMAIL   = "m.vitale88@studenti.unisa.it";
  private static final String WRONG_EMAIL_1 = "stefanocirillo";
  private static final String WRONG_EMAIL_2 = "stefano cirillo";
  
  private static final String RIGHT_NUMBER = "089464781";
  private static final String WRONG_NUMBER = "1234567";
  
  private static final String RIGHT_DATE   = "18/12/2017";
  private static final String WRONG_DATE   = "123AB";
  
  private static final String RIGHT_STRING = "12345";
  private static final String WRONG_STRING = "     ";
  
  @Test
  public void testEmailChecking() 
  {
    assertEquals(CheckUtils.checkEmail(RIGHT_EMAIL),true);
    assertEquals(CheckUtils.checkEmail(WRONG_EMAIL_1),false);
    assertEquals(CheckUtils.checkEmail(WRONG_EMAIL_2),false);
    assertEquals(CheckUtils.checkEmail(null),false);
  }
  
  @Test
  public void testTelephoneChecking()
  {
    assertEquals(CheckUtils.checkTelephone(RIGHT_NUMBER),true);
    assertEquals(CheckUtils.checkTelephone(WRONG_NUMBER),false);
    assertEquals(CheckUtils.checkTelephone(null),false);
  }
  
  @Test
  public void testDateChecking()
  {
    assertNotEquals(CheckUtils.checkDate(RIGHT_DATE),null);
    assertEquals(CheckUtils.checkDate(WRONG_DATE),null);
    assertEquals(CheckUtils.checkDate(null),null);
  }
  
  @Test
  public void testEmptinessChecking()
  {
    assertEquals(CheckUtils.checkEmptiness(RIGHT_STRING),true);
    assertEquals(CheckUtils.checkEmptiness(WRONG_STRING),false);
    assertEquals(CheckUtils.checkEmptiness(null),false);
  }
}
