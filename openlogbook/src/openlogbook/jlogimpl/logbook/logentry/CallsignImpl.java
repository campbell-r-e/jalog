package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Callsign;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation  of the Callsign object.
 * 
 * @author KC0ZPS
 */
public class CallsignImpl implements Callsign {

   private String _contactStation = new String("") ;
   private String _operatingStation = new String("") ;

   /**
    * Creates a new CallsignImpl.
    */
   public CallsignImpl() {
   }

   //**********
   // Methods that are inherited from Callsign
   //**********

   /**
    * Gets the contact station.  A "" indicates that this field is not in use.
    * 
    * @return the contact station.
    */
   public String getContactStation() {
      return _contactStation ;
   }
   
   /**
    * Sets the contact station.  A "" indicates that this field is not in use.
    * 
    * @param station the contact station.
    */
   public void setContactStation(String station) {
      _contactStation = station ;
   }

   /**
    * Gets the operating station.  A "" indicates that this field is not in use.
    * 
    * @return the operating station.
    */
   public String getOperatingStation() {
      return _operatingStation ;
   }
   
   /**
    * Sets the operating station.  A "" indicates that this field is not in use.
    * 
    * @param station the operating station.
    */
   public void setOperatingStation(String station) {
      _operatingStation = station ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if(!_contactStation.equals("")) {
         buffer.append("<" + AdifConstants.CALL + ":" + _contactStation.length() + ">" + _contactStation) ;         
      }
      if(!_operatingStation.equals("")) {
         buffer.append("<" + AdifConstants.OPERATOR + ":" + _operatingStation.length() + ">" + _operatingStation) ;
      }

      return buffer.toString() ;
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
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append(getClass() + ":\n") ;
      buffer.append(" Contact Station: " + _contactStation + "\n") ;
      buffer.append(" Operating Station: " + _operatingStation + "\n") ;
      
      return buffer.toString() ;
   }
}
