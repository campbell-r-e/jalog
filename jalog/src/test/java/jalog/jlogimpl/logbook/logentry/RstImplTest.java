package jalog.jlogimpl.logbook.logentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RstImplTest {

    private RstImpl rst;

    @BeforeEach
    void setUp() {
        rst = new RstImpl();
    }

    @Test
    void defaultConstructor_fieldsAreEmpty() {
        assertEquals("", rst.getRstReceived());
        assertEquals("", rst.getRstSent());
    }

    @Test
    void parametrizedConstructor_setsValues() {
        RstImpl r = new RstImpl("59", "57");
        assertEquals("59", r.getRstReceived());
        assertEquals("57", r.getRstSent());
    }

    @Test
    void setRstReceived_roundTrips() {
        rst.setRstReceived("599");
        assertEquals("599", rst.getRstReceived());
    }

    @Test
    void setRstSent_roundTrips() {
        rst.setRstSent("579");
        assertEquals("579", rst.getRstSent());
    }

    @Test
    void toAdifString_bothEmpty_returnsEmpty() {
        assertEquals("", rst.toAdifString());
    }

    @Test
    void toAdifString_rstReceived_includesRstRcvdTag() {
        rst.setRstReceived("599");
        String adif = rst.toAdifString();
        assertTrue(adif.contains("<RST_RCVD:3>599"));
    }

    @Test
    void toAdifString_rstSent_includesRstSentTag() {
        rst.setRstSent("57");
        String adif = rst.toAdifString();
        assertTrue(adif.contains("<RST_SENT:2>57"));
    }

    @Test
    void toAdifString_bothSet_includesBothTags() {
        rst.setRstReceived("599");
        rst.setRstSent("579");
        String adif = rst.toAdifString();
        assertTrue(adif.contains("<RST_RCVD:3>599"));
        assertTrue(adif.contains("<RST_SENT:3>579"));
    }
}
