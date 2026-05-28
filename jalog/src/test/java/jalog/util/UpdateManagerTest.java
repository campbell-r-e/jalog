package jalog.util;

import jalog.jlog.gui.TableColumnChangeEvent;
import jalog.jlog.gui.TableUpdateListener;
import jalog.jlog.gui.ThemeUpdateEvent;
import jalog.jlog.gui.ThemeUpdateListener;
import jalog.jlog.util.TableColumnChangeEventId;
import jalog.jlog.util.ThemeUpdateEventId;
import jalog.jlogimpl.gui.TableColumnEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class UpdateManagerTest {

    @Test
    void getInstance_returnsSameInstance() {
        assertSame(UpdateManager.getInstance(), UpdateManager.getInstance());
    }

    @Test
    void addThemeUpdateListener_null_doesNotThrow() {
        assertDoesNotThrow(() -> UpdateManager.getInstance().addThemeUpdateListener(null));
    }

    @Test
    void removeThemeUpdateListener_null_doesNotThrow() {
        assertDoesNotThrow(() -> UpdateManager.getInstance().removeThemeUpdateListener(null));
    }

    @Test
    void postThemeUpdateEvent_firesRegisteredListener() {
        AtomicInteger callCount = new AtomicInteger(0);
        ThemeUpdateListener listener = event -> callCount.incrementAndGet();

        UpdateManager.getInstance().addThemeUpdateListener(listener);
        try {
            UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, "test");
            assertEquals(1, callCount.get());
        } finally {
            UpdateManager.getInstance().removeThemeUpdateListener(listener);
        }
    }

    @Test
    void addThemeUpdateListener_duplicateNotAdded() {
        AtomicInteger callCount = new AtomicInteger(0);
        ThemeUpdateListener listener = event -> callCount.incrementAndGet();

        UpdateManager.getInstance().addThemeUpdateListener(listener);
        UpdateManager.getInstance().addThemeUpdateListener(listener);
        try {
            UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, "test");
            assertEquals(1, callCount.get());
        } finally {
            UpdateManager.getInstance().removeThemeUpdateListener(listener);
        }
    }

    @Test
    void removeThemeUpdateListener_stopsNotification() {
        AtomicInteger callCount = new AtomicInteger(0);
        ThemeUpdateListener listener = event -> callCount.incrementAndGet();

        UpdateManager.getInstance().addThemeUpdateListener(listener);
        UpdateManager.getInstance().removeThemeUpdateListener(listener);
        UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, "test");
        assertEquals(0, callCount.get());
    }

    @Test
    void addTableUpdateListener_null_doesNotThrow() {
        assertDoesNotThrow(() -> UpdateManager.getInstance().addTableUpdateListener(null));
    }

    @Test
    void removeTableUpdateListener_null_doesNotThrow() {
        assertDoesNotThrow(() -> UpdateManager.getInstance().removeTableUpdateListener(null));
    }

    @Test
    void postTableColumnChangeEvent_firesRegisteredListener() {
        AtomicInteger callCount = new AtomicInteger(0);
        TableUpdateListener listener = (event, columns) -> callCount.incrementAndGet();

        UpdateManager.getInstance().addTableUpdateListener(listener);
        try {
            ArrayList<TableColumnEnum> cols = new ArrayList<>();
            cols.add(TableColumnEnum.QsoDate);
            UpdateManager.postTableColumnChangeEvent(TableColumnChangeEventId.OptionDialogEvent, "test", cols);
            assertEquals(1, callCount.get());
        } finally {
            UpdateManager.getInstance().removeTableUpdateListener(listener);
        }
    }

    @Test
    void removeTableUpdateListener_stopsNotification() {
        AtomicInteger callCount = new AtomicInteger(0);
        TableUpdateListener listener = (event, columns) -> callCount.incrementAndGet();

        UpdateManager.getInstance().addTableUpdateListener(listener);
        UpdateManager.getInstance().removeTableUpdateListener(listener);
        UpdateManager.postTableColumnChangeEvent(TableColumnChangeEventId.OptionDialogEvent, "test", new ArrayList<>());
        assertEquals(0, callCount.get());
    }

    @Test
    void addTableUpdateListener_duplicateNotAdded() {
        AtomicInteger callCount = new AtomicInteger(0);
        TableUpdateListener listener = (event, columns) -> callCount.incrementAndGet();

        UpdateManager.getInstance().addTableUpdateListener(listener);
        UpdateManager.getInstance().addTableUpdateListener(listener);
        try {
            UpdateManager.postTableColumnChangeEvent(TableColumnChangeEventId.OptionDialogEvent, "test", new ArrayList<>());
            assertEquals(1, callCount.get());
        } finally {
            UpdateManager.getInstance().removeTableUpdateListener(listener);
        }
    }

    @Test
    void toString_returnsUpdateManager() {
        assertEquals("UpdateManager", UpdateManager.getInstance().toString());
    }

    @Test
    void getDebugTable_returnsNonNull() {
        assertNotNull(UpdateManager.getInstance().getDebugTable());
    }

    @Test
    void getDebugTable_tableName_isUpdateManager() {
        assertEquals("Update Manager", UpdateManager.getInstance().getDebugTable().getTableName());
    }

    @Test
    void getDebugTable_withListeners_hasRows() {
        ThemeUpdateListener themeListener = event -> {};
        UpdateManager.getInstance().addThemeUpdateListener(themeListener);
        try {
            assertNotNull(UpdateManager.getInstance().getDebugTable());
        } finally {
            UpdateManager.getInstance().removeThemeUpdateListener(themeListener);
        }
    }

    @Test
    void getDebugTable_withTableUpdateListener_includesIt() {
        TableUpdateListener tableListener = (event, columns) -> {};
        UpdateManager.getInstance().addTableUpdateListener(tableListener);
        try {
            assertNotNull(UpdateManager.getInstance().getDebugTable());
        } finally {
            UpdateManager.getInstance().removeTableUpdateListener(tableListener);
        }
    }
}
