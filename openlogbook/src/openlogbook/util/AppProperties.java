package openlogbook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * A singleton class to support application properties (ala INI files).
 */
public class AppProperties {

   /**
    * Creates a new AppProperties. This constructor is private to enforce Singleton characteristic.
    *
    */
   private AppProperties() {
   }

   //**********
   // Public methods
   //**********

   /**
    * Adds a property to the application properties.
    *
    * @param propertyName   name of the property
    * @param propertyValue  value of the property
    *
    */
   public static void addProperty(String propertyName, String propertyValue) {
      _properties.put(propertyName, propertyValue) ;
   }

   /**
    * Adds an integer property to the application properties.
    *
    * @param propertyName   name of the property
    * @param propertyValue  value of the property
    *
    */
   public static void addProperty(String propertyName, int propertyValue) {
      _properties.put(propertyName, Integer.toString(propertyValue)) ;
   }


   /**
    * Searches for the property with the specified key in this property list.
    * If the key is not found in this property list, the default property list, and its defaults,
    * recursively, are then checked. The method returns the default value argument if the property is not found.
    *
    * @param key           the hashtable key
    * @param defaultValue  a default value
    *
    * @return    the value in this property list with the specified key value.
    */
   public static String getStringProperty(String key, String defaultValue) {
      return _properties.getProperty(key, defaultValue) ;
   }


   /**
    * Searches for the property with the specified key in this property list.
    * If the key is not found in this property list, the default property list, and its defaults,
    * recursively, are then checked. The method returns the default value argument if the property is not found.
    *
    * @param key           the hashtable key
    * @param defaultValue  a default value
    *
    * @return    the value in this property list with the specified key value.
    */
   public static boolean getBooleanProperty(String key, boolean defaultValue) {
      String value = _properties.getProperty(key, String.valueOf(defaultValue)) ;
      if (value.equals("1") || value.equals("true") || value.equals("yes")) {
         return true ;
      } else {
         return false ;
      }
   }


   /**
    * Searches for the property with the specified key in this property list.
    * If the key is not found in this property list, the default property list, and its defaults,
    * recursively, are then checked. The method returns the default value argument if the property is not found.
    *
    * @param key           the hashtable key
    * @param defaultValue  a default value
    *
    * @return    the value in this property list with the specified key value.
    */
   public static int getIntProperty(String key, int defaultValue) {
      String value = _properties.getProperty(key, String.valueOf(defaultValue)) ;
      int intValue ;
      try {
         intValue = Integer.parseInt(value) ;
      } catch (NumberFormatException e) {
         intValue = defaultValue ;
      }
      return intValue ;
   }

   /**
    * Loads properties from a given file. If the file exists in the local
    * filespace, properties are loaded from the file. Otherwise, the file
    * is searched for along the classpath. This allows properties files to
    * be located in a Jar file.
    *
    * @param propertiesFilename  name of properties file to load
    *
    * @exception FileNotFoundException if the properties file does not exist
    * @exception IOException if an IO error occurs reading the file
    */
   public static void load(String propertiesFilename) throws IOException, FileNotFoundException {
      // Look for file locally
      File propertiesFile = new File(propertiesFilename) ;
      if (propertiesFile.exists()) {
         FileInputStream fileInputStream = new FileInputStream(propertiesFilename) ;
         try {
            _properties.load(fileInputStream) ;
         } finally {
            fileInputStream.close() ;
         }
      } else {
         // May be accessible as a resource
         InputStream inputStream = ClassLoader.getSystemResourceAsStream(propertiesFilename) ;
         if (inputStream == null) {
            throw new FileNotFoundException() ;
         }
         try {
            _properties.load(inputStream) ;
         } finally {
            inputStream.close() ;
         }
      }
   }

   /**
    * Loads properties from an InputStream.
    *
    * @param inputStream  an InputStream to read properties from
    *
    * @exception IOException if an IO error occurs reading the stream
    */
   public static void load(InputStream inputStream) throws IOException {
      _properties.load(inputStream) ;
   }

   /**
    * Saves properties to a given file. If the file exists in the local
    * filespace, then it is overwritten.
    *
    * @param propertiesFilename  name of file to save properties in
    *
    * @exception IOException if an IO error occurs writing the file
    */
   public static void store(String propertiesFilename) throws IOException {
      // Look for file locally
      File propertiesFile = new File(propertiesFilename) ;
      if (propertiesFile.exists()) {
         propertiesFile.delete();
      }
      
      FileOutputStream fileOutputStream = new FileOutputStream(propertiesFile);
      try {
         _properties.store(fileOutputStream, "") ;
      } finally {
         fileOutputStream.close() ;
      }
   }
   
   /**
    * Gets an enumeration of all the keys in the property list.
    * 
    * @return an enumeration of all the keys in the property list.
    */
   public static Enumeration<?> propertyNames() {
      return _properties.propertyNames();
   }


   //**********
   // Class attributes and constants
   //**********

   private static Properties      _properties = new Properties() ;
}


// End of AppProperties.java

