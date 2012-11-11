package simulator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
public class ChartLoaderTest {
    
    public ChartLoaderTest() {
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
     * Test of getBinCount method, of class ChartLoader.
     */
    @Test
    public void testGetBinCount() {
        System.out.println("getBinCount");
        ChartLoader instance = null;
        int expResult = 0;
        int result = instance.getBinCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinCount method, of class ChartLoader.
     */
    @Test
    public void testSetBinCount() {
        System.out.println("setBinCount");
        int bins = 0;
        ChartLoader instance = null;
        instance.setBinCount(bins);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinCutoffs method, of class ChartLoader.
     */
    @Test
    public void testSetBinCutoffs() {
        System.out.println("setBinCutoffs");
        ArrayList<Double> cutoffs = null;
        ChartLoader instance = null;
        instance.setBinCutoffs(cutoffs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadChart method, of class ChartLoader.
     */
    @Test
    public void testLoadChart_Population() {
        System.out.println("loadChart");
        Population p = null;
        ChartLoader instance = null;
        ChartPanel expResult = null;
        ChartPanel result = instance.loadChart(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadChart method, of class ChartLoader.
     */
    @Test
    public void testLoadChart_Population_Population() {
        System.out.println("loadChart");
        Population p1 = null;
        Population p2 = null;
        ChartLoader instance = null;
        ChartPanel expResult = null;
        ChartPanel result = instance.loadChart(p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadChart method, of class ChartLoader.
     */
    @Test
    public void testLoadChart_ArrayList() {
        System.out.println("loadChart");
        ArrayList<Trial> trials = null;
        ChartLoader instance = null;
        ChartPanel expResult = null;
        ChartPanel result = instance.loadChart(trials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveChart method, of class ChartLoader.
     */
    @Test
    public void testSaveChart() {
        System.out.println("saveChart");
        ChartPanel cp = null;
        File file = null;
        ChartLoader instance = null;
        int expResult = 0;
        int result = instance.saveChart(cp, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadChart method, of class ChartLoader.
     */
    @Test
    public void testLoadChart_ArrayList_boolean() {
        System.out.println("loadChart");
        ArrayList<TwoPopTrial> trials = null;
        boolean diff = false;
        ChartLoader instance = null;
        ChartPanel expResult = null;
        ChartPanel result = instance.loadChart(trials, diff);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of styleBarChart method, of class ChartLoader.
     */
    @Test
    public void testStyleBarChart() {
        System.out.println("styleBarChart");
        JFreeChart chart = null;
        int popNum = 0;
        ChartLoader instance = null;
        instance.styleBarChart(chart, popNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createHistogram method, of class ChartLoader.
     */
    @Test
    public void testCreateHistogram() {
        System.out.println("createHistogram");
        HashSet<String> check = null;
        DescriptiveStatistics stats = null;
        double[] values = null;
        ChartLoader instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.createHistogram(check, stats, values);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
