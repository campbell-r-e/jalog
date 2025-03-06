package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.logentrydialog.AbstractLogEntryDialog;
import openlogbook.jlog.logbook.jlogentry.LogEntry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;



/**
 * The main log entry dialog box.
 * 
 * @author KC0ZPS
 */
public class LogEntryDialog extends AbstractLogEntryDialog {

   private LogEntry _entry ;
   
   private JPanel                  _mainPanel      = new JPanel() ;
   private GeneralPanel            _generalPanel ;
   private AddressPanel            _addressPanel   = new AddressPanel() ;
   private QslPanel                _qslPanel       = new QslPanel() ;
   private ContestPanel            _contestPanel   = new ContestPanel() ;
   private NotesPanel              _notesPanel     = new NotesPanel() ;
   private SatellitePanel          _satellitePanel = new SatellitePanel() ;
   private ContactInformationPanel _contactInformationPanel = new ContactInformationPanel() ;
   private JPanel                  _miscPanel ;
   private JPanel                  _contactPanel ;
   private JTabbedPane             _tabbedPane     = new JTabbedPane() ;
   
   private JButton      _okButton = new JButton("OK") ;
   private JButton      _cancelButton = new JButton("Cancel") ;
   private JPanel       _buttonPanel  = new JPanel() ;
   
   private JFrame       _parent ;
   private boolean      _isDataValid = false ;
   
   private ActionListener _okActionListener ;
   private ActionListener _cancelActionListener ;
   
   // BUG -- Create the optionpane when the dialog is created, not when the button is pressed.
   // Otherwise, the optionpane may show an empty dialog.
   static private JOptionPane _pane = new JOptionPane("", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION) ;
   static private JDialog     _dialog ;
   static private final Dimension ERROR_DIALOG_SIZE = new Dimension(480, 140) ;

   static final long serialVersionUID = 8273280793042768676L;
   
   /**
    * Creates a new default LogEntryDialog.
    * 
    * @param parent The parent frame.
    * @param title The title of this dialog box.
    */
   public LogEntryDialog(JFrame parent, String title) {
      super(parent, title) ;

      _parent = parent ;
      _entry = DefaultLogBookFactory.createLogEntry() ;

      _generalPanel = new GeneralPanel(this) ;
      
      initialize() ;
      
      Debug.addObject(this, _generalPanel) ;
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
      PropertyDebugTable contents = new PropertyDebugTable("LogEntryDialog", columnNames);

      contents.addEntry("okActionListener", _okActionListener) ;
      contents.addEntry("cancelActionListener", _cancelActionListener) ;
      contents.addEntry("isDataValid", _isDataValid) ;

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
      return "LogEntryDialog" ;
   }

   //**********
   // Public methods
   //**********
   
   /**
    * Returns true if the data is valid and the user has confirmed entry into the log.
    * 
    * @return true if the data is valid and the user has confirmed entry into the log.
    */
   public boolean isDataValid() {
      return _isDataValid ;
   }
   
   /**
    * Sets the LogEntry for this dialog.
    * 
    * @param entry the LogEntry.
    */
   public void setLogEntry(LogEntry entry) {
      _isDataValid = false; 
      _entry = entry ;
      _generalPanel.setData(_entry) ;
      _qslPanel.setData(entry.getQsl()) ;
      _addressPanel.setData(entry.getContactAddress()) ;
      _contactInformationPanel.setData(entry) ;
      _contestPanel.setData(entry.getContest()) ;
      _satellitePanel.setData(entry.getSatellite()) ;
      _notesPanel.setData(entry.getMisc()) ;
      
      _generalPanel.requestFocus() ;
   }
   
   /**
    * Getst the LogEntry object.
    * 
    * @return the LogEntry object.
    */
   public LogEntry getLogEntry() {
      return _entry ;
   }
   
   /**
    * Cleans this dialog up.  This should be called when the dialog is no longer needed.
    */
   public void cleanup() {
      removeGenericListeners() ;
      _generalPanel.cleanup() ;
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
      genericCancelButtonActionPerformed() ;
   }

