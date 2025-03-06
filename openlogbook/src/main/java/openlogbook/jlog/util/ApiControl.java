package openlogbook.jlog.util;

import openlogbook.debug.Debug;
import openlogbook.debug.SupportManager;
import openlogbook.jlogimpl.debug.DebugManager;

/**
 * The ApiControl singleton provides the root level access to all
 * functionality.
 */
public class ApiControl {

   /**
    * Creates a new ApiControl.
    */
   private ApiControl() {
      _instance = this ;
      if (_version == null) {
         _version = "1.06" ;
      }
      Debug.addObject(null, new SystemDebug()) ;
   }

   //**********
   // Public methods
   //**********
   
   /**
    * Gets the SupportManager.
    *
    * @return the SupportManager
    */
   public static SupportManager getSupportManager() {
      return _supportManager ;
   }


   /**
    * Gets the version of the API implementation.
    *
    * @return the version of the API implementation
    */
   public static String getVersion() {
      return _version ;
   }

   /**
    * Returns an instance of this object.
    * 
    * @return an instance of this object.
    */
   public static ApiControl getInstance() {
      return _instance ;
   }
   
   //**********
   // Private methods
   //**********

   //**********
   // Class attributes and constants
   //**********

   private static ApiControl      _instance = new ApiControl() ;
   private static SupportManager  _supportManager = DebugManager.getInstance() ;
   private static String          _version ;

}


// End of ApiControl.java
