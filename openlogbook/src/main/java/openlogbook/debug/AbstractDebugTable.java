package openlogbook.debug;

import java.io.Serializable;


/**
 * An abstract implementation of DebugTable that provides the table name and column names.
 */
public abstract class AbstractDebugTable implements DebugTable, Serializable {

   /**
    * Creates a new AbstractDebugTable.
    *
    * @param tableName    The name (title) for the table
    * @param columnNames  The titles for each column in the table
    */
   public AbstractDebugTable(String tableName, String[] columnNames) {
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
   public abstract Object getTableCell(int row, int column) ;

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
   public abstract int getRowCount() ;


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
   // Class attributes and constants
   //**********

   private String       _tableName ;
   private String[]     _columnNames ;
   
   /**
    * The serialVersionUID for this class. This should not be changed unless
    * incompatible changes are made to this class. If you do not know what that
    * means, you should not be changing this class. DO NOT copy this to another
    * class. Any class that is created using this class as a template should
    * regenerate a new serialVersionUID with the serialVer tool.
    */
   static final long serialVersionUID = -5880871600474221775L;

}


// End of AbstractDebugTable.java

