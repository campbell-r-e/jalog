package openlogbook.debug;

/**
 * An InternalEventListener receives notification of all internal events, which can then
 * be logged or forwarded appropriately.
 */
public interface InternalEventListener {

   /**
    * Called anytime an internal event occurs.
    *
    * @param internalEvent  an InternalEvent to be logged or otherwise processed
    *
    */
   public void handleInternalEvent(InternalEvent internalEvent) ;


}


// End of InternalEventListener.java
