package openlogbook.debug.monitor;

/**
 * A TerminalModelListener is notified when something has changed in a TerminalModel.
 */
interface TerminalModelListener {

   /**
    * The current active row in the model has changed.
    *
    * @param row  - the new active row number
    *
    */
   public void activeRowChanged(int row) ;


   /**
    * The contents of the terminal model have changed.
    *
    */
   public void contentsChanged() ;

   /**
    * The contents have scrolled by one row.
    *
    */
   public void dataScrolled() ;


}


// End of TerminalModelListener.java

