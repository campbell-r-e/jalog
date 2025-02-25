package openlogbook.debug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * An implementation of the DebugTableContents interface suitable for debug
 * logs.
 */
public class LogDebugTable implements Serializable, DebugTable {

   /**
    * Creates a new LogDebugTable.
    * 
    * @param logName       the name of the log
    * @param columnNames   the column titles for the log
    * @param entries       the log entries
    */
   public LogDebugTable(String logName, String[] columnNames, DebugLogEntry[] entries) {
      _logName = logName ;
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
    * @return the contents of a cell formatted as a String
    */
   public Object getTableCell(int row, int column) {
      return _entries[row].getCell(column) ;
   }

   /**
    * Gets the name of the table.
    * 
    * @return the name of the table
    */
   public String getTableName() {
      return _logName ;
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
    * @param columnIndex   specifies the column of interest
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
    * Exports the log contents to a text file. Each column is space-padded to
    * achieve neatly formatted columns.
    * 
    * @param file file to export log contents to
    * 
    * @exception IOException if an IO error occurs exporting the log.
    */
   public void export(File file) throws IOException {

      // Create output file
      FileOutputStream outStream = new FileOutputStream(file) ;
      PrintWriter printWriter = new PrintWriter(outStream) ;

      print(printWriter) ;
      printWriter.flush() ;
      printWriter.close() ;
      if (printWriter.checkError()) {
         throw new IOException("Log export failed") ;
      }
   }

   /**
    * Prints the LogDebugTable to a PrintWriter.
    * 
    * @param printWriter   a PrintWriter object to print to
    */
   public void print(PrintWriter printWriter) {
      DebugTablePrinter.printTable(this, printWriter) ;
   }

   /**
    * Gets the array of log entries.
    * 
    * @return the array of log entries
    */
   public DebugLogEntry[] getEntries() {
      return _entries ;
   }

   //**********
   // Class attributes and constants
   //**********

   private String          _logName ;
   private String[]        _columnNames ;

   private DebugLogEntry[] _entries ;

   /**
    * The serialVersionUID for the class openlogbook.debug.LogDebugTable This
    * should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class. Any class that is created using this
    * class as a template should regenerate a new serialVersionUID with the
    * serialVer tool.
    *  
    */
   static final long serialVersionUID = -9218918321745079888L;

}

// End of LogDebugTable.java

