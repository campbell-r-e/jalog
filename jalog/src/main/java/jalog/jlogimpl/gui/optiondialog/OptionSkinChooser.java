package jalog.jlogimpl.gui.optiondialog;

import jalog.debug.Debug;

import java.awt.BorderLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

/**
 * FlatLaf theme chooser panel.
 *
 * @author KC0ZPS
 */
public class OptionSkinChooser extends JPanel {

   private static final Map<String, String> THEMES = new LinkedHashMap<>();
   static {
      THEMES.put("FlatLaf Light",    "com.formdev.flatlaf.FlatLightLaf");
      THEMES.put("FlatLaf Dark",     "com.formdev.flatlaf.FlatDarkLaf");
      THEMES.put("FlatLaf IntelliJ", "com.formdev.flatlaf.FlatIntelliJLaf");
      THEMES.put("FlatLaf Darcula",  "com.formdev.flatlaf.FlatDarculaLaf");
   }

   private final JList<String> _themeList;

   static final long serialVersionUID = 6366066717040416763L;

   public OptionSkinChooser() {
      setLayout(new BorderLayout(3, 3));

      _themeList = new JList<>(THEMES.keySet().toArray(new String[0]));
      _themeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      _themeList.setSelectedIndex(0);

      add(new JScrollPane(_themeList), BorderLayout.CENTER);
   }

   /**
    * Returns the FlatLaf class name of the selected theme, for persistence.
    */
   public String getSelectedSkin() {
      String display = _themeList.getSelectedValue();
      if (display == null) {
         return null;
      }
      return THEMES.get(display);
   }

   /**
    * Selects the theme by FlatLaf class name (as previously persisted).
    */
   public void setSelectedSkin(String className) {
      if (className == null) {
         return;
      }
      for (Map.Entry<String, String> entry : THEMES.entrySet()) {
         if (entry.getValue().equals(className)) {
            _themeList.setSelectedValue(entry.getKey(), true);
            return;
         }
      }
      // Fall back to first entry if stored name doesn't match (e.g. old SkinLF name)
      _themeList.setSelectedIndex(0);
   }

   /**
    * Applies the selected FlatLaf theme.
    */
   public void apply() throws Exception {
      String className = getSelectedSkin();
      if (className == null) {
         return;
      }
      UIManager.setLookAndFeel(className);
   }

   // Stubs kept for backwards compatibility with INI persistence layer.
   public void setIsDesktopBackground(boolean value) {}
   public boolean isDesktopBackground() { return false; }
   public void setIsXtraScrollbars(boolean value) {}
   public boolean isXtraScrollbars() { return false; }
}
