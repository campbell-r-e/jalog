package openlogbook.debug.monitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * A simple terminal display control.
 */
class TerminalDisplay extends JPanel {
   /**
    * Creates a new TerminalDisplay.
    * @param model  the TerminalModel for the terminal window
    */
   public TerminalDisplay(TerminalModel model) {
      super() ;
      _model = model ;
      try {
         jbInit();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   //**********
   // Public methods
   //**********

   /**
    * Returns the height of a row.
    *
    * @return    the height of a row
    */
   public int getRowHeight() {
      return _rowHeight ;
   }

   /**
    * Returns the current row number.
    *
    * @return    the current row number
    */
   public int getRow() {
      return _model.getRow() ;
   }

   /**
    * Paints the control.
    *
    * @param g a Graphics object
    *
    */
   public void paintComponent(Graphics g) {
      g.setFont(_font) ;

      Rectangle clip = g.getClipBounds();
      int topLine = clip.y / _rowHeight ;
      int bottomLine = (clip.y + clip.height) / _rowHeight ;

      // draw the characters
      for (int i = topLine; i <= bottomLine; i++) {
         if (i < _model.getNbrRows()) {
            g.setColor(Color.white) ;
            int y = i * _rowHeight ;
            g.fillRect(0, y, _rowWidth, _rowHeight) ;
            y += _rowHeight ;
            g.setColor(Color.black) ;
            g.drawChars(_model.getRowChars(i), 0, _model.getNbrColumns(), 0, y - _charDescent) ;
         }
      }
   }

   /**
    * Gets the preferred size for the control.
    *
    * @return  the preferred size for the control
    */
   public Dimension getPreferredSize() {
      return new Dimension(_rowWidth, _model.getNbrRows() * _rowHeight) ;
   }

   /**
    * Gets the size of the terminal display.
    *
    * @return the size of the terminal display.
    */
   public Dimension getSize() {
      return new Dimension(_rowWidth, _model.getNbrRows() * _rowHeight) ;
   }

   //**********
   // Private methods
   //**********

   /**
    * JBuilder initialization routine.
    *
    * @throws Exception on any exception.
    */
   private void jbInit() throws Exception {
      setLayout(new BorderLayout());
      _font = new Font("Courier", Font.PLAIN, 12);
      setFont(_font);
      _fontMetrics = getFontMetrics(_font);
      if (_fontMetrics != null) {
         _charWidth = _fontMetrics.charWidth('@');
         _charHeight = _fontMetrics.getHeight();
         _charDescent = _fontMetrics.getDescent();
         _rowHeight = _charHeight ;
         _rowWidth = _model.getNbrRows() * _charWidth ;
      }
      setSize(_rowWidth, _model.getNbrRows() * (_rowHeight)) ;
   }

   //**********
   // Class attributes and constants
   //**********

   private Font                     _font ;
   private FontMetrics              _fontMetrics ;
   private int                      _charWidth ;
   private int                      _charHeight ;
   private int                      _charDescent ;
   private int                      _rowHeight ;
   private int                      _rowWidth ;
   private transient TerminalModel  _model ;


   /**
    * The serialVersionUID for the class openlogbook.debug.monitor.TerminalDisplay
    * This should not be changed unless incompatible changes are made to this class.
    * If you do not know what that means, you should not be changing this class.
    * DO NOT copy this to another class.
    * Any class that is created using this class as a template should regenerate
    * a new serialVersionUID with the serialVer tool.
    *
    */
   static final long serialVersionUID = -6438053951234031759L;

}

// End of TerminalDisplay.java


