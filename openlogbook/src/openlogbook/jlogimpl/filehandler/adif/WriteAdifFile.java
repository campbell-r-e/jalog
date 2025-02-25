package openlogbook.jlogimpl.filehandler.adif;

import openlogbook.jlog.filehandler.SaveData;
import openlogbook.jlog.logbook.LogBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Writes to a file in ADIF format.
 * 
 * @author KC0ZPS
 */
public class WriteAdifFile implements SaveData {

   private File    _file ;
   private LogBook _logBook ;
   
   /**
    * Creates a new instance of a SaveXmlFile.
    * 
    * @param file The file to save to.
    * @param logBook The log book object that will be saved to the file.
    */
   public WriteAdifFile(File file, LogBook logBook) {
      _file = file ;
      _logBook = logBook ;
   }
   
   
   //**********
   // Methods inherited from SaveData
   //**********

   /**
    * Executes the save command.
    * 
    * @throws FileNotFoundException if the file cannot be found.  This is needed because this
    * method uses FileOutputStream.
    */
   public void execute() throws FileNotFoundException {
      
      
      FileOutputStream fOutStream = new FileOutputStream(_file);
      PrintWriter printWriter = new PrintWriter(fOutStream, true);

      printWriter.println(_logBook.toAdifString()) ;
      
      printWriter.flush();
      printWriter.close();
   }
   
   //**********
   // Private methods
   //**********
   
}
