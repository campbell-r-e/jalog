package openlogbook.debug.instrumentation;

import java.util.Iterator;
import java.util.ArrayList;

import net.sourceforge.sizeof.SizeOf;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;

public class ObjectSizer implements Debugable {

   private ArrayList<Class<?>> _classList = new ArrayList<Class<?>>();

   public ObjectSizer() {
      SizeOf.skipStaticField(true);
      SizeOf.setMinSizeToLog(10);
      SizeOf.turnOffDebug() ;

      try {
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.LogEntryImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.CallsignImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.EraImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.RstImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.FrequencyInformationImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.ContactAddressImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.ContactStationInformationImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.QslImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.SatelliteImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.ContestImpl")) ;
         _classList.add(Class.forName("openlogbook.jlogimpl.logbook.logentry.MiscImpl")) ;
      } catch (Exception e) {
         Debug.logException(e) ;
      }
   }

   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a
    *         table
    */
   public DebugTable getDebugTable() {
      String columnNames[] = { "Class", "Size" };      
      PropertyDebugTable contents = new PropertyDebugTable("Object Size", columnNames);

      try {
         Iterator<Class<?>> iterator = _classList.iterator() ;
         while(iterator.hasNext()) {
            Class<?> theClass = iterator.next() ;
            contents.addEntry(theClass.toString(), "" + SizeOf.deepSizeOf(theClass)) ;
         }
      } catch (Exception e) {
         contents.addEntry("ERROR", "Instrumentation not enabled.") ;
         Debug.logException(e) ;
      }
      return contents;
   }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of the object.
    * 
    * @return string representation of the object.
    */
   public String toString() {
      return "ObjectSizer" ;
   }
}
