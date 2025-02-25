package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.jlog.logbook.jlogentry.ContactAddress;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The panel that will handle the contact address related configuration options.
 * 
 * @author KC0ZPS
 */
public class AddressPanel extends JPanel {

   private JLabel _nameLabel = new JLabel("Name:") ;
   private JLabel _addressLabel = new JLabel("Address:") ;
   private JLabel _countyLabel = new JLabel("County:") ;
   private JLabel _continentLabel = new JLabel("Continent:") ;
   private JLabel _stateLabel = new JLabel("State:") ;
   
   private JTextField _nameTextField = new JTextField() ;
   private JTextField _addressTextField = new JTextField() ;
   private JTextField _countyTextField = new JTextField() ;
   private JTextField _continentTextField = new JTextField() ;
   private JTextField _stateTextField = new JTextField() ;
   
   private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(200, 20) ;

   static final long serialVersionUID = 6838190478109823545L;

   /**
    * Creates a new ContactPanel.
    */
   public AddressPanel() {
      super() ;
      
      initialize() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param contactAddress the contactAddress data to populate this panel with.
    */
   public void setData(ContactAddress contactAddress) {
      // Default values
      _nameTextField.setText(contactAddress.getName()) ;
      _addressTextField.setText(contactAddress.getAddress()) ;
      _countyTextField.setText(contactAddress.getCounty()) ;
      _continentTextField.setText(contactAddress.getContinent()) ;
      _stateTextField.setText(contactAddress.getUsState()) ;

      // Move the viewable text to the beginning (not the end, which is default).
      _nameTextField.select(0, 0) ;
      _addressTextField.select(0, 0) ;
      _countyTextField.select(0, 0) ;
      _continentTextField.select(0, 0) ;
      _stateTextField.select(0, 0) ;

   }

   /**
    * Gets the name set by this panel.
    * 
    * @return the name set by this panel.
    */
   public String getName() {
      return _nameTextField.getText() ;
   }
   
   /**
    * Gets the address set by this panel.
    * 
    * @return the address set by this panel.
    */
   public String getAddress() {
      return _addressTextField.getText() ;
   }

   /**
    * Gets the county set by this panel.
    * 
    * @return the county set by this panel.
    */
   public String getCounty() {
      return _countyTextField.getText() ;
   }

   /**
    * Gets the continent set by this panel.
    * 
    * @return the continent set by this panel.
    */
   public String getContinent() {
      return _continentTextField.getText() ;
   }

   /**
    * Gets the U.S. State set by this panel.
    * 
    * @return the U.S. State set by this panel.
    */
   public String getUsState() {
      return _stateTextField.getText() ;
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
      add(_nameLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_addressLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 2;
      add(_countyLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 3;
      add(_continentLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 4;
      gridBagConstraints.weighty = 1 ;      
      add(_stateLabel, gridBagConstraints) ;

   }
   
   /**
    * Adds the test fields for this panel.
    */
   private void addTextFields() {
      _nameTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _nameTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _nameTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _addressTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _addressTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _addressTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _countyTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _countyTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _countyTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _continentTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _continentTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _continentTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _stateTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _stateTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _stateTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.weightx = 1 ;      
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_nameTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_addressTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 2;
      add(_countyTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 3;
      add(_continentTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 4;
      add(_stateTextField, gridBagConstraints) ;
   }
   
}
