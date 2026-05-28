package jalog.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @TempDir
    Path tempDir;

    @Test
    void getUtcTimeZone_isUTC() {
        assertEquals(0, Utility.getUtcTimeZone().getRawOffset());
        assertEquals("UTC", Utility.getUtcTimeZone().getID());
    }

    @Test
    void getDateFormat_formatsDate() {
        Date date = new Date(0); // epoch
        String formatted = Utility.getDateFormat().format(date);
        assertNotNull(formatted);
        assertFalse(formatted.isEmpty());
    }

    @Test
    void getTimeFormat_formatsTime() {
        Date date = new Date(0);
        String formatted = Utility.getTimeFormat().format(date);
        assertNotNull(formatted);
        assertFalse(formatted.isEmpty());
    }

    @Test
    void getAdifDateFormat_producesEightCharDate() {
        SimpleDateFormat fmt = Utility.getAdifDateFormat();
        // ADIF date format is yyyyMMdd — should produce 8 chars
        String formatted = fmt.format(new Date(0));
        assertEquals(8, formatted.length());
    }

    @Test
    void getAdifTimeFormat_producesSixCharTime() {
        SimpleDateFormat fmt = Utility.getAdifTimeFormat();
        // ADIF time format is HHmmss — should produce 6 chars
        String formatted = fmt.format(new Date(0));
        assertEquals(6, formatted.length());
    }

    @Test
    void setLastFilePath_roundTrips() {
        Utility.setLastFilePath("/tmp/test");
        assertEquals("/tmp/test", Utility.getLastFilePath());
    }

    @Test
    void getLastFilePath_default_isNotNull() {
        assertNotNull(Utility.getLastFilePath());
    }

    @Test
    void addFileHistory_fileIsRetrievable() {
        File file = tempDir.resolve("mylog.adi").toFile();
        Utility.addFileHistory(file);
        assertTrue(Utility.getFileHistory().contains(file.getAbsolutePath()));
    }

    @Test
    void addFileHistory_duplicateNotAdded() {
        File file = tempDir.resolve("unique.adi").toFile();
        int sizeBefore = Utility.getFileHistory().size();
        Utility.addFileHistory(file);
        int sizeAfter = Utility.getFileHistory().size();
        Utility.addFileHistory(file); // second add
        assertEquals(sizeAfter, Utility.getFileHistory().size());
    }

    @Test
    void removeFileHistory_removesFile() {
        File file = tempDir.resolve("toremove.adi").toFile();
        Utility.addFileHistory(file);
        Utility.removeFileHistory(file);
        assertFalse(Utility.getFileHistory().contains(file.getAbsolutePath()));
    }

    @Test
    void addFileHistory_maxFiveEntries() {
        // Add 6 files; only the most recent 5 (excluding the first original) should remain
        for (int i = 0; i < 6; i++) {
            Utility.addFileHistory(tempDir.resolve("file" + i + ".adi").toFile());
        }
        assertTrue(Utility.getFileHistory().size() <= 5);
    }
}
