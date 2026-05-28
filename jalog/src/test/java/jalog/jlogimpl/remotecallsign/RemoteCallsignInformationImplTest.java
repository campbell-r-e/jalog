package jalog.jlogimpl.remotecallsign;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoteCallsignInformationImplTest {

    private RemoteCallsignInformationImpl info;

    @BeforeEach
    void setUp() {
        info = new RemoteCallsignInformationImpl();
    }

    @Test
    void defaultValues_areEmptyStrings() {
        assertEquals("", info.getStatus());
        assertEquals("", info.getCurrentCallsign());
        assertEquals("", info.getPreviousCallsign());
        assertEquals("", info.getName());
        assertEquals("", info.getAddressLine1());
        assertEquals("", info.getAddressLine2());
        assertEquals("", info.getLatitude());
        assertEquals("", info.getLongitude());
        assertEquals("", info.getGrantDate());
        assertEquals("", info.getExpiryDate());
        assertEquals("", info.getFrn());
        assertEquals("", info.getGridSquare());
    }

    @Test
    void status_roundTrips() {
        info.setStatus("VALID");
        assertEquals("VALID", info.getStatus());
    }

    @Test
    void currentCallsign_roundTrips() {
        info.setCurrentCallsign("W1AW");
        assertEquals("W1AW", info.getCurrentCallsign());
    }

    @Test
    void previousCallsign_roundTrips() {
        info.setPreviousCallsign("K1ABC");
        assertEquals("K1ABC", info.getPreviousCallsign());
    }

    @Test
    void name_roundTrips() {
        info.setName("John Smith");
        assertEquals("John Smith", info.getName());
    }

    @Test
    void addressLine1_roundTrips() {
        info.setAddressLine1("225 Main St");
        assertEquals("225 Main St", info.getAddressLine1());
    }

    @Test
    void addressLine2_roundTrips() {
        info.setAddressLine2("Newington, CT 06111");
        assertEquals("Newington, CT 06111", info.getAddressLine2());
    }

    @Test
    void latitude_roundTrips() {
        info.setLatitude("41.7143");
        assertEquals("41.7143", info.getLatitude());
    }

    @Test
    void longitude_roundTrips() {
        info.setLongitude("-72.7272");
        assertEquals("-72.7272", info.getLongitude());
    }

    @Test
    void grantDate_roundTrips() {
        info.setGrantDate("2020-01-15");
        assertEquals("2020-01-15", info.getGrantDate());
    }

    @Test
    void expiryDate_roundTrips() {
        info.setExpiryDate("2030-01-15");
        assertEquals("2030-01-15", info.getExpiryDate());
    }

    @Test
    void frn_roundTrips() {
        info.setFrn("0012345678");
        assertEquals("0012345678", info.getFrn());
    }

    @Test
    void gridSquare_roundTrips() {
        info.setGridSquare("FN31pr");
        assertEquals("FN31pr", info.getGridSquare());
    }

    @Test
    void toString_containsAllFields() {
        info.setCurrentCallsign("W1AW");
        info.setName("ARRL HQ");
        info.setStatus("VALID");
        String str = info.toString();
        assertTrue(str.contains("W1AW"));
        assertTrue(str.contains("ARRL HQ"));
        assertTrue(str.contains("VALID"));
    }
}
