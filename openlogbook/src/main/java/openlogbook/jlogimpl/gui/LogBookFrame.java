package openlogbook.jlogimpl.gui;


import openlogbook.Genesis;
import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.gui.AbstractLogBookFrame;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.logentrydialog.AbstractLogEntryDialog;
import openlogbook.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlogimpl.gui.common.JPopupMenuXY;
import openlogbook.jlogimpl.gui.logentrydialog.LogEntryDialog;
import openlogbook.util.ScreenUtil;
import openlogbook.util.UpdateManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * This is the frame that will hold the overall information on the log book.
 * 
 * @author KC0ZPS
 */
public class LogBookFrame extends AbstractLogBookFrame {

   private JPanel            _mainPanel = new JPanel();
   private JPanel            _buttonPanel = new JPanel();
   private JButton           _addButton = new JButton("Add");
   private JButton           _editButton = new JButton("Edit");
   private JButton           _deleteButton = new JButton("Delete");
   private JTable            _table ;
   private JPopupMenuXY      _popupMenu = new JPopupMenuXY();
   private JMenuItem         _addLogEntryMenuItem = new JMenuItem("Add");
   private JMenuItem         _editLogEntryMenuItem = new JMenuItem("Edit");
   private JMenuItem         _deleteLogEntryMenuItem = new JMenuItem("Delete");

   private LogBookTableModel _logBookTableModel ;
   private LogBook           _logBook;
   private Genesis           _genesis;
   private File              _file ;
   private boolean           _hasDataChanged = false ;
   
   private MouseListener     _tableMouseListeners ;
   private ActionListener    _editLogEntryActionListener ;
   private ActionListener    _addLogEntryActionListener ;
   private ActionListener    _deleteLogEntryActionListener ;
   
   private AbstractOptionsDialogBox _optionsDialogBox ;
   
   private static int        _xOffset = 30 ;
   private static int        _yOffset = 30 ;
   
   // BUG -- Create the optionpane when the dialog is created, not when the button is pressed.
   // Otherwise, the optionpane may show an empty dialog.
   static private JOptionPane     _confirmDeletePane 
      = new JOptionPane("Are you sure?", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE) ;
   static private JDialog         _confirmDeleteDialog ;
   static private JOptionPane     _deletePane 
      = new JOptionPane("Select an entry to delete.", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION) ;
   static private JDialog         _deleteDialog ;
   static private JOptionPane     _editPane 
      = new JOptionPane("Select an entry to edit.", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION) ;
   static private JDialog         _editDialog ;
   private AbstractLogEntryDialog _logEntryDialog ;

   
   static final long serialVersionUID = -4883492743092225409L;
   
