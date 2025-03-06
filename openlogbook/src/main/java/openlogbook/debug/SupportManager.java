package openlogbook.debug;

import openlogbook.jlog.gui.AbstractLogBookFrame;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Provides access to support-type functionality.
 */
public interface SupportManager {

   /**
    * Adds a listener for exceptions caught during callbacks.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be added
    *
    * @see #removeCallbackExceptionListener
    */
   public void addCallbackExceptionListener(CallbackExceptionListener listener) ;

   /**
    * Removes a listener for exceptions caught during callbacks.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    * If  <code>listener</code> is not registered, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be removed
    *
    * @see #addCallbackExceptionListener
    */
   public void removeCallbackExceptionListener(CallbackExceptionListener listener) ;

   /**
    * Adds a listener for internal events.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be added
    *
    * @see #removeInternalEventListener
    */
   public void addInternalEventListener(InternalEventListener listener) ;

   /**
    * Removes a listener for internal events.
    * If  <code>listener</code> is <code>null</code>, no exception is thrown and
    * no action is performed.
    * If  <code>listener</code> is not registered, no exception is thrown and
    * no action is performed.
    *
    * @param listener  a listener to be removed
    *
    * @see #addInternalEventListener
    */
   public void removeInternalEventListener(InternalEventListener listener) ;

   /**
    * Saves internal state to a file. This file can then be sent back to the API engineering
    * team for analysis.
    * The resulting file is for engineering use only and should not be directly exposed to
    * end users, field support staff, and so on.
    *
    * @param debugFile  specifies a file into which debugging information will be placed
    *
    * @throws IOException if an I/O error occurs writing to the file.
    */
   public void gatherDebugData(File debugFile) throws IOException ;

   /**
    * Saves internal API state to a file. This file can then be sent back to the API engineering
    * team for analysis. If the managedProduct parameter is not null, the public API for the
    * managed product will be walked through and the results will also be included in the file.
    * The resulting file is for engineering use only and should not be directly exposed to
    * end users, field support staff, and so on.
    *
    * @param logBookFrame    specifies the Log Book Frame of specific interest. This parameter
    *                        may be null, in which case this method is identical to
    *                        gatherDebugData(File).
    * @param debugFile       specifies a file into which debugging information will be placed
    *
    * @throws IOException if an I/O error occurs writing to the file.
    */
   public void gatherDebugData(AbstractLogBookFrame logBookFrame, File debugFile) throws IOException ;

   /**
    * Walks through the API for a managed product and prints the results of most API class
    * to a PrintWriter. The resulting output is for engineering use only and should not be
    * exposed to end users, field support staff, and so on. This output is a raw, unfiltered view
    * of the API. The format is not guaranteed to be constant from one release to the next.
    * The purpose of this output is to assist developers of applications using the API.
    * 
    * @param logBookFrame    the Log Book Frame of interest
    * @param printWriter     a PrintWriter to print to
    */
   public void printApi(AbstractLogBookFrame logBookFrame, PrintWriter printWriter) ;

}


// End of SupportManager.java