   /**
    * Add listeners to this dialog.
    */
   protected void addListeners() {
      _okActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            okButtonActionPerformed(evt);
         }
      } ;
      
      _cancelActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            cancelButtonActionPerformed(evt) ;
         }
      } ;
      
      _okButton.addActionListener(_okActionListener);

      _cancelButton.addActionListener(_cancelActionListener);
   }
   
   /**
    * Remove listeners from this dialog.
    */
   protected void removeListeners() {
      _okButton.removeActionListener(_okActionListener) ;
      _cancelButton.removeActionListener(_cancelActionListener) ;
   }
   
   /**
    * Updates the dialog box UI.
    * 
    * @param themeUpdateEvent The themeUpdateEvent.
    */
   protected void updateDialogUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
      pack() ;
   }

   //**********
   // Private methods
   //**********

   /**
    * Initialize the components of this dialog box.
    */
   private void initialize() {
      addGenericListeners(); 
      createRootPane() ;

      _generalPanel.setData(_entry) ;
      _qslPanel.setData(_entry.getQsl()) ;
      _addressPanel.setData(_entry.getContactAddress()) ;
      _contactInformationPanel.setData(_entry) ;
      _contestPanel.setData(_entry.getContest()) ;
      _satellitePanel.setData(_entry.getSatellite()) ;
      _notesPanel.setData(_entry.getMisc()) ;
      
      this.setSize(new Dimension(640, 480)) ;

      _mainPanel.setLayout(new BorderLayout()) ;
      
      this.getRootPane().setDefaultButton(_okButton) ;
      _buttonPanel.add(_okButton) ;
      _buttonPanel.add(_cancelButton) ;
      
      _contactPanel = createContactPanel() ;
      _miscPanel = createMiscPanel() ;
      
      _tabbedPane.add("General", _generalPanel) ;
      _tabbedPane.add("QSL", _qslPanel) ;
      _tabbedPane.add("Contact Information", _contactPanel) ;
      _tabbedPane.add("Misc", _miscPanel) ;
      _tabbedPane.add("Notes", _notesPanel) ;
      
      _mainPanel.add(_tabbedPane, BorderLayout.CENTER); 
      _mainPanel.add(_buttonPanel, BorderLayout.SOUTH) ;
      
      getContentPane().add(_mainPanel);
      pack();
      
      _dialog = _pane.createDialog(_parent, "Warning");
      _dialog.setSize(ERROR_DIALOG_SIZE) ;
   }

   /**
    * Performs action when clicking cancel.
    * 
    * @param evt The ActionEvent.
    */
   private void cancelButtonActionPerformed(ActionEvent evt) {
      genericCancelButtonActionPerformed();
   }
   
   /**
    * Performs action when clicking OK
    *
    * @param evt The ActionEvent.
    */
   private void okButtonActionPerformed(ActionEvent evt) {
      LogEntry tempLogEntry = DefaultLogBookFactory.createLogEntry() ;
      
      try {
         // General Panel
         tempLogEntry.getCallsign().setContactStation(_generalPanel.getContactStation()) ;
         tempLogEntry.getCallsign().setOperatingStation(_generalPanel.getOperatingStation()) ;
         tempLogEntry.getEra().setStartDate(_generalPanel.getStartDate()) ;
         tempLogEntry.getEra().setEndDate(_generalPanel.getEndDate()) ;
         tempLogEntry.setRst(_generalPanel.getRst()) ;
         tempLogEntry.getFrequencyInformation().setMode(_generalPanel.getMode());
         tempLogEntry.getFrequencyInformation().setBand(_generalPanel.getBand());
         tempLogEntry.getFrequencyInformation().setFrequency(_generalPanel.getFrequency()) ;
         tempLogEntry.getFrequencyInformation().setTxPower(_generalPanel.getTxPower()) ;
         tempLogEntry.getFrequencyInformation().setRxPower(_generalPanel.getRxPower()) ;
         tempLogEntry.getContactStationInformation().setQth(_generalPanel.getQth()) ;
         tempLogEntry.getMisc().setComment(_generalPanel.getComment()) ;
      } catch (IllegalArgumentException e) {
         _tabbedPane.setSelectedComponent(_generalPanel) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;
         return ;
      }

      try {   
         //Qsl Panel
         tempLogEntry.getQsl().setQslSent(_qslPanel.isQslSent()) ;
         tempLogEntry.getQsl().setQslReceived(_qslPanel.isQslReceived()) ;
         tempLogEntry.getQsl().setQslSentDate(_qslPanel.getQslSentDate()) ;
         tempLogEntry.getQsl().setQslReceivedDate(_qslPanel.getQslReceivedDate()) ;
         tempLogEntry.getQsl().setQslVia(_qslPanel.getQslVia()) ;
         tempLogEntry.getQsl().setQslMessage(_qslPanel.getQslMessage()) ;
      } catch (IllegalArgumentException e) {
         _tabbedPane.setSelectedComponent(_qslPanel) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;
         return ;
      }
 
      try {
         // Contact Panel
         tempLogEntry.getContactAddress().setName(_addressPanel.getName()) ;
         tempLogEntry.getContactAddress().setAddress(_addressPanel.getAddress()) ;
         tempLogEntry.getContactAddress().setCounty(_addressPanel.getCounty()) ;
         tempLogEntry.getContactAddress().setContinent(_addressPanel.getContinent()) ;
         tempLogEntry.getContactAddress().setUsState(_addressPanel.getUsState()) ;
         tempLogEntry.getMisc().setTenTen(_contactInformationPanel.getTenTen()) ;
         tempLogEntry.getMisc().setVeProv(_contactInformationPanel.getVeProv()) ;
         tempLogEntry.getMisc().setArrlSection(_contactInformationPanel.getArrlSection()) ;
         tempLogEntry.getMisc().setWpxPrefix(_contactInformationPanel.getwpxPrefix()) ;
         tempLogEntry.getContactStationInformation().setAge(_contactInformationPanel.getAge()) ;
         tempLogEntry.getContactStationInformation().setIota(_contactInformationPanel.getIota()) ;
         tempLogEntry.getContactStationInformation().setCqZone(_contactInformationPanel.getCqZone()) ;
         tempLogEntry.getContactStationInformation().setDxcc(_contactInformationPanel.getDxcc()) ;
         tempLogEntry.getContactStationInformation().setItuZone(_contactInformationPanel.getItuZone()) ;
         tempLogEntry.getContactStationInformation().setGridSquare(_contactInformationPanel.getGridSquare()) ;
      } catch (IllegalArgumentException e) {
         _tabbedPane.setSelectedComponent(_contactPanel) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;
         return ;
      }
      
      try {
         // Misc Panel
         tempLogEntry.getContest().setContestId(_contestPanel.getContestId()) ;
         tempLogEntry.getContest().setTransmittedSerialNumber(_contestPanel.getSentSerialNumber()) ;
         tempLogEntry.getContest().setReceivedSerialNumber(_contestPanel.getReceivedSerialNumber()) ;
         tempLogEntry.getSatellite().setPropMode(_satellitePanel.getPropMode()) ;
         tempLogEntry.getSatellite().setSatelliteMode(_satellitePanel.getSatelliteMode()) ;
         tempLogEntry.getSatellite().setSatelliteName(_satellitePanel.getSatelliteName()) ;
      } catch (IllegalArgumentException e) {
         _tabbedPane.setSelectedComponent(_miscPanel) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;
         return ;
      }
      
      try {
         tempLogEntry.getMisc().setNotes(_notesPanel.getNote()) ;
      } catch (IllegalArgumentException e) {
         _tabbedPane.setSelectedComponent(_miscPanel) ;
         _pane.setMessage(e.getMessage()) ;
         _dialog.setVisible(true) ;
         return ;
      }
      
      _entry = tempLogEntry ;
      _isDataValid = true ;
      
      this.setVisible(false) ;
   }
   
   /**
    * Creates the misc panel.
    * 
    * @return the misc panel.
    */
   private JPanel createMiscPanel() {
      JPanel miscPanel = new JPanel() ;
      miscPanel.setLayout(new GridLayout(2,1)) ;
      
      _contestPanel.setBorder(new TitledBorder("Contest Information")) ;
      miscPanel.add(_contestPanel) ;
      
      _satellitePanel.setBorder(new TitledBorder("Satellite Information")) ;
      miscPanel.add(_satellitePanel) ;    
      
      return miscPanel ;
   }

   /**
    * Creates the contact panel.
    * 
    * @return the contact panel.
    */
   private JPanel createContactPanel() {
      JPanel contactPanel = new JPanel() ;
      contactPanel.setLayout(new GridLayout(2, 1)) ;

      _addressPanel.setBorder(new TitledBorder("Address")) ;
      contactPanel.add(_addressPanel) ;
      
      _contactInformationPanel.setBorder(new TitledBorder("Other")) ;
      contactPanel.add(_contactInformationPanel) ;
      
      return contactPanel ;
   }

   /* private JPanel createMiscPanel1() {
   JPanel miscPanel = new JPanel() ;
   miscPanel.setLayout(new GridBagLayout()) ;
   
   GridBagConstraints gridBagConstraints = new GridBagConstraints();
   gridBagConstraints.gridx = 0;
   gridBagConstraints.gridy = 0;
   gridBagConstraints.insets = new Insets(0, 0, 0, 0);
   gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
   _contestPanel.setBorder(new TitledBorder("Contest Information")) ;
   miscPanel.add(_contestPanel, gridBagConstraints) ;
   
   gridBagConstraints.gridy = 1;
   gridBagConstraints.weightx = 1 ;
   gridBagConstraints.weighty = 1 ;
   _satellitePanel.setBorder(new TitledBorder("Satellite Information")) ;
   miscPanel.add(_satellitePanel, gridBagConstraints) ;    
   
   return miscPanel ;
   }*/


   
   /* private JPanel createContactPanel1() {
   JPanel contactPanel = new JPanel() ;
   contactPanel.setLayout(new GridBagLayout()) ;

   GridBagConstraints gridBagConstraints = new GridBagConstraints();
   gridBagConstraints.gridx = 0;
   gridBagConstraints.gridy = 0;
   gridBagConstraints.insets = new Insets(0, 0, 0, 0);
   gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
   _addressPanel.setBorder(new TitledBorder("Address")) ;
   contactPanel.add(_addressPanel, gridBagConstraints) ;
   
   gridBagConstraints.gridy = 1;
   gridBagConstraints.weightx = 1 ;
   gridBagConstraints.weighty = 1 ;
   _contactInformationPanel.setBorder(new TitledBorder("Other")) ;
   contactPanel.add(_contactInformationPanel, gridBagConstraints) ;
   
   return contactPanel ;
} */


}
