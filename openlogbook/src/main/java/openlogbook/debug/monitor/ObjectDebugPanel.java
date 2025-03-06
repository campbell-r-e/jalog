package openlogbook.debug.monitor;

import openlogbook.debug.CustomCommand;
import openlogbook.debug.CustomCommandEntry;
import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.FullDebugable;
import openlogbook.debug.HistoryLog;
import openlogbook.debug.TraceControl;
import openlogbook.debug.TracePoint;
import openlogbook.jlog.util.ApiControl;
import openlogbook.util.AppProperties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * The Debug panel for displaying the object tree.
 */
class ObjectDebugPanel extends JPanel {

   /**
    * Creates a new ObjectDebugPanel.
    *
    */
   public ObjectDebugPanel() {
      try {
         jbInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
      _vSplitPane.setDividerLocation(AppProperties.getIntProperty(PROPERTY_VERT_DIVIDER, 150));
      _hSplitPane.setDividerLocation(AppProperties.getIntProperty(PROPERTY_HORZ_DIVIDER, 175));
      initPopupMenu();
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the frame.
    *
    * @param frame  the frame in which this panel exists
    *
    */
   public void setFrame(Frame frame) {
      _frame = frame ;
   }

   /**
    * Gets the object tree model.
    *
    * @return the object tree model
    */
   public TreeModel getObjectTree() {
      return _objectTree.getModel();
   }

   /**
    * Sets the tree model for the object tree.
    *
    * @param objectTreeModel   TreeModel for the object tree
    *
    */
   public void setObjectTreeModel(TreeModel objectTreeModel) {
      _objectTree.setModel(objectTreeModel) ;
      ((DefaultTreeModel)_objectTree.getModel()).reload() ;
   }

   /**
    * Save any preferences/properties.
    */
   public void savePreferences() {
      AppProperties.addProperty(PROPERTY_VERT_DIVIDER, _vSplitPane.getDividerLocation());
      AppProperties.addProperty(PROPERTY_HORZ_DIVIDER, _hSplitPane.getDividerLocation());
   }


   //**********
   // Private methods
   //**********

   /**
    * Loads an icon.
    *
    * @param imagePath  path to locate icon image
    *
    * @return an ImageIcon.
    */
   private ImageIcon getIcon(String imagePath) {
      URL imageUrl = ClassLoader.getSystemResource(imagePath) ;
      if (imageUrl != null) {
         return new ImageIcon(imageUrl) ;
      }
      return null ;
   }


   /**
    * JBuilder initialization.
    *
    * @exception Exception if any exceptions occur while initializing.
    */
   private void jbInit() throws Exception {
      _objectRefreshButton.setText("Refresh");
      _objectRefreshButton.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                   refreshButtonActionPerformed();
                                                }
                                             });
      _objectTree.addTreeSelectionListener(new TreeSelectionListener() {
                                              public void valueChanged(TreeSelectionEvent e) {
                                                 objectTreeSelectionChanged(e) ;
                                              }
                                           });

      _optionTree.addMouseListener(new MouseAdapter() {
                                      public void mouseClicked(MouseEvent e) {
                                         optionTreeMouseClicked(e) ;
                                      }
                                   });

      _objectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
      _objectTree.setShowsRootHandles(true);
      _optionTree.setShowsRootHandles(true);
      _optionTree.setModel(new DefaultTreeModel(_optionsRoot)) ;
      _optionTree.setRootVisible(false) ;
      _objectTree.setRootVisible(false) ;

      DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
      ImageIcon objectIcon = getIcon("openlogbook/debug/monitor/green-ball.gif") ;
      renderer.setLeafIcon(objectIcon);
      renderer.setClosedIcon(objectIcon);
      renderer.setOpenIcon(objectIcon);
      _objectTree.setCellRenderer(renderer);

