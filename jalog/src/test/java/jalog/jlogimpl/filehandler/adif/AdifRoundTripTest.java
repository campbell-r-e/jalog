package jalog.jlogimpl.filehandler.adif;

import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.Band;
import jalog.jlog.util.IotaEnum;
import jalog.jlog.util.Mode;
import jalog.jlog.util.QslRequest;
import jalog.util.Utility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.*;

class AdifRoundTripTest {

    @TempDir
    Path tempDir;

    private static final SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");

    @Test
    void callsign_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getCallsign().setContactStation("W1AW");
        source.getCallsign().setOperatingStation("KC0ZPS");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("W1AW", result.getCallsign().getContactStation());
        assertEquals("KC0ZPS", result.getCallsign().getOperatingStation());
    }

    @Test
    void rst_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getRst().setRstReceived("599");
        source.getRst().setRstSent("579");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("599", result.getRst().getRstReceived());
        assertEquals("579", result.getRst().getRstSent());
    }

    @Test
    void bandAndMode_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getFrequencyInformation().setBand(Band._20m);
        source.getFrequencyInformation().setMode(Mode.CW);
        source.getFrequencyInformation().setFrequency("14.025");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals(Band._20m, result.getFrequencyInformation().getBand());
        assertEquals(Mode.CW, result.getFrequencyInformation().getMode());
        assertEquals("14.025", result.getFrequencyInformation().getFrequency());
    }

    @Test
    void txAndRxPower_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getFrequencyInformation().setTxPower(100);
        source.getFrequencyInformation().setRxPower(5);
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals(100, result.getFrequencyInformation().getTxPower());
        assertEquals(5, result.getFrequencyInformation().getRxPower());
    }

    @Test
    void qsoDate_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();

        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        cal.set(Calendar.MILLISECOND, 0);
        source.getEra().setStartDate(cal.getTime());
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
        dateFmt.setTimeZone(UTC);
        SimpleDateFormat timeFmt = new SimpleDateFormat("HHmmss");
        timeFmt.setTimeZone(UTC);

        assertEquals("20250615", dateFmt.format(result.getEra().getStartDate()));
        assertEquals("143000", timeFmt.format(result.getEra().getStartDate()));
    }

    @Test
    void timeOff_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();

        GregorianCalendar startCal = new GregorianCalendar(UTC);
        startCal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        source.getEra().setStartDate(startCal.getTime());

        GregorianCalendar endCal = new GregorianCalendar(UTC);
        endCal.set(2025, Calendar.JUNE, 15, 14, 45, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        source.getEra().setEndDate(endCal.getTime());
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        SimpleDateFormat timeFmt = new SimpleDateFormat("HHmmss");
        timeFmt.setTimeZone(UTC);
        assertEquals("144500", timeFmt.format(result.getEra().getEndDate()));
    }

    @Test
    void address_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getContactAddress().setName("John Doe");
        source.getContactAddress().setAddress("123 Main St");
        source.getContactAddress().setCounty("TestCounty");
        source.getContactAddress().setUsState("CA");
        source.getContactAddress().setContinent("NA");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("John Doe", result.getContactAddress().getName());
        assertEquals("123 Main St", result.getContactAddress().getAddress());
        assertEquals("TestCounty", result.getContactAddress().getCounty());
        assertEquals("CA", result.getContactAddress().getUsState());
        assertEquals("NA", result.getContactAddress().getContinent());
    }

    @Test
    void contactStationInfo_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getContactStationInformation().setQth("Springfield");
        source.getContactStationInformation().setGridSquare("EN52");
        source.getContactStationInformation().setDxcc("291");
        source.getContactStationInformation().setCqZone("4");
        source.getContactStationInformation().setItuZone("7");
        source.getContactStationInformation().setAge("35");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("Springfield", result.getContactStationInformation().getQth());
        assertEquals("EN52", result.getContactStationInformation().getGridSquare());
        assertEquals("291", result.getContactStationInformation().getDxcc());
        assertEquals("4", result.getContactStationInformation().getCqZone());
        assertEquals("7", result.getContactStationInformation().getItuZone());
        assertEquals("35", result.getContactStationInformation().getAge());
    }

    @Test
    void iota_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getContactStationInformation().setIota(DefaultLogBookFactory.createIota(IotaEnum.AF, 10));
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertNotNull(result.getContactStationInformation().getIota());
        assertEquals(IotaEnum.AF, result.getContactStationInformation().getIota().getIotaEnum());
        assertEquals(10, result.getContactStationInformation().getIota().getValue());
    }

    @Test
    void qsl_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getQsl().setQslSent(QslRequest.Yes);
        source.getQsl().setQslReceived(QslRequest.No);
        source.getQsl().setQslMessage("73 de W1AW");
        source.getQsl().setQslVia("bureau");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals(QslRequest.Yes, result.getQsl().isQslSent());
        assertEquals(QslRequest.No, result.getQsl().isQslReceived());
        assertEquals("73 de W1AW", result.getQsl().getQslMessage());
        assertEquals("bureau", result.getQsl().getQslVia());
    }

    @Test
    void satellite_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getSatellite().setSatelliteName("AO-7");
        source.getSatellite().setSatelliteMode("U/V");
        source.getSatellite().setPropMode("SAT");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("AO-7", result.getSatellite().getSatelliteName());
        assertEquals("U/V", result.getSatellite().getSatelliteMode());
        assertEquals("SAT", result.getSatellite().getPropMode());
    }

    @Test
    void contest_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getContest().setTransmittedSerialNumber("001");
        source.getContest().setReceivedSerialNumber("042");
        source.getContest().setContestId("CQ-WW-CW");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("001", result.getContest().getTransmittedSerialNumber());
        assertEquals("042", result.getContest().getReceivedSerialNumber());
        assertEquals("CQ-WW-CW", result.getContest().getContestId());
    }

    @Test
    void misc_roundTrips() throws Exception {
        LogBook book = buildBook();
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getMisc().setComment("Good signal");
        source.getMisc().setNotes("Worked on 40m");
        source.getMisc().setArrlSection("ENY");
        source.getMisc().setTenTen("12345");
        source.getMisc().setVeProv("ON");
        source.getMisc().setWpxPrefix("W1");
        book.addEntry(source);

        LogEntry result = writeAndRead(book).getEntry(0);
        assertEquals("Good signal", result.getMisc().getComment());
        assertEquals("Worked on 40m", result.getMisc().getNotes());
        assertEquals("ENY", result.getMisc().getArrlSection());
        assertEquals("12345", result.getMisc().getTenTen());
        assertEquals("ON", result.getMisc().getVeProv());
        assertEquals("W1", result.getMisc().getWpxPrefix());
    }

    @Test
    void multipleEntries_allPreserved() throws Exception {
        LogBook book = buildBook();
        for (int i = 0; i < 5; i++) {
            LogEntry entry = DefaultLogBookFactory.createLogEntry();
            entry.getCallsign().setContactStation("K" + i + "TEST");
            book.addEntry(entry);
        }

        LogBook result = writeAndRead(book);
        assertEquals(5, result.getLogEntries().size());
        for (int i = 0; i < 5; i++) {
            assertEquals("K" + i + "TEST", result.getEntry(i).getCallsign().getContactStation());
        }
    }

    // --- helpers ---

    private LogBook buildBook() {
        return DefaultLogBookFactory.createLogBook("1.0");
    }

    private LogBook writeAndRead(LogBook book) throws Exception {
        File file = tempDir.resolve("test.adi").toFile();
        new WriteAdifFile(file, book).execute();

        LogBook result = DefaultLogBookFactory.createLogBook("1.0");
        new ReadAdifFile(file).execute(result);
        return result;
    }
}
