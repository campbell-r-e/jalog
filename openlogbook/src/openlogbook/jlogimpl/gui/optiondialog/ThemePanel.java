package openlogbook.jlogimpl.gui.optiondialog;

import openlogbook.jlog.util.ThemeUpdateEventId;
import openlogbook.util.UpdateManager;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;


/**
 * A panel that displays theme configuration options.
 * 
 * @author KC0ZPS
 */
public class ThemePanel extends JPanel {

   private OptionSkinChooser _skinChooser = new OptionSkinChooser() ;
   private JPanel            _checkBoxPanel = new JPanel() ; 
   private JLabel            _label = new JLabel("Load Custom Theme") ;
   private JCheckBox         _checkBox = new JCheckBox() ;
   private Component         _component ;
   
   private boolean           _isCustomTheme ;
   private boolean           _isDesktopBackground ;
   private boolean           _isXtraScrollbars ;
   private String            _themeName ;
   
   private ActionListener    _checkBoxActionListener ;
   private ComponentListener _componentListener;

   private static LookAndFeel _defaultLookAndFeel = UIManager.getLookAndFeel() ;
   
   static final long serialVersionUID = -1310642622997969171L;
   
   /**
    * Creates a new ThemePanel.
    */
   public ThemePanel () {
      super() ;
      
      initialize() ;
      addListeners() ;
   }
   
   //**********
   // Public methods
   //**********
   
   /**
    * Checks if the theme will load a background for the frame.
    * 
    * @return true if the theme will load a background for the frame.
    */
   public boolean isDesktopBackground() {
      return _isDesktopBackground ;
   }
   
   /**
    * Sets the load background flag.  This resets the default value too.
    * 
    * @param value the load background flag.
    */
   public void setIsDesktopBackground(boolean value) {
      _isDesktopBackground = value ;
      _skinChooser.setIsDesktopBackground(value) ;
   }
   
   /**
    * Checks if the theme will load XTra ScrollBars.
    * 
    * @return true if the theme will load XTra ScrollBars.
    */
   public boolean isXtraScrollbars() {
      return _isXtraScrollbars ;
   }
   
   /**
    * Sets the XTra Scrollbars flag.   This resets the default value too.
    * 
    * @param value the XTra Scrollbars flag.
    */
   public void setIsXtraScrollbars(boolean value) {
      _isXtraScrollbars = value ;
      _skinChooser.setIsXtraScrollbars(value) ;
   }
   
   /**
    * Checks if a custom theme is used.
    * 
    * @return true if the user selected a custom theme. 
    */
   public boolean isCustomTheme() {
      return _checkBox.isSelected() ;
   }
   
   /**
    * Sets the custom theme flag.
    * 
    * @param value the custom the flag.
    */
   public void setIsCustomTheme(boolean value) {
      _checkBox.setSelected(value) ;
      checkBoxActionPerformed() ;
      _isCustomTheme = value ;
   }
   
   /**
    * Gets the selected theme.
    * 
    * @return the selected theme.
    */
   public String getSelectedTheme() {
      return _skinChooser.getSelectedSkin() ;
   }
   
   /**
    * Sets the selected theme.
    * 
    * @param theme the selected theme.
    */
   public void setSelectedTheme(String theme) {
      _skinChooser.setSelectedSkin(theme) ;
      _themeName = theme ;
   }
   