   /**
    * Creates new form AreaEditorDialog.
    *
    * @param genesis The application frame.
    * @param logBook The logbook being edited.
    * @param file The file for this log book.
    * @param optionsDialogBox The optionsDialogBox.
    */
   public LogBookFrame(Genesis genesis, LogBook logBook, File file, AbstractOptionsDialogBox optionsDialogBox) {
      super(file.getName());
      _logBook = logBook;
      _genesis = genesis;
      _file = file ;
      _optionsDialogBox = optionsDialogBox ;
      initComponents();
      addListeners() ;
      setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE) ;
      Debug.addObject(this, _logBook) ;
      Debug.addObject(this, _logEntryDialog) ;
   }

   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a
    *         table
    */
   public DebugTable getDebugTable() {
      String columnNames[] = { "Name", "Value" };
      PropertyDebugTable contents = new PropertyDebugTable("LogBookFrame", columnNames);

      contents.addEntry("LogBookTableModel", _logBookTableModel) ;
      contents.addEntry("LogBook", _logBook) ;
      contents.addEntry("Genesis", _genesis) ;
      contents.addEntry("File", _file) ;
      contents.addEntry("Has Data Changed", _hasDataChanged) ;
      contents.addEntry("_tableMouseListeners", _tableMouseListeners) ;
      contents.addEntry("_editLogEntryActionListener", _editLogEntryActionListener) ;
      contents.addEntry("_addLogEntryActionListener", _addLogEntryActionListener) ;
      contents.addEntry("_deleteLogEntryActionListener", _deleteLogEntryActionListener) ;
      contents.addEntry("Log Size", _logBook.getLogEntries().size()) ;

      return contents;
   }

   //**********
   // Methods overridden from Object
   //**********
   
   /**
    * Returns a string representation of the object.
    * 
    * @return string representation of the object.
    */
   public String toString() {
      return _file.getName() ;
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Checks if the data for this frame has changed since last save.
    * 
    * @return true if the data for this frame has changed since last save.
    */
   public boolean hasDataChanged() {
      return _hasDataChanged ;
   }
   
   /**
    * Sets the data changed flag for this frame.
    * 
    * @param title The title of this frame.
    * @param value The changed flag.
    */
   public void setDataChanged(String title, boolean value) {
      if (value == true && _hasDataChanged == false) {
         setTitle("* " + title) ;
         _hasDataChanged = true ;
      } else if (value == false && _hasDataChanged == true) {
         setTitle(title) ;
         _hasDataChanged = false ;         
      } 
   }
   
   /**
    * Gets the file for this log book.
    * 
    * @return the file for this log book.
    */
   public File getFile() {
      return _file ;
   }
   
   /**
    * Sets the file for this log book.
    * 
    * @param file the file for this log book.
    */
   public void setFile(File file) {
      _file = file ;
   }
   
   /**
    * Gets the LogBook object represented by this frame.
    * 
    * @return the LogBook object represented by this frame.
    */
   public LogBook getLogBook() {
      return _logBook ;
   }
   
   /**
    * Cleans this frame up.  This method should be called when this object is no longer needed. 
    */
   public void cleanup() {
      _logEntryDialog.cleanup() ;
      Debug.removeObject(_logBook) ;
      removeListeners() ;
   }
   
   //**********
   // Methods inherited from ThemeUpdateListener.
   //**********
   
   /**
    * Invoked when an action occurs.
    * 
    * @param themeUpdateEvent A ThemeUpdateEvent.
    */
   public void updateUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
      SwingUtilities.updateComponentTreeUI(_confirmDeleteDialog);
      SwingUtilities.updateComponentTreeUI(_deleteDialog);
      SwingUtilities.updateComponentTreeUI(_editDialog);
   }
   
   //**********
   // Private methods
   //**********

   /**
    * Initializes the components in this frame.
    */
   private void initComponents() {
      _mainPanel.setLayout(new BorderLayout());
      _mainPanel.setPreferredSize(new Dimension(730, 480));
      
      initializeButtonPanel() ;
      initializeTablePanel() ;
      
      getContentPane().add(_mainPanel, BorderLayout.CENTER);
      int openFrameCount = _genesis.getContentPane().getComponentCount() ;
      setLocation(_xOffset * (openFrameCount-1), _yOffset * (openFrameCount-1));
      pack() ;
      
      _popupMenu.add(_addLogEntryMenuItem) ;
      _popupMenu.add(_editLogEntryMenuItem) ;
      _popupMenu.add(_deleteLogEntryMenuItem) ;

      _addLogEntryMenuItem.setMnemonic('A' );
      _editLogEntryMenuItem.setMnemonic('E' );
      _deleteLogEntryMenuItem.setMnemonic('D' );
      
      _confirmDeleteDialog = _confirmDeletePane.createDialog(_genesis, "Warning");
      _deleteDialog = _deletePane.createDialog(_genesis, "Warning");
      _editDialog = _editPane.createDialog(_genesis, "Warning");
      _logEntryDialog = new LogEntryDialog(_genesis, "Log Entry") ;
   }
   
   /**
    * Adds any listeners for this frame.
    */
   private void addListeners() {
      _tableMouseListeners = new MouseListener() {
         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
               editButtonActionPerformed() ;
            } else {
               viewPopupMenu(e);
            }
         }
         public void mouseEntered(MouseEvent e) {}
         public void mouseExited(MouseEvent e) {}
         public void mousePressed(MouseEvent e) {
            viewPopupMenu(e);
         }
         public void mouseReleased(MouseEvent e) {
            viewPopupMenu(e);
         }   
      } ;
      _table.addMouseListener(_tableMouseListeners) ;
      
      _addLogEntryActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            addButtonActionPerformed() ;
         }
      } ;
      _addLogEntryMenuItem.addActionListener(_addLogEntryActionListener);
      
      _editLogEntryActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            editButtonActionPerformed() ;
         }
      } ;
      _editLogEntryMenuItem.addActionListener(_editLogEntryActionListener);

      _deleteLogEntryActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            deleteButtonActionPerformed() ;
         }
      } ;
      _deleteLogEntryMenuItem.addActionListener(_deleteLogEntryActionListener);

      UpdateManager.getInstance().addThemeUpdateListener(this) ;
      UpdateManager.getInstance().addTableUpdateListener(_logBookTableModel) ;
   }
   
   /**
    * Initializes the button panel.
    */
   private void initializeButtonPanel() {
      _buttonPanel.add(_addButton) ;
      _buttonPanel.add(_editButton) ;
      _buttonPanel.add(_deleteButton) ;
      // TODO maybe later
      // _buttonPanel.add(new JLabel("View Dates As : ")) ;
      // _jComboBox = new JComboBox(TimeZone.getValues()) ;
      // _buttonPanel.add(_jComboBox) ;
      _mainPanel.add(_buttonPanel, BorderLayout.NORTH) ;
      
      _addButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            addButtonActionPerformed() ;
         }
      }) ;

      _editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            editButtonActionPerformed() ;
         }
      }) ;

      _deleteButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            deleteButtonActionPerformed() ;
         }
      }) ;

   }
   
   /**
    * Initializes the table panel.
    */
   private void initializeTablePanel() {
      _logBookTableModel = new LogBookTableModel(_logBook, _optionsDialogBox.getColumnList()) ;
      _table = new JTable(_logBookTableModel) ;
      JScrollPane scrollPane = new JScrollPane(_table) ;
      _mainPanel.add(scrollPane, BorderLayout.CENTER) ;
      _table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS) ;
      _table.doLayout() ;
   }
   
   /**
    * Handles the add button action.
    */
   private void addButtonActionPerformed() {
      LogEntry defaultLogEntry = _genesis.getOptionsDialogBox().getDefaultLogEntry() ;
      
      _logEntryDialog.setLogEntry(DefaultLogBookFactory.createLogEntry(defaultLogEntry)) ;
      ScreenUtil.centerOnFrame(_genesis, _logEntryDialog) ;
      _logEntryDialog.setVisible(true) ;
      if (_logEntryDialog.isDataValid()) {
         _logBook.addEntry(_logEntryDialog.getLogEntry()) ;
         _logBookTableModel.fireTableDataChanged() ;
         setDataChanged(getTitle(), true) ;
      }
   }
   
   /**
    * Handles the edit button action.
    */
   private void editButtonActionPerformed() {
      int selected = _table.getSelectedRow() ;
      
      if (selected == -1) {
         _editDialog.setVisible(true);
         return ;
      }
      
      LogEntry entry = _logBook.getEntry(selected) ;
      _logEntryDialog.setLogEntry(entry) ;
      ScreenUtil.centerOnFrame(_genesis, _logEntryDialog) ;
      _logEntryDialog.setVisible(true) ;
      if (_logEntryDialog.isDataValid()) {
         _logBook.setEntry(selected, _logEntryDialog.getLogEntry()) ;
         _logBookTableModel.fireTableDataChanged() ;
         setDataChanged(getTitle(), true) ;
      }
   }

   /**
    * Handles the delete button action.
    */
   private void deleteButtonActionPerformed() {
      int selected = _table.getSelectedRow() ;

      if (selected == -1) {
         _deleteDialog.setVisible(true);
         return ;
      }
      
      _confirmDeleteDialog.setVisible(true);
      Integer selectedValue = (Integer)_confirmDeletePane.getValue();
      if (selectedValue.intValue() == 0) {
         _logBook.deleteEntry(selected) ;
         _logBookTableModel.fireTableDataChanged() ;
         setDataChanged(getTitle(), true) ;
      }
   }

   /**
    * Handles the view popup menu.
    *
    * @param evt The MouseEvent.
    */
   private void viewPopupMenu(MouseEvent evt) {
      if (evt.isPopupTrigger()) {
         int selected = _table.getSelectedRow() ;
         if (selected == -1) {
            _editLogEntryMenuItem.setEnabled(false) ;
            _deleteLogEntryMenuItem.setEnabled(false) ;            
         } else {
            _editLogEntryMenuItem.setEnabled(true) ;
            _deleteLogEntryMenuItem.setEnabled(true) ;
         }
         
         _popupMenu.show(_table,evt.getPoint().x, evt.getPoint().y) ;
      }
   }
   
   /**
    * Removes listeners from this panel.
    */
   private void removeListeners() {
      _table.removeMouseListener(_tableMouseListeners) ;
      _addLogEntryMenuItem.removeActionListener(_editLogEntryActionListener) ;
      _editLogEntryMenuItem.removeActionListener(_addLogEntryActionListener) ;
      _deleteLogEntryMenuItem.removeActionListener(_deleteLogEntryActionListener) ;      
      UpdateManager.getInstance().removeThemeUpdateListener(this) ;
      UpdateManager.getInstance().removeTableUpdateListener(_logBookTableModel) ;
   }
   
}
