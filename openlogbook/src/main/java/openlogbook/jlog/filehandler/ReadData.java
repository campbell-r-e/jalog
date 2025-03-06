package openlogbook.jlog.filehandler;

import openlogbook.jlog.logbook.LogBook;

/**
 * This interface defines the API that will allow the reading of logbook data from an unspecified source. 
 * 
 * @author KC0ZPS
 */
public interface ReadData {

   /**
    * Executes the read command.
    * 
    * @param logBook The logbook that will contain the data.
    * 
    * @throws Exception If something goes wrong with the reading of the data.
    */
   public void execute(LogBook logBook) throws Exception ;
   
}
