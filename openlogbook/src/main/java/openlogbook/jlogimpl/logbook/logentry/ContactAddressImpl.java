package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.ContactAddress;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the ContactAddressImpl interface.
 * 
 * @author KC0ZPS
 */
public class ContactAddressImpl implements ContactAddress {

   private String _county = new String("") ;
   private String _name = new String("") ;
   private String _address = new String() ;
   private String _continent = new String("") ;
   private String _state = new String("") ;
   
   /**
    * Creates a new ContactAddressImpl.
    */
   public ContactAddressImpl() {
      
   }

   //**********
   // Methods that are inherited from ContactAddress
   //**********

   /**
    * Gets the county.  A "" indicates that this field is not in use.
    * 
    * @return the county.
    */
   public String getCounty() {
      return _county ;
   }
   
   /**
    * Sets the county.  A "" indicates that this field is not in use.
    * 
    * @param county The county string.
    */
   public void setCounty(String county) {
      _county = county ;
   }
   
   /**
    * Gets the operator name.  A "" indicates that this field is not in use.
    * 
    * @return the operator name.
    */
   public String getName() {
      return _name ;
   }
   
   /**
    * Sets the operator name.  A "" indicates that this field is not in use.
    * 
    * @param name The name of the contact.
    */
   public void setName(String name) {
      _name = name ;
   }
   
   /**
    * Gets the contact address.  A "" indicates that this field is not in use.
    * 
    * @return the contact address.
    */
   public String getAddress() {
      return _address ;
   }
   
   /**
    * Sets the contact address.  A "" indicates that this field is not in use.
    * 
    * @param address the contact address.
    */
   public void setAddress(String address) {
      _address = address ;
   }
   
   /**
    * Gets the continent that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @return the continent that the contact resides in.
    */
   public String getContinent() {
      return _continent ;
   }

   /**
    * Sets the continent that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @param continent the continent that the contact resides in.
    */
   public void setContinent(String continent) {
      _continent = continent ;
   }
   
   /**
    * Gets the continent that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @return the continent that the contact resides in.
    */
   public String getUsState() {
      return _state ;
   }
   
   /**
    * Sets the United States state that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @param state the United States state that the contact resides in.
    */
   public void setUsState(String state) {
      _state = state ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if(!_county.equals("")) {
         buffer.append("<" + AdifConstants.CNTY + ":" + _county.length() + ">" + _county) ;         
      }

      if(!_name.equals("")) {
         buffer.append("<" + AdifConstants.NAME + ":" + _name.length() + ">" + _name) ;         
      }

      if(!_address.equals("")) {
         buffer.append("<" + AdifConstants.ADDRESS + ":" + _address.length() + ">" + _address) ;         
      }

      if(!_continent.equals("")) {
         buffer.append("<" + AdifConstants.CONT + ":" + _continent.length() + ">" + _continent) ;         
      }

      if(!_state.equals("")) {
         buffer.append("<" + AdifConstants.STATE + ":" + _state.length() + ">" + _state) ;         
      }

      return buffer.toString() ;

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
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append(getClass() + ":\n") ;
      buffer.append(" Name: " + _name + "\n") ;
      buffer.append(" Address: " + _address + "\n") ;
      buffer.append(" State: " + _state + "\n") ;
      buffer.append(" Country: " + _county + "\n") ;
      buffer.append(" Continent: " + _continent + "\n") ;
      
      return buffer.toString() ;
   }

}
