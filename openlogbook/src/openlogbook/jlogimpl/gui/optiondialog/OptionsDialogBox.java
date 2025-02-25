package openlogbook.jlogimpl.gui.optiondialog;

import openlogbook.debug.Debug;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.util.TableColumnChangeEventId;
import openlogbook.jlogimpl.gui.TableColumnEnum;
import openlogbook.util.UpdateManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


/**
 * This is the options dialog box. 
 * 
 * @author KC0ZPS
 */
public class OptionsDialogBox extends AbstractOptionsDialogBox {
   
   private JPanel         _buttonPanel = new JPanel();
   private boolean        _okPressed = false;
   private JButton        _okButton = new JButton();
   private JButton        _cancelButton = new JButton();
   private JTabbedPane    _tabbedPane = new JTabbedPane() ;
   
   private ThemePanel         _themePanel ;
   private TableColumnPanel   _tableColumnPanel ;
   private DefaultValuesPanel _defaultValuesPanel ;
   
   private WindowAdapter  _windowAdapter ;
   private ActionListener _okButtonActionListener ;
   private ActionListener _cancelButtonActionListener ;

   // BUG -- Create the optionpane when the dialog is created, not when the button is pressed.
   // Otherwise, the optionpane may show an empty dialog.
   static private JOptionPane _pane = new JOptionPane("", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION) ;
   static private JDialog     _dialog ;
   
   static final long serialVersionUID = 8148530009040091378L;

   /**
    * Creates a new OptionsDialogBox.
    * 
    * @param parent The parent frame.
    */
   public OptionsDialogBox(JFrame parent) {
      super(parent, "Options", false);

      _defaultValuesPanel = new DefaultValuesPanel() ;
      _tableColumnPanel = new TableColumnPanel() ;
      _themePanel = new ThemePanel() ;
      
      initComponents();
   }
   
   //**********
   // Public methods
   //**********
   
   /**
    * Gets the DefaultLogEntry defined in the options dialog box.
    * 
    * @return a DefaultLogEntry defined in the options dialog box.
    */
   public LogEntry getDefaultLogEntry() {
      return _defaultValuesPanel.getDefaultLogEntry() ;
   }
   
   /**
    * Sets the default log entry.
    * 
    * @param entry the default log entry.
    */
   public void setDefaultLogEntry(LogEntry entry) {
      _defaultValuesPanel.setDefaultLogEntry(entry) ;
   }
   
   /**
    * Checks if the custom theme selection is checked.
    * 
    * @return true if the custom theme selection is checked.
    */
   public boolean isCustomTheme() {
      return _themePanel.isCustomTheme() ;
   }
   
   /**
    * Sets the custom theme checked value.
    * 
    * @param value the custom theme checked value.
    */
   public void setIsCustomTheme(boolean value) {
      _themePanel.setIsCustomTheme(value) ;
   }
   
   /**
    * Returns the selected theme.
    * 
    * @return the selected theme.
    */
   public String getSelectedTheme() {
      return _themePanel.getSelectedTheme() ;
   }
   
   /**
    * Sets the selected theme.
    * 
    * @param theme the theme to selected.
    */
   public void setSelectedTheme(String theme) {
      _themePanel.setSelectedTheme(theme) ;
   }
   
   /**
    * Checks if the theme will load a background for the frame.
    * 
    * @return true if the theme will load a background for the frame.
    */
   public boolean isDesktopBackground() {
      return _themePanel.isDesktopBackground() ;
   }
   
   /**
    * Sets the load background flag.
    * 
    * @param value the load background flag.
    */
   public void setIsDesktopBackground(boolean value) {
      _themePanel.setIsDesktopBackground(value) ;
   }
   
   /**
    * Checks if the theme will load XTra ScrollBars.
    * 
    * @return true if the theme will load XTra ScrollBars.
    */
   public boolean isXtraScrollbars() {
      return _themePanel.isXtraScrollbars() ;
   }
   
   /**
    * Sets the XTra Scrollbars flag.
    * 
    * @param value the XTra Scrollbars flag.
    */
   public void setIsXtraScrollbars(boolean value) {
      _themePanel.setIsXtraScrollbars(value) ;
   }
   
   /**
    * Gets the column list that the user wants to display.
    * 
    * @return the column list that the user wants to display.
    */
   public ArrayList<TableColumnEnum> getColumnList() {
      return _tableColumnPanel.getColumnData() ;
   }
   
