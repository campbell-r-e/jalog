package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.jlog.logbook.jlogentry.Era;
import openlogbook.jlogimpl.filehandler.adif.AdifConstants;
import openlogbook.util.Utility;

import java.util.Date;


/**
 * An implementation of the Era interface.
 * 
 * @author KC0ZPS
 */
public class EraImpl implements Era {

   private Date    _startDate ;
   private Date    _endDate ;
   /**
    * Creates a new EraImpl.
    */
   public EraImpl() {
   }
   
   //**********
   // Methods that are inherited from Era
   //**********

   /**
    * Gets the starting Date when contact occurred.  null if not used.
    * 
    * @return the starting Date when contact occurred.
    */
   public Date getStartDate() {
      return _startDate ;
   }
   
   /**
    * Sets the starting Date on when contact occurred.  null if not used.
    * 
    * @param date the starting Date on when contact occurred.
    */
   public void setStartDate(Date date) {
      _startDate = date ;
   }
   
   /**
    * The contact end time.  null if not used.
    * 
    * @return The contact end time.
    */
   public Date getEndDate() {
      return _endDate ;
   }
   
   /**
    * Sets The contact end time.  null if not used.
    * 
    * @param date The contact end time.
    */
   public void setEndDate(Date date) {
      _endDate = date ;
   }
   
   /**
    * Returns a String repesentation of the start date containing only the Date.
    * 
    * @return a String repesentation of the start date containing only the Date.
    */
   public String getStartDateString() {
      if (_startDate == null) {
         return "" ;
      }
      return Utility.getDateFormat().format(_startDate) ;
   }
   
   /**
    * Returns a String repesentation of the start date containing only the time.
    * 
    * @return a String repesentation of the start date containing only the time.
    */
   public String getStartTimeString() {            
      if (_startDate == null) {
         return "" ;
      }
      return Utility.getTimeFormat().format(_startDate) ;
   }
   
   /**
    * Returns a String repesentation of the end date containing only the time.
    * 
    * @return a String repesentation of the end date containing only the time.
    */
   public String getEndTimeString() {
      if (_endDate == null) {
         return "" ;
      }
      return Utility.getTimeFormat().format(_endDate) ;
   }
   
   /**
    * Returns an ADIF formatted String.
    * 
    * @return an ADIF formatted String.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;

      if (_startDate != null) {
         String date = Utility.getAdifDateFormat().format(_startDate) ;
         buffer.append("<" + AdifConstants.QSO_DATE + ":" + date.length() + ">" + date) ;         

         date = Utility.getAdifTimeFormat().format(_startDate) ;
         buffer.append("<" + AdifConstants.TIME_ON + ":" + date.length() + ">" + date) ;
      }

      if (_endDate != null) {
         String date = Utility.getAdifTimeFormat().format(_endDate) ;
         buffer.append("<" + AdifConstants.TIME_OFF + ":" + date.length() + ">" + date) ;
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
      buffer.append(" Start Date: " + getStartDateString() + " " + getStartTimeString() + "\n") ;
      buffer.append(" End Time: " + getEndTimeString() + "\n") ;
      
      return buffer.toString() ;
   }
}
