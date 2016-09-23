/*
 * The MIT License
 *
 * Copyright 2016 Antonino Cucchiara.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gpxsplitter.model.builder;

import gpxsplitter.view.View;
import gpxsplitter.model.generated.GpxType;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxFileBuilderTest {

    @Test
    public void testCreateGpxTemplate() {
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        GpxType result = instance.createGpxTemplate();
        assertEquals(GpxFileBuilder.GPX_VERSION, result.getVersion());
        assertEquals(View.GPX_SPLITTER, result.getCreator());
    }

    @Test
    public void testStripGpxExtension() {
        String fileName = "abc.gpx";
        String extension = ".gpx";
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        String expResult = "abc";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }

    @Test
    public void testStripExtensionNoExtension() {
        String fileName = "abc";
        String extension = ".gpx";
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        String expResult = "abc";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }

    @Test
    public void testStripExtensionDifferentExtension() {
        String fileName = "abc.txt";
        String extension = ".gpx";
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        String expResult = "abc.txt";
        String result = instance.stripExtension(fileName, extension);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesOneFileNoInstructions() {
        int currNumOfInstr = 0;
        int desiredNumOfInstr = 1;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 0;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesOneFileOneInstruction() {
        int currNumOfInstr = 1;
        int desiredNumOfInstr = 1;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 1;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesOneFileTwoInstructions() {
        int currNumOfInstr = 2;
        int desiredNumOfInstr = 2;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 1;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesTwoFilesOneInstruction() {
        int currNumOfInstr = 2;
        int desiredNumOfInstr = 1;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 2;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesTwoFilesTwoInstruction() {
        int currNumOfInstr = 4;
        int desiredNumOfInstr = 2;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 2;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesTwoFilesTwoInstructionOdd() {
        int currNumOfInstr = 3;
        int desiredNumOfInstr = 2;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 2;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesException() {
        int currNumOfInstr = 0;
        int desiredNumOfInstr = 2;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 0;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesException1() {
        int currNumOfInstr = 2;
        int desiredNumOfInstr = 0;
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        int expResult = 0;
        int result = instance.howManyFiles(currNumOfInstr, desiredNumOfInstr);
        assertEquals(expResult, result);
    }

    @Test
    public void testBuild() throws Exception {
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        instance.build("", new GpxType(), 1);
    }

    public class GpxFileBuilderImpl extends GpxFileBuilder {

        @Override
        public List<GpxType> buildSplitGpx(GpxType gpx, int preferredInstrNum) {
            return Arrays.asList(new GpxType());
        }

        @Override
        void saveFile(final String fileName, final GpxType newGpxDocument)
                throws JAXBException {

        }
    }
}
