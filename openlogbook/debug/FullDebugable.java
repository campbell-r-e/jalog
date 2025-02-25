package openlogbook.debug;

/**
 * The FullDebugable interface is implemented by any class that wishes to have
 * full debug support in the debug monitor window. This interface adds the debugable data
 * access, which supports logs, tracepoints, and custom commands.
 */
public interface FullDebugable extends Debugable {

   /**
    * Returns the debug data object for this object.
    *
    * @return    the debug data object for this object
    */
   public DebugableData getDebugableData() ;


}


// End of FullDebugable.java

