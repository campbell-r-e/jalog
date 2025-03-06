package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.util.IotaEnum;

/**
 * An interface that defines the IOTA API.
 * 
 * @author KC0ZPS
 */
public interface Iota {

   /**
    * Gets the IotaEnum.
    * 
    * @return the IotaEnum.
    */
   public IotaEnum getIotaEnum() ;
   
   /**
    * Sets the IotaEnum.
    * 
    * @param iotaEnum the IotaEnum.
    */
   public void setIotaEnum(IotaEnum iotaEnum) ;
   
   /**
    * Gets the Iota integer value.
    * 
    * @return the Iota integer value.
    */
   public Integer getValue() ;
   
   /**
    * Sets the Iota integer value.
    * 
    * @param value the Iota integer value.
    */
   public void setValue(Integer value) ;
  
   /**
    * Gets the unknown iota value.  This is used if the value read from the file does not match the enum mapping.
    * 
    * @return the unknown iota value.
    */
   public String getUnknownIota() ;
   
   /**
    * Sets the unknown iota value.  This is used if the value read from the file does not match the enum mapping.
    * 
    * @param unknownIota the unknown iota value.
    */
   public void setUnknownIota(String unknownIota) ;

}
