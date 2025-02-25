package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.logbook.jlogentry.ContactStationInformation;
import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.logbook.jlogentry.Misc;
import openlogbook.jlog.util.IotaEnum;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The panel that will handle general contact information related options.
 * 
 * @author KC0ZPS
 */
public class ContactInformationPanel extends JPanel {

   private JLabel _ageLabel = new JLabel("Age:") ;
   private JLabel _iotaLabel = new JLabel("IOTA:") ;
   private JLabel _dashLabel = new JLabel("-") ;
   private JLabel _tentenLabel = new JLabel("Ten-Ten") ;
   private JLabel _veProvLabel = new JLabel("VE Prov:") ;
   private JLabel _arrlSectionLabel = new JLabel("ARRL Section:") ;
   private JLabel _cqzLabel = new JLabel("CQ Zone:") ;
   private JLabel _wpxPrefixLabel = new JLabel("WPX Prefix:") ;
   private JLabel _dxccLabel = new JLabel("DXCC:") ;
   private JLabel _ituzLabel = new JLabel("ITU Zone:") ;
   private JLabel _gridSquareLabel = new JLabel("Grid Square:") ;
   
   private JTextField _ageTextField = new JTextField() ;
   private JTextField _iotaNumberTextField = new JTextField() ;
   private JTextField _tentenTextField = new JTextField() ;
   private JTextField _veProvTextField = new JTextField() ;
   private JTextField _arrlSectionTextField = new JTextField() ;
   private JTextField _cqzTextField = new JTextField() ;
   private JTextField _wpxPrefixTextField = new JTextField() ;
   private JTextField _dxccTextField = new JTextField() ;
   private JTextField _ituzTextField = new JTextField() ;
   private JTextField _gridSquareTextField = new JTextField() ;
   
   private JComboBox _iotaComboBox = new JComboBox(IotaEnum.getValues()) ;
   
   private static final Dimension AGE_TEXT_FIELD_DIMENSION = new Dimension(45, 20) ;
   private static final Dimension IOTA2_TEXT_FIELD_DIMENSION = new Dimension(35, 20) ;
   private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(125, 20) ;

   static final long serialVersionUID = 8240900603820576809L;

   /**
    * Creates a new ContactInformationPanel.
    */
   public ContactInformationPanel() {
      super() ;
      
      initialize() ;
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
      Misc misc = logEntry.getMisc(); 
      ContactStationInformation contactStationInformation = logEntry.getContactStationInformation() ;
      _iotaComboBox.setEnabled(true) ;
      
      // Default values
      _ageTextField.setText(contactStationInformation.getAge()) ;
      _tentenTextField.setText(misc.getTenTen()) ;
      _veProvTextField.setText(misc.getVeProv()) ;
      _arrlSectionTextField.setText(misc.getArrlSection()) ;
      _cqzTextField.setText(contactStationInformation.getCqZone()) ;
      _wpxPrefixTextField.setText(misc.getWpxPrefix()) ;
      _dxccTextField.setText(contactStationInformation.getDxcc()) ;
      _ituzTextField.setText(contactStationInformation.getItuZone()) ;
      _gridSquareTextField.setText(contactStationInformation.getGridSquare()) ;
      Iota iota = contactStationInformation.getIota() ;
      if (iota != null) {
         _iotaComboBox.setSelectedItem(iota.getIotaEnum()) ;
         _iotaNumberTextField.setText("" + iota.getValue()) ;         
      } else {
         _iotaComboBox.setSelectedIndex(0) ;
         _iotaNumberTextField.setText("") ;
      }

      // Move the viewable text to the beginning (not the end, which is default).
      _ageTextField.select(0, 0) ;
      _tentenTextField.select(0, 0) ;
      _veProvTextField.select(0, 0) ;
      _arrlSectionTextField.select(0, 0) ;
      _cqzTextField.select(0, 0) ;
      _wpxPrefixTextField.select(0, 0) ;
      _dxccTextField.select(0, 0) ;
      _ituzTextField.select(0, 0) ;
      _gridSquareTextField.select(0, 0) ;
      _iotaNumberTextField.select(0, 0) ;
      
      // Disable unknown values.
      if (iota != null && iota.getIotaEnum().equals(IotaEnum.Unknown)) {
         _iotaComboBox.setEnabled(false) ;
      }
   }

   /**
    * Gets the age set by this panel.  A "" indicates that the field is not used.
    * 
    * @return the age.
    */
   public String getAge() {
      if (_ageTextField.getText().equals("")) {
         return "" ;
      }
      Integer age ;
      try {
         // Even though we store age as a string, verify that its a valid number.
         age = new Integer(_ageTextField.getText()) ;
      } catch (NumberFormatException e) {
         _ageTextField.selectAll() ;
         _ageTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid age field.  Expecting a number.") ;
      }
      return age.toString() ;
   }
   
