package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ThemeUpdateEventIdTest {

    @Test
    void general_getDescription() {
        assertEquals("General Theme Update", ThemeUpdateEventId.General.getDescription());
    }

    @Test
    void general_getEventCode() {
        assertEquals(1, ThemeUpdateEventId.General.getEventCode());
    }

    @Test
    void general_toString_containsCode() {
        String s = ThemeUpdateEventId.General.toString();
        assertTrue(s.contains("1"));
        assertTrue(s.contains("General Theme Update"));
    }

    @Test
    void componentConstruction_getDescription() {
        assertEquals("Update due to component construction",
                ThemeUpdateEventId.ComponentConstruction.getDescription());
    }

    @Test
    void componentConstruction_getEventCode() {
        assertEquals(1, ThemeUpdateEventId.ComponentConstruction.getEventCode());
    }

    @Test
    void componentConstruction_toString_containsDescription() {
        assertTrue(ThemeUpdateEventId.ComponentConstruction.toString()
                .contains("component construction"));
    }

    @Test
    void serialization_generalDeserializesToSameConstant() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(ThemeUpdateEventId.General);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        ThemeUpdateEventId deserialized = (ThemeUpdateEventId) ois.readObject();
        assertSame(ThemeUpdateEventId.General, deserialized);
    }

    @Test
    void serialization_componentConstructionDeserializesToSameConstant() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(ThemeUpdateEventId.ComponentConstruction);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        ThemeUpdateEventId deserialized = (ThemeUpdateEventId) ois.readObject();
        assertSame(ThemeUpdateEventId.ComponentConstruction, deserialized);
    }
}
