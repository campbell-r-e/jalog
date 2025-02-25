package openlogbook.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Title: ScreenUtil.java</p>
 * <p>Description: Basic screen utilities.</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * @author KC0ZPS
 * @version 1.0
 */
public class ScreenUtil {

   /**
    * Creates a new ScreenUtil.  This constructor should not be used.
    */
   private ScreenUtil() {
   }
   
   /**
    *  This method will center a frame on the screen
    * 
    *  @param frame - the frame to be centered
    */
   public static void centerOnScreen(JFrame frame) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();
      if (frameSize.height > screenSize.height) {
         frameSize.height = screenSize.height;         
      }
      if (frameSize.width > screenSize.width) {
         frameSize.width = screenSize.width;         
      }
      frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
   }
   
   /**
    *  Makes sure dialog can fit on screen -- edges won't be clipped
    * 
    *  @param dlg - the dialog to be relocated
    *  @param dialogLocation - dialogs current location
    */
   public static void fitOnScreen(JDialog dlg, Point dialogLocation) {
      int padding = 15;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int widthOfScreen = screenSize.width;
      int heightOfScreen = screenSize.height;
      int widthOfComponent = dlg.getSize().width;
      int heightOfComponent = dlg.getSize().height;
      Point currentLocation = dialogLocation;
      // make sure the component fits on the screen vertically    
      if ((currentLocation.y + heightOfComponent) > heightOfScreen) {
         currentLocation.y = (currentLocation.y - ((currentLocation.y + heightOfComponent) - heightOfScreen)) - padding;
      }
      if (currentLocation.y < 0) {
         currentLocation.y = 0;
      }
      // make sure the component fits on the screen horizontally
      if ((currentLocation.x + widthOfComponent) > widthOfScreen) {
         currentLocation.x = (currentLocation.x - ((currentLocation.x + widthOfComponent) - widthOfScreen)) - padding;
      }
      if (currentLocation.x < 0) {
         currentLocation.x = padding;
      }
      // re-position component on screen
      dlg.setLocation(currentLocation);
   }

   /**
    *  This method will center a component on the screen
    * 
    *  @param c - the component to be centered
    *
    */
   public static void centerOnScreen(Component c) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension cSize = c.getSize();
      if (cSize.height > screenSize.height) {
         cSize.height = screenSize.height;         
      }
      if (cSize.width > screenSize.width) {
         cSize.width = screenSize.width;         
      }
      c.setLocation((screenSize.width - cSize.width) / 2, (screenSize.height - cSize.height) / 2);
   }

   /**
    *  This method will find the center of the screen
    *
    *  @return    returns the center of the screen coordinates
    */
   public static Dimension getCenterScreenCoords() {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      return (new Dimension(screenSize.width / 2, screenSize.height / 2));
   }
   
   /**
    * This method will center a frame on another frame
    * 
    * @param parent - the frame to be centered on
    * @param child - the frame to be centered
    *
    */
   public static void centerOnFrame(JFrame parent, JFrame child) {
      Point parentLocation = parent.getLocation();
      Dimension parentSize = parent.getSize();
      int xCoord = parentLocation.x + parentSize.width / 2;
      int yCoord = parentLocation.y + parentSize.height / 2;
      child.setLocation(xCoord, yCoord);
   }
   
   /**
    *  This method will center a dialog on a frame.
    * 
    *  @param parent - the frame to be centered on
    *  @param child - the dialog to be centered
    */
   public static void centerOnFrame(JFrame parent, JDialog child) {
      Point parentLocation = parent.getLocation();
      Dimension parentSize = parent.getSize();
      int xCoord = parentLocation.x + parentSize.width / 2;
      int yCoord = parentLocation.y + parentSize.height / 2;
      xCoord -= child.getSize().width / 2;
      yCoord -= child.getSize().height / 2;
      child.setLocation(xCoord, yCoord);
   }
   
   /**
    * This method will center a dialog on a panel.
    * 
    * @param panel - the panel to be centered on
    * @param dialog - the dialog to be centered
    */
   public static void centerOnPanel(JPanel panel, JDialog dialog) {
      JFrame parent = getFrame(panel);
      Point parentLocation = parent.getLocation();
      Dimension parentSize = parent.getSize();
      int xCoord = parentLocation.x + parentSize.width / 2;
      int yCoord = parentLocation.y + parentSize.height / 2;
      xCoord -= dialog.getLocation().x + dialog.getSize().width / 2;
      yCoord -= dialog.getLocation().y + dialog.getSize().height / 2;
      dialog.setLocation(xCoord, yCoord);
   }

   /**
    *  This method will get the frame this component lies on.  Given the component,
    *  the parent of the component will be checked to see if it's a frame.  This process continues
    *  until a frame is found.
    * 
    *  @param c - the component
    *
    *  @return    Frame
    */
   public static JFrame getFrame(Component c) {
      while (c.getParent() != null && !(c instanceof JFrame)) {
         c = c.getParent();         
      }
      return (JFrame) c;
   }

   /**
    * Drills down through component hierarchy until a JDialog is found, in which
    * case the JDialog is returned.
    *
    * @param c  - component on the dialog.
    *
    * @return the dialog on which the component lies.
    */
   public static JDialog getDialog(Component c) {
      while (c.getParent() != null && !(c instanceof JDialog)) {
         c = c.getParent();
      }
      return ((JDialog) c);
   }
   
   /**
    * Checks if parent is a JDialog
    *
    * @param c The component to check.
    *
    * @return  true/false if parent component is a dialog
    */
   public static boolean parentIsaDialog(Component c) {
      while (c.getParent() != null && !(c instanceof JDialog)) {
         c = c.getParent();
      }
      if (c instanceof JDialog) {
         return true;
      }
      return false;
   }

}