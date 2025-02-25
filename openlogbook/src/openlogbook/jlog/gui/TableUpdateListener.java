package openlogbook.jlog.gui;

import openlogbook.jlogimpl.gui.TableColumnEnum;

import java.util.ArrayList;

/**
 * An interface defining a TableUpdateListener.
 * 
 * @author KC0ZPS
 */
public interface TableUpdateListener {

   /**
    * Updates the table columns.
    * 
    * @param tableColumnChangeEvent The tableColumnChangeEvent.
    * @param columnList The list of new columns.
    */
   public void updateColumns(TableColumnChangeEvent tableColumnChangeEvent, ArrayList<TableColumnEnum> columnList) ;
}
