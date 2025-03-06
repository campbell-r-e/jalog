package openlogbook.jlogimpl.logbook.logentry;

import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.logbook.jlogentry.Callsign;
import openlogbook.jlog.logbook.jlogentry.ContactAddress;
import openlogbook.jlog.logbook.jlogentry.ContactStationInformation;
import openlogbook.jlog.logbook.jlogentry.Contest;
import openlogbook.jlog.logbook.jlogentry.Era;
import openlogbook.jlog.logbook.jlogentry.FrequencyInformation;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.logbook.jlogentry.Misc;
import openlogbook.jlog.logbook.jlogentry.Qsl;
import openlogbook.jlog.logbook.jlogentry.Rst;
import openlogbook.jlog.logbook.jlogentry.Satellite;

/**
 * An implementation of the LogEntry interface.
 * 
 * @author KC0ZPS
 */
public class LogEntryImpl implements LogEntry {

   private Callsign                  _callsign = DefaultLogBookFactory.createCallsign() ;
   private Era                       _era = DefaultLogBookFactory.createEra() ;
   private Rst                       _rst = DefaultLogBookFactory.createRst() ;
   private FrequencyInformation      _frequencyInformation = DefaultLogBookFactory.createFrequencyInformation() ;
   private ContactAddress            _contactAddress = DefaultLogBookFactory.createContactAddress() ;
   private ContactStationInformation _contactStationInformation = DefaultLogBookFactory.createContactStationInformation() ;
   private Qsl                       _qsl = DefaultLogBookFactory.createQsl() ;
   private Satellite                 _satellite = DefaultLogBookFactory.createSatellite() ;
   private Contest                   _contest = DefaultLogBookFactory.createContest() ;
   private Misc                      _misc = DefaultLogBookFactory.createMisc() ;

   /**
    * Creates a new LogEntryImpl.
    */
   public LogEntryImpl() {
   }

   /**
    * Creates a new LogEntryImpl.
    * 
    * @param defaultLogEntry a LogEntry with which to initialize this object with.  Not all values are currently used.
    */
   public LogEntryImpl(LogEntry defaultLogEntry) {
      _rst = defaultLogEntry.getRst() ;
      _callsign.setOperatingStation(defaultLogEntry.getCallsign().getOperatingStation()) ;
      _frequencyInformation.setBand(defaultLogEntry.getFrequencyInformation().getBand()) ;
      _frequencyInformation.setMode(defaultLogEntry.getFrequencyInformation().getMode()) ;
      _frequencyInformation.setTxPower(defaultLogEntry.getFrequencyInformation().getTxPower()) ;
      _frequencyInformation.setRxPower(defaultLogEntry.getFrequencyInformation().getRxPower()) ;
   }
   
   //**********
   // Methods that are inherited from LogEntry
   //**********

   /**
    * Returns the callsign information for this log entry.
    * 
    * @return the callsign information for this log entry.
    */
   public Callsign getCallsign() {
      return _callsign ;
   }
   
   /**
    * Gets the date information.
    * 
    * @return the date information.
    */
   public Era getEra() {
      return _era ;
   }
     
   /**
    * Gets the RST information.
    * 
    * @return the RST information.
    */
   public Rst getRst() {
      return _rst ;
   }
   
   /**
    * Sets the RST information.
    * 
    * @param rst the RST information.
    */
   public void setRst(Rst rst) {
      _rst = rst ;
   }
   
   /**
    * Gets the frequency information.
    * 
    * @return the frequency information.
    */
   public FrequencyInformation getFrequencyInformation() {
      return _frequencyInformation ;
   }
   
   /**
    * Gets the contact address.
    * 
    * @return the contact address.
    */
   public ContactAddress getContactAddress() {
      return _contactAddress ;
   }
   
   /**
    * Gets the basic contact station information.
    * 
    * @return the basic contact station information.
    */
   public ContactStationInformation getContactStationInformation() {
      return _contactStationInformation ;
   }

   /**
    * Returns the QSL information.
    * 
    * @return the QSL information.
    */
   public Qsl getQsl() {
      return _qsl ;
   }
   
   /**
    * Gets the satellite information.
    * 
    * @return the satellite information.
    */
   public Satellite getSatellite() {
      return _satellite ;
   }

   /**
    * Gets contesting information.
    * 
    * @return the contesting information.
    */
   public Contest getContest() {
      return _contest ;
   }
   
   /**
    * Get misc information.
    * 
    * @return misc information.
    */
   public Misc getMisc() {
      return _misc ;
   }

   /**
    * Returns a string representation of this object in ADIF format.
    * 
    * @return a string representation of this object in ADIF format.
    */
   public String toAdifString() {
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append(_callsign.toAdifString()) ;
      buffer.append(_era.toAdifString()) ;
      buffer.append(_rst.toAdifString()) ;
      buffer.append(_frequencyInformation.toAdifString()) ;
      buffer.append(_contactAddress.toAdifString()) ;
      buffer.append(_contactStationInformation.toAdifString()) ;
      buffer.append(_qsl.toAdifString()) ;
      buffer.append(_satellite.toAdifString()) ;
      buffer.append(_contest.toAdifString()) ;
      buffer.append(_misc.toAdifString()) ;
      
      return buffer.toString() ;
   }
   
   //**********
   // Methods overridden from Object
   //**********
   
   /**
    * Returns a string representation of the object.
    * 
    * @return string representation of the object.
    */
   public String toString() {
      StringBuffer buffer = new StringBuffer() ;
      
      buffer.append(_callsign.toString()) ;
      buffer.append(_era.toString()) ;
      buffer.append(_rst.toString()) ;
      buffer.append(_frequencyInformation.toString()) ;
      buffer.append(_contactAddress.toString()) ;
      buffer.append(_contactStationInformation.toString()) ;
      buffer.append(_qsl.toString()) ;
      buffer.append(_satellite.toString()) ;
      buffer.append(_contest.toString()) ;
      buffer.append(_misc.toString()) ;

      return buffer.toString() ;
   }
}
