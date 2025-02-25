package openlogbook.factory;

import openlogbook.jlog.remotecallsign.RemoteCallsignInformation;
import openlogbook.jlogimpl.remotecallsign.RemoteCallsignInformationImpl;

/**
 * A factory class that creates default callsign information objects.
 * 
 * @author KC0ZPS
 */
public class CallsignInformationFactory {

   /**
    * Creates a new CallsignInformationFactory.  The constructor is private.  This object should not be instantiated.
    */
   private CallsignInformationFactory() {}
   
   /**
    * Creates a CallsignInformationImpl.
    * 
    * @return a CallsignInformationImpl.
    */
   public static RemoteCallsignInformation createCallsignInformation() {
      return new RemoteCallsignInformationImpl() ;
   }
   
}
