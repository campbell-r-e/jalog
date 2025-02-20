package jalog.jlog.main;

import jalog.debug.DebugTable;
import jalog.debug.Debugable;
import jalog.debug.PropertyDebugTable ;

import java.util.Enumeration;
import java.util.Properties;

/**
 * A Debugable class for reporting various interesting pieces of system information.
 * This includes things such as the jalog version information and various system properties.
 */
class SystemDebug implements Debugable {
   /**
    * Creates a new SystemDebug.
    */
   SystemDebug() {

   }

   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a table.
    *
    * @return  the contents of the object in a form suitable for displaying in a table
    */
   public DebugTable getDebugTable() {
      String columnNames[] = {"Name", "Value"} ;
      PropertyDebugTable contents = new PropertyDebugTable("System Data", columnNames);

      // jalog Version info
      contents.addEntry("jaLog Version", ApiControl.getVersion()) ;

      // Java Memory Info
      contents.addEntry("Total Memory", Runtime.getRuntime().totalMemory()) ;
      contents.addEntry("Free Memory", Runtime.getRuntime().freeMemory()) ;

      // System properties
      Properties systemProperties = System.getProperties() ;
      Enumeration<?> elements = systemProperties.propertyNames() ;
      while (elements.hasMoreElements()) {
         String name = (String)elements.nextElement() ;
         String value = systemProperties.getProperty(name) ;
         if (value != null) {
            contents.addEntry(name, value) ;
         }
      }
      return contents ;
   }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation of the object
    */
   public String toString() {
      return "SystemDebug" ;
   }


   //**********
   // Public methods
   //**********

   //**********
   // Package methods
   //**********

   //**********
   // Protected methods
   //**********

   //**********
   // Private methods
   //**********

   //**********
   // Class attributes and constants
   //**********

}


// End of SystemDebug.java
