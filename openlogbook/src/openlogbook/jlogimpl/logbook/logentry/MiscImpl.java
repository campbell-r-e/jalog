package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Misc;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;

/**
 * An implementation of the Misc interface.
 * 
 * @author KC0ZPS
 */
public class MiscImpl implements Misc {

   private String _comment = new String() ;
   private String _arrlSection = new String() ;
   private String _notes = new String() ;
   private String _wpxPrefix = new String() ;
   private String _tenTen = new String() ;
   private String _veProv = new String() ;
   
   /**
    * Creates a new MiscImpl.
    */
   public MiscImpl() {
   }
   
   //**********
   // Methods that are inherited from Misc
   //**********

   /**
    * Gets the comment.  A "" indicates that this field is not in use.
    * 
    * @return the comment.
    */
   public String getComment() {
      return _comment ;
   }
   
   /**
    * Sets the comment.  A "" indicates that this field is not in use.
    * 
    * @param comment the comment.
    */
   public void setComment(String comment) {
      _comment = comment ;
   }

   /**
    * Gets the ARRL Section.  A "" indicates that this field is not in use.
    * 
    * @return the ARRL Section.
    */
   public String getArrlSection() {
      return _arrlSection ;
   }
   
   /**
    * Sets the ARRL Section.  A "" indicates that this field is not in use.
    * 
    * @param arrlSection the ARRL Section.
    */
   public void setArrlSection(String arrlSection) {
      _arrlSection = arrlSection ;
   }
   
   /**
    * Gets the long text for digital copy, third party traffic, etc.
    * A "" indicates that this field is not in use.
    * 
    * @return the long text for digital copy, third party traffic, etc.
    */
   public String getNotes() {
      return _notes ;
   }
   
   /**
    * Sets the long text for digital copy, third party traffic, etc.
    * A "" indicates that this field is not in use.
    * 
    * @param notes the long text for digital copy, third party traffic, etc.
    */   
   public void setNotes(String notes) {
      _notes = notes ;
   }
   
   /**
    * Gets the WPX Prefix.  A "" indicates that this field is not in use.
    * 
    * @return the WPX Prefix.
    */
   public String getWpxPrefix() {
      return _wpxPrefix ;
   }
   
   /**
    * Sets the WPX Prefix.  A "" indicates that this field is not in use.
    * 
    * @param wpxPrefix the WPX Prefix.
    */
   public void setWpxPrefix(String wpxPrefix) {
      _wpxPrefix = wpxPrefix ;
   }
   
   /**
    * Gets the Ten Ten identifier.  A "" indicates that this field is not in use.
    * 
    * @return the Ten Ten identifier.
    */
   public String getTenTen() {
      return _tenTen ;
   }
   
   /**
    * Sets the Ten Ten identifier.  A "" indicates that this field is not in use.
    * 
    * @param tenTen the Ten Ten identifier.
    */
   public void setTenTen(String tenTen) {
      _tenTen = tenTen ;
   }
   
   /**
    * Gets the VE_PROV.  A "" indicates that this field is not in use.
    * 
    * @return the VE_PROV.
    */
   public String getVeProv() {
      return _veProv ;
   }
   
   /**
    * Sets the the VE_PROV.  A "" indicates that this field is not in use.
    *
    * @param veProv the VE_PROV.
    */
   public void setVeProv(String veProv) {
      _veProv = veProv ;
   }
   
   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      if(!_comment.equals("")) {
         buffer.append("<" + AdifConstants.COMMENT + ":" + _comment.length() + ">" + _comment) ;         
      }

      if(!_arrlSection.equals("")) {
         buffer.append("<" + AdifConstants.ARRL_SECT + ":" + _arrlSection.length() + ">" + _arrlSection) ;         
      }

      if(!_notes.equals("")) {
         buffer.append("<" + AdifConstants.NOTES + ":" + _notes.length() + ">" + _notes) ;         
      }

      if(!_wpxPrefix.equals("")) {
         buffer.append("<" + AdifConstants.PFX + ":" + _wpxPrefix.length() + ">" + _wpxPrefix) ;         
      }

      if(!_tenTen.equals("")) {
         buffer.append("<" + AdifConstants.TEN_TEN + ":" + _tenTen.length() + ">" + _tenTen) ;         
      }

      if(!_veProv.equals("")) {
         buffer.append("<" + AdifConstants.VE_PROV + ":" + _veProv.length() + ">" + _veProv) ;         
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
      buffer.append(" Comment: " + _comment + "\n") ;
      buffer.append(" ARRL Section: " + _arrlSection + "\n") ;
      buffer.append(" Notes: " + _notes + "\n") ;
      buffer.append(" WPX Prefix: " + _wpxPrefix + "\n") ;
      buffer.append(" Ten-Ten: " + _tenTen + "\n") ;
      buffer.append(" VE Prov: " + _veProv + "\n") ;
      
      return buffer.toString() ;
   }

}
