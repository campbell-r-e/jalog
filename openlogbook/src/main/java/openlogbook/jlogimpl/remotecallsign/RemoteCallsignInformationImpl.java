package openlogbook.jlogimpl.remotecallsign;

import openlogbook.jlog.remotecallsign.RemoteCallsignInformation;

/**
 * An implementation of the RemoteCallsignInformation object.
 * This class should never return a null for any of its fields.  The current design will always
 * return some default value.  For example, a String will return "" if it hasn't been defined.
 * 
 * @author KC0ZPS
 */
public class RemoteCallsignInformationImpl implements RemoteCallsignInformation {

   private String _status = new String() ;
   private String _currentCallsign = new String() ;
   private String _previousCallsign = new String() ;   
   private String _name = new String() ;
   private String _address1 = new String() ;
   private String _address2 = new String() ;
   private String _latitude = new String() ;
   private String _longitude = new String() ;
   private String _grantDate = new String() ;
   private String _expiryDate = new String() ;
   private String _frn = new String() ;
   private String _gridSquare = new String() ;

   /**
    * Creates a new RemoteCallsignInformationImpl.
    */
   public RemoteCallsignInformationImpl() {
   }
   
   //**********
   // Methods that are inherited from RemoteCallsignInformation
   //**********

   public String getStatus() {
      return _status ;
   }
   
   public void setStatus(String status) {
      _status = status ;
   }
   
   public String getCurrentCallsign() {
      return _currentCallsign ;
   }
   
   public void setCurrentCallsign(String currentCallsign) {
      _currentCallsign = currentCallsign ;
   }
   
   public String getPreviousCallsign() {
      return _previousCallsign ; 
   }
   
   public void setPreviousCallsign(String previousCallsign) {
      _previousCallsign = previousCallsign ;
   }

   public String getName() {
      return _name ;
   }
   
   public void setName(String name) {
      _name = name ;
   }
   
   public String getAddressLine1() {
      return _address1 ;
   }
   
   public void setAddressLine1(String address) {
      _address1 = address ;
   }

   public String getAddressLine2() {
      return _address2 ;
   }
   
   public void setAddressLine2(String address) {
      _address2 = address ;
   }

   public String getLatitude() {
      return _latitude ;
   }
   
   public void setLatitude(String latitude) {
      _latitude = latitude ;
   }
   
   public String getLongitude() {
      return _longitude ;
   }
   
   public void setLongitude(String longitude) {
      _longitude = longitude ;
   }
   
   public String getGrantDate() {
      return _grantDate ;
   }
   
   public void setGrantDate(String grantDate) {
      _grantDate = grantDate ;
   }
   
   public String getExpiryDate() {
      return _expiryDate ;
   }
   
   public void setExpiryDate(String expiryDate) {
      _expiryDate = expiryDate ;
   }
   
   public String getFrn() {
      return _frn ;
   }
   
   public void setFrn(String frn) {
      _frn = frn ;
   }

   public String getGridSquare() {
      return _gridSquare ;
   }
   
   public void setGridSquare(String gridSquare) {
      _gridSquare = gridSquare ;
   }

   //**********
   // Methods overridden from Object
   //**********

   public String toString() {
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append("Status: ").append(_status).append("\n") ;
      buffer.append("Current Callsign: ").append(_currentCallsign).append("\n") ;
      buffer.append("Previous Callsign: ").append(_previousCallsign).append("\n") ;
      buffer.append("Name: ").append(_name).append("\n") ;
      buffer.append("Address Line 1: ").append(_address1).append("\n") ;
      buffer.append("Address Line 2: ").append(_address2).append("\n") ;
      buffer.append("Latitude: ").append(_latitude).append("\n") ;
      buffer.append("Longitude: ").append(_longitude).append("\n") ;
      buffer.append("Grant Date: ").append(_grantDate).append("\n") ;
      buffer.append("Expiry Date: ").append(_expiryDate).append("\n") ;
      buffer.append("FRN: ").append(_frn).append("\n") ;
      buffer.append("Grid Square: ").append(_gridSquare).append("\n") ;
      
      return buffer.toString() ;
   }
   
}
