package jalog.jlog.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedFileFilterTest {

    private EnhancedFileFilter filter;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        filter = new EnhancedFileFilter();
    }

    @Test
    void defaultConstructor_noFilters_rejectsFiles() {
        assertFalse(filter.accept(tempDir.resolve("test.jpg").toFile()));
    }

    @Test
    void accept_directory_returnsTrue() {
        assertTrue(filter.accept(tempDir.toFile()));
    }

    @Test
    void accept_null_returnsFalse() {
        assertFalse(filter.accept(null));
    }

    @Test
    void accept_matchingExtension_returnsTrue() {
        filter.addExtension("adi");
        assertTrue(filter.accept(tempDir.resolve("log.adi").toFile()));
    }

    @Test
    void accept_nonMatchingExtension_returnsFalse() {
        filter.addExtension("adi");
        assertFalse(filter.accept(tempDir.resolve("log.txt").toFile()));
    }

    @Test
    void accept_extensionIsCaseInsensitive() {
        filter.addExtension("adi");
        assertTrue(filter.accept(tempDir.resolve("log.ADI").toFile()));
    }

    @Test
    void getExtension_returnsLowercaseExtension() {
        assertEquals("adi", filter.getExtension(tempDir.resolve("log.ADI").toFile()));
    }

    @Test
    void getExtension_noExtension_returnsNull() {
        assertNull(filter.getExtension(tempDir.resolve("noext").toFile()));
    }

    @Test
    void getExtension_null_returnsNull() {
        assertNull(filter.getExtension(null));
    }

    @Test
    void getExtension_dotAtEnd_returnsNull() {
        assertNull(filter.getExtension(tempDir.resolve("file.").toFile()));
    }

    @Test
    void constructorWithExtension_acceptsMatchingFile() {
        filter = new EnhancedFileFilter("jpg");
        assertTrue(filter.accept(tempDir.resolve("image.jpg").toFile()));
    }

    @Test
    void constructorWithExtensionAndDescription_setsDescription() {
        filter = new EnhancedFileFilter("jpg", "JPEG Images");
        assertTrue(filter.accept(tempDir.resolve("image.jpg").toFile()));
        assertTrue(filter.getDescription().contains("JPEG Images"));
    }

    @Test
    void constructorWithArray_acceptsAllExtensions() {
        filter = new EnhancedFileFilter(new String[]{"gif", "jpg"});
        assertTrue(filter.accept(tempDir.resolve("image.jpg").toFile()));
        assertTrue(filter.accept(tempDir.resolve("anim.gif").toFile()));
        assertFalse(filter.accept(tempDir.resolve("doc.txt").toFile()));
    }

    @Test
    void constructorWithArrayAndDescription_hasDescription() {
        filter = new EnhancedFileFilter(new String[]{"gif", "jpg"}, "Image Files");
        assertTrue(filter.getDescription().contains("Image Files"));
    }

    @Test
    void setDescription_updatesDescription() {
        filter.addExtension("adi");
        filter.setDescription("ADIF Files");
        assertTrue(filter.getDescription().contains("ADIF Files"));
    }

    @Test
    void getDescription_noDescriptionWithExtension_includesExtensionInParens() {
        filter.addExtension("adi");
        String desc = filter.getDescription();
        assertTrue(desc.contains("adi"));
        assertTrue(desc.contains("("));
    }

    @Test
    void isExtensionListInDescription_defaultTrue() {
        assertTrue(filter.isExtensionListInDescription());
    }

    @Test
    void setExtensionListInDescription_false_descriptionOmitsExtensions() {
        filter.addExtension("adi");
        filter.setDescription("ADIF Files");
        filter.setExtensionListInDescription(false);
        assertEquals("ADIF Files", filter.getDescription());
    }

    @Test
    void getDescription_cached_returnsSameValue() {
        filter.addExtension("adi");
        String first = filter.getDescription();
        String second = filter.getDescription();
        assertEquals(first, second);
    }
}
