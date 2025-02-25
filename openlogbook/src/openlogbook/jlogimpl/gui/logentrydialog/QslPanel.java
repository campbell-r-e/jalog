package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.jlog.logbook.jlogentry.Qsl;
import openlogbook.jlog.util.QslRequest;
import openlogbook.util.Utility;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * The panel that will handle qsl related configuration options.
 * 
 * @author KC0ZPS
 */
public class QslPanel extends JPanel {

   private JLabel _qslSentLabel = new JLabel("QSL Sent:") ;
   private JLabel _qslReceivedLabel = new JLabel("QSL Received:") ;
   private JLabel _qslSentDateLabel = new JLabel("Sent Date:") ;
   private JLabel _qslReceivedDateLabel = new JLabel("Received Date:") ;
   private JLabel _qslViaLabel = new JLabel("QSL Via:") ;
   
   private JTextField _sentDateTextField = new JTextField() ;
   private JTextField _receivedDateTextField = new JTextField() ;
   private JTextField _qslViaTextField = new JTextField() ;
   
   private JComboBox _qslSentComboBox = new JComboBox(QslRequest.getValues()) ;
   private JComboBox _qslReceivedComboBox = new JComboBox(QslRequest.getValues()) ;
   
   private JPanel    _messagePanel = new JPanel() ;   
   private JTextArea _messageTextArea = new JTextArea() ;
 
   private static final Dimension DATE_TEXT_FIELD_DIMENSION = new Dimension(83, 20) ;
   private static final Dimension VIA_TEXT_FIELD_DIMENSION = new Dimension(125, 20) ;
   private static final Dimension MESSAGE_TEXT_FIELD_DIMENSION = new Dimension(400, 150) ;

   static final long serialVersionUID = -7193524914394957782L;

