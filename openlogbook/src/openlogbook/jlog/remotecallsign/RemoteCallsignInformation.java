package openlogbook.jlog.remotecallsign;

/**
 * This class defines the remote callsign information interface.
 * 
 * @author KC0ZPS
 */
public interface RemoteCallsignInformation {

   /**
    * Gets the requested callsign status.
    * 
    * @return the requested callsign status.
    */
   public String getStatus() ;
   
   /**
    * Sets the callsign status.
    * 
    * @param status the callsign status.
    */
   public void setStatus(String status) ;
   
   /**
    * Gets the current callsign.
    * 
    * @return the current callsign.
    */
   public String getCurrentCallsign() ;
   
   /**
    * Sets the current callsign.
    * 
    * @param currentCallsign the current callsign.
    */
   public void setCurrentCallsign(String currentCallsign) ;

   /**
    * Gets the previous callsign, if any.
    * 
    * @return the previous callsign, if any.
    */
   public String getPreviousCallsign() ;
   
   /**
    * Sets the previous callsign, if any.
    * 
    * @param previousCallsign the previous callsign, if any.
    */
   public void setPreviousCallsign(String previousCallsign) ;

   /**
    * Gets the name of the callsign holder.
    * 
    * @return the name of the callsign holder.
    */
   public String getName() ;
   
   /**
    * Sets the name of the callsign holder.
    * 
    * @param name the name of the callsign holder.
    */
   public void setName(String name) ;
   
   /**
    * Gets the first line of the address.
    *  
    * @return the first line of the address.
    */
   public String getAddressLine1() ;
   
   /**
    * Sets the first line of the address.
    * 
    * @param address the first line of the address.
    */
   public void setAddressLine1(String address) ;

   /**
    * Gets the second line of the address.
    * 
    * @return the second line of the address.
    */
   public String getAddressLine2() ;
   
   /**
    * Sets the second line of the address.
    * 
    * @param address the second line of the address.
    */
   public void setAddressLine2(String address) ;

   /**
    * Gets the latitude of the callsign.
    * 
    * @return the latitude of the callsign.
    */
   public String getLatitude() ;
   
   /**
    * Sets the latitude of the callsign.
    * 
    * @param latitude the latitude of the callsign.
    */
   public void setLatitude(String latitude) ;
   
   /**
    * Gets the longitude of the callsign.
    * 
    * @return the longitude of the callsign.
    */
   public String getLongitude() ;
   
   /**
    * Sets the the longitude of the callsign.
    * 
    * @param longitude
    */
   public void setLongitude(String longitude) ;
   
   /**
    * Gets the grant date of the callsign.
    * 
    * @return the grant date of the callsign.
    */
   public String getGrantDate() ;
   
   /**
    * Sets the grant date of the callsign.
    * 
    * @param grantDate the grant date of the callsign.
    */
   public void setGrantDate(String grantDate) ; 
   
   /**
    * Gets the expiration date of the callsign.
    * 
    * @return the expiration date of the callsign.
    */
   public String getExpiryDate() ;
   
   /**
    * Sets the expiration date of the callsign.
    * 
    * @param expiryDate the expiration date of the callsign.
    */
   public void setExpiryDate(String expiryDate) ;
   
   /**
    * Gets the federal registration number.
    * 
    * @return the federal registration number.
    */
   public String getFrn() ;
   
   /**
    * Sets the federal registration number.
    * 
    * @param frn the federal registration number.
    */
   public void setFrn(String frn) ;
   
   /**
    * Gets the station grid square.
    * 
    * @return the station grid square.
    */
   public String getGridSquare() ;
   
   /**
    * Sets the station grid square.
    * 
    * @param gridSquare the station grid square.
    */
   public void setGridSquare(String gridSquare) ;
}
