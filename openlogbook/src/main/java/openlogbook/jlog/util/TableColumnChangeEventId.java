package openlogbook.jlog.util;

import java.io.Serializable;

/**
 * An enumeration of table column change event id's.
 * 
 * @author KC0ZPS
 */
public class TableColumnChangeEventId implements Serializable {

   static final long serialVersionUID = -1782736206953888302L;
   
   /**
    * Creates a new TableColumnChangeEventId. The constructor is private. The only instances are the constants
    * instantiated in this module.
    *
    * @param eventCode    the numeric code for the event
    * @param description  a description of this internal event
    */
   private TableColumnChangeEventId(int eventCode, String description) {
      _eventCode = eventCode ;
      _description = description ;
   }

   // Public methods

   public String getDescription() {
      return _description ;
   }

   public int getEventCode() {
      return _eventCode ;
   }

   // Serialization support
   private Object readResolve() {
      return _values[_ordinal] ;
   }

   // Attributes and constants
   private static int _nextOrdinal = 0 ;
   private static final TableColumnChangeEventId[] _values = {
      OptionDialogEvent // Issue: This variable is undefined
   } ;

   private String _description ;
   private int _eventCode ;
   private int _ordinal = _nextOrdinal++ ;
}
