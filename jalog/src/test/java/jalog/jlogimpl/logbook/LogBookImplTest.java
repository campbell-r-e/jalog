package jalog.jlogimpl.logbook;

import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlogimpl.logbook.LogBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogBookImplTest {

    private LogBook logBook;

    @BeforeEach
    void setUp() {
        logBook = DefaultLogBookFactory.createLogBook("1.0");
    }

    @Test
    void getVersion_returnsVersionPassedToConstructor() {
        assertEquals("1.0", logBook.getVersion());
    }

    @Test
    void setVersion_updatesVersion() {
        logBook.setVersion("2.0");
        assertEquals("2.0", logBook.getVersion());
    }

    @Test
    void newLogBook_hasNoEntries() {
        assertTrue(logBook.getLogEntries().isEmpty());
    }

    @Test
    void addEntry_entryIsRetrievable() {
        LogEntry entry = DefaultLogBookFactory.createLogEntry();
        entry.getCallsign().setContactStation("W1AW");
        logBook.addEntry(entry);

        assertEquals(1, logBook.getLogEntries().size());
        assertEquals("W1AW", logBook.getEntry(0).getCallsign().getContactStation());
    }

    @Test
    void addMultipleEntries_allRetrievable() {
        for (int i = 0; i < 5; i++) {
            LogEntry entry = DefaultLogBookFactory.createLogEntry();
            entry.getCallsign().setContactStation("K" + i + "ABC");
            logBook.addEntry(entry);
        }
        assertEquals(5, logBook.getLogEntries().size());
        assertEquals("K3ABC", logBook.getEntry(3).getCallsign().getContactStation());
    }

    @Test
    void deleteEntry_removesCorrectEntry() {
        LogEntry first = DefaultLogBookFactory.createLogEntry();
        first.getCallsign().setContactStation("FIRST");
        LogEntry second = DefaultLogBookFactory.createLogEntry();
        second.getCallsign().setContactStation("SECOND");

        logBook.addEntry(first);
        logBook.addEntry(second);
        logBook.deleteEntry(0);

        assertEquals(1, logBook.getLogEntries().size());
        assertEquals("SECOND", logBook.getEntry(0).getCallsign().getContactStation());
    }

    @Test
    void setEntry_replacesEntryAtIndex() {
        LogEntry original = DefaultLogBookFactory.createLogEntry();
        original.getCallsign().setContactStation("ORIGINAL");
        logBook.addEntry(original);

        LogEntry replacement = DefaultLogBookFactory.createLogEntry();
        replacement.getCallsign().setContactStation("REPLACEMENT");
        logBook.setEntry(0, replacement);

        assertEquals("REPLACEMENT", logBook.getEntry(0).getCallsign().getContactStation());
    }

    @Test
    void toAdifString_emptyBook_returnsEmptyString() {
        assertEquals("", logBook.toAdifString().trim());
    }

    @Test
    void toAdifString_singleEntry_containsEor() {
        LogEntry entry = DefaultLogBookFactory.createLogEntry();
        entry.getCallsign().setContactStation("W1AW");
        logBook.addEntry(entry);

        String adif = logBook.toAdifString();
        assertTrue(adif.contains("<eor>"));
        assertTrue(adif.contains("W1AW"));
    }

    @Test
    void toAdifString_twoEntries_containsTwoEorTags() {
        logBook.addEntry(DefaultLogBookFactory.createLogEntry());
        logBook.addEntry(DefaultLogBookFactory.createLogEntry());

        long eorCount = logBook.toAdifString().chars()
                .filter(c -> c == '\n')
                .count();
        // Each entry ends with "<eor>\n"
        assertTrue(logBook.toAdifString().contains("<eor>"));
        assertEquals(2, countOccurrences(logBook.toAdifString(), "<eor>"));
    }

    @Test
    void toString_containsLogBookImpl() {
        assertTrue(((LogBookImpl) logBook).toString().contains("LogBookImpl"));
    }

    private int countOccurrences(String text, String pattern) {
        int count = 0;
        int idx = 0;
        while ((idx = text.indexOf(pattern, idx)) != -1) {
            count++;
            idx += pattern.length();
        }
        return count;
    }
}
