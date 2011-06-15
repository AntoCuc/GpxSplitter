/**
 * Exception thrown when the file loaded is not a Valid Gpx.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.tools;

public class FileNotValidException extends Exception{

    public FileNotValidException(String message)
    {
        super(message);
    }

}
