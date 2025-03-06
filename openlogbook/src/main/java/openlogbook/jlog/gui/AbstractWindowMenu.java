package openlogbook.jlog.gui;

import javax.swing.JMenu;

/**
 * An abstract class defining a WindowMenu. 
 * 
 * @author KC0ZPS
 */
public abstract class AbstractWindowMenu extends JMenu {

   /**
    * Adds a log book entry to the menu.
    * 
    * @param frame The frame thats associated with the menu item/
    */
   public abstract void addLogBookEntry(AbstractLogBookFrame frame) ;
   
   /**
    * Removes the menu item associated with the frame from the windows menu.
    * 
    * @param frame The frame to remove from the menu.
    */
   public abstract void removeLogBookEntry(AbstractLogBookFrame frame) ;
   
   /**
    * Updates the name of the menu item.
    * 
    * @param frame The frame associated with the menu item to update.
    */
   public abstract void updateMenuItem(AbstractLogBookFrame frame) ;
}
