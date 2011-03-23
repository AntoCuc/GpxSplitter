package gpxsplitter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anc6
 */
public class ControllerTest {

    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of howManyFiles method, of class Controller.
     */
    @Test
    public void testHowManyFilesNoInstructionsInvalid() {
        int currentNumOfInstructions = 0;
        int outputFileNumOfInstructions = 2;
        int expResult = 0;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesInvalidOutputNumber() {
        int currentNumOfInstructions = 2;
        int outputFileNumOfInstructions = 0;
        int expResult = 0;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesEvenInstructions() {
        int currentNumOfInstructions = 2;
        int outputFileNumOfInstructions = 2;
        int expResult = 1;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesOddInstructions() {
        int currentNumOfInstructions = 3;
        int outputFileNumOfInstructions = 2;
        int expResult = 2;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesOddOutputInstructions() {
        int currentNumOfInstructions = 2;
        int outputFileNumOfInstructions = 1;
        int expResult = 2;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }

    @Test
    public void testHowManyFilesEvenOutputInstructions() {
        int currentNumOfInstructions = 3;
        int outputFileNumOfInstructions = 2;
        int expResult = 2;
        int result = Controller.howManyFiles(currentNumOfInstructions, outputFileNumOfInstructions);
        assertEquals(expResult, result);
    }
}
