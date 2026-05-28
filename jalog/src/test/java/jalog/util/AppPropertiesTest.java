package jalog.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AppPropertiesTest {

    @TempDir
    Path tempDir;

    @Test
    void addStringProperty_retrievedByKey() {
        AppProperties.addProperty("test.string.key", "hello");
        assertEquals("hello", AppProperties.getStringProperty("test.string.key", "default"));
    }

    @Test
    void getStringProperty_missingKey_returnsDefault() {
        assertEquals("fallback", AppProperties.getStringProperty("test.missing.key.xyz", "fallback"));
    }

    @Test
    void addIntProperty_retrievedAsInt() {
        AppProperties.addProperty("test.int.key", 42);
        assertEquals(42, AppProperties.getIntProperty("test.int.key", 0));
    }

    @Test
    void getIntProperty_missingKey_returnsDefault() {
        assertEquals(99, AppProperties.getIntProperty("test.int.missing.xyz", 99));
    }

    @Test
    void getIntProperty_nonNumericValue_returnsDefault() {
        AppProperties.addProperty("test.int.bad", "notanumber");
        assertEquals(7, AppProperties.getIntProperty("test.int.bad", 7));
    }

    @Test
    void getBooleanProperty_trueVariants() {
        AppProperties.addProperty("test.bool.one", "1");
        assertTrue(AppProperties.getBooleanProperty("test.bool.one", false));

        AppProperties.addProperty("test.bool.true", "true");
        assertTrue(AppProperties.getBooleanProperty("test.bool.true", false));

        AppProperties.addProperty("test.bool.yes", "yes");
        assertTrue(AppProperties.getBooleanProperty("test.bool.yes", false));
    }

    @Test
    void getBooleanProperty_falseValue_returnsFalse() {
        AppProperties.addProperty("test.bool.false", "false");
        assertFalse(AppProperties.getBooleanProperty("test.bool.false", true));
    }

    @Test
    void getBooleanProperty_missingKey_returnsDefault() {
        assertTrue(AppProperties.getBooleanProperty("test.bool.missing.xyz", true));
        assertFalse(AppProperties.getBooleanProperty("test.bool.missing.xyz2", false));
    }

    @Test
    void propertyNames_containsAddedKey() {
        AppProperties.addProperty("test.names.key", "v");
        var names = AppProperties.propertyNames();
        boolean found = false;
        while (names.hasMoreElements()) {
            if ("test.names.key".equals(names.nextElement())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void loadFromFile_loadsProperties() throws Exception {
        Path propsFile = tempDir.resolve("test.properties");
        java.nio.file.Files.writeString(propsFile, "load.file.key=loaded_value\n");
        AppProperties.load(propsFile.toString());
        assertEquals("loaded_value", AppProperties.getStringProperty("load.file.key", ""));
    }

    @Test
    void loadFromInputStream_loadsProperties() throws Exception {
        String content = "stream.key=stream_value\n";
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes());
        AppProperties.load(in);
        assertEquals("stream_value", AppProperties.getStringProperty("stream.key", ""));
    }

    @Test
    void store_writesFile() throws Exception {
        Path propsFile = tempDir.resolve("stored.properties");
        AppProperties.addProperty("store.test.key", "store_value");
        AppProperties.store(propsFile.toString());
        assertTrue(propsFile.toFile().exists());
        String content = java.nio.file.Files.readString(propsFile);
        assertTrue(content.contains("store.test.key"));
    }

    @Test
    void store_overwritesExistingFile() throws Exception {
        Path propsFile = tempDir.resolve("overwrite.properties");
        java.nio.file.Files.writeString(propsFile, "old content");
        AppProperties.addProperty("overwrite.key", "new_value");
        AppProperties.store(propsFile.toString());
        String content = java.nio.file.Files.readString(propsFile);
        assertFalse(content.equals("old content"));
    }

    @Test
    void loadFromFile_nonExistentFile_throwsFileNotFoundException() {
        assertThrows(Exception.class,
                () -> AppProperties.load("/nonexistent/path/that/doesnt/exist.properties"));
    }
}