   /**
    * Creates a new ContactPanel.
    */
   public QslPanel() {
      super() ;
      
      initialize() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param qsl the qsl data to populate this panel with.
    */
   public void setData(Qsl qsl) {
      _qslSentComboBox.setEnabled(true) ;
      _qslReceivedComboBox.setEnabled(true) ;

      // Default values
      _sentDateTextField.setText(qsl.getQslSentDateString()) ;
      _receivedDateTextField.setText(qsl.getQslReceivedDateString()) ;
      _qslSentComboBox.setSelectedItem(qsl.isQslSent()) ;
      _qslReceivedComboBox.setSelectedItem(qsl.isQslReceived()) ;
      _qslViaTextField.setText(qsl.getQslVia()) ;
      _messageTextArea.setText(qsl.getQslMessage()) ;
      
      // Move the viewable text to the beginning (not the end, which is default).
      _sentDateTextField.select(0, 0) ;
      _receivedDateTextField.select(0, 0) ;

      // Disable unknown values.
      if (qsl.isQslSent() != null && qsl.isQslSent().equals(QslRequest.Unknown)) {
         _qslSentComboBox.setEnabled(false) ;
      }
      if (qsl.isQslReceived() != null && qsl.isQslReceived().equals(QslRequest.Unknown)) {
         _qslReceivedComboBox.setEnabled(false) ;
      }
   }

   /**
    * Gets the QSL Sent Request.
    * 
    * @return the QSL Sent Request.
    */
   public QslRequest isQslSent() {
      int index = _qslSentComboBox.getSelectedIndex() ;
      QslRequest qslRequest = (QslRequest)QslRequest.getIntToBandType().getObjectValue(index) ;

      return qslRequest ;
   }
   
   /**
    * Gets the QSL Received Request.
    * 
    * @return the QSL Received Request.
    */
   public QslRequest isQslReceived() {
      int index = _qslReceivedComboBox.getSelectedIndex() ;
      QslRequest qslRequest = (QslRequest)QslRequest.getIntToBandType().getObjectValue(index) ;

      return qslRequest ;
   }
   
   /**
    * Gets the qsl sent date set by this panel.
    * Returns null if a date is not set.
    * 
    * @return the qsl sent date set by this panel.
    */
   public Date getQslSentDate() {
      if (_sentDateTextField.getText().equals("")) {
         return null ;
      } else {
         return verifyDate(_sentDateTextField) ;
      }
   }

   /**
    * Gets the qsl received date set by this panel.
    * Returns null if a date is not set.
    * 
    * @return the qsl received date set by this panel.
    */
   public Date getQslReceivedDate() {
      if (_receivedDateTextField.getText().equals("")) {
         return null ;
      } else {
         return verifyDate(_receivedDateTextField) ;         
      }
   }
   
   /**
    * Gets the QSL Via text.
    * 
    * @return the QSL Via text.
    */
   public String getQslVia() {
      return _qslViaTextField.getText() ;
   }
   
   /**
    * Gets the personal message to appear on qsl card.
    * 
    * @return the personal message to appear on qsl card.
    */
   public String getQslMessage() {
      return _messageTextArea.getText() ;
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
      addComboBoxes() ;
      addTextBoxes() ;
      addCommentPanel() ;
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
      add(_qslSentLabel, gridBagConstraints) ;

      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      add(_qslReceivedLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      add(_qslSentDateLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      add(_qslReceivedDateLabel, gridBagConstraints) ;
      
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      add(_qslViaLabel, gridBagConstraints) ;
   }
   
   /**
    * Adds the combo boxes.
    */
   private void addComboBoxes() {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_qslSentComboBox, gridBagConstraints) ;      
      
      gridBagConstraints.gridy = 1;
      add(_qslReceivedComboBox, gridBagConstraints) ;
   }
   
   /**
    * Adds the text boxes.
    */
   private void addTextBoxes() {
      _sentDateTextField.setPreferredSize(DATE_TEXT_FIELD_DIMENSION);
      _sentDateTextField.setMaximumSize(DATE_TEXT_FIELD_DIMENSION);
      _sentDateTextField.setMinimumSize(DATE_TEXT_FIELD_DIMENSION);

      _receivedDateTextField.setPreferredSize(DATE_TEXT_FIELD_DIMENSION);
      _receivedDateTextField.setMaximumSize(DATE_TEXT_FIELD_DIMENSION);
      _receivedDateTextField.setMinimumSize(DATE_TEXT_FIELD_DIMENSION);

      _qslViaTextField.setPreferredSize(VIA_TEXT_FIELD_DIMENSION);
      _qslViaTextField.setMaximumSize(VIA_TEXT_FIELD_DIMENSION);
      _qslViaTextField.setMinimumSize(VIA_TEXT_FIELD_DIMENSION);

      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_sentDateTextField, gridBagConstraints) ;     
      
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      add(_receivedDateTextField, gridBagConstraints) ; 
      
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.gridwidth = 2;
      add(_qslViaTextField, gridBagConstraints) ;            
   }
   
   /**
    * Adds the comment pane.
    */
   private void addCommentPanel() {
      _messageTextArea.setPreferredSize(MESSAGE_TEXT_FIELD_DIMENSION);
      _messageTextArea.setMaximumSize(MESSAGE_TEXT_FIELD_DIMENSION);
      _messageTextArea.setMinimumSize(MESSAGE_TEXT_FIELD_DIMENSION);

      _messagePanel.setBorder(new TitledBorder("Message"));
      _messagePanel.add(new JScrollPane(_messageTextArea)) ;
      
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.gridwidth = 4 ;
      gridBagConstraints.weightx = 1 ;      
      gridBagConstraints.weighty = 1 ;      
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_messagePanel, gridBagConstraints) ;
   }

   /**
    * Verifies that the date format is correct.
    * 
    * @param dateTextField The JTextField representing the date to parse.
    * 
    * @return A date object formed from the date text field.
    * 
    * @throws IllegalArgumentException if the date text field is incorrect.  The incorrect
    * text field will be highlighted in this case.
    */
   private Date verifyDate(JTextField dateTextField) {
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

      GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth) ;
      calendar.setTimeZone(Utility.getUtcTimeZone()) ;
            
      return calendar.getTime() ;      

   }

}
