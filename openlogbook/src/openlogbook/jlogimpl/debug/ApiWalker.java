package openlogbook.jlogimpl.debug;

import openlogbook.jlog.gui.AbstractLogBookFrame;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import openlogbook.debug.Debug;
import openlogbook.util.StringUtility;

/**
 * A utility class for 'walking' through the API and printing the results
 * of 'get' and 'is' API methods.
 */
public class ApiWalker {

   /**
    * Creates a new ApiWalker.
    */
   public ApiWalker() {
   }

   //**********
   // Package methods
   //**********

   /**
    * Walks the API for a ManagedProduct and displays to a PrintWriter.
    *
    * @param walkManagedProduct  a AbstractLogBookFrame to be inspected
    * @param printWriter         a PrintWriter to print to.
    *
    */
   public void walkLogBook(AbstractLogBookFrame logBookFrame, PrintWriter printWriter) {
      try {
         printWriter.println("Log Book") ;
         printWriter.println("---------------") ;
         displayObject(printWriter, logBookFrame, 0) ;
      } catch (Exception e) {
         Debug.logException(e) ;
      } finally {
         _beenThere.clear() ;
         _objectStack.clear() ;
      }
   }

   /**
    * Walks the API for an arbitrary object.
    *
    * @param object          an object to be inspected
    * @param printWriter     a PrintWriter to print to.
    *
    */
   public void walkObject(Object object, PrintWriter printWriter) {
      try {
         displayObject(printWriter, object, 0) ;
      } catch (Exception e) {
         Debug.logException(e) ;
      } finally {
         _beenThere.clear() ;
         _objectStack.clear() ;
      }
   }


   //**********
   // Protected methods
   //**********

   //**********
   // Private methods
   //**********

   /**
    * Displays methods and results for an object.
    * 
    * @param printWriter  a PrintWriter to print to
    * @param object       the instance of objectClass to be displayed
    * @param indentLevel  indent level for display purposes
    */
   private void displayObject(PrintWriter printWriter, Object object, int indentLevel) {
      // Sanity check
      if (indentLevel > 25) {
         throw new RuntimeException("Exceed max nestng depth") ;
      }

      Class<?> objectClass = getFirstApiClass(object) ;
      if (!object.getClass().isArray()) {
         if (isLeafClass(objectClass)) {
            // Don't recurse on classes designated as leaf classes
            printWriter.println(formatObject(object)) ;
            return ;
         } else if (_objectStack.contains(object)) {
            // Don't recurse on an object already in the stack
            printWriter.print("(recursive)") ;
            printWriter.println(object) ;
            return ;
         } else if (_beenThere.contains(object)) {
            // Or certain objects we've already displayed
            printWriter.print("(repeat)") ;
            printWriter.println(object) ;
            return ;
         } else if (isExcludedSibling(object)) {
            // Don't recurse from one object to another of the same type
            printWriter.print("(sibling)") ;
            printWriter.println(object) ;
            return ;
         }
         
         // The object stack keeps track of where we are
         _objectStack.push(object) ;
         
         // Except for classes 'near the edge', keep track that we've displayed them
         // so we don't do it again.
         if (!isAlmostLeafClass(objectClass)) {
            _beenThere.add(object) ;
         }
      }

      try {
         printWriter.println(trimClassname(objectClass.getName())) ;
         Method[] methods = getUniqueMethods(objectClass.getMethods()) ;
         for (int idx = 0; idx < methods.length; idx++) {
            if (isMethodViewable(methods[idx])) {
               displayMethod(printWriter, object, methods[idx], indentLevel) ;
               printWriter.flush() ;
            }
         }
      } finally {
         _objectStack.pop() ;
      }
   }

