package openlogbook.jlog.util;

import java.util.HashMap;

/**
* Supports mapping between string values and objects. Usually used in conjunction
* with an enumerated type.  The intended use is to convert between string values exchanged
* in some protocol with an external source and internally defined enumerated types.
* 
* @author KC0ZPS
*/
public class StringToObject {

/**
 * Creates a new StringToObject. The creator should supply a table of lookup values. Each
 * row in the table corresponds to a pair of values. The first value is an String object
 * that corresponds to the string value, while the second value is the corresponding object.
 *
 * @param lookupData         a table of Strings and Objects. lookupData[][0] should be
 *                           an String. lookupData[][1] should be the corresponding object.
 * @param defaultObjectValue the default object to return if a lookup of the string value fails.
 */
public StringToObject(Object[][] lookupData, Object defaultObjectValue) {
   _defaultObjectValue = defaultObjectValue ;
   _strToObject = new HashMap<Object, Object>(lookupData.length) ;
   _objectToStr = new HashMap<Object, Object>(lookupData.length) ;
   for (int idx = 0; idx < lookupData.length; idx++) {
      _strToObject.put(lookupData[idx][0], lookupData[idx][1]) ;
      _objectToStr.put(lookupData[idx][1], lookupData[idx][0]) ;
   }
}

//**********
// Public methods
//**********

/**
 * Gets the string value corresponding to the object passed in.
 *
 * @param object  an object to be converted
 *
 * @return the string value corresponding to the object passed in.
 *
 * @throws IllegalArgumentException if the object can't be mapped.
 */
public String getStringValue(Object object) {
   String result = (String)_objectToStr.get(object) ;
   if (result == null) {
      throw new IllegalArgumentException("Can't convert to string: " + object) ;
   }
   return result ;
}

/**
 * Gets the object corresponding to the string passed in.
 *
 * @param strValue  an string to be converted
 *
 * @return the object corresponding to the string passed in. The default value set in this object
 *         will be returned if the input string is not registered.
 */
public Object getObjectValue(String strValue) {
   Object result = _strToObject.get(new String(strValue)) ;
   if (result == null) {
      result = _defaultObjectValue ;
   }
   return result ;
}


//**********
// Class attributes and constants
//**********

private HashMap<Object, Object>  _strToObject ;
private HashMap<Object, Object>  _objectToStr ;
private Object                   _defaultObjectValue ;

}

//End of class StringToObject.java
