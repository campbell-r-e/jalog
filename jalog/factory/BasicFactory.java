package jalog.factory;

import jalog.Genesis;
import jalog.jlog.gui.AboutDialogBox;
import jalog.jlog.gui.AbstractLogBookFrame;
import jalog.jlog.gui.AbstractWindowMenu;
import jalog.jlog.gui.ThemeUpdateEvent;
import jalog.jlog.gui.notepad.AbstractNotepadFrame;
import jalog.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.util.ThemeUpdateEventId;
import jalog.jlogimpl.gui.AboutDialogBoxImpl;
import jalog.jlogimpl.gui.LogBookFrame;
import jalog.jlogimpl.gui.ThemeUpdateEventImpl;
import jalog.jlogimpl.gui.WindowMenu;
import jalog.jlogimpl.gui.notepad.NotepadFrame;
import jalog.jlogimpl.gui.optiondialog.OptionsDialogBox;

import java.io.File;

import javax.swing.JFrame;


/**
 * This factory creates basic jlog components.
 * 
 * @author KC0ZPS
 */
public class BasicFactory {

   /**
    * Creates a new BasicFactory.  The constructor is private.  This object should not be instantiated.
    */
   private BasicFactory() {}

   /**
    * Creates a new AboutDialogBox.
    * 
    * @param parent The parent frame.
    * @return a AboutDialogBoxImpl.
    */
   public static AboutDialogBox createAboutDialogBox(JFrame parent) {
      return new AboutDialogBoxImpl(parent) ;
   }
 
   /**
    * Creates a new AbstractLogBookFrame.
    * 
    * @param genesis The application frame.
    * @param logBook The logbook being edited.
    * @param file The file for this log book.
    * @param optionsDialogBox The OptionsDialogBox.
    * 
    * @return a LogBookFrame.
    */
   public static AbstractLogBookFrame createLogBookFrame(Genesis genesis, 
         LogBook logBook, 
         File file, 
         AbstractOptionsDialogBox optionsDialogBox) {
      return new LogBookFrame(genesis, logBook, file, optionsDialogBox);
   }
   
   /**
    * Creates a AbstractWindowMenu.
    * 
    * @return a WindowMenu.
    */
   public static AbstractWindowMenu createWindowMenu() {
      return new WindowMenu() ;
   }
   
   /**
    * Creates a AbstractNotepadFrame.
    * 
    * @return a NotepadFrame.
    */
   public static AbstractNotepadFrame createNotepadFrame() {
      return new NotepadFrame() ;
   }
   
   /**
    * Creates a AbstractOptionsDialogBox.
    * 
    * @param parent the parent frame.
    * 
    * @return a OptionsDialogBox.
    */
   public static AbstractOptionsDialogBox createOptionsDialogBox(JFrame parent) {
      return new OptionsDialogBox(parent);
   }
   
   /**
    * Creates a ThemeUpdateEvent.
    * 
    * @param eventId The event id.
    * @param description The description of the event.
    * 
    * @return a ThemeUpdateEventImpl.
    */
   public static ThemeUpdateEvent createThemeUpdateEvent(ThemeUpdateEventId eventId, String description) {
      return new ThemeUpdateEventImpl(eventId, description) ;
   }
}
