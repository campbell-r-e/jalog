package openlogbook.factory;

import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.Callsign;
import openlogbook.jlog.logbook.jlogentry.ContactAddress;
import openlogbook.jlog.logbook.jlogentry.ContactStationInformation;
import openlogbook.jlog.logbook.jlogentry.Contest;
import openlogbook.jlog.logbook.jlogentry.Era;
import openlogbook.jlog.logbook.jlogentry.FrequencyInformation;
import openlogbook.jlog.logbook.jlogentry.Iota;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.logbook.jlogentry.Misc;
import openlogbook.jlog.logbook.jlogentry.Qsl;
import openlogbook.jlog.logbook.jlogentry.Rst;
import openlogbook.jlog.logbook.jlogentry.Satellite;
import openlogbook.jlog.util.IotaEnum;
import openlogbook.jlogimpl.logbook.LogBookImpl;
import openlogbook.jlogimpl.logbook.logentry.CallsignImpl;
import openlogbook.jlogimpl.logbook.logentry.ContactAddressImpl;
import openlogbook.jlogimpl.logbook.logentry.ContactStationInformationImpl;
import openlogbook.jlogimpl.logbook.logentry.ContestImpl;
import openlogbook.jlogimpl.logbook.logentry.EraImpl;
import openlogbook.jlogimpl.logbook.logentry.FrequencyInformationImpl;
import openlogbook.jlogimpl.logbook.logentry.IotaImpl;
import openlogbook.jlogimpl.logbook.logentry.LogEntryImpl;
import openlogbook.jlogimpl.logbook.logentry.MiscImpl;
import openlogbook.jlogimpl.logbook.logentry.QslImpl;
import openlogbook.jlogimpl.logbook.logentry.RstImpl;
import openlogbook.jlogimpl.logbook.logentry.SatelliteImpl;

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
