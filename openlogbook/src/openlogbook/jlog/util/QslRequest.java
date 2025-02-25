package openlogbook.jlog.util;

/**
 * An enumeration of Qsl Request types.
 * 
 * @author KC0ZPS
 */
public class QslRequest {

   /**
    * Creates a new QslRequest.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private QslRequest(String description) {
       _description = description ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Returns the IntToObject object.
    * 
    * @return the IntToObject object.
    */
   public static IntToObject getIntToBandType() {
       return _intToQslRequestType ;
   }

   /**
    * Returns the StringTObject object.
    * 
    * @return the StringTObject object.
    */
   public static StringToObject getStrToQslRequestType() {
      return _strToQslRequestType ;
   }
   
   /**
    * Returns all objects represented by this class as an array.
    * 
    * @return all objects represented by this class as an array.
    */
   public static QslRequest[] getValues() {
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

   public static final QslRequest Blank = new QslRequest("") ;
   public static final QslRequest Unknown = new QslRequest("Unknown") ;
   public static final QslRequest Yes = new QslRequest("Yes") ;
   public static final QslRequest No = new QslRequest("No") ;
   public static final QslRequest Requested = new QslRequest("Requested") ;
   public static final QslRequest Ignore = new QslRequest("Ignore") ;
   
   private static final QslRequest _values[] = {
      Blank,
      Yes,
      No,
      Requested,
      Ignore
   } ;
   
   /**
    * A mapping of the objects to the constants as Integers.
    */
   private static final Object[][] _intlookupData = {
      {new Integer(0), QslRequest.Blank},
      {new Integer(1), QslRequest.Yes},
      {new Integer(2), QslRequest.No},
      {new Integer(3), QslRequest.Requested},
      {new Integer(4), QslRequest.Ignore},
   } ;
   
   /**
    * A mapping of the objects to the constants as Strings.
    */
   private static final Object[][] _strlookupData = {
      {new String(""), QslRequest.Blank},
      {new String("Unknown"), QslRequest.Unknown},
      {new String("Y"), QslRequest.Yes},
      {new String("N"), QslRequest.No},
      {new String("R"), QslRequest.Requested},
      {new String("I"), QslRequest.Ignore}
   } ;
   
   private static final IntToObject    _intToQslRequestType = new IntToObject(_intlookupData, QslRequest.Unknown) ;
   private static final StringToObject _strToQslRequestType = new StringToObject(_strlookupData, QslRequest.Unknown) ;

}
