package openlogbook.debug.monitor;

import openlogbook.debug.LogDebugTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Provides support for a popup dialog displayed when a user "drills down" into
 * a history log entry.
 */
class HistoryDrillDownDialog extends JDialog {
   /**
    * Creates a new HistoryDrillDownDialog.
    *
    * @param frame           parent frame
    * @param title           title for the dialog
    * @param contentPanel    Panel containing log-specific contents
    * @param contents        the log contents
    * @param row             current row in the log
    */
   public HistoryDrillDownDialog(HistoryLogFrame frame,
                                 String title,
                                 DrillDownPanel contentPanel,
                                 LogDebugTable contents,
                                 int row) {
      super(frame, title, true);
      _frame = frame ;
      _contentPanel = contentPanel ;
      _contents = contents ;
      _row = row ;
      _contentPanel.setContents(_contents, row) ;
      try {
         jbInit();
         pack();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //**********
   // Private methods
   //**********

   /**
    * Jbuilder initialization.
    *
    * @throws Exception on any exception
    */
   private void jbInit() throws Exception {
      _closeButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           closeButtonActionPerformed();
                                        }
                                     });
      _nextButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           nextButtonActionPerformed();
                                        }
                                     });
      _previousButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           previousButtonActionPerformed();
                                        }
                                     });
      _mainPanel.setLayout(new BorderLayout());
      _panel1.setLayout(new BorderLayout());
      _panel1.add(_buttonPanel, BorderLayout.SOUTH);
      _buttonPanel.add(_nextButton, null) ;
      _buttonPanel.add(_previousButton, null) ;
      _buttonPanel.add(_closeButton, null);
      _panel1.add(_mainPanel, BorderLayout.CENTER);
      _mainPanel.add(_contentPanel, BorderLayout.CENTER);
      getContentPane().add(_panel1);
   }

   /**
    * Called when the close button is pressed.
    *
    */
   private void closeButtonActionPerformed() {
      dispose() ;
   }

   /**
    * Called when the Next button is pressed. Moves to the next NMRU.
    *
    */
   private void nextButtonActionPerformed() {
      if (_row < _contents.getRowCount() - 1) {
         _row = _row + 1 ;
         _frame.selectRow(_row) ;
         _contentPanel.setContents(_contents, _row) ;
      }
   }
   
   /**
    * Called when the Previous button is pressed. Moves to the previous NMRU.
    *
    */
   private void previousButtonActionPerformed() {
      if (_row > 0) {
         _row = _row - 1 ;
         _frame.selectRow(_row) ;
         _contentPanel.setContents(_contents, _row) ;
      }
   }
   
   //**********
   // Class attributes and constants
   //**********

   private HistoryLogFrame _frame ;
   private JPanel          _panel1 = new JPanel();
   private JPanel          _buttonPanel = new JPanel();
   private JPanel          _mainPanel = new JPanel();
   private JButton         _closeButton = new JButton("Close");
   private JButton         _nextButton = new JButton("Next") ;
   private JButton         _previousButton = new JButton("Previous") ;
   private DrillDownPanel  _contentPanel ;
   private LogDebugTable   _contents ;
   private int             _row ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.HistoryDrillDownDialog
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 5628662730708134125L;

}


// End of HistoryDrillDownDialog.java

