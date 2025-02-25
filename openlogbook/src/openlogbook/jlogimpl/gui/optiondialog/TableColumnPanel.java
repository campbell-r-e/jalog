package openlogbook.jlogimpl.gui.optiondialog;

import openlogbook.jlogimpl.gui.TableColumnEnum;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A panel that allows the user to decide what columns will be in the table.
 * 
 * @author KC0ZPS
 */
public class TableColumnPanel extends JPanel {

   private JList _addedList = new JList() ;
   private JList _removedList = new JList() ;
   
   private JButton _addButton = new JButton("Add >>") ;
   private JButton _removeButton = new JButton("<< Remove") ;
   private JButton _upButton = new JButton("Move Up") ;
   private JButton _downButton = new JButton("Move Down") ;
   private JButton _defaultButton = new JButton("Default") ;
   
   private Vector<TableColumnEnum> _addedVector = new Vector<TableColumnEnum>() ;
   private Vector<TableColumnEnum> _removedVector = new Vector<TableColumnEnum>() ;
   
   private Vector<TableColumnEnum> _originalAddedVector = new Vector<TableColumnEnum>() ;
   private Vector<TableColumnEnum> _originalRemovedVector = new Vector<TableColumnEnum>() ;
   
   private ActionListener _addButtonActionListener ;
   private ActionListener _removeButtonActionListener ;
   private ActionListener _upButtonActionListener ;
   private ActionListener _downButtonActionListener ;
   private ActionListener _defaultButtonActionListener ;

   private static final Dimension LIST_AREA_DIMENSION = new Dimension(130, 160) ;
   
   static final long serialVersionUID = -7037189354325071612L;
   /**
    * Creates a new DefaultValuesPanel.
    */
   public TableColumnPanel() {
      super() ;
      
      initialize() ;
      initializeLists() ;
      addListeners() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the new column data that the user wants to see.
    * 
    * @return the new column data that the user wants to see.
    */
   public ArrayList<TableColumnEnum> getColumnData() {
      return new ArrayList<TableColumnEnum>(_addedVector) ;
   }
   
   /**
    * Adds a column to the list of data.  This method adds the data to both the default data
    * and the data being modified.
    * 
    * @param tableColumnEnum the column to add.
    */
   public void addColumn(TableColumnEnum tableColumnEnum) {
      if (!_addedVector.contains(tableColumnEnum)) {
         _addedVector.add(tableColumnEnum) ;
         _removedVector.remove(tableColumnEnum) ;
         
         _originalAddedVector.add(tableColumnEnum) ;
         _originalRemovedVector.remove(tableColumnEnum) ;
      }
   }

   /**
    * Restore the old values.
    */
   public void restoreDefaultValues() {
      _addedVector = (Vector)_originalAddedVector.clone() ;
      _removedVector = (Vector)_originalRemovedVector.clone() ;      
      _removedList.setListData(_removedVector) ;
      _addedList.setListData(_addedVector) ;

   }
   
   /**
    * Set the values set in the dialog box as the new default values.
    */
   public void setDefaultValues() {
      _originalAddedVector = (Vector)_addedVector.clone() ;
      _originalRemovedVector = (Vector)_removedVector.clone() ;
   }
   
   /**
    * Cleans the panel up.  Should be called when this object is no longer needed.
    */
   public void cleanup() {
      removeListeners() ;
   }

   //**********
   // Private methods
   //**********
   
   /**
    * Initializes the lists.
    */
   private void initializeLists() {
      _addedVector.clear() ;
      _removedVector.clear() ;
      _originalAddedVector.clear();
      _originalRemovedVector.clear();

      TableColumnEnum[] tableColumnEnums = TableColumnEnum.getValues() ;
      for (int index = 0; index < TableColumnEnum.getValues().length; index++) { 
         if (!_addedVector.contains(tableColumnEnums[index])) {
            _removedVector.add(tableColumnEnums[index]) ;
            _originalRemovedVector.add(tableColumnEnums[index]) ;
         }
      }
      _removedList.setListData(_removedVector) ;
      _addedList.setListData(_addedVector) ;
   }

   /**
    * Initializes the components of this panel using BorderLayout.
    */
   private void initialize() {
      BorderLayout borderLayout = new BorderLayout() ;
      borderLayout.setHgap(20) ;
      // borderLayout.setVgap(50) ;
      setLayout(borderLayout) ;
      
      JPanel leftPanel = new JPanel() ;
      leftPanel.setLayout(new BorderLayout()) ;
      JScrollPane removedScrollPane = new JScrollPane(_removedList) ;
      removedScrollPane.setPreferredSize(LIST_AREA_DIMENSION);
      removedScrollPane.setMaximumSize(LIST_AREA_DIMENSION);
      removedScrollPane.setMinimumSize(LIST_AREA_DIMENSION);
      leftPanel.add(Box.createVerticalStrut(10), BorderLayout.NORTH) ;
      leftPanel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH) ;
      leftPanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST) ;
      leftPanel.add(removedScrollPane, BorderLayout.CENTER) ;
      add(leftPanel, BorderLayout.WEST) ;
      
