package openlogbook.jlog.gui;

/**
 * This interface defines an update listener for themes.  This listener is invoked when
 * a theme is applied.
 * 
 * @author KC0ZPS
 */
public interface ThemeUpdateListener {

   /**
    * Invoked when an action occurs.
    * 
    * @param e The ThemeUpdateEvent.
    */
   public void updateUI(ThemeUpdateEvent e);

}
