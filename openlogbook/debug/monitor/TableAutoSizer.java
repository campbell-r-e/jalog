package openlogbook.debug.monitor;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * A helper class to auto-size columns in a JTable.
 */
class TableAutoSizer {

   /**
    * Creates a new TableAutoSizer. The constructor is private because
    * this is a utility class and should never be instantiated.
    * 
    */
   private TableAutoSizer() {
   }
   
   //**********
   // Public methods
   //**********
   
   /**
    * Auto-size columns in a JTable.  This method looks for the longest string in each
    * column, then determines the screen length of that screen. It then sets the column
    * width to that size.
    *
    * @param table  a table to be auto-sized
    *
    */
   public static void autoSizeTable(JTable table) {
      TableModel model = table.getModel() ;
      Font font = table.getFont() ;
      if (font == null) {
         font = new Font("Dialog", Font.PLAIN, 10) ;
      }
      FontMetrics fm = table.getFontMetrics(font) ;
      if (fm == null) {
         return ;
      }
      int pad = fm.stringWidth("W") ;
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

      for (int column = 0; column < model.getColumnCount(); column++) {
         String columnName = model.getColumnName(column) ;
         String longestEntry = columnName ;
         int width = longestEntry.length() ;
         for (int row = 0; row < model.getRowCount(); row++) {
            Object obj = model.getValueAt(row, column) ;
            String value = obj.toString() ;
            if (value.length() > width) {
               width = value.length() ;
               longestEntry = value ;
            }
         }
         if (width > 0) {
            int columnWidth = fm.stringWidth(longestEntry) + pad ;
            table.getColumnModel().getColumn(column).setPreferredWidth(columnWidth) ;
         }
      }
   }

}


// End of TableAutoSizer.java

