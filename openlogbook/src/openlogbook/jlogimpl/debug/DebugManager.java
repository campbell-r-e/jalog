package openlogbook.jlogimpl.debug ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.DebugableData;
import openlogbook.debug.FullDebugable;
import openlogbook.debug.HistoryLog;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.debug.CallbackExceptionListener;
import openlogbook.debug.InternalEvent;
import openlogbook.debug.InternalEventListener;
import openlogbook.debug.SupportManager;
import openlogbook.jlog.gui.AbstractLogBookFrame;

/**
 * Provides internal debugging support.
 */
public class DebugManager implements SupportManager, FullDebugable {

   /**
    * Creates a new DebugManager.
    */
   private DebugManager() {
      _debugData.addHistoryLog(_internalEventLog) ;
      Debug.addObject(null, this) ;
   }

   //**********
   // Implementation of the FullDebugable interface
   //**********

   /**
    * Gets the debug data object for this object.
    *
    * @return    the debug data object for this object
    */
   public DebugableData getDebugableData() {
      return _debugData ;
   }

   /**
    * Gets the contents of the object in a form suitable for displaying in a table.
    *
    * @return    the contents of the object in a form suitable for displaying in a table
    */
   public DebugTable getDebugTable() {
      final String[] tableColumnNames = {"Name", "Value"} ;
      PropertyDebugTable contents = new PropertyDebugTable("Debug Manager", tableColumnNames);
      contents.addEntry("Nbr Callback Exception Listeners", _callbackExceptionListeners.size()) ;
      contents.addEntry("Nbr Internal Event Listeners", _internalEventListeners.size()) ;
      return contents ;
   }
   
   //**********
   // Implementation of SupportManager interface.
   //**********

