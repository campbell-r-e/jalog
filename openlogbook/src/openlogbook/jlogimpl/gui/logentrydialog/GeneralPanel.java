package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.logbook.jlogentry.Callsign;
import openlogbook.jlog.logbook.jlogentry.ContactStationInformation;
import openlogbook.jlog.logbook.jlogentry.Era;
import openlogbook.jlog.logbook.jlogentry.FrequencyInformation;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.logbook.jlogentry.Misc;
import openlogbook.jlog.logbook.jlogentry.Rst;
import openlogbook.jlog.util.Band;
import openlogbook.jlog.util.Mode;
import openlogbook.util.Utility;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * The panel that will general information that the operator might commonly want to input.
 * 
 * @author KC0ZPS
 */
public class GeneralPanel extends JPanel implements Debugable {

   private JLabel _startDateLabel   = new JLabel("Start Date:") ;
   private JLabel _endDateLabel     = new JLabel("End Date:") ;
   private JLabel _stationLabel     = new JLabel("Station:") ;
   private JLabel _operatorLabel    = new JLabel("Operator:") ;
   private JLabel _rstReceivedLabel = new JLabel("RST Received:") ;
   private JLabel _rstSentLabel     = new JLabel("RST Sent:") ;
   private JLabel _qthLabel         = new JLabel("QTH:") ;
   private JLabel _bandLabel        = new JLabel("Band:") ;
   private JLabel _modeLabel        = new JLabel("Mode:") ;
   private JLabel _frequencyLabel   = new JLabel("Frequency:") ;
   private JLabel _txPowerLabel     = new JLabel("TX Power:") ;
   private JLabel _rxPowerLabel     = new JLabel("RX Power:") ;
   
   private JTextField _startDateTextField   = new JTextField() ;
   private JTextField _startTimeTextField   = new JTextField() ;
   private JTextField _endTimeTextField     = new JTextField() ;
   private JTextField _stationTextField     = new JTextField() ;
   private JTextField _operatorTextField    = new JTextField() ;
   private JTextField _rstReceivedTextField = new JTextField() ;
   private JTextField _rstSentTextField     = new JTextField() ;
   private JTextField _qthTextField         = new JTextField() ;
   private JTextField _txPowerTextField     = new JTextField() ;
   private JTextField _rxPowerTextField     = new JTextField() ;
   private JTextField _frequencyTextField   = new JTextField() ;
   
   private JButton _startDateButton = new JButton("Now") ;
   private JButton _endDateButton = new JButton("Now") ;
   private JButton _rstReceivedButton = new JButton("59") ;
   private JButton _rstSentButton = new JButton("59") ;
   private JButton _lookupButton = new JButton("Lookup") ;
   
   private JComboBox _bandComboBox = new JComboBox(Band.getValues()) ;
   private JComboBox _modeComboBox = new JComboBox(Mode.getValues()) ;
   
   private JPanel    _commentPanel = new JPanel() ;
   private JTextArea _commentTextArea = new JTextArea() ;
   
   private ActionListener _receivedActionListener ;
   private ActionListener _sentActionListener ;
   private ActionListener _startDateActionListener ;
   private ActionListener _endDateActionListener ;
   private ActionListener _lookupActionListener ;

   private CallsignLookupDialog _callsignLookupDialog ;
   
   private static final Dimension TEXT_FIELD_DIMENSION         = new Dimension(100, 20) ;
   private static final Dimension DATE_TEXT_FIELD_DIMENSION    = new Dimension(95, 20) ;
   private static final Dimension TIME_TEXT_FIELD_DIMENSION    = new Dimension(75, 20) ;
   private static final Dimension RST_TEXT_FIELD_DIMENSION     = new Dimension(40, 20) ;
   private static final Dimension QTH_TEXT_FIELD_DIMENSION     = new Dimension(175, 20) ;
   private static final Dimension COMMENT_TEXT_FIELD_DIMENSION = new Dimension(400, 100) ;
   private static final Dimension POWER_TEXT_FIELD_DIMENSION   = new Dimension(50, 20) ;

   static final long serialVersionUID = 1460249337203671890L;

