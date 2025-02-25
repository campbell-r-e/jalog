package openlogbook.jlogimpl.gui;

import java.util.ArrayList;

import openlogbook.jlog.gui.TableColumnChangeEvent;
import openlogbook.jlog.gui.TableUpdateListener;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.logbook.jlogentry.LogEntry;

import javax.swing.table.AbstractTableModel;


/**
 * The table model for the log book.
 * 
 * @author KC0ZPS
 */
public class LogBookTableModel extends AbstractTableModel implements TableUpdateListener {

   private ArrayList<TableColumnEnum> _columnArray = new ArrayList<TableColumnEnum>() ;
   private LogBook _logBook ;

   static final long serialVersionUID = -833003358330402803L;
   
   /**
    * Creates a new LogBookTableModel.
    * 
    * @param logBook The log book that this table model will represent.
    * @param columnValues The column values to display.
    */
   public LogBookTableModel(LogBook logBook, ArrayList<TableColumnEnum> columnValues) {
      _logBook = logBook ;
      
      int size = columnValues.size() ;
      for (int index = 0; index < size; index++) {
         _columnArray.add((TableColumnEnum)columnValues.get(index)) ;
      }
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Returns the row count.
    * 
    * @return the row count.
    */
   public int getRowCount(){
      return _logBook.getLogEntries().size() ;
   }
   
   /**
    * Returns the column count.
    * 
    * @return the column count.
    */
   public int getColumnCount() {
      return _columnArray.size() ;
   }
   
   /**
    * Gets the name of the column at the specified location.
    * 
    * @param columnIndex the index of the column.
    * 
    * @return the name of the column.
    */
   public String getColumnName(int columnIndex) {
      return _columnArray.get(columnIndex).toString() ;
   }
   
   /**
    * Gets the value at the specified location.
    * 
    * @param row the row of the object.
    * @param col the column of the object.
    * 
    * @return the object at the specified coordinates.
    */
   public Object getValueAt(int row, int col) {
      TableColumnEnum tableColumn = _columnArray.get(col) ;
      LogEntry entry = _logBook.getEntry(row) ;
      
      if (tableColumn.equals(TableColumnEnum.QsoDate)) {
         return entry.getEra().getStartDateString() ;
      } else if (tableColumn.equals(TableColumnEnum.TimeOn)) {
         return entry.getEra().getStartTimeString() ;
      } else if (tableColumn.equals(TableColumnEnum.TimeOff)) {
         if (entry.getEra().getEndDate() == null) {
            return "N/A" ;
         } else {
            return entry.getEra().getEndTimeString() ;
         }         
      } else if (tableColumn.equals(TableColumnEnum.ContactStation)) {
         return entry.getCallsign().getContactStation() ;
      } else if (tableColumn.equals(TableColumnEnum.Band)) {
         return entry.getFrequencyInformation().getBand() ;
      } else if (tableColumn.equals(TableColumnEnum.Frequency)) {
         return entry.getFrequencyInformation().getFrequency() ;
      } else if (tableColumn.equals(TableColumnEnum.Mode)) {
         return entry.getFrequencyInformation().getMode() ;
      } else if (tableColumn.equals(TableColumnEnum.RstSent)) {
         return entry.getRst().getRstSent() ;
      } else if (tableColumn.equals(TableColumnEnum.RstReceived)) {
         return entry.getRst().getRstReceived() ;
      } else if (tableColumn.equals(TableColumnEnum.Name)) {
         return entry.getContactAddress().getName() ;         
      } else if (tableColumn.equals(TableColumnEnum.Address)) {
         return entry.getContactAddress().getAddress() ;         
      } else if (tableColumn.equals(TableColumnEnum.Qth)) {
         return entry.getContactStationInformation().getQth() ;         
      } else if (tableColumn.equals(TableColumnEnum.Comment)) {
         return entry.getMisc().getComment() ;
      } else if (tableColumn.equals(TableColumnEnum.Operator)) {
         return entry.getCallsign().getOperatingStation() ;
      } else if (tableColumn.equals(TableColumnEnum.County)) {
         return entry.getContactAddress().getCounty() ;
      } else if (tableColumn.equals(TableColumnEnum.Continent)) {
         return entry.getContactAddress().getContinent() ;
      } else if (tableColumn.equals(TableColumnEnum.State)) {
         return entry.getContactAddress().getUsState() ;
      } else if (tableColumn.equals(TableColumnEnum.Age)) {
         return entry.getContactStationInformation().getAge() ;
      } else if (tableColumn.equals(TableColumnEnum.CqZone)) {
         return entry.getContactStationInformation().getCqZone() ;
      } else if (tableColumn.equals(TableColumnEnum.Dxcc)) {
         return entry.getContactStationInformation().getDxcc() ;
      } else if (tableColumn.equals(TableColumnEnum.GridSquare)) {
         return entry.getContactStationInformation().getGridSquare() ;
      } else if (tableColumn.equals(TableColumnEnum.ItuZone)) {
         return entry.getContactStationInformation().getItuZone() ;
      } else if (tableColumn.equals(TableColumnEnum.TxPower)) {
         return entry.getFrequencyInformation().getTxPower() ;
      } else if (tableColumn.equals(TableColumnEnum.RxPower)) {
         return entry.getFrequencyInformation().getRxPower() ;
      } else if (tableColumn.equals(TableColumnEnum.Iota)) {
         return entry.getContactStationInformation().getIota() ;
      } else if (tableColumn.equals(TableColumnEnum.QslReceivedDate)) {
         return entry.getQsl().getQslReceivedDateString() ;
      } else if (tableColumn.equals(TableColumnEnum.QslSentDate)) {
         return entry.getQsl().getQslSentDateString() ;
      } else if (tableColumn.equals(TableColumnEnum.QslReceived)) {
         return entry.getQsl().isQslReceived() ;
      } else if (tableColumn.equals(TableColumnEnum.QslSent)) {
         return entry.getQsl().isQslSent() ;
      } else if (tableColumn.equals(TableColumnEnum.QslMessage)) {
         return entry.getQsl().getQslMessage() ;
      } else if (tableColumn.equals(TableColumnEnum.QslVia)) {
         return entry.getQsl().getQslVia() ;
      } else if (tableColumn.equals(TableColumnEnum.ArrlSection)) {
         return entry.getMisc().getArrlSection() ;
      } else if (tableColumn.equals(TableColumnEnum.ContestId)) {
         return entry.getContest().getContestId() ;
      } else if (tableColumn.equals(TableColumnEnum.Notes)) {
         return entry.getMisc().getNotes() ;
      } else if (tableColumn.equals(TableColumnEnum.WpxPrefix)) {
         return entry.getMisc().getWpxPrefix() ;
      } else if (tableColumn.equals(TableColumnEnum.TenTen)) {
         return entry.getMisc().getTenTen() ;
      } else if (tableColumn.equals(TableColumnEnum.VeProv)) {
         return entry.getMisc().getVeProv() ;
      } else if (tableColumn.equals(TableColumnEnum.PropMode)) {
         return entry.getSatellite().getPropMode() ;
      } else if (tableColumn.equals(TableColumnEnum.SatelliteMode)) {
         return entry.getSatellite().getSatelliteMode() ;
      } else if (tableColumn.equals(TableColumnEnum.SatelliteName)) {
         return entry.getSatellite().getSatelliteName() ;
      } else if (tableColumn.equals(TableColumnEnum.ReceivedSerialNumber)) {
         return entry.getContest().getReceivedSerialNumber() ;
      } else if (tableColumn.equals(TableColumnEnum.SentSerialNumber)) {
         return entry.getContest().getTransmittedSerialNumber() ;
      }
      return "what the..." ;
   }
   
   /**
    * Updates the column array with a new list of columns.
    * 
    * @param tableColumnChangeEvent the TableColumnChangeEvent.
    * @param columnList the new column list.
    */
   public void updateColumns(TableColumnChangeEvent tableColumnChangeEvent, ArrayList<TableColumnEnum> columnList) {
      _columnArray.clear() ;
      
      _columnArray = columnList ;
      
      fireTableStructureChanged() ;
   }
}
