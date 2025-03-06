package openlogbook.debug;

import openlogbook.util.StringUtility;

/**
 * A simple log entry that allows a string to be placed in a log
 * along with a time stamp.
 */
public class SimpleDebugLogEntry implements DebugLogEntry {

   /**
    * Creates a new SimpleDebugLogEntry.
    *
    * @param event - a string to be logged
    */
   public SimpleDebugLogEntry(String event) {
      _event = event ;
      _time = System.currentTimeMillis() ;
   }

   //**********
   // Implementation of DebugLogEntry interface
   //**********

   /**
    * Gets the contents of a cell.
    *
    * @param column  indicates which column to get
    *
    * @return the contents of a cell
    */
   public Object getCell(int column) {
      switch (column) {
      case 0:
         return StringUtility.formatSimpleDate(_time) ;
      case 1:
         return _event ;
      default:
         throw new ArrayIndexOutOfBoundsException() ;
      }
   }

   /**
    * return a string representation of the object.
    * @return a string representation of the object.
    */
   public String toString() {
      return StringUtility.formatSimpleDate(_time) + " : " + _event;
   }
    
   //**********
   // Class attributes and constants
   //**********

   private String _event ;
   private long   _time ;

   /**
    * The serialVersionUID for the class openlogbook.debug.SimpleDebugLogEntry
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 6423159868897926545L;


}


// End of SimpleDebugLogEntry.java