   /**
    * Adds a column.
    * 
    * @param tableColumnEnum the column to add.
    */
   public void addColumn(TableColumnEnum tableColumnEnum) {
      _tableColumnPanel.addColumn(tableColumnEnum) ;
   }
   
   /**
    * Cleans up this dialog box.
    */
   public void cleanup() {
      removeGenericListeners() ;
      _themePanel.cleanup() ;
      _tableColumnPanel.cleanup() ;
      _defaultValuesPanel.cleanup() ;
   }

   /**
    * Tests if the ok button was pressed.
    * 
    * @return true if ok was pressed.
    */
   public boolean okPressed() {
      return _okPressed ;
   }
   
   //**********
   // Methods inherited from GenericDialog
   //**********   

   /**
    * Called when the window is closing.
    * 
    * @param e The WindowEvent.
    */
   protected void handleWindowClosingEvent(WindowEvent e) {
      cancelButtonActionPerformed() ;
   }

   /**
    * Initializes all listeners in this dialog box.
    */
   protected void addListeners() {
      _windowAdapter = new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
            closeDialog(evt);
         }
      } ;
      addWindowListener(_windowAdapter);
      
      _okButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            okButtonActionPerformed(evt);
         }
      } ;
      _okButton.addActionListener(_okButtonActionListener);
      
      _cancelButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            cancelButtonActionPerformed();
         }
      } ;
      _cancelButton.addActionListener(_cancelButtonActionListener);      
   }
   
   /**
    * Removes listeners defined in this class.
    */
   protected void removeListeners() {
      removeWindowListener(_windowAdapter) ;
      _okButton.removeActionListener(_okButtonActionListener) ;
      _cancelButton.removeActionListener(_cancelButtonActionListener) ;
   }
   
   /**
    * Updates the dialog box UI.
    * 
    * @param themeUpdateEvent The themeUpdateEvent.
    */
   protected void updateDialogUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
   }

   
   //**********
   // Private methods
   //**********
   
   /**
     * Initilization Method
     */
   private void initComponents() {
           
      Container contentPane = this.getContentPane();
      contentPane.setLayout(new BorderLayout());

      setTitle("Options");
      setResizable(false);
      getRootPane().setDefaultButton(_okButton);
      createButtonPanel();

      _tabbedPane.add("Default Values", _defaultValuesPanel) ;
      _tabbedPane.add("Table Columns", _tableColumnPanel) ;
      _tabbedPane.add("Themes", _themePanel) ;
      contentPane.add(_tabbedPane) ;
      contentPane.add(_buttonPanel, BorderLayout.SOUTH);
      
      addGenericListeners();
 
      pack();
      
      _dialog = _pane.createDialog(this, "Warning");
      _dialog.setSize(430, 125) ;
   }

   /**
    * Creates the button panel.
    */
   private void createButtonPanel() {
      _okButton.setText("OK");
      _okButton.setSelected(true);
      _cancelButton.setText("Cancel");
      _buttonPanel.add(_okButton);
      _buttonPanel.add(_cancelButton);
   }

   /**
     * Performs action when clicking OK
     * 
     * @param evt the ActionEvent.
     */
   private void okButtonActionPerformed(ActionEvent evt) {
      try {
         UpdateManager.postTableColumnChangeEvent(TableColumnChangeEventId.OptionDialogEvent, 
               "OK pressed in option dialog box.", 
               _tableColumnPanel.getColumnData()) ;
         _defaultValuesPanel.setDefaultValues() ;
         _tableColumnPanel.setDefaultValues() ;
      } catch (IllegalArgumentException e) {
         Debug.logException(e) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;

         return ;         
      }
      
      try {
         _themePanel.apply() ;
         SwingUtilities.updateComponentTreeUI(this);
      }catch (Exception e) {
         Debug.logException(e) ;
         return ;
      }

      _okPressed = true;
      this.setVisible(false);
   }

   /**
     * Performs action when clicking cancel
     */
   private void cancelButtonActionPerformed() {
      _defaultValuesPanel.restoreDefaultValues() ;
      _themePanel.restoreDefaultValues() ;
      _tableColumnPanel.restoreDefaultValues() ;
      setVisible(false);
   }

   /**
     * Performs action when closing
     * 
     * @param evt the WindowEvent.
     */
   private void closeDialog(WindowEvent evt) {
      setVisible(false);
   }
}