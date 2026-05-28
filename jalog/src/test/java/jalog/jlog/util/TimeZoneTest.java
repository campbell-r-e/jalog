package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeZoneTest {

    @Test
    void utc_toString_returnsUTC() {
        assertEquals("UTC", TimeZone.UTC.toString());
    }

    @Test
    void adt_toString_containsAtlanticDaylight() {
        assertTrue(TimeZone.ADT.toString().contains("ATLANTIC DAYLIGHT"));
    }

    @Test
    void ast_toString_containsAtlanticStandard() {
        assertTrue(TimeZone.AST.toString().contains("ATLANTIC STANDARD"));
    }

    @Test
    void edt_toString_containsEasternDaylight() {
        assertTrue(TimeZone.EDT.toString().contains("EASTERN DAYLIGHT"));
    }

    @Test
    void est_toString_isCorrect() {
        assertEquals("(UTC-5h) EASTERN STANDARD TIME", TimeZone.EST.toString());
    }

    @Test
    void cdt_toString_containsCentralDaylight() {
        assertTrue(TimeZone.CDT.toString().contains("CENTRAL DAYLIGHT"));
    }

    @Test
    void cst_toString_containsCentralStandard() {
        assertTrue(TimeZone.CST.toString().contains("CENTRAL STANDARD"));
    }

    @Test
    void mdt_toString_containsMountainDaylight() {
        assertTrue(TimeZone.MDT.toString().contains("MOUNTAIN DAYLIGHT"));
    }

    @Test
    void mst_toString_containsMountainStandard() {
        assertTrue(TimeZone.MST.toString().contains("MOUNTAIN STANDARD"));
    }

    @Test
    void pdt_toString_containsPacificDaylight() {
        assertTrue(TimeZone.PDT.toString().contains("PACIFIC DAYLIGHT"));
    }

    @Test
    void akdt_toString_containsAlaskanDaylight() {
        assertTrue(TimeZone.AKDT.toString().contains("ALASKAN DAYLIGHT"));
    }

    @Test
    void pst_toString_containsPacificStandard() {
        assertTrue(TimeZone.PST.toString().contains("PACIFIC STANDARD"));
    }

    @Test
    void akst_toString_containsAlaskanStandard() {
        assertTrue(TimeZone.AKST.toString().contains("ALASKAN STANDARD"));
    }

    @Test
    void hst_toString_containsHawaii() {
        assertTrue(TimeZone.HST.toString().contains("HAWAII"));
    }

    @Test
    void getValues_returns14Zones() {
        assertEquals(14, TimeZone.getValues().length);
    }

    @Test
    void getValues_firstIsUtc() {
        assertSame(TimeZone.UTC, TimeZone.getValues()[0]);
    }

    @Test
    void getValues_lastIsHst() {
        TimeZone[] values = TimeZone.getValues();
        assertSame(TimeZone.HST, values[values.length - 1]);
    }

    @Test
    void getValues_allConstantsPresent() {
        TimeZone[] values = TimeZone.getValues();
        assertSame(TimeZone.ADT,  values[1]);
        assertSame(TimeZone.AST,  values[2]);
        assertSame(TimeZone.EDT,  values[3]);
        assertSame(TimeZone.EST,  values[4]);
        assertSame(TimeZone.CDT,  values[5]);
        assertSame(TimeZone.CST,  values[6]);
        assertSame(TimeZone.MDT,  values[7]);
        assertSame(TimeZone.MST,  values[8]);
        assertSame(TimeZone.PDT,  values[9]);
        assertSame(TimeZone.AKDT, values[10]);
        assertSame(TimeZone.PST,  values[11]);
        assertSame(TimeZone.AKST, values[12]);
    }
}
