package openlogbook.debug;

/**
 * Supports a simple table in which each row has an array of objects.
 */
public class ArrayDebugTableEntry {

   /**
    * Creates a new ArrayDebugTableEntry.
    *
    * @param fields the fields for the entry
    *
    */
   public ArrayDebugTableEntry(Object[] fields) {
      _fields = fields ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Returns the number of fields that can be formatted.
    *
    * @return the number of fields that can be formatted
    */
   public int getNbrFields() {
      return _fields.length ;
   }

   /**
    * Returns the contents of a field formatted as a String.
    *
    * @param column  indicates which field to format
    *
    * @return the contents of a field formatted as a String
    */
   public Object getField(int column) {
      return _fields[column] ;
   }

   //**********
   // Class attributes and constants
   //**********

   private Object[] _fields ;

}



// End of ArrayDebugTableEntry.java

