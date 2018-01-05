package it.unisa.libra.util;

import java.util.Map;
import com.google.gson.Gson;

public class JsonUtils {

  // EX. {"LUN":"9.30-11.30","MER":"9.30-11.30 14.30-18.30"}
  public static Map<String, String> parseOrariApertura(String json) {
    return new Gson().fromJson(json, Map.class);
  }

  public static String parseMapToJson(Map<String, String> map) {
    return new Gson().toJson(map);
  }

}
