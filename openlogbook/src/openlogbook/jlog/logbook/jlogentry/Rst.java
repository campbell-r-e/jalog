package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines the RST API.
 * 
 * @author KC0ZPS
 */
public interface Rst extends Adif {

   /**
    * Gets the received RST.  A "" indicates that this field is not in use.
    * 
    * @return the received RST.
    */
   public String getRstReceived() ;
   
   /**
    * Sets the received RST.  A "" indicates that this field is not in use.
    * 
    * @param rst the received RST.
    */
   public void setRstReceived(String rst) ;
   
   /**
    * Gets the sent RST.  A "" indicates that this field is not in use.
    * 
    * @return the sent RST.
    */
   public String getRstSent() ;
   
   /**
    * Sets the sent RST.  A "" indicates that this field is not in use.
    * 
    * @param rst the sent RST.
    */
   public void setRstSent(String rst) ;
   
}
