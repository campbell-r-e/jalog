package openlogbook.debug;

/**
 * Provides common handling for displaying product data in the debug tree.
 * This class can be used when the an object simply wants to display data, but
 * has no trace points or custom commands.
 */
public abstract class DebugableImpl implements FullDebugable {
  /**
   * Creates a new DebugableImpl.
   */
   public DebugableImpl() {

   }

   //**********
   // Implementation of FullDebugable interface
   //**********

   /**
    * Returns the debug data object for this object.
    *
    * @return    the debug data object for this object
    */
   public DebugableData getDebugableData() {
      return _debugData ;
   }

   /**
    * Returns the contents of the object in a form suitable for displaying in a table.
    *
    * @return    the contents of the object in a form suitable for displaying in a table
    */
   public abstract DebugTable getDebugTable() ;

   //**********
   // Class attributes and constants
   //**********

   private DebugableData         _debugData = new DebugableData() ;
}


// End of DebugableImpl.java

