/*
 * Sample.java
 */
package simulator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Richard Roberts
 */
public class Sample {
    boolean isFixedOutcomes; // True if fixed, false if variable
    boolean isWithReplacement; // True if with replacement is set, false otherwise
    String stopConditionType; // 'someOrder, allSet, someSet'
    String successCondType;  // someOrder, allSet, someSet
    int numObservations;
    String variableType;
    String distType;
    ArrayList<Outcome> stopCondOutcomes; // based on the sample
    HashMap<String, Integer> stopOutcomeSet;
    
    ArrayList<Outcome> sample1;
    public Sample(){
            
    }
    public Sample(ArrayList<Outcome> sample1){
        
    }
    
    public boolean isFixedOutcomes() {
        return isFixedOutcomes;
    }

    public boolean isWithReplacement() {
        return isWithReplacement;
    }
    
    public void setIsWithReplacement(boolean replace) {
        isWithReplacement = replace;
    }


    public String getStopConditionType() {
        return stopConditionType;
    }

    public String getVariableType() {
        return variableType;
    }

    public String getSuccessCondType() {
        return successCondType;
    }

    public int getNumObservations() {
        return numObservations;
    }

    public void setNumObservations(int obs) {
        this.numObservations = obs;
    }

}
