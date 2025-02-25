/*
 * @(#)EnhancedFileFilter.java   1.9 99/04/23
 *
 * Copyright (c) 1998, 1999 by Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package openlogbook.jlog.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.filechooser.FileFilter;

/**
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.
 *
 * Extensions are of the type ".foo", which is typically found on
 * Windows and Unix boxes, but not on Macinthosh. Case is ignored.
 *
 * Example - create a new filter that filerts out all files
 * but gif and jpg image files:
 *
 *     JFileChooser chooser = new JFileChooser();
 *     EnhancedFileFilter filter = new EnhancedFileFilter(
 *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 *
 * @version 1.9 04/23/99
 * @author Jeff Dinkins
 */
public class EnhancedFileFilter extends FileFilter {

   // private static String TYPE_UNKNOWN = "Type Unknown";
   // private static String HIDDEN_FILE = "Hidden File";

   private Hashtable<String, EnhancedFileFilter> _filters = null;
   private String  _description = null;
   private String  _fullDescription = null;
   private boolean _useExtensionsInDescription = true;

   /**
    * Creates a file filter. If no filters are added, then all
    * files are accepted.
    *
    * @see #addExtension
    */
   public EnhancedFileFilter() {
      _filters = new Hashtable<String, EnhancedFileFilter>();
   }

   /**
    * Creates a file filter that accepts files with the given extension.
    * Example: new EnhancedFileFilter("jpg");
    *
    * @param extension The name of the file extension.
    *
    * @see #addExtension
    */
   public EnhancedFileFilter(String extension) {
      this(extension, null);
   }

   /**
    * Creates a file filter that accepts the given file type.
    * Example: new EnhancedFileFilter("jpg", "JPEG Image Images");
    *
    * Note that the "." before the extension is not needed. If
    * provided, it will be ignored.
    *
    * @param extension The name of the file extension.
    * @param description The description of the file extension.
    *
    * @see #addExtension
    */
   public EnhancedFileFilter(String extension, String description) {
      this();
      if (extension != null) {
         addExtension(extension);         
      }
      if (description != null) {
         setDescription(description);         
      }
   }

   /**
    * Creates a file filter from the given string array.
    * Example: new EnhancedFileFilter(String {"gif", "jpg"});
    *
    * Note that the "." before the extension is not needed adn
    * will be ignored.
    *
    * @param filters The array of filters.
    *
    * @see #addExtension
    */
   public EnhancedFileFilter(String[] filters) {
      this(filters, null);
   }

   /**
    * Creates a file filter from the given string array and description.
    * Example: new EnhancedFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
    *
    * Note that the "." before the extension is not needed and will be ignored.
    *
    * @param filters The array of filters.
    * @param description The description of the filter.
    *
    * @see #addExtension
    */
   public EnhancedFileFilter(String[] filters, String description) {
      this();
      for (int i = 0; i < filters.length; i++) {
         // add filters one by one
         addExtension(filters[i]);
      }
      if (description != null) {
         setDescription(description);         
      }
   }

   /**
    * Return true if this file should be shown in the directory pane,
    * false if it shouldn't.
    *
    * Files that begin with "." are ignored.
    *
    * @param f The file in question.
    * 
    * @return true true if this file should be shown in the directory pane,
    * false if it shouldn't.
    *
    * @see #getExtension
    * @see FileFilter#accepts
    */
   public boolean accept(File f) {
      if (f != null) {
         if (f.isDirectory()) {
            return true;
         }
         String extension = getExtension(f);
         if (extension != null && _filters.get(getExtension(f)) != null) {
            return true;
         }
      }
      return false;
   }

   /**
    * Return the extension portion of the file's name .
    *
    * @param f The file in question.
    * 
    * @return The extension.
    *
    * @see #getExtension
    * @see FileFilter#accept
    */
   public String getExtension(File f) {
      if (f != null) {
         String filename = f.getName();
         int i = filename.lastIndexOf('.');
         if (i > 0 && i < filename.length() - 1) {
            return filename.substring(i + 1).toLowerCase();
         }
      }
      return null;
   }

   /**
    * Adds a filetype "dot" extension to filter against.
    *
    * For example: the following code will create a filter that filters
    * out all files except those that end in ".jpg" and ".tif":
    *
    *   EnhancedFileFilter filter = new EnhancedFileFilter();
    *   filter.addExtension("jpg");
    *   filter.addExtension("tif");
    *
    * Note that the "." before the extension is not needed and will be ignored.
    * 
    * @param extension The extension.
    */
   public void addExtension(String extension) {
      if (_filters == null) {
         _filters = new Hashtable<String, EnhancedFileFilter>(5);
      }
      _filters.put(extension.toLowerCase(), this);
      _fullDescription = null;
   }

   /**
    * Returns the human readable description of this filter. For
    * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
    *
    * @return The description of the extension.
    *
    * @see setDescription
    * @see setExtensionListInDescription
    * @see isExtensionListInDescription
    * @see FileFilter#getDescription
    */
   public String getDescription() {
      if (_fullDescription == null) {
         if (_description == null || isExtensionListInDescription()) {
            _fullDescription = _description == null ? "(" : _description + " (";
            // build the description from the extension list
            Enumeration<String> extensions = _filters.keys();
            if (extensions != null) {
               _fullDescription += "." + (String) extensions.nextElement();
               while (extensions.hasMoreElements()) {
                  _fullDescription += ", " + (String) extensions.nextElement();
               }
            }
            _fullDescription += ")";
         } else {
            _fullDescription = _description;
         }
      }
      return _fullDescription;
   }

   /**
    * Sets the human readable description of this filter. For
    * example: filter.setDescription("Gif and JPG Images");
    *
    * @param description The description of the file filter.
    *
    * @see setDescription
    * @see setExtensionListInDescription
    * @see isExtensionListInDescription
    */
   public void setDescription(String description) {
      _description = description;
      _fullDescription = null;
   }

   /**
    * Determines whether the extension list (.jpg, .gif, etc) should
    * show up in the human readable description.
    *
    * Only relevent if a description was provided in the constructor
    * or using setDescription();
    *
    * @param b whether the extension list (.jpg, .gif, etc) should
    * show up in the human readable description.
    *
    * @see getDescription
    * @see setDescription
    * @see isExtensionListInDescription
    */
   public void setExtensionListInDescription(boolean b) {
      _useExtensionsInDescription = b;
      _fullDescription = null;
   }

   /**
    * Returns whether the extension list (.jpg, .gif, etc) should
    * show up in the human readable description.
    *
    * Only relevent if a description was provided in the constructor
    * or using setDescription();
    *
    * @return true of the the extension list (.jpg, .gif, etc) should
    * show up in the human readable description.
    *
    * @see getDescription
    * @see setDescription
    * @see setExtensionListInDescription
    */
   public boolean isExtensionListInDescription() {
      return _useExtensionsInDescription;
   }
}