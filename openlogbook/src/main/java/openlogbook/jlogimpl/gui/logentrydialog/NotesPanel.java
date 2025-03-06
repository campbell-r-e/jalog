package openlogbook.jlogimpl.gui.logentrydialog;

import openlogbook.jlog.logbook.jlogentry.Misc;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


/**
 * The panel that will handle notes.
 * 
 * @author KC0ZPS
 */
public class NotesPanel extends JPanel {

   private JPanel    _notePanel = new JPanel() ;   
   private JTextArea _noteTextArea = new JTextArea() ;

   private static final Dimension NOTES_TEXT_FIELD_DIMENSION = new Dimension(400, 300) ;

   static final long serialVersionUID = 8469489550469965553L;

   /**
    * Creates a new ContactPanel.
    */
   public NotesPanel() {
      super() ;
      
      initialize() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the data for this panel.
    * 
    * @param misc the misc data to populate this panel with.
    */
   public void setData(Misc misc) {
      // Default values
      _noteTextArea.setText(misc.getNotes()) ;

      // Move the viewable text to the beginning (not the end, which is default).
      _noteTextArea.select(0, 0) ;
   }

   /**
    * Gets the long text for digital copy, third party traffic, etc.
    * A "" indicates that this field is not in use.
    * 
    * @return the long text for digital copy, third party traffic, etc.
    */
   public String getNote() {
      return _noteTextArea.getText() ;
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
      _noteTextArea.setPreferredSize(NOTES_TEXT_FIELD_DIMENSION);
      _noteTextArea.setMaximumSize(NOTES_TEXT_FIELD_DIMENSION);
      _noteTextArea.setMinimumSize(NOTES_TEXT_FIELD_DIMENSION);

      _notePanel.setBorder(new TitledBorder("Long text for digital copy, third party traffic, etc."));
      _notePanel.add(new JScrollPane(_noteTextArea)) ;
      
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.gridwidth = 4 ;
      gridBagConstraints.weightx = 1 ;      
      gridBagConstraints.weighty = 1 ;      
      gridBagConstraints.insets = new Insets(2, 5, 0, 0);
      gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;      
      add(_notePanel, gridBagConstraints) ;   
   }

   
}
