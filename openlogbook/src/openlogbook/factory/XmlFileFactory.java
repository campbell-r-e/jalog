package openlogbook.factory;

import openlogbook.jlog.filehandler.ReadData;
import openlogbook.jlog.filehandler.SaveData;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlogimpl.filehandler.xmlfilehandler.ParseXmlFile;
import openlogbook.jlogimpl.filehandler.xmlfilehandler.SaveXmlFile;

import java.io.File;


/**
 * This is a factory singleton class that creates the xml file objects.
 * 
 * @author KC0ZPS
 * 
 * @deprecated Use ADIF Instead.
 */
public class XmlFileFactory {

   /**
    * Creates a new XmlFileFactory.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    */
   private XmlFileFactory() {
   }
   
   /**
    * Creates a SaveXmlFile.
    * 
    * @param file The file to save to.
    * @param logBook The logbook that will be saved.
    * 
    * @return A SaveData object.
    */
   public static SaveData createSaveXmlFile(File file, LogBook logBook) {
      return new SaveXmlFile(file, logBook) ;
   }
   
   /**
    * Creates a ParseXmlFile.
    * 
    * @param fileName The xml file to parse.
    * 
    * @return A ReadData object.
    */
   public static ReadData createParseXmlFile(String fileName) {
      return new ParseXmlFile(fileName) ;
   }
}
