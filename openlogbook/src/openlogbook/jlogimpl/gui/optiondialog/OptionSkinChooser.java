package openlogbook.jlogimpl.gui.optiondialog;

import com.l2fprod.gui.plaf.skin.CompoundSkin;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.gui.plaf.skin.SkinPreviewWindow;
import com.l2fprod.util.OS;

import openlogbook.debug.Debug;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// import javax.swing.*;

/**
 * Skin Chooser.  This is almost an exact copy of the SkinChooser code that is packaged with the
 * Skin Look and Feel class.  I had to clone it because the original SkinChooser had a few bugs
 * and lacked certain functions I needed.
 * 
 * For example,
 * getSelectedSkins() is bugged.  It throws a ClassCastException.
 * Created getSelectedSkin() instead.
 * Created setSelectedSkin()
 * Only one theme can be selected.
 * 
 * @author $Author: KC0ZPS $
 * @created 27 avril 2002
 * @version $Revision: 1.3 $, $Date: 2004/07/18 16:54:48 $
 */
public class OptionSkinChooser extends JPanel {

   private JList          _skinList;
   private String[]       _directories;
   private JCheckBox      _backgroundCheckBox;
   private JCheckBox      _scrollBarCheckBox;
   private ResourceBundle _bundle;
   private boolean        _themePackMode = false;
   
   final static String REFRESH_CMD = "refresh";
   final static String PREVIEW_CMD = "preview";
   final static String GETSKINS_CMD = "getskins";

   static final long serialVersionUID = 6366066717040416763L;

