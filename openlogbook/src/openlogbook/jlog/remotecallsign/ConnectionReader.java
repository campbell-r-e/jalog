package openlogbook.jlog.remotecallsign;

/**
 * This class defines the ConnectionReader interface.
 * 
 * @author KC0ZPS
 */
public interface ConnectionReader {

   /**
    * Gets the remote callsign information from the remote site.
    * 
    * @param callsign The callsign to query.
    * 
    * @return the remote callsign information from the remote site.
    * 
    * @throws Exception if something goes wrong.
    */
   public RemoteCallsignInformation get(String callsign) throws Exception ;
   
   /**
    * Returns the data that was originally read from the get() method.
    * 
    * @return the data that was originally read from the get() method.
    */
   public RemoteCallsignInformation getCachedCallsignInformation() ;
   
}
