package openlogbook.debug;

import java.util.Date;

/**
 * A simple data class to pair a Date with an exception.
 * This class is used for entries in the exception log.
 */
class ExceptionLogEntry {

   /**
    * Creates a new ExceptionLogEntry.
    *
    * @param throwable  the exception being logged.
    */
   public ExceptionLogEntry(Throwable throwable) {
      _date = new Date() ;
      _throwable = throwable ;
   }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation of the object
    */
   public String toString() {
      return "ExceptionLogEntry: " + getThrowable().toString() ;
   }


   //**********
   // Package methods
   //**********

   /**
    * Gets the date associated with the entry.
    *
    *
    * @return the date associated with the entry.
    */
   Date getDate() {
      return _date ;
   }

   /**
    * Gets the throwable object associated with the entry.
    *
    *
    * @return the throwable object associated with the entry.
    */
   Throwable getThrowable() {
      return _throwable ;
   }

   //**********
   // Class attributes and constants
   //**********

   private Date      _date ;
   private Throwable _throwable ;

}


// End of ExceptionLogEntry.java