      _inspectTable.setModel(_tableModel) ;
      _inspectTable.setSelectionForeground(_inspectTable.getForeground()) ;
      _inspectTable.setSelectionBackground(_inspectTable.getBackground()) ;
      _inspectTable.setRequestFocusEnabled(false) ;
      _tableScrollPane.getViewport().add(_inspectTable, null);
      _objectScrollPane.getViewport().add(_objectTree, null);
      _optionScrollPane.getViewport().add(_optionTree, null);

      _inspectPanel.setLayout(_borderLayout2);
      setLayout(_borderLayout1);

      _inspectPanel.add(_objectButtonPanel, BorderLayout.EAST);
      _inspectPanel.add(_tableScrollPane, BorderLayout.CENTER) ;
      _inspectPanel.setMinimumSize(new Dimension(150, 200)) ;
      _objectButtonPanel.add(_objectRefreshButton, null);

      _objectScrollPane.setMinimumSize(new Dimension(150, 200)) ;
      _optionScrollPane.setMinimumSize(new Dimension(150, 200)) ;
      _hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, _objectScrollPane, _optionScrollPane) ;
      _hSplitPane.setOneTouchExpandable(true) ;
      //_hSplitPane.setDividerLocation(175) ;
      _hSplitPane.setBorder(BorderFactory.createEmptyBorder()) ;

      _vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _hSplitPane, _inspectPanel) ;
      _vSplitPane.setOneTouchExpandable(true) ;
      //_vSplitPane.setDividerLocation(150) ;
      add(_vSplitPane, BorderLayout.CENTER);

      ImageIcon optionIcon = getIcon("openlogbook/debug/monitor/blue-ball.gif") ;
      ImageIcon tableIcon = getIcon("openlogbook/debug/monitor/table1.gif") ;
      ImageIcon commandIcon = getIcon("openlogbook/debug/monitor/arrow.gif") ;
      DebugOptionTreeCellRenderer optionRenderer = new DebugOptionTreeCellRenderer(commandIcon, tableIcon) ;
      optionRenderer.setClosedIcon(optionIcon);
      optionRenderer.setOpenIcon(optionIcon);
      _optionTree.setCellRenderer(optionRenderer) ;
      _vSplitPane.resetToPreferredSizes() ;
      _hSplitPane.resetToPreferredSizes() ;
      _hSplitPane.validate() ;
   }

   /**
    * Initialize the popup menu and setup all listeners for it.
    */
   private void initPopupMenu() {
      _popupMenu = new JPopupMenu();
      JMenuItem menuItem = new JMenuItem("Data collection...");
      menuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setSelectedFile(new File("openlogbookdump.zip"));
            int returnVal = chooser.showSaveDialog(ObjectDebugPanel.this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               try {
                  ApiControl.getSupportManager().gatherDebugData(chooser.getSelectedFile());
               } catch (IOException exc) {
                  JOptionPane.showMessageDialog(ObjectDebugPanel.this, "Error:  " + exc.getMessage());
               }
            }
         }
      });
      _popupMenu.add(menuItem);
      
      //Add listener to components that can bring up popup menus.
      MouseListener popupListener = new PopupListener();
      addMouseListener(popupListener);
      _objectTree.addMouseListener(popupListener);
      _inspectTable.addMouseListener(popupListener);
      _inspectPanel.addMouseListener(popupListener);
      _tableScrollPane.addMouseListener(popupListener);
      _optionTree.addMouseListener(popupListener);  //upper right
   }
   
   /**
    * Handles actions in the object option tree.
    *
    * @param e   Mouse Event
    *
    */
   private void optionTreeMouseClicked(MouseEvent e) {
      TreePath path = _optionTree.getPathForLocation(e.getX(), e.getY());
      if (path != null && e.getClickCount() == 2) {
         DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)path.getLastPathComponent() ;
         if (treeNode == null) {
            return ;
         }
         Object obj = treeNode.getUserObject() ;
         // TBD - kludgy tests here
         if ((obj != null) && (obj instanceof TracePoint)) {
            TracePoint tracePoint = (TracePoint)obj ;
            boolean enabled = tracePoint.isEnabled() ;
            tracePoint.setEnabled(!enabled) ;
            _optionTree.repaint() ;
         } else if ((obj != null) && (obj instanceof CustomCommandEntry)) {
            CustomCommandEntry customCommandEntry = (CustomCommandEntry)obj ;
            try {
               customCommandEntry.getCommand().execute(getCommandParameters(customCommandEntry)) ;
            } catch (Exception ex) {
               ex.printStackTrace() ; // Ok to print. We're only in this path in test environments.
            }
         } else if ((obj != null) && (obj instanceof HistoryLog)) {
            HistoryLog log = (HistoryLog)obj ;
            HistoryLogFrame logFrame = new HistoryLogFrame(log) ;
            logFrame.setSize(700, 500) ;
            logFrame.setVisible(true) ;
         }
      }
   }

   /**
    * Gets a set of parameters for a custom command.
    *
    * @param customCommandEntry  Definition of the custom command.
    *
    * @return a set of parameters for a custom command.
    */
   private String[] getCommandParameters(CustomCommandEntry customCommandEntry) {
      String parameterNames = customCommandEntry.getParameterNames() ;
      String[] parameters = new String[0] ;
      if (parameterNames.length() != 0) {
         CustomCommandDlg ccDlg = new CustomCommandDlg(_frame, "Custom Command Parameters", true, parameterNames);
         ccDlg.validate() ;
         ccDlg.setVisible(true) ;
         if (ccDlg.isDialogAccepted()) {
            parameters = ccDlg.getParmResults() ;
         }
      }
      return parameters ;
   }

   /**
    * Called when the selected debug object changes.
    *
    * @param obj  the new selected object
    *
    */
   private void updateObjectDisplay(Debugable obj) {
      _selectedObj = obj ;
      if (obj == null) {
         return ;
      }

      try {
         DebugTable debugTable = obj.getDebugTable() ;
         if (debugTable == null) {
            _inspectTable.setVisible(false) ;
            return ;
         }
         _tableModel.setContents(debugTable) ;
      } catch (Exception e) {
         e.printStackTrace() ; // Ok to print. We're only in this path in test environments.
      }

      TableAutoSizer.autoSizeTable(_inspectTable) ;
      _inspectTable.sizeColumnsToFit(-1) ;

      // The following lines are an attempt to get the table to get the table columns to
      // do "the right thing". Sometimes the columns aren't the proper size.
      _inspectPanel.repaint() ;
      _inspectTable.revalidate() ;
      _inspectTable.repaint() ;
      _inspectTable.doLayout() ;
   }

   /**
    * Called when the refresh button is pressed.
    *
    */
   private void refreshButtonActionPerformed() {
      updateObjectDisplay(_selectedObj) ;
   }

   /**
    * Called when the selection in the object tree changes.
    *
    * @param e  a TreeSelectionEvent
    *
    */
   private void objectTreeSelectionChanged(TreeSelectionEvent e) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
      Object obj = node.getUserObject();
      _optionsRoot.removeAllChildren() ;
      if (obj == null || !(obj instanceof Debugable)) {
         return ;
      }
      DefaultMutableTreeNode traceNode = null ;
      DefaultMutableTreeNode commandNode = null ;
      DefaultMutableTreeNode logNode = null ;

      if (obj instanceof Debugable) {
         updateObjectDisplay((Debugable)obj) ;
      }
      _selectedObj = (Debugable)obj ;
      if (obj instanceof FullDebugable) {
         FullDebugable debugable = (FullDebugable)obj ;

         Vector<CustomCommand>  commands = debugable.getDebugableData().getCustomCommands() ;
         TraceControl traceControl = debugable.getDebugableData().getTraceControl() ;
         Vector<HistoryLog> logs = debugable.getDebugableData().getHistoryLogs() ;

         if (commands != null) {
            commandNode = new DefaultMutableTreeNode("Commands");
            _optionsRoot.insert(commandNode, _optionsRoot.getChildCount()) ;
            for (int cmdIdx = 0; cmdIdx < commands.size(); cmdIdx++) {
               CustomCommandEntry customCommandEntry = (CustomCommandEntry)commands.elementAt(cmdIdx) ;
               DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(customCommandEntry) ;
               commandNode.insert(newNode, commandNode.getChildCount()) ;
            }
         }

         if (traceControl != null) {
            traceNode = new DefaultMutableTreeNode("Traces");
            _optionsRoot.insert(traceNode, _optionsRoot.getChildCount()) ;
            Vector<TracePoint> tracePoints = traceControl.listTracePoints() ;
            for (int i = 0; i < tracePoints.size(); i++) {
               TracePoint tp = tracePoints.elementAt(i) ;
               DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(tp) ;
               traceNode.insert(newNode, traceNode.getChildCount()) ;
            }
         }

         if (logs != null) {
            logNode = new DefaultMutableTreeNode("Logs");
            _optionsRoot.insert(logNode, _optionsRoot.getChildCount()) ;
            for (int logIdx = 0; logIdx < logs.size(); logIdx++) {
               HistoryLog log = (HistoryLog)logs.elementAt(logIdx) ;
               DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(log) ;
               logNode.insert(newNode, logNode.getChildCount()) ;
            }
         }
      }

      DefaultTreeModel treeModel = (DefaultTreeModel)_optionTree.getModel() ;
      treeModel.reload() ;
      if (commandNode != null) {
         _optionTree.expandRow(_optionTree.getRowForPath(new TreePath(commandNode))) ;
      }
      if (traceNode != null) {
         _optionTree.expandRow(_optionTree.getRowForPath(new TreePath(traceNode))) ;
      }
      if (logNode != null) {
         _optionTree.expandRow(_optionTree.getRowForPath(new TreePath(logNode))) ;
      }
      _optionTree.repaint() ;
   }

   
   /**
    * Inner class to handle mouse events for showing right-click popup menu 
    */
   private class PopupListener extends MouseAdapter {
       /**
        * Called when the mouse is pressed.
        * 
        * @param e  a MouseEvent
        */
      public void mousePressed(MouseEvent e) {
         maybeShowPopup(e);
      }

      /**
       * Called when the mouse is released.
       * 
       * @param e  a MouseEvent
       */
      public void mouseReleased(MouseEvent e) {
         maybeShowPopup(e);
      }

      /**
       * Shows popup menu if so indicated by mouse event.
       * 
       * @param e  a MouseEvent
       */
      private void maybeShowPopup(MouseEvent e) {
         if (e.isPopupTrigger()) {
            _popupMenu.show(e.getComponent(), e.getX(), e.getY());
         }
      }
   }

   
   //**********
   // Class attributes and constants
   //**********

   //names of properties in the file
   private static final String PROPERTY_VERT_DIVIDER     = "inspector.vertDivider";
   private static final String PROPERTY_HORZ_DIVIDER     = "inspector.horzDivider";

   private JSplitPane             _vSplitPane = new JSplitPane();
   private JSplitPane             _hSplitPane ;
   private BorderLayout           _borderLayout1 = new BorderLayout();
   private BorderLayout           _borderLayout2 = new BorderLayout();
   private JPanel                 _inspectPanel = new JPanel();
   private JPanel                 _objectButtonPanel = new JPanel();
   private JButton                _objectRefreshButton = new JButton();
   private JTable                 _inspectTable = new JTable();
   private JScrollPane            _objectScrollPane = new JScrollPane() ;
   private JScrollPane            _optionScrollPane = new JScrollPane() ;
   private JScrollPane            _tableScrollPane = new JScrollPane() ;

   private DefaultMutableTreeNode _optionsRoot = new DefaultMutableTreeNode("Options");
   private JTree                  _optionTree = new JTree();
   private JTree                  _objectTree = new JTree();
   private Frame                  _frame ;
   private transient Debugable    _selectedObj ;
   private DebugTableModel        _tableModel = new DebugTableModel() ;
   private JPopupMenu             _popupMenu ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.ObjectDebugPanel
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -641134648749289564L;

}


// End of ObjectDebugPanel.java

