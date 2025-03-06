package openlogbook.jlog.gui;

import openlogbook.debug.Debugable;
import openlogbook.jlog.logbook.LogBook;

import java.io.File;

import javax.swing.JInternalFrame;

/**
 * An abstract class defining a LogBookFrame.
 * 
 * @author KC0ZPS
 */
public abstract class AbstractLogBookFrame extends JInternalFrame implements ThemeUpdateListener, Debugable {

   /**
    * Creates new form AreaEditorDialog.
    *
    * @param filename the name of the file.
    */
   public AbstractLogBookFrame(String filename) {
      super(filename, true, true, true, true);
   }
   
   /**
    * Checks if the data for this frame has changed since last save.
    * 
    * @return true if the data for this frame has changed since last save.
    */
   public abstract boolean hasDataChanged() ;
   
   /**
    * Sets the data changed flag for this frame.
    * 
    * @param title The title of this frame.
    * @param value The changed flag.
    */
   public abstract void setDataChanged(String title, boolean value) ;
   
   /**
    * Gets the file for this log book.
    * 
    * @return the file for this log book.
    */
   public abstract File getFile() ;
   
   /**
    * Sets the file for this log book.
    * 
    * @param file the file for this log book.
    */
   public abstract void setFile(File file) ;
   
   /**
    * Gets the LogBook object represented by this frame.
    * 
    * @return the LogBook object represented by this frame.
    */
   public abstract LogBook getLogBook() ;
   
   /**
    * Cleans this frame up.  This method should be called when this object is no longer needed. 
    */
   public abstract void cleanup() ;
}
