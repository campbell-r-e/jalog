package openlogbook.jlog.logbook.jlogentry;

import openlogbook.jlog.logbook.Adif;

import java.util.Date;


/**
 * An interface that defines the date API.
 * 
 * @author KC0ZPS
 */
public interface Era extends Adif {

   /**
    * Gets the starting Date when contact occurred.  null if not used.
    * 
    * @return the starting Date when contact occurred.
    */
   public Date getStartDate() ;
   
   /**
    * Sets the starting Date on when contact occurred.  null if not used.
    * 
    * @param date the starting Date on when contact occurred.
    */
   public void setStartDate(Date date) ;
   
   /**
    * The contact end time.  null if not used.
    * 
    * @return The contact end time.
    */
   public Date getEndDate() ;
   
   /**
    * Sets The contact end time.  null if not used.
    * 
    * @param date The contact end time.
    */
   public void setEndDate(Date date) ;
   
   /**
    * Returns a String repesentation of the start date containing only the Date.
    * 
    * @return a String repesentation of the start date containing only the Date.
    */
   public String getStartDateString() ;
   
   /**
    * Returns a String repesentation of the start date containing only the time.
    * 
    * @return a String repesentation of the start date containing only the time.
    */
   public String getStartTimeString() ;
   
   /**
    * Returns a String repesentation of the end date containing only the time.
    * 
    * @return a String repesentation of the end date containing only the time.
    */
   public String getEndTimeString() ;

}
