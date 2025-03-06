package openlogbook.jlog.util;

/**
 * An enumeration of time zones. 
 * 
 * @author KC0ZPS
 */
public class TimeZone {
   
   /**
    * Creates a new TimeZone.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private TimeZone(String description) {
       _description = description ;
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Returns all objects represented by this class as an array.
    * 
    * @return all objects represented by this class as an array.
    */
   public static TimeZone[] getValues() {
      return _values ;
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
       return _description ;
   }

   //**********
   // Class attributes and constants
   //**********
   
   private String                    _description ;

   public static final TimeZone UTC   = new TimeZone("UTC") ;
   public static final TimeZone ADT   = new TimeZone("(UTC-3h) ATLANTIC DAYLIGHT SAVING TIME") ;
   public static final TimeZone AST   = new TimeZone("(UTC-4h) ATLANTIC STANDARD TIME") ;
   public static final TimeZone EDT   = new TimeZone("(UTC-4h) EASTERN DAYLIGHT SAVING TIME") ;
   public static final TimeZone EST   = new TimeZone("(UTC-5h) EASTERN STANDARD TIME") ;
   public static final TimeZone CDT   = new TimeZone("(UTC-5h) CENTRAL DAYLIGHT SAVING TIME") ;
   public static final TimeZone CST   = new TimeZone("(UTC-6h) CENTRAL STANDARD TIME") ;
   public static final TimeZone MDT   = new TimeZone("(UTC-6h) MOUNTAIN DAYLIGHT SAVING TIME") ;   
   public static final TimeZone MST   = new TimeZone("(UTC-7h) MOUNTAIN STANDARD TIME") ;
   public static final TimeZone PDT   = new TimeZone("(UTC-7h) PACIFIC DAYLIGHT SAVING TIME") ;
   public static final TimeZone AKDT  = new TimeZone("(UTC-8h) ALASKAN DAYLIGHT SAVING TIME") ;
   public static final TimeZone PST   = new TimeZone("(UTC-8h) PACIFIC STANDARD TIME") ;
   public static final TimeZone AKST  = new TimeZone("(UTC-9h) ALASKAN STANDARD TIME") ;
   public static final TimeZone HST   = new TimeZone("(UTC-10h) HAWAII-ALEUTIAN STANDARD TIME") ;
   
   private static final TimeZone _values[] = {
      UTC,
      ADT,
      AST,
      EDT,
      EST,
      CDT,
      CST,
      MDT,
      MST,
      PDT,
      AKDT,
      PST,
      AKST,
      HST
   } ;

}
