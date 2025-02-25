package openlogbook.debug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import openlogbook.util.AppProperties;
import openlogbook.util.StringUtility;

/**
 * The Debug class provides the central mechanism for debug support.  There should
 * exist a single instance of the Debug class in any application.
 */
public class Debug {

   /**
    * Creates a new Debug.
    *
    */
   private Debug() {
   }

   //**********
   // Public methods
   //**********

   /**
    * Registers an object for viewing in the object viewer. Objects are displayed in
    * a tree format.
    *
    * @param parent  the parent debug object.
    * @param o       object to be added
    *
    */
   public static void addObject(Debugable parent, Debugable o) {
      try {
         if (o != null) {
            _objectDebugTree.addObject(parent, o) ;
         }
      } catch (Exception e) {
         logException(e) ;
      }
   }

   /**
    * Returns an instance variable for this singleton object.
    *
    * @return an instance variable for this singleton object
    */
   public static Debug getInstance() {
      return _instance ;
   }


   /**
    * Logs an exception object. A stack trace is printed to the
    * debug monitor window.
    *
    * @param throwable   an Throwable object to be logged
    *
    */
   public static void logException(Throwable throwable) {
      try {
         if (_exceptionPrintWriter != null) {
            throwable.printStackTrace(_exceptionPrintWriter) ;
            _exceptionPrintWriter.flush() ;
         }
         synchronized (_exceptionLog) {
            if (_exceptionLog.size() == EXCEPTION_LOG_CAPACITY) {
               _exceptionLog.remove(0) ;
            }
            _exceptionLog.add(new ExceptionLogEntry(throwable)) ;
         }
      } catch (Exception e) {
         // Last resort - if we got an exception logging an exception, well....
         e.printStackTrace() ;
      }
   }


   /**
    * De-Registers an object for viewing in the object viewer.
    *
    * @param debugable   object to be removed
    *
    */
   public static void removeObject(Debugable debugable) {
      try {
         if (debugable != null) {
            _objectDebugTree.removeObject(debugable) ;
         }
      } catch (Exception e) {
         logException(e) ;
      }
   }


   /**
    * Saves contents of debug objects to a zip file.
    *
    * @param saveFile   file to save debug data to
    *
    * @throws IOException if an I/O Error occurs
    */
   public static void saveDebugData(File saveFile) throws IOException {
      ZipOutputStream zipOut = null ;
      try {
         zipOut = new ZipOutputStream(new FileOutputStream(saveFile)) ;
         saveNode(zipOut, (TreeNode)_objectDebugTree.getRoot(), "", new ArrayList<String>()) ;
         saveExceptionLog(zipOut) ;
      } catch (RuntimeException e) {
         logException(e) ;
      } finally {
         if (zipOut != null) {
            try {
               zipOut.close() ;
            } catch (Exception e) {
               logException(e) ;  // only helps on the next data collection
            }
         }
      }
   }

   /**
    * Gets the ObjectDebugTree object.
    *
    * @return the ObjectDebugTree object
    */
   public static ObjectDebugTree getObjectDebugTree() {
      return _objectDebugTree ;
   }

   /**
    * Sets a PrintWriter to which logged exceptions will be written to.
    * This is normally done during debug mode so that exceptions can be seen at
    * a console when they occur.
    *
    * @param printWriter  a PrintWriter to write exception stack traces to
    *
    */
   public static void setExceptionPrintWriter(PrintWriter printWriter) {
      _exceptionPrintWriter = printWriter ;
   }

   /**
    * If monitoring is turned on, output the given data collection monitor message.
    *
    * @param message the message to print out
    */
   public static void monitorDataCollect(String message) {
      if (_monitorPrintWriter == null) {
         if (AppProperties.getBooleanProperty(MONITOR_PROPERTY, false)) {
            File monitorFile = new File("openlogbookDumpMonitor.txt");
            try {
               FileWriter monitorStream = new FileWriter(monitorFile, true);
               _monitorPrintWriter = new PrintWriter(monitorStream, true);
            } catch (IOException ioExc) {
               _monitorPrintWriter = new PrintWriter(System.out, true);
            }
         } else {
            return;
         }
      }
      _monitorPrintWriter.println(message);
   }


