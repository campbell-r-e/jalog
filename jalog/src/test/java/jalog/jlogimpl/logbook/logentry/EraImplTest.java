package jalog.jlogimpl.logbook.logentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import static org.junit.jupiter.api.Assertions.*;

class EraImplTest {

    private static final SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");
    private EraImpl era;

    @BeforeEach
    void setUp() {
        era = new EraImpl();
    }

    @Test
    void defaults_areBothNull() {
        assertNull(era.getStartDate());
        assertNull(era.getEndDate());
    }

    @Test
    void setStartDate_roundTrips() {
        Date now = new Date();
        era.setStartDate(now);
        assertEquals(now, era.getStartDate());
    }

    @Test
    void setEndDate_roundTrips() {
        Date now = new Date();
        era.setEndDate(now);
        assertEquals(now, era.getEndDate());
    }

    @Test
    void getStartDateString_whenNull_returnsEmpty() {
        assertEquals("", era.getStartDateString());
    }

    @Test
    void getStartTimeString_whenNull_returnsEmpty() {
        assertEquals("", era.getStartTimeString());
    }

    @Test
    void getEndTimeString_whenNull_returnsEmpty() {
        assertEquals("", era.getEndTimeString());
    }

    @Test
    void getStartDateString_whenSet_returnsFormattedDate() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 0, 0, 0);
        era.setStartDate(cal.getTime());
        String s = era.getStartDateString();
        assertFalse(s.isEmpty());
        assertTrue(s.contains("2025") || s.contains("15"));
    }

    @Test
    void getStartTimeString_whenSet_returnsFormattedTime() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        era.setStartDate(cal.getTime());
        String s = era.getStartTimeString();
        assertFalse(s.isEmpty());
    }

    @Test
    void getEndTimeString_whenSet_returnsFormattedTime() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 15, 45, 0);
        era.setEndDate(cal.getTime());
        String s = era.getEndTimeString();
        assertFalse(s.isEmpty());
    }

    @Test
    void toAdifString_noDateSet_returnsEmpty() {
        assertEquals("", era.toAdifString());
    }

    @Test
    void toAdifString_startDateSet_containsQsoDateAndTimeOn() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        era.setStartDate(cal.getTime());
        String adif = era.toAdifString();
        assertTrue(adif.contains("<QSO_DATE:8>20250615"));
        assertTrue(adif.contains("<TIME_ON:6>143000"));
    }

    @Test
    void toAdifString_endDateSet_containsTimeOff() {
        GregorianCalendar startCal = new GregorianCalendar(UTC);
        startCal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        era.setStartDate(startCal.getTime());

        GregorianCalendar endCal = new GregorianCalendar(UTC);
        endCal.set(2025, Calendar.JUNE, 15, 14, 45, 0);
        era.setEndDate(endCal.getTime());

        String adif = era.toAdifString();
        assertTrue(adif.contains("<TIME_OFF:6>144500"));
    }

    @Test
    void toString_nullDates_noException() {
        assertDoesNotThrow(() -> era.toString());
        assertTrue(era.toString().contains("EraImpl"));
    }

    @Test
    void toString_withDates_containsClass() {
        GregorianCalendar cal = new GregorianCalendar(UTC);
        cal.set(2025, Calendar.JUNE, 15, 14, 30, 0);
        era.setStartDate(cal.getTime());
        String str = era.toString();
        assertTrue(str.contains("EraImpl") || str.contains("Era"));
    }
}
