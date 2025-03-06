package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Rst;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the Rst interface.
 * 
 * @author KC0ZPS
 */
public class RstImpl implements Rst {

   private String _rstReceived = new String() ;
   private String _rstSent = new String() ;
   
   /**
    * Creates a new QslImpl with default values.
    */
   public RstImpl() {
      
   }
   
   /**
    * Creates a new RstImpl with pre-initialized values.
    * 
    * @param rstReceived The recieved RST value.
    * @param rstSent The sent RST value.
    */
   public RstImpl(String rstReceived, String rstSent) {
      _rstReceived = rstReceived ;
      _rstSent = rstSent ;
   }
   
   //**********
   // Methods that are inherited from Rst.
   //**********

   /**
    * Gets the received RST.  A "" indicates that this field is not in use.
    * 
    * @return the received RST.
    */
   public String getRstReceived() {
      return _rstReceived ;
   }
   
   /**
    * Sets the received RST.  A "" indicates that this field is not in use.
    * 
    * @param rst the received RST.
    */
   public void setRstReceived(String rst) {
      _rstReceived = rst ;
   }
   
   /**
    * Gets the sent RST.  A "" indicates that this field is not in use.
    * 
    * @return the sent RST.
    */
   public String getRstSent() {
      return _rstSent ;
   }
   
   /**
    * Sets the sent RST.  A "" indicates that this field is not in use.
    * 
    * @param rst the sent RST.
    */
   public void setRstSent(String rst) {
      _rstSent = rst ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;

      if(!_rstReceived.equals("")) {
         buffer.append("<" + AdifConstants.RST_RCVD + ":" + _rstReceived.length() + ">" + _rstReceived) ;         
      }

      if(!_rstSent.equals("")) {
         buffer.append("<" + AdifConstants.RST_SENT + ":" + _rstSent.length() + ">" + _rstSent) ;         
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
      buffer.append(" RST Received: " + _rstReceived + "\n") ;
      buffer.append(" RST Sent: " + _rstSent + "\n") ;
      
      return buffer.toString() ;
   }

}
