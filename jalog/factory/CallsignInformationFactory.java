package jalog.factory;

import jalog.jlog.remotecallsign.RemoteCallsignInformation;
import jalog.jlogimpl.remotecallsign.RemoteCallsignInformationImpl;

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
