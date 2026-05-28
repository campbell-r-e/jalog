package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IotaEnumTest {

    @Test
    void af_toString() {
        assertEquals("AF", IotaEnum.AF.toString());
    }

    @Test
    void unknown_toString() {
        assertEquals("Unknown", IotaEnum.Unknown.toString());
    }

    @Test
    void getValues_nonNull() {
        assertNotNull(IotaEnum.getValues());
        assertTrue(IotaEnum.getValues().length > 0);
    }

    @Test
    void getStrToIotaType_notNull() {
        assertNotNull(IotaEnum.getStrToIotaType());
    }

    @Test
    void getIntToIotaType_notNull() {
        assertNotNull(IotaEnum.getIntToIotaType());
    }

    @Test
    void strToIotaType_lookupAF_returnsAF() {
        Object result = IotaEnum.getStrToIotaType().getObjectValue("AF");
        assertSame(IotaEnum.AF, result);
    }

    @Test
    void intToIotaType_lookup1_returnsAF() {
        Object result = IotaEnum.getIntToIotaType().getObjectValue(1);
        assertSame(IotaEnum.AF, result);
    }
}
