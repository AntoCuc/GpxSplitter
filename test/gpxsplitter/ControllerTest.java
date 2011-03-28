/**
 * Controller, handling user interaction and abstracting away the model.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter;

import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTest
{

    @Test
    public void testHowManyFiles()
    {
        int expected = 2;
        int actual = Controller.howManyFiles(10, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesNoInstructions()
    {
        int expected = 0;
        int actual = Controller.howManyFiles(0, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesNoDesiredInstructions()
    {
        int expected = 0;
        int actual = Controller.howManyFiles(19, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesOddFiles()
    {
        int expected = 4;
        int actual = Controller.howManyFiles(10, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void testHowManyFilesOddInstructions()
    {
        int expected = 2;
        int actual = Controller.howManyFiles(19, 10);
        assertEquals(expected, actual);
    }
}
