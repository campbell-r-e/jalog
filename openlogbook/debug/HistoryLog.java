package openlogbook.debug;

/**
 * Provides a simple, wrapping history log.
 */
public class HistoryLog {

   /**
    * Creates a new HistoryLog.
    *
    * @param logName      Name of the log
    * @param columnNames  labels for the log columns
    * @param capacity     max number of entries in the log
    *
    */
   public HistoryLog(String logName, String[] columnNames, int capacity) {
      _logName = logName ;
      _capacity = capacity ;
      _columnNames = new String[columnNames.length] ;
      System.arraycopy(columnNames, 0, _columnNames, 0, columnNames.length) ;
      _entries = new DebugLogEntry[_capacity] ;
      _writeIndex = 0 ;
      _isFull = false ;
      _isEmpty = true ;
   }

    /**
     * Creates a new HistoryLog.
     *
     * @param logName      Name of the log
     *
     */
    public HistoryLog(String logName) {
       this(logName, COLUMN_NAMES, DEFAULT_CAPACITY);
    }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of the object.
    *
    * @return  a string representation of the object
    */
   public String toString() {
      return _logName ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Adds a new entry to the log. If the log is already full,
    * the oldest entry will be overwritten
    *
    * @param entry   entry to be added to the log
    *
    */
   public synchronized void addEntry(DebugLogEntry entry) {
      if (_writeIndex > 0) {
         _writeIndex-- ;
      } else {
         _writeIndex = _capacity - 1 ;
      }
      _entries[_writeIndex] = entry ;
      if (_writeIndex == 0) {
         _isFull = true ;
      }
      _isEmpty = false ;
   }

   /**
    * Clears the log.
    *
    */
   public synchronized void clear() {
      _writeIndex = 0 ;
      _isFull = false ;
      _isEmpty = true ;
   }


   /**
    * Gets the contents of the log.
    *
    * @return    Returns the contents of the log
    */
   public synchronized LogDebugTable getContents() {
      int count ;
      DebugLogEntry[] entries ;
      if (_isEmpty) {
         count = 0 ;
         entries = new DebugLogEntry[0] ;
      } else if (_isFull) {
         count = _capacity ;
         entries = new DebugLogEntry[count] ;
         int endCount = _capacity - _writeIndex ;
         System.arraycopy(_entries, _writeIndex, entries, 0, endCount) ;
         System.arraycopy(_entries, 0, entries, endCount, _capacity - endCount) ;
      } else {
         count = _capacity - _writeIndex ;
         entries = new DebugLogEntry[count] ;
         System.arraycopy(_entries, _writeIndex, entries, 0, count) ;
      }
      // Create a contiguous entry array

      return new LogDebugTable(getName(), _columnNames, entries) ;
   }

   /**
    * Gets the name of the log.
    *
    * @return    Returns the name of the log
    */
   public String getName() {
      return _logName ;
   }

   /**
    * Gets the classname of a DrillDownPanel suitable for displaying drill-down information for the specified row.
    *
    * Note that this should be overridden by subclasses if a drill-down is desired.
    *
    * @return    the classname of a DrillDownPanel object if drill-down is supported; else returns null
    */
   public String getDrillDownPanelClassname() {
      return null ;
   }
   
   /**
    * Tests if this log should be written as a Java serialized object during
    * a data collection operation. 
    * 
    * @return true if this log should be written as a Java serialized object during
    * a data collection operation.
    */
   public boolean serializeOnDataCollection() {
      return false ;
   }

   //**********
   // Class attributes and constants
   //**********

   private static final String[] COLUMN_NAMES = {"Date/Time", "Activity"};
   private static final int      DEFAULT_CAPACITY = 50;

   private int             _capacity ;
   private int             _writeIndex ;
   private boolean         _isFull ;
   private boolean         _isEmpty ;
   private String          _logName ;
   private String[]        _columnNames ;
   private DebugLogEntry[] _entries ;

}

// End of HistoryLog.java

