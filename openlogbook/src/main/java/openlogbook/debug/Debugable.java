package openlogbook.debug;

/**
 * The Debugable interface is implemented by any class that wishes to have
 * support in the debug monitor window.
 */
public interface Debugable {

   /**
    * Gets the contents of the object in a form suitable for displaying in a table.
    *
    * @return  the contents of the object in a form suitable for displaying in a table
    */
   public DebugTable getDebugTable() ;


}


// End of Debugable.java

