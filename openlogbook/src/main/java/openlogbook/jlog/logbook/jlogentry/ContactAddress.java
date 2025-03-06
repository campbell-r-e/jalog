package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines the contact information API.  This interface contains :
 * 
 * NAME
 * ADDRESS
 * CNTY
 * CONT
 * STATE
 * 
 * @author KC0ZPS
 */
public interface ContactAddress extends Adif {

   /**
    * Gets the operator name.  A "" indicates that this field is not in use.
    * 
    * @return the operator name.
    */
   public String getName() ;
   
   /**
    * Sets the operator name.  A "" indicates that this field is not in use.
    * 
    * @param name The name of the contact.
    */
   public void setName(String name) ;
   
   /**
    * Gets the contact address.  A "" indicates that this field is not in use.
    * 
    * @return the contact address.
    */
   public String getAddress() ;
   
   /**
    * Sets the contact address.  A "" indicates that this field is not in use.
    * 
    * @param address the contact address.
    */
   public void setAddress(String address) ;

   /**
    * Gets the county.  A "" indicates that this field is not in use.
    * 
    * @return the county.
    */
   public String getCounty() ;
   
   /**
    * Sets the county.  A "" indicates that this field is not in use.
    * 
    * @param county The county string.
    */
   public void setCounty(String county) ;
   
   /**
    * Gets the continent that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @return the continent that the contact resides in.
    */
   public String getContinent() ;

   /**
    * Sets the continent that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @param continent the continent that the contact resides in.
    */
   public void setContinent(String continent) ;
   
   /**
    * Gets the United States state that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @return the United States state that the contact resides in.
    */
   public String getUsState() ;
   
   /**
    * Sets the United States state that the contact resides in.  A "" indicates that this field is not in use.
    * 
    * @param state the United States state that the contact resides in.
    */
   public void setUsState(String state) ;
}
