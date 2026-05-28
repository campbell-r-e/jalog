package jalog.factory;

import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.Callsign;
import jalog.jlog.logbook.jlogentry.ContactAddress;
import jalog.jlog.logbook.jlogentry.ContactStationInformation;
import jalog.jlog.logbook.jlogentry.Contest;
import jalog.jlog.logbook.jlogentry.Era;
import jalog.jlog.logbook.jlogentry.FrequencyInformation;
import jalog.jlog.logbook.jlogentry.Iota;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.logbook.jlogentry.Misc;
import jalog.jlog.logbook.jlogentry.Qsl;
import jalog.jlog.logbook.jlogentry.Rst;
import jalog.jlog.logbook.jlogentry.Satellite;
import jalog.jlog.util.IotaEnum;
import jalog.jlogimpl.logbook.LogBookImpl;
import jalog.jlogimpl.logbook.logentry.CallsignImpl;
import jalog.jlogimpl.logbook.logentry.ContactAddressImpl;
import jalog.jlogimpl.logbook.logentry.ContactStationInformationImpl;
import jalog.jlogimpl.logbook.logentry.ContestImpl;
import jalog.jlogimpl.logbook.logentry.EraImpl;
import jalog.jlogimpl.logbook.logentry.FrequencyInformationImpl;
import jalog.jlogimpl.logbook.logentry.IotaImpl;
import jalog.jlogimpl.logbook.logentry.LogEntryImpl;
import jalog.jlogimpl.logbook.logentry.MiscImpl;
import jalog.jlogimpl.logbook.logentry.QslImpl;
import jalog.jlogimpl.logbook.logentry.RstImpl;
import jalog.jlogimpl.logbook.logentry.SatelliteImpl;

/**
 * A factory class that creates default log book objects.
 * 
 * @author KC0ZPS
 */
public class DefaultLogBookFactory {

   /**
    * Creates a new DefaultLogBookFactory.  The constructor is private.  This object should not be instantiated.
    */
   private DefaultLogBookFactory() {}
   
   /**
    * Creates a LogBook.
    * 
    * @param version The version of this LogBook.
    * 
    * @return a LogBookImpl.
    */
   public static LogBook createLogBook(String version) {
      return new LogBookImpl(version) ;
   }
   
   /**
    * Creates a Callsign object.
    * 
    * @return a CallsignImp.
    */
   public static Callsign createCallsign() {
      return new CallsignImpl() ;
   }
   
   /**
    * Creates an Rst with pre-intialized values.
    * 
    * @param rstReceived The RST received.
    * @param rstSent The RST sent.
    * 
    * @return An RstImpl.
    */
   public static Rst createRst(String rstReceived, String rstSent) {
      return new RstImpl(rstReceived, rstSent) ;
   }
   
   /**
    * Creates an Rst with default values.
    * 
    * @return An RstImpl.
    */
   public static Rst createRst() {
      return new RstImpl() ;
   }

   /**
    * Creates a FrequencyInformation object.
    * 
    * @return A FrequencyInformationImpl.
    */
   public static FrequencyInformation createFrequencyInformation() {
      return new FrequencyInformationImpl() ;
   }
   
   /**
    * Creates a ContactAddress object.
    * 
    * @return an ContactAddressImpl.
    */
   public static ContactAddress createContactAddress() {
      return new ContactAddressImpl() ;
   }
 
   /**
    * Creates a ContactStationInformation object.
    * 
    * @return a ContactStationInformationImpl.
    */
   public static ContactStationInformation createContactStationInformation() {
      return new ContactStationInformationImpl() ;
   }
   
   /**
    * Creates an Era object.
    * 
    * @return an EraImpl.
    */
   public static Era createEra() {
      return new EraImpl() ;
   }

   /**
    * Creates a Misc object.
    * 
    * @return a MiscImpl.
    */
   public static Misc createMisc() {
      return new MiscImpl() ;
   }
   
   /**
    * Creates an Iota with pre-initalized values.
    * 
    * @param iotaEnum The IotaEnum object.
    * @param index The value.
    * 
    * @return a IotaImpl.
    */
   public static Iota createIota(IotaEnum iotaEnum, int index) {
      return new IotaImpl(iotaEnum, index) ;
   }
   
   /**
    * Creates a Qsl.
    * 
    * @return a QslImpl.
    */
   public static Qsl createQsl() {
      return new QslImpl() ;
   }
   
   /**
    * Creates a LogEntry.
    * 
    * @return a LogEntryImpl.
    */
   public static LogEntry createLogEntry() {
      return new LogEntryImpl() ;
   }

   /**
    * Creates a LogEntry.
    * 
    * @param defaultLogEntry a LogEntry with which to initialize this object with.  Not all values are currently used.
    * 
    * @return a LogEntryImpl.
    */
   public static LogEntry createLogEntry(LogEntry defaultLogEntry) {
      return new LogEntryImpl(defaultLogEntry) ;
   }
   
   /**
    * Creates a Satellite object.
    * 
    * @return a SatelliteImpl.
    */
   public static Satellite createSatellite() {
      return new SatelliteImpl() ;
   }
   
   /**
    * Creates a Contest.
    * 
    * @return a ContestImpl.
    */
   public static Contest createContest() {
      return new ContestImpl() ;
   }
}
