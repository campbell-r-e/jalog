package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntToObjectTest {

    private static final Object DEFAULT = new Object();
    private static final Object VALUE_A = new Object();

    private IntToObject buildMap() {
        Object[][] data = {{new Integer(1), VALUE_A}};
        return new IntToObject(data, DEFAULT);
    }

    @Test
    void getObjectValue_knownKey_returnsValue() {
        assertSame(VALUE_A, buildMap().getObjectValue(1));
    }

    @Test
    void getObjectValue_unknownKey_returnsDefault() {
        assertSame(DEFAULT, buildMap().getObjectValue(99));
    }

    @Test
    void getIntValue_knownObject_returnsInt() {
        assertEquals(1, buildMap().getIntValue(VALUE_A));
    }

    @Test
    void getIntValue_unknownObject_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> buildMap().getIntValue(new Object()));
    }
}
