package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Satellite;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the Satellite interface.
 * 
 * @author KC0ZPS
 */
public class SatelliteImpl implements Satellite {

   private String _propMode = new String() ;
   private String _satelliteMode = new String() ;
   private String _satelliteName = new String() ;
   
   /**
    * Creates a new SatelliteImpl.
    */
   public SatelliteImpl() {
      
   }

   //**********
   // Methods that are inherited from Satellite.
   //**********

   /**
    * Gets the prop mode.  A "" indicates that this field is not in use.
    * 
    * @return the prop mode.
    */
   public String getPropMode() {
      return _propMode ;
   }
   
   /**
    * Sets the prop mode.  A "" indicates that this field is not in use.
    * 
    * @param propMode the prop mode.
    */
   public void setPropMode(String propMode) {
      _propMode = propMode ;
   }
   
   /**
    * Gets the satellite mode.  A "" indicates that this field is not in use.
    * 
    * @return the satellite mode.
    */
   public String getSatelliteMode() {
      return _satelliteMode ;
   }
   
   /**
    * Sets the satellite mode.  A "" indicates that this field is not in use.
    * 
    * @param satelliteMode the satellite mode.
    */
   public void setSatelliteMode(String satelliteMode) {
      _satelliteMode = satelliteMode ;
   }
   
   /**
    * Gets the satellite name.  A "" indicates that this field is not in use.
    * 
    * @return the satellite name.
    */
   public String getSatelliteName() {
      return _satelliteName ;
   }
   
   /**
    * Sets the satellite name.  A "" indicates that this field is not in use.
    *  
    * @param satelliteName the satellite name.
    */
   public void setSatelliteName(String satelliteName) {
      _satelliteName = satelliteName ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if(!_propMode.equals("")) {
         buffer.append("<" + AdifConstants.PROP_MODE + ":" + _propMode.length() + ">" + _propMode) ;         
      }

      if(!_satelliteMode.equals("")) {
         buffer.append("<" + AdifConstants.SAT_MODE + ":" + _satelliteMode.length() + ">" + _satelliteMode) ;         
      }

      if(!_satelliteName.equals("")) {
         buffer.append("<" + AdifConstants.SAT_NAME + ":" + _satelliteName.length() + ">" + _satelliteName) ;         
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
      buffer.append(" Prop Mode: " + _propMode + "\n") ;
      buffer.append(" Satellite Mode: " + _satelliteMode + "\n") ;
      buffer.append(" Satellite Name: " + _satelliteName + "\n") ;
      
      return buffer.toString() ;
   }

}