   /**
    * Creates a new ContactPanel.
    * 
    * @param parent The parent frame.
    */
   public GeneralPanel(Dialog parentDialog) {
      super() ;
      
      _callsignLookupDialog = new CallsignLookupDialog(parentDialog, this) ;
      
      initialize() ;

      Debug.addObject(this, _callsignLookupDialog) ;
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
      PropertyDebugTable contents = new PropertyDebugTable("GeneralPanel", columnNames);

      contents.addEntry("receiveActionListener", _receivedActionListener) ;
      contents.addEntry("sentActionListener", _sentActionListener) ;
      contents.addEntry("startActionListener", _startDateActionListener) ;
      contents.addEntry("endDateActionListener", _endDateActionListener) ;
      contents.addEntry("lookupActionListener", _lookupActionListener) ;

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
      return "GeneralPanel" ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param logEntry the logEntry data to populate this panel with.
    */
   public void setData(LogEntry logEntry) {
      Callsign callsign = logEntry.getCallsign() ;
      Era era = logEntry.getEra() ;
      Rst rst = logEntry.getRst() ;
      FrequencyInformation frequencyInformation = logEntry.getFrequencyInformation() ;
      ContactStationInformation contactStationInformation = logEntry.getContactStationInformation() ;
      Misc misc = logEntry.getMisc() ;
      
      _bandComboBox.setEnabled(true) ;
      _modeComboBox.setEnabled(true) ;

      // Default values
      _txPowerTextField.setText("") ;
      _rxPowerTextField.setText("") ;
      _frequencyTextField.setText(frequencyInformation.getFrequency()) ;
      _bandComboBox.setSelectedItem(frequencyInformation.getBand()) ;
      _modeComboBox.setSelectedItem(frequencyInformation.getMode()) ;
      _startDateTextField.setText(era.getStartDateString()) ;
      _startTimeTextField.setText(era.getStartTimeString()) ;
      _endTimeTextField.setText(era.getEndTimeString()) ;
      _stationTextField.setText(callsign.getContactStation()) ;
      _operatorTextField.setText(callsign.getOperatingStation()) ;
      _rstReceivedTextField.setText(rst.getRstReceived()) ;
      _rstSentTextField.setText(rst.getRstSent()) ;
      _qthTextField.setText(contactStationInformation.getQth()) ;
      _commentTextArea.setText(misc.getComment()) ;
      
      // Conditional values
      if (frequencyInformation.getTxPower() != null) {
         _txPowerTextField.setText("" + frequencyInformation.getTxPower()) ;         
      }
      if (frequencyInformation.getRxPower() != null) {
         _rxPowerTextField.setText("" + frequencyInformation.getRxPower()) ;         
      }
      if (_startDateTextField.getText().equals("")) {
         startDateButtonActionPerformed() ;         
      }
      
      // Move the viewable text to the beginning (not the end, which is default).
      _startDateTextField.select(0, 0) ;
      _startTimeTextField.select(0, 0) ;
      _endTimeTextField.select(0, 0) ;
      _stationTextField.select(0, 0) ;
      _operatorTextField.select(0, 0) ;
      _rstReceivedTextField.select(0, 0) ;
      _rstSentTextField.select(0, 0) ;
      _frequencyTextField.select(0, 0) ;
      _txPowerTextField.select(0, 0) ;
      _rxPowerTextField.select(0, 0) ;
      _qthTextField.select(0, 0) ;
      _commentTextArea.select(0, 0) ;
      
      // Disable unknown values.
      if (frequencyInformation.getBand().equals(Band.Unknown)) {
         _bandComboBox.setEnabled(false) ;
      }
      if (frequencyInformation.getMode().equals(Mode.Unknown)) {
         _modeComboBox.setEnabled(false) ;
      }

   }

   /**
    * Gets the start date set by this panel.
    * 
    * @return the start date set by this panel.
    * 
    * @throws IllegalArgumentException if the start date isn't formatted correctly.
    */
   public Date getStartDate() throws IllegalArgumentException {
      return verifyDate(_startDateTextField, _startTimeTextField) ;
   }

   /**
    * Gets the end date set by this panel.
    * 
    * @return the end date set by this panel.
    * 
    * @throws IllegalArgumentException if the end date isn't formatted corrently.
    */
   public Date getEndDate() throws IllegalArgumentException {
      if (_startDateTextField.getText().equals("") || _endTimeTextField.getText().equals("")) {
         return null ;
      } else {
         return verifyDate(_startDateTextField, _endTimeTextField) ;
      }
   }

   /**
    * Gets the contact station set by this panel.
    * 
    * @return the contact station set by this panel.
    */
   public String getContactStation() {
      return _stationTextField.getText() ;
   }

   /**
    * Gets the operating station set by this panel.
    * 
    * @return the operating station set by this panel.
    */
   public String getOperatingStation() {
      return _operatorTextField.getText() ;
   }

   /**
    * Gets the rst set by this panel.
    * 
    * @return the rst set by this panel.
    */
   public Rst getRst() {
      return DefaultLogBookFactory.createRst(_rstReceivedTextField.getText(), _rstSentTextField.getText()) ;
   }

   /**
    * Gets the mode set by this panel.
    * 
    * @return the mode set by this panel.
    */
   public Mode getMode() {
      int index = _modeComboBox.getSelectedIndex() ;
      Mode mode = (Mode)Mode.getIntToModeType().getObjectValue(index) ;
      return mode ;
   }
   
   /**
    * Gets the band set by this panel.
    * 
    * @return the band set by this panel.
    */
   public Band getBand() {
      int index = _bandComboBox.getSelectedIndex() ;
      Band band = (Band)Band.getIntToBandType().getObjectValue(index) ;
      return band ;      
   }
   
   /**
    * Gets the frequency set by this panel.
    * 
    * @return the frequency set by this panel.
    */
   public String getFrequency() {
      return _frequencyTextField.getText() ;
   }
   
   /**
    * Gets the rx power set by the panel.
    * 
    * @return the rx power set by the panel.
    */
   public Integer getRxPower() {
      if (_rxPowerTextField.getText().equals("")) {
         return null ;
      }
      Integer power ;
      try {
         power = new Integer(_rxPowerTextField.getText()) ;
      } catch (NumberFormatException e) {
         _rxPowerTextField.selectAll() ;
         _rxPowerTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid rx power field.  Expecting numbers only.") ;
      }
      return power ;
   }

   /**
    * Gets the rx power set by the panel.
    * 
    * @return the rx power set by the panel.
    */
   public Integer getTxPower() {
      if (_txPowerTextField.getText().equals("")) {
         return null ;
      }

      Integer power ;
      try {
         power = new Integer(_txPowerTextField.getText()) ;
      } catch (NumberFormatException e) {
         _txPowerTextField.selectAll() ;
         _txPowerTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid tx power field.  Expecting numbers only.") ;
      }
      return power ;
   }

   /**
    * Gets the qth set by this panel.
    * 
    * @return the qth set by this panel.
    */
   public String getQth() {
      return _qthTextField.getText() ;
   }

   /**
    * Gets the comment set by this panel.
    * 
    * @return the comment set by this panel.
    */
   public String getComment() {
      return _commentTextArea.getText() ;
   }

   /**
    * Sets the cursor focus on the station text field.
    */
   public void requestFocus() {
      _stationTextField.select(0, 0) ;      
      _stationTextField.requestFocus() ;
   }
   
   /**
    * Cleans this panel up.  This should be called when the panel is no longer needed.
    */
   public void cleanup() {
      removeListeners() ;
      _callsignLookupDialog.cleanup() ;
   }
   
   //**********
   // Private methods
   //**********

   /**
    * Initialize the components of this panel.
    */
   private void initialize() {
      setLayout(new GridBagLayout()) ;
      
      addLabels() ;
      addTextFields() ;
      addButtons() ;
      addComboBoxes() ;
      addCommentPanel() ;
      addListeners() ;
   }
   
   /**
    * Adds the comment panel.
    */
   private void addCommentPanel() {
      _commentTextArea.setPreferredSize(COMMENT_TEXT_FIELD_DIMENSION);
      _commentTextArea.setMaximumSize(COMMENT_TEXT_FIELD_DIMENSION);
      _commentTextArea.setMinimumSize(COMMENT_TEXT_FIELD_DIMENSION);

      _commentPanel.setBorder(new TitledBorder("Comment"));
      _commentPanel.add(new JScrollPane(_commentTextArea)) ;
      
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 10;
      gridBagConstraints.gridwidth = 10 ;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.WEST;      
      add(_commentPanel, gridBagConstraints) ;

   }
   
   /**
    * Adds the combo boxes.
    */
   private void addComboBoxes() {
  
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 6;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.WEST;      
      add(_bandComboBox, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 7;
      add(_modeComboBox, gridBagConstraints) ;

   }
   
   /**
    * Adds the text fields.
    */
   private void addTextFields() {
      _startDateTextField.setPreferredSize(DATE_TEXT_FIELD_DIMENSION);
      _startDateTextField.setMaximumSize(DATE_TEXT_FIELD_DIMENSION);
      _startDateTextField.setMinimumSize(DATE_TEXT_FIELD_DIMENSION);

      _startTimeTextField.setPreferredSize(TIME_TEXT_FIELD_DIMENSION);
      _startTimeTextField.setMaximumSize(TIME_TEXT_FIELD_DIMENSION);
      _startTimeTextField.setMinimumSize(TIME_TEXT_FIELD_DIMENSION);

      _endTimeTextField.setPreferredSize(TIME_TEXT_FIELD_DIMENSION);
      _endTimeTextField.setMaximumSize(TIME_TEXT_FIELD_DIMENSION);
      _endTimeTextField.setMinimumSize(TIME_TEXT_FIELD_DIMENSION);

      _stationTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _stationTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _stationTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _operatorTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _operatorTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _operatorTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _rstReceivedTextField.setPreferredSize(RST_TEXT_FIELD_DIMENSION);
      _rstReceivedTextField.setMaximumSize(RST_TEXT_FIELD_DIMENSION);
      _rstReceivedTextField.setMinimumSize(RST_TEXT_FIELD_DIMENSION);

      _rstSentTextField.setPreferredSize(RST_TEXT_FIELD_DIMENSION);
      _rstSentTextField.setMaximumSize(RST_TEXT_FIELD_DIMENSION);
      _rstSentTextField.setMinimumSize(RST_TEXT_FIELD_DIMENSION);

      _qthTextField.setPreferredSize(QTH_TEXT_FIELD_DIMENSION);
      _qthTextField.setMaximumSize(QTH_TEXT_FIELD_DIMENSION);
      _qthTextField.setMinimumSize(QTH_TEXT_FIELD_DIMENSION);

      _txPowerTextField.setPreferredSize(POWER_TEXT_FIELD_DIMENSION);
      _txPowerTextField.setMaximumSize(POWER_TEXT_FIELD_DIMENSION);
      _txPowerTextField.setMinimumSize(POWER_TEXT_FIELD_DIMENSION);

      _rxPowerTextField.setPreferredSize(POWER_TEXT_FIELD_DIMENSION);
      _rxPowerTextField.setMaximumSize(POWER_TEXT_FIELD_DIMENSION);
      _rxPowerTextField.setMinimumSize(POWER_TEXT_FIELD_DIMENSION);

      _frequencyTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _frequencyTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _frequencyTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 2 ;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.WEST;      
      add(_startDateTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 1 ;
      add(_startTimeTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      add(_endTimeTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      add(_stationTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 3;
      add(_operatorTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 4;
      add(_rstReceivedTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 5;
      add(_rstSentTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 8;
      gridBagConstraints.gridwidth = 2 ;
      add(_qthTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 4;
      add(_rxPowerTextField, gridBagConstraints) ;      

      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 5;
      add(_txPowerTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 6;
      gridBagConstraints.gridwidth = 1 ;
      add(_frequencyTextField, gridBagConstraints) ;      

   }
   
   /**
    * Adds the buttons.
    */
   private void addButtons() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.WEST;      
      add(_startDateButton, gridBagConstraints) ;

      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      add(_endDateButton, gridBagConstraints) ;

      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.anchor = GridBagConstraints.EAST;      
      add(_rstReceivedButton, gridBagConstraints) ;

      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.anchor = GridBagConstraints.EAST;      
      add(_rstSentButton, gridBagConstraints) ;

      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.anchor = GridBagConstraints.EAST;      
      add(_lookupButton, gridBagConstraints) ;

   }
   
   /**
    * Adds the labels.
    */
   private void addLabels() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.EAST;      
      add(_startDateLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_endDateLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 2;
      add(_stationLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 3;
      add(_operatorLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 4;
      add(_rstReceivedLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 5;
      add(_rstSentLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 6;
      add(_bandLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 7;
      add(_modeLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 8;
      add(_qthLabel, gridBagConstraints) ;

      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 4;
      add(_rxPowerLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 5;
      add(_txPowerLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 6;
      add(_frequencyLabel, gridBagConstraints) ;
   }
   
   /**
    * Adds the appropriate listeners for this panel.
    */
   private void addListeners() {
      _receivedActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            receivedButtonActionPerformed(evt);
         }
      } ;
      
      _sentActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            sentButtonActionPerformed(evt);
         }
      } ;
      
      _rstReceivedButton.addActionListener(_receivedActionListener);
      _rstSentButton.addActionListener(_sentActionListener);
      
      _startDateActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            startDateButtonActionPerformed();
         }
      } ;
         
      _endDateActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            endDateButtonActionPerformed();
         }
      } ;
      
      _startDateButton.addActionListener(_startDateActionListener);
      _endDateButton.addActionListener(_endDateActionListener);

      _lookupActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            lookupButtonActionPerformed();
         }
      } ;
      _lookupButton.addActionListener(_lookupActionListener) ;

   }

   /**
    * Removes listeners from this panel.
    */
   private void removeListeners() {
      _rstReceivedButton.removeActionListener(_receivedActionListener) ;
      _rstSentButton.removeActionListener(_sentActionListener) ;
      _startDateButton.removeActionListener(_startDateActionListener) ;
      _endDateButton.removeActionListener(_endDateActionListener) ;
      _lookupButton.removeActionListener(_lookupActionListener) ;
   }

   /**
    * Handles the recieved button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void receivedButtonActionPerformed(ActionEvent evt) {
      _rstReceivedTextField.setText("59") ;
   }

   /**
    * Handles the sent button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void sentButtonActionPerformed(ActionEvent evt) {
      _rstSentTextField.setText("59") ;
   }

   /**
    * Handles the start button action.
    */
   private void startDateButtonActionPerformed() {
      String dateFormat = Utility.getDateFormat().format(new Date()) ;
      _startDateTextField.setText(dateFormat) ;
      
      String timeFormat = Utility.getTimeFormat().format(new Date()) ;
      _startTimeTextField.setText(timeFormat) ;
   }
   
   /**
    * Handles the end button action.
    */
   private void endDateButtonActionPerformed() {
      String dateFormat = Utility.getTimeFormat().format(new Date()) ;
      _endTimeTextField.setText(dateFormat) ;
   }

   /**
    * The lookup button action handler.
    */
   private void lookupButtonActionPerformed() {
      _callsignLookupDialog.setVisible(true, _stationTextField.getText()) ;
   }
   
   /**
    * Verifies that the date format is correct.
    * 
    * @param dateTextField The JTextField representing the date to parse.
    * @param timeTextField The JTextField representing the time to parse.
    * 
    * @return A date object formed from the date and time text field.
    * 
    * @throws IllegalArgumentException if the date or time text field is incorrect.  The incorrect
    * text field will be highlighted in this case.
    */
   private Date verifyDate(JTextField dateTextField, JTextField timeTextField) 
   throws IllegalArgumentException {
      String[] dateArray = dateTextField.getText().split("/", 5) ;
      
      if (dateArray.length != 3) {
         dateTextField.selectAll() ;
         dateTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid date field.  Expecting a format of mm/dd/yyyy") ;
      }
      
      int year ;
      int month ;
      int dayOfMonth ;         
      
      try {
         year = new Integer(dateArray[2]).intValue() ;
         dayOfMonth = new Integer(dateArray[1]).intValue();         
         // According to the GregorianCalendar javadoc, the month is 0 based.
         month = new Integer(dateArray[0]).intValue() - 1;         
      } catch (NumberFormatException e) {
         dateTextField.selectAll() ;
         dateTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid date field.  Expecting numbers for the date.") ;
      }
      
      String[] time = timeTextField.getText().split(":", 5) ;

      if (time.length != 3) {
         timeTextField.selectAll() ;
         timeTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid time field.  Expecting a format of hh:mm:ss") ;
      }

      int hourOfDay ;
      int minute ;
      int second ;

      try {
         hourOfDay = new Integer(time[0]).intValue();
         minute = new Integer(time[1]).intValue();
         second = new Integer(time[2]).intValue();
      } catch (NumberFormatException e) {
         timeTextField.selectAll() ;
         timeTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid time field.  Expecting numbers for the time.") ;
      }
            
      GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second) ;
      calendar.setTimeZone(Utility.getUtcTimeZone()) ;
            
      return calendar.getTime() ;      
   }

}
