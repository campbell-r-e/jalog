package openlogbook.jlogimpl.gui;

import openlogbook.debug.Debug;
import openlogbook.jlog.gui.AbstractLogBookFrame;
import openlogbook.jlog.gui.AbstractWindowMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import javax.swing.JMenuItem;


/**
 * This class represents the Window menu item for the main application.  It handles all functionality
 * associated with any menu items under the Window menu.
 * 
 * @author KC0ZPS
 */
public class WindowMenu extends AbstractWindowMenu {

   private HashMap<AbstractLogBookFrame, JMenuItem> _hashMap = new HashMap<AbstractLogBookFrame, JMenuItem>() ;
   private JMenuItem _emptyMenuItem = new JMenuItem("<empty>") ;
   
   static final long serialVersionUID = -8587831439060875515L;
   
   /**
    * Creates a new WindowMenu.
    */
   public WindowMenu() {
      super() ;
      
      _emptyMenuItem.setEnabled(false) ;
      this.add(_emptyMenuItem) ;
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Adds a log book entry to the menu.
    * 
    * @param frame The frame thats associated with the menu item/
    */
   public void addLogBookEntry(AbstractLogBookFrame frame) {
      JMenuItem menuItem = new JMenuItem(frame.getFile().getName()) ;
      _hashMap.put(frame, menuItem) ;
      this.add(menuItem) ;
      this.remove(_emptyMenuItem) ;
      
      menuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            menuItemActionPerformed(evt) ;
         }
      });
   }
   
   /**
    * Removes the menu item associated with the frame from the windows menu.
    * 
    * @param frame The frame to remove from the menu.
    */
   public void removeLogBookEntry(AbstractLogBookFrame frame) {
      JMenuItem menuItem = _hashMap.get(frame) ;
      
      if (menuItem != null) {
         this.remove(menuItem) ; 
         _hashMap.remove(frame) ;
         
         ActionListener[] listener = menuItem.getActionListeners() ;
         for (int index = 0; index < listener.length; index++) {
            menuItem.removeActionListener(listener[index]) ;
         }
      }
      
      if (this.getItemCount() == 0) {
         this.add(_emptyMenuItem) ;
      }
   }
   
   /**
    * Updates the name of the menu item.
    * 
    * @param frame The frame associated with the menu item to update.
    */
   public void updateMenuItem(AbstractLogBookFrame frame) {
      JMenuItem menuItem = _hashMap.get(frame) ;
      
      if (menuItem != null) {
         menuItem.setText(frame.getFile().getName()) ;         
      }
   }

   //**********
   // Private methods
   //**********
   
   /**
    * Handles the action event for the menu item.
    * 
    * @param evt The ActionEvent object.  This code assumes that the event came from a LogBookFrame.
    */
   private void menuItemActionPerformed(ActionEvent evt) {
      AbstractLogBookFrame frame = null ;
      for (Iterator<Map.Entry<AbstractLogBookFrame, JMenuItem>> iter = _hashMap.entrySet().iterator(); iter.hasNext();)
      { 
          Map.Entry<AbstractLogBookFrame, JMenuItem> entry = (Map.Entry<AbstractLogBookFrame, JMenuItem>)iter.next();
          frame = (AbstractLogBookFrame)entry.getKey();
          JMenuItem value = (JMenuItem)entry.getValue();
          
          if (value.equals(evt.getSource())) {
             break ;
          }
      }
      
      if (frame != null) {
         try {
            frame.setSelected(true) ;            
         } catch (PropertyVetoException e) {
            Debug.logException(e) ;
         }
      }
   }
   
}
