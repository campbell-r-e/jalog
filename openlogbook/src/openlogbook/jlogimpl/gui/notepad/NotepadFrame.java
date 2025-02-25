package openlogbook.jlogimpl.gui.notepad;


import openlogbook.debug.Debug;
import openlogbook.jlog.gui.notepad.AbstractNotepadFrame;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * This is the Notepad frame. 
 * 
 * @author KC0ZPS
 */
public class NotepadFrame extends AbstractNotepadFrame {

   private JPanel            _mainPanel = new JPanel() ;
   private JTextArea         _textArea = new JTextArea() ;
   
   private static final File _notepadFile = new File("notepad.dat") ; 
   
   static final long serialVersionUID = -8696820932672326864L;

   /**
    * Creates a new NotepadFrame.
    */
   public NotepadFrame() {
      super();
      initComponents();
   }
   
   //**********
   // Public methods
   //**********

   /**
    * Reads text from the file.
    * 
    * @throws IOException if an I/O exception occurs.
    * @throws FileNotFoundException if the file isn't found.
    */
   public void readText() throws IOException, FileNotFoundException {
      FileReader fileReader = new FileReader(_notepadFile);
      BufferedReader  bufferedReader = new BufferedReader (fileReader);
      String inputString = new String() ;
      
      int count = 0;
      while((inputString = bufferedReader.readLine()) != null) {
         count++ ;
         StringBuffer buffer = new StringBuffer(inputString) ;
         if (count == 1) {
            _textArea.append(buffer.toString()) ;                        
         } else {
            _textArea.append("\n" + buffer.toString()) ;                        
         }
      }
      
      bufferedReader.close() ;
   }
   
   /**
    * Saves the text to the file.
    */
   public void saveText() {
      try {
         FileOutputStream fOutStream = new FileOutputStream(_notepadFile);
         PrintWriter printWriter = new PrintWriter(fOutStream, true);

         printWriter.println(_textArea.getText()) ;

         printWriter.flush();
         printWriter.close();
      } catch (Exception e) {
         Debug.logException(e) ;
      }      
   }
   
   //**********
   // Private methods
   //**********

   /**
    * Initializes the components in this frame.
    */
   private void initComponents() {      
      _mainPanel.setLayout(new BorderLayout()) ;
      _mainPanel.add(new JScrollPane(_textArea), BorderLayout.CENTER) ;
      
      getContentPane().add(_mainPanel);
      pack() ;
      
      setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE) ;
   }

}
