package openlogbook.jlogimpl.filehandler.adif;

import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.filehandler.ReadData;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.util.Band;
import openlogbook.jlog.util.IotaEnum;
import openlogbook.jlog.util.Mode;
import openlogbook.jlog.util.QslRequest;
import openlogbook.util.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Reads an ADIF formatted file.
 * 
 * @author KC0ZPS
 */
public class ReadAdifFile implements ReadData {

   private File     _file ;
   
   /**
    * Creates a new ReadAdifFile.
    * 
    * @param file The file to read from.
    */
   public ReadAdifFile(File file) {
      _file = file ;
   }
   
   /**
    * Executes the read command.
    * 
    * @param logBook The logbook that will contain the data.
    * 
    * @throws Exception If something goes wrong with the reading of the data.
    */
   public void execute(LogBook logBook) throws Exception {
      FileReader fileReader = new FileReader(_file);
      BufferedReader  bufferedReader = new BufferedReader (fileReader);
      
      int intValue ;
      boolean creatingToken = false ;
      boolean creatingSize = false ;
      StringBuffer token = new StringBuffer() ;
      StringBuffer size = new StringBuffer() ;
      LogEntry logEntry = DefaultLogBookFactory.createLogEntry() ;
      while((intValue = bufferedReader.read()) != -1) {
         char charValue = (char)intValue;
         
         if (charValue == '\n') {
            continue ; 
         }
         
         if (charValue == '<') {
            creatingToken = true ;
            continue ;
         }

         if (charValue == ':') {
            creatingToken = false ;
            creatingSize = true ;
            continue ;
         }
         
         if (charValue == '>') {
            if (token.toString().toLowerCase().equals("eor")) {
               logBook.addEntry(logEntry) ;
               logEntry = DefaultLogBookFactory.createLogEntry() ;
               token = new StringBuffer() ;
               size = new StringBuffer() ;
               continue ;
            }
            
            creatingSize = false ;
            createData(bufferedReader, token.toString(), new Integer(size.toString()).intValue(), logEntry) ;
            size = new StringBuffer() ;
            token = new StringBuffer() ;
         }
         
         if (creatingToken) {
            token.append(charValue) ;            
         }
         
         if (creatingSize) {
            size.append(charValue) ;
         }
      }
   }
   
   /**
    * Puts the data following the identifier into the log book.
    * e.g., <QSO_DATE:4>data
    * At this point, the parser has assumed that its starting to read the "data" portion of the entry.
    * 
    * @param bufferedReader The input stream. 
    * @param token The field identifier.
    * @param size The size of the data.
    * @param logEntry The logEntry that will contain the data.
    * 
    * @throws IOException If something goes wrong with reading the file.
    */
   private void createData(BufferedReader bufferedReader, String token, int size, LogEntry logEntry) 
   throws IOException {
      char[] cbuf = new char[size] ;
      int value = bufferedReader.read(cbuf, 0, size) ;
      if (value == -1) {
         throw new IOException("EOF read while reading data for " + token + " where size is " + size + ".") ;
      }
      
      String data = new String(cbuf) ;
      assignData(token, data, logEntry) ;
   }
   
