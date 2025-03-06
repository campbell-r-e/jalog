package openlogbook.jlogimpl.remotecallsign.callookreader;

import javax.swing.JTextArea;

import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.jlog.remotecallsign.ConnectionReader;
import openlogbook.jlog.remotecallsign.RemoteCallsignInformation;

/**
 * A thread class that reads data from the callook web site.  This function should be run
 * in a thread so that any network problems do not freeze the application.
 * 
 * @author KC0ZPS
 */
public class CallookReaderThread implements Runnable, Debugable {

   private static ConnectionReader   _connectionReader = new CallookReaderImpl() ;
   private static boolean            _isRunning = false ;
   private RemoteCallsignInformation _remoteCallsignInfo ;
   private String                    _callsign ;
   private JTextArea                 _textArea ;
   
   /**
    * Creates a new CallookReaderThread.
    */
   public CallookReaderThread() {
   }
   
   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a table.
    */
   public DebugTable getDebugTable() {
      String columnNames[] = { "Name", "Value" };
      PropertyDebugTable contents = new PropertyDebugTable("CallookReaderThread", columnNames);

      contents.addEntry("connectionReader", _connectionReader) ;
      contents.addEntry("is Running", _isRunning) ;
      contents.addEntry("remoteCallsignInformation", _remoteCallsignInfo) ;
      contents.addEntry("callsign", _callsign) ;
      contents.addEntry("textArea", _textArea) ;

      return contents;
   }

   //**********
   // Methods overridden from Object
   //**********
   
   /**
    * Returns a string representation of the object.
    * 
    * @return string representation of the object.
    */
   public String toString() {
      return "CallookReaderThread" ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the callsign to lookup.
    * 
    * @param callsign The callsign to lookup.
    * @param textArea The textarea to update.
    */
   public void setCallsign(String callsign, JTextArea textArea) {
      _callsign = callsign ;
      _textArea = textArea ;
   }
   
   /**
    * Gets the RemoteCallsignInformation.  This will be null if something went wrong.
    * 
    * @return the RemoteCallsignInformation.  This will be null if something went wrong.
    */
   public RemoteCallsignInformation getRemoteCallsignInformation() {
      return _remoteCallsignInfo ;
   }
   
   /**
    * Fetch the callsign data from the website.  A locking mechanism has been added so that
    * only one network call will ever be executed.  If another thread attempts to make the network call, 
    * an error message will be displayed.
    */
   public void run() {
      _textArea.setText("Fetching data for " + _callsign + "from http://callook.info/") ;
      if (!_isRunning) {
         _isRunning = true ;
         _remoteCallsignInfo = null ;
         try {
            _remoteCallsignInfo = _connectionReader.get(_callsign) ;
         } catch (Exception e) {
            e.printStackTrace() ;
         } finally {
            _isRunning = false ;                     
         }
      }
      if (_remoteCallsignInfo == null) {
         _textArea.setText("Problem fetching data.  Try again later.") ;         
      } else {
         _textArea.setText(_remoteCallsignInfo.toString()) ;
      }
   }

}
