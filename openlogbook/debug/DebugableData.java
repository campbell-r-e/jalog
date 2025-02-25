package openlogbook.debug;

import java.util.Vector;

/**
 * A data class to manage debug data for objects that implement
 * the Debugable interface.  An instance of this class is contained
 * by all classes that implement the Debugable interface.
 */
public class DebugableData {

   /**
    * Creates a new DebugableData.
    */
   public DebugableData() {

   }

   //**********
   // Public methods
   //**********

   /**
    * Adds a CustomCommand object to the list of commands handled by an object.
    *
    * @param customCommand  a custom command to be added to the list of managed commands
    *
    */
   public void addCustomCommand(CustomCommand customCommand) {
      if (_customCommands == null) {
         _customCommands = new Vector<CustomCommand>() ;
      }
      _customCommands.addElement(customCommand) ;
   }


   /**
    * Registers a history log for viewing in the debug monitor window.
    *
    * @param log  a history log to be added
    *
    */
   public void addHistoryLog(HistoryLog log) {
      if (_historyLogs == null) {
         _historyLogs = new Vector<HistoryLog>() ;
      }
      _historyLogs.addElement(log) ;
   }

   /**
    * Sets the trace control object.
    *
    * @param traceControl  - a trace control object
    *
    * @see #getTraceControl
    *
    */
   public void setTraceControl(TraceControl traceControl) {
      _traceControl = traceControl ;
   }

   /**
    * Gets a list of custom commands.
    *
    *
    * @return    a list of custom commands
    */
   public Vector<CustomCommand> getCustomCommands() {
      return _customCommands ;
   }

   /**
    * Returns a list of history logs.
    *
    * @return    a list of history logs
    */
   public Vector<HistoryLog> getHistoryLogs() {
      return _historyLogs ;
   }

   /**
    * Gets the trace control object.
    *
    * @return  a trace control object
    *
    * @see #setTraceControl
    */
   public TraceControl getTraceControl() {
      return _traceControl ;
   }


   //**********
   // Class attributes and constants
   //**********

   private Vector<CustomCommand> _customCommands ;
   private Vector<HistoryLog>    _historyLogs ;
   private TraceControl _traceControl ;

}

// End of DebugableData.java

