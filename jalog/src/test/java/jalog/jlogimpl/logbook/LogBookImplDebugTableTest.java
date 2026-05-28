package jalog.jlogimpl.logbook;

import jalog.debug.DebugTable;
import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.Band;
import jalog.jlog.util.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.*;

class LogBookImplDebugTableTest {

    private static final SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");
    private LogBook logBook;
    private DebugTable debugTable;

    @BeforeEach
    void setUp() {
        logBook = DefaultLogBookFactory.createLogBook("1.0");

        LogEntry entry = DefaultLogBookFactory.createLogEntry();
        entry.getCallsign().setContactStation("W1AW");
        entry.getCallsign().setOperatingStation("KC0ZPS");

        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        entry.getEra().setStartDate(cal.getTime());
        entry.getEra().setEndDate(cal.getTime());

        entry.getRst().setRstReceived("599");
        entry.getRst().setRstSent("579");
        entry.getFrequencyInformation().setMode(Mode.CW);
        entry.getFrequencyInformation().setBand(Band._20m);
        entry.getFrequencyInformation().setFrequency("14.025");
        entry.getFrequencyInformation().setTxPower(100);
        entry.getFrequencyInformation().setRxPower(5);

        logBook.addEntry(entry);
        debugTable = ((LogBookImpl) logBook).getDebugTable();
    }

    @Test
    void getTableName_returnsLogBookImpl() {
        assertEquals("LogBookImpl", debugTable.getTableName());
    }

    @Test
    void getColumnCount_returns15() {
        assertEquals(15, debugTable.getColumnCount());
    }

    @Test
    void getRowCount_matchesEntryCount() {
        assertEquals(1, debugTable.getRowCount());
    }

    @Test
    void getColumnTitle_allColumnsHaveTitles() {
        for (int i = 0; i < debugTable.getColumnCount(); i++) {
            assertNotNull(debugTable.getColumnTitle(i));
            assertFalse(debugTable.getColumnTitle(i).isEmpty());
        }
    }

    @Test
    void getTableCell_col0_returnsRowIndex() {
        assertEquals("0", debugTable.getTableCell(0, 0));
    }

    @Test
    void getTableCell_col1_returnsContactStation() {
        assertEquals("W1AW", debugTable.getTableCell(0, 1));
    }

    @Test
    void getTableCell_col2_returnsOperatingStation() {
        assertEquals("KC0ZPS", debugTable.getTableCell(0, 2));
    }

    @Test
    void getTableCell_col3_returnsStartDate() {
        assertNotNull(debugTable.getTableCell(0, 3));
    }

    @Test
    void getTableCell_col4_returnsEndDate() {
        assertNotNull(debugTable.getTableCell(0, 4));
    }

    @Test
    void getTableCell_col5_returnsStartDateString() {
        assertNotNull(debugTable.getTableCell(0, 5));
        assertFalse(((String) debugTable.getTableCell(0, 5)).isEmpty());
    }

    @Test
    void getTableCell_col6_returnsEndTimeString() {
        assertNotNull(debugTable.getTableCell(0, 6));
    }

    @Test
    void getTableCell_col7_returnsRstReceived() {
        assertEquals("599", debugTable.getTableCell(0, 7));
    }

    @Test
    void getTableCell_col8_returnsRstSent() {
        assertEquals("579", debugTable.getTableCell(0, 8));
    }

    @Test
    void getTableCell_col9_returnsMode() {
        assertEquals(Mode.CW, debugTable.getTableCell(0, 9));
    }

    @Test
    void getTableCell_col10_returnsUnknownMode() {
        assertEquals("", debugTable.getTableCell(0, 10));
    }

    @Test
    void getTableCell_col11_returnsBand() {
        assertEquals(Band._20m, debugTable.getTableCell(0, 11));
    }

    @Test
    void getTableCell_col12_returnsFrequency() {
        assertEquals("14.025", debugTable.getTableCell(0, 12));
    }

    @Test
    void getTableCell_col13_returnsTxPower() {
        assertEquals(100, debugTable.getTableCell(0, 13));
    }

    @Test
    void getTableCell_col14_returnsRxPower() {
        assertEquals(5, debugTable.getTableCell(0, 14));
    }

    @Test
    void getTableCell_invalidColumn_returnsError() {
        assertEquals("Error", debugTable.getTableCell(0, 99));
    }
}
