package openlogbook.debug.monitor;

import openlogbook.debug.HistoryLog;
import openlogbook.debug.LogDebugTable;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


/**
 * A Frame to display a history log.
 */
public class HistoryLogFrame extends JFrame {
   
   /**
    * Creates a new HistoryLogFrame
    *
    * @param log  the history log to display
    *
    */
   public HistoryLogFrame(HistoryLog log) {
      super() ;
      _log = log ;
      _drillDownPanelClassname = _log.getDrillDownPanelClassname() ;
      _contents = _log.getContents() ;
      try {
         jbInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Creates a new HistoryLogFrame
    *
    * @param logFile  File containing saved log contents
    *
    */
   public HistoryLogFrame(File logFile) {
      this(logFile, null) ;
   }
   

   /**
    * Creates a new HistoryLogFrame
    *
    * @param logFile                 File containing saved log contents
    * @param drillDownPanelClassname Classname of class to use for drilling down on an individual entry
    *
    */
   public HistoryLogFrame(File logFile, String drillDownPanelClassname) {
      super() ;
      loadFromFile(logFile) ;
      _drillDownPanelClassname = drillDownPanelClassname ;
      try {
         jbInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   //**********
   // Package methods
   //**********

   /**
    * Selects a particular row in the log table. This method is provided so that a drill-down
    * dialog can modify the selected row when the Next/Previous buttons are operated.
    * 
    * @param row  the row to be selected.
    */
   void selectRow(int row) {
      DefaultListSelectionModel selectionModel = new DefaultListSelectionModel() ;
      selectionModel.addSelectionInterval(row, row) ;
      _logTable.setSelectionModel(selectionModel) ;
   }
   
   //**********
   // Private methods
   //**********

   /**
    * JBuilder initialization routine.
    *
    * @throws Exception on any exception
    */
   private void jbInit() throws Exception {
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      setSize(new Dimension(800, 500));
      _displayPanel.setLayout(_borderLayout1);
      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      addWindowListener(new java.awt.event.WindowAdapter() {
                           public void windowClosing(WindowEvent e) {
                              thisWindowClosing();
                           }
                        }) ;
      _clearButton.setText("Clear");
      _clearButton.setToolTipText("Click to clear contents") ;
      _clearButton.setMinimumSize(new Dimension(80, 30));
      _clearButton.setMaximumSize(new Dimension(80, 30));
      _clearButton.setPreferredSize(new Dimension(80, 30));
      _clearButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           clearButtonActionPerformed();
                                        }
                                     });
      _refreshButton.setText("Refresh");
      _refreshButton.setToolTipText("Click to refresh contents") ;
      _refreshButton.setMinimumSize(new Dimension(80, 30));
      _refreshButton.setMaximumSize(new Dimension(80, 30));
      _refreshButton.setPreferredSize(new Dimension(80, 30));
      _refreshButton.addActionListener(new java.awt.event.ActionListener() {
                                          public void actionPerformed(ActionEvent e) {
                                             refreshButtonActionPerformed();
                                          }
                                       });
      _exportButton.setText("Export");
      _exportButton.setToolTipText("Click to export to a text file") ;
      _exportButton.setMinimumSize(new Dimension(80, 30));
      _exportButton.setMaximumSize(new Dimension(80, 30));
      _exportButton.setPreferredSize(new Dimension(80, 30));
      _exportButton.addActionListener(new java.awt.event.ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                            exportButtonActionPerformed();
                                         }
                                      });