   /**
    * Call a method and display its name and results.
    *  
    * @param printWriter  a PrintWriter to print to
    * @param object       the object on which to invoke the method
    * @param method       the method to be invoked
    * @param indentLevel  indent level for display purposes.
    */
   private void displayMethod(PrintWriter printWriter, Object object, Method method, int indentLevel) {
      // Separate print calls made to minimize string concatenation
      printIndent(printWriter, indentLevel) ;
      printWriter.print(method.getName()) ;
      printWriter.print("()=") ;
      printWriter.flush() ;
      
      // Call the method and get the return object
      Object result = getAttribute(object, method) ;
      if (result == null) {
         printWriter.println("null") ;
         return ;
      }

      // Display the return object. If it's an array, it needs special handling.
      Class<?> returnType = method.getReturnType() ;
      boolean isLeaf = _leafMethods.contains(method) ;
      if (result.getClass().isArray()) {
         displayArray(printWriter, returnType.getComponentType(), result, indentLevel, isLeaf) ;
      } else if (isLeaf) {
         printWriter.println(result) ;
      } else {
         displayObject(printWriter, result, indentLevel+1) ;
      }
   }

   /**
    * Display an array of object.
    * 
    * @param printWriter   a PrintWriter to print to 
    * @param elementClass  the class object of an element in the array
    * @param arrayObject   the array object itself
    * @param indentLevel   indent level for display purposes
    * @param isLeaf        indicates if the method that produced the array was a special 'leaf' method.
    */
   private void displayArray(PrintWriter printWriter, Class<?> elementClass, Object arrayObject, int indentLevel, boolean isLeaf) {
      if (!arrayObject.getClass().isArray()) {
         // Check for foul-ups somewhere else
         System.out.println("Not array: " + arrayObject) ;
         return ;
      }
      
      int length = Array.getLength(arrayObject) ;
      printWriter.println(trimClassname(elementClass.getName()) + "[" + length + "]") ;
      if (elementClass.isPrimitive() || isLeaf) {
         // For primitive types or leaf types, just print the object
         for (int idx = 0; idx < length; idx++) {
            printIndent(printWriter, indentLevel+1) ;
            printWriter.print("[" + idx + "]=") ;
            printWriter.println(formatObject(Array.get(arrayObject, idx))) ;
         }
         printWriter.flush() ;
      } else {
         // Otherwise, drill down into the object
         indentLevel++ ;
         for (int idx = 0; idx < length; idx++) {
            printIndent(printWriter, indentLevel) ;
            printWriter.print("[" + idx + "]=") ;
            Object elementObject = Array.get(arrayObject, idx) ;
            displayObject(printWriter, elementObject, indentLevel+1) ;
            printWriter.flush() ;
         }
      }
   }

   /**
    * Calls a method on an object and returns the result.
    * 
    * @param object  the object to work on
    * @param method  the method to be called
    * 
    * @return the return value of the called method
    */
   private Object getAttribute(Object object, Method method) {
      try {
         Object result = method.invoke(object, (Object[])null) ;
         return result ;
      } catch (InvocationTargetException e) {
         // The method that was called threw an exception.
         // Get that exception object and return a suitable string.
         Throwable targetException = e.getTargetException() ;
         return trimClassname(targetException.getClass().getName()) + ": " + targetException.getMessage() ;
      } catch (Exception e) {
         // Some other exception occurred that was not expected
         System.out.println("Exception on method " + method + " for " + object) ;
         e.printStackTrace() ;
         return trimClassname(e.getClass().getName()) + ": " + e.getMessage() ;
      }
   }

   /**
    * Gets the 'first' public API class (or interface) encountered for the given object.
    * The basic algorithm is:
    * 1. If the object's class is part of the public API return it.
    * 2. Else, look for the first interface the class implements that is part of the public API.
    * 3. If no public class or interface is found, check the superclass.
    * 
    * @param object  an object of interest
    * 
    * @return the 'first' public API class (or interface) encountered for the given object.
    */
   private Class<?> getFirstApiClass(Object object) {
      Class<?> objectClass = object.getClass() ;
      
      // Don't bother if the class isn't part of the public or private API
      if (!objectClass.getName().startsWith(_apiPrefix)) {
         return objectClass ;
      }
      
      while (objectClass != null) {
         if (objectClass.getName().startsWith(_apiPublicPrefix)) {
            return objectClass ;
         }
         
         // Check declared interfaces
         Class<?> interfaceClass = getFirstApiInterface(objectClass) ;
         if (interfaceClass != null) {
            return interfaceClass ;
         }
         
         // Move up to the superclass
         objectClass = objectClass.getSuperclass() ;
      }

      return object.getClass() ;
   }

