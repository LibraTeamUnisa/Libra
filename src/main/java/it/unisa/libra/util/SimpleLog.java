package it.unisa.libra.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleLog {
  /**
   * Instanza la classe logger.
   */
  private static final SimpleLog instance = new SimpleLog();

  // Retrieve the execution directory. Note that this is whereever this process was launched
  public String logname = "Logger";
  protected String env = System.getProperty("user.home");
  private static File logFile;

  /**
   * Restituisce l'istanza del logger.
   * 
   * @return Istanza del logger
   */
  public static SimpleLog getInstance() {
    instance.createLogFile();
    return instance;
  }

  /**
   * Crea il nuovo file di log.
   */
  public void createLogFile() {
    // Determine if a logs directory exists or not.
    File logsFolder = new File(env + "/Libra/" + "logs");
    if (!logsFolder.exists()) {
      // Create the directory
      System.err.println("INFO: Creating new logs directory in " + env + "/Libra/");
      logsFolder.mkdirs();
    }

    // Get the current date and time
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();

    // Create the name of the file from the path and current time
    logname = "Logger" + '-' + dateFormat.format(cal.getTime()) + ".log";
    SimpleLog.logFile = new File(logsFolder, logname);
    try {
      if (logFile.createNewFile()) {
        // New file made
        System.err.println("INFO: Creating new log file");
      }
    } catch (IOException e) {
      System.err.println("ERROR: Cannot create log file");
    }

  }

  private SimpleLog() {
    if (instance != null) {
      // Prevent Reflection
      throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
    }
    this.createLogFile();
  }

  /**
   * Inserisce un nuovo messaggio all'interno il file di log.
   * 
   * @param message Il messaggio da inserire
   */
  public void log(String message) {
    try {
      OutputStream stream = new FileOutputStream(SimpleLog.logFile, true);
      stream.write(message.getBytes());
      System.out.print(message);
      stream.close();
    } catch (IOException e) {
      System.err.println("ERROR: Could not write to log file");
    }
  }

  public void entering(String clazz, String name) {
    log("[ENTERING - " + clazz + "." + name + "]\n");
  }

  public void exiting(String clazz, String name, long duration) {
    log("[EXITING - " + clazz + "." + name + " - DURATION : " + duration + "ms ]\n");
  }
}