   //**********
   // Private methods
   //**********

   /**
    * Saves contents of the exception log.
    *
    * @param zipOut   a zip output stream to write to
    *
    * @throws IOException if an I/O Error occurs
    */
   private static void saveExceptionLog(ZipOutputStream zipOut) throws IOException {
      Debug.monitorDataCollect("Outputting exceptionLog.txt");
      ZipEntry zipEntry = null ;
      try {
         zipEntry = new ZipEntry("exceptionLog.txt") ;
         zipOut.putNextEntry(zipEntry) ;
         PrintWriter printWriter = new PrintWriter(zipOut) ;
         synchronized (_exceptionLog) {
            for (int idx = 0; idx < _exceptionLog.size(); idx++) {
               ExceptionLogEntry entry = (ExceptionLogEntry)_exceptionLog.get(idx) ;
               printWriter.println("*** " + StringUtility.formatSimpleDate(entry.getDate()) + " ***") ;
               entry.getThrowable().printStackTrace(printWriter) ;
               printWriter.println("") ;
            }
         }
         printWriter.flush() ;
         zipOut.closeEntry() ;
         Debug.monitorDataCollect("Finished exceptionLog.txt");
      } catch (ZipException e) {
         throw new IOException(e.getMessage()) ;
      } catch (RuntimeException e) {
         logException(e) ;
      } finally {
         if (zipOut != null) {
            try {
               zipOut.closeEntry() ;
            } catch (Exception e) {
               logException(e) ;
            }
         }
      }
   }


   /**
    * Saves a node in the debug tree to a data collection zip file.
    *
    * @param zipOut      zip output stream to save to
    * @param node        node in a tree to save
    * @param directory   directory name in the zip file to use
    * @param fileNames   a list of names that have already been used in the data collection
    *
    * @throws IOException if an I/O Error occurs
    */
   private static void saveNode(ZipOutputStream zipOut, TreeNode node, String directory, List<String> fileNames)
   throws IOException {
      // Save this node
      if (!(node instanceof DefaultMutableTreeNode)) {
         return ;
      }
      Object nodeObject = ((DefaultMutableTreeNode)node).getUserObject() ;

      if (nodeObject == null) {
         return ;
      }

      String nodeName ;
      if (directory.length() == 0) {  // Don't want to prepend a slash on the first node
         nodeName = nodeObject.toString() ;
      } else {
         // PKZIP wants a forward slash
         // Strip out any ':' characters; zip will think we're specifying a drive letter
         nodeName = directory + "/" + nodeObject.toString().replace(':', '-') ;
      }
      if (nodeObject instanceof Debugable) {
         Debugable debugable = (Debugable)nodeObject ;
         if (debugable == null) {
            return ;
         }

         Debug.monitorDataCollect("Starting " + nodeName + " " + System.identityHashCode(debugable));
         try {
            saveTable(zipOut, debugable.getDebugTable(), nodeName, fileNames) ;
         } catch (RuntimeException e) {
            logException(new Exception(e.getClass() + " examining " + debugable.getClass(), e)) ;
         }
         if (debugable instanceof FullDebugable) {
            Debug.monitorDataCollect("   Full debug data for " + nodeName);
            DebugableData debugableData = ((FullDebugable)debugable).getDebugableData() ;
            Vector<HistoryLog> historyLogs = debugableData.getHistoryLogs() ;
            for (int i = 0; historyLogs != null && i < historyLogs.size(); i++) {
               HistoryLog historyLog = (HistoryLog)historyLogs.elementAt(i) ;
               Debug.monitorDataCollect("   History log [" + historyLog.getName() + "] for " + nodeName);
               try {
                  DebugTable logContents = historyLog.getContents() ;
                  saveTable(zipOut, logContents, nodeName + "/" + historyLog.getName(), fileNames) ;
                  if (historyLog.serializeOnDataCollection()) {
                     saveHistoryLog(zipOut, logContents, nodeName + "/" + historyLog.getName(), fileNames) ;
                  }
               } catch (RuntimeException e) {
                  logException(new Exception(e.getClass() + " examining " + debugable.getClass(), e)) ;
               }
            }
         }
         Debug.monitorDataCollect("Finished " + nodeName);
      }

      // Save this node's children
      for (int i = 0; i < node.getChildCount(); i++) {
         saveNode(zipOut, node.getChildAt(i), nodeName, fileNames) ;
      }
   }

