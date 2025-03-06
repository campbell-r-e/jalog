package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

/**
 * An interface that defines the data that doesn't belong anywhere else API.
 * This interface defines :
 * 
 * COMMENT
 * ARRL_SECT 
 * CONTEST_ID
 * NOTES
 * PFX
 * TEN_TEN  
 * VE_PROV
 * 
 * @author KC0ZPS
 */
public interface Misc extends Adif {
   
   /**
    * Gets the comment.  A "" indicates that this field is not in use.
    * 
    * @return the comment.
    */
   public String getComment() ;
   
   /**
    * Sets the comment.  A "" indicates that this field is not in use.
    * 
    * @param comment the comment.
    */
   public void setComment(String comment) ;

   /**
    * Gets the ARRL Section.  A "" indicates that this field is not in use.
    * 
    * @return the ARRL Section.
    */
   public String getArrlSection() ;
   
   /**
    * Sets the ARRL Section.  A "" indicates that this field is not in use.
    * 
    * @param arrlSection the ARRL Section.
    */
   public void setArrlSection(String arrlSection) ;
   
   /**
    * Gets the long text for digital copy, third party traffic, etc.
    * A "" indicates that this field is not in use.
    * 
    * @return the long text for digital copy, third party traffic, etc.
    */
   public String getNotes() ;
   
   /**
    * Sets the long text for digital copy, third party traffic, etc.
    * A "" indicates that this field is not in use.
    * 
    * @param notes the long text for digital copy, third party traffic, etc.
    */
   public void setNotes(String notes) ;
   
   /**
    * Gets the WPX Prefix.  A "" indicates that this field is not in use.
    * 
    * @return the WPX Prefix.
    */
   public String getWpxPrefix() ;
   
   /**
    * Sets the WPX Prefix.  A "" indicates that this field is not in use.
    * 
    * @param wpxPrefix the WPX Prefix.
    */
   public void setWpxPrefix(String wpxPrefix) ;
   
   /**
    * Gets the Ten Ten identifier.  A "" indicates that this field is not in use.
    * 
    * @return the Ten Ten identifier.
    */
   public String getTenTen() ;
   
   /**
    * Sets the Ten Ten identifier.  A "" indicates that this field is not in use.
    * 
    * @param tenTen the Ten Ten identifier.
    */
   public void setTenTen(String tenTen) ;
   
   /**
    * Gets the VE_PROV.  A "" indicates that this field is not in use.
    * 
    * @return the VE_PROV.
    */
   public String getVeProv() ;
   
   /**
    * Sets the the VE_PROV.  A "" indicates that this field is not in use.
    *
    * @param veProv the VE_PROV.
    */
   public void setVeProv(String veProv) ;
   
}
