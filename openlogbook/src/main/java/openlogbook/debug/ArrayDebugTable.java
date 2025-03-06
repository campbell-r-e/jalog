package openlogbook.debug;

/**
 * Contents the contents of a table as an n x m matrix of objects.
 */
public class ArrayDebugTable implements DebugTable {

   /**
    * Creates a new ArrayDebugTable.
    *
    * @param tableName   name of the string table
    * @param columnNames names of the columns in the table
    * @param entries     entries for the table
    *
    */
   public ArrayDebugTable(String tableName, String[] columnNames, ArrayDebugTableEntry[] entries) {
      _tableName = tableName ;
      _columnNames = columnNames ;
      _entries = entries ;
   }

   //**********
   // Implementation of the DebugTable interface
   //**********

   /**
    * Gets the contents of a cell in the table.
    *
    * @param row     specifies the table row
    * @param column  specifies the table column
    *
    * @return the contents of a cell
    */
   public Object getTableCell(int row, int column) {
      return _entries[row].getField(column) ;
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
      return _entries.length ;
   }


   /**
    * Gets the name of the column at columnIndex.
    *
    * @param columnIndex  specifies the column of interest
    *
    * @return the name of the column at columnIndex
    */
   public String getColumnTitle(int columnIndex) {
      return _columnNames[columnIndex] ;
   }


   //**********
   // Class attributes and constants
   //**********

   private String                   _tableName ;
   private String[]                 _columnNames ;
   private ArrayDebugTableEntry[]   _entries ;

}


// End of ArrayDebugTable.java

