package jalog.util;

import jalog.factory.DefaultLogBookFactory;
import jalog.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import jalog.jlog.logbook.jlogentry.LogEntry;
import jalog.jlog.util.Band;
import jalog.jlog.util.Mode;
import jalog.jlogimpl.gui.TableColumnEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilityLoadWriteTest {

    @TempDir
    Path tempDir;

    private AbstractOptionsDialogBox mockDialog() {
        return mock(AbstractOptionsDialogBox.class);
    }

    private LogEntry entryWithDefaults() {
        LogEntry e = DefaultLogBookFactory.createLogEntry();
        e.getCallsign().setOperatingStation("W1AW");
        e.getFrequencyInformation().setBand(Band._20m);
        e.getFrequencyInformation().setMode(Mode.CW);
        e.getRst().setRstSent("599");
        e.getRst().setRstReceived("579");
        return e;
    }

    @Test
    void write_producesOperatorLine() throws Exception {
        Path ini = tempDir.resolve("out.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        when(dlg.getColumnList()).thenReturn(new ArrayList<>());
        when(dlg.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(dlg.isCustomTheme()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("Operator=W1AW"));
    }

    @Test
    void write_producesRstLines() throws Exception {
        Path ini = tempDir.resolve("out.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        when(dlg.getColumnList()).thenReturn(new ArrayList<>());
        when(dlg.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(dlg.isCustomTheme()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("RstSent=599"));
        assertTrue(content.contains("RstReceived=579"));
    }

    @Test
    void write_producesBandAndMode() throws Exception {
        Path ini = tempDir.resolve("out.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        when(dlg.getColumnList()).thenReturn(new ArrayList<>());
        when(dlg.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(dlg.isCustomTheme()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("Band="));
        assertTrue(content.contains("Mode="));
    }

    @Test
    void write_withTxRxPower_producesPowerLines() throws Exception {
        Path ini = tempDir.resolve("power.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        LogEntry e = entryWithDefaults();
        e.getFrequencyInformation().setTxPower(100);
        e.getFrequencyInformation().setRxPower(5);
        when(dlg.getColumnList()).thenReturn(new ArrayList<>());
        when(dlg.getDefaultLogEntry()).thenReturn(e);
        when(dlg.isCustomTheme()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("TxPower=100"));
        assertTrue(content.contains("RxPower=5"));
    }

    @Test
    void write_withCustomTheme_producesThemeLines() throws Exception {
        Path ini = tempDir.resolve("theme.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        when(dlg.getColumnList()).thenReturn(new ArrayList<>());
        when(dlg.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(dlg.isCustomTheme()).thenReturn(true);
        when(dlg.getSelectedTheme()).thenReturn("FlatDarkLaf");
        when(dlg.isXtraScrollbars()).thenReturn(true);
        when(dlg.isDesktopBackground()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("IsCustomTheme=true"));
        assertTrue(content.contains("Theme=FlatDarkLaf"));
        assertTrue(content.contains("XtraScrollBar=true"));
        assertTrue(content.contains("Background=false"));
    }

    @Test
    void write_withColumns_producesColumnLines() throws Exception {
        Path ini = tempDir.resolve("cols.ini");
        AbstractOptionsDialogBox dlg = mockDialog();
        ArrayList<TableColumnEnum> cols = new ArrayList<>();
        cols.add(TableColumnEnum.QsoDate);
        when(dlg.getColumnList()).thenReturn(cols);
        when(dlg.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(dlg.isCustomTheme()).thenReturn(false);

        Utility.write(ini.toString(), dlg);

        String content = Files.readString(ini);
        assertTrue(content.contains("Column="));
    }

    @Test
    void load_setsDefaultLogEntry() throws Exception {
        Path ini = tempDir.resolve("load.ini");
        Files.writeString(ini,
            "Operator=KC0ZPS\n" +
            "Band=5\n" +
            "Mode=2\n" +
            "RstReceived=599\n" +
            "RstSent=579\n" +
            "IsCustomTheme=false\n"
        );

        AbstractOptionsDialogBox dlg = mockDialog();
        Utility.load(ini.toString(), dlg);

        verify(dlg).setDefaultLogEntry(any(LogEntry.class));
        verify(dlg).setIsCustomTheme(false);
    }

    @Test
    void load_withCustomTheme_setsThemeFields() throws Exception {
        Path ini = tempDir.resolve("theme.ini");
        Files.writeString(ini,
            "IsCustomTheme=true\n" +
            "Theme=FlatDarkLaf\n" +
            "XtraScrollBar=true\n" +
            "Background=false\n" +
            "Operator=W1AW\n" +
            "Band=0\n" +
            "Mode=0\n"
        );

        AbstractOptionsDialogBox dlg = mockDialog();
        Utility.load(ini.toString(), dlg);

        verify(dlg).setIsCustomTheme(true);
        verify(dlg).setSelectedTheme("FlatDarkLaf");
        verify(dlg).setIsXtraScrollbars(true);
        verify(dlg).setIsDesktopBackground(false);
    }

    @Test
    void load_withColumnEntry_callsAddColumn() throws Exception {
        Path ini = tempDir.resolve("cols.ini");
        Files.writeString(ini,
            "Column=0\n" +
            "Operator=W1AW\n" +
            "Band=0\n" +
            "Mode=0\n"
        );

        AbstractOptionsDialogBox dlg = mockDialog();
        Utility.load(ini.toString(), dlg);

        verify(dlg, atLeastOnce()).addColumn(any(TableColumnEnum.class));
    }

    @Test
    void writeAndLoad_roundTripOperator() throws Exception {
        Path ini = tempDir.resolve("roundtrip.ini");

        AbstractOptionsDialogBox writer = mockDialog();
        when(writer.getColumnList()).thenReturn(new ArrayList<>());
        when(writer.getDefaultLogEntry()).thenReturn(entryWithDefaults());
        when(writer.isCustomTheme()).thenReturn(false);
        Utility.write(ini.toString(), writer);

        AbstractOptionsDialogBox reader = mockDialog();
        Utility.load(ini.toString(), reader);

        verify(reader).setDefaultLogEntry(argThat(entry ->
            "W1AW".equals(entry.getCallsign().getOperatingStation())
        ));
    }

    @Test
    void load_withFileHistoryEntry_addsToHistory() throws Exception {
        Path ini = tempDir.resolve("history.ini");
        String testFilePath = tempDir.resolve("old.adi").toString();
        Files.writeString(ini,
            "File=" + testFilePath + "\n" +
            "Operator=W1AW\n" +
            "Band=0\n" +
            "Mode=0\n"
        );

        AbstractOptionsDialogBox dlg = mockDialog();
        Utility.load(ini.toString(), dlg);

        assertTrue(Utility.getFileHistory().contains(testFilePath));
    }

    @Test
    void load_withTxRxPower_parsesValues() throws Exception {
        Path ini = tempDir.resolve("powers.ini");
        Files.writeString(ini,
            "Operator=W1AW\n" +
            "Band=5\n" +
            "Mode=2\n" +
            "TxPower=100\n" +
            "RxPower=5\n" +
            "RstReceived=599\n" +
            "RstSent=579\n" +
            "IsCustomTheme=false\n"
        );

        AbstractOptionsDialogBox dlg = mockDialog();
        Utility.load(ini.toString(), dlg);

        verify(dlg).setDefaultLogEntry(argThat(entry ->
            Integer.valueOf(100).equals(entry.getFrequencyInformation().getTxPower()) &&
            Integer.valueOf(5).equals(entry.getFrequencyInformation().getRxPower())
        ));
    }
}
