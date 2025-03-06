package openlogbook.debug.monitor;

import openlogbook.debug.CustomCommandEntry;
import openlogbook.debug.HistoryLog;
import openlogbook.debug.TracePoint;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * A little class to render cells in the option tree. This is here to provide support for rendering
 * trace point entries as checkboxes.
 * This class that peeks at the type of the data in the tree to decide whether
 * to use the default rendering as provided by the superclass, or to render the item with a checkbox.
 */
class DebugOptionTreeCellRenderer extends DefaultTreeCellRenderer {

   /**
    * Creates a new DebugOptionTreeCellRenderer.
    *
    * @param commandIcon  The Icon to display for command nodes
    * @param logIcon      The Icon to display for log nodes
    */
   DebugOptionTreeCellRenderer(ImageIcon commandIcon, ImageIcon logIcon) {
      super() ;
      _commandIcon = commandIcon ;
      _logIcon = logIcon ;
   }

   //**********
   // Methods overridden from DefaultTreeCellRenderer
   //**********

   /**
    * Configures the renderer based on the passed in components.
    * The value is set from messaging the tree with
    * <code>convertValueToText</code>, which ultimately invokes
    * <code>toString</code> on <code>value</code>.
    * The foreground color is set based on the selection and the icon
    * is set based on on leaf and expanded.
    *
    * @param tree      Identifies the tree
    * @param value     The current value
    * @param selected  Indicates if item is selected
    * @param expanded  Indicates if node is expanded
    * @param leaf      Indicates if this is a leaf
    * @param row       Specifies the row
    * @param hasFocus  Indicates if this component has focus
    * 
    * @return appropriate renderer for the cell. 
    */
   public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                 boolean expanded, boolean leaf, int row, boolean hasFocus) {
      if (value instanceof DefaultMutableTreeNode) {
         Object o = ((DefaultMutableTreeNode)value).getUserObject() ;
         if (o instanceof TracePoint) {
            _checkbox.setSelected(((TracePoint)o).isEnabled()) ;
            _checkbox.setText(((TracePoint)o).getDescription()) ;
            _checkbox.setBackground(Color.white) ;
            return _checkbox ;
         } else if (o instanceof HistoryLog) {
            setLeafIcon(_logIcon) ;
         } else if (o instanceof CustomCommandEntry) {
            setLeafIcon(_commandIcon) ;
         }
      }
      return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus) ;
   }

   //**********
   // Class attributes and constants
   //**********

   private JCheckBox _checkbox = new JCheckBox() ;
   private ImageIcon _logIcon ;
   private ImageIcon _commandIcon ;
   
   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.monitorDebugOptionTreeCellRenderer
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 7492044790362833574L;

}


// End of DebugOptionTreeCellRenderer.java
