package openlogbook.debug.monitor;

import openlogbook.debug.LogDebugTable;

import javax.swing.JPanel;

/**
 * A generic history log drill down panel. This panel is displayed in a dialog
 * when drilling down into a history log entry.
 */
public abstract class DrillDownPanel extends JPanel {

   /**
    * Creates a new DrillDownPanel.
    */
   public DrillDownPanel() {
      super() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Sets the drill down contents.
    *
    * @param logDebugTable   Log contents object
    * @param row             initial row
    *
    */
   public abstract void setContents(LogDebugTable logDebugTable, int row) ;


   //**********
   // Class attributes and constants
   //**********

   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.DrillDownPanel
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -5824206703581773022L;

}

// End of DrillDownPanel.java

