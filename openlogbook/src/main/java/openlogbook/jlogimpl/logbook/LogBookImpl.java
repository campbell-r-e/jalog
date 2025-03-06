package openlogbook.jlogimpl.logbook;

import openlogbook.debug.DebugTable;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.LogEntry;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of the LogBook object. 
 * 
 * @author KC0ZPS
 */
public class LogBookImpl implements LogBook {

   private String              _version = new String("Error") ;
   private ArrayList<LogEntry> _entries = new ArrayList<LogEntry>() ;
   
   /**
    * Creates a new LogBookImpl.
    * 
    * @param version The version of this LogBook.
    */
   public LogBookImpl(String version) {
      _version = version ;
      // createTestEntries() ; // TODO 
   }
   
   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a
    *         table
    */
   public DebugTable getDebugTable() {
      // Create the DebugTable at the LogBook level.
      // It would be nice to move the debug information to the lower levels, but that would mean
      // creating a DebugTable for every entry, which would suck up memory. 
      return new DebugTable() {
         public Object getTableCell(int row, int column) {
            LogEntry entry = _entries.get(row) ;
            switch (column) {
            case 0:
               return String.valueOf(row);
            case 1:
               return entry.getCallsign().getContactStation();
            case 2:
               return entry.getCallsign().getOperatingStation();
            case 3:
               return entry.getEra().getStartDate().toString() ;
            case 4:
               return entry.getEra().getEndDate().toString() ;
            case 5:
               return entry.getEra().getStartDateString() ;
            case 6:
               return entry.getEra().getEndTimeString() ;
            case 7:
               return entry.getRst().getRstReceived() ;
            case 8:
               return entry.getRst().getRstSent() ;
            case 9:
               return entry.getFrequencyInformation().getMode() ;
            case 10:
               return entry.getFrequencyInformation().getUnknownMode() ;
            case 11:
               return entry.getFrequencyInformation().getBand() ;
            case 12:
               return entry.getFrequencyInformation().getFrequency() ;
            case 13:
               return entry.getFrequencyInformation().getTxPower() ;
            case 14:
               return entry.getFrequencyInformation().getRxPower() ;
            default:
               return "Error";
            }
         }

         public String getTableName() {
            return "LogBookImpl";
         }

         public int getColumnCount() {
            return _debugTitles.length;
         }

         public int getRowCount() {
            return _entries.size() ;
         }

         public String getColumnTitle(int columnIndex) {
            return _debugTitles[columnIndex];
         }

         private final String[] _debugTitles = {
               "Index", 
               "Contact Station", "Operating Station", 
               "Start Date", "End Date", "Start Date String", "End Date String",
               "RSTR", "RSTS",
               "Mode", "Unknown Mode", "Band", "Frequency", "TX Power", "RX Power", };
      };
   }

   //**********
   // Methods that are inherited from LogBook
   //**********
   
   /**
    * Gets the file version.
    * 
    * @return a String representing the file version.
    */
   public String getVersion() {
      return _version ;
   }
   
   /**
    * Sets the file version.
    * 
    * @param version The file version.
    */
   public void setVersion(String version) {
      _version = version ;
   }
   
   /**
    * Returns the ArrayList of LogEntries.
    * 
    * @return the ArrayList of LogEntries.
    */
   public ArrayList<LogEntry> getLogEntries() {
      return _entries ;
   }
 
   /**
    * Gets a specific LogEntry.
    * 
    * @param index The index of the LogEntry.
    * 
    * @return a specific LogEntry.
    */
   public LogEntry getEntry(int index) {
      return _entries.get(index) ;
   }
   
   /**
    * Adds a LogEntry into the LogBook.
    * 
    * @param entry The LogEntry to add.
    */
   public void addEntry(LogEntry entry) {
      _entries.add(entry) ;
   }
   
   /**
    * Sets the LogEntry at the specified index.
    * 
    * @param index The index of the LogEntry.
    * @param entry The LogEntry itself.
    */
   public void setEntry(int index, LogEntry entry) {
      _entries.set(index, entry) ;
   }
   
   /**
    * Deletes a LogEntry.
    * 
    * @param index The index of the LogEntry to delete.
    */
   public void deleteEntry(int index) {
      _entries.remove(index) ;
   }
   
   /**
    * Returns an ADIF formatted String.
    * 
    * @return an ADIF formatted String.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      Iterator<LogEntry> iterator = _entries.iterator() ;
      while (iterator.hasNext()) {
         LogEntry entry = iterator.next() ;
         buffer.append(entry.toAdifString() + "<eor>\n") ;
      }
      
      return buffer.toString() ;      
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
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append("LogBookImpl") ;
      
      // Iterator<LogEntry> iterator = _entries.iterator() ;
      // int count = 0 ;
      // while (iterator.hasNext()) {
      //    count++ ;
      //    buffer.append("#" + count + "\n" + iterator.next()) ;
      // }

      return buffer.toString() ;
   }

   //**********
   // Private methods
   //**********

   /* private void createTestEntries() {
      
      // This section of code needs to be fixed.  Should use iterator too.
      LogEntry entry ;
      for (int index = 0; index < 10; index++) {
         entry = DefaultLogBookFactory.createLogEntry() ;
         entry.setStation("KC0" + index) ;
         
         entry.getRst().setRstReceived("599") ;
         entry.getRst().setRstSent("599") ;
         
         entry.getMisc().setComment("Comment #" + index) ;
         entry.getMisc().setLocator("L" + index) ;
         
         entry.getFrequencyInformation().setBand(Band._2190m) ;
         entry.getFrequencyInformation().setFrequency(index + "." + index + "." + index) ;
         entry.getFrequencyInformation().setMode(Mode.OLIVIA) ;
         entry.getFrequencyInformation().setPower(index) ;
         
         entry.getEra().setDateSameAsStart(false) ;
         // entry.getEra().setStartDate(new Date(index)) ;
         // entry.getEra().setEndDate(new Date(index+100)) ;
         
         entry.getContactInformation().setAddress(index + " Address Road") ;
         entry.getContactInformation().setCountry(index + " Country") ;
         entry.getContactInformation().setIota(DefaultLogBookFactory.createIota(IotaEnum.AF, index)) ;
         entry.getContactInformation().setName("Mortimer Dipthong") ;
         entry.getContactInformation().setQth(index + " QTH") ;
         
         _entries.add(entry) ;
      } 
   } */
   
}
