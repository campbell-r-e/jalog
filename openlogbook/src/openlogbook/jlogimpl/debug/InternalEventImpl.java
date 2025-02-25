package openlogbook.jlogimpl.debug ;

import java.util.Date;

import openlogbook.debug.InternalEvent;

/**
 * Defines an internally generated event.
 */
public class InternalEventImpl implements InternalEvent {

   /**
    * Creates a new InternalEventImpl.
    *
    * @param eventId         the event id
    * @param filename        the filename of the log book associated with the event
    * @param extendedMessage additional text to qualify the event.
    */
   public InternalEventImpl(InternalEventId eventId, String filename, String extendedMessage) {
      _date = new Date() ;
      _eventId = eventId ;
      _filename = filename ;
      _extendedMessage = extendedMessage ;
   }

   //**********
   // Implementation of InternalEvent interface.
   //**********

   /**
    * Gets the numeric code associated with the event.
    *
    * @return the numeric code associated with the event
    */
   public int getEventCode() {
      return getEventId().getEventCode() ;
   }

   /**
    * Gets the date when the event occurred.
    *
    * @return the date when the event occurred
    */
   public Date getDate() {
      return _date ;
   }

   /**
    * Gets a text description of the event.
    *
    * @return a text description of the event
    */
   public String getEventDescription() {
      return getEventId().getDescription() ;
   }

   /**
    * Gets an additional message associated with the event.
    *
    * @return an additional message associated with the event
    */
   public String getExtendedMessage() {
      return _extendedMessage ;
   }


   /**
    * Gets the file name of the log book that the event was associated with.
    *
    * @return the file name of the log book that the event was associated with.
    */
   public String getFileName() {
      return _filename ;
   }
   //**********
   // Methods overridden from Object
   //**********

   /**
    * Indicates whether some other object is "equal to" this one.
    *
    * @param   obj   the reference object with which to compare.
    *
    * @return  <code>true</code> if this object is the same as the obj
    *          argument; <code>false</code> otherwise.
    */
   public boolean equals(Object obj) {
      if (!(obj instanceof InternalEventImpl)) {
         return false ;
      }
      if (obj == this) {
         return true ;
      }
      InternalEventImpl otherObject = (InternalEventImpl)obj ;
      if (getEventId() != otherObject.getEventId()) {
         return false ;
      }
      if (!getFileName().equals(otherObject.getFileName())) {
         return false ;
      }
      if (!getExtendedMessage().equals(otherObject.getExtendedMessage())) {
         return false ;
      }
      return true ;
   }


   /**
    * Returns a hash code value for the object. This method is
    * supported for the benefit of hashtables such as those provided by
    * <code>java.util.Hashtable</code>.
    *
    * @return  a hash code value for this object.
    * @see     java.lang.Object#equals(java.lang.Object)
    * @see     java.util.Hashtable
    */
   public int hashCode() {
      int result = 17 ;
      result = 37*result + getEventId().hashCode() ;
      result = 37*result + getFileName().hashCode() ;
      result = 37*result + getExtendedMessage().hashCode() ;
      return result ;
   }

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation of the object
    */
   public String toString() {
      StringBuffer result = new StringBuffer() ;
      result.append(getEventDescription()) ;
      if (getExtendedMessage().length() > 0) {
         result.append('(') ;
         result.append(getExtendedMessage()) ;
         result.append(')') ;
      }
      result.append(" from ") ;
      result.append(getFileName()) ;
      result.append(" at ") ;
      result.append(getDate()) ;
      return result.toString() ;
   }

   //**********
   // Private methods
   //**********

   /**
    * Gets the event id.
    *
    * @return the event id
    */
   private InternalEventId getEventId() {
      return _eventId ;
   }


   //**********
   // Class attributes and constants
   //**********

   private Date            _date ;
   private InternalEventId _eventId ;
   private String          _filename ;
   private String          _extendedMessage ;

   /**
    * The serialVersionUID for this class.
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = 8998063660265733836L;

}


// End of InternalEventImpl.java
