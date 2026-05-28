package jalog.jlog.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class TableColumnChangeEventIdTest {

    @Test
    void optionDialogEvent_getDescription() {
        assertEquals("Option Dialog Event", TableColumnChangeEventId.OptionDialogEvent.getDescription());
    }

    @Test
    void optionDialogEvent_getEventCode() {
        assertEquals(1, TableColumnChangeEventId.OptionDialogEvent.getEventCode());
    }

    @Test
    void optionDialogEvent_toString_containsDescription() {
        String s = TableColumnChangeEventId.OptionDialogEvent.toString();
        assertTrue(s.contains("Option Dialog Event"));
        assertTrue(s.contains("1"));
    }

    @Test
    void serialization_deserializesToSameConstant() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(TableColumnChangeEventId.OptionDialogEvent);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        TableColumnChangeEventId deserialized = (TableColumnChangeEventId) ois.readObject();
        assertSame(TableColumnChangeEventId.OptionDialogEvent, deserialized);
    }
}
