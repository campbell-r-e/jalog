package jalog.jlogimpl.filehandler.xmlfilehandler;

import jalog.jlog.filehandler.SaveData;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.ContactAddress;
import jalog.jlog.logbook.jlogentry.ContactStationInformation;
import jalog.jlog.logbook.jlogentry.Era;
import jalog.jlog.logbook.jlogentry.FrequencyInformation;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.logbook.jlogentry.Misc;
import jalog.jlog.logbook.jlogentry.Qsl;
import jalog.jlog.logbook.jlogentry.Rst;
import jalog.jlog.util.Band;
import jalog.jlog.util.IotaEnum;
import jalog.jlog.util.Mode;
import jalog.jlog.util.QslRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.ecs.xml.XML;
import org.apache.ecs.xml.XMLDocument;




/**
 * Saves a LogBook to an XML file.
 * 
 * @author KC0ZPS
 * 
 * @deprecated Use ADIF Instead.
 */
public class SaveXmlFile implements SaveData {
  
   private File    _file ;
   private LogBook _logBook ;
   
   /**
    * Creates a new instance of a SaveXmlFile.
    * 
    * @param file The file to save to.
    * @param logBook The log book object that will be saved to the file.
    */
   public SaveXmlFile(File file, LogBook logBook) {
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
      XMLDocument document = new XMLDocument() ;
      
      XML logBookXml = new XML("LogBook") ; 

      logBookXml.addAttribute(XmlConstants.Version.toString(), _logBook.getVersion()) ;
      
      ArrayList<LogEntry> list = _logBook.getLogEntries() ;
      for (int index = 0; index < list.size(); index++) {
         LogEntry entry = list.get(index) ;
         
         logBookXml.addElement(createLogEntry(entry)) ;
      }
      
      document.addElement(logBookXml) ;         
      
      FileOutputStream stream = new FileOutputStream(_file) ;
      
      System.out.println("Saving to " + _file.getAbsolutePath()) ;
      
      document.output(stream) ;
      
      // document.output(System.out) ;
   }

   //**********
   // Private methods
   //**********

   /**
    * Creates an XML object from a LogEntry.
    * 
    * @param entry The log entry to create the XML object from.
    * 
    * @return An XML object representing the log entry.
    */
   private XML createLogEntry(LogEntry entry) {
      XML logEntryXml = new XML("LogEntry") ;
      
      // Station
      XML node = new XML(XmlConstants.Station.toString()) ;
      node.addElement(entry.getCallsign().getContactStation()) ;
      logEntryXml.addElement(node) ;
      
      // Date
      Era era = entry.getEra() ;
      node = new XML(XmlConstants.StartDate.toString()) ;
      node.addElement("" + era.getStartDate().getTime()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.EndDate.toString()) ;
      node.addElement("" + era.getEndDate().getTime()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.SameAsStart.toString()) ;
      // node.addElement(new Boolean(era.isDateSameAsStart()).toString()) ;
      logEntryXml.addElement(node) ;

      // Frequency Information
      FrequencyInformation freqInfo = entry.getFrequencyInformation() ;
      node = new XML(XmlConstants.Mode.toString()) ;
      int value = Mode.getIntToModeType().getIntValue(freqInfo.getMode()) ;
      node.addElement("" + value) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Band.toString()) ;
      value = Band.getIntToBandType().getIntValue(freqInfo.getBand()) ; 
      node.addElement("" + value) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Frequency.toString()) ;
      node.addElement(freqInfo.getFrequency()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Power.toString()) ;
      node.addElement("" + freqInfo.getTxPower()) ;
      logEntryXml.addElement(node) ;
      
      // Misc
      Misc misc = entry.getMisc() ;
      node = new XML(XmlConstants.Locator.toString()) ;
      // node.addElement(misc.getLocator()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Comment.toString()) ;
      node.addElement(misc.getComment()) ;
      logEntryXml.addElement(node) ;

      // RST
      Rst rst = entry.getRst() ;
      node = new XML(XmlConstants.RstReceived.toString()) ;
      node.addElement(rst.getRstReceived()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.RstSent.toString()) ;
      node.addElement(rst.getRstSent()) ;
      logEntryXml.addElement(node.toString()) ;

      // Address
      ContactAddress contactAddress = entry.getContactAddress() ;
      node = new XML(XmlConstants.County.toString()) ;
      node.addElement(contactAddress.getCounty()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Name.toString()) ;
      node.addElement(contactAddress.getName()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.Address.toString()) ;
      node.addElement(contactAddress.getAddress()) ;
      logEntryXml.addElement(node) ;
      
      // Basic Contact Station Information
      ContactStationInformation contactStationInformation = entry.getContactStationInformation() ;
      node = new XML(XmlConstants.QTH.toString()) ;
      node.addElement(contactStationInformation.getQth()) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.IOTA1.toString()) ;
      IotaEnum iotaEnum = contactStationInformation.getIota().getIotaEnum() ;
      value = IotaEnum.getIntToIotaType().getIntValue(iotaEnum) ;
      node.addElement("" + value) ;
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.IOTA2.toString()) ;
      node.addElement("" + contactStationInformation.getIota().getValue()) ;
      logEntryXml.addElement(node) ;         
      
      // QSL
      Qsl qsl = entry.getQsl() ;
      node = new XML(XmlConstants.QslSent.toString()) ;
      if(qsl.isQslSent().equals(QslRequest.Yes)) {
         node.addElement("true") ;            
      } else if(qsl.isQslSent().equals(QslRequest.Requested)) {
         node.addElement("true") ;                        
      } else {
         node.addElement("false") ;            
      }
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.QslSentDate.toString()) ;
      node.addElement("" + qsl.getQslSentDate().getTime()) ;
      logEntryXml.addElement(node) ;
         
      node = new XML(XmlConstants.QslReceived.toString()) ;
      if(qsl.isQslReceived().equals(QslRequest.Yes)) {
         node.addElement("true") ;            
      } else if(qsl.isQslSent().equals(QslRequest.Requested)) {
         node.addElement("true") ;                        
      } else {
         node.addElement("false") ;            
      }
      logEntryXml.addElement(node) ;

      node = new XML(XmlConstants.QslReceivedDate.toString()) ;
      node.addElement("" + qsl.getQslReceivedDate().getTime()) ;
      logEntryXml.addElement(node) ;
      
      return logEntryXml ;
   }
   
}
