package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.jlog.logbook.jlogentry.Satellite;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The panel that will handle satellite related configuration options.
 * 
 * @author KC0ZPS
 */
public class SatellitePanel extends JPanel {

   private JLabel _propModeLabel = new JLabel("Prop Mode:") ;
   private JLabel _satelliteModeLabel = new JLabel("Satellite Mode:") ;
   private JLabel _satelliteNameLabel = new JLabel("Satellite Name:") ;
   
   private JTextField _propModeTextField = new JTextField() ;
   private JTextField _satelliteModeTextField = new JTextField() ;
   private JTextField _satelliteNameTextField = new JTextField() ;

   private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(150, 20) ;

   static final long serialVersionUID = -4067420664882665925L;

   /**
    * Creates a new SatellitePanel.
    */
   public SatellitePanel() {
      super() ;
      
      initialize() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param satellite the satellite data to populate this panel with.
    */
   public void setData(Satellite satellite) {
      // Default values
      _propModeTextField.setText(satellite.getPropMode()) ;
      _satelliteModeTextField.setText(satellite.getSatelliteMode()) ;
      _satelliteNameTextField.setText(satellite.getSatelliteName()) ;
      
      // Move the viewable text to the beginning (not the end, which is default).
      _propModeTextField.select(0, 0) ;
      _satelliteModeTextField.select(0, 0) ;
      _satelliteNameTextField.select(0, 0) ;
   }

   /**
    * Gets the prop mode.  A "" indicates that this field is not in use.
    * 
    * @return the prop mode.
    */
   public String getPropMode() {
      return _propModeTextField.getText() ;
   }
   
   /**
    * Gets the satellite mode.  A "" indicates that this field is not in use.
    * 
    * @return the satellite mode.
    */
   public String getSatelliteMode() {
      return _satelliteModeTextField.getText() ;
   }
   
   /**
    * Gets the satellite name.  A "" indicates that this field is not in use.
    * 
    * @return the satellite name.
    */
   public String getSatelliteName() {
      return _satelliteNameTextField.getText() ;
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
    * Adds the labels.
    */
   private void addLabels() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;      
      add(_propModeLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_satelliteModeLabel, gridBagConstraints) ;
            
      gridBagConstraints.gridy = 2;
      add(_satelliteNameLabel, gridBagConstraints) ;

   }
   
   /**
    * Adds the text fields.
    */
   private void addTextFields() {
      _propModeTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _propModeTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _propModeTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _satelliteModeTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _satelliteModeTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _satelliteModeTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _satelliteNameTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _satelliteNameTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _satelliteNameTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.weightx = 1 ;      
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_propModeTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 1;
      add(_satelliteModeTextField, gridBagConstraints) ;

      gridBagConstraints.gridy = 2;
      add(_satelliteNameTextField, gridBagConstraints) ;
   }

}
