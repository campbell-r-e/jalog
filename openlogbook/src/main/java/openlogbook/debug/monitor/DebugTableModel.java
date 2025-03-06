package openlogbook.debug.monitor;

import openlogbook.debug.DebugTable;

import javax.swing.table.AbstractTableModel;

/**
 * A Data Model to support displaying a DebugTable object in a JTable
 */
class DebugTableModel extends AbstractTableModel {

   /**
    * Creates a new DebugTableModel.
    */
   public DebugTableModel() {
   }

   /**
    * Creates a new DebugTableModel.
    *
    * @param debugTable a DebugTable object for the model
    */
   public DebugTableModel(DebugTable debugTable) {
      _debugTable = debugTable ;
   }

   //**********
   // Implementation of TableModel interface
   //**********

   /**
    * JTable uses this method to determine the default renderer/
    * editor for each cell.  If we didn't implement this method,
    * then the last column would contain text ("true"/"false"),
    * rather than a check box.
    *
    * @param column a column
    * 
    * @return the Class for the column
    */
   public Class<?> getColumnClass(int column) {
      if (_debugTable == null) {
         return String.class ;
      } else {
         return getValueAt(0, column).getClass();
      }
   }

   /**
    * Returns data object at row and column.
    *
    * @param row     The row address of the data object to get.
    * @param column The column address of the data object to get.
    *
    * @return    The requested data object.
    */
   public Object getValueAt(int row, int column) {
      if (_debugTable == null) {
         return "" ;
      }
      try {
         Object o = _debugTable.getTableCell(row, column) ;
         return (o != null) ? o : "" ;
      } catch (Exception e) {
         return "" ;
      }
   }

   /**
    * Returns count of rows.
    *
    * @return    The number of rows in this container.
    */
   public int getRowCount() {
      if (_debugTable != null) {
         return _debugTable.getRowCount() ;
      } else {
         return 0 ;
      }
   }

   /**
    * Returns count of columns.
    *
    *
    * @return    The number of columns in this container.
    */
   public int getColumnCount() {
      if (_debugTable != null) {
         return _debugTable.getColumnCount() ;
      } else {
         return 0 ;
      }
   }

   /**
    * Returns name for the specified column
    *
    * @param column  column number
    *
    * @return name for the specified column
    */
   public String getColumnName(int column) {
      if (_debugTable != null) {
         return _debugTable.getColumnTitle(column) ;
      } else {
         return "" ;
      }
   }


   /**
    * Updates the contents of the model
    *
    * @param debugTable  updated table contents for the model
    *
    */
   public void setContents(DebugTable debugTable) {
      _debugTable = debugTable ;
      fireTableStructureChanged() ;
   }

   //**********
   // Class attributes and constants
   //**********

   private transient DebugTable    _debugTable = null ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.DebugTableModel
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -8400413876888224967L;

}


// End of DebugTableModel.java

