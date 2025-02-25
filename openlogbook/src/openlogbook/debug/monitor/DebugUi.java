package openlogbook.debug.monitor ;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import openlogbook.debug.Debug;

/**
 * The DebugUi class provides the central mechanism for debug support.  There should
 * exist a single instance of the DebugUi class in any application.  The DebugUi class
 * provides the debug monitor window and all associated functionality.
 */
public class DebugUi {

   /**
    * Creates a new Debug
    *
    *
    */
   private DebugUi() {
   }

   //**********
   // Public methods
   //**********

   /**
    * Turns off redirection of print output to a file.
    *
    */
   public static void clearPrintFile() {
      _printToFile = false ;
   }

   /**
    * Sets name of file for redirection of print output. The redirection is turned on
    * only if the file exists.
    *
    * @param printFilename  name of file to redirect print output to
    *
    */
   public static void setPrintFile(String printFilename) {
      _printFilename = printFilename ;
      File file = new File(_printFilename) ;
      if (file.exists()) {
         _printToFile = true ;
      }
   }

   /**
    * Returns an instance variable for this singleton object.
    *
    * @return an instance variable for this singleton object
    */
   public static DebugUi getInstance() {
      return _instance ;
   }


   /**
    * Redirects System.out to the debug monitor window .
    *
    */
   public static void grabSystemOut() {
      PrintStream printStream = new PrintStream(new DebugOutputStream()) ;
      System.setOut(printStream) ;
      System.setErr(printStream) ;
   }

   /**
    * Open a monitor window for debugging.
    *
    *
    */
   public static void openMonitorWindow() {
      if (_monitorFrame == null) {
         _monitorFrame = new MonitorFrame(_terminalModel) ;
         _monitorFrame.setTitle(_monitorTitle) ;
         _monitorFrame.setSize(600, 400) ;
      }
      _monitorFrame.setVisible(true) ;
   }


   /**
    * Open debug window for debugging.
    *
    *
    */
   public static void openDebugWindow() {
      if (_debugFrame == null) {
         _debugFrame = new DebugFrame(Debug.getObjectDebugTree()) ;
         _debugFrame.setTitle(_debugTitle) ;
      }
      _debugFrame.setVisible(true) ;
   }


   /**
    * Print a String to the system output window. If print-to-file is enabled
    * also send the string to the file.
    *
    * @param s string to be printed
    *
    */
   public static void print(String s) {
      if (_printToFile) {
         printToFile(s) ;
      }
      try {
         _terminalModel.put(s) ;
      } catch (Exception e) {
         // Ignoring the exception here, because if exception traces are redirected
         // to our monitor window, we may get into a loop.
      }
   }

   /**
    * Sets the title of the debug window.
    *
    * @param title new title for the debug window
    *
    */
   public static void setDebugTitle(String title) {
      _debugTitle = title ;
      if (_debugFrame != null) {
         _debugFrame.setTitle(title) ;
      }
   }


   /**
    * Sets the title of the monitor window.
    *
    * @param title new title for the monitor window
    *
    */
   public static void setMonitorTitle(String title) {
      _monitorTitle = title ;
      if (_monitorFrame != null) {
         _monitorFrame.setTitle(title) ;
      }
   }

   /**
    * Save any preferences/properties.
    */
   public static void savePreferences() {
      if (_debugFrame != null) {
         _debugFrame.savePreferences();
      }
   }


   //**********
   // Package methods
   //**********

   /**
    * Print an array of characters (as bytes)  to the system output window. If print-to-file is enabled
    * also send the string to the file. This is used only by the output stream class for taking over
    * System.out and System.err.
    *
    * @param b characters to be printed
    *
    */
   static void print(byte[] b) {
      if (_printToFile) {
         printToFile(b) ;
      }
      try {
         _terminalModel.put(b) ;
      } catch (Exception e) {
         // Ignoring the exception here, because if exception traces are redirected
         // to our monitor window, we may get into a loop.
      }
   }

   /**
    * Prints a string to the print log file, if logging is enabled.
    *
    * @param s  a string to be printed to the print log file
    *
    */
   static void printToFile(String s) {
      try {
         PrintWriter printWriter = new PrintWriter(new FileOutputStream(_printFilename, true)) ;
         printWriter.print(s) ;
         printWriter.flush() ;
         printWriter.close() ;
      } catch (Exception e) {
         // Ignoring the exception here, because if exception traces are redirected
         // to our monitor window, we may get into a loop.
      }
   }


   /**
    * Prints a string (as an array of bytes) to the print log file, if logging is enabled.
    *
    * @param b  bytes to be printed to the print log file
    *
    */
   static void printToFile(byte[] b) {
      try {
         FileOutputStream fileOutputStream = new FileOutputStream(_printFilename, true) ;
         fileOutputStream.write(b) ;
         fileOutputStream.close() ;
      } catch (Exception e) {
         // Ignoring the exception here, because if exception traces are redirected
         // to our monitor window, we may get into a loop.
      }
   }

   //**********
   // Class attributes and constants
   //**********

   /**
    * The debug print writer.
    */
   private static DebugUi           _instance = new DebugUi() ;
   private static MonitorFrame      _monitorFrame ;
   private static DebugFrame        _debugFrame = null ;
   private static boolean           _printToFile = false ;
   private static String            _printFilename = "" ;
   private static TerminalModel     _terminalModel = new TerminalModel(250, 128) ;
   private static String            _monitorTitle = "Management Services Monitor" ;
   private static String            _debugTitle = "Object Inspector" ;

}

// End of DebugUi.java

