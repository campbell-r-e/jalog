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

   //**********
   // Methods overridden from Object
   //**********
   
   /**
    * Returns a string representation of the object.
    *
    * @return string representation of the object.
    */
   public String toString() { 
      return String.valueOf(getEventCode()) + ": " + getDescription() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the event description.
    *
    * @return the event description
    */
   public String getDescription() {
      return _description ;
   }

   /**
    * Gets the event code.
    *
    * @return the event code
    */
   public int getEventCode() {
      return _eventCode ;
   }

   //**********
   // Private methods
   //**********

   /**
    * Returns a substitute object during deserialization. The intention is to prevent duplicate
    * constants.
    *
    * @return a substitute object during deserialization.
    */
   private Object readResolve() {
      return _values[_ordinal] ;
   }

   //**********
   // Class attributes and constants
   //**********

   /**
    * A general theme update event id.
    */
   public static final TableColumnChangeEventId OptionDialogEvent = new TableColumnChangeEventId(1, "Option Dialog Event") ;

   // The following are used to support Serialization. Together with the use of readResolve(),
   // this is intended to prevent duplicate constants from coexisting as a result of deserialization.
   // The idea was taken from the book "Effective Java Programming" by Joshua Bloch.
   // NOTE: The entries in the _values array must be in the same order as the declaration order of the
   // constants above.
   private static int                     _nextOrdinal = 0 ;
   private static final TableColumnChangeEventId[] _values = {
      OptionDialogEvent
   } ;
  
   private String       _description ;
   private int          _eventCode ;
   private int          _ordinal = _nextOrdinal++ ;

   /**
    * The serialVersionUID.
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */


}
