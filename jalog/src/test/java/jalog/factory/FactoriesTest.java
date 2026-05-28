package jalog.factory;

import jalog.jlog.logbook.LogBook;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.ApiControl;
import jalog.jlog.util.IotaEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FactoriesTest {

    @TempDir
    Path tempDir;

    // ApiControl

    @Test
    void apiControl_getVersion_notNull() {
        assertNotNull(ApiControl.getVersion());
    }

    @Test
    void apiControl_getInstance_notNull() {
        assertNotNull(ApiControl.getInstance());
    }

    @Test
    void apiControl_getInstance_sameInstance() {
        assertSame(ApiControl.getInstance(), ApiControl.getInstance());
    }

    @Test
    void apiControl_getSupportManager_notNull() {
        assertNotNull(ApiControl.getSupportManager());
    }

    // AdifFileFactory

    @Test
    void adifFileFactory_createReadAdifFile_notNull() {
        assertNotNull(AdifFileFactory.createReadAdifFile(new File("test.adi")));
    }

    @Test
    void adifFileFactory_createWriteAdifFile_notNull() {
        LogBook logBook = DefaultLogBookFactory.createLogBook("1.0");
        assertNotNull(AdifFileFactory.createWriteAdifFile(tempDir.resolve("out.adi").toFile(), logBook));
    }

    // CallsignInformationFactory

    @Test
    void callsignInformationFactory_createCallsignInformation_notNull() {
        assertNotNull(CallsignInformationFactory.createCallsignInformation());
    }

    // DefaultLogBookFactory — individual factory methods

    @Test
    void defaultLogBookFactory_createCallsign_notNull() {
        assertNotNull(DefaultLogBookFactory.createCallsign());
    }

    @Test
    void defaultLogBookFactory_createRstWithValues_notNull() {
        assertNotNull(DefaultLogBookFactory.createRst("599", "579"));
    }

    @Test
    void defaultLogBookFactory_createRstDefault_notNull() {
        assertNotNull(DefaultLogBookFactory.createRst());
    }

    @Test
    void defaultLogBookFactory_createFrequencyInformation_notNull() {
        assertNotNull(DefaultLogBookFactory.createFrequencyInformation());
    }

    @Test
    void defaultLogBookFactory_createContactAddress_notNull() {
        assertNotNull(DefaultLogBookFactory.createContactAddress());
    }

    @Test
    void defaultLogBookFactory_createContactStationInformation_notNull() {
        assertNotNull(DefaultLogBookFactory.createContactStationInformation());
    }

    @Test
    void defaultLogBookFactory_createEra_notNull() {
        assertNotNull(DefaultLogBookFactory.createEra());
    }

    @Test
    void defaultLogBookFactory_createMisc_notNull() {
        assertNotNull(DefaultLogBookFactory.createMisc());
    }

    @Test
    void defaultLogBookFactory_createQsl_notNull() {
        assertNotNull(DefaultLogBookFactory.createQsl());
    }

    @Test
    void defaultLogBookFactory_createSatellite_notNull() {
        assertNotNull(DefaultLogBookFactory.createSatellite());
    }

    @Test
    void defaultLogBookFactory_createContest_notNull() {
        assertNotNull(DefaultLogBookFactory.createContest());
    }

    @Test
    void defaultLogBookFactory_createIota_notNull() {
        assertNotNull(DefaultLogBookFactory.createIota(IotaEnum.NA, 7));
    }

    @Test
    void defaultLogBookFactory_createLogEntryWithDefault_copiesOperator() {
        LogEntry source = DefaultLogBookFactory.createLogEntry();
        source.getCallsign().setOperatingStation("W1AW");
        LogEntry copy = DefaultLogBookFactory.createLogEntry(source);
        assertEquals("W1AW", copy.getCallsign().getOperatingStation());
    }
}