   /**
    * Gets the first public API interface implemented by a class.
    * 
    * @param objectClass  the class of interest
    * 
    * @return the first public API interface implemented by a class.
    */
   private Class<?> getFirstApiInterface(Class<?> objectClass) {
      Class<?>[] interfaces = objectClass.getInterfaces() ;
      for (int idx = 0; idx < interfaces.length; idx++) {
         if (interfaces[idx].getName().startsWith(_apiPublicPrefix)) {
            return interfaces[idx] ;
         }
      }
      return null ;
   }

   /**
    * Gets the unique methods from a set of methods. This is necessary because
    * Class.getMethods() might return duplicates.
    * 
    * @param methods  an array of methods
    * 
    * @return the unique subset of the input methods
    */
   private Method[] getUniqueMethods(Method[] methods) {
      ArrayList<Method> result = new ArrayList<Method>(methods.length) ;
      for (int idx = 0; idx < methods.length; idx++) {
         if (!result.contains(methods[idx])) {
            result.add(methods[idx]) ;
         }
      }
      return (Method[])result.toArray(new Method[result.size()]) ;
   }

   /**
    * Tests if a class is 'almost' a leaf class. This condition is deemed true if
    * the class does not have any methods that nest to other API classes beyond
    * a certain depth (currently 1).
    * 
    * @param testClass  a class to be tested
    * 
    * @return true if a class is 'almost' a leaf class.
    */
   private boolean isAlmostLeafClass(Class<?> testClass) {
      // Look for 'get' or 'is' methods that lead to other API classes
      Method[] methods = testClass.getMethods() ;
      for (int idx = 0; idx < methods.length; idx++) {
         if (isMethodViewable(methods[idx])) {
            Class<?> returnType = methods[idx].getReturnType() ;
            if (returnType.isArray()) {
               returnType = returnType.getComponentType() ;
            }
            if (!isLeafClass(returnType) && returnType.getName().startsWith(_apiPrefix)) {
               return false ;
            }
         }
      }
      return true ;
   }

   /**
    * Tests if a class is a 'leaf' class. A leaf class must meet one of the following criteria:
    * 1. It's not part of the public or private API.
    * 2. It's specified in the collection of leaf classes.
    * 3. It has not public 'is' or 'get' methods that lead to other API classes.
    * 
    * @param testClass  a class to be tested
    * 
    * @return if a class is a 'leaf' class.
    */
   private boolean isLeafClass(Class<?> testClass) {
      if (!testClass.getName().startsWith(_apiPrefix)) {
         return true ;
      }
      if (_leafClasses.contains(testClass)) {
         return true ;
      }

      // Look for 'get' or 'is' methods
      Method[] methods = testClass.getMethods() ;
      for (int idx = 0; idx < methods.length; idx++) {
         if (isMethodViewable(methods[idx])) {
            return false ;
         }
      }
      return true ;
   }

   /**
    * Tests if an object is a possible candidate for exclusion due to sibling rules.
    * This test simply looks at the collection of classes that require special 'sibling exclusion'
    * processing to see if the given object's class is in the list or is a subclass of a class
    * in the list.
    * 
    * @param object  an object to be tested
    * 
    * @return true if an object is a possible candidate for exclusion due to sibling rules.
    */
   private boolean isCandidateExcludedSibling(Object object) {
      Iterator<?> iterator = _excludedSiblingClasses.iterator() ;
      while (iterator.hasNext()) {
         Class<?> siblingClass = (Class<?>)iterator.next() ;
         if (siblingClass.isAssignableFrom(object.getClass())) {
            return true ;
         }
      }
      return false ;
   }

