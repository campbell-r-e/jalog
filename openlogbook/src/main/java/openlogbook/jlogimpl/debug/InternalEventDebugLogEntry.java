package openlogbook.jlogimpl.debug ;

import openlogbook.debug.DebugLogEntry;
import openlogbook.debug.InternalEvent;

/**
 * Represents an entry in a debug log for audit entries.
 */
class InternalEventDebugLogEntry implements DebugLogEntry {

   /**
    * Creates a new InternalEventDebugLogEntry.
    * 
    * @param internalEvent  the internal event to be logged.
    */
   InternalEventDebugLogEntry(InternalEvent internalEvent) {
      _internalEvent = internalEvent ;
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
         return _internalEvent.getDate() ;
      case 1:
         return _internalEvent.getFileName() ;
      case 2:
         return new Integer(_internalEvent.getEventCode()) ;
      case 3:
         return _internalEvent.getEventDescription() ;
      case 4:
         return _internalEvent.getExtendedMessage() ;
      default:
         return "" ;
      }
   }

   private InternalEvent   _internalEvent ;

   /**
    * The serialVersionUID for the class openlogbook.jlogimpl.debug.InternalEventDebugLogEntry
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -767066181049994663L;

}


// End of InternalEventDebugLogEntry.java
