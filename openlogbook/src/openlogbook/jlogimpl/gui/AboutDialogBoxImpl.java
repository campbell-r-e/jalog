package openlogbook.jlogimpl.gui;

import openlogbook.jlog.gui.AboutDialogBox;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.util.ApiControl;
import openlogbook.util.UpdateManager;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 * This is the about dialog box. 
 * 
 * @author KC0ZPS
 */
public class AboutDialogBoxImpl implements AboutDialogBox {

   private JFrame                 _parentFrame ;
   static private final ImageIcon _imageIcon = new ImageIcon("resource.jar") ;

   static private JOptionPane     _pane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, 
                                                          JOptionPane.DEFAULT_OPTION, _imageIcon) ;
   static private JDialog         _dialog ;

   /**
    * Creates a new AboutDialogBox
    *
    * @param parent The parent frame.
    */
   public AboutDialogBoxImpl(JFrame parent) {
      _parentFrame = parent ;
      initComponents();
      UpdateManager.getInstance().addThemeUpdateListener(this) ;
   }

   /**
    * Makes the dialog visible.
    */
   public void setVisible() {
      _dialog.setVisible(true) ;
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
      SwingUtilities.updateComponentTreeUI(_pane);
   }

   //**********
   // Private methods
   //**********

   /**
    * Initializes the components within this dialog box.
    */
   private void initComponents() {
      StringBuffer aboutString = new StringBuffer() ;
      aboutString.append("openlogbook " + ApiControl.getVersion()) ;
      aboutString.append("Legacy information Jalog (c) Copyright 2007.  All Rights Reserved.\n") ;
      aboutString.append("\n") ;
      aboutString.append("Author:\n") ;
      aboutString.append("     Ron Kinney(KC0ZPS)\n") ;
      
      _pane.setMessage(aboutString) ;
      _dialog = _pane.createDialog(_parentFrame, "Warning");
   }
   
   

}