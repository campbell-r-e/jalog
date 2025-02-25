package openlogbook.jlogimpl.remotecallsign.callookreader;

public class CallookXmlConstants {

   /**
    * Creates a new CallLookXmlConstants.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private CallookXmlConstants(String description) {
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
       return _description ;
   }

   //**********
   // Class attributes and constants
   //**********
   
   private String                    _description ;

   //**********
   // Constants
   //**********
   
   /**
    * Node Names
    */
   public static final CallookXmlConstants Root           = new CallookXmlConstants("callook") ;
   public static final CallookXmlConstants Status         = new CallookXmlConstants("status") ;
   public static final CallookXmlConstants Callsign       = new CallookXmlConstants("callsign") ;
   public static final CallookXmlConstants Name           = new CallookXmlConstants("name") ;
   public static final CallookXmlConstants Address        = new CallookXmlConstants("address") ;
   public static final CallookXmlConstants AddressLine1   = new CallookXmlConstants("line1") ;
   public static final CallookXmlConstants AddressLine2   = new CallookXmlConstants("line2") ;
   public static final CallookXmlConstants Latitude       = new CallookXmlConstants("latitude") ;
   public static final CallookXmlConstants Longitude      = new CallookXmlConstants("longitude") ;
   public static final CallookXmlConstants GrantDate      = new CallookXmlConstants("grantdate") ;
   public static final CallookXmlConstants ExpiryDate     = new CallookXmlConstants("expirydate") ;
   public static final CallookXmlConstants Frn            = new CallookXmlConstants("frn") ;
   public static final CallookXmlConstants CallsignStatus = new CallookXmlConstants("status") ;
   public static final CallookXmlConstants GridSquare     = new CallookXmlConstants("gridsquare") ;

   /**
    * Attribute Names
    */
   public static final CallookXmlConstants CurrentCallsignValue = new CallookXmlConstants("current") ;
   public static final CallookXmlConstants PreviousCallsignValue = new CallookXmlConstants("previous") ;   

   /**
    * Nodes that don't have data or are parent nodes.
    */
   public static final CallookXmlConstants LastActionDate = new CallookXmlConstants("lastactiondate") ;
   public static final CallookXmlConstants Attn           = new CallookXmlConstants("attn") ;
   public static final CallookXmlConstants Trustee        = new CallookXmlConstants("trustee") ;
   public static final CallookXmlConstants Callook        = new CallookXmlConstants("callook") ;

}
