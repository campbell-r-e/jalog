package jalog.jlogimpl.logbook.logentry;

import jalog.jlog.util.Band;
import jalog.jlog.util.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyInformationImplTest {

    private FrequencyInformationImpl freq;

    @BeforeEach
    void setUp() {
        freq = new FrequencyInformationImpl();
    }

    @Test
    void defaults_areBlankAndEmpty() {
        assertEquals(Mode.Blank, freq.getMode());
        assertEquals("", freq.getUnknownMode());
        assertEquals(Band.Blank, freq.getBand());
        assertEquals("", freq.getFrequency());
        assertNull(freq.getTxPower());
        assertNull(freq.getRxPower());
    }

    @Test
    void setMode_roundTrips() {
        freq.setMode(Mode.CW);
        assertEquals(Mode.CW, freq.getMode());
    }

    @Test
    void setUnknownMode_roundTrips() {
        freq.setUnknownMode("FT8X");
        assertEquals("FT8X", freq.getUnknownMode());
    }

    @Test
    void setBand_roundTrips() {
        freq.setBand(Band._20m);
        assertEquals(Band._20m, freq.getBand());
    }

    @Test
    void setFrequency_roundTrips() {
        freq.setFrequency("14.225");
        assertEquals("14.225", freq.getFrequency());
    }

    @Test
    void setTxPower_roundTrips() {
        freq.setTxPower(100);
        assertEquals(100, freq.getTxPower());
    }

    @Test
    void setRxPower_roundTrips() {
        freq.setRxPower(5);
        assertEquals(5, freq.getRxPower());
    }

    @Test
    void toAdifString_allBlank_returnsEmpty() {
        assertEquals("", freq.toAdifString());
    }

    @Test
    void toAdifString_knownMode_writesModeTag() {
        freq.setMode(Mode.SSB);
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<MODE:"));
        assertTrue(adif.contains(">SSB"));
    }

    @Test
    void toAdifString_unknownMode_writesUnknownModeTag() {
        freq.setUnknownMode("FT8X");
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<MODE:4>FT8X"));
    }

    @Test
    void toAdifString_band_writesBandTag() {
        freq.setBand(Band._40m);
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<BAND:3>40m"));
    }

    @Test
    void toAdifString_frequency_writesFreqTag() {
        freq.setFrequency("7.050");
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<FREQ:5>7.050"));
    }

    @Test
    void toAdifString_txPower_writesTxPwrTag() {
        freq.setTxPower(50);
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<TX_PWR:2>50"));
    }

    @Test
    void toAdifString_rxPower_writesRxPwrTag() {
        freq.setRxPower(3);
        String adif = freq.toAdifString();
        assertTrue(adif.contains("<RX_PWR:1>3"));
    }

    @Test
    void toString_containsFields() {
        freq.setMode(Mode.FM);
        freq.setBand(Band._2m);
        freq.setFrequency("146.520");
        String str = freq.toString();
        assertTrue(str.contains("FM"));
        assertTrue(str.contains("2m"));
        assertTrue(str.contains("146.520"));
    }

    @Test
    void band_getValues_nonEmpty() {
        assertNotNull(Band.getValues());
        assertTrue(Band.getValues().length > 0);
    }

    @Test
    void band_getStrToBandType_notNull() {
        assertNotNull(Band.getStrToBandType());
    }

    @Test
    void band_strToBandType_lookup20m() {
        Object result = Band.getStrToBandType().getObjectValue("20m");
        assertSame(Band._20m, result);
    }

    @Test
    void mode_getValues_nonEmpty() {
        assertNotNull(Mode.getValues());
        assertTrue(Mode.getValues().length > 0);
    }

    @Test
    void mode_getStrToModeType_notNull() {
        assertNotNull(Mode.getStrToModeType());
    }

    @Test
    void mode_strToModeType_lookupCW() {
        Object result = Mode.getStrToModeType().getObjectValue("CW");
        assertSame(Mode.CW, result);
    }
}
