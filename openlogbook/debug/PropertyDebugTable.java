package openlogbook.debug;

import java.util.ArrayList;

/**
 * A DebugTable that consists of name value pairs. This table can be built
 * dynamically, allowing the caller to add entries one at a time.
 */
public class PropertyDebugTable implements DebugTable {

   /**
    * Creates a new PropertyDebugTable.
    *
    * @param tableName    The name (title) for the table
    * @param columnNames  The titles for each column in the table
    */
   public PropertyDebugTable(String tableName, String[] columnNames) {
      _tableName = tableName ;
      _columnNames = columnNames ;
   }

   //**********
   // Implementation of DebugTable interface
   //**********

   /**
    * Gets the contents of a cell in the table.
    *
    * @param row     specifies the table row
    * @param column  specifies the table column
    *
    * @return the contents of a cell formatted as a String
    */
   public Object getTableCell(int row, int column) {
      PropertyTableEntry entry = (PropertyTableEntry)_entries.get(row) ;
      switch (column) {
      case 0:
         return entry.getName() ;
      case 1:
         return entry.getValue() ;
      default:
         throw new ArrayIndexOutOfBoundsException() ;
      }
   }

   /**
    * Gets the name of the table.
    *
    * @return the name of the table
    */
   public String getTableName() {
      return _tableName ;
   }


   /**
    * Gets the number of columns in the table.
    *
    * @return the number of columns in the table.
    */
   public int getColumnCount() {
      return _columnNames.length ;
   }


   /**
    * Gets the number of rows in the table.
    *
    * @return the number of rows in the table
    */
   public int getRowCount() {
      return _entries.size() ;
   }


   /**
    * Gets the name of the column at columnIndex.
    *
    *
    * @param columnIndex  specifies the column of interest
    *
    * @return the name of the column at columnIndex
    */
   public String getColumnTitle(int columnIndex) {
      return _columnNames[columnIndex] ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Adds an entry to the table contents.
    *
    * @param name   name of the property (left column)
    * @param value  value of the property (right column)
    *
    */
   public void addEntry(String name, Object value) {
      _entries.add(new PropertyTableEntry(name, value)) ;
   }


   /**
    * Adds an entry to the table contents.
    *
    * @param name   name of the property (left column)
    * @param value  value of the property (right column)
    *
    */
   public void addEntry(String name, int value) {
      _entries.add(new PropertyTableEntry(name, String.valueOf(value))) ;
   }


   /**
    * Adds an entry to the table contents.
    *
    * @param name   name of the property (left column)
    * @param value  value of the property (right column)
    *
    */
   public void addEntry(String name, long value) {
      _entries.add(new PropertyTableEntry(name, String.valueOf(value))) ;
   }


   /**
    * Adds an entry to the table contents.
    *
    * @param name   name of the property (left column)
    * @param value  value of the property (right column)
    *
    */
   public void addEntry(String name, boolean value) {
      _entries.add(new PropertyTableEntry(name, String.valueOf(value))) ;
      return ;
   }

   /**
    * A data class to associate a name/value pair.
    */
   private static class PropertyTableEntry {

      /**
       * Creates a new PropertyTableEntry
       *
       * @param name  name of the table entry
       * @param value value of the table entry
       *
       */
      PropertyTableEntry(String name, Object value) {
         _name = name ;
         _value = value ;
      }

      /**
       * Gets the property name.
       *
       * @return the property name
       */
      public String getName() {
         return _name ;
      }

      /**
       * Gets the property value.
       *
       * @return the property value
       */
      public Object getValue() {
         return _value ;
      }

      private String _name ;
      private Object _value ;


   }


   //**********
   // Class attributes and constants
   //**********

   private String       _tableName ;
   private String[]     _columnNames ;
   private ArrayList<PropertyTableEntry> _entries = new ArrayList<PropertyTableEntry>() ;

}


// End of PropertyDebugTable.java

