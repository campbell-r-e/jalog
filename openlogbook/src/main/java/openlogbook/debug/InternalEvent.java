package openlogbook.debug;

import java.io.Serializable;
import java.util.Date;

/**
 * An InternalEvent is generated when the API implementation detects an unexpected problem.
 */
public interface InternalEvent extends Serializable {

   /**
    * Gets the date when the event occurred.
    *
    * @return the date when the event occurred
    */
   public Date getDate() ;

   /**
    * Gets the numeric code associated with the event.
    *
    * @return the numeric code associated with the event
    */
   public int getEventCode() ;

   /**
    * Gets a text description of the event.
    *
    * @return a text description of the event
    */
   public String getEventDescription() ;

   /**
    * Gets an additional message associated with the event.
    *
    * @return an additional message associated with the event
    */
   public String getExtendedMessage() ;

   /**
    * Gets the file name of the log book that the event was associated with.
    *
    * @return the file name of the log book that the event was associated with.
    */
   public String getFileName() ;

}


// End of InternalEvent.java