      _saveButton.setText("Save");
      _saveButton.setToolTipText("Click to save to a file") ;
      _saveButton.setMinimumSize(new Dimension(80, 30));
      _saveButton.setMaximumSize(new Dimension(80, 30));
      _saveButton.setPreferredSize(new Dimension(80, 30));
      _saveButton.addActionListener(new java.awt.event.ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                          saveButtonActionPerformed();
                                       }
                                    });

      _loadButton.setText("Load");
      _loadButton.setToolTipText("Click to load from a file") ;
      _loadButton.setMinimumSize(new Dimension(80, 30));
      _loadButton.setMaximumSize(new Dimension(80, 30));
      _loadButton.setPreferredSize(new Dimension(80, 30));
      _loadButton.addActionListener(new java.awt.event.ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                          loadButtonActionPerformed();
                                       }
                                    });

      _closeButton.setText("Close");
      _closeButton.setToolTipText("Click to close the log") ;
      _closeButton.setMinimumSize(new Dimension(80, 30));
      _closeButton.setMaximumSize(new Dimension(80, 30));
      _closeButton.setPreferredSize(new Dimension(80, 30));
      _closeButton.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                           closeButtonActionPerformed();
                                        }
                                     });

      getContentPane().setLayout(_borderLayout2);
      getContentPane().add(_buttonPanel, BorderLayout.WEST);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_clearButton, null);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_refreshButton, null);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_exportButton, null);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_saveButton, null);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_loadButton, null);
      _buttonPanel.add(Box.createVerticalStrut(5)) ;
      _buttonPanel.add(_closeButton, null);
      getContentPane().add(_displayPanel, BorderLayout.CENTER);

      _tableScrollPane = new JScrollPane(_logTable) ;
      _tableScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));

      _displayPanel.add(_tableScrollPane, BorderLayout.CENTER);

      _logTable.setRequestFocusEnabled(false) ;
      _logTable.setRowSelectionAllowed(true);
      _logTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
      _logTable.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(MouseEvent e) {
                                       logTableMouseClicked(e) ;
                                    }
                                 }) ;

      _detailsMenu.addActionListener(new java.awt.event.ActionListener() {

                                        public void actionPerformed(ActionEvent e) {
                                           detailsMenuActionPerformed() ;
                                           _detailsMenu.setVisible(false) ;
                                        }
                                     });
      _jPopupMenu1.add(_detailsMenu) ;
      if (_log == null) {
         _refreshButton.setEnabled(false) ;
         _clearButton.setEnabled(false) ;
      }

      _logModel = new DebugTableModel(_contents) ;
      _logTable.setModel(_logModel) ;
      TableAutoSizer.autoSizeTable(_logTable) ;
      _logTable.sizeColumnsToFit(-1) ;
      _logTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
      setTitle(_contents.getTableName()) ;
   }

   /**
    * Loads the log from a file
    * 
    * @param logFile  File containing the log.
    */
   private void loadFromFile(File logFile) {
      ObjectInputStream inStream = null ;
      try {
         inStream = new ObjectInputStream(new FileInputStream(logFile)) ;
         _contents = (LogDebugTable)inStream.readObject() ;
         _logModel = new DebugTableModel(_contents) ;
         _logTable.setModel(_logModel) ;
         TableAutoSizer.autoSizeTable(_logTable) ;
         _logTable.sizeColumnsToFit(-1) ;
      } catch (Exception e) {
         e.printStackTrace() ;
         JOptionPane.showMessageDialog(this, e.getMessage(), "Error reading the log", JOptionPane.ERROR_MESSAGE);
      } finally {
         if (inStream != null) {
            try {
               inStream.close() ;
            } catch (Exception e) {
               e.printStackTrace() ; // Ok to print. We're only in this path in test environments.
            }
         }
      }
   }
   /**
    * Reads the log contents and updates the model for the log table.
    *
    */
   private void readLog() {
      _contents = _log.getContents() ;
      _logModel.setContents(_contents) ;
      TableAutoSizer.autoSizeTable(_logTable) ;
      _logTable.sizeColumnsToFit(-1) ;
   }

   /**
    * Called when the Close button is pushed.
    *
    */
   private void closeButtonActionPerformed() {
      cleanup() ;
   }

   /**
    * Called when the Export button is pushed. Allows the user to
    * save the log contents to a text file.
    *
    */
   private void exportButtonActionPerformed() {
      _chooser.setDialogTitle("Export Log Contents") ;
      int returnVal = _chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         try {
            _contents.export(_chooser.getSelectedFile()) ;
         } catch (IOException ex) {
            // TBD - need to pop up some kind of error message
         }
      }
   }

   /**
    * Called when the Save button is pushed. Allows the user to
    * save the log contents to a binary file.
    *
    */
   private void saveButtonActionPerformed() {
      _chooser.setDialogTitle("Save Log Contents") ;
      int returnVal = _chooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         try {
            ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(_chooser.getSelectedFile())) ;
            try {
               objectStream.writeObject(_contents) ;
            } finally {
               objectStream.close() ;
            }
         } catch (Exception e) {
            e.printStackTrace() ;
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error saving the log", JOptionPane.ERROR_MESSAGE);
         }
      }
   }

   /**
    * Called when the Load button is pushed. Allows the user to
    * load the log contents to a binary file.
    *
    */
   private void loadButtonActionPerformed() {
      _chooser.setDialogType(JFileChooser.OPEN_DIALOG) ;
      _chooser.setMultiSelectionEnabled(false) ;
      _chooser.setDialogTitle("Load Log Contents") ;
      int returnVal = _chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         loadFromFile(_chooser.getSelectedFile()) ;
      }
   }

   /**
    * Called when the refresh button is pushed. Re-reads the log contents
    *
    */
   private void refreshButtonActionPerformed() {
      readLog() ;
   }

   /**
    * Called when the clear button is pushed. Clears the log
    *
    */
   private void clearButtonActionPerformed() {
      _log.clear() ;
      readLog() ;
   }

   /**
    * Called when the mouse is clicked on the log table. If the mouse was
    * double-clicked, attempts to "drill down".
    *
    * @param e  a MouseEvent
    */
   private void logTableMouseClicked(MouseEvent e) {
      boolean leftButton = (e.getModifiers() & InputEvent.BUTTON1_MASK) != 0 ;
      boolean rightButton = (e.getModifiers() & InputEvent.BUTTON3_MASK) != 0 ;
      if (leftButton && (e.getClickCount() == 2)) {
         detailsMenuActionPerformed();
      } else if (rightButton && e.getClickCount() == 1) {
         if (_drillDownPanelClassname != null) {
            _jPopupMenu1.show(e.getComponent(), e.getX(), e.getY()) ;
         }
      }
   }

   /**
    * Handles the remove ISL menu command.
    *
    */
   private void detailsMenuActionPerformed() {
      if (_drillDownPanelClassname == null) {
         return ;
      }
      try {
         Class<?> drillDownPanelClass = Class.forName(_drillDownPanelClassname) ;
         DrillDownPanel drillDownPanel = (DrillDownPanel)drillDownPanelClass.newInstance() ;
         int row = _logTable.getSelectedRow() ;
         if (drillDownPanel != null) {
            HistoryDrillDownDialog d = new HistoryDrillDownDialog(this,
                                                                  "History Log Entry Details",
                                                                  drillDownPanel,
                                                                  _contents,
                                                                  row);
            d.validate() ;
            d.setVisible(true) ;
         }
      } catch (Exception e) {
         e.printStackTrace() ; // Ok to print. We're only in this path in test environments.
      }
   }

   /**
    * Cleans up and closes the window.
    *
    *
    */
   private void cleanup() {
      setVisible(false) ;
      removeAll() ;
      _logTable.setModel(new DefaultTableModel()) ;
      _log = null ;
      _contents = null ;
      _logModel = null ;
      dispose() ;
   }


   /**
    * Called when the window is closing. Closes the frame.
    *
    */
   private void thisWindowClosing() {
      cleanup() ;
   }

   //**********
   // Class attributes and constants
   //**********

   private Box                   _buttonPanel = Box.createVerticalBox();
   private JPanel                _displayPanel = new JPanel();
   private JButton               _clearButton = new JButton();
   private JButton               _refreshButton = new JButton();
   private JButton               _exportButton = new JButton();
   private JButton               _saveButton = new JButton();
   private JButton               _loadButton = new JButton();
   private JButton               _closeButton = new JButton();
   private JScrollPane           _tableScrollPane = new JScrollPane() ;
   private JTable                _logTable = new JTable();
   private BorderLayout          _borderLayout1 = new BorderLayout();
   private BorderLayout          _borderLayout2 = new BorderLayout();
   private JMenuItem             _detailsMenu = new JMenuItem("Details") ;
   private JPopupMenu            _jPopupMenu1 = new JPopupMenu();
   private JFileChooser          _chooser = new JFileChooser() ;
   private transient HistoryLog  _log ;
   private LogDebugTable         _contents ;
   private DebugTableModel       _logModel ;
   private String                _drillDownPanelClassname = null ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.HistoryLogFrame
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -117747880669582135L;

}


// End of HistoryLogFrame.java

