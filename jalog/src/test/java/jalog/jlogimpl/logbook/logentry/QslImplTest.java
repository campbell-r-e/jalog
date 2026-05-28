package jalog.jlogimpl.logbook.logentry;

import jalog.jlog.util.QslRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.*;

class QslImplTest {

    private static final SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");
    private QslImpl qsl;

    @BeforeEach
    void setUp() {
        qsl = new QslImpl();
    }

    @Test
    void defaults_areBlankAndNullDates() {
        assertEquals(QslRequest.Blank, qsl.isQslSent());
        assertEquals(QslRequest.Blank, qsl.isQslReceived());
        assertEquals("", qsl.getUnknownQslSent());
        assertEquals("", qsl.getUnknownQslReceived());
        assertNull(qsl.getQslSentDate());
        assertNull(qsl.getQslReceivedDate());
        assertEquals("", qsl.getQslMessage());
        assertEquals("", qsl.getQslVia());
    }

    @Test
    void setQslSent_roundTrips() {
        qsl.setQslSent(QslRequest.Yes);
        assertEquals(QslRequest.Yes, qsl.isQslSent());
    }

    @Test
    void setQslReceived_roundTrips() {
        qsl.setQslReceived(QslRequest.Requested);
        assertEquals(QslRequest.Requested, qsl.isQslReceived());
    }

    @Test
    void setUnknownQslSent_roundTrips() {
        qsl.setUnknownQslSent("X");
        assertEquals("X", qsl.getUnknownQslSent());
    }

    @Test
    void setUnknownQslReceived_roundTrips() {
        qsl.setUnknownQslReceived("Z");
        assertEquals("Z", qsl.getUnknownQslReceived());
    }

    @Test
    void setQslSentDate_roundTrips() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.MARCH, 15);
        qsl.setQslSentDate(cal.getTime());
        assertNotNull(qsl.getQslSentDate());
    }

    @Test
    void setQslReceivedDate_roundTrips() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.APRIL, 1);
        qsl.setQslReceivedDate(cal.getTime());
        assertNotNull(qsl.getQslReceivedDate());
    }

    @Test
    void getQslSentDateString_whenNull_returnsEmpty() {
        assertEquals("", qsl.getQslSentDateString());
    }

    @Test
    void getQslReceivedDateString_whenNull_returnsEmpty() {
        assertEquals("", qsl.getQslReceivedDateString());
    }

    @Test
    void getQslSentDateString_whenSet_returnsNonEmpty() {
        qsl.setQslSentDate(new java.util.Date());
        assertFalse(qsl.getQslSentDateString().isEmpty());
    }

    @Test
    void getQslReceivedDateString_whenSet_returnsNonEmpty() {
        qsl.setQslReceivedDate(new java.util.Date());
        assertFalse(qsl.getQslReceivedDateString().isEmpty());
    }

    @Test
    void setQslMessage_roundTrips() {
        qsl.setQslMessage("73 de W1AW");
        assertEquals("73 de W1AW", qsl.getQslMessage());
    }

    @Test
    void setQslVia_roundTrips() {
        qsl.setQslVia("bureau");
        assertEquals("bureau", qsl.getQslVia());
    }

    @Test
    void toAdifString_allBlank_returnsEmpty() {
        assertEquals("", qsl.toAdifString());
    }

    @Test
    void toAdifString_qslSentYes_writesYTag() {
        qsl.setQslSent(QslRequest.Yes);
        assertTrue(qsl.toAdifString().contains("<QSL_SENT:1>Y"));
    }

    @Test
    void toAdifString_qslReceivedNo_writesNTag() {
        qsl.setQslReceived(QslRequest.No);
        assertTrue(qsl.toAdifString().contains("<QSL_RCVD:1>N"));
    }

    @Test
    void toAdifString_unknownQslSent_writesRawValue() {
        qsl.setUnknownQslSent("Q");
        assertTrue(qsl.toAdifString().contains("<QSL_SENT:1>Q"));
    }

    @Test
    void toAdifString_unknownQslReceived_writesRawValue() {
        qsl.setUnknownQslReceived("P");
        assertTrue(qsl.toAdifString().contains("<QSL_RCVD:1>P"));
    }

    @Test
    void toAdifString_qslSentDate_writesDateTag() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15);
        qsl.setQslSentDate(cal.getTime());
        assertTrue(qsl.toAdifString().contains("<QSLSDATE:8>20250615"));
    }

    @Test
    void toAdifString_qslReceivedDate_writesDateTag() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JULY, 4);
        qsl.setQslReceivedDate(cal.getTime());
        assertTrue(qsl.toAdifString().contains("<QSLRDATE:8>20250704"));
    }

    @Test
    void toAdifString_message_writesQslMsgTag() {
        qsl.setQslMessage("73");
        assertTrue(qsl.toAdifString().contains("<QSLMSG:2>73"));
    }

    @Test
    void toAdifString_via_writesQslViaTag() {
        qsl.setQslVia("direct");
        assertTrue(qsl.toAdifString().contains("<QSL_VIA:6>direct"));
    }

    @Test
    void toString_containsFields() {
        qsl.setQslSent(QslRequest.Yes);
        qsl.setQslMessage("hello");
        String str = qsl.toString();
        assertTrue(str.contains("Yes") || str.contains("QSL"));
    }
}
