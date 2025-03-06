package openlogbook.jlog.util;

/**
 * An enumeration of IOTA objects.
 * 
 * @author KC0ZPS
 */
public class IotaEnum {

   /**
    * Creates a new IotaEnum.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private IotaEnum(String description) {
       _description = description ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Returns the StringToObject object.
    * 
    * @return the StringToObject object.
    */
   public static StringToObject getStrToIotaType() {
       return _strToIotaType ;
   }

   /**
    * Returns the IntToObject object.
    * 
    * @return the IntToObject object.
    */
   public static IntToObject getIntToIotaType() {
       return _intToIotaType ;
   }   
   
   /**
    * Returns all objects represented by this class as an array.
    * 
    * @return all objects represented by this class as an array.
    */
   public static IotaEnum[] getValues() {
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

   public static final IotaEnum Blank   = new IotaEnum("  ") ;
   public static final IotaEnum Unknown = new IotaEnum("Unknown") ;
   public static final IotaEnum AF      = new IotaEnum("AF") ;
   public static final IotaEnum AN      = new IotaEnum("AN") ;
   public static final IotaEnum AS      = new IotaEnum("AS") ;
   public static final IotaEnum OC      = new IotaEnum("OC") ;
   public static final IotaEnum EU      = new IotaEnum("EU") ;
   public static final IotaEnum NA      = new IotaEnum("NA") ;
   public static final IotaEnum SA      = new IotaEnum("SA") ;

   private static final IotaEnum _values[] = {
      Blank,
      AF,
      AN,
      AS,
      OC,
      EU,
      NA,
      SA
   } ;
   
   /**
    * A mapping of the objects to the constants as Integers.
    */
   private static final Object[][] _intlookupData = {
      {new Integer(0), IotaEnum.Blank},      
      {new Integer(1), IotaEnum.AF},      
      {new Integer(2), IotaEnum.AN},      
      {new Integer(3), IotaEnum.AS},      
      {new Integer(4), IotaEnum.OC},      
      {new Integer(5), IotaEnum.EU},      
      {new Integer(6), IotaEnum.NA},      
      {new Integer(7), IotaEnum.SA},      
   } ;

   /**
    * A mapping of the objects to the constants as Strings.
    */
   private static final Object[][] _strlookupData = {
      {new String(""), IotaEnum.Blank},
      {new String("Unknown"), IotaEnum.Unknown},
      {new String("AF"), IotaEnum.AF},      
      {new String("AN"), IotaEnum.AN},      
      {new String("AS"), IotaEnum.AS},      
      {new String("OC"), IotaEnum.OC},      
      {new String("EU"), IotaEnum.EU},      
      {new String("NA"), IotaEnum.NA},      
      {new String("SA"), IotaEnum.SA}      
   } ;

   private static final IntToObject    _intToIotaType = new IntToObject(_intlookupData, IotaEnum.Unknown) ;
   private static final StringToObject _strToIotaType = new StringToObject(_strlookupData, IotaEnum.Unknown) ;

}
