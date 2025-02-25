package openlogbook.debug;


/**
 * Contains data associated with a single trace point.
 */
public class TracePoint {

   /**
    * Create a new TracePoint.
    *
    * @param description describes the trace point
    */
   public TracePoint(String description) {
      _description = description ;
   }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a String representation of this object.
    *
    * @return a String representation of this object
    */
   public String toString() {
      return _description ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Tests if the trace point is enabled.
    *
    * @return true if the trace point is enabled
    */
   public boolean isEnabled() {
      return _enabled ;
   }


   /**
    * Sets the state of the tracepoint.
    *
    * @param  enabled New value for the state of the tracepoint
    *
    */
   public void setEnabled(boolean enabled) {
      _enabled = enabled ;
   }

   /**
    * Gets the trace point description.
    *
    * @return    The trace point description
    */
   public String getDescription() {
      return _description ;
   }


   //**********
   // Class attributes and constants
   //**********

   private String    _description ;
   private boolean   _enabled = false ;
}


// End of TracePoint.java

