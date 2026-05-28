package jalog.factory;

import jalog.jlog.filehandler.ReadData;
import jalog.jlog.filehandler.SaveData;
import jalog.jlog.logbook.LogBook;
import jalog.jlogimpl.filehandler.adif.ReadAdifFile;
import jalog.jlogimpl.filehandler.adif.WriteAdifFile;

import java.io.File;

/**
 * A factory class that creates ADIF File classes. 
 * 
 * @author KC0ZPS
 */
public class AdifFileFactory {

   /**
    * Creates a new AdifFileFactory.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    */
   private AdifFileFactory() {
   }

   /**
    * Creates a ReadData object.
    * 
    * @param file The adif file to parse.
    * 
    * @return a ReadAdifFile.
    */
   public static ReadData createReadAdifFile(File file) {
      return new ReadAdifFile(file) ;
   }
   
   /**
    * Creates a SaveData object.
    * 
    * @param file The file to save to.
    * @param logBook The log book object that will be saved to the file.
    * 
    * @return a WriteAdifFile.
    */
   public static SaveData createWriteAdifFile(File file, LogBook logBook) {
      return new WriteAdifFile(file, logBook) ;
   }

}
