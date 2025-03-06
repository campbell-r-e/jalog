package openlogbook.jlog.filehandler;

/**
 * This interface defines the API that will allow the saving of logbook data. 
 * 
 * @author KC0ZPS
 */
public interface SaveData {

   /**
    * Executes the save command.
    * 
    * @throws Exception if something goes wrong with the save.
    */
   public void execute() throws Exception ;
   
}
