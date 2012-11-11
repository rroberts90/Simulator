package simulator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math.*;
import org.apache.commons.math.distribution.AbstractContinuousDistribution;
import org.apache.commons.math.distribution.BinomialDistributionImpl;
import org.apache.commons.math.distribution.ContinuousDistribution;
import org.apache.commons.math.distribution.ExponentialDistributionImpl;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.commons.math.random.UniformRandomGenerator;
import org.apache.commons.math.stat.Frequency;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Richard
 */
public class TrialRunner {
    final DecimalFormat twoDForm = new DecimalFormat("#.####"); // rounds floats to 2 decimal points

    Random rand = new Random();
    int numTrials;
    Population p;
    boolean isFixedOutcomes; // True if fixed, false if variable
    boolean isWithReplacement; // True if with replacement is set, false otherwise
    String stopConditionType; // 'someOrder, allSet, someSet'
    String successCondType;  // someOrder, allSet, someSet
    int numObservations;
    // 'length, median, mean, meanSTD,sum ,containsSuccess, percentOfSuccess'
    // numSuccess
    String variableType;
    String distType;
    ArrayList<Outcome> stopCondOutcomes; // based on the sample
    HashMap<String, Integer> stopOutcomeSet;
    HashMap<String, Integer> successOutcomeSet;
    ArrayList<Outcome> successCondOutcomes; // based on the variable
    // AbstractContinuousDistribution dist;
    double distVal1, distVal2;
    ArrayList<Trial> trials = new ArrayList<Trial>();
    public String descriptiveStats;
       int binCount;
    ArrayList<Double> binOffs;
    
// Constructors 
    TrialRunner() {
        trials = new ArrayList<Trial>();

    }

    TrialRunner(Population p) {
        this.p = p;
    }

    
    // gets and setters 
    public double getDistVal1() {
        return distVal1;
    }

    public double getDistVal2() {
        return distVal2;
    }

    public void setDistVal1(double val1) {
        distVal1 = val1;
    }

    public void setDistVal2(double val2) {
        distVal2 = val2;
    }

    public String getDistType() {
        return distType;
    }

    public void setDistType(String type) {
        this.distType = type;
    }

    public int getNumTrials() {
        return numTrials;
    }

    public Population getPopulation() {
        return p;
    }

    public boolean isFixedOutcomes() {
        return isFixedOutcomes;
    }

