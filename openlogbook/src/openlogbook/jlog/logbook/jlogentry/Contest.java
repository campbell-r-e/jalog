package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines contest related information.  This class defines :
 * 
 * SRX
 * STX
 * 
 * @author KC0ZPS
 */
public interface Contest extends Adif {

   /**
    * Gets the contest id.  A "" indicates that this field is not in use.
    * 
    * @return the contest id.
    */
   public String getContestId() ;
   
   /**
    * Sets the contest id.  A "" indicates that this field is not in use.
    * 
    * @param contestId the contest id.
    */
   public void setContestId(String contestId) ;
   
   /**
    * Gets the received serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @return the received serial number for a contest QSO.
    */
   public String getReceivedSerialNumber() ;
   
   /**
    * Sets the received serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @param receivedSerialNumber the received serial number for a contest QSO.
    */
   public void setReceivedSerialNumber(String receivedSerialNumber) ;

   /**
    * Gets the transmitted serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @return the transmitted serial number for a contest QSO.
    */
   public String getTransmittedSerialNumber() ;
   
   /**
    * Sets the transmitted serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @param transmittedSerialNumber the transmitted serial number for a contest QSO.
    */
   public void setTransmittedSerialNumber(String transmittedSerialNumber) ;

}
