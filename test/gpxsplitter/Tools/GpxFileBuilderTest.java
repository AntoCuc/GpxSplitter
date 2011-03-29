/**
 * Tests the file builder.
 *
 * @author Antonino Cucchiara
 */

package gpxsplitter.Tools;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxFileBuilderTest {

    private GpxFileBuilder gpxFileBuilder;

    @Before
    public void setUp() {
        this.gpxFileBuilder = new GpxFileBuilder(null) {

            @Override
            public void build(File fileName, int totalInstructionsNumber, int filesNum) throws IOException
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Test
    public void testStripExtensionWithExtension() throws Exception
    {
        String expected = "testFileName";
        String fileNameWithExtension = expected + GpxFileBuilder.GPX_FORMAT;
        String actual = this.gpxFileBuilder.stripExtension(fileNameWithExtension, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }

    @Test
    public void testStripExtensionWithNoExtension() throws Exception
    {
        String expected = "testFileName";
        String actual = this.gpxFileBuilder.stripExtension(expected, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }

    @Test
    public void testStripExtensionWithDifferentExtension() throws Exception
    {
        String expected = "testFileName.xml";
        String actual = this.gpxFileBuilder.stripExtension(expected, GpxFileBuilder.GPX_FORMAT);
        assertEquals(expected, actual);
    }

}