package openlogbook.jlog.gui;

import java.io.Serializable;

/**
 * An interface defining a ThemeUpdateEvent.
 * 
 * @author KC0ZPS
 */
public interface ThemeUpdateEvent extends Serializable {

   /**
    * Gets a text description of the event.
    *
    * @return a text description of the event
    */
   public String getDescription() ;
   
}
