package openlogbook.debug;

/**
 * An CallbackExceptionListener receives notification when an exception occurs during
 * a callback (Eg, a listener interface) to client application code.
 */
public interface CallbackExceptionListener {

   /**
    * Called anytime an exception occurs while calling back into client application code.
    *
    * @param throwable  the exception object
    *
    */
   public void handleCallbackException(Throwable throwable) ;


}


// End of CallbackExceptionListener.java
