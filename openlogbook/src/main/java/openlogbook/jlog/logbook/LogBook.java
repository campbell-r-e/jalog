package openlogbook.jlog.logbook;

import openlogbook.debug.Debugable;
import openlogbook.jlog.logbook.jlogentry.LogEntry;

import java.util.ArrayList;


/**
 * This class defines the LogBook interface, which will contain all of the entries in a logbook.
 * 
 * @author KC0ZPS
 */
public interface LogBook extends Debugable {

   /**
    * Gets the file version.
    * 
    * @return a String representing the file version.
    */
   public String getVersion() ;
   
   /**
    * Sets the file version.
    * 
    * @param version The file version.
    */
   public void setVersion(String version) ;

   /**
    * Returns the ArrayList of LogEntries.
    * 
    * @return the ArrayList of LogEntries.
    */
   public ArrayList<LogEntry> getLogEntries() ;
   
   /**
    * Gets a specific LogEntry.
    * 
    * @param index The index of the LogEntry.
    * 
    * @return a specific LogEntry.
    */
   public LogEntry getEntry(int index) ;
   
   /**
    * Adds a LogEntry into the LogBook.
    * 
    * @param entry The LogEntry to add.
    */
   public void addEntry(LogEntry entry) ;
   
   /**
    * Sets the LogEntry at the specified index.
    * 
    * @param index The index of the LogEntry.
    * @param entry The LogEntry itself.
    */
   public void setEntry(int index, LogEntry entry) ;
   
   /**
    * Deletes a LogEntry.
    * 
    * @param index The index of the LogEntry to delete.
    */
   public void deleteEntry(int index) ;
   
   /**
    * Returns an ADIF formatted String.
    * 
    * @return an ADIF formatted String.
    */
   public String toAdifString() ;

}
