package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines satellite related information.  This class defines :
 * 
 * PROP_MODE
 * SAT_MODE
 * SAT_NAME
 * 
 * @author KC0ZPS
 */
public interface Satellite extends Adif {
   
   /**
    * Gets the prop mode.  A "" indicates that this field is not in use.
    * 
    * @return the prop mode.
    */
   public String getPropMode() ;
   
   /**
    * Sets the prop mode.  A "" indicates that this field is not in use.
    * 
    * @param propMode the prop mode.
    */
   public void setPropMode(String propMode) ;
   
   /**
    * Gets the satellite mode.  A "" indicates that this field is not in use.
    * 
    * @return the satellite mode.
    */
   public String getSatelliteMode() ;
   
   /**
    * Sets the satellite mode.  A "" indicates that this field is not in use.
    * 
    * @param satelliteMode the satellite mode.
    */
   public void setSatelliteMode(String satelliteMode) ;
   
   /**
    * Gets the satellite name.  A "" indicates that this field is not in use.
    * 
    * @return the satellite name.
    */
   public String getSatelliteName() ;
   
   /**
    * Sets the satellite name.  A "" indicates that this field is not in use.
    *  
    * @param satelliteName the satellite name.
    */
   public void setSatelliteName(String satelliteName) ;
   
}
