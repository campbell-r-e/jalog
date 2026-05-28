package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QslRequestTest {

    @Test
    void blank_toString() {
        assertEquals("", QslRequest.Blank.toString());
    }

    @Test
    void yes_toString() {
        assertEquals("Yes", QslRequest.Yes.toString());
    }

    @Test
    void getValues_nonNull() {
        assertNotNull(QslRequest.getValues());
        assertTrue(QslRequest.getValues().length > 0);
    }

    @Test
    void getIntToBandType_notNull() {
        assertNotNull(QslRequest.getIntToBandType());
    }

    @Test
    void getStrToQslRequestType_notNull() {
        assertNotNull(QslRequest.getStrToQslRequestType());
    }

    @Test
    void strToQslRequestType_lookupY_returnsYes() {
        Object result = QslRequest.getStrToQslRequestType().getObjectValue("Y");
        assertSame(QslRequest.Yes, result);
    }

    @Test
    void intToBandType_lookup1_returnsYes() {
        Object result = QslRequest.getIntToBandType().getObjectValue(1);
        assertSame(QslRequest.Yes, result);
    }
}
