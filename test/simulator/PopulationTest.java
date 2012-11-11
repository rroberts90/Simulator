package simulator;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richard
 */
public class PopulationTest {
    
    public PopulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sample method, of class Population.
     */
    @Test
    public void testSample_int_boolean() {
        System.out.println("sample");
        int sampleSize = 0;
        boolean replace = false;
        Population instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.sample(sampleSize, replace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sample method, of class Population.
     */
    @Test
    public void testSample_boolean() {
        System.out.println("sample");
        boolean replace = false;
        Population instance = null;
        Outcome expResult = null;
        Outcome result = instance.sample(replace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endSample method, of class Population.
     */
    @Test
    public void testEndSample() {
        System.out.println("endSample");
        Population instance = null;
        instance.endSample();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
