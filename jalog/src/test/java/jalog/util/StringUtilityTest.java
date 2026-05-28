package jalog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilityTest {

    @Test
    void rawIPToString_typicalAddress() {
        byte[] ip = {(byte) 192, (byte) 168, 1, 1};
        assertEquals("192.168.1.1", StringUtility.rawIPToString(ip));
    }

    @Test
    void rawIPToString_allZeros() {
        byte[] ip = {0, 0, 0, 0};
        assertEquals("0.0.0.0", StringUtility.rawIPToString(ip));
    }

    @Test
    void rawIPToString_allFF() {
        byte[] ip = {(byte) 255, (byte) 255, (byte) 255, (byte) 255};
        assertEquals("255.255.255.255", StringUtility.rawIPToString(ip));
    }

    @Test
    void hexToBytes_roundTrip() {
        byte[] original = {0x0A, 0x1B, 0x2C, (byte) 0xFF};
        String hex = StringUtility.toHexString(original);
        byte[] result = StringUtility.hexToBytes(hex);
        assertArrayEquals(original, result);
    }

    @Test
    void hexToBytes_simple() {
        byte[] result = StringUtility.hexToBytes("0A1B");
        assertEquals(2, result.length);
        assertEquals(0x0A, result[0]);
        assertEquals(0x1B, result[1]);
    }

    @Test
    void toHexChar_digits() {
        assertEquals('0', StringUtility.toHexChar(0));
        assertEquals('9', StringUtility.toHexChar(9));
    }

    @Test
    void toHexChar_letters() {
        assertEquals('A', StringUtility.toHexChar(10));
        assertEquals('F', StringUtility.toHexChar(15));
    }

    @Test
    void toHexChar_masksBeyondNibble() {
        assertEquals('F', StringUtility.toHexChar(0x1F));
    }

    @Test
    void toHexString_byte_knownValues() {
        assertEquals("00", StringUtility.toHexString((byte) 0x00));
        assertEquals("FF", StringUtility.toHexString((byte) 0xFF));
        assertEquals("0A", StringUtility.toHexString((byte) 0x0A));
    }

    @Test
    void toHexString_byteArray_noSeparator() {
        byte[] data = {0x0A, 0x1B, (byte) 0xFF};
        assertEquals("0A1BFF", StringUtility.toHexString(data));
    }

    @Test
    void toHexString_byteArray_withSeparator() {
        byte[] data = {0x0A, 0x1B};
        assertEquals("0A:1B:", StringUtility.toHexString(data, ':'));
    }

    @Test
    void toHexString_emptyArray() {
        assertEquals("", StringUtility.toHexString(new byte[0]));
    }

    @Test
    void octetStr2HexStr_singleChar() {
        String result = StringUtility.octetStr2HexStr("A", "");
        assertEquals("41", result);
    }

    @Test
    void octetStr2HexStr_withDelimiter() {
        String result = StringUtility.octetStr2HexStr("AB", "-");
        assertEquals("41-42", result);
    }

    @Test
    void toString_objectArrayWithSeparator() {
        Object[] objs = {"a", "b", "c"};
        assertEquals("a, b, c", StringUtility.toString(objs, ','));
    }

    @Test
    void toString_singleElementArray() {
        Object[] objs = {"only"};
        assertEquals("only", StringUtility.toString(objs, ','));
    }

    @Test
    void toString_intArrayWithSeparator() {
        int[] values = {1, 2, 3};
        assertEquals("1, 2, 3", StringUtility.toString(values, ','));
    }

    @Test
    void toString_byteArray_nullTerminated() {
        byte[] data = {'H', 'i', 0, 'X'};
        assertEquals("Hi", StringUtility.toString(data));
    }

    @Test
    void toString_byteArray_empty() {
        byte[] data = {0};
        assertEquals("", StringUtility.toString(data));
    }

    @Test
    void toString_byteArray_noNullTerminator() {
        byte[] data = {'H', 'i'};
        assertEquals("Hi", StringUtility.toString(data));
    }

    @Test
    void toBytes_fitsExactly() {
        byte[] dest = new byte[3];
        StringUtility.toBytes("ABC", dest);
        assertEquals('A', dest[0]);
        assertEquals('B', dest[1]);
        assertEquals('C', dest[2]);
    }

    @Test
    void toBytes_shorterThanDest_paddedWithZeros() {
        byte[] dest = new byte[5];
        StringUtility.toBytes("AB", dest);
        assertEquals('A', dest[0]);
        assertEquals('B', dest[1]);
        assertEquals(0, dest[2]);
        assertEquals(0, dest[3]);
        assertEquals(0, dest[4]);
    }

    @Test
    void toBytes_longerThanDest_truncated() {
        byte[] dest = new byte[2];
        StringUtility.toBytes("ABCDE", dest);
        assertEquals('A', dest[0]);
        assertEquals('B', dest[1]);
    }

    @Test
    void toString_2dObjectArray_formatsRows() {
        Object[][] objs = {{"a", "b"}, {"c", "d"}};
        String result = StringUtility.toString(objs, ',', '\n');
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("d"));
        assertTrue(result.contains("\n"));
    }

    @Test
    void toString_2dObjectArray_singleRow() {
        Object[][] objs = {{"x", "y", "z"}};
        String result = StringUtility.toString(objs, '|', '\n');
        assertTrue(result.contains("x"));
        assertTrue(result.contains("y"));
        assertTrue(result.contains("z"));
    }

    @Test
    void formatSimpleDate_date_notEmpty() {
        String result = StringUtility.formatSimpleDate(new java.util.Date(0));
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void formatSimpleDate_millis_notEmpty() {
        String result = StringUtility.formatSimpleDate(0L);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void formatSimpleDate_millis_matchesDate() {
        long ts = 1_000_000_000L;
        assertEquals(StringUtility.formatSimpleDate(new java.util.Date(ts)),
                     StringUtility.formatSimpleDate(ts));
    }

    @Test
    void octetStr2HexStr_charBelow16_paddedWithZero() {
        // tab character = 0x09, below 16 → padded as "09"
        String result = StringUtility.octetStr2HexStr("\t", "");
        assertEquals("09", result);
    }
}
