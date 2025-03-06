package openlogbook.debug;

import java.io.Serializable;

/**
 * Defines the interface that all log entries must implement.
 */
public interface DebugLogEntry extends Serializable {

   /**
    * Gets the contents of a cell.
    *
    * @param column  indicates which column to get
    *
    * @return the contents of a cell
    */
   public Object getCell(int column) ;

}


// End of DebugLogEntry.java

