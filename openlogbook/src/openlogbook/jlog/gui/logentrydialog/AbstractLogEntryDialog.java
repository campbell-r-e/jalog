package openlogbook.jlog.gui.logentrydialog;

import openlogbook.debug.Debugable;
import openlogbook.jlog.gui.common.GenericDialog;
import openlogbook.jlog.logbook.jlogentry.LogEntry;

import javax.swing.JFrame;

/**
 * An abstract class that defines a LogEntryDialog.
 * 
 * @author KC0ZPS
 */
public abstract class AbstractLogEntryDialog extends GenericDialog implements Debugable {

   /**
    * Creates a new default LogEntryDialog.
    * 
    * @param parent The parent frame.
    * @param title The title of this dialog box.
    */
   public AbstractLogEntryDialog(JFrame parent, String title) {
      super(parent, title, true) ;
   }
   
   /**
    * Returns true if the data is valid and the user has confirmed entry into the log.
    * 
    * @return true if the data is valid and the user has confirmed entry into the log.
    */
   public abstract boolean isDataValid() ;
   
   /**
    * Sets the LogEntry for this dialog.
    * 
    * @param entry the LogEntry.
    */
   public abstract void setLogEntry(LogEntry entry) ;
   
   /**
    * Getst the LogEntry object.
    * 
    * @return the LogEntry object.
    */
   public abstract LogEntry getLogEntry() ;
   
   /**
    * Cleans this dialog up.  This should be called when the dialog is no longer needed.
    */
   public abstract void cleanup() ;
}
