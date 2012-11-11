package simulator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class FunctionTester {
    public static void main(String[] args){
        Population p1 = new Population(createTestList());

        TrialRunner testRun = new TrialRunner();
        testSettings6(testRun, p1);
        testRun.runFixedOutcomeTrials();
        displayAll(testRun);

   
        
    }
    public static void displayAll(TrialRunner t){
        System.out.println(t.getDescriptiveStats());
        System.out.println(t.displayOutcomes());
        System.out.println(t.displayRandNumbers());
        System.out.println(t.displayResponseVariables());
    }
    
    public static  void testSettings2(TrialRunner t, Population p1){
        t.setPopulation(p1);
        t.setNumTrials(100);
        t.setNumObservations(10);
        t.setIsFixedOutcomes(true);
        t.setVariableType("containsSuccess");
        t.setSuccessCondType("someOrder");
        ArrayList<Outcome> sucTestList = new ArrayList<Outcome>();
        sucTestList.add(new Outcome("one", 0,0,0,0));
        sucTestList.add(new Outcome("three", 0,0,0,0));
        sucTestList.add(new Outcome("eight", 0,0,0,0));
        t.successCondOutcomes = sucTestList;

    }
    
        public static  void testSettings6(TrialRunner t, Population p1){
        t.setPopulation(p1);
        t.setNumTrials(5);
        t.setNumObservations(10);
        t.setIsFixedOutcomes(true);
        t.setVariableType("containsSuccess");
        t.setSuccessCondType("allSet");
        HashMap<String, Integer> tstMp = new HashMap<String, Integer>();
        tstMp.put("one", 1);
        tstMp.put("three", 1);
        tstMp.put("eight", 3);
        t.successOutcomeSet = tstMp;
    }
          
          
    public static void testSettings1(TrialRunner t){
        t.setNumTrials(15);
        t.setNumObservations(5);
        t.setDistType("norm");
        t.setVariableType("mean");
        t.setDistVal1(50);
        t.setDistVal2(10);
        
    }
        public static void testSettings4(TrialRunner t){
        t.setNumTrials(15);
        t.setNumObservations(5);

        t.setDistType("exp");
        t.setVariableType("mean");
        t.setDistVal1(50);
        t.setDistVal2(10);
        
    }
     public static void testSettings5(TrialRunner t){
        t.setNumTrials(15);
        t.setNumObservations(5);

        t.setDistType("binomial");
        t.setVariableType("mean");
        t.setDistVal1(50);
        t.setDistVal2(10);
        
    }
 
    
    
    public static ArrayList<Outcome> createTestList(){
        
        ArrayList<Outcome> tester = new ArrayList<Outcome>();
        tester.add(new Outcome("one", 5, 0, 0, 0)) ;   
         tester.add(new Outcome("two", 5, 0, 0, 0)) ;   
        tester.add(new Outcome("three", 3, 0, 0, 0)) ;   
        tester.add(new Outcome("four", 5, 0, 0, 0)) ;   
        tester.add(new Outcome("five", 5, 0, 0, 0)) ;   
        tester.add(new Outcome("six", 5, 0, 0, 0)) ;   
        tester.add(new Outcome("seven", 5, 0, 0, 0)) ;   
        tester.add(new Outcome("eight", 15, 0, 0, 0)) ;   
        tester.add(new Outcome("nine", 2, 0, 0, 0)) ;   

        return tester;
    }
}
