package openlogbook.jlogimpl.filehandler.adif;

/**
 * This class is an enumeration object that represents all of the ADIF field names.
 * 
 * @author KC0ZPS
 */
public class AdifConstants {

   /**
    * Creates a new AdifConstants.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private AdifConstants(String description) {
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

   /**
    * Callsign of the contact.
    */
   public static final AdifConstants CALL       = new AdifConstants("CALL") ;
   
   /**
    * Callsign of person logging the QSO
    */
   public static final AdifConstants OPERATOR   = new AdifConstants("OPERATOR") ;
   
   /**
    * Name of the contact.
    */
   public static final AdifConstants NAME       = new AdifConstants("NAME") ;
   
   /**
    * As it will appear on the mailing label
    */
   public static final AdifConstants ADDRESS    = new AdifConstants("ADDRESS") ;
   
   /**
    * US County in the format STATE,COUNTY. For example GA,BARROW. Use CQ County list
    */
   public static final AdifConstants CNTY       = new AdifConstants("CNTY") ;
   
   /**
    * Continent: NA,SA,EU,AF,OC,AS
    */
   public static final AdifConstants CONT       = new AdifConstants("CONT") ;
   
   /**
    * US state
    */
   public static final AdifConstants STATE      = new AdifConstants("STATE") ;
   
   /**
    * QTH
    */
   public static final AdifConstants QTH        = new AdifConstants("QTH") ;
   
   /**
    * Age of the contact.
    */
   public static final AdifConstants AGE        = new AdifConstants("AGE") ;
   
   /**
    * CQ Zone
    */
   public static final AdifConstants CQZ        = new AdifConstants("CQZ") ;
   
   /**
    * Numeric identifiers from ARRL.  See table for values.
    */
   public static final AdifConstants DXCC       = new AdifConstants("DXCC") ;
   
   /**
    * (4, 6, or 8) or however many characters
    */
   public static final AdifConstants GRIDSQUARE = new AdifConstants("GRIDSQUARE") ;
   
   /**
    * ITU Zone
    */
   public static final AdifConstants ITUZ       = new AdifConstants("ITUZ") ;
   
   /**
    * YYYYMMDD in UTC
    */
   public static final AdifConstants QSO_DATE   = new AdifConstants("QSO_DATE") ;
   
   /**
    * HHMM or HHMMSS in UTC
    */
   public static final AdifConstants TIME_OFF   = new AdifConstants("TIME_OFF") ;
   
   /**
    * HHMM or HHMMSS in UTC
    */
   public static final AdifConstants TIME_ON    = new AdifConstants("TIME_ON") ;
   
   /**
    * SSB, CW, RTTY, TOR=AMTOR, PKT, AM, FM, SSTV, ATV, PAC=PACTOR,CLO=CLOVER
    */
   public static final AdifConstants MODE       = new AdifConstants("MODE") ;
   
   /**
    * 160M, 80M, 40M, 30M, 20M, 17M, 15M, 12M, 10M, 6M, 2M, 70CM,23CM...see table.
    */
   public static final AdifConstants BAND       = new AdifConstants("BAND") ;
   
   /**
    * in Megahertz
    */
   public static final AdifConstants FREQ       = new AdifConstants("FREQ") ;
   
   /**
    * Power of this station in watts
    */
   public static final AdifConstants TX_PWR     = new AdifConstants("TX_PWR") ;
   
   /**
    * Power of other station in Watts
    */
   public static final AdifConstants RX_PWR     = new AdifConstants("RX_PWR") ;
   
   /**
    * HYPHEN MUST BE INCLUDED. Example: NA-001 IOTA PROVIDES DISK IN THIS FORMAT
    */
   public static final AdifConstants IOTA       = new AdifConstants("IOTA") ;
   
   /**
    * QSL Rcvd Date
    */
   public static final AdifConstants QSLRDATE   = new AdifConstants("QSLRDATE") ;
   
   /**
    * QSL Sent Date
    */
   public static final AdifConstants QSLSDATE   = new AdifConstants("QSLSDATE") ;
   
   /**
    * Y=Yes, N=No, R=Requested, I=Ignore or Invalid
    */
   public static final AdifConstants QSL_RCVD   = new AdifConstants("QSL_RCVD") ;
   
   /**
    * Y=Yes, N=No, R=Requested, I=Ignore or Invalid
    */
   public static final AdifConstants QSL_SENT   = new AdifConstants("QSL_SENT") ;
   
   /**
    * Personal message to appear on qsl card
    */
   public static final AdifConstants QSLMSG     = new AdifConstants("QSLMSG") ;
   
   /**
    * How was the qsl sent.
    */
   public static final AdifConstants QSL_VIA    = new AdifConstants("QSL_VIA") ;
   
   /**
    * Sent rst.
    */
   public static final AdifConstants RST_RCVD   = new AdifConstants("RST_RCVD") ;
   
   /**
    * Received rst.
    */
   public static final AdifConstants RST_SENT   = new AdifConstants("RST_SENT") ;
   
   /**
    * Comment field for QSO
    */
   public static final AdifConstants COMMENT    = new AdifConstants("COMMENT") ;
   
   /**
    * ARRL Section
    */
   public static final AdifConstants ARRL_SECT  = new AdifConstants("ARRL_SECT") ;
   
   /**
    * Contest Indentifier -- SS, ARRLVHF, ARRLDX, etc.
    */
   public static final AdifConstants CONTEST_ID = new AdifConstants("CONTEST_ID") ;
   
   /**
    * Long text for digital copy, third party traffic, etc.
    */
   public static final AdifConstants NOTES      = new AdifConstants("NOTES") ;
   
   /**
    * WPX prefix
    */
   public static final AdifConstants PFX        = new AdifConstants("PFX") ;
   
   /**
    * Ten-Ten Number
    */
   public static final AdifConstants TEN_TEN    = new AdifConstants("TEN_TEN") ;
   
   /**
    * 2-letter abbreviations: AB, BC, MB, NB, NF, NS, NT, ON, PE, QC, SK, YT
    */
   public static final AdifConstants VE_PROV    = new AdifConstants("VE_PROV") ;
   
   /**
    * Prop mode
    */
   public static final AdifConstants PROP_MODE  = new AdifConstants("PROP_MODE") ;
   
   /**
    * Satellite Mode
    */
   public static final AdifConstants SAT_MODE   = new AdifConstants("SAT_MODE") ;
   
   /**
    * Name of satellite
    */
   public static final AdifConstants SAT_NAME   = new AdifConstants("SAT_NAME") ;
   
   /**
    * Received serial number for a contest QSO
    */
   public static final AdifConstants SRX        = new AdifConstants("SRX") ;
   
   /**
    * Transmitted serial number for a contest QSO
    */
   public static final AdifConstants STX        = new AdifConstants("STX") ;

}