      JPanel rightPanel = new JPanel() ;
      rightPanel.setLayout(new BorderLayout()) ;
      JScrollPane addedScrollPane = new JScrollPane(_addedList) ;
      addedScrollPane.setPreferredSize(LIST_AREA_DIMENSION);
      addedScrollPane.setMaximumSize(LIST_AREA_DIMENSION);
      addedScrollPane.setMinimumSize(LIST_AREA_DIMENSION);
      rightPanel.add(Box.createVerticalStrut(10), BorderLayout.NORTH) ;
      rightPanel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH) ;
      rightPanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST) ;
      rightPanel.add(addedScrollPane, BorderLayout.CENTER) ;
      add(rightPanel, BorderLayout.EAST) ;
      
      GridLayout gridLayout = new GridLayout(7, 1) ;
      // gridLayout.setHgap(5) ;
      gridLayout.setVgap(7) ;
      JPanel buttonPanel = new JPanel() ;
      buttonPanel.setLayout(gridLayout) ;
      buttonPanel.add(Box.createVerticalStrut(10)) ;
      buttonPanel.add(_addButton) ;
      buttonPanel.add(_removeButton) ;
      buttonPanel.add(_upButton) ;
      buttonPanel.add(_downButton) ;
      buttonPanel.add(_defaultButton) ;
      buttonPanel.add(Box.createVerticalStrut(10)) ;
      add(buttonPanel, BorderLayout.CENTER) ;
   }
   
   /**
    * Initializes the components of this panel using GridbagLayout.
    */
   /* private void initialize1() {
      setLayout(new GridBagLayout()) ;
      GridBagConstraints gridBagConstraints1 = new GridBagConstraints();      
      gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
      gridBagConstraints1.anchor = GridBagConstraints.CENTER;

      JScrollPane removedScrollPane = new JScrollPane(_removedList) ;
      removedScrollPane.setPreferredSize(LIST_AREA_DIMENSION);
      removedScrollPane.setMaximumSize(LIST_AREA_DIMENSION);
      removedScrollPane.setMinimumSize(LIST_AREA_DIMENSION);      
      gridBagConstraints1.gridx = 0;
      gridBagConstraints1.gridy = 0;
      gridBagConstraints1.gridheight = 5 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(removedScrollPane, gridBagConstraints1);
      
      JScrollPane addedScrollPane = new JScrollPane(_addedList) ;
      addedScrollPane.setPreferredSize(LIST_AREA_DIMENSION);
      addedScrollPane.setMaximumSize(LIST_AREA_DIMENSION);
      addedScrollPane.setMinimumSize(LIST_AREA_DIMENSION);      
      gridBagConstraints1.gridx = 3;
      gridBagConstraints1.gridy = 0;
      gridBagConstraints1.gridheight = 5 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(addedScrollPane, gridBagConstraints1);

      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 0;
      gridBagConstraints1.gridheight = 1 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(_addButton, gridBagConstraints1);
      
      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 1;
      gridBagConstraints1.gridheight = 1 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(_removeButton, gridBagConstraints1);
      
      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 2;
      gridBagConstraints1.gridheight = 1 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(_upButton, gridBagConstraints1);

      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 3;
      gridBagConstraints1.gridheight = 1 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(_downButton, gridBagConstraints1);

      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 4;
      gridBagConstraints1.gridheight = 1 ;
      gridBagConstraints1.gridwidth = 1 ;
      add(_defaultButton, gridBagConstraints1);
   } */
   
   /**
    * Initializes all listeners in this panel.
    */
   private void addListeners() {
      _addButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            addButtonActionPerformed(evt);
         }
      } ;

      _removeButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            removeButtonActionPerformed(evt);
         }
      } ;

      _addButton.addActionListener(_addButtonActionListener);
      _removeButton.addActionListener(_removeButtonActionListener);

      _upButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            upButtonActionPerformed(evt);
         }
      } ;

      _downButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            downButtonActionPerformed(evt);
         }
      } ;

      _defaultButtonActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            defaultButtonActionPerformed(evt);
         }
      } ;

      _upButton.addActionListener(_upButtonActionListener);
      _downButton.addActionListener(_downButtonActionListener);
      _defaultButton.addActionListener(_defaultButtonActionListener) ;
   }
   
   /**
    * Removes all listeners in this panel.
    */
   private void removeListeners() {
      _addButton.removeActionListener(_addButtonActionListener) ;
      _removeButton.removeActionListener(_removeButtonActionListener) ;
      _upButton.removeActionListener(_upButtonActionListener) ;
      _downButton.removeActionListener(_downButtonActionListener) ;
   }

   /**
    * Handles the add button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void addButtonActionPerformed(ActionEvent evt) {
      handleListAction(_removedList, _addedList, _removedVector, _addedVector) ;
   }

   /**
    * Handles the remove button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void removeButtonActionPerformed(ActionEvent evt) {
      handleListAction(_addedList, _removedList, _addedVector, _removedVector) ;
   }

   /**
    * Handles the up button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void upButtonActionPerformed(ActionEvent evt) {
      int selectedIndex = _addedList.getSelectedIndex() ;
      
      if (selectedIndex > 0) {
         Collections.swap(_addedVector, selectedIndex - 1, selectedIndex);   
         _addedList.setListData(_addedVector) ;
         _addedList.setSelectedIndex(selectedIndex - 1) ;
      }
   }
   
   /**
    * Handles the down button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void downButtonActionPerformed(ActionEvent evt) {
      int selectedIndex = _addedList.getSelectedIndex() ;
      
      if (selectedIndex > -1 && selectedIndex < _addedVector.size() - 1) {
         Collections.swap(_addedVector, selectedIndex, selectedIndex + 1);   
         _addedList.setListData(_addedVector) ;
         _addedList.setSelectedIndex(selectedIndex + 1) ;
      }
   }

   /**
    * The default button action.
    * 
    * @param evt The ActionEvent object.
    */
   private void defaultButtonActionPerformed(ActionEvent evt) {
      _addedVector.clear() ;
      _removedVector.clear() ;
      
      _addedVector.add(TableColumnEnum.QsoDate) ;
      _addedVector.add(TableColumnEnum.TimeOn) ;
      _addedVector.add(TableColumnEnum.TimeOff) ;
      _addedVector.add(TableColumnEnum.ContactStation) ;
      _addedVector.add(TableColumnEnum.Frequency) ;
      _addedVector.add(TableColumnEnum.Band) ;
      _addedVector.add(TableColumnEnum.Mode) ;
      _addedVector.add(TableColumnEnum.RstSent) ;
      _addedVector.add(TableColumnEnum.RstReceived) ;
      _addedVector.add(TableColumnEnum.Name) ;
      _addedVector.add(TableColumnEnum.Address) ;
      _addedVector.add(TableColumnEnum.Qth) ;
      _addedVector.add(TableColumnEnum.Comment) ;
      
      TableColumnEnum[] tableColumnEnums = TableColumnEnum.getValues() ;
      for (int index = 0; index < TableColumnEnum.getValues().length; index++) { 
         if (!_addedVector.contains(tableColumnEnums[index])) {
            _removedVector.add(tableColumnEnums[index]) ;            
         }
      }
      _removedList.setListData(_removedVector) ;
      _addedList.setListData(_addedVector) ;

   }

   
   /**
    * Moves an element from one list to another.
    * 
    * @param from The JList to move the element from.
    * @param to The JList to move the element to.
    * @param fromVector The vector to move the element from.
    * @param toVector The vector to move the element to.
    */
   private void handleListAction(JList from, JList to, Vector<TableColumnEnum> fromVector, Vector<TableColumnEnum> toVector) {
      Object[] object = from.getSelectedValues() ;
      for (int index = 0; index < object.length; index++) {
         TableColumnEnum tableColumnEnum = (TableColumnEnum)object[index] ;
         if (!toVector.contains(tableColumnEnum)) {
            toVector.add(tableColumnEnum) ;
            fromVector.remove(tableColumnEnum) ;
         }
      }
      to.setListData(toVector) ;      
      from.setListData(fromVector) ;
   }
  
}
