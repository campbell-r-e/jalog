package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Contest;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the Contest interface.
 * 
 * @author KC0ZPS
 */
public class ContestImpl implements Contest {
   
   private String _contestId = new String() ;
   private String _receivedSerialNumber = new String() ;
   private String _transmittedSerialNumber = new String() ;
   
   /**
    * Creates a new ContestImpl.
    */
   public ContestImpl() {
   }

   //**********
   // Methods that are inherited from Contest.
   //**********

   /**
    * Gets the contest id.  A "" indicates that this field is not in use.
    * 
    * @return the contest id.
    */
   public String getContestId() {
      return _contestId ;
   }
   
   /**
    * Sets the contest id.  A "" indicates that this field is not in use.
    * 
    * @param contestId the contest id.
    */
   public void setContestId(String contestId) {
      _contestId = contestId ;
   }
   
   /**
    * Gets the received serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @return the received serial number for a contest QSO.
    */
   public String getReceivedSerialNumber() {
      return _receivedSerialNumber ;
   }
   
   /**
    * Sets the received serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @param receivedSerialNumber the received serial number for a contest QSO.
    */
   public void setReceivedSerialNumber(String receivedSerialNumber) {
      _receivedSerialNumber = receivedSerialNumber ;
   }

   /**
    * Gets the transmitted serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @return the transmitted serial number for a contest QSO.
    */
   public String getTransmittedSerialNumber() {
      return _transmittedSerialNumber ;
   }
   
   /**
    * Sets the transmitted serial number for a contest QSO.  A "" indicates that this field is not in use.
    * 
    * @param transmittedSerialNumber the transmitted serial number for a contest QSO.
    */
   public void setTransmittedSerialNumber(String transmittedSerialNumber) {
      _transmittedSerialNumber = transmittedSerialNumber ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if(!_contestId.equals("")) {
         buffer.append("<" + AdifConstants.CONTEST_ID + ":" + _contestId.length() + ">" + _contestId) ;         
      }
      
      if(!_receivedSerialNumber.equals("")) {
         buffer.append("<" + AdifConstants.SRX + ":" + _receivedSerialNumber.length() + ">" + _receivedSerialNumber) ;         
      }

      if(!_transmittedSerialNumber.equals("")) {
         buffer.append("<" + AdifConstants.STX + ":" + _transmittedSerialNumber.length() + ">" + _transmittedSerialNumber) ;
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
      buffer.append(" Contest ID: " + _contestId + "\n") ;
      buffer.append(" Received Serial Number: " + _receivedSerialNumber + "\n") ;
      buffer.append(" Transmitted Serial Number: " + _transmittedSerialNumber + "\n") ;
      
      return buffer.toString() ;
   }

}
