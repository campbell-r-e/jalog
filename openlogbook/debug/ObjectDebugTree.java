package openlogbook.debug;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 */
class ObjectDebugTree extends DefaultTreeModel {

   /**
    * Creates a new ObjectDebugTree
    */
   public ObjectDebugTree() {
      super(new DefaultMutableTreeNode("Objects")) ;
      _root = (DefaultMutableTreeNode)getRoot() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Registers an object for viewing in the object viewer
    *
    * @param parent     parent debugable object
    * @param debugable  object to be added
    *
    */
   public void addObject(Debugable parent, Debugable debugable) {
      // Check if this node was already added to the root. If so, move it to its real parent
      DefaultMutableTreeNode thisNode = (DefaultMutableTreeNode)findTreeNode(_root, debugable) ;
      if (thisNode == null) {
         thisNode = new DefaultMutableTreeNode(debugable) ;
      }

      DefaultMutableTreeNode parentNode = null ;
      if (parent == null) {
         parentNode = _root ;
      } else {
         parentNode = (DefaultMutableTreeNode)findTreeNode(_root, parent) ;
      }
      if (parentNode == null) {
         // parent not yet in the tree, so add it to the root
         // it may get moved later
         addObject(null, parent) ;
         parentNode = (DefaultMutableTreeNode)findTreeNode(_root, parent) ;
         if (parentNode == null) {
            // this should not happen!
            parentNode = _root ;
         }
      }
      parentNode.insert(thisNode, parentNode.getChildCount()) ;
      reload() ;
   }


   /**
    * De-Registers an object for viewing in the object viewer
    *
    * @param debugable  object to be removed
    *
    */
   public void removeObject(Debugable debugable) {
      MutableTreeNode node = (MutableTreeNode)findTreeNode(_root, debugable) ;
      if (node != null) {
         MutableTreeNode parent = (MutableTreeNode)node.getParent() ;
         if (parent != null) {
            parent.remove(node) ;
            reload() ;
         }
      }
   }

   /**
    * Finds a node containing a specific user data object
    *
    * @param parent    parent node to start at
    * @param userData  data in a node
    *
    * @return node if found, else null
    */
   private TreeNode findTreeNode(TreeNode parent, Object userData) {
      for (int i = 0; i < parent.getChildCount(); i++) {
         DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)parent.getChildAt(i) ;
         if (childNode.getUserObject() == userData) {
            return childNode ;
         } else {
            TreeNode nextChild = findTreeNode(childNode, userData) ;
            if (nextChild != null) {
               return nextChild ;
            }
         }
      }
      return null ;
   }


   //**********
   // Class attributes and constants
   //**********

   private DefaultMutableTreeNode _root ;

   /**
    * The serialVersionUID for the class copenlogbook.debug.ObjectDebugTree
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -5013036768275392654L;

}

// End of ObjectDebugTree.java

