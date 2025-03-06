package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.jlog.logbook.jlogentry.Contest;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The panel that will handle contest related configuration options.
 * 
 * @author KC0ZPS
 */
public class ContestPanel extends JPanel {

   private JLabel _receivedSerialNumberLabel = new JLabel("Received Serial Number:") ;
   private JLabel _sentSerialNumberLabel = new JLabel("Sent Serial Number:") ;
   private JLabel _contestIdLabel = new JLabel("Contest ID:") ;
   
   private JTextField _receivedSerialNumberTextField = new JTextField() ;
   private JTextField _sentSerialNumberTextField = new JTextField() ;
   private JTextField _contestIdTextField = new JTextField() ;
   
   private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(95, 20) ;
   
   static final long serialVersionUID = -7164887128093086142L;

   /**
    * Creates a new ContactPanel.
    */
   public ContestPanel() {
      super() ;
      
      initialize() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param contest the contest data to populate this panel with.
    */
   public void setData(Contest contest) {
      // Default values
      _contestIdTextField.setText(contest.getContestId()) ;
      _sentSerialNumberTextField.setText(contest.getTransmittedSerialNumber()) ;
      _receivedSerialNumberTextField.setText(contest.getReceivedSerialNumber()) ;
      
      // Move the viewable text to the beginning (not the end, which is default).
      _contestIdTextField.select(0, 0) ;
      _sentSerialNumberTextField.select(0, 0) ;
      _receivedSerialNumberTextField.select(0, 0) ;
   }

   /**
    * Returns the contestId set in this panel.
    * 
    * @return the contest id.
    */
   public String getContestId() {
      return _contestIdTextField.getText() ;
   }
   
   /**
    * Gets the sent serial number set in this panel.
    * 
    * @return the sent serial number.
    */
   public String getSentSerialNumber() {
      if (_sentSerialNumberTextField.getText().equals("")) {
         return "" ;
      }
      Integer sentSerialNumber ;
      try {
         // Even though we store age as a string, verify that its a valid number.
         sentSerialNumber = new Integer(_sentSerialNumberTextField.getText()) ;
      } catch (NumberFormatException e) {
         _sentSerialNumberTextField.selectAll() ;
         _sentSerialNumberTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid sent serial number field.  Expecting a number.") ;
      }
      return sentSerialNumber.toString() ;
   }
   
   /**
    * Gets the received serial number set in this panel.
    * 
    * @return the received serial number
    */
   public String getReceivedSerialNumber() {
      if (_receivedSerialNumberTextField.getText().equals("")) {
         return "" ;
      }
      Integer receivedSerialNumber ;
      try {
         // Even though we store age as a string, verify that its a valid number.
         receivedSerialNumber = new Integer(_receivedSerialNumberTextField.getText()) ;
      } catch (NumberFormatException e) {
         _receivedSerialNumberTextField.selectAll() ;
         _receivedSerialNumberTextField.requestFocus() ;
         throw new IllegalArgumentException("Invalid received serial number field.  Expecting a number.") ;
      }
      return receivedSerialNumber.toString() ;      
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
      addTextBoxes() ;
   }
   
   /**
    * Adds any labels for this panel.
    */
   private void addLabels() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;      
      add(_contestIdLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridy = 1;
      add(_sentSerialNumberLabel, gridBagConstraints) ;

      gridBagConstraints.gridy = 2;
      add(_receivedSerialNumberLabel, gridBagConstraints) ;
   }
   
   /**
    * Adds an text boxes for this panel.
    */
   private void addTextBoxes() {
      _receivedSerialNumberTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _receivedSerialNumberTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _receivedSerialNumberTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _sentSerialNumberTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _sentSerialNumberTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _sentSerialNumberTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      _contestIdTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
      _contestIdTextField.setMaximumSize(TEXT_FIELD_DIMENSION);
      _contestIdTextField.setMinimumSize(TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.weightx = 1 ;      
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_contestIdTextField, gridBagConstraints) ;      

      gridBagConstraints.gridy = 1;
      add(_sentSerialNumberTextField, gridBagConstraints) ;      
      
      gridBagConstraints.gridy = 2;
      add(_receivedSerialNumberTextField, gridBagConstraints) ;      
   }
}
