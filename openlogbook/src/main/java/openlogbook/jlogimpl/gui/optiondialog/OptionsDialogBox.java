package openlogbook.jlogimpl.gui.optiondialog;

import openlogbook.debug.Debug;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import openlogbook.jlog.logbook.jlogentry.LogEntry;
import openlogbook.jlog.util.TableColumnChangeEventId;
import openlogbook.jlogimpl.gui.TableColumnEnum;
import openlogbook.util.UpdateManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This is the options dialog box. 
 * 
 * @author KC0ZPS
 */
public class OptionsDialogBox extends AbstractOptionsDialogBox {
   
   private ThemePanel _themePanel ;

   // Other attributes and methods omitted for brevity...

   public String getSelectedTheme() {
      return _themePanel.getSelectedTheme() ;
   }

   public void setSelectedTheme(String theme) {
      _themePanel.setSelectedTheme(theme) ;
   }

   private void okButtonActionPerformed(ActionEvent evt) {
      try {
         UpdateManager.postTableColumnChangeEvent(TableColumnChangeEventId.OptionDialogEvent, 
               "OK pressed in option dialog box.", 
               _tableColumnPanel.getColumnData()) ;
      } catch (IllegalArgumentException e) {
         Debug.logException(e) ;
      }
   }
}
