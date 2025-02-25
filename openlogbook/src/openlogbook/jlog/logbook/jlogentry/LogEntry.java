package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines a single log entry API.
 * 
 * @author KC0ZPS
 */
public interface LogEntry extends Adif {
   
   /**
    * Returns the callsign information for this log entry.
    * 
    * @return the callsign information for this log entry.
    */
   public Callsign getCallsign() ;
   
   /**
    * Gets the date information.
    * 
    * @return the date information.
    */
   public Era getEra() ;   
     
   /**
    * Gets the RST information.
    * 
    * @return the RST information.
    */
   public Rst getRst() ;
   
   /**
    * Sets the RST information.
    * 
    * @param rst the RST information.
    */
   public void setRst(Rst rst) ;
   
   /**
    * Gets the frequency information.
    * 
    * @return the frequency information.
    */
   public FrequencyInformation getFrequencyInformation() ;
   
   /**
    * Gets the contact address.
    * 
    * @return the contact address.
    */
   public ContactAddress getContactAddress() ;
   
   /**
    * Gets the basic contact station information.
    * 
    * @return the basic contact station information.
    */
   public ContactStationInformation getContactStationInformation() ;
   
   /**
    * Returns the QSL information.
    * 
    * @return the QSL information.
    */
   public Qsl getQsl() ;

   /**
    * Gets the satellite information.
    * 
    * @return the satellite information.
    */
   public Satellite getSatellite() ;

   /**
    * Gets contesting information.
    * 
    * @return the contesting information.
    */
   public Contest getContest() ;
   
   /**
    * Get misc information.
    * 
    * @return misc information.
    */
   public Misc getMisc() ;
   
}
