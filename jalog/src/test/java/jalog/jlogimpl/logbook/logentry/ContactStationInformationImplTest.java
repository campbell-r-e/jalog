package jalog.jlogimpl.logbook.logentry;

import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.logbook.jlogentry.Iota;
import jalog.jlog.util.IotaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactStationInformationImplTest {

    private ContactStationInformationImpl csi;

    @BeforeEach
    void setUp() {
        csi = new ContactStationInformationImpl();
    }

    @Test
    void defaults_allEmptyStrings() {
        assertEquals("", csi.getQth());
        assertEquals("", csi.getAge());
        assertEquals("", csi.getCqZone());
        assertEquals("", csi.getDxcc());
        assertEquals("", csi.getGridSquare());
        assertEquals("", csi.getItuZone());
        assertNull(csi.getIota());
    }

    @Test
    void setQth_roundTrips() {
        csi.setQth("Denver, CO");
        assertEquals("Denver, CO", csi.getQth());
    }

    @Test
    void setAge_roundTrips() {
        csi.setAge("35");
        assertEquals("35", csi.getAge());
    }

    @Test
    void setCqZone_roundTrips() {
        csi.setCqZone("4");
        assertEquals("4", csi.getCqZone());
    }

    @Test
    void setDxcc_roundTrips() {
        csi.setDxcc("291");
        assertEquals("291", csi.getDxcc());
    }

    @Test
    void setGridSquare_roundTrips() {
        csi.setGridSquare("DM79");
        assertEquals("DM79", csi.getGridSquare());
    }

    @Test
    void setItuZone_roundTrips() {
        csi.setItuZone("7");
        assertEquals("7", csi.getItuZone());
    }

    @Test
    void setIota_roundTrips() {
        Iota iota = DefaultLogBookFactory.createIota(IotaEnum.NA, 42);
        csi.setIota(iota);
        assertSame(iota, csi.getIota());
    }

    @Test
    void toAdifString_empty_returnsEmpty() {
        assertEquals("", csi.toAdifString());
    }

    @Test
    void toAdifString_qth_containsQthTag() {
        csi.setQth("Denver");
        assertTrue(csi.toAdifString().contains("<QTH:6>Denver"));
    }

    @Test
    void toAdifString_age_containsAgeTag() {
        csi.setAge("42");
        assertTrue(csi.toAdifString().contains("<AGE:2>42"));
    }

    @Test
    void toAdifString_cqZone_containsCqzTag() {
        csi.setCqZone("4");
        assertTrue(csi.toAdifString().contains("<CQZ:1>4"));
    }

    @Test
    void toAdifString_dxcc_containsDxccTag() {
        csi.setDxcc("291");
        assertTrue(csi.toAdifString().contains("<DXCC:3>291"));
    }

    @Test
    void toAdifString_gridSquare_containsGridsquareTag() {
        csi.setGridSquare("DM79");
        assertTrue(csi.toAdifString().contains("<GRIDSQUARE:4>DM79"));
    }

    @Test
    void toAdifString_ituZone_containsItuzTag() {
        csi.setItuZone("7");
        assertTrue(csi.toAdifString().contains("<ITUZ:1>7"));
    }

    @Test
    void toAdifString_knownIota_containsIotaTag() {
        Iota iota = DefaultLogBookFactory.createIota(IotaEnum.NA, 7);
        csi.setIota(iota);
        String adif = csi.toAdifString();
        assertTrue(adif.contains("IOTA"));
        assertTrue(adif.contains("NA-7"));
    }

    @Test
    void toAdifString_unknownIota_usesUnknownIotaString() {
        Iota iota = DefaultLogBookFactory.createIota(IotaEnum.Unknown, 5);
        iota.setUnknownIota("XX");
        csi.setIota(iota);
        String adif = csi.toAdifString();
        assertTrue(adif.contains("XX-5"));
    }

    @Test
    void toAdifString_blankIota_omitsIotaTag() {
        Iota iota = DefaultLogBookFactory.createIota(IotaEnum.Blank, 0);
        csi.setIota(iota);
        assertFalse(csi.toAdifString().contains("IOTA"));
    }

    @Test
    void toString_doesNotThrow() {
        csi.setQth("Denver");
        String s = csi.toString();
        assertTrue(s.contains("Denver"));
    }
}
