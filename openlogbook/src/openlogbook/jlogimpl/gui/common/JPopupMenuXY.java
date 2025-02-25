package openlogbook.jlogimpl.gui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPopupMenu;

/**
 * A JPopupMenu that doesn't open the popup menu beyond the borders of the screen.
 * 
 * @author KC0ZPS
 */
public class JPopupMenuXY extends JPopupMenu {

   static final long serialVersionUID = 1886215344152556833L;

   /**
    * Creates a new JPopupMenuXY
    */
   public JPopupMenuXY() {
      super();
   } // End of JPopupMenuXY() constructor

   /**
    * Displays the menu for the current port
    *
    * @param invoker The component in whose space the popupmenu is to appear
    * @param x The x coordinate in invoker's coordinate space at which popup menu is to be displayed
    * @param y The y coordinate in invoker's coordinate space at which popup menu is to be displayed
    */
   public void show(Component invoker, int x, int y) {
      int padX = 5;
      int padY = 15;

      // Pad a couple of pixels up front to ensure menu is slightly away from mouse position passed in
      x += 2;
      y += 2;

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension menuSize = getSize();
      Point parentLoc = invoker.getLocationOnScreen();
      if (menuSize.width == 0) {
         menuSize = getPreferredSize();
      }
      if (parentLoc.x + x + menuSize.width + padX > screenSize.width) {
         x = (screenSize.width - menuSize.width) - parentLoc.x - padX; // Get new position relative to invoker
      }
      if (parentLoc.y + y + menuSize.height + padY > screenSize.height) {
         y = (screenSize.height - menuSize.height) - parentLoc.y - padY;
      }
      super.show(invoker, x, y);

      // A quick solution to an issue with popup menus not getting focus.  JDK1.3.1_01+ needed.
      this.requestFocus();
      this.grabFocus();

      return;
   }

}