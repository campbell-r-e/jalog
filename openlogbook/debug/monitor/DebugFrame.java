package openlogbook.debug.monitor;

import openlogbook.util.AppProperties;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.tree.TreeModel;

/**
 *  Frame for the debug monitor window.
 */
class DebugFrame extends JFrame {

   /**
    * Creates a new DebugFrame
    *
    * @param objectDebugTree - the TreeModel for the debug tree
    */
   public DebugFrame(TreeModel objectDebugTree) {
      super() ;
      _objectDebugTree = objectDebugTree ;
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
         jbInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
      initPreferences();
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the object tree model.
    *
    * @return the object tree model
    */
   public TreeModel getObjectTree() {
      return _debugPanel.getObjectTree() ;
   }

   /**
    * Save any preferences/properties.
    */
   public void savePreferences() {
      AppProperties.addProperty(PROPERTY_GUI_WIDTH, getWidth());
      AppProperties.addProperty(PROPERTY_GUI_HEIGHT, getHeight());
      AppProperties.addProperty(PROPERTY_GUI_POSITION_X, getX());
      AppProperties.addProperty(PROPERTY_GUI_POSITION_Y, getY());
      _debugPanel.savePreferences();
   }


   //**********
   // Private methods
   //**********

   /**
    * JBuilder initialization.
    *
    * @throws Exception on any exception
    *
    */
   private void jbInit() throws Exception{
      _debugPanel.setFrame(this) ;
      _debugPanel.setObjectTreeModel(_objectDebugTree) ;
      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
      setTitle("Debugger Window");
      getContentPane().add(_debugPanel, null);
      setSize(new Dimension(656, 444));
      URL imageUrl = getClass().getResource("debug.gif") ;
      if (imageUrl == null) {
         ClassLoader classLoader = getClass().getClassLoader() ;
         if (classLoader instanceof URLClassLoader) {
            imageUrl = ((URLClassLoader)classLoader).findResource("openlogbook/debug/monitor/debug.gif");
         }
      }
      if (imageUrl != null) {
         ImageIcon imageIcon = new ImageIcon(imageUrl) ;
         if (imageIcon != null) {
            setIconImage(imageIcon.getImage()) ;
         }
      }
   }
   
   /**
    * Initialize preferences defined by properties.
    *
    */
   private void initPreferences() {
      setLocation(AppProperties.getIntProperty(PROPERTY_GUI_POSITION_X, 0),
                  AppProperties.getIntProperty(PROPERTY_GUI_POSITION_Y, 0));
      setSize(new Dimension(AppProperties.getIntProperty(PROPERTY_GUI_WIDTH, 500),
                            AppProperties.getIntProperty(PROPERTY_GUI_HEIGHT, 400)));

      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent event) {
            savePreferences();
         }
      });
   }

   //**********
   // Class attributes and constants
   //**********

   //names of properties in the file
   private static final String PROPERTY_GUI_WIDTH        = "inspector.guiWidth";
   private static final String PROPERTY_GUI_HEIGHT       = "inspector.guiHeight";
   private static final String PROPERTY_GUI_POSITION_X   = "inspector.guiPositionX";
   private static final String PROPERTY_GUI_POSITION_Y   = "inspector.guiPositionY";

   private ObjectDebugPanel     _debugPanel = new ObjectDebugPanel() ;
   private transient TreeModel  _objectDebugTree;
   
   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.DebugFrame
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 5704357228616924609L;

}


// end of DebugFrame.java

