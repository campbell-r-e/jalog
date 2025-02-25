package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines the contact information API.  This interface contains :
 * 
 * QTH          
 * IOTA      
 * AGE          
 * CQZ       
 * DXCC      
 * GRIDSQUARE
 * ITUZ
 * 
 * @author KC0ZPS
 */
public interface ContactStationInformation extends Adif {

   /**
    * Gets the IOTA information.  null if not used.
    * 
    * @return an IOTA information.
    */
   public Iota getIota() ;
   
   /**
    * Sets the IOTA information.  null if not used.
    * 
    * @param iota the IOTA information.
    */
   public void setIota(Iota iota) ;
   
   /**
    * Gets the contact QTH.  A "" indicates that this field is not in use.
    * 
    * @return the contact QTH.
    */
   public String getQth() ;
   
   /**
    * Sets the contact QTH.  A "" indicates that this field is not in use.
    * 
    * @param qth the contact QTH.
    */
   public void setQth(String qth) ;

   /**
    * Gets the contact age.  A "" indicates that this field is not in use.
    * 
    * @return the contact age.
    */
   public String getAge() ;
   
   /**
    * Sets the contact age.  A "" indicates that this field is not in use.
    * 
    * @param age the contact age.
    */
   public void setAge(String age) ;
   
   /**
    * Gets the CQ Zone.  A "" indicates that this field is not in use.
    * 
    * @return the CQ Zone.
    */
   public String getCqZone() ;
   
   /**
    * Sets the CQ Zone.  A "" indicates that this field is not in use.
    * 
    * @param cqZone the CQ Zone.
    */
   public void setCqZone(String cqZone) ;
   
   /**
    * Gets the numeric identifiers from ARRL.   A "" indicates that this field is not in use.
    * 
    * @return the numeric identifiers from ARRL.
    */
   public String getDxcc() ;
   
   /**
    * Returns the numeric identifiers from ARRL.  A "" indicates that this field is not in use.
    * 
    * @param dxcc the numeric identifiers from ARRL.
    */
   public void setDxcc(String dxcc) ;
   
   /**
    * Gets the grid square.  A "" indicates that this field is not in use.
    * 
    * @return the grid square.
    */
   public String getGridSquare() ;
   
   /**
    * Sets the grid square.  A "" indicates that this field is not in use.
    * 
    * @param gridSquare the the grid square.
    */
   public void setGridSquare(String gridSquare) ;
   
   /**
    * Gets the ITU Zone.  A "" indicates that this field is not in use.
    * 
    * @return the ITU Zone.
    */
   public String getItuZone() ;
   
   /**
    * Sets the ITU Zone.  A "" indicates that this field is not in use.
    * 
    * @param ituZone the ITU Zone.
    */
   public void setItuZone(String ituZone) ;
}
