package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.util.IotaEnum;

/**
 * An implementation of the Iota interface.
 * 
 * @author KC0ZPS
 */
public class IotaImpl implements Iota {

   private IotaEnum _iotaEnum ;
   private Integer  _value ;
   private String   _unknownIota = new String("") ;
   
   /**
    * Creates a new IotaImpl.
    * 
    * @param iotaEnum The IotaEnum that this Iota wrapper will be set to.
    * @param value The value field that this Iota wrapper will be set to.
    */
   public IotaImpl(IotaEnum iotaEnum, Integer value) {
      _iotaEnum = iotaEnum ;
      _value = value ;
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
      return _iotaEnum + " - " + _value ;
   }
   
   //**********
   // Methods that are inherited from Iota
   //**********

   /**
    * Gets the IotaEnum.
    * 
    * @return the IotaEnum.
    */
   public IotaEnum getIotaEnum() {
      return _iotaEnum ;
   }

   /**
    * Sets the IotaEnum.
    * 
    * @param iotaEnum the IotaEnum.
    */
   public void setIotaEnum(IotaEnum iotaEnum) {
      _iotaEnum = iotaEnum ;
   }
   
   /**
    * Gets the Iota integer value.
    * 
    * @return the Iota integer value.
    */
   public Integer getValue() {
      return _value ;
   }
      
   /**
    * Sets the Iota integer value.
    * 
    * @param value the Iota integer value.
    */
   public void setValue(Integer value) {
      _value = value ;
   }

   /**
    * Gets the unknown iota value.  This is used if the value read from the file does not match the enum mapping.
    * 
    * @return the unknown iota value.
    */
   public String getUnknownIota() {
      return _unknownIota ;
   }
   
   /**
    * Sets the unknown iota value.  This is used if the value read from the file does not match the enum mapping.
    * 
    * @param unknownIota the unknown iota value.
    */
   public void setUnknownIota(String unknownIota) {
      _unknownIota = unknownIota ;
   }

}
