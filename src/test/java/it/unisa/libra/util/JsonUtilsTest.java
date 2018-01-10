package it.unisa.libra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Map;
import org.junit.Test;
import com.google.gson.JsonSyntaxException;
import it.unisa.libra.util.JsonUtils;

public class JsonUtilsTest {

  @Test
  public void parseTest() {
    String toParse = "{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}";
    Map<String, String> map = JsonUtils.parseOrariApertura(toParse);

    assertNotNull(map);
    assertEquals(map.get("LUN"), "9.30-11.30");
    assertEquals(map.get("MER"), "9.30-11.30 14.30-18.30");
  }

  @Test(expected = JsonSyntaxException.class)
  public void invalidJsonTest() {
    String toParse = "{]]|[[}";
    JsonUtils.parseOrariApertura(toParse);
  }
}
