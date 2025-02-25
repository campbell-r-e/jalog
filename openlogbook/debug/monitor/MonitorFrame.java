package openlogbook.debug.monitor;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *  Frame for the debug monitor window.
 */
class MonitorFrame extends JFrame {

   /**
    * Creates a new MonitorFrame
    * 
    * @param terminalModel the TerminalModel for the terminal window
    * 
    */
   public MonitorFrame(TerminalModel terminalModel) {
      super() ;
      _terminalModel = terminalModel ;
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
         jbInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //**********
   // Private methods
   //**********

   /**
    * JBuilder initialization
    *
    * @throws Exception on any exception
    */
   private void jbInit() throws Exception{
      setTitle("Monitor Window");
      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE) ;
      _terminalWindow = new TerminalControl(_terminalModel) ;
      _outputPanel.setLayout(new BorderLayout()) ;
      _saveButton.setText("Save") ;
      _saveButton.setRolloverEnabled(true) ;
      _saveButton.setToolTipText("Click to save to a file") ;
      _saveButton.addActionListener(new java.awt.event.ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                          saveButtonActionPerformed() ;
                                       }
                                    });
      _clearButton.setText("Clear") ;
      _clearButton.setRolloverEnabled(true) ;
      _clearButton.setToolTipText("Click to clear contents") ;
      _clearButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           clearButtonActionPerformed() ;
                                        }
                                     });
      getContentPane().setLayout(new BorderLayout()) ;
      getContentPane().add(_outputPanel, BorderLayout.CENTER) ;
      _outputPanel.add(_terminalWindow, BorderLayout.CENTER) ;
      _outputPanel.add(_outputButtonPanel, BorderLayout.SOUTH) ;
      _outputButtonPanel.add(_saveButton, null) ;
      _outputButtonPanel.add(_clearButton, null) ;
      _outputButtonPanel.setLayout(new FlowLayout()) ;

      setSize(new Dimension(656, 444)) ;
      URL imageUrl = ClassLoader.getSystemResource("openlogbook/console/debug/debug.gif") ;
      if (imageUrl != null) {
         ImageIcon imageIcon = new ImageIcon(imageUrl) ;
         if (imageIcon != null) {
            setIconImage(imageIcon.getImage()) ;
         }
      }
   }

   /**
    * Called when the clear button is pressed.
    *
    */
   private void clearButtonActionPerformed() {
      _terminalModel.clear() ;
   }

   /**
    * Saves the contents of the terminal window to a file.
    *
    */
   private void saveButtonActionPerformed() {
      JFileChooser chooser = new JFileChooser(".") ;
      chooser.setDialogTitle("Save Window Contents") ;
      int returnVal = chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         _terminalModel.saveToFile(chooser.getSelectedFile()) ;
      }
   }

   //**********
   // Class attributes and constants
   //**********

   private JPanel                   _outputPanel = new JPanel();
   private JPanel                   _outputButtonPanel = new JPanel();
   private JButton                  _saveButton = new JButton();
   private JButton                  _clearButton = new JButton();
   private TerminalControl          _terminalWindow ;
   private transient TerminalModel  _terminalModel ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.MonitorFrame
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 5510542393167595940L;

}


// end of MonitorFrame.java

