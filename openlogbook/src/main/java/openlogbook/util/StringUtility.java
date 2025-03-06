package openlogbook.util;

import openlogbook.debug.Debug;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class containing string utility methods.
 */
public class StringUtility {
   
   /**
    * Creates a new StringUtility. The constructor is private because this
    * is a utility class and we don't want instances created.
    */
   private StringUtility() {
   }
   
   /**
    * Converts a raw IP address to a dotted decimal string format.
    *
    * @param rawIPAddress  a raw IP address
    *
    * @return a String representation of the IP address
    */
   public static String rawIPToString(byte[] rawIPAddress) {
      int[] decimals = new int[4] ;
      for (int idx = 0; idx < 4; idx++) {
         decimals[idx] = rawIPAddress[idx] & 0xFF ;
      }

      String ipString = decimals[0] + "." + decimals[1] + "." + decimals[2] + "." + decimals[3] ;
      return ipString ;
   }


   /**
    * Converts a String to a fixed length array of bytes using
    * the 8859-1 encoding.
    *
    * @param sourceString  a string to be converted to bytes
    * @param destination   a byte array to receive the converted string
    *
    */
   public static void toBytes(String sourceString, byte[] destination) {
      try {
         byte[] bytes = sourceString.getBytes("8859_1") ;
         int length = Math.min(bytes.length, destination.length) ;
         System.arraycopy(bytes, 0, destination, 0, length) ;
         while (length < destination.length) {
            destination[length] = 0 ;
            length++ ;
         }
      } catch (UnsupportedEncodingException e) {
         Debug.logException(e) ;
      }
   }

   
   /**
    * Convert the given hex string into an array of bytes.
    * 
    * @param hexString  a hex string to be converted to bytes
    * 
    * @return an array of bytes representing the hex string
    */   
   public static byte[] hexToBytes(String hexString) {
      //convert hex string to bytes
      byte[] bytes = new byte[hexString.length() / 2];
      for (int index = 0; index < bytes.length; index++) {
         int begin = index * 2;
         int intValue = Integer.parseInt(hexString.substring(begin, begin + 2), 16);
         bytes[index] = (byte) intValue;
      }
      return bytes;
   }


   /**
    * Converts a nibble to a hex string consisting of a single character.
    *
    * @param nibble  a nibble to be converted to hex
    *
    * @return  a string with a single hex character
    */
   public static char toHexChar(int nibble) {
      int value = nibble & 0x0F ;
      return (char)((value < 10) ? (value + '0') : (value + 'A' - 10)) ;
   }

   /**
    * Converts a data byte to a hex string.
    *
    * @param dataByte  a byte of data to be converted to hex
    *
    * @return    Data decoded as a hex string
    */
   public static String toHexString(byte dataByte) {
      int value = dataByte ;
      return _hexValues[value & 0xFF] ;
   }

   /**
    * Converts an octet string into a hex string padded with "0"s and
    * seperated by the given delimiter.
    * 
    * @param octetStr The octet string.
    * @param delimiter The delimeter seperating the octet strings, if any.
    * 
    * @return a hex string.
    */
   public static String octetStr2HexStr(String octetStr, String delimiter) {
      StringBuffer sbuf = new StringBuffer();
      int s;
      for (int i = 0; i < octetStr.length(); i++) {
         s = octetStr.charAt(i);

         if (s < 16) {
            sbuf.append("0" + Integer.toString(s, 16).toUpperCase());
         } else {
            sbuf.append(Integer.toString(s, 16).toUpperCase());
         }
   
         sbuf.append(delimiter);
      }

      return sbuf.substring(0, sbuf.length()-delimiter.length());
   }

   /**
    * Converts a data byte to a hex string.
    *
    * @param dataBytes  an array of bytes to be converted to hex
    *
    * @return    Data decoded as a hex string
    */
   public static String toHexString(byte[] dataBytes) {
      return toHexString(dataBytes, '\0') ;
   }

