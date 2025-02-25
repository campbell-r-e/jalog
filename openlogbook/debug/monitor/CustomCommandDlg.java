package openlogbook.debug.monitor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * A class to display a dialog to input parameters for custom commands.
 */
class CustomCommandDlg extends JDialog {
   /**
    * Creates a new CustomCommandDlg.
    *
    * @param frame       parent frame
    * @param title       title for the dialog
    * @param modal       true if modal
    * @param parameters  comma separated list of parameter names
    *
    */
   public CustomCommandDlg(Frame frame, String title, boolean modal, String parameters) {
      super(frame, title, modal);
      _parameters = parameters;
      try {
         jbInit();
         pack();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets an array of input parameters.
    *
    *
    * @return an array of input parameters
    */
   public String[] getParmResults() {
      String[] result = new String[_parmCount];
      for (int idx = 0; idx < _parmCount; idx++) {
         result[idx] = (String)_tableData[idx][1];
      }
      return result;
   }

   /**
    * Returns true if dialog data was accepted (ie, OK was pushed).
    *
    *
    * @return true if dialog data was accepted (ie, OK was pushed).
    */
   public boolean isDialogAccepted() {
      return _okPushed;
   }

   //**********
   // Private methods
   //**********

   /**
    *  JBuilder initialization.
    *
    * @throws Exception on any exception
    */
   private void jbInit() throws Exception {
      _panel1.setLayout(_borderLayout1);
      _cancelButton.setText("Cancel");
      _cancelButton.setPreferredSize(new Dimension(80, 24));
      _cancelButton.setRolloverEnabled(true);
      _cancelButton.setToolTipText("Click to cancel");
      _cancelButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cancelButtonActionPerformed(e);
         }
      });
      _okButton.setText("jButton2");
      _okButton.setPreferredSize(new Dimension(80, 24));
      _okButton.setText("OK");
      _okButton.setToolTipText("Click to execute the command");
      _okButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(ActionEvent e) {
            okButtonActionPerformed(e);
         }
      });
      getContentPane().add(_panel1);
      _panel1.add(_buttonPanel, BorderLayout.SOUTH);
      _buttonPanel.add(_okButton, null);
      _buttonPanel.add(_cancelButton, null);
      _panel1.add(_jScrollPane1, BorderLayout.CENTER);
      _jScrollPane1.getViewport().add(_jTable1, null);

      StringTokenizer tokenizer = new StringTokenizer(_parameters, ",");
      _parmCount = tokenizer.countTokens();
      _tableData = new String[_parmCount][2];
      for (int idx = 0; tokenizer.hasMoreTokens(); idx++) {
         _tableData[idx][0] = tokenizer.nextToken(",");
         _tableData[idx][1] = "";
      }

      // Create a model of the data.
      _jTable1.setModel(new ParameterTableModel());
   }

   /**
    * Handle the OK button.
    *
    * @param e an ActionEvent (unused)
    */
   private void okButtonActionPerformed(ActionEvent e) {
      _jTable1.getCellEditor().stopCellEditing();
      _okPushed = true;
      setVisible(false);
      dispose();
   }

   /**
    * handles the Cancel Button.
    *
    * @param e  an ActionEvent (unused)
    */
   private void cancelButtonActionPerformed(ActionEvent e) {
      setVisible(false);
      dispose();
   }

   private class ParameterTableModel extends AbstractTableModel {

      static final long serialVersionUID = 1744025731477959195L;

      //**********
      // Override methods from AbstractTableModel
      //**********

      /**
       * Gets the number of columns.
       *
       * @return the number of columns.
       */
      public int getColumnCount() {
         return 2 ;
      }

      /**
       * Gets the number of rows.
       *
       * @return the number of rows.
       */
      public int getRowCount() {
         return _tableData.length ;
      }

      /**
       * Returns the value for the cell at <code>column</code> and
       * <code>row</code>.
       *
       * @param   row      the row whose value is to be queried
       * @param   column   the column whose value is to be queried
       *
       * @return  the value Object at the specified cell
       */
      public Object getValueAt(int row, int column) {
         try {
            return _tableData[row][column];
         } catch (Exception e) {
            return "";
         }
      }

      /**
       * Returns the name of the column at <code>column</code>.
       *
       * @param   column   the index of the column
       *
       * @return  the name of the column
       */
      public String getColumnName(int column) {
         return _columnNames[column];
      }

      /**
       * Returns the most specific superclass for all the cell values
       * in the column.  This is used by the <code>JTable</code> to set up a
       * default renderer and editor for the column.
       *
       * @param column  the index of the column
       * @return the common ancestor class of the object values in the model.
       */
      public Class<?> getColumnClass(int column) {
         return getValueAt(0, column).getClass();
      }

      /**
       * Returns true if the cell at <code>row</code> and
       * <code>column</code>
       * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
       * change the value of that cell.
       *
       * @param   row     the row whose value to be queried
       * @param   column   he column whose value to be queried
       *
       * @return  true if the cell is editable
       */
      public boolean isCellEditable(int row, int column) {
         return column == 1;
      }

      /**
       * Sets the value in the cell at <code>column</code> and
       * <code>row</code> to <code>aValue</code>.
       *
       * @param   aValue  the new value
       * @param   row     the row whose value is to be changed
       * @param   column  the column whose value is to be changed
       *
       * @see #getValueAt
       * @see #isCellEditable
       */
      public void setValueAt(Object aValue, int row, int column) {
         _tableData[row][column] = aValue;
      }
   }

   //**********
   // Class attributes and constants
   //**********

   private JPanel       _panel1 = new JPanel();
   private BorderLayout _borderLayout1 = new BorderLayout();
   private JPanel       _buttonPanel = new JPanel();
   private JButton      _cancelButton = new JButton();
   private JButton      _okButton = new JButton();
   private JScrollPane  _jScrollPane1 = new JScrollPane();
   private JTable       _jTable1 = new JTable();

   private String       _parameters;
   private Object[][]   _tableData;
   private String[]     _columnNames = { "Parameter Name", "Value" };
   private int          _parmCount;

   private boolean      _okPushed = false;

   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.CustomCommandDlg
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 3253734708899184896L;

}

// End of CustomCommandDlg.java
