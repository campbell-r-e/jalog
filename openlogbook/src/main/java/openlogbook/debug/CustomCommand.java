package openlogbook.debug ;

/**
 * Any class that processes a custom command must implement this interface.
 */
public interface CustomCommand {

   /**
    * Executes the custom command.
    *
    * @param parameters   Command parameters
    *
    */
   public void execute(String[] parameters) ;


}


// End of CustomCommand.java
