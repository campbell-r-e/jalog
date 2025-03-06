package openlogbook.jlog.logbook;

/**
 * An interface that will output to an ADIF stream.
 * 
 * @author KC0ZPS
 */
public interface Adif {

   /**
    * Returns an ADIF formatted String.
    * 
    * @return an ADIF formatted String.
    */
   public String toAdifString() ;


}
