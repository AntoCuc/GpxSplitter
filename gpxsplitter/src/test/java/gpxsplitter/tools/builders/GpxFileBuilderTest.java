/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpxsplitter.tools.builders;

import gpxsplitter.UI;
import gpxsplitter.model.GpxType;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GpxFileBuilderTest {

    @Test
    public void testCreateGpxTemplate() {
        GpxFileBuilder instance = new GpxFileBuilderImpl();
        GpxType result = instance.createGpxTemplate();
        assertEquals(GpxFileBuilder.GPX_VERSION, result.getVersion());
        assertEquals(UI.GPX_SPLITTER, result.getCreator());
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

    public class GpxFileBuilderImpl extends GpxFileBuilder {

        public List<GpxType> buildSplitGpx(GpxType gpx, int preferredInstrNum) {
            return null;
        }
    }

}
