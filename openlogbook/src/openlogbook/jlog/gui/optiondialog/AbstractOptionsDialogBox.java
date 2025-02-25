package openlogbook.jlog.gui.optiondialog;

import java.util.ArrayList;

import openlogbook.jlog.gui.common.GenericDialog;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlogimpl.gui.TableColumnEnum;

import javax.swing.JFrame;

/**
 * An abstract class defining common methods for a generic dialog box. 
 * 
 * @author KC0ZPS
 */
public abstract class AbstractOptionsDialogBox extends GenericDialog {

   /**
    * Creates a new AbstractOptionsDialogBox.
    * 
    * @param parent The parent frame.
    * @param title The title of this dialog box.
    * @param showWarningOnExit Set to true if this dialog should warn the user before closing.
    */
   public AbstractOptionsDialogBox(JFrame parent, String title, boolean showWarningOnExit) {
      super(parent, "Options", false);
   }
   
   /**
    * Gets the DefaultLogEntry defined in the options dialog box.
    * 
    * @return a DefaultLogEntry defined in the options dialog box.
    */
   public abstract LogEntry getDefaultLogEntry() ;
   
   /**
    * Sets the default log entry.
    * 
    * @param entry the default log entry.
    */
   public abstract void setDefaultLogEntry(LogEntry entry) ;
   
   /**
    * Checks if the custom theme selection is checked.
    * 
    * @return true if the custom theme selection is checked.
    */
   public abstract boolean isCustomTheme() ;
   
   /**
    * Sets the custom theme checked value.
    * 
    * @param value the custom theme checked value.
    */
   public abstract void setIsCustomTheme(boolean value) ;
   
   /**
    * Checks if the theme will load a background for the frame.
    * 
    * @return true if the theme will load a background for the frame.
    */
   public abstract boolean isDesktopBackground() ;
   
   /**
    * Sets the load background flag.
    * 
    * @param value the load background flag.
    */
   public abstract void setIsDesktopBackground(boolean value) ;
   
   /**
    * Checks if the theme will load XTra ScrollBars.
    * 
    * @return true if the theme will load XTra ScrollBars.
    */
   public abstract boolean isXtraScrollbars() ;
   
   /**
    * Sets the XTra Scrollbars flag.
    * 
    * @param value the XTra Scrollbars flag.
    */
   public abstract void setIsXtraScrollbars(boolean value) ;
   /**
    * Returns the selected theme.
    * 
    * @return the selected theme.
    */
   public abstract String getSelectedTheme() ;
   
   /**
    * Sets the selected theme.
    * 
    * @param theme the theme to selected.
    */
   public abstract void setSelectedTheme(String theme) ;
   
   /**
    * Cleans up this dialog box.
    */
   public abstract void cleanup() ;

   /**
    * Tests if the ok button was pressed.
    * 
    * @return true if ok was pressed.
    */
   public abstract boolean okPressed() ;
   
   /**
    * Gets the column list that the user wants to display.
    * 
    * @return the column list that the user wants to display.
    */
   public abstract ArrayList<TableColumnEnum> getColumnList() ;
   
   /**
    * Adds a column to the column array.
    * 
    * @param tableColumnEnum The column enumeration to add.
    */
   public abstract void addColumn(TableColumnEnum tableColumnEnum) ;
}
