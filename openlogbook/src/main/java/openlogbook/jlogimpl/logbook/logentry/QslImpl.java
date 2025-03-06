package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Qsl;
import openlogbook.jlog.util.QslRequest;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;
import openlogbook.util.Utility;

import java.util.Date;



/**
 * An implementation of the Qsl interface.
 * 
 * @author KC0ZPS
 */
public class QslImpl implements Qsl {
   
   private QslRequest _isQslSent = QslRequest.Blank ;
   private String     _unknownQslSent = new String("") ;
   private QslRequest _isQslReceived = QslRequest.Blank;
   private String     _unknownQslReceived = new String("") ;
   private Date       _qslSentDate ;
   private Date       _qslReceivedDate ;
   private String     _qslMessage = new String("") ;
   private String     _qslVia = new String("") ;
   
   /**
    * Creates a new QslImpl.
    */
   public QslImpl() {
      
   }

   //**********
   // Methods that are inherited from Qsl.
   //**********

   /**
    * Has a QSL been sent.  QslRequest.Blank if not used.
    * 
    * @return true if a QSL has been sent.
    */
   public QslRequest isQslSent() {
      return _isQslSent ;
   }
   
   /**
    * Sets the QSL sent field.  QslRequest.Blank if not used.
    * 
    * @param value the QSL sent value.
    */
   public void setQslSent(QslRequest value) {
      _isQslSent = value ;
   }
   
   /**
    * Gets the unknown QSL Sent value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @return the unknown QSL Sent value.
    */
   public String getUnknownQslSent() {
      return _unknownQslSent ;
   }
   
   /**
    * Sets the unknown QSL Sent value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @param unknownQslSent the unknown QSL Sent value.
    */
   public void setUnknownQslSent(String unknownQslSent) {
      _unknownQslSent = unknownQslSent ;
   }

   /**
    * Gets the date the QSL was sent.  null if not used.
    * 
    * @return the date the QSL was sent. 
    */
   public Date getQslSentDate() {
      return _qslSentDate ;
   }
   
   /**
    * Sets the date that the QSL was sent.  null if not used.
    * 
    * @param date the date that the QSL was sent. 
    */
   public void setQslSentDate(Date date) {
      _qslSentDate = date ;
   }
   
   /**
    * Checks if a QSL was received.  QslRequest.Blank if not used.
    * 
    * @return true if a QSL was received.
    */
   public QslRequest isQslReceived() {
      return _isQslReceived ;
   }
   
   /**
    * Sets the QSL received field.  QslRequest.Blank if not used.
    * 
    * @param value the QSL received value.
    */
   public void setQslReceived(QslRequest value) {
      _isQslReceived = value ;
   }
   
   /**
    * Gets the unknown QSL Received value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @return the unknown QSL Received value.
    */
   public String getUnknownQslReceived() {
      return _unknownQslReceived ;
   }
   
   /**
    * Sets the unknown QSL Received value.  This is used if the file enumeration does not match the QslRequest enumeration.
    * 
    * @param unknownQslReceived the unknown QSL Received value.
    */
   public void setUnknownQslReceived(String unknownQslReceived) {
      _unknownQslReceived = unknownQslReceived ;
   }

   /**
    * Gets the date the QSL was received.  null if not used.
    * 
    * @return the date the QSL was received. 
    */
   public Date getQslReceivedDate() {
      return _qslReceivedDate ;
   }

   /**
    * Sets the date that the QSL was received.  null if not used.
    * 
    * @param date the date that the QSL was received. 
    */
   public void setQslReceivedDate(Date date) {
      _qslReceivedDate = date ;
   }
   
   /**
    * Gets a formatted text version of the qsl sent date.
    * 
    * @return a formatted text version of the qsl sent date.
    */
   public String getQslSentDateString() {
      if (_qslSentDate == null) {
         return "" ;
      }
      return Utility.getDateFormat().format(_qslSentDate) ;
   }
   
