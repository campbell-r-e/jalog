package jalog.jlogimpl.filehandler.adif;

import jalog.factory.AdifFileFactory;
import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.Mode;
import jalog.jlog.util.QslRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ReadAdifFileEdgeCaseTest {

    @TempDir
    Path tempDir;

    private LogBook readAdif(String adif) throws Exception {
        Path file = tempDir.resolve("test.adi");
        Files.writeString(file, adif);
        LogBook logBook = DefaultLogBookFactory.createLogBook("1.0");
        AdifFileFactory.createReadAdifFile(file.toFile()).execute(logBook);
        return logBook;
    }

    @Test
    void unknownMode_setsUnknownModeField() throws Exception {
        String adif = "<MODE:6>OLIVIA<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        assertEquals(1, book.getLogEntries().size());
        LogEntry entry = book.getEntry(0);
        assertEquals(Mode.Unknown, entry.getFrequencyInformation().getMode());
        assertEquals("OLIVIA", entry.getFrequencyInformation().getUnknownMode());
    }

    @Test
    void unknownQslReceived_setsUnknownQslReceivedField() throws Exception {
        String adif = "<QSL_RCVD:1>X<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        LogEntry entry = book.getEntry(0);
        assertEquals(QslRequest.Unknown, entry.getQsl().isQslReceived());
        assertEquals("X", entry.getQsl().getUnknownQslReceived());
    }

    @Test
    void unknownQslSent_setsUnknownQslSentField() throws Exception {
        String adif = "<QSL_SENT:1>Z<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        LogEntry entry = book.getEntry(0);
        assertEquals(QslRequest.Unknown, entry.getQsl().isQslSent());
        assertEquals("Z", entry.getQsl().getUnknownQslSent());
    }

    @Test
    void unknownToken_ignoredGracefully() throws Exception {
        // An unrecognized field should just be ignored (printed to stdout but not thrown)
        String adif = "<UNKNOWN_FIELD:4>DATA<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        assertEquals(1, book.getLogEntries().size());
        assertEquals("W1AW", book.getEntry(0).getCallsign().getContactStation());
    }

    @Test
    void timeOn_fourCharFormat_parsedCorrectly() throws Exception {
        // TIME_ON with HHMM (4 chars, no seconds) should use second=0
        String adif = "<QSO_DATE:8>20250615<TIME_ON:4>1430<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        LogEntry entry = book.getEntry(0);
        assertNotNull(entry.getEra().getStartDate());
    }

    @Test
    void iota_unknownPrefix_setsUnknownIota() throws Exception {
        // "XX" is not a recognized IOTA prefix → IotaEnum.Unknown path
        String adif = "<IOTA:6>XX-001<CALL:4>W1AW<eor>\n";
        LogBook book = readAdif(adif);
        LogEntry entry = book.getEntry(0);
        assertNotNull(entry.getContactStationInformation().getIota());
        assertEquals("XX", entry.getContactStationInformation().getIota().getUnknownIota());
    }

    @Test
    void multipleEntriesWithNewlines_parsedCorrectly() throws Exception {
        String adif = "<CALL:4>W1AW<eor>\n<CALL:6>KC0ZPS<eor>\n";
        LogBook book = readAdif(adif);
        assertEquals(2, book.getLogEntries().size());
        assertEquals("W1AW", book.getEntry(0).getCallsign().getContactStation());
        assertEquals("KC0ZPS", book.getEntry(1).getCallsign().getContactStation());
    }
}
