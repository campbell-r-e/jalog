package openlogbook.jlog.util;

import java.util.HashMap;

/**
 * Supports mapping between integer values and objects. Usually used in conjunction
 * with an enumerated type.  The intended use is to convert between integer values exchanged
 * in some protocol with an external source and internally defined enumerated types.
 * 
 * @author KC0ZPS
 */
public class IntToObject {

   /**
    * Creates a new IntToObject. The creator should supply a table of lookup values. Each
    * row in the table corresponds to a pair of values. The first value is an Integer object
    * that corresponds to the integer value, while the second value is the corresponding object.
    *
    * @param lookupData         a table of Integers and Objects. lookupData[][0] should be
    *                           an Integer. lookupData[][1] should be the corresponding object.
    * @param defaultObjectValue the default object to return if a lookup of the integer value fails.
    */
   public IntToObject(Object[][] lookupData, Object defaultObjectValue) {
      _defaultObjectValue = defaultObjectValue ;
      _intToObject = new HashMap<Object, Object>(lookupData.length) ;
      _objectToInt = new HashMap<Object, Object>(lookupData.length) ;
      for (int idx = 0; idx < lookupData.length; idx++) {
         _intToObject.put(lookupData[idx][0], lookupData[idx][1]) ;
         _objectToInt.put(lookupData[idx][1], lookupData[idx][0]) ;
      }
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the integer value corresponding to the object passed in.
    *
    * @param object  an object to be converted
    *
    * @return the integer value corresponding to the object passed in.
    *
    *
    * @throws IllegalArgumentException if the object can't be mapped.
    */
   public int getIntValue(Object object) {
      Integer result = (Integer)_objectToInt.get(object) ;
      if (result == null) {
         throw new IllegalArgumentException("Can't convert to integer: " + object + " (" + object.getClass().getName() + ")") ;
      }
      return result.intValue() ;
   }

   /**
    * Gets the object corresponding to the integer passed in.
    *
    * @param intValue  an integer to be converted
    *
    * @return the object corresponding to the integer passed in. The default value set in this object
    *         will be returned if the input integer is not registered.
    */
   public Object getObjectValue(int intValue) {
      Object result = _intToObject.get(new Integer(intValue)) ;
      if (result == null) {
         result = _defaultObjectValue ;
      }
      return result ;
   }


   //**********
   // Class attributes and constants
   //**********

   private HashMap<Object, Object>      _intToObject ;
   private HashMap<Object, Object>      _objectToInt ;
   private Object       _defaultObjectValue ;
}
