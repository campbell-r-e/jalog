package openlogbook;

import openlogbook.debug.Debug;
import openlogbook.debug.DebugTable;
import openlogbook.debug.Debugable;
import openlogbook.debug.PropertyDebugTable;

import openlogbook.debug.monitor.DebugUi;
import openlogbook.factory.AdifFileFactory;
import openlogbook.factory.BasicFactory;
import openlogbook.factory.DefaultLogBookFactory;
import openlogbook.jlog.filehandler.ReadData;
import openlogbook.jlog.filehandler.SaveData;
import openlogbook.jlog.filehandler.XmlFileConstants;
import openlogbook.jlog.gui.AboutDialogBox;
import openlogbook.jlog.gui.AbstractLogBookFrame;
import openlogbook.jlog.gui.AbstractWindowMenu;
import openlogbook.jlog.gui.ThemeUpdateEvent;
import openlogbook.jlog.gui.ThemeUpdateListener;
import openlogbook.jlog.gui.notepad.AbstractNotepadFrame;
import openlogbook.jlog.gui.optiondialog.AbstractOptionsDialogBox;
import openlogbook.jlog.logbook.LogBook;
import openlogbook.jlog.util.EnhancedFileFilter;
import openlogbook.jlog.util.ThemeUpdateEventId;
import openlogbook.util.ScreenUtil;
import openlogbook.util.UpdateManager;
import openlogbook.util.Utility;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

import org.xml.sax.SAXException;

import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;

/**
 * So it all begins.
 * 
 * @author KC0ZPS
 */
public class Genesis extends JFrame implements ThemeUpdateListener, Debugable {
   
   private JDesktopPane         _jDesktopPane = new JDesktopPane();
   private JMenuBar             _mainMenuBar = new JMenuBar();
   private JMenu                _fileMenu = new JMenu();
   private JMenuItem            _fileNewMenuItem = new JMenuItem();
   private JMenuItem            _fileOpenMenuItem = new JMenuItem();
   private JMenuItem            _fileCloseMenuItem = new JMenuItem();
   private JSeparator           _fileSeperator1 = new JSeparator();
   private JMenuItem            _saveMenuItem = new JMenuItem();
   private JMenuItem            _saveAsMenuItem = new JMenuItem();
   private JSeparator           _jSeparator1 = new JSeparator();
   private JMenuItem            _fileExitMenuItem = new JMenuItem();
   private JSeparator           _jSeparator2 = new JSeparator();
   private JSeparator           _jSeparator3 = new JSeparator();
   private JMenu                _helpMenu = new JMenu();
   private JMenuItem            _helpAboutMenuItem = new JMenuItem();
   private JMenuItem            _helpDebugMenuItem = new JMenuItem();
   private JMenuItem            _recentFilesMenuItem[] = new JMenuItem[5];
   private JMenu                _editMenu = new JMenu();
   private JMenuItem            _optionsMenuItem = new JMenuItem();
   private JMenuItem            _notepadMenuItem = new JMenuItem();
   private WindowListener       _windowListener ;

   private AbstractOptionsDialogBox _optionsDialogBox ;
   private AbstractNotepadFrame     _notepadFrame = BasicFactory.createNotepadFrame() ;
   private AbstractWindowMenu       _windowMenu = BasicFactory.createWindowMenu() ;
   private AboutDialogBox           _aboutDialogBox = BasicFactory.createAboutDialogBox(this) ;

   // BUG -- Create the optionpane when the dialog is created, not when the button is pressed.
   // Otherwise, the optionpane may show an empty dialog.
   private static JOptionPane    _optionPane = new JOptionPane("Close without saving?", JOptionPane.QUESTION_MESSAGE, 
                                                               JOptionPane.YES_NO_OPTION) ;
   private static JDialog        _warningDialog ;

   private static final String FILE_EXTENSION = "adi" ;
   private static final String FILE_TYPE_NAME = "ADIF File" ;
   private static final String INI_FILE = "./openlogbook.ini" ;
   
   static final long serialVersionUID = -6822619067078391607L;
   
   /**
    * Genesis -- the start of it all.
    */
   public Genesis() {
      coreInit();
      initComponents();
      Debug.addObject(null, this) ;
      Debug.addObject(null, UpdateManager.getInstance()) ;
      
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
   }
   
   //**********
   // Implementation of Debugable interface
   //**********