   /**
    * Saves a table to the debug zip file.
    *
    * @param zipOut       zip outputstream to write to
    * @param debugTable   table to save
    * @param name         filename to save table under
    * @param fileNames    a list of names that have already been used in the data collection
    *
    * @throws IOException if an I/O Error occurs
    */
   private static void saveTable(ZipOutputStream zipOut, DebugTable debugTable, String name, List<String> fileNames)
   throws IOException {
      ZipEntry zipEntry = null ;
      try {
         zipEntry = new ZipEntry(createUniqueName(name, ".txt", fileNames)) ;

         zipOut.putNextEntry(zipEntry) ;
         PrintWriter printWriter = new PrintWriter(zipOut) ;
         DebugTablePrinter.printTable(debugTable, printWriter) ;
         printWriter.flush() ;
         zipOut.closeEntry() ;
      } finally {
         if (zipOut != null) {
            zipOut.closeEntry() ;
         }
      }
   }

   /**
    * Create a name that is unique within the existingNames. If the combined prefix and suffix
    * are not unique, then a number is inserted between them to create a unique name. It is
    * valid for the prefix or suffix to be an empty string but neither of them can be null.
    *
    * @param namePrefix    the first part of the name
    * @param nameSuffix    the second part of the name
    * @param existingNames the names that have already been used
    *
    * @return a new name that is unique within the existingNames
    */
   private static String createUniqueName(String namePrefix, String nameSuffix, List<String> existingNames) {
      String name = namePrefix + nameSuffix;
      int count = 1;
      while (existingNames.contains(name)) {
         name = namePrefix + count + nameSuffix ;
         count++;
      }
      existingNames.add(name);
      return name;
   }


   /**
    * Saves a history to the debug zip file.
    *
    * @param zipOut         zip outputstream to write to
    * @param logContents    the log contents
    * @param name           filename to save table under
    * @param fileNames      a list of names that have already been used in the data collection
    *
    * @throws IOException if an I/O Error occurs
    */
   private static void saveHistoryLog(ZipOutputStream zipOut, DebugTable logContents, String name, List<String> fileNames)
   throws IOException {
      ZipEntry zipEntry = null ;
      try {
         zipEntry = new ZipEntry(createUniqueName(name, ".jso", fileNames)) ;
         zipOut.putNextEntry(zipEntry) ;
         try {
            ObjectOutputStream objectStream = new ObjectOutputStream(zipOut) ;
            objectStream.writeObject(logContents) ;
         } catch (NotSerializableException e) {
            logException(e) ;
         } catch (RuntimeException e) {
            logException(e) ;
         }
         zipOut.closeEntry() ;
      } finally {
         if (zipOut != null) {
            zipOut.closeEntry() ;
         }
      }
   }


   //**********
   // Class attributes and constants
   //**********

   private static final String      MONITOR_PROPERTY = "openlogbook.monitorDataCollect";
   private static PrintWriter       _monitorPrintWriter ;

   private static final int         EXCEPTION_LOG_CAPACITY = 100 ;

   private static Debug             _instance = new Debug() ;
   private static Vector<ExceptionLogEntry>  _exceptionLog = new Vector<ExceptionLogEntry>(EXCEPTION_LOG_CAPACITY) ;
   private static ObjectDebugTree   _objectDebugTree = new ObjectDebugTree() ;
   private static PrintWriter       _exceptionPrintWriter = null ;

}


// End of Debug.java

