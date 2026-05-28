package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToObjectTest {

    private static final Object DEFAULT = new Object();
    private static final Object VALUE_A = new Object();

    private StringToObject buildMap() {
        Object[][] data = {{"hello", VALUE_A}};
        return new StringToObject(data, DEFAULT);
    }

    @Test
    void getObjectValue_knownKey_returnsValue() {
        assertSame(VALUE_A, buildMap().getObjectValue("hello"));
    }

    @Test
    void getObjectValue_unknownKey_returnsDefault() {
        assertSame(DEFAULT, buildMap().getObjectValue("unknown"));
    }

    @Test
    void getStringValue_knownObject_returnsString() {
        assertEquals("hello", buildMap().getStringValue(VALUE_A));
    }

    @Test
    void getStringValue_unknownObject_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> buildMap().getStringValue(new Object()));
    }
}