   /**
    * Converts a data byte to a hex string.
    *
    * @param dataBytes  an array of bytes to be converted to hex
    * @param separator  Separator character to insert between each byte
    *
    * @return    Data decoded as a hex string
    */
   public static String toHexString(byte[] dataBytes, char separator) {
      StringBuffer stringBuffer = new StringBuffer() ;
      for (int i = 0; i < dataBytes.length; i++) {
         byte dataByte = dataBytes[i] ;
         int lowNibble = dataByte & 0x0F ;
         int highNibble = (dataByte >> 4) & 0x0F ;
         stringBuffer.append((char)((highNibble < 10) ? (highNibble + '0') : (highNibble + 'A' - 10))) ;
         stringBuffer.append((char)((lowNibble < 10) ? (lowNibble + '0') : (lowNibble + 'A' - 10))) ;
         if (separator != '\0') {
            stringBuffer.append(separator) ;
         }
      }
      return stringBuffer.toString() ;
   }


   /**
    * Converts an array of objects to a string.
    *
    * @param objects  an array of objects to be converted to a string
    * @param separator the separator to be placed between each object.
    *
    * @return    a string with all objects in the array.
    */
   public static String toString(Object[] objects, char separator) {
      StringBuffer stringBuffer = new StringBuffer() ;
      for (int idx = 0; idx < objects.length; idx++) {
         stringBuffer.append(objects[idx]) ;
         if (idx < objects.length - 1) {
            stringBuffer.append(separator) ;
            stringBuffer.append(' ') ;
         }
      }
      return stringBuffer.toString() ;
   }

   /**
    * Converts an array of objects to a string.
    *
    * @param objects    a two diminsional array of objects to be converted to a string
    * @param separator1 the separator to be placed between each object in the first dimension.
    * @param separator2 the separator to be placed between each object in the second dimension.
    *
    * @return    a string with all objects in the array.
    */
   public static String toString(Object[][] objects, char separator1, char separator2) {
      StringBuffer stringBuffer = new StringBuffer() ;
      for (int idx1 = 0; idx1 < objects.length; idx1++) {
         for (int idx2 = 0; idx2 < objects[idx1].length; idx2++) {
            stringBuffer.append(objects[idx1][idx2]) ;
            if (idx2 < objects[idx1].length - 1) {
               stringBuffer.append(separator1) ;
               stringBuffer.append(' ') ;
            }
         }
         stringBuffer.append(separator2) ;
      }
      return stringBuffer.toString() ;
   }

   /**
    * Converts an array of ints to a string.
    *
    * @param values  an array of ints to be converted to a string
    * @param separator the separator to be placed between each int.
    *
    * @return    a string with all ints in the array.
    */
   public static String toString(int[] values, char separator) {
      StringBuffer stringBuffer = new StringBuffer() ;
      for (int idx = 0; idx < values.length; idx++) {
         stringBuffer.append(values[idx]) ;
         if (idx < values.length - 1) {
            stringBuffer.append(separator) ;
            stringBuffer.append(' ') ;
         }
      }
      return stringBuffer.toString() ;
   }

  /**
    * Converts a (possibly) null-terminated array of bytes to a string,
    * assuming 8859-1 encoding.
    *
    * @param data an array of bytes to be converted
    *
    * @return    A string representation of the bytes.
    */
   public static String toString(byte[] data) {
      int length ;
      for (length = 0; length < data.length; length++) {
         if (data[length] == 0) {
            break ;
         }
      }
      try {
         if (length > 0) {
            return new String(data, 0, length, "8859_1") ;
         } else {
            return "" ;
         }
      } catch (UnsupportedEncodingException e) {
         Debug.logException(e) ;
         return "" ;
      }
   }


   /**
    * Formats a date as MM:DD:YY HH:MM:SS.MSEC
    *
    * @param date A Date to be formmatted
    *
    * @return Date represented as MM:DD:YY HH:MM:SS.MSEC
    */
   public static String formatSimpleDate(Date date) {
      return _dateFormat.format(date) ;
   }

   /**
    * Formats a date as MM:DD:YY HH:MM:SS.MSEC
    *
    * @param time Time in milliseconds
    *
    * @return Date represented as MM:DD:YY HH:MM:SS.MSEC
    */
   public static String formatSimpleDate(long time) {
      return _dateFormat.format(new Date(time)) ;
   }

   //**********
   // Class attributes and constants
   //**********
   private static final String[] _hexValues = {
      "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", 
      "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", 
      "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F",
      "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F",
      "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F",
      "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F",
      "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F",
      "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F",
      "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F",
      "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
      "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF",
      "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF",
      "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF",
      "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF",
      "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF",
      "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"
   } ;

   private static final DateFormat   _dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss.SSS") ;
}



// End of StringUtility.java