   /**
    * Gets the iota set by this panel.  A null will be returned if the blank iota is selected.
    * 
    * @return the iota set by this panel.
    */
   public Iota getIota() {
      int index = _iotaComboBox.getSelectedIndex() ;
      IotaEnum iotaEnum = (IotaEnum)IotaEnum.getIntToIotaType().getObjectValue(index) ;
      
      if (iotaEnum.equals(IotaEnum.Blank)) {
         return null ;
      }
      
      Integer iotaInt ;      
      try {
         iotaInt = new Integer(_iotaNumberTextField.getText()) ;
      } catch (NumberFormatException e) {
         _iotaNumberTextField.selectAll() ;
         _iotaNumberTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid time field.  Expecting numbers for the time.") ;
      }

      if (iotaInt == null) {
         throw new IllegalArgumentException("IOTA Value is null.  How the heck?") ;
      }
      
      return DefaultLogBookFactory.createIota(iotaEnum, iotaInt) ;
   }

   /**
    * Gets the Ten Ten identifier.  A "" indicates that this field is not in use.
    * 
    * @return the Ten Ten identifier.
    */
   public String getTenTen() {
      return _tentenTextField.getText() ;
   }
   
   /**
    * Gets the VE_PROV.  A "" indicates that this field is not in use.
    * 
    * @return the VE_PROV.
    */
   public String getVeProv() {
      return _veProvTextField.getText() ;
   }
   
   /**
    * Gets the ARRL Section.  A "" indicates that this field is not in use.
    * 
    * @return the ARRL Section.
    */
   public String getArrlSection()  {
      return _arrlSectionTextField.getText() ;
   }
   
   /**
    * Gets the CQ Zone.  A "" indicates that this field is not in use.
    * 
    * @return the CQ Zone.
    */
   public String getCqZone() {
      return _cqzTextField.getText() ;
   }
   
   /**
    * Gets the WPX Prefix.  A "" indicates that this field is not in use.
    * 
    * @return the WPX Prefix.
    */
   public String getwpxPrefix() {
      return _wpxPrefixTextField.getText() ;
   }
   
   /**
    * Gets the numeric identifiers from ARRL.   A "" indicates that this field is not in use.
    * 
    * @return the numeric identifiers from ARRL.
    */
   public String getDxcc() {
      return _dxccTextField.getText() ;
   }
   
   /**
    * Gets the ITU Zone.  A "" indicates that this field is not in use.
    * 
    * @return the ITU Zone.
    */
   public String getItuZone() {
      return _ituzTextField.getText() ;
   }
   
   /**
    * Gets the grid square.  A "" indicates that this field is not in use.
    * 
    * @return the grid square.
    */
   public String getGridSquare() {
      return _gridSquareTextField.getText() ;
   }
   
   /**
    * Cleans this panel up.  This should be called when the panel is no longer needed.
    */
   public void cleanup() {
      
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
      addComboBoxes() ;
   }
   
   /**
    * Adds the labels for this panel.
    */
   private void addLabels() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;      
      add(_ageLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_iotaLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 2;
      add(_tentenLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 3;
      add(_arrlSectionLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 4;
      add(_wpxPrefixLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 5;
      add(_ituzLabel, gridBagConstraints) ;
            
      gridBagConstraints.gridx = 4;
      gridBagConstraints.gridy = 2;
      add(_veProvLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 3;
      add(_cqzLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 4;
      add(_dxccLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 5;
      gridBagConstraints.weighty = 1 ;      
      add(_gridSquareLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.weighty = 0 ;      
      gridBagConstraints.anchor = GridBagConstraints.CENTER ;
      add(_dashLabel, gridBagConstraints) ;
   }
   
   /**
    * Adds the text fields for this panel.
    */
   private void addTextFields() {
      _ageTextField.setPreferredSize(AGE_TEXT_FIELD_DIMENSION);
      _ageTextField.setMaximumSize(AGE_TEXT_FIELD_DIMENSION);
      _ageTextField.setMinimumSize(AGE_TEXT_FIELD_DIMENSION);

      _tentenTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _tentenTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _tentenTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _arrlSectionTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _arrlSectionTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _arrlSectionTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _wpxPrefixTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _wpxPrefixTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _wpxPrefixTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _ituzTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _ituzTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _ituzTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _iotaNumberTextField.setPreferredSize(IOTA2_TEXT_FIELD_DIMENSION);
      _iotaNumberTextField.setMaximumSize(IOTA2_TEXT_FIELD_DIMENSION);
      _iotaNumberTextField.setMinimumSize(IOTA2_TEXT_FIELD_DIMENSION);

      _veProvTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _veProvTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _veProvTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _cqzTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _cqzTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _cqzTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _dxccTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _dxccTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _dxccTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _gridSquareTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _gridSquareTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _gridSquareTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 3;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_ageTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 2;
      add(_tentenTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 3;
      add(_arrlSectionTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 4;
      add(_wpxPrefixTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 5;
      add(_ituzTextField, gridBagConstraints) ;

      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      add(_iotaNumberTextField, gridBagConstraints) ;

      gridBagConstraints.gridx = 5;
      gridBagConstraints.gridy = 2;
      add(_veProvTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 3;
      add(_cqzTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 4;
      add(_dxccTextField, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 5;
      add(_gridSquareTextField, gridBagConstraints) ;

   }
   
   /**
    * Adds the combo boxes for this panel.
    */
   private void addComboBoxes() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_iotaComboBox, gridBagConstraints) ;  
   }
}
