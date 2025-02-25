package openlogbook.jlogimpl.filehandler.xmlfilehandler;

/**
 * This class is an enumeration object that represents all of the XML node keywords 
 * that can be saved to an XML file.
 * 
 * @author KC0ZPS
 */
public class XmlConstants {

   /**
    * Creates a new XmlConstants.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private XmlConstants(String description) {
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
   // Constants at the LogBook level.  If this gets large, move to another class.
   //**********
   
   public static final XmlConstants Version         = new XmlConstants("Version") ;

   //**********
   // Constants at the LogEntry level.
   //**********
   
   public static final XmlConstants Station         = new XmlConstants("Station") ;
   public static final XmlConstants StartDate       = new XmlConstants("StartDate") ;
   public static final XmlConstants EndDate         = new XmlConstants("EndDate") ;
   public static final XmlConstants SameAsStart     = new XmlConstants("SameAsStart") ;
   public static final XmlConstants Mode            = new XmlConstants("Mode") ;
   public static final XmlConstants Band            = new XmlConstants("Band") ;
   public static final XmlConstants Frequency       = new XmlConstants("Frequency") ;
   public static final XmlConstants Power           = new XmlConstants("Power") ;
   public static final XmlConstants Locator         = new XmlConstants("Locator") ;
   public static final XmlConstants Comment         = new XmlConstants("Comment") ;
   public static final XmlConstants RstReceived     = new XmlConstants("RstReceived") ;
   public static final XmlConstants RstSent         = new XmlConstants("RstSent") ;
   public static final XmlConstants County          = new XmlConstants("County") ;
   public static final XmlConstants Name            = new XmlConstants("Name") ;
   public static final XmlConstants Address         = new XmlConstants("Address") ;
   public static final XmlConstants QTH             = new XmlConstants("QTH") ;
   public static final XmlConstants IOTA1           = new XmlConstants("IOTA1") ;
   public static final XmlConstants IOTA2           = new XmlConstants("IOTA2") ;
   public static final XmlConstants QslSent         = new XmlConstants("QslSent") ;
   public static final XmlConstants QslReceived     = new XmlConstants("QslReceived") ;
   public static final XmlConstants QslSentDate     = new XmlConstants("QslSentDate") ;
   public static final XmlConstants QslReceivedDate = new XmlConstants("QslReceivedDate") ;
   
   
}
