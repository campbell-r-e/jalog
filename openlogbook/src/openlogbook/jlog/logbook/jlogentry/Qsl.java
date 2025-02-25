package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;
import openlogbook.jlog.util.QslRequest;

import java.util.Date;


/**
 * An interface that defines the QSL API.  This class defines :
 * 
 * QSLRDATE
 * QSLSDATE
 * QSL_RCVD
 * QSL_SENT
 * QSLMSG 
 * QSL_VIA
 * 
 * @author KC0ZPS
 */
public interface Qsl extends Adif {

   /**
    * Has a QSL been sent.  QslRequest.Blank if not used.
    * 
    * @return true if a QSL has been sent.
    */
   public QslRequest isQslSent() ;
   
   /**
    * Sets the QSL sent field.  QslRequest.Blank if not used.
    * 
    * @param qslRequest the QSL sent value.
    */
   public void setQslSent(QslRequest qslRequest);

   /**
    * Gets the unknown QSL Sent value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @return the unknown QSL Sent value.
    */
   public String getUnknownQslSent() ;
   
   /**
    * Sets the unknown QSL Sent value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @param unknownQslSent the unknown QSL Sent value.
    */
   public void setUnknownQslSent(String unknownQslSent) ;
   
   /**
    * Gets the date the QSL was sent.  null if not used.
    * 
    * @return the date the QSL was sent. 
    */
   public Date getQslSentDate() ;
   
   /**
    * Sets the date that the QSL was sent.  null if not used.
    * 
    * @param date the date that the QSL was sent. 
    */
   public void setQslSentDate(Date date) ;
   
   /**
    * Checks if a QSL was received.  QslRequest.Blank if not used.
    * 
    * @return true if a QSL was received.
    */
   public QslRequest isQslReceived() ;
   
   /**
    * Sets the QSL received field.  QslRequest.Blank if not used.
    * 
    * @param qslRequest the QSL received value.
    */
   public void setQslReceived(QslRequest qslRequest) ;

   /**
    * Gets the unknown QSL Received value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @return the unknown QSL Received value.
    */
   public String getUnknownQslReceived() ;
   
   /**
    * Sets the unknown QSL Received value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @param unknownQslReceived the unknown QSL Received value.
    */
   public void setUnknownQslReceived(String unknownQslReceived) ;

   /**
    * Gets the date the QSL was received.  null if not used.
    * 
    * @return the date the QSL was received. 
    */
   public Date getQslReceivedDate() ;
   
   /**
    * Sets the date that the QSL was received.  null if not used.
    * 
    * @param date the date that the QSL was received. 
    */
   public void setQslReceivedDate(Date date) ;
   
   /**
    * Gets a formatted text version of the qsl sent date.
    * 
    * @return a formatted text version of the qsl sent date.
    */
   public String getQslSentDateString() ;
   
   /**
    * Gets a formatted text version of the qsl received date.
    * 
    * @return a formatted text version of the qsl received date.
    */
   public String getQslReceivedDateString() ;
   
   /**
    * Gets the personal message to appear on qsl card.  A "" indicates that this field is not in use.
    * 
    * @return the personal message to appear on qsl card.
    */
   public String getQslMessage() ; 
   
   /**
    * Sets the personal message to appear on qsl card.  A "" indicates that this field is not in use.
    * 
    * @param message the personal message to appear on qsl card.
    */
   public void setQslMessage(String message) ;
   
   /**
    * Gets how the qsl was sent.  A "" indicates that this field is not in use.
    * 
    * @return how the qsl was sent.
    */
   public String getQslVia() ;
   
   /**
    * Sets how the qsl was sent.  A "" indicates that this field is not in use.
    * 
    * @param qslVia how the qsl was sent.
    */
   public void setQslVia(String qslVia) ;
}
