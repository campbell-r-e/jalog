package jalog.jlogimpl.logbook.logentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallsignImplTest {

    private CallsignImpl callsign;

    @BeforeEach
    void setUp() {
        callsign = new CallsignImpl();
    }

    @Test
    void defaultContactStation_isEmpty() {
        assertEquals("", callsign.getContactStation());
    }

    @Test
    void defaultOperatingStation_isEmpty() {
        assertEquals("", callsign.getOperatingStation());
    }

    @Test
    void setContactStation_roundTrips() {
        callsign.setContactStation("W1AW");
        assertEquals("W1AW", callsign.getContactStation());
    }

    @Test
    void setOperatingStation_roundTrips() {
        callsign.setOperatingStation("KC0ZPS");
        assertEquals("KC0ZPS", callsign.getOperatingStation());
    }

    @Test
    void toAdifString_bothEmpty_returnsEmpty() {
        assertEquals("", callsign.toAdifString());
    }

    @Test
    void toAdifString_contactStation_includesCallTag() {
        callsign.setContactStation("W1AW");
        String adif = callsign.toAdifString();
        assertTrue(adif.contains("<CALL:4>W1AW"));
    }

    @Test
    void toAdifString_operatingStation_includesOperatorTag() {
        callsign.setOperatingStation("KC0ZPS");
        String adif = callsign.toAdifString();
        assertTrue(adif.contains("<OPERATOR:6>KC0ZPS"));
    }

    @Test
    void toAdifString_bothSet_includesBoth() {
        callsign.setContactStation("W1AW");
        callsign.setOperatingStation("KC0ZPS");
        String adif = callsign.toAdifString();
        assertTrue(adif.contains("<CALL:4>W1AW"));
        assertTrue(adif.contains("<OPERATOR:6>KC0ZPS"));
    }
}
