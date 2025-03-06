package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines callsign information.  This class currently holds :
 * 
 * CALL       
 * OPERATOR
 * 
 * @author KC0ZPS
 */
public interface Callsign extends Adif {
   
   /**
    * Gets the contact station.  A "" indicates that this field is not in use.
    * 
    * @return the contact station.
    */
   public String getContactStation() ;
   
   /**
    * Sets the contact station.  A "" indicates that this field is not in use.
    * 
    * @param station the contact station.
    */
   public void setContactStation(String station) ;

   /**
    * Gets the operating station.  A "" indicates that this field is not in use.
    * 
    * @return the operating station.
    */
   public String getOperatingStation() ;
   
   /**
    * Sets the operating station.  A "" indicates that this field is not in use.
    * 
    * @param station the operating station.
    */
   public void setOperatingStation(String station) ;

}