   /**
    * Applies any selected theme.
    * 
    * @throws Exception if an error occurrs applying the selected theme.
    */
   public void apply() throws Exception {
      if (isCustomTheme()) {
         // No theme is selected.  Uncheck the box.
         if (_skinChooser.getSelectedSkin() == null) {
            _checkBox.setSelected(false) ;
            checkBoxActionPerformed() ;
            return ;
         }
         
         _skinChooser.apply() ;
         UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, "Theme update from OptionDialogBox.") ;
      } else {
         UIManager.setLookAndFeel(_defaultLookAndFeel) ;
         UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, 
               "Falling back to default look and feel from OptionDialogBox.") ;
         
      }
      _isCustomTheme = _checkBox.isSelected() ;
      _themeName = _skinChooser.getSelectedSkin() ;
      _isXtraScrollbars = _skinChooser.isXtraScrollbars() ;
      _isDesktopBackground = _skinChooser.isDesktopBackground() ;
   }
   
   /**
    * Cleans this object up.  Should be called when the object is no longer needed.
    */
   public void cleanup() {
      removeListeners() ;
   }

   /**
    * Restores the default values of this panel.
    */
   public void restoreDefaultValues() {
      _skinChooser.setIsDesktopBackground(_isDesktopBackground) ;
      _skinChooser.setIsXtraScrollbars(_isXtraScrollbars) ;
      _skinChooser.setSelectedSkin(_themeName) ;
      _checkBox.setSelected(_isCustomTheme) ;
      checkBoxActionPerformed() ;
   }
      
   //**********
   // Private methods
   //**********

   /**
    * Initializes the look and feel components.
    */
   private void initialize() {
      setLayout(new GridBagLayout()) ;
      GridBagConstraints gridBagConstraints1 = new GridBagConstraints();

      _checkBoxPanel.add(_checkBox) ;
      _checkBoxPanel.add(_label) ;
      gridBagConstraints1.gridx = 0;
      gridBagConstraints1.gridy = 0;
      gridBagConstraints1.gridwidth = 1;
      gridBagConstraints1.gridheight = 1;
      gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
      gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
      gridBagConstraints1.weightx = 1.0;
      add(_checkBoxPanel, gridBagConstraints1);
      
      String[] directories = {"themes"} ;
      _skinChooser.setBorder(BorderFactory.createEtchedBorder());
      _skinChooser.setThemePackMode(true) ;
      _skinChooser.setSkinLocations(directories) ;
      gridBagConstraints1.gridx = 0;
      gridBagConstraints1.gridy = 1;
      gridBagConstraints1.gridwidth = 1;
      gridBagConstraints1.gridheight = 1;
      gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
      gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
      gridBagConstraints1.weightx = 1.0;
      add(_skinChooser, gridBagConstraints1);      
   }

   /**
    * Initializes all listeners in this panel.
    */
   private void addListeners() {
      _checkBoxActionListener = new ActionListener() {
         public void actionPerformed(ActionEvent  evt) {
            checkBoxActionPerformed();
         }         
      } ;

      _checkBox.addActionListener(_checkBoxActionListener);
      
      _componentListener = new ComponentListener() {
         public void componentResized(ComponentEvent e){
            postOpenInit() ;
         }
         public void componentHidden(ComponentEvent e) {}
         public void componentMoved(ComponentEvent e) {}
         public void componentShown(ComponentEvent e) {}
      } ;
      
      addComponentListener(_componentListener) ;
   }

   /**
    * Removes any listeners.
    *
    */
   private void removeListeners() {
      _checkBox.removeActionListener(_checkBoxActionListener) ;
      removeComponentListener(_componentListener) ;
   }
   
   /**
    * Any initialization required after the panel has been opened.
    */
   private void postOpenInit() {
      checkBoxActionPerformed() ;
   }
   
   /**
    * Handles the same as start checkbox action.
    */
   private void checkBoxActionPerformed() {
      if (_checkBox == null) {
         return ;
      }
      
      if (_checkBox.isSelected()) {
         remove(_component) ;
         
         GridBagConstraints gridBagConstraints1 = new GridBagConstraints();

         gridBagConstraints1.gridx = 0;
         gridBagConstraints1.gridy = 1;
         gridBagConstraints1.gridwidth = 1;
         gridBagConstraints1.gridheight = 1;
         gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
         gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
         gridBagConstraints1.weightx = 1.0;
         add(_skinChooser, gridBagConstraints1) ;
      } else {
         remove(_skinChooser) ;
         
         GridBagConstraints gridBagConstraints1 = new GridBagConstraints();

         gridBagConstraints1.gridx = 0;
         gridBagConstraints1.gridy = 1;
         gridBagConstraints1.gridwidth = 1;
         gridBagConstraints1.gridheight = 1;
         gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
         gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
         gridBagConstraints1.weightx = 1.0;         
         _component = Box.createVerticalStrut(_skinChooser.getHeight()) ;
         add(_component, gridBagConstraints1);

      }
      
      this.repaint() ;
      this.updateUI() ;
   }
}
