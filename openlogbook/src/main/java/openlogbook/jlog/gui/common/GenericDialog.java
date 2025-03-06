package openlogbook.jlog.gui.common;

import openlogbook.factory.BasicFactory;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.ThemeUpdateListener;
import openlogbook.jlog.util.ThemeUpdateEventId;
import openlogbook.util.UpdateManager;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


/**
 * This is an abstract class to handle generic functions of a dialog box.
 * 
 * @author KC0ZPS
 */
public abstract class GenericDialog extends JDialog implements ThemeUpdateListener {

   private Component      _parent ;
   private ActionListener _closeActionListener ;
   private WindowListener _windowListener ;
   private boolean        _showWarning ;
   
   // BUG -- Create the optionpane when the dialog is created, not when the button is pressed.
   // Otherwise, the optionpane may show an empty dialog.
   static private JOptionPane    _optionPane = new JOptionPane("Are you sure?", JOptionPane.QUESTION_MESSAGE, 
                                                               JOptionPane.YES_NO_OPTION) ;
   static private JDialog        _dialog ;

   private static final KeyStroke _escKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
   
   static final long serialVersionUID = 4622733121859831207L;

   /**
    * Creates a new GenericDialog.
    * 
    * @param parent The parent frame.
    * @param title The title of this dialog box.
    * @param showWarningOnExit Set to true if this dialog should warn the user before closing.
    */
   public GenericDialog(JFrame parent, String title, boolean showWarningOnExit) {
      super(parent, title, true) ;
      
      initialize(parent, title, showWarningOnExit) ;
   }
   
   public GenericDialog(Dialog parent, String title, boolean showWarningOnExit) {
      super(parent, title, true) ;

      initialize(parent, title, showWarningOnExit) ;
   }
   
   //**********
   // Methods overwritten from JDialog.
   //**********   
   
   /**
    * Override the createRootPane for JDialog to allow the escape button to close the dialog.
    * 
    * @return A JRootPane where the escape key will close the dialog.
    */
   protected JRootPane createRootPane() {
      _closeActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent actionEvent) {
            genericCancelButtonActionPerformed();
         }
      };

      JRootPane rootPane = new JRootPane();
      rootPane.registerKeyboardAction(_closeActionListener, _escKeyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
      return rootPane;
   }

   //**********
   // Methods inherited from ThemeUpdateListener.
   //**********
   
   /**
    * Invoked when an action occurs.
    * 
    * @param themeUpdateEvent The event associated with this update.
    */
   public void updateUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
      SwingUtilities.updateComponentTreeUI(_optionPane);
      updateDialogUI(themeUpdateEvent) ;
   }
   
   //**********
   // Protected methods.
   //**********      
   
   /**
    * Adds listeners to this dialog.
    */
   protected void addGenericListeners() {
      UpdateManager.getInstance().addThemeUpdateListener(this) ;
      addListeners() ;
   }
   
   /**
    * Removes listeners from this dialog.
    */
   protected void removeGenericListeners() {
      UpdateManager.getInstance().removeThemeUpdateListener(this) ;
      this.getRootPane().unregisterKeyboardAction(_escKeyStroke) ;
      this.removeWindowListener(_windowListener) ;
      removeListeners() ;
   }
   
   /**
    * Performs action when clicking cancel
    */
   protected void genericCancelButtonActionPerformed() {
      if (_showWarning) {
         _dialog.setVisible(true);
         Integer selectedValue = (Integer)_optionPane.getValue();
         if (selectedValue.intValue() == 0) {
            setVisible(false) ;
         }      
      } else {
         setVisible(false) ;         
      }
   }

   /**
    * Sets the show warning dialog box flag when clicking cancel.
    * 
    * @param showWarning True if a warning needs to be displayed, false otherwise.
    */
   protected void setShowWarning(boolean showWarning) {
      _showWarning = showWarning ;
   }
   
   //**********
   // Private methods.
   //**********      
   
   /**
    * Initialize this component.
    * 
    * @param parent The parent component.
    * @param title The title of this dialog.
    * @param showWarningOnExit Set to true if the dialog box should warn on close.
    */
   private void initialize(Component parent, String title, boolean showWarningOnExit) {
      _parent = parent ;
      _dialog = _optionPane.createDialog(_parent, "Confirm");
      _showWarning = showWarningOnExit ;
      initializeListeners() ;
      
      updateUI(BasicFactory.createThemeUpdateEvent(ThemeUpdateEventId.ComponentConstruction, "Creating GenericDialog")) ;
   }
   
   /**
    * Initialize the listeners of this dialog.
    */
   private void initializeListeners() {
      _windowListener = new WindowListener() {
         public void windowClosing(WindowEvent e) {
            handleWindowClosingEvent(e) ;
         }
         public void windowActivated(WindowEvent e) {}
         public void windowClosed(WindowEvent e) {}
         public void windowDeactivated(WindowEvent e) {}
         public void windowDeiconified(WindowEvent e) {}
         public void windowIconified(WindowEvent e) {}
         public void windowOpened(WindowEvent e) {} 
      } ;
      this.addWindowListener(_windowListener) ;
   }

   //**********
   // Abstract methods.
   //**********      

   /**
    * Cleanup this dialog box on close.
    */
   abstract protected void cleanup() ;
   
   /**
    * Add any additional listeners to this dialog. 
    */
   abstract protected void addListeners() ;
   
   /**
    * Remove the additional listeners from this dialog.
    */
   abstract protected void removeListeners() ;

   /**
    * Updates the dialog box UI.
    * 
    * @param themeUpdateEvent The themeUpdateEvent.
    */
   abstract protected void updateDialogUI(ThemeUpdateEvent themeUpdateEvent) ;
 
   /**
    * Any actions that need to occur when the window is closed.
    * 
    * @param e The WindowEvent.
    */
   abstract protected void handleWindowClosingEvent(WindowEvent e) ;
   
}
