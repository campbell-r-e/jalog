package jalog.jlogimpl.debug ;

import java.io.Serializable;

/**
 * An enumeration of internal events.
 *
 */
public class InternalEventId implements Serializable {

   /**
    * Creates a new InternalEventId. The constructor is private. The only instances are the constants
    * instantiated in this module.
    *
    * @param eventCode    the numeric code for the event
    * @param description  a description of this internal event
    */
   private InternalEventId(int eventCode, String description) {
      _eventCode = eventCode ;
      _description = description ;
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
      return String.valueOf(getEventCode()) + ": " + getDescription() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Gets the event description.
    *
    * @return the event description
    */
   public String getDescription() {
      return _description ;
   }


   /**
    * Gets the event code.
    *
    * @return the event code
    */
   public int getEventCode() {
      return _eventCode ;
   }



   //**********
   // Private methods
   //**********

   /**
    * Returns a substitute object during deserialization. The intention is to prevent duplicate
    * constants.
    *
    * @return a substitute object during deserialization.
    */
   private Object readResolve() {
      return _values[_ordinal] ;
   }


   //**********
   // Class attributes and constants
   //**********

   /**
    * An exception occurred while trying to build an NMRU Payload.
    */
   public static final InternalEventId
      NmruPayloadBuildError = new InternalEventId(1, "Failed to build an NMRU payload") ;

   /**
    * An invalid FirmwareFile object was given to the FirmwareManager.
    */
   public static final InternalEventId
      InvalidFirmwareFileObject = new InternalEventId(2, "Invalid FirmwareFile object") ;

   /**
    * An exception occurred while trying to decode a change notification payload.
    */
   public static final InternalEventId
      NmruPayloadDecodeError = new InternalEventId(3, "Failed to parse change notification payload") ;

   /**
    * An exception occurred while trying to process a Name Server query.
    */
   public static final InternalEventId
      NameServerQueryIoError = new InternalEventId(4, "IOException processing a Name Server Query") ;

   /**
    * An exception occurred while processing a change notification hook.
    */
   public static final InternalEventId
      ChangeNotificationHookException = new InternalEventId(5, "Exception while processing a change notification hook") ;

   /**
    * A Change Notification NMRU contained an invalid port number.
    */
   public static final InternalEventId
      ChangeNotificationInvalidPortNbr = new InternalEventId(6, "Change Notification contained invalid port number") ;

   /**
    * A Change Notification NMRU contained an invalid port address.
    */
   public static final InternalEventId
      ChangeNotificationInvalidPortAddress = new InternalEventId(7, "Change Notification contained invalid port address") ;

   /**
    * A Change Notification NMRU contained an invalid port number.
    */
   public static final InternalEventId
      DefaultInetAddressException = new InternalEventId(8, "Exception while creating a default InetAddress") ;

   /**
    * A Change Notification NMRU contained invalid switch data.
    */
   public static final InternalEventId
      InvalidSwitchData = new InternalEventId(9, "Exception while creating a default InetAddress") ;

   /**
    * An exception occurred while dispatching a link status change to other internal jalog components.
    */
   public static final InternalEventId
      LinkStatusDispatchException = new InternalEventId(9, "Exception while dispatching a link status change") ;

   /**
    * An SNMP communication error occurred during a GET operation.
    */
   public static final InternalEventId
      SnmpExceptionGet = new InternalEventId(10, "Exception while getting data via SNMP") ;

   /**
    * An SNMP communication error occurred during a SET operation.
    */
   public static final InternalEventId
      SnmpExceptionSet = new InternalEventId(11, "Exception while setting data via SNMP") ;

   /**
    * An SNMP communication error occurred during a SET operation.
    */
   public static final InternalEventId
      XmlRpcException = new InternalEventId(12, "Exception during an XML RPC call") ;

   /**
    * An SNMP communication error occurred during a change notification operation.
    */
   public static final InternalEventId
      SnmpExceptionCn = new InternalEventId(13, "Exception while reading change notification data via SNMP") ;

   /**
    * An exception occurred related to encrypting/decrypting data.
    */
   public static final InternalEventId
      EncryptionException = new InternalEventId(14, "Encryption failed") ;

   /**
    * A failure occurred during SSL initialization.
    */
   public static final InternalEventId
      SslInitFailure = new InternalEventId(15, "SSL Initialization failed") ;

   /**
    * An exception occurred while dispatching a link status change to other internal jalog components.
    */
   public static final InternalEventId
      LogEntryDispatchException = new InternalEventId(16, "Exception while dispatching a log entry") ;


   // The following are used to support Serialization. Together with the use of readResolve(),
   // this is intended to prevent duplicate constants from coexisting as a result of deserialization.
   // The idea was taken from the book "Effective Java Programming" by Joshua Bloch.
   // NOTE: The entries in the _values array must be in the same order as the declaration order of the
   // constants above.
   private static int                     _nextOrdinal = 0 ;
   private static final InternalEventId[] _values = {
      NmruPayloadBuildError,
      InvalidFirmwareFileObject,
      NmruPayloadDecodeError,
      NameServerQueryIoError,
      ChangeNotificationHookException,
      ChangeNotificationInvalidPortNbr,
      ChangeNotificationInvalidPortAddress,
      DefaultInetAddressException,
      InvalidSwitchData,
      LinkStatusDispatchException,
      SnmpExceptionGet,
      SnmpExceptionSet,
      XmlRpcException,
      SnmpExceptionCn,
      EncryptionException,
      SslInitFailure,
      LogEntryDispatchException
   } ;

   private String       _description ;
   private int          _eventCode ;
   private int          _ordinal = _nextOrdinal++ ;


   /**
    * The serialVersionUID for the class jalog.jlogimpl.debug.InternalEventId
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -5396595714450917416L;

}


// End of InternalEventId.java

