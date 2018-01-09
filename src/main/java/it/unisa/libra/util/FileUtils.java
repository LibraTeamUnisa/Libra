package it.unisa.libra.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

  public static final String PATH_IMG_PROFILO = "/img/profile/";
  public static final String PATH_PDF_PROGETTOF = "/pdf/progetto/";
  public static final String BASE_PATH = System.getProperty("user.home");

  public static boolean saveBase64ToFile(String path, String fileName, String base64) {
    boolean saved = false;

    byte[] data = base64.getBytes();
    try (OutputStream stream = new FileOutputStream(BASE_PATH + path + "/" + fileName)) {
      stream.write(data);
      saved = true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return saved;
  }

  public static String readBase64FromFile(String path, String fileName) {
    String base64 = null;
    try {
      base64 = new String(Files.readAllBytes(Paths.get(BASE_PATH + path + "/" + fileName)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return base64;
  }

}
