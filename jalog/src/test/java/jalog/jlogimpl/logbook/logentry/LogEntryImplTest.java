package jalog.jlogimpl.logbook.logentry;

import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.Band;
import jalog.jlog.util.Mode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryImplTest {

    @Test
    void defaultConstructor_allSubcomponentsNonNull() {
        LogEntryImpl entry = new LogEntryImpl();
        assertNotNull(entry.getCallsign());
        assertNotNull(entry.getEra());
        assertNotNull(entry.getRst());
        assertNotNull(entry.getFrequencyInformation());
        assertNotNull(entry.getContactAddress());
        assertNotNull(entry.getContactStationInformation());
        assertNotNull(entry.getQsl());
        assertNotNull(entry.getSatellite());
        assertNotNull(entry.getContest());
        assertNotNull(entry.getMisc());
    }

    @Test
    void copyConstructor_copiesRstAndCallsignOperator() {
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getCallsign().setOperatingStation("W1AW");
        source.getRst().setRstSent("599");
        source.getRst().setRstReceived("579");
        source.getFrequencyInformation().setBand(Band._20m);
        source.getFrequencyInformation().setMode(Mode.CW);
        source.getFrequencyInformation().setTxPower(100);
        source.getFrequencyInformation().setRxPower(10);

        LogEntryImpl copy = new LogEntryImpl(source);

        assertEquals("W1AW", copy.getCallsign().getOperatingStation());
        assertEquals("599", copy.getRst().getRstSent());
        assertEquals("579", copy.getRst().getRstReceived());
        assertEquals(Band._20m, copy.getFrequencyInformation().getBand());
        assertEquals(Mode.CW, copy.getFrequencyInformation().getMode());
        assertEquals(100, copy.getFrequencyInformation().getTxPower());
        assertEquals(10, copy.getFrequencyInformation().getRxPower());
    }

    @Test
    void setRst_replacesRstObject() {
        LogEntryImpl entry = new LogEntryImpl();
        RstImpl newRst = new RstImpl("599", "579");
        entry.setRst(newRst);
        assertSame(newRst, entry.getRst());
        assertEquals("599", entry.getRst().getRstReceived());
        assertEquals("579", entry.getRst().getRstSent());
    }

    @Test
    void toAdifString_emptyEntry_returnsEmpty() {
        LogEntryImpl entry = new LogEntryImpl();
        assertEquals("", entry.toAdifString());
    }

    @Test
    void toAdifString_withCallsign_containsCallTag() {
        LogEntryImpl entry = new LogEntryImpl();
        entry.getCallsign().setContactStation("W1AW");
        assertTrue(entry.toAdifString().contains("<CALL:4>W1AW"));
    }

    @Test
    void toString_doesNotThrow() {
        LogEntryImpl entry = new LogEntryImpl();
        entry.getCallsign().setContactStation("W1AW");
        assertDoesNotThrow(() -> entry.toString());
        assertTrue(entry.toString().contains("W1AW"));
    }

    @Test
    void copyConstructor_doesNotCopyContactStation() {
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getCallsign().setContactStation("W1AW");

        LogEntryImpl copy = new LogEntryImpl(source);
        assertEquals("", copy.getCallsign().getContactStation());
    }
}
