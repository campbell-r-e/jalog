package openlogbook.debug.monitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A data model for managing the data contained in the terminal window.
 */
class TerminalModel {

   /**
    * Creates a new TerminalModel.
    *
    * @param nbrRows     The number of rows in the model
    * @param nbrColumns  The number of columns in the model
    */
   public TerminalModel(int nbrRows, int nbrColumns) {
      _nbrRows = nbrRows ;
      _nbrColumns = nbrColumns ;
      _data = new char[_nbrRows][_nbrColumns] ;
      clear() ;
   }

   //**********
   // Public methods
   //**********

   /**
    * Adds a listener.
    *
    * @param listener   a listener
    *
    */
   public void addListener(TerminalModelListener listener) {
      _listeners.add(listener) ;
   }

   /**
    * Removes a listener.
    *
    * @param listener   a listener to be removed
    *
    */
   public void removeListener(TerminalModelListener listener) {
      if (listener != null) {
         _listeners.remove(listener) ;
      }
   }


   /**
    * Clears the window.
    *
    */
   public void clear() {
      _row = 0 ;
      _column = 0 ;
      _firstRow = 0 ;
      for (int row = 0; row < _nbrRows; row++) {
         for (int column = 0; column < _nbrColumns; column++) {
            _data[row][column] = ' ' ;
         }
      }
      contentsChanged() ;
      activeRowChanged(0) ;
   }

   /**
    * Gets the number of columns.
    *
    * @return the number of columns.
    */
   public int getNbrColumns() {
      return _nbrColumns ;
   }

   /**
    * Gets the number of rows.
    *
    * @return the number of rows.
    */
   public int getNbrRows() {
      return _nbrRows ;
   }


   /**
    * Gets the current row number
    *
    * @return    the current row number
    */
   public int getRow() {
      return _row ;
   }

   /**
    * Gets the array characters for the logical row number
    *
    * @param row  the logical row number
    *
    * @return the array characters for the logical row number
    */
   public char[] getRowChars(int row) {
      return _data[getPhysicalRow(row)];
   }


   /**
    * Saves the window contents to an output stream.
    *
    * @param printWriter   PrintWriter to print to
    *
    */
   public void print(PrintWriter printWriter) {
      for (int row = 0; row <= _nbrRows; row++) {
         // Stop if the rest of the monitor window is empty
         if ((row > _row) && (_firstRow == 0)) {
            break ;
         }
         String s = new String(_data[getPhysicalRow(row)], 0, _nbrColumns) ;
         s.trim() ;
         printWriter.println(s) ;
      }
   }


   /**
    * Puts a character to the display model.
    *
    * @param c  character to be displayed
    *
    */
   public void put(char c) {
      putChar(c) ;
      contentsChanged() ;
   }


   /**
    * Puts an array of bytes (converting to characters) to the display model.
    *
    * @param b   array of bytes to be displayed
    *
    */
   public void put(byte[] b) {
      for (int i = 0; i < b.length; i++) {
         putChar((char)b[i]) ;
      }
      contentsChanged() ;
   }


   /**
    * Puts a string to the display model.
    *
    * @param s  string to be displayed
    *
    */
   public void put(String s) {
      for (int idx = 0; idx < s.length(); idx++) {
         putChar(s.charAt(idx)) ;
      }
      contentsChanged() ;
   }


   /**
    * Saves contents to a file.
    *
    * @param file   file to save contents to
    *
    */
   public void saveToFile(File file) {
      try {
         PrintWriter printWriter = new PrintWriter(new FileOutputStream(file)) ;
         print(printWriter) ;
         printWriter.close() ;
      } catch (Exception e) {
         e.printStackTrace() ; // Ok to print. We're only in this path in test environments.
      }
   }


   //**********
   // Private methods
   //**********

   /**
    * Notifies listeners that the active row has changed.
    *
    * @param   row   the new active row
    *
    */
   private void activeRowChanged(int row) {
      Iterator<TerminalModelListener> iterator = _listeners.iterator() ;
      while (iterator.hasNext()) {
         TerminalModelListener listener = iterator.next() ;
         listener.activeRowChanged(row) ;
      }
   }


   /**
    * Notifies listeners that the model contents have changed.
    *
    *
    */
   private void contentsChanged() {
      Iterator<TerminalModelListener> iterator = _listeners.iterator() ;
      while (iterator.hasNext()) {
         TerminalModelListener listener = iterator.next() ;
         listener.contentsChanged() ;
      }
   }


   /**
    * Returns the index of the physical row, given the logical row number.
    *
    * @param logicalRow   a logical row number
    *
    * @return the index of the physical row, given the logical row number.
    */
   private int getPhysicalRow(int logicalRow) {
      return (logicalRow + _firstRow) % _nbrRows ;
   }


   /**
    * Moves to the next row, handling wrapping of the buffer.
    *
    */
   private void nextRow() {
      int originalRow = _row ;
      boolean scrolled = false ;
      _row = _row + 1 ;
      if (_row >= _nbrRows) {
         _firstRow = (_firstRow + 1) % _nbrRows ;
         _row = _nbrRows-1 ;
         scrolled = true ;
      }

      // Clear out the new row
      int currentRow = getPhysicalRow(_row) ;
      for (int idx = 0; idx < _nbrColumns; idx++) {
         _data[currentRow][idx] = ' ' ;
      }

      if (originalRow != _row) {
         activeRowChanged(_row) ;
      }

      Iterator<TerminalModelListener> iterator = _listeners.iterator() ;
      while (iterator.hasNext()) {
         TerminalModelListener listener = (TerminalModelListener)iterator.next() ;
         if (scrolled) {
            listener.dataScrolled() ;
         }
      }
   }


   /**
    * Puts a character to the model.
    *
    * @param c  character to be displayed
    *
    */
   private void putChar(char c) {
      int currentRow = getPhysicalRow(_row) ;
      switch (c) {
      case '\t':
         // no real tabstop support; just output 3 spaces
         for (int idx = 0; idx < 3; idx++) {
            putChar(' ') ;
         }
         break ;
      case '\b':
         if (_column > 0) {
            _column-- ;
            _data[currentRow][_column] = ' ' ;
         }
         break ;
      case '\n':
         while (_column < _nbrColumns) {
            _data[currentRow][_column++] = ' ' ;
         }
         _column = 0 ;
         nextRow() ;
         break ;
      case '\r':
         break ;
      default:
         _data[currentRow][_column] = c ;
         _column = _column + 1 ;
         if (_column >= _nbrColumns) {
            _column = 0 ;
            nextRow() ;
         }
         break ;
      }
   }


   //**********
   // Class attributes and constants
   //**********

   private char[][]                         _data ;
   private int                              _nbrColumns ;
   private int                              _nbrRows ;
   private int                              _row ;
   private int                              _column ;
   private int                              _firstRow = 0 ;
   private ArrayList<TerminalModelListener> _listeners = new ArrayList<TerminalModelListener>(2) ;

}


// End of TerminalModel.java

