package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.ContactStationInformation;
import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.util.IotaEnum;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the ContactStationInformationImpl object.
 * 
 * @author KC0ZPS
 */
public class ContactStationInformationImpl implements ContactStationInformation {

   private Iota    _iota ;
   private String  _qth = new String("") ;
   private String  _age = new String("") ;
   private String  _cqZone = new String("") ;
   private String  _dxcc = new String("") ;
   private String  _gridSquare = new String("") ;
   private String  _ituZone = new String("") ;

   /**
    * Creates a new ContactAddressImpl.
    */
   public ContactStationInformationImpl() {
      
   }

   //**********
   // Methods that are inherited from ContactAddress
   //**********

   /**
    * Gets the IOTA information.  null if not used.
    * 
    * @return an IOTA information.
    */
   public Iota getIota() {
      return _iota ;
   }
   
   /**
    * Sets the IOTA information.  null if not used.
    * 
    * @param iota the IOTA information.
    */
   public void setIota(Iota iota) {
      _iota = iota ;
   }
  
   /**
    * Gets the contact QTH.  A "" indicates that this field is not in use.
    * 
    * @return the contact QTH.
    */
   public String getQth() {
      return _qth ;
   }
   
   /**
    * Sets the contact QTH.  A "" indicates that this field is not in use.
    * 
    * @param qth the contact QTH.
    */
   public void setQth(String qth) {
      _qth = qth ;
   }

   /**
    * Gets the contact age.  A "" indicates that this field is not in use.
    * 
    * @return the contact age.
    */
   public String getAge() {
      return _age ;
   }
   
   /**
    * Sets the contact age.  A "" indicates that this field is not in use.
    * 
    * @param age the contact age.
    */
   public void setAge(String age) {
      _age = age ;
   }
   
   /**
    * Gets the CQ Zone.  A "" indicates that this field is not in use.
    * 
    * @return the CQ Zone.
    */
   public String getCqZone() {
      return _cqZone ;
   }
   
   /**
    * Sets the CQ Zone.  A "" indicates that this field is not in use.
    * 
    * @param cqZone the CQ Zone.
    */
   public void setCqZone(String cqZone) {
      _cqZone = cqZone ;
   }
   
   /**
    * Gets the numeric identifiers from ARRL.   A "" indicates that this field is not in use.
    * 
    * @return the numeric identifiers from ARRL.
    */
   public String getDxcc() {
      return _dxcc ;
   }
   
   /**
    * Returns the numeric identifiers from ARRL.  A "" indicates that this field is not in use.
    * 
    * @param dxcc the numeric identifiers from ARRL.
    */
   public void setDxcc(String dxcc) {
      _dxcc = dxcc ;
   }
   
   /**
    * Gets the grid square.  A "" indicates that this field is not in use.
    * 
    * @return the grid square.
    */
   public String getGridSquare() {
      return _gridSquare ;
   }
   
   /**
    * Sets the grid square.  A "" indicates that this field is not in use.
    * 
    * @param gridSquare the the grid square.
    */
   public void setGridSquare(String gridSquare) {
      _gridSquare = gridSquare ;
   }
   
   /**
    * Gets the ITU Zone.  A "" indicates that this field is not in use.
    * 
    * @return the ITU Zone.
    */
   public String getItuZone() {
      return _ituZone ;
   }
   
   /**
    * Sets the ITU Zone.  A "" indicates that this field is not in use.
    * 
    * @param ituZone the ITU Zone.
    */
   public void setItuZone(String ituZone) {
      _ituZone = ituZone ;
   }
   
   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if (_iota != null) {
         if (!_iota.getUnknownIota().equals("")) {
            String iota = _iota.getUnknownIota() + "-" + _iota.getValue() ;
            buffer.append("<" + AdifConstants.IOTA + ":" + iota.length() + ">" + iota) ;         
         } else if(!_iota.getIotaEnum().equals(IotaEnum.Blank) && !_iota.getIotaEnum().equals(IotaEnum.Unknown)) {
            String iotaStr = IotaEnum.getStrToIotaType().getStringValue(_iota.getIotaEnum()) ;
            String iotaValue = new String("" + _iota.getValue()) ;
            String iota = iotaStr + "-" + iotaValue ;
            buffer.append("<" + AdifConstants.IOTA + ":" + iota.length() + ">" + iota) ;         
         }         
      }

      if(!_qth.equals("")) {
         buffer.append("<" + AdifConstants.QTH + ":" + _qth.length() + ">" + _qth) ;
      }

      if(!_age.equals("")) {
         buffer.append("<" + AdifConstants.AGE + ":" + _age.length() + ">" + _age) ;
      }

      if(!_cqZone.equals("")) {
         buffer.append("<" + AdifConstants.CQZ + ":" + _cqZone.length() + ">" + _cqZone) ;
      }

      if(!_dxcc.equals("")) {
         buffer.append("<" + AdifConstants.DXCC + ":" + _dxcc.length() + ">" + _dxcc) ;
      }

      if(!_gridSquare.equals("")) {
         buffer.append("<" + AdifConstants.GRIDSQUARE + ":" + _gridSquare.length() + ">" + _gridSquare) ;
      }

      if(!_ituZone.equals("")) {
         buffer.append("<" + AdifConstants.ITUZ + ":" + _ituZone.length() + ">" + _ituZone) ;
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
      buffer.append(" IOTA: " + _iota + "\n") ;
      buffer.append(" QTH: " + _qth + "\n") ;
      buffer.append(" Age: " + _age + "\n") ;
      buffer.append(" CQ Zone: " + _cqZone + "\n") ;
      buffer.append(" DXCC: " + _dxcc + "\n") ;
      buffer.append(" Gridsquare: " + _gridSquare + "\n") ;
      buffer.append(" ITU Zone: " + _ituZone + "\n") ;
      
      return buffer.toString() ;
   }

}
