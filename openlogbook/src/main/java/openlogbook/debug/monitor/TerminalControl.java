package openlogbook.debug.monitor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * A terminal-style window for the debug monitor.
 */
class TerminalControl extends JScrollPane implements TerminalModelListener {

  /**
   * Creates a new TerminalControl.
   * 
   * @param terminalModel the model for this control
   */
   public TerminalControl(TerminalModel terminalModel) {
      super() ;
      _terminalModel = terminalModel ;
      _terminalModel.addListener(this) ;
      try  {
         jbInit();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   //********
   // Implementation of TerminalModelListener interface
   //********

   /**
    * The current active row in the model has changed.
    *
    * @param row  the new active row number
    *
    */
   public void activeRowChanged(int row) {
      int topRow = _vScrollBar.getValue() / _vScrollBar.getUnitIncrement() ;
      int count = _vScrollBar.getVisibleAmount() / _vScrollBar.getUnitIncrement() ;
      if ((count > 0) && (row >= topRow + count - 1 || row < topRow)) {
         int y = (row - count)*_vScrollBar.getUnitIncrement() ;
         _vScrollBar.setValue(y) ;
      }
   }


   /**
    * The contents of the terminal model have changed.
    *
    *
    */
   public void contentsChanged() {
      _terminalDisplay.repaint() ;
   }

   /**
    * The contents have scrolled by one row.
    */
   public void dataScrolled() {
   }

   //**********
   // Private methods
   //**********

   /**
    * JBuilder initialization.
    *
    * @throws Exception on any exception
    */
   private void jbInit() throws Exception {
      _terminalDisplay = new TerminalDisplay(_terminalModel) ;
      setBackground(Color.white);
      setSize(new Dimension(255, 174));
      getViewport().add(_terminalDisplay, null);
      _vScrollBar = getVerticalScrollBar() ;
      _vScrollBar.setUnitIncrement(_terminalDisplay.getRowHeight()) ;
   }

   //**********
   // Class attributes and constants
   //**********

   private TerminalDisplay          _terminalDisplay ;
   private JScrollBar               _vScrollBar ;
   private transient TerminalModel  _terminalModel ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.TerminalControl
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 3077708425443876187L;

}


// End of TerminalControl.java


