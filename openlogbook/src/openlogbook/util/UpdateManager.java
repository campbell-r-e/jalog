package openlogbook.util;

import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;
import openlogbook.jlog.gui.TableColumnChangeEvent;
import openlogbook.jlog.gui.TableUpdateListener;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.ThemeUpdateListener;
import openlogbook.jlog.util.TableColumnChangeEventId;
import openlogbook.jlog.util.ThemeUpdateEventId;
import openlogbook.jlogimpl.gui.TableColumnChangeEventImpl;
import openlogbook.jlogimpl.gui.TableColumnEnum;
import openlogbook.jlogimpl.gui.ThemeUpdateEventImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import openlogbook.debug.DebugTable;

/**
 * A utility class that contains an general listeners.
 * 
 * @author KC0ZPS
 */
public class UpdateManager implements Debugable {

   private static final UpdateManager _instance = new UpdateManager() ;
   private static LinkedList<ThemeUpdateListener> _themeUpdateListeners = new LinkedList<ThemeUpdateListener>() ;
   private static LinkedList<TableUpdateListener> _tableUpdateListeners = new LinkedList<TableUpdateListener>() ;

   
   /**
    * Creates a new UpdateManager.
    */
   private UpdateManager() {
      
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
      String columnNames[] = { "Name", "Value" };
      PropertyDebugTable contents = new PropertyDebugTable("Update Manager", columnNames);

      Iterator<ThemeUpdateListener> themeIterator = _themeUpdateListeners.listIterator() ;
      while(themeIterator.hasNext()) {
         ThemeUpdateListener themeUpdateListener = (ThemeUpdateListener)themeIterator.next() ;
         contents.addEntry("ThemeUpdateListener", themeUpdateListener);
      }

      Iterator<TableUpdateListener> tableIterator = _tableUpdateListeners.listIterator() ;
      while(tableIterator.hasNext()) {
         TableUpdateListener tableUpdateListener = (TableUpdateListener)tableIterator.next() ;
         contents.addEntry("TableUpdateListener", tableUpdateListener);
      }

      return contents;
   }

   //**********
   // Methods overwritten from Object.
   //**********

   public String toString() {
      return "UpdateManager" ;
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Gets the instance object for this Singleton.
    *
    * @return the instance object for this Singleton
    */
   public static UpdateManager getInstance() {
      return _instance ;
   }

   /**
    * Removes a listener for theme update events.
    * If  listener is null, no exception is thrown and no action is performed.
    *
    * @param listener a listener to be removed
    */
   public void removeThemeUpdateListener(ThemeUpdateListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_themeUpdateListeners) {
         _themeUpdateListeners.remove(listener) ;
      }
   }
   
   /**
    * Adds a listener for theme update events.
    * If listener is null, no exception is thrown and no action is performed.
    *
    * @param listener a listener to be added
    *
    * @see #removeThemeUpdateEventListener
    */
   public void addThemeUpdateListener(ThemeUpdateListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_themeUpdateListeners) {
         if (!_themeUpdateListeners.contains(listener)) {
            _themeUpdateListeners.add(listener) ;
         }
      }
   }

   /**
    * Builds and distributes and internal event to all registered listeners.
    *
    * @param eventId     The event id
    * @param description The description of the event.
    *
    */
   public static void postThemeUpdateEvent(ThemeUpdateEventId eventId, String description) {
      ThemeUpdateEvent themeUpdateEvent = new ThemeUpdateEventImpl(eventId, description);
      Collection<ThemeUpdateListener> listeners = (Collection)_themeUpdateListeners.clone() ;
      Iterator<ThemeUpdateListener> iterator = listeners.iterator() ;
      while (iterator.hasNext()) {
         ThemeUpdateListener listener = (ThemeUpdateListener)iterator.next() ;
         listener.updateUI(themeUpdateEvent) ;
      }
   }

   /**
    * Removes a listener for table update events.
    * If  listener is null, no exception is thrown and no action is performed.
    *
    * @param listener a listener to be removed
    */
   public void removeTableUpdateListener(TableUpdateListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_tableUpdateListeners) {
         _tableUpdateListeners.remove(listener) ;
      }
   }

   /**
    * Adds a listener for column update events.
    * If listener is null, no exception is thrown and no action is performed.
    *
    * @param listener a listener to be added
    */
   public void addTableUpdateListener(TableUpdateListener listener) {
      if (listener == null) {
         return ;
      }
      synchronized (_tableUpdateListeners) {
         if (!_tableUpdateListeners.contains(listener)) {
            _tableUpdateListeners.add(listener) ;
         }
      }
   }

   /**
    * Builds and distributes and internal event to all registered listeners.
    *
    * @param eventId     The event id
    * @param description The description of the event.
    * @param columnList  The new columns.
    */
   public static void postTableColumnChangeEvent(TableColumnChangeEventId eventId, 
         String description, 
         ArrayList<TableColumnEnum> columnList) {
      TableColumnChangeEvent tableColumnChangeEvent = new TableColumnChangeEventImpl(eventId, description);
      Collection<TableUpdateListener> listeners = (Collection)_tableUpdateListeners.clone() ;
      Iterator<TableUpdateListener> iterator = listeners.iterator() ;
      while (iterator.hasNext()) {
         TableUpdateListener listener = (TableUpdateListener)iterator.next() ;
         listener.updateColumns(tableColumnChangeEvent, columnList) ;
      }
   }


}