   /**
    * Gets the contents of the object in a form suitable for displaying in a
    * table.
    *
    * @return the contents of the object in a form suitable for displaying in a
    *         table
    */
   public DebugTable getDebugTable() {
      String columnNames[] = { "Name", "Value" };
      PropertyDebugTable contents = new PropertyDebugTable("Genesis", columnNames);
      return contents;
   }
   
   //**********
   // Methods overwritten from Object.
   //**********

   public String toString() {
      return "Genesis" ;
   }

   //**********
   // Public methods
   //**********
   
   /**
    * Gets the OptionsDialogBox.
    * 
    * @return the OptionsDialogBox.
    */
   public AbstractOptionsDialogBox getOptionsDialogBox() {
      return _optionsDialogBox ;
   }
   
   //**********
   // Methods inherited from ThemeUpdateListener.
   //**********
   
   /**
    * Invoked when an action occurs.
    * 
    * @param themeUpdateEvent The ThemeUpdateEvent.
    */
   public void updateUI(ThemeUpdateEvent themeUpdateEvent) {
      SwingUtilities.updateComponentTreeUI(this);
      SwingUtilities.updateComponentTreeUI(_optionsDialogBox);
      SwingUtilities.updateComponentTreeUI(_optionPane) ;
   }
   
   //**********
   // Private methods
   //**********

