package gpxsplitter;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class GpxFileFilterTest {

    private final GpxFileFilter fileFilter;

    public GpxFileFilterTest() {
        this.fileFilter = new GpxFileFilter();
    }

    @Test
    public void testAcceptPositive() {
        assertTrue(fileFilter.accept(new File("dummy.gpx")));
    }

    @Test
    public void testAcceptNegative() {
        assertFalse(fileFilter.accept(new File("dummy.txt")));
    }

    @Test
    public void testGetDescription() {
        assertEquals(GpxFileFilter.GPX_FILE_DESCRIPTION, fileFilter.getDescription());
    }

}
