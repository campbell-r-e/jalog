package openlogbook.jlogimpl.gui;

import openlogbook.jlog.gui.TableColumnChangeEvent;
import openlogbook.jlog.util.TableColumnChangeEventId;

/**
 * An implementation of the TableColumnChangeEvent.
 * 
 * @author KC0ZPS
 */
public class TableColumnChangeEventImpl implements TableColumnChangeEvent {

   private TableColumnChangeEventId _eventId ;

   static final long serialVersionUID = 3603806012738060177L;

   /**
    * Creates a new TableColumnChangeEventImpl.
    * 
    * @param eventId The event id.
    * @param description The description of the event.
    */
   public TableColumnChangeEventImpl(TableColumnChangeEventId eventId, String description) {
      _eventId = eventId ;
   }
   
   //**********
   // Implementation of InternalEvent interface.
   //**********

   /**
    * Gets a text description of the event.
    *
    * @return a text description of the event
    */
   public String getDescription() {
      return getEventId().getDescription() ;
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
      if (!(obj instanceof ThemeUpdateEventImpl)) {
         return false ;
      }
      if (obj == this) {
         return true ;
      }
      TableColumnChangeEventImpl otherObject = (TableColumnChangeEventImpl)obj ;
      if (getDescription() != otherObject.getDescription()) {
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
      result = 37*result + getDescription().hashCode() ;
      return result ;
   }

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation of the object
    */
   public String toString() {
      StringBuffer result = new StringBuffer() ;
      result.append(getDescription()) ;
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
   private TableColumnChangeEventId getEventId() {
      return _eventId ;
   }

}
