package openlogbook.factory;

import openlogbook.Genesis;
import openlogbook.jlog.gui.AboutDialogBox;
import openlogbook.jlog.gui.AbstractLogBookFrame;
import openlogbook.jlog.gui.AbstractWindowMenu;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.notepad.AbstractNotepadFrame;
import openlogbook.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.util.ThemeUpdateEventId;
import openlogbook.jlogimpl.gui.AboutDialogBoxImpl;
import openlogbook.jlogimpl.gui.LogBookFrame;
import openlogbook.jlogimpl.gui.ThemeUpdateEventImpl;
import openlogbook.jlogimpl.gui.WindowMenu;
import openlogbook.jlogimpl.gui.notepad.NotepadFrame;
import openlogbook.jlogimpl.gui.optiondialog.OptionsDialogBox;

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