   /**
    * Initializes application variables.
    */
    private void coreInit() {
      File externalIniFile = new File("openlogbook.ini"); // Check in the execution directory
  
      // 1️⃣ First, try loading an external INI file from the same folder as the JAR
      if (externalIniFile.exists()) {
          System.out.println("Using external INI file: " + externalIniFile.getAbsolutePath());
          try {
              _optionsDialogBox = BasicFactory.createOptionsDialogBox(this);
              Utility.load(externalIniFile.getAbsolutePath(), _optionsDialogBox);
              return;
          } catch (IOException e) {
              System.out.println("Error reading external INI file. Using fallback.");
          }
      }
  
      // 2️⃣ If not found, extract a default INI file from inside the JAR
      System.out.println("INI file not found externally. Extracting default from JAR...");
      try (InputStream in = Genesis.class.getClassLoader().getResourceAsStream("openlogbook.ini")) {
          if (in != null) {
              System.out.println("Extracting INI file from JAR...");
              Files.copy(in, externalIniFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
              _optionsDialogBox = BasicFactory.createOptionsDialogBox(this);
              Utility.load(externalIniFile.getAbsolutePath(), _optionsDialogBox);
          } else {
              System.err.println("ERROR: INI file not found anywhere!");
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
   
   /**
    * Initializes the components within this form.
    */
   private void initComponents() {
      _jDesktopPane.setPreferredSize(new Dimension(800, 600));
      
      for (int count = 0; count < _recentFilesMenuItem.length; count++) {
         _recentFilesMenuItem[count] = new JMenuItem();
      }
      _recentFilesMenuItem[0].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            history0MenuActionPerformed(evt);
         }
      });
      _recentFilesMenuItem[1].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            history1MenuActionPerformed(evt);
         }
      });
      _recentFilesMenuItem[2].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            history2MenuActionPerformed(evt);
         }
      });
      _recentFilesMenuItem[3].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            history3MenuActionPerformed(evt);
         }
      });
      _recentFilesMenuItem[4].addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            history4MenuActionPerformed(evt);
         }
      });
      
      _fileMenu.setMnemonic('F');
      _fileMenu.setText("File");
      _fileNewMenuItem.setMnemonic('N');
      _fileNewMenuItem.setText("New");
      _fileNewMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            fileNewMenuItemActionPerformed(evt);
         }
      });
      _fileMenu.add(_fileNewMenuItem);
      _fileOpenMenuItem.setMnemonic('O');
      _fileOpenMenuItem.setText("Open");
      _fileOpenMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            fileOpenMenuItemActionPerformed(evt);
         }
      });
      _fileMenu.add(_fileOpenMenuItem);
      _fileCloseMenuItem.setMnemonic('C');
      _fileCloseMenuItem.setText("Close");
      _fileCloseMenuItem.setEnabled(false);      
      _fileCloseMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            closeFrame(evt) ;               
         }
      });
      _fileMenu.add(_fileCloseMenuItem);      
      _fileMenu.add(_fileSeperator1);
      _saveMenuItem.setMnemonic('S');
      _saveMenuItem.setText("Save");
      _saveMenuItem.setEnabled(false);
      _saveMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            saveMenuItemActionPerformed(evt);
         }
      });
      _fileMenu.add(_saveMenuItem);
      _saveAsMenuItem.setMnemonic('A');
      _saveAsMenuItem.setText("Save As...");
      _saveAsMenuItem.setEnabled(false);
      _saveAsMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            saveAsMenuItemActionPerformed(evt);
         }
      });
      _fileMenu.add(_saveAsMenuItem);
      _fileMenu.add(_jSeparator1);
      _fileExitMenuItem.setMnemonic('x');
      _fileExitMenuItem.setText("Exit");
      _fileExitMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            fileExitMenuItemActionPerformed(evt);
         }
      });
      _fileMenu.add(_fileExitMenuItem);
      _fileMenu.add(_jSeparator2);
      _mainMenuBar.add(_fileMenu);
      _editMenu.setMnemonic('E');
      _editMenu.setText("Edit");
      _optionsMenuItem.setMnemonic('O');
      _optionsMenuItem.setText("Options");
      _optionsMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            optionsMenuItemActionPerformed(evt);
         }
      });
      _editMenu.add(_optionsMenuItem);
      
      _notepadMenuItem.setMnemonic('N');
      _notepadMenuItem.setText("Notepad");
      _notepadMenuItem.setEnabled(true);      
      _notepadMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            notepadMenuItemActionPerformed(evt);
         }
      });
      _editMenu.add(_notepadMenuItem);
      _mainMenuBar.add(_editMenu);
            
      _windowMenu.setMnemonic('W') ;
      _windowMenu.setText("Windows") ;
      _mainMenuBar.add(_windowMenu) ;
      
      _helpDebugMenuItem.setMnemonic('A');
      _helpDebugMenuItem.setText("Debug");
      _helpDebugMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            helpDebugMenuItemActionPerformed(evt);
         }
      });
      _helpMenu.add(_helpDebugMenuItem);
      _helpMenu.setMnemonic('H');
      _helpMenu.setText("Help");
      _helpAboutMenuItem.setMnemonic('A');
      _helpAboutMenuItem.setText("About");
      _helpAboutMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            helpAboutMenuItemActionPerformed(evt);
         }
      });
      _helpMenu.add(_helpAboutMenuItem);
      _mainMenuBar.add(_helpMenu);
            
      getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
      setTitle("openlogbook");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
            exitForm(evt);
         }
      });
      if (Utility.getFileHistory().size() > 0) {
         updateFileHistoryMenuItems();
      }
      setJMenuBar(_mainMenuBar);
      setContentPane(_jDesktopPane);
      pack();
      
      initializeNotepad() ;
      
      // Initialize the warning dialog.
      _warningDialog = _optionPane.createDialog(this, "Warning");
      
      initializeListeners() ;
   }
   
   /**
    * Initialize the notepad.
    */
   private void initializeNotepad() {
      addNotepadListeners() ;
      _jDesktopPane.add(_notepadFrame);
      _notepadFrame.setSize(new Dimension(300,300)) ;
      try {
         _notepadFrame.readText() ;         
      } catch (Exception e) {
         Debug.logException(e) ;
      }      
   }
   
   /**
    * Initialize any frame listeners.
    */
   private void initializeListeners() {
      _windowListener = new WindowListener() {
         public void windowActivated(WindowEvent e) {}
         public void windowClosed(WindowEvent e) {}
         public void windowClosing(WindowEvent e){}
         public void windowDeactivated(WindowEvent e){}
         public void windowDeiconified(WindowEvent e){}
         public void windowIconified(WindowEvent e) {} 
         public void windowOpened(WindowEvent e) {
            postOpenInit() ;
         }
      } ;
      addWindowListener(_windowListener) ;
      
      UpdateManager.getInstance().addThemeUpdateListener(this) ;
   }
   
   /**
    * Handles the history menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void history0MenuActionPerformed(ActionEvent evt) {
      String fileName = Utility.getFileHistory(0);
      File file = new File(fileName);
      openFile(file);
   }

   /**
    * Handles the history menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void history1MenuActionPerformed(ActionEvent evt) {
      String fileName = Utility.getFileHistory(1);
      File file = new File(fileName);
      openFile(file);
   }

   /**
    * Handles the history menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void history2MenuActionPerformed(ActionEvent evt) {
      String fileName = Utility.getFileHistory(2);
      File file = new File(fileName);
      openFile(file);
   }

   /**
    * Handles the history menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void history3MenuActionPerformed(ActionEvent evt) {
      String fileName = Utility.getFileHistory(3);
      File file = new File(fileName);
      openFile(file);
   }

   /**
    * Handles the history menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void history4MenuActionPerformed(ActionEvent evt) {
      String fileName = Utility.getFileHistory(4);
      File file = new File(fileName);
      openFile(file);
   }
   
   /**
    * Handles the open menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void fileOpenMenuItemActionPerformed(ActionEvent evt) {
      JFileChooser chooser = new JFileChooser(new File(Utility.getLastFilePath()));
      EnhancedFileFilter filter = new EnhancedFileFilter();
      filter.addExtension(FILE_EXTENSION);
      filter.setDescription(FILE_TYPE_NAME);
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File file = chooser.getSelectedFile();
         addFileHistoryMenuItem(file) ;
         openFile(file);
      }
   }
   
   /**
    * Opens a new file.
    * 
    * @param file The file to read from.
    */
   private void openFile(File file) {
      String saveFileName = file.getAbsolutePath() ;
      JInternalFrame[] logBookFrames = (JInternalFrame[])_jDesktopPane.getAllFrames() ;
      for (int count = 0; count < logBookFrames.length; count++) {
         if (logBookFrames[count] instanceof AbstractLogBookFrame) {
            String tmpfileName = ((AbstractLogBookFrame)logBookFrames[count]).getFile().getAbsolutePath() ;
            if (saveFileName.equals(tmpfileName)) {
               // File is already open.  Select it.
               try {
                  logBookFrames[count].setSelected(true) ;               
               } catch (PropertyVetoException e) {
                  displayExceptionDialog("Exception occurred requesting window focus.", e) ;
                  return ;
               }

               return ;
            }
         }
      }
      
      LogBook logBook = DefaultLogBookFactory.createLogBook(XmlFileConstants.FILE_VERSION) ;
      ReadData readData = AdifFileFactory.createReadAdifFile(file) ;
      
      try {
         readData.execute(logBook) ;
      } catch (SAXException e) {
         Debug.logException(e) ;
         displayExceptionDialog("Wrong XML file structure.", e) ;
         return ;
      } catch (IOException e) {
         Debug.logException(e) ;
         Utility.removeFileHistory(file) ;
         updateFileHistoryMenuItems();
         displayExceptionDialog("Could not read source file.", e) ;
         return ;
      } catch (ParserConfigurationException e) {
         Debug.logException(e) ;
         displayExceptionDialog("Wrong parser configuration.", e) ;
         return ;
      } catch (NumberFormatException e) {
         Debug.logException(e) ;
         displayExceptionDialog("Save file contains invalid data.", e) ;
         return ;
      } catch (Exception e) {
         Debug.logException(e) ;
         displayExceptionDialog("Unhandled exception occurred.", e) ;
         return ;         
      }
      
      AbstractLogBookFrame logBookFrame = BasicFactory.createLogBookFrame(this, logBook, file, _optionsDialogBox);
      _jDesktopPane.add(logBookFrame);
      addLogBookListener(logBookFrame) ;
      logBookFrame.setVisible(true);
      
      Debug.addObject(this, logBookFrame) ;
      _windowMenu.addLogBookEntry(logBookFrame) ;
   }
   
   /**
    * Handles the new menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void fileNewMenuItemActionPerformed(ActionEvent evt) {
      File file = new File("New Log Book." + FILE_EXTENSION) ;
      LogBook newLogBook = DefaultLogBookFactory.createLogBook(XmlFileConstants.FILE_VERSION) ;
      AbstractLogBookFrame logBookFrame = BasicFactory.createLogBookFrame(this, newLogBook, file, _optionsDialogBox);
      logBookFrame.setDataChanged(file.getName(), true) ;
      _jDesktopPane.add(logBookFrame);
      addLogBookListener(logBookFrame) ;
      logBookFrame.setVisible(true);
      Debug.addObject(this, logBookFrame) ;
      _windowMenu.addLogBookEntry(logBookFrame) ;
   }
   
   /**
    * Handles the save menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void saveMenuItemActionPerformed(ActionEvent evt) {
      JInternalFrame frame = _jDesktopPane.getSelectedFrame() ;
      
      // Handle Notepad frame.
      if (frame.equals(_notepadFrame)) {
         _notepadFrame.saveText() ;
         return ;
      }
      
      // Handle other frames.
      AbstractLogBookFrame focusedLogBook = (AbstractLogBookFrame)frame ;

      if (focusedLogBook.getFile() == null) {
         saveAsMenuItemActionPerformed(evt);
         return;
      }
      if (focusedLogBook.getFile().getName().equals("")) {
         saveAsMenuItemActionPerformed(evt);
         return;
      }
         
         SaveData saveData = AdifFileFactory.createWriteAdifFile(focusedLogBook.getFile(), focusedLogBook.getLogBook()) ;
         try {
            saveData.execute() ;  
         } catch (FileNotFoundException e){
            displayExceptionDialog("Error saving file.", e) ;
            return ;
         } catch (Exception e) {
            displayExceptionDialog("Unhandled exception occurred.", e) ;
            return ;         
         }
         addFileHistoryMenuItem(focusedLogBook.getFile()) ;
         focusedLogBook.setDataChanged(focusedLogBook.getFile().getName(), false) ;
   }
   
   /**
    * Close and cleanup the selected log book.
    * 
    * @param e The EventObject.
    */
   private void closeFrame(EventObject e) {
      Object object = e.getSource() ;

      JInternalFrame frame ;
      
      if (object instanceof JMenuItem) {
         frame = (JInternalFrame)_jDesktopPane.getSelectedFrame() ;         
      } else {
         frame = (JInternalFrame)object ;
         frame.requestFocus() ;
      }
      

      // Handle Notepad frame.
      if (frame.equals(_notepadFrame)) {
         _saveMenuItem.setEnabled(false);
         _saveAsMenuItem.setEnabled(false);
         _fileCloseMenuItem.setEnabled(false);
         _notepadFrame.setVisible(false) ;
         return ;
      }
      
      // Handle other frames.
      AbstractLogBookFrame focusedLogBook = (AbstractLogBookFrame)frame ;
      
      if (focusedLogBook.hasDataChanged()) {
         _warningDialog.setVisible(true);
         Integer selectedValue = (Integer)_optionPane.getValue();
         if (selectedValue.intValue() != 0) {
            return ;
         }      
      }
      
      _windowMenu.removeLogBookEntry(focusedLogBook) ;
      Debug.removeObject(focusedLogBook) ;
      System.out.println(focusedLogBook) ;
      focusedLogBook.dispose() ;
      focusedLogBook.cleanup() ;
      
      InternalFrameListener[] internalFrameListeners = focusedLogBook.getInternalFrameListeners() ;
      for (int count = 0; count < internalFrameListeners.length; count++) {
         focusedLogBook.removeInternalFrameListener(internalFrameListeners[count]) ;
      }
      
      if (_jDesktopPane.getSelectedFrame() == null) {
         _saveMenuItem.setEnabled(false);
         _saveAsMenuItem.setEnabled(false);
         _fileCloseMenuItem.setEnabled(false);              
      }
   }
   
   /**
    * Adds a the internal frame listeners to the log book.
    * 
    * @param logBookFrame The log book in which the listeners will be added.
    */
   private void addLogBookListener(AbstractLogBookFrame logBookFrame) {
      InternalFrameListener internalFrameListener = new InternalFrameListener() {
         public void internalFrameActivated(InternalFrameEvent e) {
            _saveMenuItem.setEnabled(true);
            _saveAsMenuItem.setEnabled(true);
            _fileCloseMenuItem.setEnabled(true);

         }
         public void internalFrameClosing(InternalFrameEvent evt) {
            closeFrame(evt) ;
         }
         public void internalFrameIconified(InternalFrameEvent e) {
            _saveMenuItem.setEnabled(false);
            _saveAsMenuItem.setEnabled(false);
            _fileCloseMenuItem.setEnabled(false);
         }
         public void internalFrameClosed(InternalFrameEvent e) {}
         public void internalFrameDeactivated(InternalFrameEvent e) {}
         public void internalFrameDeiconified(InternalFrameEvent e) {}
         public void internalFrameOpened(InternalFrameEvent e) {}
      } ;
      logBookFrame.addInternalFrameListener(internalFrameListener);
   }
   
   /**
    * Adds the notepad listeners.
    */
   private void addNotepadListeners() {
      InternalFrameListener internalFrameListener = new InternalFrameListener() {
         public void internalFrameActivated(InternalFrameEvent e) {
            _saveMenuItem.setEnabled(true);
            _saveAsMenuItem.setEnabled(false);
            _fileCloseMenuItem.setEnabled(true);
         }
         public void internalFrameClosing(InternalFrameEvent evt) {
            _notepadFrame.setVisible(false) ;
            _saveMenuItem.setEnabled(false);
            _saveAsMenuItem.setEnabled(false);
            _fileCloseMenuItem.setEnabled(false);
         }
         public void internalFrameIconified(InternalFrameEvent e) {
            _saveMenuItem.setEnabled(false);
            _saveAsMenuItem.setEnabled(false);
            _fileCloseMenuItem.setEnabled(false);
         }
         public void internalFrameClosed(InternalFrameEvent e) {}
         public void internalFrameDeactivated(InternalFrameEvent e) {}
         public void internalFrameDeiconified(InternalFrameEvent e) {}
         public void internalFrameOpened(InternalFrameEvent e) {}
      } ;
      _notepadFrame.addInternalFrameListener(internalFrameListener);

   }
   /**
    * Handles the save as menu item action.
    * 
    * @param evt The ActionEvent.
    */
   private void saveAsMenuItemActionPerformed(ActionEvent evt) {
      JInternalFrame frame = _jDesktopPane.getSelectedFrame() ;
      AbstractLogBookFrame focusedLogBook = (AbstractLogBookFrame)frame ;
      
      JFileChooser chooser = new JFileChooser(new File(Utility.getLastFilePath()));
      EnhancedFileFilter filter = new EnhancedFileFilter();
      filter.addExtension(FILE_EXTENSION);
      filter.setDescription(FILE_TYPE_NAME);
      chooser.setFileFilter(filter);
      int returnVal = chooser.showSaveDialog(this);
      this.validate();
      this.repaint();
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         if (focusedLogBook != null) {
               File file = chooser.getSelectedFile() ;
               if (!file.getName().endsWith("." + FILE_EXTENSION)) {
                  file = new File(file.getAbsolutePath() + "." + FILE_EXTENSION);
               }
               
               focusedLogBook.setFile(file) ;
               focusedLogBook.setDataChanged(file.getName(), false) ;
               SaveData saveData = AdifFileFactory.createWriteAdifFile(file, focusedLogBook.getLogBook()) ;
               try {
                  saveData.execute() ;
               } catch (FileNotFoundException e){
                  displayExceptionDialog("Error saving file.", e) ;
                  return ;
               } catch (Exception e) {
                  displayExceptionDialog("Unhandled exception occurred.", e) ;
                  return ;         
               }
               addFileHistoryMenuItem(file) ;
               _windowMenu.updateMenuItem(focusedLogBook) ;
         }
      }
   }
   
   /**
    * Handles the exit menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void fileExitMenuItemActionPerformed(ActionEvent evt) {
      this.quit();
   }
      
   /**
    * Handles the option menu item action.
    * 
    * @param evt The ActionEvent .
    */
   private void optionsMenuItemActionPerformed(ActionEvent evt) {
      ScreenUtil.centerOnFrame(this, _optionsDialogBox);
      _optionsDialogBox.setVisible(true);
      this.validate();
      this.repaint();      
   }
   
   /**
    * Handles the renumber menu item action.
    * 
    * @param evt The ActionEvent .
    */
   private void notepadMenuItemActionPerformed(ActionEvent evt) {
      _notepadFrame.setVisible(true) ;
      _saveMenuItem.setEnabled(true);
      _saveAsMenuItem.setEnabled(false);
      _fileCloseMenuItem.setEnabled(true);

   }
   
   /**
    * Handles the help->about menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void helpAboutMenuItemActionPerformed(ActionEvent evt) {
      _aboutDialogBox.setVisible() ;
   }

   /**
    * Handles the help->about menu item action.
    *
    * @param evt The ActionEvent.
    */
   private void helpDebugMenuItemActionPerformed(ActionEvent evt) {
      DebugUi.openDebugWindow() ;
   }

   /**
    * Adds a file entry to the file history menu item.
    * 
    * @param file The file to add.
    */
   private void addFileHistoryMenuItem(File file) {
      Utility.setLastFilePath(file.getPath());
      Utility.addFileHistory(file);
      updateFileHistoryMenuItems() ;
   }
   
   /**
    * Updates the history of files in the file menu item.
    */
   private void updateFileHistoryMenuItems() {
      for (int count = 0; count < 5; count++) {
         if (_recentFilesMenuItem[count] != null) {
            _recentFilesMenuItem[count].setText("") ;
            _fileMenu.remove(_recentFilesMenuItem[count]);
         }
      }
      ArrayList<String> fileList = Utility.getFileHistory();
      _fileMenu.remove(_fileExitMenuItem);
      _fileMenu.remove(_jSeparator2);
      _fileMenu.remove(_jSeparator3);
      for (int count = 0; count < fileList.size(); count++) {
         String fileName = (String) fileList.get(count);
         String reducedName = shortenName(fileName);
         _recentFilesMenuItem[count].setText(reducedName);
         _fileMenu.add(_recentFilesMenuItem[count]);
      }
      _fileMenu.add(_jSeparator2);
      _fileMenu.add(_fileExitMenuItem);
      _fileMenu.add(_jSeparator3);      
   }
   
   /**
    * Displays an exception in a user dialog.
    * 
    * @param prefix Any prefix to append to the message.
    * @param e The exception. 
    */
   private void displayExceptionDialog(String prefix, Exception e) {
      String message = prefix + "\n" 
      + e.getClass() + "\n"
      + e.getMessage() ;
      JOptionPane.showMessageDialog(_jDesktopPane, message, "Error", JOptionPane.ERROR_MESSAGE) ;
   }
   
   /**
    * Exit the Application.
    *
    *  @param evt The windowEvent.
    */
   private void exitForm(WindowEvent evt) {
      this.quit();
   }

   /**
    * The application shutdown procedure.
    */
   private void quit() {
      JInternalFrame[] frames = _jDesktopPane.getAllFrames() ;
      
      boolean suggestSave = false ;
      for (int count = 0; count < frames.length; count++) {
         if (frames[count] instanceof AbstractLogBookFrame) {
            AbstractLogBookFrame logBookFrame = (AbstractLogBookFrame)frames[count] ;
            if (logBookFrame.hasDataChanged()) {
               suggestSave = true ;
               continue ;
            }
         }
      }
      
      if (suggestSave) {
         _warningDialog.setVisible(true);
         Integer selectedValue = (Integer)_optionPane.getValue();
         if (selectedValue.intValue() != 0) {
            return ;
         }      
      }

      Utility.write(INI_FILE, _optionsDialogBox);
      _notepadFrame.saveText() ;
      System.exit(0);
   }
   
   /**
    * For file names in the menu item, shortens the string if its too long.
    * 
    * @param string the string to shorten.
    * @return a shortened version of the string, if too long.
    */
   private String shortenName(String string) {
      StringBuffer buffer = new StringBuffer(string);
      if (buffer.length() <= 25) {
         return string;
      }

      int startChar = buffer.length() - 25;
      int endChar = buffer.length();
      return new StringBuffer("...").append(buffer.substring(startChar, endChar)).toString();
   }
  
   /**
    * Any initialization that is required after the frame is open.
    */
   private void postOpenInit() {
      // Initialize the theme after the frame has been displayed.  For some reason unknown to me
      // not all of the components will be themed if this is called before the frame is actually 
      // visible.
      try {
         if(_optionsDialogBox.isCustomTheme()) {
            System.out.println("Applying the custom theme " + _optionsDialogBox.getSelectedTheme()) ;
            UIManager.put("JDesktopPane.backgroundEnabled", true);
            UIManager.put("ScrollBar.alternateLayout", true);

            Skin theSkinToUse = SkinLookAndFeel.loadThemePack("themes/" + _optionsDialogBox.getSelectedTheme());
            SkinLookAndFeel.setSkin(theSkinToUse) ;    
            UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
         } else {
            System.out.println("Using the system theme.") ;
         }
      } catch (Exception e) {
         System.out.println("Error loading theme.  Using the system theme instead.\n") ;
         Debug.logException(e) ;
      }

      UpdateManager.postThemeUpdateEvent(ThemeUpdateEventId.General, "Theme update on startup.") ;
   }

   
   /**
    * This applications main entry point.
    *
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      new Genesis().setVisible(true) ;
   }
}
