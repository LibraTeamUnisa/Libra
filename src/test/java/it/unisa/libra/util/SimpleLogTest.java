package it.unisa.libra.util;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleLogTest {

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() throws FileNotFoundException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    String logName = "Logger" + '-' + dateFormat.format(cal.getTime()) + ".log";
    assertNotNull(SimpleLog.getInstance());
    assertEquals(logName, "Logger" + '-' + dateFormat.format(cal.getTime()) + ".log");
  }
}