   /**
    * This method compares the token value and determines where to put that data.
    * 
    * @param token The token (or field) type of the data.
    * @param data The data following the field.
    * @param logEntry The logEntry that will hold the data.
    */
   private void assignData(String token, String data, LogEntry logEntry) {
      if (token.toUpperCase().equals(AdifConstants.ADDRESS.toString().toUpperCase())) {
         logEntry.getContactAddress().setAddress(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.AGE.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setAge(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.ARRL_SECT.toString().toUpperCase())) {
         logEntry.getMisc().setArrlSection(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.BAND.toString().toUpperCase())) {
         Band band = (Band)Band.getStrToBandType().getObjectValue(data.toLowerCase()) ;
         logEntry.getFrequencyInformation().setBand(band) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.CALL.toString().toUpperCase())) {
         logEntry.getCallsign().setContactStation(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.CNTY.toString().toUpperCase())) {
         logEntry.getContactAddress().setCounty(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.COMMENT.toString().toUpperCase())) {
         logEntry.getMisc().setComment(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.CONT.toString().toUpperCase())) {
         logEntry.getContactAddress().setContinent(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.CONTEST_ID.toString().toUpperCase())) {
         logEntry.getContest().setContestId(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.CQZ.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setCqZone(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.DXCC.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setDxcc(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.FREQ.toString().toUpperCase())) {
         logEntry.getFrequencyInformation().setFrequency(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.GRIDSQUARE.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setGridSquare(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.IOTA.toString().toUpperCase())) {
         StringTokenizer tokenizer = new StringTokenizer(data, "-") ;
         String iotaToken = tokenizer.nextToken().toUpperCase() ;
         String valueToken = tokenizer.nextToken() ;
         IotaEnum iotaEnum = (IotaEnum)IotaEnum.getStrToIotaType().getObjectValue(iotaToken) ;
         Iota iota = DefaultLogBookFactory.createIota(iotaEnum, new Integer(valueToken).intValue()) ;
         if (iotaEnum.equals(IotaEnum.Unknown)) {
            System.out.println("Unknown iota value " + iotaToken) ;
            iota.setUnknownIota(iotaToken) ;
         }
         logEntry.getContactStationInformation().setIota(iota) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.ITUZ.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setItuZone(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.MODE.toString().toUpperCase())) {
         String modeValue = data.toUpperCase() ;
         Mode mode = (Mode)Mode.getStrToModeType().getObjectValue(modeValue) ; 
         if (mode.equals(Mode.Unknown)) {
            System.out.println("Unknown mode value " + data) ;
            logEntry.getFrequencyInformation().setUnknownMode(data) ;
         }
         logEntry.getFrequencyInformation().setMode(mode) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.NAME.toString().toUpperCase())) {
         logEntry.getContactAddress().setName(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.NOTES.toString().toUpperCase())) {
         logEntry.getMisc().setNotes(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.OPERATOR.toString().toUpperCase())) {
         logEntry.getCallsign().setOperatingStation(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.PFX.toString().toUpperCase())) {
         logEntry.getMisc().setWpxPrefix(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.PROP_MODE.toString().toUpperCase())) {
         logEntry.getSatellite().setPropMode(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSL_RCVD.toString().toUpperCase())) {
         String qslValue = data.toUpperCase() ;
         QslRequest qslRequest = (QslRequest)QslRequest.getStrToQslRequestType().getObjectValue(qslValue) ; 
         if (qslRequest.equals(QslRequest.Unknown)) {
            System.out.println("Unknown qsl_rcvd value " + data) ;
            logEntry.getQsl().setUnknownQslReceived(data) ;
         }
         logEntry.getQsl().setQslReceived(qslRequest) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSL_SENT.toString().toUpperCase())) {
         String qslValue = data.toUpperCase() ;
         QslRequest qslRequest = (QslRequest)QslRequest.getStrToQslRequestType().getObjectValue(qslValue) ; 
         if (qslRequest.equals(QslRequest.Unknown)) {
            System.out.println("Unknown qsl_sent value " + data) ;
            logEntry.getQsl().setUnknownQslSent(data) ;
         }
         logEntry.getQsl().setQslSent(qslRequest) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSL_VIA.toString().toUpperCase())) {
         logEntry.getQsl().setQslVia(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSLMSG.toString().toUpperCase())) {
         logEntry.getQsl().setQslMessage(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSLRDATE.toString().toUpperCase())) {
         Date date = logEntry.getQsl().getQslReceivedDate() ;
         if (date == null) {
            date = new Date() ;
         }
         logEntry.getQsl().setQslReceivedDate(getDate(data, date)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSLSDATE.toString().toUpperCase())) {
         Date date = logEntry.getQsl().getQslSentDate() ;
         if (date == null) {
            date = new Date() ;
         }
         logEntry.getQsl().setQslSentDate(getDate(data, date)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QSO_DATE.toString().toUpperCase())) {
         Date date = logEntry.getEra().getStartDate() ;
         if (date == null) {
            date = new Date() ;
         }
         logEntry.getEra().setStartDate(getDate(data, date)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.QTH.toString().toUpperCase())) {
         logEntry.getContactStationInformation().setQth(data) ;         
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.RST_RCVD.toString().toUpperCase())) {
         logEntry.getRst().setRstReceived(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.RST_SENT.toString().toUpperCase())) {
         logEntry.getRst().setRstSent(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.RX_PWR.toString().toUpperCase())) {
         logEntry.getFrequencyInformation().setRxPower(new Integer(data)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.SAT_MODE.toString().toUpperCase())) {
         logEntry.getSatellite().setSatelliteMode(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.SAT_NAME.toString().toUpperCase())) {
         logEntry.getSatellite().setSatelliteName(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.SRX.toString().toUpperCase())) {
         logEntry.getContest().setReceivedSerialNumber(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.STATE.toString().toUpperCase())) {
         logEntry.getContactAddress().setUsState(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.STX.toString().toUpperCase())) {
         logEntry.getContest().setTransmittedSerialNumber(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.TEN_TEN.toString().toUpperCase())) {
         logEntry.getMisc().setTenTen(data) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.TIME_OFF.toString().toUpperCase())) {
         Date date = logEntry.getEra().getEndDate() ;
         if (date == null) {
            date = new Date() ;
         }
         logEntry.getEra().setEndDate(getTime(data, date)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.TIME_ON.toString().toUpperCase())) {
         Date date = logEntry.getEra().getStartDate() ;
         if (date == null) {
            date = new Date() ;
         }
         logEntry.getEra().setStartDate(getTime(data, date)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.TX_PWR.toString().toUpperCase())) {
         logEntry.getFrequencyInformation().setTxPower(new Integer(data)) ;
         return ;
      }
      if (token.toUpperCase().equals(AdifConstants.VE_PROV.toString().toUpperCase())) {
         logEntry.getMisc().setVeProv(data) ;
         return ;
      }
      
      System.out.println(token + " not found.  Data = " + data) ;
   }
   
   /**
    * Returns a YYYYMMDD formatted string as a Date.
    * 
    * @param dateString a string in the format of YYYYMMDD.
    * @param date a date object to base the creation of the new date object on.  The new
    *  date object will replace the year, month, and year but will keep all other
    *  values (hour, minute, and seconds).
    * 
    * @return a Date.
    */
   private Date getDate(String dateString, Date date) {
      int year = new Integer(dateString.substring(0, 4)).intValue() ;
      // According to the GregorianCalendar javadoc, the month is 0 based.
      int month = new Integer(dateString.substring(4, 6)).intValue() - 1 ;
      int dayOfMonth = new Integer(dateString.substring(6, 8)).intValue() ;
      GregorianCalendar calendar = new GregorianCalendar() ;
      calendar.setTimeZone(Utility.getUtcTimeZone()) ;
      calendar.setTime(date) ;
      calendar.set(Calendar.YEAR, year) ;
      calendar.set(Calendar.MONTH, month) ;
      calendar.set(Calendar.DATE, dayOfMonth) ;
      return calendar.getTime() ;
   }
   
   /**
    * Translates a string in a HHMM or HHMMSS format into a Date format.
    * 
    * @param timeString The string in the format of HHMM or HHMMSS format.
    * @param date a date object to base the creation of the new date object on.  The new
    *  date object will replace the hour, minute, and second but will keep all other
    *  values (year, month, and day of month).
    * 
    * @return a Date.
    */
   private Date getTime(String timeString, Date date) {
      int hour = new Integer(timeString.substring(0, 2)).intValue() ;
      int minute = new Integer(timeString.substring(2, 4)).intValue() ;
      int second = 0;
      if (timeString.length() > 4) {
         second = new Integer(timeString.substring(4, 6)).intValue() ;
      }
      GregorianCalendar calendar = new GregorianCalendar() ;
      calendar.setTimeZone(Utility.getUtcTimeZone()) ;
      calendar.setTime(date) ;
      calendar.set(Calendar.HOUR_OF_DAY, hour) ;
      calendar.set(Calendar.MINUTE, minute) ;
      calendar.set(Calendar.SECOND, second) ;
      return calendar.getTime() ;
   }
}
