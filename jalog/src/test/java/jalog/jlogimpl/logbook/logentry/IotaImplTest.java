package jalog.jlogimpl.logbook.logentry;

import jalog.jlog.util.IotaEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IotaImplTest {

    @Test
    void constructor_setsEnumAndValue() {
        IotaImpl iota = new IotaImpl(IotaEnum.AF, 42);
        assertEquals(IotaEnum.AF, iota.getIotaEnum());
        assertEquals(42, iota.getValue());
    }

    @Test
    void setIotaEnum_roundTrips() {
        IotaImpl iota = new IotaImpl(IotaEnum.AF, 1);
        iota.setIotaEnum(IotaEnum.EU);
        assertEquals(IotaEnum.EU, iota.getIotaEnum());
    }

    @Test
    void setValue_roundTrips() {
        IotaImpl iota = new IotaImpl(IotaEnum.NA, 5);
        iota.setValue(99);
        assertEquals(99, iota.getValue());
    }

    @Test
    void unknownIota_defaultIsEmpty() {
        IotaImpl iota = new IotaImpl(IotaEnum.Blank, 0);
        assertEquals("", iota.getUnknownIota());
    }

    @Test
    void setUnknownIota_roundTrips() {
        IotaImpl iota = new IotaImpl(IotaEnum.Unknown, 0);
        iota.setUnknownIota("XX");
        assertEquals("XX", iota.getUnknownIota());
    }

    @Test
    void toString_containsEnumAndValue() {
        IotaImpl iota = new IotaImpl(IotaEnum.OC, 7);
        String str = iota.toString();
        assertTrue(str.contains("OC"));
        assertTrue(str.contains("7"));
    }
}
