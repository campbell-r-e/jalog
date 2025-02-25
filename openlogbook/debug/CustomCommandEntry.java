package openlogbook.debug;

/**
 * Class to support Debug custom commands. A CustomCommandEntry object contains
 * information necessary to describe an individual custom command.  .
 */
public class CustomCommandEntry {

   /**
    * Creates a new CustomCommandEntry.
    *
    * @param description     description of the custom command
    * @param parameterNames  list of the custom command parameters
    * @param command         command handler for this command
    *
    */
   CustomCommandEntry(String description, String parameterNames, CustomCommand command) {
      _description = description ;
      _parameterNames = parameterNames ;
      _command = command ;
   }

   //**********
   // Methods overridden from Object
   //**********

   /**
    * Returns a string representation of this object.
    *
    * @return a string representation of this object
    */
   public String toString() {
      return _description ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the CustomCommand object.
    *
    * @return the CustomCommand object
    */
   public CustomCommand getCommand() {
      return _command ;
   }


   /**
    * Gets the Description of the command.
    *
    *
    * @return    The Description of the command
    */
   public String getDescription() {
      return _description ;
   }

   /**
    * Gets the names of the parameters. Required parameters should end
    * with the '*' character.
    *
    * @return    The names of the parameters
    */
   public String getParameterNames() {
      return _parameterNames ;
   }


   //**********
   // Class attributes and constants
   //**********

   private String          _description ;
   private String          _parameterNames ;
   private CustomCommand   _command ;

}



// End of CustomCommandEntry.java

