package openlogbook.debug;

import java.util.Vector;

/**
 * A class for controlling tracepoints.
 */
public class TraceControl {

   /**
    * Creates a new TraceControl.
    *
    * @param nbrTracePoints number of trace points in this trace
    *
    */
   public TraceControl(int nbrTracePoints) {
      _tracePoints = new Vector<TracePoint>(nbrTracePoints) ;
   }

   /**
    * Create a new TraceControl.
    *
    * @param descriptions array of strings describing the trace points
    */
   public TraceControl(String[] descriptions) {
      _tracePoints = new Vector<TracePoint>(descriptions.length) ;
      for (int idx = 0; idx < descriptions.length; idx++) {
         addTracePoint(descriptions[idx]) ;
      }
   }

   //**********
   // Public methods
   //**********

   /**
    * Adds a new tracepoint.
    *
    * @param traceDescription  description of the trace point
    *
    */
   public void addTracePoint(String traceDescription) {
      _tracePoints.addElement(new TracePoint(traceDescription)) ;
   }


   /**
    * Adds a new tracepoint.
    *
    * @param traceDescription  description of the trace point
    * @param traceId           tracepoint id
    *
    */
   public void addTracePoint(String traceDescription, int traceId) {
      if (traceId >= _tracePoints.size()) {
         _tracePoints.setSize(traceId + 1) ;
      }
      _tracePoints.setElementAt(new TracePoint(traceDescription), traceId) ;
   }


   /**
    * Set enable state for all tracepoints.
    *
    * @param enabled new enable state for all tracepoints
    *
    */
   public void enableAll(boolean enabled) {
      for (int idx = 0; idx < _tracePoints.size(); idx++) {
         TracePoint tp = (TracePoint)_tracePoints.elementAt(idx) ;
         tp.setEnabled(enabled) ;
      }
   }


   /**
    * Turns a tracepoint on or off.
    *
    * @param traceId  identifies the tracepoint to be modified
    * @param enabled  new state for the tracepoint
    *
    */
   public void enableTrace(int traceId, boolean enabled) {
      TracePoint tp = (TracePoint)_tracePoints.elementAt(traceId) ;
      tp.setEnabled(enabled) ;
   }


   /**
    * Returns true if the specified trace point is enabled.
    *
    * @param traceId  identifies a tracepoint
    *
    * @return    true if the specified trace point is enabled.
    */
   public boolean isTraceEnabled(int traceId) {
      try {
         return ((TracePoint)_tracePoints.elementAt(traceId)).isEnabled() ;
      } catch (ArrayIndexOutOfBoundsException e) {
         return false ;
      }
   }


   /**
    * Returns a list of all tracepoints.
    *
    * @return    a list of all tracepoints
    */
   public Vector<TracePoint> listTracePoints() {
      return _tracePoints ;
   }


   /**
    * Conditionally traces a string to the debug monitor.
    *
    * @param traceId   Identifies a specific trace point
    * @param s         The string to be traced
    *
    */
   public void trace(int traceId, String s) {
      try {
         TracePoint tp = (TracePoint)_tracePoints.elementAt(traceId) ;
         if (tp.isEnabled()) {
            System.out.print(s) ;
         }
      } catch (ArrayIndexOutOfBoundsException e) {
         e.printStackTrace() ;
      }
   }

   /**
    * Conditionally traces a string to the debug monitor and add
    * a newline at the end.
    *
    * @param traceId   Identifies a specific trace point
    * @param s         The string to be traced
    *
    */
   public void traceln(int traceId, String s) {
      trace(traceId, s + "\n") ;
   }

   //**********
   // Class attributes and constants
   //**********

   private Vector<TracePoint>    _tracePoints ;
}


// End of TraceControl.java