   /**
    * Gets a formatted text version of the qsl received date.
    * 
    * @return a formatted text version of the qsl received date.
    */
   public String getQslReceivedDateString() {
      if (_qslReceivedDate == null) {
         return "" ;
      }
      return Utility.getDateFormat().format(_qslReceivedDate) ;
   }

   /**
    * Gets the personal message to appear on qsl card.  A "" indicates that this field is not in use.
    * 
    * @return the personal message to appear on qsl card.
    */
   public String getQslMessage() {
      return _qslMessage ;
   }
   
   /**
    * Sets the personal message to appear on qsl card.  A "" indicates that this field is not in use.
    * 
    * @param message the personal message to appear on qsl card.
    */
   public void setQslMessage(String message) {
      _qslMessage = message ;
   }
   
   /**
    * Gets how the qsl was sent.  A "" indicates that this field is not in use.
    * 
    * @return how the qsl was sent.
    */
   public String getQslVia() {
      return _qslVia ;
   }
   
   /**
    * Sets how the qsl was sent.  A "" indicates that this field is not in use.
    * 
    * @param qslVia how the qsl was sent.
    */
   public void setQslVia(String qslVia) {
      _qslVia = qslVia ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if (!_unknownQslSent.equals("")) {
         buffer.append("<" + AdifConstants.QSL_SENT + ":" + _unknownQslSent.length() + ">" + _unknownQslSent) ;
      } else {
         if (!_isQslSent.equals(QslRequest.Blank)) {
            String qslSent = QslRequest.getStrToQslRequestType().getStringValue(_isQslSent) ;
            buffer.append("<" + AdifConstants.QSL_SENT + ":" + qslSent.length() + ">" + qslSent) ;         
         }         
      }

      if (!_unknownQslReceived.equals("")) {
         buffer.append("<" + AdifConstants.QSL_RCVD + ":" + _unknownQslReceived.length() + ">" + _unknownQslReceived) ;
      } else {
         if (!_isQslReceived.equals(QslRequest.Blank)) {
            String qslReceived = QslRequest.getStrToQslRequestType().getStringValue(_isQslReceived) ;
            buffer.append("<" + AdifConstants.QSL_RCVD + ":" + qslReceived.length() + ">" + qslReceived) ;         
         }         
      }

      if (_qslSentDate != null) {
         String date = Utility.getAdifDateFormat().format(_qslSentDate) ;
         buffer.append("<" + AdifConstants.QSLSDATE + ":" + date.length() + ">" + date) ;
      }

      if (_qslReceivedDate != null) {
         String date = Utility.getAdifDateFormat().format(_qslReceivedDate) ;
         buffer.append("<" + AdifConstants.QSLRDATE + ":" + date.length() + ">" + date) ;
      }

      if(!_qslMessage.equals("")) {
         buffer.append("<" + AdifConstants.QSLMSG + ":" + _qslMessage.length() + ">" + _qslMessage) ;         
      }

      if(!_qslVia.equals("")) {
         buffer.append("<" + AdifConstants.QSL_VIA + ":" + _qslVia.length() + ">" + _qslVia) ;         
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
      buffer.append(" QSL Sent: " + _isQslSent + "\n") ;
      buffer.append(" Unknown QSL Sent: " + _unknownQslSent + "\n") ;
      buffer.append(" QSL Received: " + _isQslReceived + "\n") ;
      buffer.append(" Unknown QSL Received: " + _unknownQslReceived + "\n") ;
      buffer.append(" QSL Sent Date: " + _qslSentDate + "\n") ;
      buffer.append(" QSL Received Date: " + _qslReceivedDate + "\n") ;
      buffer.append(" QSL Message: " + _qslMessage + "\n") ;
      buffer.append(" QSL Via: " + _qslVia + "\n") ;
      
      return buffer.toString() ;
   }

}
