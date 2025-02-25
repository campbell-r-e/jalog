package openlogbook.debug;

import java.io.PrintWriter;

/**
 * A class that provides a common way of "printing" the contents of a
 * DebugTable object.
 */
public class DebugTablePrinter {

   /**
    * Creates a new DebugTablePrinter. The constructor is private because
    * this is a utility class and we don't want instances created.
    *
    */
   private DebugTablePrinter() {
   }
   
   //**********
   // Public methods
   //**********

   /**
    * "Prints" the contents of a DebugTable object to a PrintWriter.
    *
    * @param debugTable   a DebugTable object to be printed
    * @param printWriter  a PrintWriter object to print the contents to
    *
    */
   public static void printTable(DebugTable debugTable, PrintWriter printWriter) {
      // Determine width of each column
      int[] columnWidths = new int[debugTable.getColumnCount()] ;
      for (int column = 0; column < debugTable.getColumnCount(); column++) {
         String columnName = debugTable.getColumnTitle(column) ;
         int width = columnName.length() ;
         // Don't bother to pad the last column
         if (column != debugTable.getColumnCount()-1) {
            for (int row = 0; row < debugTable.getRowCount(); row++) {
               try {
                  Object cell = debugTable.getTableCell(row, column) ;
                  if (cell != null) {
                     width = Math.max(width, cell.toString().length()) ;
                  }
               } catch (ArrayIndexOutOfBoundsException e) {
                  // Ok To Ignore
               }
            }
         }
         columnWidths[column] = width + COLUMN_PADDING ;
      }

      printWriter.println(debugTable.getTableName()) ;

      // column headings
      for (int column = 0; column < debugTable.getColumnCount(); column++) {
         printPaddedString(printWriter, debugTable.getColumnTitle(column), columnWidths[column], ' ') ;
      }
      printWriter.println() ;

      // Print the separator line
      for (int column = 0; column < debugTable.getColumnCount(); column++) {
         printPaddedString(printWriter, "", columnWidths[column] - COLUMN_PADDING, '-') ;
         printPaddedString(printWriter, "", COLUMN_PADDING, ' ') ;
      }
      printWriter.println() ;

      // print the table contents
      for (int row = 0; row < debugTable.getRowCount(); row++) {
         for (int column = 0; column < debugTable.getColumnCount(); column++) {
            Object cell = debugTable.getTableCell(row, column) ;
            if (cell == null) {
               cell = "" ;
            }
            printPaddedString(printWriter, cell.toString(), columnWidths[column], ' ') ;
         }
         printWriter.println() ;
      }
   }


   //**********
   // Private methods
   //**********

   /**
    * Pads a string to a specified length and prints to a PrintWriter stream
    *
    * @param printWriter  PrintWriter object to output to
    * @param s            string to be output
    * @param length       length to pad to
    * @param padChar      character to use for padding
    *
    */
   private static void printPaddedString(PrintWriter printWriter, String s, int length, char padChar) {
      printWriter.print(s) ;
      for (int idx = s.length(); idx < length; idx++) {
         printWriter.print(padChar) ;
      }
   }

   //**********
   // Class attributes and constants
   //**********

   private static final int COLUMN_PADDING = 2 ;

}



// End of DebugTablePrinter.java

