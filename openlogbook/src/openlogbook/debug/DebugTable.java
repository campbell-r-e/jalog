package openlogbook.debug;

/**
 * An interface representing the contents of a table. This class is used to
 * support various debugging features
 */
public interface DebugTable {

   /**
    * Gets the contents of a cell in the table.
    *
    * @param row     specifies the table row
    * @param column  specifies the table column
    *
    * @return the contents of a cell formatted as a String
    */
   public Object getTableCell(int row, int column) ;

   /**
    * Gets the name of the table.
    *
    * @return the name of the table
    */
   public String getTableName() ;


   /**
    * Gets the number of columns in the table.
    *
    * @return the number of columns in the table.
    */
   public int getColumnCount() ;


   /**
    * Gets the number of rows in the table.
    *
    * @return the number of rows in the table
    */
   public int getRowCount() ;


   /**
    * Gets the name of the column at columnIndex.
    *
    * @param columnIndex specifies the column of interest
    *
    * @return the name of the column at columnIndex
    */
   public String getColumnTitle(int columnIndex) ;

}

// End of DebugTable.java