   /**
    * Construct a new SkinChooser pane.
    */
   public OptionSkinChooser() {
      loadResourceBundle();

      setLayout(new BorderLayout(3, 3));

      JPanel listPane = new JPanel(new BorderLayout(3, 3));

      JPanel buttonPane = new JPanel(new GridLayout(1, 3, 3, 3));

      JButton button = new JButton(_bundle.getString("SkinChooser.getskins"));
      // buttonPane.add(button);
      button.addActionListener(new GetSkinsAction());
      button.setToolTipText(_bundle.getString("SkinChooser.getskins.tip"));

      button = new JButton(_bundle.getString("SkinChooser.preview"));
      // buttonPane.add(button);
      button.addActionListener(new PreviewAction());

      button = new JButton(_bundle.getString("SkinChooser.refresh"));
      buttonPane.add(button);
      button.addActionListener(new RefreshAction());

      _skinList = new JList() ;
      listPane.add("Center", new JScrollPane(_skinList));
      listPane.add("South", buttonPane);

      add("Center", listPane);

      Box optionPane = Box.createVerticalBox();
      _backgroundCheckBox = new JCheckBox(_bundle.getString("SkinChooser.enableBackground")) ;
      optionPane.add(_backgroundCheckBox);
      _scrollBarCheckBox = new JCheckBox(_bundle.getString("SkinChooser.enableScrollBar")) ;
      optionPane.add(_scrollBarCheckBox);
      add("East", optionPane);

      _skinList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   //**********
   // Public methods
   //**********

   /**
    * Set search paths
    * 
    * @param directories
    *           search paths
    */
   public void setSkinLocations(String[] directories) {
      _directories = directories;
      Vector<String> skins = new Vector<String>();
      int c = directories.length ;
      for (int i = 0; i < c; i++) {
         buildSkinList(skins, new File(directories[i]));
      }
      _skinList.setListData(skins);
   }

   /**
    * Set theme pack mode to true if you want to select a theme pack from the
    * chooser.
    * 
    * @param b
    *           the new value
    */
   public void setThemePackMode(boolean b) {
      _themePackMode = b;
   }

   /**
    * @return search paths
    */
   public String[] getSkinLocations() {
      return _directories;
   }

   /**
    * @return true if the chooser shows only theme packs
    */
   public boolean getThemePackMode() {
      return _themePackMode;
   }

   /**
    * Returns the currently selected skin.
    * 
    * @return the currently selected skin.
    */
   public String getSelectedSkin() {
      return (String) _skinList.getSelectedValue();
   }

   /**
    * Sets the selected skin.
    * 
    * @param theme the theme file name.
    */
   public void setSelectedSkin(String theme) {
      _skinList.setSelectedValue(theme, false) ;         
   }

   /**
    * Refresh the skin list.
    * 
    * @see #setSkinLocations
    */
   public void refreshList() {
      if ((_directories != null) && (_directories.length > 0)) {
         setSkinLocations(_directories);
      }
   }

   /**
    * Apply current selection. <br>
    * The method sets the current skin (SkinLookAndFeel.setSkin) then calls
    * UIManager.setLookAndFeel.
    * 
    * @exception Exception
    *               Description of Exception
    */
   public void apply() throws Exception {
      Object[] values = _skinList.getSelectedValues();
      
      if ((values == null) || (_themePackMode && values.length != 1)
            || (!_themePackMode && values.length != 2)) {
         return;
      }

      StringBuffer buffer = new StringBuffer() ;
      buffer.append("themes/" + (String)values[0]) ;
      

      UIManager.put("JDesktopPane.backgroundEnabled", _backgroundCheckBox
            .isSelected() ? Boolean.TRUE : null);
      UIManager.put("ScrollBar.alternateLayout",
            _scrollBarCheckBox.isSelected() ? Boolean.TRUE : null);

      Skin skin = null;

      if (_themePackMode) {
         skin = SkinLookAndFeel.loadThemePack(buffer.toString());
      } else {
         // skin = new CompoundSkin(SkinLookAndFeel.loadSkin((String) values[0]),
         //      SkinLookAndFeel.loadSkin((String) values[1]));
      }

      SkinLookAndFeel.setSkin(skin);

      UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
   }

   /**
    * Sets the XTra Scrollbars flag.
    * 
    * @param value the XTra Scrollbars flag.
    */
   public void setIsXtraScrollbars(boolean value) {
      _scrollBarCheckBox.setSelected(value) ;
   }
   
   /**
    * Returns the xtrascrollbars checked value.
    * 
    * @return the xtrascrollbars checked value.
    */
   public boolean isXtraScrollbars() {
      return _scrollBarCheckBox.isSelected() ;
   }
   
   /**
    * Sets the load background flag.
    * 
    * @param value the load background flag.
    */
   public void setIsDesktopBackground(boolean value) {
      _backgroundCheckBox.setSelected(value) ;
   }

   /**
    * Returns the desktop background checked value.
    * 
    * @return the desktop background checked value.
    */
   public boolean isDesktopBackground() {
      return _backgroundCheckBox.isSelected(); 
   }
   
   //**********
   // Protected methods
   //**********

   /**
    * Recursively traverse <code>directory</code> and add skin files to
    * <code>v</code> . <br>
    * Skin files are added if <code>accept(skinFile)</code> returns
    * <code>true</code>
    * 
    * @param v
    *           vector to store skin list
    * @param directory
    *           the directory to list for skin files
    */
   protected void buildSkinList(Vector<String> v, File directory) {
      if (!directory.isDirectory() || !directory.exists()) {
         return;
      }

      String[] files = directory.list();
      File f;
      int c = files.length ;
      for (int i = 0; i < c; i++) {
         f = new File(directory, files[i]);
         if (f.isDirectory()) {
            buildSkinList(v, f);
         } else if (accept(f)) {
            // try {
               // v.addElement(f.getCanonicalPath());
               v.addElement(f.getName());
            // } catch (IOException e) {
            // }
         }
      }
   }

   /**
    * Check if a given file is a skin file. <br>
    * Subclasses can override this method to provide better handling of skin
    * files. <br>
    * The default implementation checks if the file ends with gtkrc or themerc.
    * 
    * @param f
    *           the file to check
    * @return true if the file is a valid skin file
    */
   protected boolean accept(File f) {
      return (f.isDirectory() == false && ((_themePackMode && f.getName()
            .endsWith(".zip")) || (f.getName().endsWith("gtkrc") || f.getName()
            .endsWith("themerc"))));
   }

   /**
    * Description of the Method
    */
   protected void showPreviewWindow() {
      Skin oldSkin = SkinLookAndFeel.getSkin();
      LookAndFeel oldLAF = UIManager.getLookAndFeel();

      try {
         Object[] values = _skinList.getSelectedValues();
         if ((values == null) || (values.length != 2)) {
            return;
         }

         Skin skin = new CompoundSkin(SkinLookAndFeel
               .loadSkin((String) values[0]), SkinLookAndFeel
               .loadSkin((String) values[1]));

         SkinLookAndFeel.setSkin(skin);
         UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");

         Window window = new SkinPreviewWindow();
         window.setVisible(true);

      } catch (Exception e) {
         Debug.logException(e) ;
      } finally {
         SkinLookAndFeel.setSkin(oldSkin);
         try {
            UIManager.setLookAndFeel(oldLAF);
         } catch (UnsupportedLookAndFeelException e) {
            Debug.logException(e) ;
         }
      }
   }

   //**********
   // Private methods
   //**********

   /**
    * Description of the Method
    */
   private void loadResourceBundle() {
      _bundle = ResourceBundle
            .getBundle("com.l2fprod.gui.plaf.skin.resources.skin");
   }

   /**
    * Description of the Class
    * 
    * @author fred
    * @created 27 avril 2002
    */
   private class RefreshAction extends AbstractAction {
      
      static final long serialVersionUID = -3309820901628850649L;

      /**
       * Constructor for the RefreshAction object
       */
      public RefreshAction() {
         super(REFRESH_CMD);
      }

      /**
       * Description of the Method
       * 
       * @param event
       *           Description of Parameter
       */
      public void actionPerformed(ActionEvent event) {
         refreshList();
      }
   }

   /**
    * Description of the Class
    * 
    * @author fred
    * @created 27 avril 2002
    */
   private class GetSkinsAction extends AbstractAction {
      
      static final long serialVersionUID = -6844767787487114797L;
      
      /**
       * Constructor for the GetSkinsAction object
       */
      public GetSkinsAction() {
         super(GETSKINS_CMD);
      }

      /**
       * Description of the Method
       * 
       * @param event
       *           Description of Parameter
       */
      public void actionPerformed(ActionEvent event) {
         try {
            OS.openDocument(_bundle.getString("SkinChooser.getskins.url"));
         } catch (Exception e) {
            Debug.logException(e) ;
         }
      }
   }

   /**
    * Description of the Class
    * 
    * @author fred
    * @created 27 avril 2002
    */
   private class PreviewAction extends AbstractAction {
      
      static final long serialVersionUID = 2311413564240859984L;

      /**
       * Constructor for the PreviewAction object
       */
      public PreviewAction() {
         super(PREVIEW_CMD);
      }

      /**
       * Description of the Method
       * 
       * @param event
       *           Description of Parameter
       */
      public void actionPerformed(ActionEvent event) {
         showPreviewWindow();
      }
   }

}
