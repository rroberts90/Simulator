package simulator;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
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
public class TrialRunnerTest {
    
    public TrialRunnerTest() {
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
     * Test of getDistVal1 method, of class TrialRunner.
     */
    @Test
    public void testGetDistVal1() {
        System.out.println("getDistVal1");
        TrialRunner instance = new TrialRunner();
        double expResult = 0.0;
        double result = instance.getDistVal1();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistVal2 method, of class TrialRunner.
     */
    @Test
    public void testGetDistVal2() {
        System.out.println("getDistVal2");
        TrialRunner instance = new TrialRunner();
        double expResult = 0.0;
        double result = instance.getDistVal2();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDistVal1 method, of class TrialRunner.
     */
    @Test
    public void testSetDistVal1() {
        System.out.println("setDistVal1");
        double val1 = 0.0;
        TrialRunner instance = new TrialRunner();
        instance.setDistVal1(val1);

    }

    /**
     * Test of setDistVal2 method, of class TrialRunner.
     */
    @Test
    public void testSetDistVal2() {
        System.out.println("setDistVal2");
        double val2 = 0.0;
        TrialRunner instance = new TrialRunner();
        instance.setDistVal2(val2);

    }

    /**
     * Test of getDistType method, of class TrialRunner.
     */
    @Test
    public void testGetDistType() {
        System.out.println("getDistType");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getDistType();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDistType method, of class TrialRunner.
     */
    @Test
    public void testSetDistType() {
        System.out.println("setDistType");
        String type = "";
        TrialRunner instance = new TrialRunner();
        instance.setDistType(type);
 
    }

    /**
     * Test of getNumTrials method, of class TrialRunner.
     */
    @Test
    public void testGetNumTrials() {
        System.out.println("getNumTrials");
        TrialRunner instance = new TrialRunner();
        int expResult = 0;
        int result = instance.getNumTrials();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPopulation method, of class TrialRunner.
     */
    @Test
    public void testGetPopulation() {
        System.out.println("getPopulation");
        TrialRunner instance = new TrialRunner();
        Population expResult = null;
        Population result = instance.getPopulation();
        assertEquals(expResult, result);

    }

    /**
     * Test of isFixedOutcomes method, of class TrialRunner.
     */
    @Test
    public void testIsFixedOutcomes() {
        System.out.println("isFixedOutcomes");
        TrialRunner instance = new TrialRunner();
        boolean expResult = false;
        boolean result = instance.isFixedOutcomes();
        assertEquals(expResult, result);

    }

    /**
     * Test of isWithReplacement method, of class TrialRunner.
     */
    @Test
    public void testIsWithReplacement() {
        System.out.println("isWithReplacement");
        TrialRunner instance = new TrialRunner();
        boolean expResult = false;
        boolean result = instance.isWithReplacement();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of getStopConditionType method, of class TrialRunner.
     */
    @Test
    public void testGetStopConditionType() {
        System.out.println("getStopConditionType");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getStopConditionType();
        assertEquals(expResult, result);
   
    }

    /**
     * Test of getVariableType method, of class TrialRunner.
     */
    @Test
    public void testGetVariableType() {
        System.out.println("getVariableType");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getVariableType();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getSuccessCondType method, of class TrialRunner.
     */
    @Test
    public void testGetSuccessCondType() {
        System.out.println("getSuccessCondType");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getSuccessCondType();
        assertEquals(expResult, result);

    }

    /**
     * Test of getNumObservations method, of class TrialRunner.
     */
    @Test
    public void testGetNumObservations() {
        System.out.println("getNumObservations");
        TrialRunner instance = new TrialRunner();
        int expResult = 0;
        int result = instance.getNumObservations();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNumObservations method, of class TrialRunner.
     */
    @Test
    public void testSetNumObservations() {
        System.out.println("setNumObservations");
        int obs = 0;
        TrialRunner instance = new TrialRunner();
        instance.setNumObservations(obs);

    }

    /**
     * Test of setVariableType method, of class TrialRunner.
     */
    @Test
    public void testSetVariableType() {
        System.out.println("setVariableType");
        String type = "";
        TrialRunner instance = new TrialRunner();
        instance.setVariableType(type);

    }

    /**
     * Test of setStopConditionType method, of class TrialRunner.
     */
    @Test
    public void testSetStopConditionType() {
        System.out.println("setStopConditionType");
        String type = "";
        TrialRunner instance = new TrialRunner();
        instance.setStopConditionType(type);

    }

    /**
     * Test of setNumTrials method, of class TrialRunner.
     */
    @Test
    public void testSetNumTrials() {
        System.out.println("setNumTrials");
        int trials = 0;
        TrialRunner instance = new TrialRunner();
        instance.setNumTrials(trials);
 
    }

    /**
     * Test of setIsWithReplacement method, of class TrialRunner.
     */
    @Test
    public void testSetIsWithReplacement() {
        System.out.println("setIsWithReplacement");
        boolean replace = false;
        TrialRunner instance = new TrialRunner();
        instance.setIsWithReplacement(replace);

    }

    /**
     * Test of setIsFixedOutcomes method, of class TrialRunner.
     */
    @Test
    public void testSetIsFixedOutcomes() {
        System.out.println("setIsFixedOutcomes");
        boolean fixed = false;
        TrialRunner instance = new TrialRunner();
        instance.setIsFixedOutcomes(fixed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPopulation method, of class TrialRunner.
     */
    @Test
    public void testSetPopulation() {
        System.out.println("setPopulation");
        Population pop = null;
        TrialRunner instance = new TrialRunner();
        instance.setPopulation(pop);

    }

    /**
     * Test of setStopCondOutcomes method, of class TrialRunner.
     */
    @Test
    public void testSetStopCondOutcomes() {
        System.out.println("setStopCondOutcomes");
        ArrayList<Outcome> outList = null;
        TrialRunner instance = new TrialRunner();
        instance.setStopCondOutcomes(outList);

    }

    /**
     * Test of setStopCondSet method, of class TrialRunner.
     */
    @Test
    public void testSetStopCondSet() {
        System.out.println("setStopCondSet");
        HashMap<String, Integer> outSet = null;
        TrialRunner instance = new TrialRunner();
        instance.setStopCondSet(outSet);

    }

    /**
     * Test of setSuccessSet method, of class TrialRunner.
     */
    @Test
    public void testSetSuccessSet() {
        System.out.println("setSuccessSet");
        HashMap<String, Integer> outSet = null;
        TrialRunner instance = new TrialRunner();
        instance.setSuccessSet(outSet);
   
    }

    /**
     * Test of setVariableOutcomes method, of class TrialRunner.
     */
    @Test
    public void testSetVariableOutcomes() {
        System.out.println("setVariableOutcomes");
        ArrayList<Outcome> outList = null;
        TrialRunner instance = new TrialRunner();
        instance.setVariableOutcomes(outList);

    }

    /**
     * Test of setSuccessCondType method, of class TrialRunner.
     */
    @Test
    public void testSetSuccessCondType() {
        System.out.println("setSuccessCondType");
        String type = "";
        TrialRunner instance = new TrialRunner();
        instance.setSuccessCondType(type);

    }

    /**
     * Test of setSuccessOutcomes method, of class TrialRunner.
     */
    @Test
    public void testSetSuccessOutcomes() {
        System.out.println("setSuccessOutcomes");
        ArrayList<Outcome> outList = null;
        TrialRunner instance = new TrialRunner();
        instance.setSuccessOutcomes(outList);

    }

    /**
     * Test of runTrials method, of class TrialRunner.
     */
    @Test
    public void testRunTrials() {
        System.out.println("runTrials");
        TrialRunner instance = new TrialRunner();
        instance.runTrials();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printAllData method, of class TrialRunner.
     */
    @Test
    public void testPrintAllData() {
        System.out.println("printAllData");
        TrialRunner instance = new TrialRunner();
        instance.printAllData();
   
    }

    /**
     * Test of setupTrials method, of class TrialRunner.
     */
    @Test
    public void testSetupTrials() {
        System.out.println("setupTrials");
        TrialRunner instance = new TrialRunner();
        instance.setupTrials();

    }

    /**
     * Test of runFixedOutcomeTrials method, of class TrialRunner.
     */
    @Test
    public void testRunFixedOutcomeTrials() {
        System.out.println("runFixedOutcomeTrials");
        TrialRunner instance = new TrialRunner();
        instance.runFixedOutcomeTrials();
   
    }

    /**
     * Test of runVariableOutcomeTrials method, of class TrialRunner.
     */
    @Test
    public void testRunVariableOutcomeTrials() {
        System.out.println("runVariableOutcomeTrials");
        TrialRunner instance = new TrialRunner();
        instance.runVariableOutcomeTrials();

    }

    /**
     * Test of runUniformDistTrials method, of class TrialRunner.
     */
    @Test
    public void testRunUniformDistTrials() {
        System.out.println("runUniformDistTrials");
        TrialRunner instance = new TrialRunner();
        instance.runUniformDistTrials();
  
    }

    /**
     * Test of runNormDistTrials method, of class TrialRunner.
     */
    @Test
    public void testRunNormDistTrials() {
        System.out.println("runNormDistTrials");
        TrialRunner instance = new TrialRunner();
        instance.runNormDistTrials();

    }

    /**
     * Test of runBinomialDistTrials method, of class TrialRunner.
     */
    @Test
    public void testRunBinomialDistTrials() {
        System.out.println("runBinomialDistTrials");
        TrialRunner instance = new TrialRunner();
        instance.runBinomialDistTrials();
  
    }

    /**
     * Test of runExpDistTrials method, of class TrialRunner.
     */
    @Test
    public void testRunExpDistTrials() {
        System.out.println("runExpDistTrials");
        TrialRunner instance = new TrialRunner();
        instance.runExpDistTrials();

    }

    /**
     * Test of getStats method, of class TrialRunner.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        double[] rands = null;
        TrialRunner instance = new TrialRunner();
        DescriptiveStatistics expResult = null;
        DescriptiveStatistics result = instance.getStats(rands);
        assertEquals(expResult, result);

    }

    /**
     * Test of measureSuccess method, of class TrialRunner.
     */
    @Test
    public void testMeasureSuccess_DescriptiveStatistics() {
        System.out.println("measureSuccess");
        DescriptiveStatistics stats = null;
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.measureSuccess(stats);
        assertEquals(expResult, result);
  
    }

    /**
     * Test of measureSuccess method, of class TrialRunner.
     */
    @Test
    public void testMeasureSuccess_ArrayList() {
        System.out.println("measureSuccess");
        ArrayList<Outcome> sample = null;
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.measureSuccess(sample);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkForStop method, of class TrialRunner.
     */
    @Test
    public void testCheckForStop() {
        System.out.println("checkForStop");
        ArrayList<Outcome> sample = null;
        TrialRunner instance = new TrialRunner();
        boolean expResult = false;
        boolean result = instance.checkForStop(sample);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDescriptiveStats method, of class TrialRunner.
     */
    @Test
    public void testGetDescriptiveStats() {
        System.out.println("getDescriptiveStats");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getDescriptiveStats();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of getDistribution method, of class TrialRunner.
     */
    @Test
    public void testGetDistribution() {
        System.out.println("getDistribution");
        TrialRunner instance = new TrialRunner();
        String expResult = "";
        String result = instance.getDistribution();
        assertEquals(expResult, result);

    }

    /**
     * Test of displayRandNumbers method, of class TrialRunner.
     */
    @Test
    public void testDisplayRandNumbers() {
        System.out.println("displayRandNumbers");
        TrialRunner instance = new TrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayRandNumbers();
        assertEquals(expResult, result);

    }

    /**
     * Test of displayOutcomes method, of class TrialRunner.
     */
    @Test
    public void testDisplayOutcomes() {
        System.out.println("displayOutcomes");
        TrialRunner instance = new TrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayOutcomes();
        assertEquals(expResult, result);

    }

    /**
     * Test of displayResponseVariables method, of class TrialRunner.
     */
    @Test
    public void testDisplayResponseVariables() {
        System.out.println("displayResponseVariables");
        TrialRunner instance = new TrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayResponseVariables();
        assertEquals(expResult, result);

    }
}
