package openlogbook.jlogimpl.debug ;

import java.io.Serializable;

/**
 * An enumeration of internal events.
 *
 */
public class InternalEventId implements Serializable {

   /**
    * Creates a new InternalEventId. The constructor is private. The only instances are the constants
    * instantiated in this module.
    *
    * @param eventCode    the numeric code for the event
    * @param description  a description of this internal event
    */
   private InternalEventId(int eventCode, String description) {
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
    * A general error id.  As more error conditions are defined, I'll create more groups.
    */
   public static final InternalEventId
      GeneralError= new InternalEventId(1, "General Error") ;

   // The following are used to support Serialization. Together with the use of readResolve(),
   // this is intended to prevent duplicate constants from coexisting as a result of deserialization.
   // The idea was taken from the book "Effective Java Programming" by Joshua Bloch.
   // NOTE: The entries in the _values array must be in the same order as the declaration order of the
   // constants above.
   private static int                     _nextOrdinal = 0 ;
   private static final InternalEventId[] _values = {
      GeneralError
   } ;

   private String       _description ;
   private int          _eventCode ;
   private int          _ordinal = _nextOrdinal++ ;


   /**
    * The serialVersionUID for the class openlogbook.jlogimpl.debug.InternalEventId
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -5396595714450917416L;

}


// End of InternalEventId.java

