package openlogbook.tester;

import java.io.File;
import java.util.Date;
import java.util.Random;

import openlogbook.factory.AdifFileFactory;
import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.filehandler.SaveData;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.util.Band;
import openlogbook.jlog.util.IotaEnum;
import openlogbook.jlog.util.Mode;
import openlogbook.jlog.util.QslRequest;

/**
 * This is a simple application that creates a random ADIF file. 
 * 
 * @author KC0ZPS
 */
public class LogCreator {

   private LogBook _logBook ;
   private File    _file = new File("testlog.adi") ;
   private Random  _random = new Random(new Date().getTime());

   private static final int LOG_SIZE = 8000 ;
   
   /**
    * Creates a new LogCreator.
    */
   public LogCreator() {
      System.out.println("Generating...");
      _logBook = DefaultLogBookFactory.createLogBook("ADIF 1.0") ;
      for (int index = 0; index < LOG_SIZE; index++) {
         _logBook.addEntry(createLogEntry(index)) ;
      }
      save() ;
      System.out.println("Done.");
   }
   
   /**
    * Creates a single random log entry.
    * 
    * @param index the number of entries so far.
    * 
    * @return a random log entry.
    */
   private LogEntry createLogEntry(int index) {
      LogEntry logEntry = DefaultLogBookFactory.createLogEntry() ;
      logEntry.getCallsign().setContactStation("CONTACT" + index) ;
      logEntry.getCallsign().setOperatingStation("STATION" + index) ;
      
      logEntry.getContactAddress().setAddress(index + " fake street") ;
      logEntry.getContactAddress().setContinent(index + " continent") ;
      logEntry.getContactAddress().setCounty(index + " count") ;
      logEntry.getContactAddress().setName(index + " name") ;
      logEntry.getContactAddress().setUsState(index + " state") ;
      
      logEntry.getContactStationInformation().setAge("" + index) ;
      logEntry.getContactStationInformation().setCqZone(index + " cqz") ;
      logEntry.getContactStationInformation().setDxcc(index + " dxcc") ;
      logEntry.getContactStationInformation().setGridSquare(index + " gridsquare") ;
      IotaEnum iotaEnum = generateRandomIotaEnum() ;
      if (!iotaEnum.equals(IotaEnum.Blank)) {
         Iota iota = DefaultLogBookFactory.createIota(iotaEnum, index) ;
         logEntry.getContactStationInformation().setIota(iota) ;         
      }
      logEntry.getContactStationInformation().setItuZone(index + " ituz") ;
      logEntry.getContactStationInformation().setQth(index + " qth") ;
      
      logEntry.getContest().setContestId(index + " contestid") ;
      logEntry.getContest().setReceivedSerialNumber("" + index) ;
      logEntry.getContest().setTransmittedSerialNumber("" + index) ;
      
      logEntry.getEra().setEndDate(new Date()) ;
      logEntry.getEra().setStartDate(new Date()) ;
      
      Band band = generateRandomBand() ;
      if (!band.equals(Band.Blank)) {
         logEntry.getFrequencyInformation().setBand(band) ;         
      }
      Mode mode = generateRandomMode() ;
      if (!mode.equals(Mode.Blank)) {
         logEntry.getFrequencyInformation().setMode(mode) ;                  
      }
      logEntry.getFrequencyInformation().setFrequency(index + "freq") ;
      logEntry.getFrequencyInformation().setRxPower(index) ;
      logEntry.getFrequencyInformation().setTxPower(index + 10) ;

      logEntry.getMisc().setArrlSection(index + " arrl") ;
      logEntry.getMisc().setComment(index + " comment") ;
      logEntry.getMisc().setNotes(index + " note") ;
      logEntry.getMisc().setTenTen(index + " tenten") ;
      logEntry.getMisc().setVeProv(index + " veprov") ;
      logEntry.getMisc().setWpxPrefix(index + " wpxpre") ;
      
      logEntry.getQsl().setQslMessage(index + " qsl message") ;
      QslRequest qslSent = generateRandomQslRequest() ;
      if (!qslSent.equals(QslRequest.Blank)) {
         logEntry.getQsl().setQslSent(qslSent) ;
      }
      QslRequest qslReceived = generateRandomQslRequest() ;
      if (!qslReceived.equals(QslRequest.Blank)) {
         logEntry.getQsl().setQslReceived(qslReceived) ;
      }
      logEntry.getQsl().setQslReceivedDate(new Date()) ;
      logEntry.getQsl().setQslSentDate(new Date()) ;
      logEntry.getQsl().setQslVia(index + " qslvia") ;

      logEntry.getRst().setRstReceived("" + index) ;
      logEntry.getRst().setRstSent("" + index + 20) ;
      
      logEntry.getSatellite().setPropMode(index + " propmode") ;
      logEntry.getSatellite().setSatelliteMode(index + " sat mode") ;
      logEntry.getSatellite().setSatelliteName(index + " sat name") ;
      
      return logEntry ;
   }
   
   
   /**
    * Generates a random qsl request.
    * 
    * @return a random qsl request.
    */
   private QslRequest generateRandomQslRequest() {
      QslRequest[] qslRequests = QslRequest.getValues() ;
      QslRequest qslRequest = qslRequests[_random.nextInt(qslRequests.length)] ;
      return qslRequest ;         
   }
   
   /**
    * Generates a random Mode.
    * 
    * @return a random Mode.
    */
   private Mode generateRandomMode() {
      Mode[] modes = Mode.getValues() ;
      Mode mode = modes[_random.nextInt(modes.length)] ;
      return mode ;         
   }
   
   /**
    * Generates a random Band.
    * 
    * @return a random Band.
    */
   private Band generateRandomBand() {
      Band[] bands = Band.getValues() ;
      Band band = bands[_random.nextInt(bands.length)] ;     
      return band ;         
   }
   
   
   /**
    * Generates a random IotaEnum.
    * 
    * @return a random IotaEnum.
    */
   private IotaEnum generateRandomIotaEnum() {
      IotaEnum[] iotaEnums = IotaEnum.getValues() ;
      IotaEnum iotaEnum = iotaEnums[_random.nextInt(iotaEnums.length)] ;
      return iotaEnum ;         
   }
   
   /**
    * Save the file.
    */
   private void save() {
      SaveData saveData = AdifFileFactory.createWriteAdifFile(_file, _logBook) ;
      try {
         saveData.execute() ;  
      } catch (Exception e) {
         e.printStackTrace() ;
      }
   }

   
   /**
    * This applications main entry point.
    *
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      new LogCreator() ;
   }

}
