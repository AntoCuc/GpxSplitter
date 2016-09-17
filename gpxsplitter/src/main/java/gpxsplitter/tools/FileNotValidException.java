/**
 * Exception thrown when the file loaded is not a Valid Gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools;

public class FileNotValidException extends Exception {
    
    public static final String ERROR_MSG = "The file you are trying to load is not supported.";

    public FileNotValidException()
    {
        super(ERROR_MSG);
    }

}
