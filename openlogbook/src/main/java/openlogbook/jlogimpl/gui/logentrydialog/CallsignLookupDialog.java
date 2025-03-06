package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.common.GenericDialog;
import openlogbook.jlogimpl.remotecallsign.callookreader.CallookReaderThread;
import openlogbook.util.ScreenUtil;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Dialog box for looking up callsign information from a remote site.
 * 
 * @author KC0ZPS
 */
public class CallsignLookupDialog extends GenericDialog implements Debugable {
   
   private JPanel    _parentPanel ;
   
   private JPanel    _mainPanel = new JPanel() ;
   private JPanel    _buttonPanel = new JPanel() ;
   private JTextArea _textArea = new JTextArea() ;
   private JButton   _closeButton = new JButton("Close") ;
   
   private ActionListener _closeButtonActionListener ;

   private static CallookReaderThread _callookReaderThread = new CallookReaderThread() ;

   static final long serialVersionUID = -307012856723636200L;
   
   /**
    * Creates a new CallsignLookupDialog.
    * 
    * @param parentDialog The parent dialog box.
    * @param parentPanel The parent panel.
    */
   public CallsignLookupDialog(Dialog parentDialog, JPanel parentPanel) {
      super(parentDialog, "Options", false);

      _parentPanel = parentPanel ;
      
      initComponents();
      
      Debug.addObject(this, _callookReaderThread) ;
   }
   
   /**
    * Makes the component visible or invisible.
    * 
    * @param b true if visible, false if invisible.
    * @param callsign the callsign to assign to this dialog box.
    */
   public void setVisible(boolean b, String callsign) {
      _callookReaderThread.setCallsign(callsign, _textArea) ;
      new Thread(_callookReaderThread).start() ;
      
      ScreenUtil.centerOnPanel(_parentPanel, this) ;
      super.setVisible(b) ;
   }
   
   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a
    *         table
    */
   public DebugTable getDebugTable() {
      String columnNames[] = { "Name", "Value" };
      PropertyDebugTable contents = new PropertyDebugTable("CallsignLookupDialog", columnNames);

      contents.addEntry("closeButtonActionListener", _closeButtonActionListener) ;
      contents.addEntry("callookReaderThread", _callookReaderThread) ;

      return contents;
   }

   //**********
   // Methods overridden from Object
   //**********
   
   /**
    * Returns a string representation of the object.
    * 
    * @return string representation of the object.
    */
   public String toString() {
      return "CallsignLookupDialog" ;
   }

   //**********
   // Methods inherited from GenericDialog
   //**********   

   /**
    * Cleanup this dialog box on close.
    */
   protected void cleanup() {
      removeGenericListeners() ;
   }

   /**
    * Called when the window is closing.
    * 
    * @param e The WindowEvent.
    */
   protected void handleWindowClosingEvent(WindowEvent e) {
      closeButtonActionPerformed() ;
   }

   /**
    * Initializes all listeners in this dialog box.
    */
   protected void addListeners() {
      _closeButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            closeButtonActionPerformed();
         }
      } ;
      _closeButton.addActionListener(_closeButtonActionListener);      
   }
   
   /**
    * Removes listeners defined in this class.
    */
   protected void removeListeners() {
      _closeButton.removeActionListener(_closeButtonActionListener) ;
   }
   
   /**
    * Updates the dialog box UI.
    * 
    * @param themeUpdateEvent The themeUpdateEvent.
    */
   protected void updateDialogUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
   }

   //**********
   // Private methods
   //**********
   
   /**
     * Initilization Method
     */
   private void initComponents() {
      Container contentPane = this.getContentPane();
      contentPane.setLayout(new BorderLayout());

      setSize(400, 300) ;
      setTitle("Callsign Lookup");
      setResizable(true);
      getRootPane().setDefaultButton(_closeButton);
      _closeButton.requestFocus() ;
      
      _mainPanel.setLayout(new BorderLayout()) ;
      _mainPanel.add(new JScrollPane(_textArea), BorderLayout.CENTER) ;
      _mainPanel.add(_buttonPanel, BorderLayout.SOUTH) ;

      _buttonPanel.add(_closeButton) ;
      
      contentPane.add(_mainPanel, BorderLayout.CENTER) ;
      
      addGenericListeners();      
   }
   
   /**
    * Performs action when clicking close
    */
   private void closeButtonActionPerformed() {
      setVisible(false) ;
   }
  
}
