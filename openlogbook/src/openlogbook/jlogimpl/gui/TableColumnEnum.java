package openlogbook.jlogimpl.gui;

import openlogbook.jlog.util.IntToObject;

/**
 * An enumeration of columns.  The values assigned in this class is what the user will see in the columns for the table.
 * 
 * @author KC0ZPS
 */
public class TableColumnEnum {

   /**
    * Creates a new TableColumnEnum.  The constructor is private.  The only instances are the constants
    * instantiated in this module.
    * 
    * @param description   Description of this meeting type.
    */
   private TableColumnEnum(String description) {
       _description = description ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Returns the IntToObject object.
    * 
    * @return the IntToObject object.
    */
   public static IntToObject getIntToTableColumnType() {
       return _intToTableColumnType ;
   }

   /**
    * Returns all objects represented by this class as an array.
    * 
    * @return all objects represented by this class as an array.
    */
   public static TableColumnEnum[] getValues() {
      return _values ;
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
       return _description ;
   }

   //**********
   // Class attributes and constants
   //**********
   
   private String _description ;

   public static final TableColumnEnum QsoDate        = new TableColumnEnum("QSO Date") ;
   public static final TableColumnEnum TimeOn         = new TableColumnEnum("Time On") ;
   public static final TableColumnEnum TimeOff        = new TableColumnEnum("Time Off") ;
   public static final TableColumnEnum ContactStation = new TableColumnEnum("Contact Station") ;
   public static final TableColumnEnum Band           = new TableColumnEnum("Band") ;
   public static final TableColumnEnum Frequency      = new TableColumnEnum("Frequency") ;
   public static final TableColumnEnum Mode           = new TableColumnEnum("Mode") ;
   public static final TableColumnEnum RstSent        = new TableColumnEnum("RST Sent") ;
   public static final TableColumnEnum RstReceived    = new TableColumnEnum("RST Recv") ;
   public static final TableColumnEnum Name           = new TableColumnEnum("Name") ;
   public static final TableColumnEnum Address        = new TableColumnEnum("Address") ;
   public static final TableColumnEnum Qth            = new TableColumnEnum("Qth") ;
   public static final TableColumnEnum Comment        = new TableColumnEnum("Comment") ;
   public static final TableColumnEnum Operator       = new TableColumnEnum("Operator") ;
   public static final TableColumnEnum County         = new TableColumnEnum("County") ;
   public static final TableColumnEnum Continent      = new TableColumnEnum("Continent") ;
   public static final TableColumnEnum State          = new TableColumnEnum("State") ;
   public static final TableColumnEnum Age            = new TableColumnEnum("Age") ;
   public static final TableColumnEnum CqZone         = new TableColumnEnum("CQ Zone") ;
   public static final TableColumnEnum Dxcc           = new TableColumnEnum("DXCC") ;
   public static final TableColumnEnum GridSquare     = new TableColumnEnum("Grid Square") ;
   public static final TableColumnEnum ItuZone        = new TableColumnEnum("ITU Zone") ;
   public static final TableColumnEnum TxPower        = new TableColumnEnum("TX Power") ;
   public static final TableColumnEnum RxPower        = new TableColumnEnum("RX Power") ;
   public static final TableColumnEnum Iota           = new TableColumnEnum("IOTA") ;
   public static final TableColumnEnum QslReceivedDate = new TableColumnEnum("QSL Recv Date") ;
   public static final TableColumnEnum QslSentDate    = new TableColumnEnum("QSL Sent Date") ;
   public static final TableColumnEnum QslReceived    = new TableColumnEnum("QSL Rcvd") ;
   public static final TableColumnEnum QslSent        = new TableColumnEnum("QSL Sent") ;
   public static final TableColumnEnum QslMessage     = new TableColumnEnum("QSL Message") ;
   public static final TableColumnEnum QslVia         = new TableColumnEnum("QSL Via") ;
   public static final TableColumnEnum ArrlSection    = new TableColumnEnum("ARRL Section") ;
   public static final TableColumnEnum ContestId      = new TableColumnEnum("Contest ID") ;
   public static final TableColumnEnum Notes          = new TableColumnEnum("Notes") ;
   public static final TableColumnEnum WpxPrefix      = new TableColumnEnum("WPX Prefix") ;
   public static final TableColumnEnum TenTen         = new TableColumnEnum("Ten-Ten") ;
   public static final TableColumnEnum VeProv         = new TableColumnEnum("VE Prov") ;
   public static final TableColumnEnum PropMode       = new TableColumnEnum("Prop Mode") ;
   public static final TableColumnEnum SatelliteMode  = new TableColumnEnum("Satellite Mode") ;
   public static final TableColumnEnum SatelliteName  = new TableColumnEnum("Satellite Name") ;
   public static final TableColumnEnum ReceivedSerialNumber = new TableColumnEnum("Received Serial Number") ;
   public static final TableColumnEnum SentSerialNumber = new TableColumnEnum("Sent Serial Number") ;

   /**
    * These values MUST match _intlookupData.
    */
   private static final TableColumnEnum _values[] = {
      QsoDate,
      TimeOn,
      TimeOff,
      ContactStation,
      Band,
      Frequency,
      Mode,
      RstSent,
      RstReceived,
      Name,
      Address,
      Qth,
      Comment,
      Operator,
      County,
      Continent,
      State,
      Age,
      CqZone,
      Dxcc,
      GridSquare,
      ItuZone,
      TxPower,
      RxPower,
      Iota,
      QslReceivedDate,
      QslSentDate,
      QslReceived,
      QslSent,
      QslMessage,
      QslVia,
      ArrlSection,
      ContestId,
      Notes,
      WpxPrefix,
      TenTen,
      VeProv,
      PropMode,
      SatelliteMode,
      SatelliteName,
      ReceivedSerialNumber,
      SentSerialNumber
   } ;
   
   private static final Object[][] _intlookupData = {
      {new Integer(0), TableColumnEnum.QsoDate}, 
      {new Integer(1), TableColumnEnum.TimeOn}, 
      {new Integer(2), TableColumnEnum.TimeOff}, 
      {new Integer(3), TableColumnEnum.ContactStation}, 
      {new Integer(4), TableColumnEnum.Band}, 
      {new Integer(5), TableColumnEnum.Frequency}, 
      {new Integer(6), TableColumnEnum.Mode}, 
      {new Integer(7), TableColumnEnum.RstSent}, 
      {new Integer(8), TableColumnEnum.RstReceived}, 
      {new Integer(9), TableColumnEnum.Name}, 
      {new Integer(10), TableColumnEnum.Address}, 
      {new Integer(12), TableColumnEnum.Qth}, 
      {new Integer(13), TableColumnEnum.Comment}, 
      {new Integer(14), TableColumnEnum.Operator}, 
      {new Integer(15), TableColumnEnum.County}, 
      {new Integer(16), TableColumnEnum.Continent}, 
      {new Integer(17), TableColumnEnum.State}, 
      {new Integer(18), TableColumnEnum.Age}, 
      {new Integer(19), TableColumnEnum.CqZone}, 
      {new Integer(20), TableColumnEnum.Dxcc}, 
      {new Integer(21), TableColumnEnum.GridSquare}, 
      {new Integer(22), TableColumnEnum.ItuZone}, 
      {new Integer(23), TableColumnEnum.TxPower}, 
      {new Integer(24), TableColumnEnum.RxPower}, 
      {new Integer(25), TableColumnEnum.Iota}, 
      {new Integer(26), TableColumnEnum.QslReceivedDate}, 
      {new Integer(27), TableColumnEnum.QslSentDate}, 
      {new Integer(28), TableColumnEnum.QslReceived}, 
      {new Integer(29), TableColumnEnum.QslSent}, 
      {new Integer(30), TableColumnEnum.QslMessage}, 
      {new Integer(31), TableColumnEnum.QslVia}, 
      {new Integer(32), TableColumnEnum.ArrlSection}, 
      {new Integer(33), TableColumnEnum.ContestId}, 
      {new Integer(34), TableColumnEnum.Notes}, 
      {new Integer(35), TableColumnEnum.WpxPrefix}, 
      {new Integer(36), TableColumnEnum.TenTen}, 
      {new Integer(37), TableColumnEnum.VeProv}, 
      {new Integer(38), TableColumnEnum.PropMode}, 
      {new Integer(39), TableColumnEnum.SatelliteMode}, 
      {new Integer(40), TableColumnEnum.SatelliteName}, 
      {new Integer(41), TableColumnEnum.ReceivedSerialNumber}, 
      {new Integer(42), TableColumnEnum.SentSerialNumber}
   } ;
   
   private static final IntToObject _intToTableColumnType = new IntToObject(_intlookupData, TableColumnEnum.QsoDate) ;

}
