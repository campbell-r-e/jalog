package jalog.jlogimpl.logbook.logentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SatelliteImplTest {

    private SatelliteImpl satellite;

    @BeforeEach
    void setUp() {
        satellite = new SatelliteImpl();
    }

    @Test
    void defaults_areEmpty() {
        assertEquals("", satellite.getSatelliteName());
        assertEquals("", satellite.getSatelliteMode());
        assertEquals("", satellite.getPropMode());
    }

    @Test
    void setSatelliteName_roundTrips() {
        satellite.setSatelliteName("AO-91");
        assertEquals("AO-91", satellite.getSatelliteName());
    }

    @Test
    void setSatelliteMode_roundTrips() {
        satellite.setSatelliteMode("U/V");
        assertEquals("U/V", satellite.getSatelliteMode());
    }

    @Test
    void setPropMode_roundTrips() {
        satellite.setPropMode("SAT");
        assertEquals("SAT", satellite.getPropMode());
    }

    @Test
    void toAdifString_allEmpty_returnsEmpty() {
        assertEquals("", satellite.toAdifString());
    }

    @Test
    void toAdifString_satelliteName_writesSatNameTag() {
        satellite.setSatelliteName("AO-91");
        assertTrue(satellite.toAdifString().contains("<SAT_NAME:5>AO-91"));
    }

    @Test
    void toAdifString_satelliteMode_writesSatModeTag() {
        satellite.setSatelliteMode("U/V");
        assertTrue(satellite.toAdifString().contains("<SAT_MODE:3>U/V"));
    }

    @Test
    void toAdifString_propMode_writesPropModeTag() {
        satellite.setPropMode("SAT");
        assertTrue(satellite.toAdifString().contains("<PROP_MODE:3>SAT"));
    }

    @Test
    void toString_containsFields() {
        satellite.setSatelliteName("AO-91");
        String str = satellite.toString();
        assertTrue(str.contains("AO-91"));
    }
}
