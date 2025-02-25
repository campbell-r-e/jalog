package openlogbook.jlog.gui.notepad;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JInternalFrame;

/**
 * An abstract class defining the notepad frame. 
 * 
 * @author KC0ZPS
 */
public abstract class AbstractNotepadFrame extends JInternalFrame {

   /**
    * Creates a new AbstractNotepadFrame.
    */
   public AbstractNotepadFrame() {
      super("Notepad", true, true, true, true);
   }

   /**
    * Reads text from the file.
    * 
    * @throws IOException if an I/O exception occurs.
    * @throws FileNotFoundException if the file isn't found.
    */
   public abstract void readText() throws IOException, FileNotFoundException ;
   
   /**
    * Saves the text to the file.
    */
   public abstract void saveText() ;
}