   /**
    * Adds a listener for exceptions caught during callbacks.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be added
    *
    * @see #removeCallbackExceptionListener
    */
   public void addCallbackExceptionListener(CallbackExceptionListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_callbackExceptionListeners) {
         if (!_callbackExceptionListeners.contains(listener)) {
            _callbackExceptionListeners.add(listener) ;
         }
      }
   }

   /**
    * Removes a listener for exceptions caught during callbacks.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be removed
    *
    * @see #addCallbackExceptionListener
    */
   public void removeCallbackExceptionListener(CallbackExceptionListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_callbackExceptionListeners) {
         _internalEventListeners.remove(listener) ;
      }
   }

   /**
    * Removes a listener for internal events.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be removed
    *
    * @see #addInternalEventListener
    */
   public void removeInternalEventListener(InternalEventListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_internalEventListeners) {
         _internalEventListeners.remove(listener) ;
      }
   }

   /**
    * Adds a listener for internal events.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be added
    *
    * @see #removeInternalEventListener
    */
   public void addInternalEventListener(InternalEventListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_internalEventListeners) {
         if (!_internalEventListeners.contains(listener)) {
            _internalEventListeners.add(listener) ;
         }
      }
   }

   /**
    * Saves internal state to a file. This file can then be sent back to the API engineering
    * team for analysis.
    * The resulting file is for engineering use only and should not be directly exposed to
    * end users, field support staff, and so on.
    *
    * @param debugFile  specifies a file into which debugging information will be placed
    *
    * @throws IOException if an I/O error occurs writing to the file.
    */
   public void gatherDebugData(File debugFile) throws IOException {
      Debug.monitorDataCollect("Start of gatherDebugData(File)");
      Debug.saveDebugData(debugFile) ;
      Debug.monitorDataCollect("End of gatherDebugData(File)");
   }

   /**
    * Saves internal API state to a file. This file can then be sent back to the API engineering
    * team for analysis. If the managedProduct parameter is not null, the public API for the
    * managed product will be walked through and the results will also be included in the file.
    * The resulting file is for engineering use only and should not be directly exposed to
    * end users, field support staff, and so on.
    *
    * @param logBookFrame    specifies the Log Book Frame of specific interest. This parameter
    *                        may be null, in which case it is ignored.
    * @param debugFile       specifies a file into which debugging information will be placed
    *
    * @throws IOException if an I/O error occurs writing to the file.
    */
   public void gatherDebugData(AbstractLogBookFrame logBookFrame, File debugFile) throws IOException {
      Debug.monitorDataCollect("Start of gatherDebugData(ManagedProduct, File)");
      if (logBookFrame == null) {
         gatherDebugData(debugFile) ;
         return ;
      }
      
      ZipOutputStream zipOut = null ;
      try {
         zipOut = new ZipOutputStream(new FileOutputStream(debugFile)) ;
         
         // Save Debug data
         File directory = debugFile.getParentFile() ;
         if (directory == null) {
            directory = new File(".") ;
         }
         File debugTreeFile = new File(directory, "logbookState.DataCollection") ;
         Debug.saveDebugData(debugTreeFile) ;
         saveFileToZip(zipOut, debugTreeFile) ;
         debugTreeFile.delete() ;

         // Save API results for the specified product
         try {
            saveLogBookApi(zipOut, logBookFrame) ;
         } catch (Exception e) {
            Debug.logException(e) ;
         }
      } catch (RuntimeException e) {
         Debug.logException(e) ;
      } finally {
         if (zipOut != null) {
            zipOut.close() ;
         }
         Debug.monitorDataCollect("End of gatherDebugData(ManagedProduct, File)");
      }
   }
   
   /**
    * Walks through the API for a managed product and prints the results of most API class
    * to a PrintWriter. The resulting output is for engineering use only and should not be
    * exposed to end users, field support staff, and so on. This output is a raw, unfiltered view
    * of the API. The format is not guaranteed to be constant from one release to the next.
    * The purpose of this output is to assist developers of applications using the API.
    * 
    * @param managedProduct  the ManagedProduct of interest
    * @param printWriter     a PrintWriter to print to
    */
   public void printApi(AbstractLogBookFrame logBookFrame, PrintWriter printWriter) {
      ApiWalker walker = new ApiWalker() ;
      walker.walkLogBook(logBookFrame, printWriter) ;
      printWriter.flush() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the instance object for this Singleton.
    *
    * @return the instance object for this Singleton
    */
   public static DebugManager getInstance() {
      return _instance ;
   }


   /**
    * Notifies registered listeners of an exception that occurred during a callback.
    *
    * @param throwable  an exception that occurred during a client callback
    *
    */
   public static void postCallbackException(Throwable throwable) {
      Debug.logException(throwable) ;
      Collection<CallbackExceptionListener> listeners = (Collection<CallbackExceptionListener>)_callbackExceptionListeners.clone() ;
      Iterator<CallbackExceptionListener> iterator = listeners.iterator() ;
      while (iterator.hasNext()) {
         CallbackExceptionListener listener = (CallbackExceptionListener)iterator.next() ;
         try {
            listener.handleCallbackException(throwable) ;
         } catch (Throwable e) {
            // we don't care if the listener messes up!
         }
      }
   }


   /**
    * Builds and distributes and internal event to all registered listeners.
    *
    * @param eventId         the event id
    * @param source          the source class that caused the event.
    * @param extendedMessage additional text to qualify the event.
    *
    */
   public static void postEvent(InternalEventId eventId, String source, String extendedMessage) {
      InternalEvent internalEvent;
      if (source == null) {
         internalEvent = new InternalEventImpl(eventId, "unknown", extendedMessage) ;
      } else {
         internalEvent = new InternalEventImpl(eventId, source, extendedMessage) ;
      }
      
      _internalEventLog.addEntry(new InternalEventDebugLogEntry(internalEvent)) ;
      
      // Don't send an event that's already been sent
      if (_postedEvents.contains(internalEvent)) {
         return ;
      }
      _postedEvents.add(internalEvent) ;
      Collection<InternalEventListener> listeners = (Collection<InternalEventListener>)_internalEventListeners.clone() ;
      Iterator<InternalEventListener> iterator = listeners.iterator() ;
      while (iterator.hasNext()) {
         InternalEventListener listener = (InternalEventListener)iterator.next() ;
         try {
            listener.handleInternalEvent(internalEvent) ;
         } catch (Throwable e) {
            postCallbackException(e) ;
         }
      }
   }


   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation of the object.
    */
   public String toString() {
      return "DebugManager" ;
   }

   //**********
   // Private Methods
   //**********

   /**
    * Saves a product's API results to an entry in a zip file.
    * 
    * @param zipOut         the ZipOutputStream to write to
    * @param logBookFrame   the Log Book Frame to inspect
    * 
    * @throws IOException if an IO Exception occurs.
    */
   private void saveLogBookApi(ZipOutputStream zipOut, AbstractLogBookFrame logBookFrame) throws IOException {
      String name = logBookFrame.getFile().getName() ;
      Debug.monitorDataCollect("Starting API walk for " + name);
      ZipEntry zipEntry = null ;
      try {
         if (name.startsWith("/")) {
            name = name.substring(1) ;
         }
         name = name.replace('/', '-') ;
         name = name.replace('\\', '-') ;
         zipEntry = new ZipEntry(name + ".txt") ;
         zipOut.putNextEntry(zipEntry) ;
         PrintWriter printWriter = new PrintWriter(zipOut) ;
         printApi(logBookFrame, printWriter) ;
      } finally {
         if (zipOut != null) {
            zipOut.closeEntry() ;
         }
      }
      Debug.monitorDataCollect("Finished API walk for " + name);
   }
   
   /**
    * Writes the contents of a file to a ZIP file.
    *
    * @param zipOut      the ZipOutputStream to write to
    * @param sourceFile  the file to be saved
    *
    * @exception IOException if an IO error occurs
    */
   private void saveFileToZip(ZipOutputStream zipOut, File sourceFile) throws IOException {
      ZipEntry zipEntry = new ZipEntry(sourceFile.getName()) ;
      zipOut.putNextEntry(zipEntry) ;
      int bufferSize = 4096 ;
      FileInputStream inputStream = new FileInputStream(sourceFile) ;
      byte[] data = new byte[bufferSize] ;
      try {
         long fileLength = sourceFile.length() ;
         while (fileLength > 0) {
            int count = (fileLength > bufferSize) ? bufferSize : (int)fileLength ;
            inputStream.read(data, 0, count) ;
            zipOut.write(data, 0, count) ;
            fileLength -= count ;
         }
      } finally {
         inputStream.close() ;
      }
   }  // End of method storeDataCollectFile()

   
   //**********
   // Class attributes and constants
   //**********

   private static final String[] _logColumnNames = {
      "Date",
      "Address",
      "Event Code",
      "Description",
      "Extended Message"
   } ;

   private static LinkedList<InternalEventListener>     _internalEventListeners = new LinkedList<InternalEventListener>() ;
   private static LinkedList<CallbackExceptionListener> _callbackExceptionListeners = new LinkedList<CallbackExceptionListener>() ;
   private static HashSet<InternalEvent>                _postedEvents = new HashSet<InternalEvent>() ;
   private DebugableData                                _debugData = new DebugableData() ;
   private static HistoryLog                            _internalEventLog= new HistoryLog("Internal Event Log", _logColumnNames, 50) ;

   private static final DebugManager   _instance = new DebugManager() ;
   
}


// End of DebugManager.java