    public boolean isWithReplacement() {
        return isWithReplacement;
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

    public void setVariableType(String type) {
        this.variableType = type;

    }

    public void setStopConditionType(String type) {
        this.stopConditionType = type;
    }

    public void setNumTrials(int trials) {
        this.numTrials = trials;
    }

    public void setIsWithReplacement(boolean replace) {
        isWithReplacement = replace;
    }

    public void setIsFixedOutcomes(boolean fixed) {
        isFixedOutcomes = fixed;
    }

    public void setPopulation(Population pop) {
        this.p = pop;
    }

    public void setStopCondOutcomes(ArrayList<Outcome> outList) {
        this.stopCondOutcomes = outList;

    }

    public void setStopCondSet(HashMap<String, Integer> outSet) {
        this.stopOutcomeSet = outSet;
    }

    public void setSuccessSet(HashMap<String, Integer> outSet) {
        this.successOutcomeSet = outSet;
    }

    public void setVariableOutcomes(ArrayList<Outcome> outList) {
        this.successCondOutcomes = outList;

    }

    public void setSuccessCondType(String type) {
        this.successCondType = type;
    }

    public void setSuccessOutcomes(ArrayList<Outcome> outList) {
        this.successCondOutcomes = outList;
    }

    public TrialRunner(int numTrials) {
        this.numTrials = numTrials;
    }

    public void runTrials() {
        setupTrials();
    }

    public void printAllData() {
        System.out.println("TRIAL DATA");
/*        for (Outcome o : p.outcomes) {
            System.out.println("Outcome: " + o.outcomeName);
            System.out.println("Draw: " + o.count + "\n");

        }*/
        System.out.println("replace: " + isWithReplacement);
        System.out.println("fixed outcomes: " + isFixedOutcomes);

        System.out.println("num observations: " + numObservations);
        System.out.println("num trials " + numTrials);
        System.out.println("Variable Type: "+ variableType);
        System.out.println("Success Condition: " + successCondType);


    }

    /**
     * Handles preprocessing for the trial running methods. distType controlls which distribution to sample from, 
     * or whether to sample from a list of outcomes. 
     * @throws NumberFormatException 
     */
    public void setupTrials() throws NumberFormatException{
        trials = new ArrayList<Trial>();
        printAllData();
        if (distType != null) {
            if (distType.equals("norm")) {
                runNormDistTrials();

            } else if (distType.equals("exp")) {
                runExpDistTrials();
            } else if (distType.equals("uniform")) {
                runUniformDistTrials();
                // dist = new UniformNumberGenerator();
            } else if (distType.equals("binomial")) {
                runBinomialDistTrials();

            }
        } else { // sample from population
            if (this.isFixedOutcomes) {
                runFixedOutcomeTrials();
            } else {
                runVariableOutcomeTrials();
            }
        }
    }

    /**
     *  Runs trials with a fixed number of observations.  
     * @throws NumberFormatException 
     */
    public void runFixedOutcomeTrials() throws NumberFormatException{
       // System.out.println("running trials...");
       // long timeStart, timeEnd;  
        for (int i = 0; i < numTrials; i++) {
            //System.out.println("Trial Number: " + i);
            //timeStart = System.currentTimeMillis();
            ArrayList<Outcome> sample = p.sample(numObservations, isWithReplacement);
            String response = measureSuccess(sample);
            trials.add(new Trial(i + 1, sample, null, response, null));
           // timeEnd = System.currentTimeMillis();
           // System.out.println("Trial Time: " + (timeEnd - timeStart));
        }
    }

    /**
     * Runs trials where outcomes are not fixed. 
     * @throws NumberFormatException 
     */
    public void runVariableOutcomeTrials() throws NumberFormatException{
     //   System.out.println("running trials...");

        ArrayList<Outcome> sample; 
       // sample.add(p.sample(isWithReplacement));
        for (int i = 0; i < numTrials; i++) {
            sample = new ArrayList<Outcome>();
        //    System.out.println("Trial: " + i);
           do {
                Outcome o = p.sample(isWithReplacement);
                if (o != null){
                    sample.add(o);
            //        System.out.println("Added: " + sample.get(sample.size() -1));
                }
                else{
              //      System.out.println("exhusted sample. ");
                    p.endSample();
                    break;
                            
                }
            } while (!checkForStop(sample));

            //p.endSample();
            String response = measureSuccess(sample);
             System.out.println(response);
             if (!isWithReplacement){
                p.endSample();
             }
            trials.add(new Trial(i + 1, sample, null, response, null));

        }
    }

    /**
     * Runs trials where a random sample is taken from a uniform distribution.
     */
    public void runUniformDistTrials() {
        System.out.println("running trials...");

        RandomDataImpl uniform = new RandomDataImpl();
        DescriptiveStatistics stats = new DescriptiveStatistics();
        double lowerBound, upperBound;
        lowerBound = distVal1;
        upperBound = distVal2;
        double[] sample = new double[numObservations];
        for (int i = 0; i < numTrials; i++) {
            // double randNums = [];

            for (int j = 0; j < numObservations; j++) {
                sample[j] = uniform.nextUniform(lowerBound, upperBound);
                stats.addValue(sample[j]);
                //   double randNum  rand.nextDouble() * upperBound + lowerBound;
            }
            String response = measureSuccess(stats);
            trials.add(new Trial(i + 1, null, sample, response, stats));
        }
    }

    public void runNormDistTrials() {
        System.out.println("running trials...");

        Trial t;
        DescriptiveStatistics stats;
        NormalDistributionImpl dist = new NormalDistributionImpl(distVal1, distVal2);


        try {
            for (int i = 0; i < numTrials; i++) {

                double[] rands = dist.sample(numObservations);
                stats = getStats(rands);
                String response = this.measureSuccess(stats);
                System.out.println(response);
                trials.add(new Trial(i + 1, null, rands, response, stats));

            }
        } catch (MathException e) {
            System.out.println("Math Error");
        }
    }

    public void runBinomialDistTrials() {
        System.out.println("running trials...");

        Trial t;
        DescriptiveStatistics stats;
        BinomialDistributionImpl dist = new BinomialDistributionImpl((int) distVal1, distVal2);


        try {
            for (int i = 0; i < numTrials; i++) {

                int[] rands0 = dist.sample(numObservations);
                double[] rands = new double[rands0.length];
                for (int j = 0; j < rands0.length; j++) {
                    rands[j] = rands0[j];
                }
                stats = getStats(rands);
                String response = this.measureSuccess(stats);
                trials.add(new Trial(i + 1, null, rands, response, stats));

            }
        } catch (MathException e) {
            System.out.println("Math Error");

        }
    }

    public void runExpDistTrials() {
        System.out.println("running trials...");

        Trial t;
        DescriptiveStatistics stats;
        ExponentialDistributionImpl dist = new ExponentialDistributionImpl((int) distVal1, distVal2);


        try {
            for (int i = 0; i < numTrials; i++) {

                double[] rands = dist.sample(numObservations);
                stats = getStats(rands);
                String response = this.measureSuccess(stats);
                trials.add(new Trial(i + 1, null, rands, response, stats));

            }
        } catch (MathException e) {
            System.out.println("Math Error");

        }
    }

    /**
     * Only works for continuous models 
     * @param rands
     * @return 
     */
    public DescriptiveStatistics getStats(double[] rands) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int i = 0; i < rands.length; i++) {
            stats.addValue(rands[i]);
        }
        return stats;
    }
    // for distribution  methods

    public String measureSuccess(DescriptiveStatistics stats) {
        if (this.variableType.equals("mean")) {
            return stats.getMean() + "";
        } else if (this.variableType.equals("median")) {
            return stats.getPercentile(50) + "";
        } else if (this.variableType.equals("mean+std")) {
            return stats.getMean() + "      " + stats.getStandardDeviation();
        } else if (this.variableType.equals("sum")) {
            System.out.println("Stats: " + stats.getMax());
            return stats.getSum() + "";

        } else if (this.variableType.equals("length")) {
            return stats.getValues().length + "";
        }
        else if(this.variableType.equals("mode")){
            double mode = 0;
            double modeVal = 0;
            double currMode;
            double[] vals = stats.getSortedValues();
            for (int i = 0; i < vals.length; i++){
                currMode = 0;
                for(int j = i + 1; j < vals.length; j++){
                    if (vals[j] != vals[i]){
                        break;
                    }
                    else{
                        currMode += 1 ;
                    }
                }
                if (currMode > mode){
                    mode = currMode;
                    modeVal = vals[i];
                }
            }
            
            return modeVal + "";
            }
         else if(this.variableType.equals("modeSize")){
                double mode = 0;
            double modeVal = 0;
            double currMode;
            double[] vals = stats.getSortedValues();
            for (int i = 0; i < vals.length; i++){
                currMode = 0;
                for(int j = i + 1; j < vals.length; j++){
                    if (vals[j] != vals[i]){
                        break;
                    }
                    else{
                        currMode += 1 ;
                    }
                }
                if (currMode > mode){
                    mode = currMode;
                    modeVal = vals[i];
                }
            }
            
            return mode + "";
            }
       
         else {
            return "error"; // means the variable type wasn't recognized
        }

    }
    // for success methods

    public String measureSuccess(ArrayList<Outcome> sample) throws NumberFormatException {
       // System.out.println("Checking success");
        if (this.variableType.equals("containsSuccess")) {
            boolean match;
            if (this.successCondType.equals("someOrder")) {

                for (int i = 0; i < sample.size() - (this.successCondOutcomes.size() -1); i++) {
                    match = true;
                    for (int j = 0; j < this.successCondOutcomes.size() ; j++) {
                        if (!sample.get(i + j).outcomeName.equals(this.successCondOutcomes.get(j).outcomeName)) {    // potential match
                            match = false;
                            break;
                        }
                    }
                    if (match == true) {
                        return "True";
                    }


                }

                return "False";
            } else if (this.successCondType.equals("someSet")) {
                HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
                for (Outcome o : sample) {
                    if (successOutcomeSet.containsKey(o.outcomeName)) {
                        if (sampleSet.containsKey(o.outcomeName)) {
                            int val = sampleSet.get(o.outcomeName) + 1;
                            sampleSet.put(o.outcomeName, val);
                        } else {
                            sampleSet.put(o.outcomeName, 1);

                        }
                    }
                }
                for (Map.Entry<String, Integer> entry : successOutcomeSet.entrySet()) {
                    if (sampleSet.containsKey(entry.getKey()) && sampleSet.get(entry.getKey()) >= entry.getValue()) {
                        return "True";
                    }
                }


                return "False";
            } else if (this.successCondType.equals("allSet")) {
                HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
                for (Outcome o : sample) {
                    if (successOutcomeSet.containsKey(o.outcomeName)) {
                        if (sampleSet.containsKey(o.outcomeName)) {
                            int val = sampleSet.get(o.outcomeName) + 1;
                            sampleSet.put(o.outcomeName, val);
                        } else {
                            sampleSet.put(o.outcomeName, 1);

                        }
                    }
                }
                for (Map.Entry<String, Integer> entry : successOutcomeSet.entrySet()) {
                    if (!(sampleSet.containsKey(entry.getKey()) && sampleSet.get(entry.getKey()) >= entry.getValue())) // check this 
                    {
                        return "False";
                    }
                }


                return "True";
            }
        } else if (this.variableType.equals("percentSuccess")) {
            int scsCnt = 0;
            for (int i = 0; i < sample.size() - (this.successCondOutcomes.size() -1) ; i++) {
                boolean match = true;
                for (int j = 0; j < this.successCondOutcomes.size(); j++) {
                    if (!sample.get(i + j).outcomeName.equals(this.successCondOutcomes.get(j).outcomeName)) {    // potential match
                        match = false;
                        break;
                    }
                }
                if (match == true) {
                    scsCnt += this.successCondOutcomes.size(); // check this with Aaron
                }


            }
            System.out.println("Success Count: " + scsCnt);
            double prcnt = (double) scsCnt / sample.size();
            return prcnt + "";
        } else if (this.variableType.equals("numSuccess")) {
            int scsCnt = 0;
            System.out.println("measuring num successes");
            for (int i = 0; i <  sample.size() - (this.successCondOutcomes.size() - 1 ) ; i++) {
                boolean match = true;
                for (int j = 0; j < this.successCondOutcomes.size() ; j++) {
                    if (!sample.get(i + j).outcomeName.equals(this.successCondOutcomes.get(j).outcomeName)) {    // potential match
                        System.out.println("Sample: " + sample.get(i).outcomeName);
                        match = false;
                        break;
                    }
                }
                if (match == true) {
                    scsCnt += this.successCondOutcomes.size(); // check this with Aaron
                }


            }
            System.out.println("Success Count: " + scsCnt);

            return scsCnt + "";
        }     
          else if(this.variableType.equals("length")){
             return sample.size() + "";
         }
         else if(this.variableType.equals("mode")){
            double mode = 0;
            String modeVal = "";
            double currMode;
            for (int i = 0; i < sample.size(); i++){
                currMode = 0;
                for(int j = 0; j < sample.size(); j++){
                    if (sample.get(j).outcomeName.equals(sample.get(i).outcomeName)){
                        currMode += 1;
                    }
                    
                }
                if (currMode > mode){
                    mode = currMode;
                    modeVal = sample.get(i).outcomeName;
                }
            }
            
            return modeVal + "";
            }
         else if(this.variableType.equals("modeSize")){
            double mode = 0;
            String modeVal = "";
            double currMode;
            for (int i = 0; i < sample.size(); i++){
                currMode = 0;
                for(int j = 0; j < sample.size(); j++){
                    if (sample.get(j).outcomeName.equals(sample.get(i).outcomeName)){
                        currMode += 1;
                    }
                    
                }
                if (currMode > mode){
                    mode = currMode;
                    modeVal = sample.get(i).outcomeName;
                }
            }
            
            return mode + "";
            }
          else {
              
         //   System.out.println("Measuring numeric stats...");
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (Outcome o : sample) {
                System.out.println("outcomeName: " + o.outcomeName);
                stats.addValue(Double.parseDouble(o.outcomeName));    // this has to be correct. for real. 
            }
            return measureSuccess(stats);
        }
        return null;
    }

    public boolean checkForStop(ArrayList<Outcome> sample) {
        boolean match;
        System.out.println("Stop Condition Type: " + this.stopConditionType);
        if (this.stopConditionType.equals("someOrder")) {

            for (int i = 0; i < sample.size() -  (this.stopCondOutcomes.size() -1); i++) {
                match = true;
                System.out.println("Sample at i: " + sample.get(i).outcomeName);

                for (int j = 0; j < this.stopCondOutcomes.size(); j++) {
                    System.out.println("Sample at i + j: " + sample.get(i + j).outcomeName);
                                          
                    if (!sample.get(i + j).outcomeName.equals(this.stopCondOutcomes.get(j).outcomeName)) {    // potential match
                        match = false;
                        System.out.println("No match");


                        break;
                    }
                }
                if (match == true) {
                    return match;
                }
            }

            return false;
        } else if (this.stopConditionType.equals("someSet")) {
            HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
            for (Outcome o : sample) {
                if (stopOutcomeSet.containsKey(o.outcomeName)) {
                    if (sampleSet.containsKey(o.outcomeName)) {
                        int val = sampleSet.get(o.outcomeName) + 1;
                        sampleSet.put(o.outcomeName, val);
                    } else {
                        sampleSet.put(o.outcomeName, 1);

                    }
                }
            }
            for (Map.Entry<String, Integer> entry : stopOutcomeSet.entrySet()) {
                if (sampleSet.containsKey(entry.getKey()) && sampleSet.get(entry.getKey()) >= entry.getValue()) {
                    return true;
                }
            }


            return false;
        } else if (this.stopConditionType.equals("allSet")) {
            
            System.out.println("\n");
            HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
            for (Outcome o : sample) {
                if (stopOutcomeSet.containsKey(o.outcomeName)) {
                    if (sampleSet.containsKey(o.outcomeName)) {
                        int val = sampleSet.get(o.outcomeName) + 1;
                        sampleSet.put(o.outcomeName, val);
                    } else {
                        sampleSet.put(o.outcomeName, 1);

                    }
                }
            }
            for (Map.Entry<String, Integer> entry : stopOutcomeSet.entrySet()) {
                if (!(sampleSet.containsKey(entry.getKey()) && sampleSet.get(entry.getKey()) >= entry.getValue())) // check this 
                {
                    return false;
                }
            }


            return true;
        } else {
            System.out.println("Error. Stop condition type not recognized");
            return false;
        }
    }

    public String getDescriptiveStats() {
        double ttlMean = 0;

        double ttlStd = 0;
        double ttlMin = 0;
        double ttlMax = 0;
        String desc = "";
        DescriptiveStatistics descStat = new DescriptiveStatistics();
        try{
            for (Trial trial : trials) {
                descStat.addValue(Double.parseDouble(parseResponse(trial.response)));
            }
            
           desc = "Mean:" + Double.valueOf(twoDForm.format(descStat.getMean())) + "\nStandard Deviation: " + Double.valueOf(twoDForm.format(descStat.getStandardDeviation())) + "\n\nMinimum: " +Double.valueOf(twoDForm.format(descStat.getMin()))
                + "\nQ1: " + Double.valueOf(twoDForm.format(descStat.getPercentile(25))) + "\nMedian: " + Double.valueOf(twoDForm.format(descStat.getPercentile(50))) + "\nQ3: " + Double.valueOf(twoDForm.format(descStat.getPercentile(75))) + "\nMaximum: " + Double.valueOf(twoDForm.format(descStat.getMax()));

        }
        catch(NumberFormatException ne){
            
            if(this.variableType.equals("mean+std")){
                descStat = new DescriptiveStatistics();
                for (Trial trial : trials) {
                 descStat.addValue(Double.parseDouble(trial.response.split(" ")[0]));
                }
            
                desc = "Mean:" + Double.valueOf(twoDForm.format(descStat.getMean())) + "\nStandard Deviation: " + Double.valueOf(twoDForm.format(descStat.getStandardDeviation())) + "\n\nMinimum: " +Double.valueOf(twoDForm.format(descStat.getMin()))
                + "\nQ1: " + Double.valueOf(twoDForm.format(descStat.getPercentile(25))) + "\nMedian: " + Double.valueOf(twoDForm.format(descStat.getPercentile(50))) + "\nQ3: " + Double.valueOf(twoDForm.format(descStat.getPercentile(75))) + "\nMaximum: " + Double.valueOf(twoDForm.format(descStat.getMax()));

            }else{
            desc = "N/A";
            }
        }
        return desc;
    }

    public String getDistribution() {
        double ttlMean = 0;

        double ttlStd = 0;
        double ttlMin = 0;
        double ttlMax = 0;
        String dist = "Value      Frequency    Relative Frequency\n";
        Frequency freq = new Frequency();
        for (Trial trial : trials) {
            freq.addValue(trial.response); // adds raw response before formatting to avoid roundoff errors
        }
        
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        Iterator freqItr = freq.valuesIterator();
        String dispVal, val; 
        while (freqItr.hasNext()) {
            val = (String) freqItr.next();
            dispVal = parseResponse(val);
            dist += dispVal + "            " + freq.getCount(val) + "            " + freq.getPct(val) + "\n";

        }
        // desc =  "Mean:" + freq.getMean() + "\nStandard Deviation: " + descStat.getStandardDeviation() + "\n\nMinimum: " + descStat.getMin() + 
        //       "\nQ1: " + freq.getPercentile(25) + "\nMedian: " + descStat.getPercentile(50) + "\nQ3: " + descStat.getPercentile(75) + "\nMaximum: " + descStat.getMax(); 
        return dist;
    }

    public StringBuffer displayRandNumbers() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);
        System.out.println("Number of trials: " + trials.size());
        for (Trial t : trials) {
        //    System.out.println("Trial id: " + t.id);
            if (t.randoms != null) {
             //   System.out.println("Trial randoms length:  " + t.randoms.length);
                for (int i = 0; i < t.randoms.length; i++) {
                    disp.append((int) t.randoms[i] + ", ");
                }
                disp.append("\n");
            } else if (t.outcomes != null) {
              //  System.out.println("Trial random outcome length:  " + t.outcomes.size());
                     
                for (int i = 0; i < t.outcomes.size(); i++) {
                    disp.append((int) t.outcomes.get(i).randNum + ", ");
                }
                disp.append("\n");
            } else {
                disp.append("Nothing to Display" + '\n');
            }
        }
        System.out.println("Done with rands ( still in method)");
        return disp;
    }

    public StringBuffer displayOutcomes() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);
        System.out.println("Number of trials: " + trials.size());

        for (Trial t : trials) {
            if (t.outcomes != null) {
                for (int i = 0; i < t.outcomes.size(); i++) {
                    disp.append(t.outcomes.get(i).outcomeName + ", ");
                }
                disp.append("\n");
            } else {
                disp.append( "Nothing to Display" + '\n');
            }
        }
              // System.out.println("Done with rands ( still in method)");

        return disp;

    }

    /**
     * Creates and returns a string buffer with a list of the response variables for each trial.
     * @return 
     */
    public StringBuffer displayResponseVariables() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);
        for (Trial t : trials) {
            if (t.response != null) {
                System.out.println("response: " + t.response);
                String r = parseResponse(t.response);
                disp.append(r + "\n");
            } else {
                disp.append( "Nothing to Display" + '\n');
            }
        }
        return disp;
    }

    /**
     * Converts a response variable into a displayable format.  
     * The action performed is contingent on the type of variable.  
     * Note that if the variable is a mode, descriptive stats are not applicable.  
     * @param response
     * @return 
     */
    private String parseResponse(String response) throws NumberFormatException {
        System.out.println("\n Formatting Response Variable: " + response);
        if (variableType.equals("mean+std")) {
            String[] unpacked = response.split("      ");
            response = twoDForm.format(Double.parseDouble(unpacked[0])) + "      " + twoDForm.format(Double.parseDouble(unpacked[1]));
            return response;

        } else if (variableType.equals("containsSuccess")) {
            if (response.equalsIgnoreCase("true")) {
                return "1";
            } else {
                return "0";
            }
        } else if(!variableType.equals("mode")){
                return twoDForm.format(Double.parseDouble(response));
        }
      else {
            return response;
        }
    
}
}
    