   /**
    * Tests if an object is an 'excluded sibling'.
    * The basic idea here is that for certain special cases (such as Nodes and Ports), we don't
    * want to recurse through additional objects that are of the same type as an object already in
    * the stack. For example, while following a ManagedPort's connections, this may lead to another ManagedPort
    * (in the case of an ISL). We don't want to drill down into the entire other ManagedProduct at that
    * point in the inspection.
    * 
    * @param object  an object to be tested
    * 
    * @return if an object is an 'excluded sibling'.
    */
   private boolean isExcludedSibling(Object object) {
      if (!isCandidateExcludedSibling(object)) {
         return false ;
      }
      
      // Look through the call stack for matching classes
      Iterator<?> iterator = _objectStack.iterator() ;
      while (iterator.hasNext()) {
         Class<?> siblingClass = iterator.next().getClass() ;
         if (siblingClass.isAssignableFrom(object.getClass()) || object.getClass().isAssignableFrom(siblingClass)) {
            return true ;
         }
      }
      return false ;
   }

   /**
    * Tests if a method is 'viewable'. To be 'viewable', the method must:
    * 1. Not be static.
    * 2. Not require any parameters.
    * 3. Not be in the list of excluded methods.
    * 4. Begin with 'get' or 'is'.
    * 5. Not be the method getClass.
    * 
    * @param method  a Method to be tested
    * 
    * @return true if a method is 'viewable'.
    */
   private boolean isMethodViewable(Method method) {
      String methodName = method.getName() ;
      if (Modifier.isStatic(method.getModifiers())) {
         return false ;
      }
      if (method.getParameterTypes().length != 0) {
         return false ;
      }
      if ((methodName.startsWith("get") || methodName.startsWith("is"))
       && !methodName.equals("getClass")) {
         if (_excludedMethods.contains(method)) {
            return false ;
         } else {
            return true ;
         }
      } else {
         return false ;
      }
   }

   /**
    * Apply any special formatting to an object. Current formatting cases are:
    * 1. java.util.Date objects are converted with a custom DateFormat
    * 2. Objects whose string representation include the API package name have the package name stripped off
    * 3. Byte objects are displayed as two hex characters.
    * 
    * @param object an object to be formatted.
    * 
    * @return a formatted object
    */
   private Object formatObject(Object object) {
      if (object instanceof Date) {
         return StringUtility.formatSimpleDate((Date)object) ;
      } else if (object instanceof Byte) {
         return StringUtility.toHexString(((Byte)object).byteValue()) ;
      } else if (object instanceof Integer) {
         return object.toString() + " (0x" + Integer.toHexString(((Integer)object).intValue()) + ")" ;
      } else {
         String result = object.toString() ;
         if (result.startsWith(_apiPrefix)) {
            result = trimClassname(result) ;
         }
         return result ;
      }
   }

   /**
    * 'Trims' the package prefix from a classname.
    * 
    * @param className  a class name
    * 
    * @return the classname minus the package prefix
    */
   private String trimClassname(String className) {
      int index = className.lastIndexOf('.') ;
      return className.substring(index+1) ;
   }

   /**
    * Prints the correct number of leading spaces for the specified indent level.
    * 
    * @param printWriter  a PrinterWriter to print to
    * @param indentLevel  specifies the indentation level
    */
   private void printIndent(PrintWriter printWriter, int indentLevel) {
      for (int idx = 0; idx < indentLevel; idx++) {
         printWriter.print("   ") ;
      }
   }

   //**********
   // Class attributes and constants
   //**********

   private HashSet<Class<?>> _leafClasses = new HashSet<Class<?>>() ;
   private HashSet<Method>   _leafMethods = new HashSet<Method>() ;
   private HashSet<Method>   _excludedMethods = new HashSet<Method>() ;
   private HashSet<Object>   _beenThere = new HashSet<Object>() ;
   private ArrayList<?>      _excludedSiblingClasses = new ArrayList<Class<?>>() ;
   private Stack<Object>     _objectStack = new Stack<Object>() ;

   private static final String      _apiPrefix = "openlogbook" ;
   private static final String      _apiPublicPrefix = "openlogbook.jlog" ;

}


// End of ApiWalker.java
