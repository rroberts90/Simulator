
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
public class TwoPopTrialRunnerTest {
    
    public TwoPopTrialRunnerTest() {
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
     * Test of setCase method, of class TwoPopTrialRunner.
     */
    @Test
    public void testSetCase() {
        System.out.println("setCase");
        int varCase = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.setCase(varCase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiffGroup method, of class TwoPopTrialRunner.
     */
    @Test
    public void testSetDiffGroup() {
        System.out.println("setDiffGroup");
        int diffGroup = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.setDiffGroup(diffGroup);

    }

    /**
     * Test of runTrials method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunTrials() {
        System.out.println("runTrials");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runTrials();
        //fail("The test case is a prototype.");
    }

    /**
     * Test of runNormDistTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunNormDistTrial() throws Exception {
        System.out.println("runNormDistTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runNormDistTrial(id);
    
    }

    /**
     * Test of runBinomialDistTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunBinomialDistTrial() throws Exception {
        System.out.println("runBinomialDistTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runBinomialDistTrial(id);
       
    }

    /**
     * Test of runTwoPopProportionCategoryTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunTwoPopProportionCategoryTrial() {
        System.out.println("runTwoPopProportionCategoryTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runTwoPopProportionCategoryTrial(id);
     
    }

    /**
     * Test of runVariableTwoPopProportionCategoryTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunVariableTwoPopProportionCategoryTrial() {
        System.out.println("runVariableTwoPopProportionCategoryTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runVariableTwoPopProportionCategoryTrial(id);
     
    }

    /**
     * Test of runTwoPopNumericalTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunTwoPopNumericalTrial() {
        System.out.println("runTwoPopNumericalTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runTwoPopNumericalTrial(id);
 
    }

    /**
     * Test of runVariableTwoPopNumericalTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunVariableTwoPopNumericalTrial() {
        System.out.println("runVariableTwoPopNumericalTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runVariableTwoPopNumericalTrial(id);

    }

    /**
     * Test of runMatchDrawTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunMatchDrawTrial() {
        System.out.println("runMatchDrawTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runMatchDrawTrial(id);
    }

    /**
     * Test of runVariableMatchDrawTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunVariableMatchDrawTrial() {
        System.out.println("runVariableMatchDrawTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runVariableMatchDrawTrial(id);
    }

    /**
     * Test of runMeanDrawTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunMeanDrawTrial() {
        System.out.println("runMeanDrawTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runMeanDrawTrial(id);
    }

    /**
     * Test of runVariableMeanDrawTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunVariableMeanDrawTrial() {
        System.out.println("runVariableMeanDrawTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runVariableMeanDrawTrial(id);
    }

    /**
     * Test of runCompareTwoCategoriesTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunCompareTwoCategoriesTrial() {
        System.out.println("runCompareTwoCategoriesTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runCompareTwoCategoriesTrial(id);
    }

    /**
     * Test of runCompareTwoNumericTrial method, of class TwoPopTrialRunner.
     */
    @Test
    public void testRunCompareTwoNumericTrial() {
        System.out.println("runCompareTwoNumericTrial");
        int id = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        instance.runCompareTwoNumericTrial(id);
    }

    /**
     * Test of drawOneFromPop method, of class TwoPopTrialRunner.
     */
    @Test
    public void testDrawOneFromPop() {
        System.out.println("drawOneFromPop");
        int popNum = 0;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        Outcome expResult = null;
        Outcome result = instance.drawOneFromPop(popNum);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescriptiveStats method, of class TwoPopTrialRunner.
     */
    @Test
    public void testGetDescriptiveStats() {
        System.out.println("getDescriptiveStats");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        String expResult = "";
        String result = instance.getDescriptiveStats();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistribution method, of class TwoPopTrialRunner.
     */
    @Test
    public void testGetDistribution() {
        System.out.println("getDistribution");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        String expResult = "";
        String result = instance.getDistribution();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayRandNumbers method, of class TwoPopTrialRunner.
     */
    @Test
    public void testDisplayRandNumbers() {
        System.out.println("displayRandNumbers");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayRandNumbers();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayOutcomes method, of class TwoPopTrialRunner.
     */
    @Test
    public void testDisplayOutcomes() {
        System.out.println("displayOutcomes");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayOutcomes();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayResponseVariables method, of class TwoPopTrialRunner.
     */
    @Test
    public void testDisplayResponseVariables() {
        System.out.println("displayResponseVariables");
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        StringBuffer expResult = null;
        StringBuffer result = instance.displayResponseVariables();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkForStop method, of class TwoPopTrialRunner.
     */
    @Test
    public void testCheckForStop() {
        System.out.println("checkForStop");
        ArrayList<Outcome> sample1 = null;
        ArrayList<Outcome> sample2 = null;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        boolean expResult = false;
        boolean result = instance.checkForStop(sample1, sample2);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkSampleForStop method, of class TwoPopTrialRunner.
     */
    @Test
    public void testCheckSampleForStop() {
        System.out.println("checkSampleForStop");
        ArrayList<Outcome> sample1 = null;
        Sample s = null;
        TwoPopTrialRunner instance = new TwoPopTrialRunner();
        boolean expResult = false;
        boolean result = instance.checkSampleForStop(sample1, s);
        assertEquals(expResult, result);
    }
}
