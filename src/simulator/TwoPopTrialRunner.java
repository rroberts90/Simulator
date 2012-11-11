/*
 * TwoPopTrialRunner.java
 */
package simulator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.BinomialDistributionImpl;
import org.apache.commons.math.distribution.ExponentialDistributionImpl;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.commons.math.random.UniformRandomGenerator;
import org.apache.commons.math.stat.Frequency;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

/**
 *  Contains all properties and methods required to run full simulations with 
 * two distinct groups or populations.  
 * @author Richard
 */
public class TwoPopTrialRunner extends TrialRunner {

    float realAvg = 0;
    int diffGroup = 1;
    Population p1;
    Population p2;
    String distType1;
    String distType2;
    String trialType;
    Sample s1 = new Sample();
    Sample s2 = new Sample();
    String populationType;
    NormalDistributionImpl normDist;
    BinomialDistributionImpl binomialDist;
    ExponentialDistributionImpl expDist;
    ArrayList<TwoPopTrial> trials = new ArrayList<TwoPopTrial>();
    String category = "";
    double p1DistVal1, p1DistVal2, p2DistVal1, p2DistVal2;
    int groupSize;
    int varCase;
    boolean g1Replace;
    boolean g2Replace;
    String ANDOR;

    // getters and setters 
    public Population getPopulation1() {
        return p1;
    }

    public Population getPopulation2() {
        return p2;
    }

    public void setPopulation1(Population pop) {
        this.p1 = pop;
    }

    public void setPopulation2(Population pop) {
        this.p2 = pop;
    }

    public void setCase(int varCase) {
        this.varCase = varCase;
    }

    public void setDiffGroup(int diffGroup) {
        this.diffGroup = diffGroup;
    }

    /**
     * Default Constructor.
     */
    public TwoPopTrialRunner() {
    }

    /**
     * 
     * Purpose: runs series of simulations based on user-defined parameters
     * Preconditions: all variables related to the user's simulation are initialized.  
     * 
     * Runs the simulation described by the user for a specified # of trials
     * 
     * If a trial stops running, it throws an error message and stops computing trials.  
     * Errors are thrown whenever the user attempts to run an impossible trial.  
     * 
     */
    public void runTrials() {
        System.out.println(numTrials);
        RandomDataImpl uD1, uD2;


        uD1 = new RandomDataImpl();
        uD2 = new RandomDataImpl();


        try {

            trials = new ArrayList<TwoPopTrial>();
            System.out.println("Runing trials....");
            System.out.println("Fixed Outcomes?" + s1.isFixedOutcomes);
            System.out.println("Var case: " + varCase);
            System.out.println("Fixed outcomes: " + s1.isFixedOutcomes);

            if (s1.isFixedOutcomes) {
                for (int i = 0; i < numTrials; i++) {
        
                    if (varCase == 1 || varCase == 2) {

                        runMatchDrawTrial(i);
                    } else if (varCase == 4) { 

                        runCompareTwoNumericTrial(i);
                    } else if (varCase == 5) {

                        runCompareTwoCategoriesTrial(i);
                    } else if (varCase == 7) {
                        if (populationType.equals("categorical")) {
                            runTwoPopProportionCategoryTrial(i);

                        } else {
                            runTwoPopNumericalTrial(i);


                        }
                    } else if (varCase == 8) {
                        runMeanDrawTrial(i);
                    } 
                    else if (distType.equals("norm")) {

                        runNormDistTrial(i + 1);

                    } else if (distType.equals("binomial")) {
                        runBinomialDistTrial(i + 1);
                    }




                }
            } else {
                for (int i = 0; i < numTrials; i++) {
                    if (distType == null) {

                        if (varCase == 1 || varCase == 2) {
                            runVariableMatchDrawTrial(i);
                        } else if (varCase == 7) {
                            if (populationType.equals("categorical")) {
                                runVariableTwoPopProportionCategoryTrial(i);
                            } else {
                                runVariableTwoPopNumericalTrial(i);
                            }
                        } else if (varCase == 8) {
                            runVariableMeanDrawTrial(i);
                        }
                    } else if (distType.equals("norm")) {

                        runNormDistTrial(i + 1);

                    } else if (distType.equals("binomial")) {
                        runBinomialDistTrial(i + 1);
                    }


                }
            }
        } catch (MathException e) {
        }
    }

    /**
     * Samples from a normal distribution
     * @param id
     * @throws MathException 
     */
    public void runNormDistTrial(int id) throws MathException {

        NormalDistributionImpl nD1, nD2;

        nD1 = new NormalDistributionImpl(p1DistVal1, p1DistVal2);
        nD2 = new NormalDistributionImpl(p2DistVal1, p2DistVal2);

        if (variableType.equals("trialMeanDiff")) { 

            double[] s1 = nD1.sample(numObservations);
            double[] s2 = nD1.sample(numObservations);
            DescriptiveStatistics stats1 = new DescriptiveStatistics();
            DescriptiveStatistics stats2 = new DescriptiveStatistics();

            for (int j = 0; j < numObservations; j++) {
                stats1.addValue(s1[j]);
                stats2.addValue(s2[j]);
            }
            String response = stats1.getMean() - stats2.getMean() + "";
            trials.add(new TwoPopTrial(id, s1, s2, response));

        }
        if (variableType.equals("observationMeanDiff")) { 


            double[] s1 = nD1.sample(numObservations);
            double[] s2 = nD1.sample(numObservations);
            DescriptiveStatistics stats1 = new DescriptiveStatistics();

            for (int j = 0; j < numObservations; j++) {
                stats1.addValue(s1[j] - s2[j]);

            }
            String response = stats1.getMean() + "";
            trials.add(new TwoPopTrial(id, s1, s2, response));

        }




    }

    /**
     * Samples from a Binomial Distribution
     * @param id
     * @throws MathException 
     */
    public void runBinomialDistTrial(int id) throws MathException {

        BinomialDistributionImpl bD1, bD2;


        bD1 = new BinomialDistributionImpl((int) p1DistVal1, p1DistVal2);
        bD2 = new BinomialDistributionImpl((int) p2DistVal1, p2DistVal2);


        if (variableType.equals("trialMeanDiff")) { // measure the difference in proportions

            int[] s1 = bD1.sample(numObservations);
            int[] s2 = bD1.sample(numObservations);
            DescriptiveStatistics stats1 = new DescriptiveStatistics();
            DescriptiveStatistics stats2 = new DescriptiveStatistics();

            for (int j = 0; j < numObservations; j++) {
                stats1.addValue(s1[j]);
                stats2.addValue(s2[j]);
            }
            String response = stats1.getMean() - stats2.getMean() + "";
            double[] s11 = new double[s1.length];
            double[] s22 = new double[s2.length];

            for (int i = 0; i < s1.length; i++) {
                s11[i] = s1[i];
            }
            for (int i = 0; i < s2.length; i++) {
                s22[i] = s2[i];
            }
            trials.add(new TwoPopTrial(id, s11, s22, response));

        }
        if (variableType.equals("observationMeanDiff")) { // measure the difference in proportions


            int[] s1 = bD1.sample(numObservations);
            int[] s2 = bD1.sample(numObservations);
            DescriptiveStatistics stats1 = new DescriptiveStatistics();

            for (int j = 0; j < numObservations; j++) {
                stats1.addValue(s1[j] - s2[j]);

            }
            String response = stats1.getMean() + "";
            double[] s11 = new double[s1.length];
            double[] s22 = new double[s2.length];

            for (int i = 0; i < s1.length; i++) {
                s11[i] = s1[i];
            }
            for (int i = 0; i < s2.length; i++) {
                s22[i] = s2[i];
            }
            trials.add(new TwoPopTrial(id, s11, s22, response));

        }

    }

    public void runTwoPopProportionCategoryTrial(int id) {
        ArrayList<Outcome> s1List = p1.sample(s1.numObservations, s1.isWithReplacement);
        ArrayList<Outcome> s2List = p2.sample(s2.numObservations, s2.isWithReplacement);
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < s1.numObservations; i++) {

            if (s1List.get(i).outcomeName.equals(category)) {

                oneCount++;
            }

        }

        for (int i = 0; i < s2.numObservations; i++) {

            if (s2List.get(i).outcomeName.equals(category)) {

                twoCount++;
            }

        }   
        double response = Math.abs((double) oneCount / s1.numObservations - (double) twoCount / s2.numObservations);
        trials.add(new TwoPopTrial(id, s1List, s2List, response + ""));


    }

    /**
     * Computes the difference in proportion of a single variable across two populations.
     * @param id 
     */
    public void runVariableTwoPopProportionCategoryTrial(int id) {
        ArrayList<Outcome> s1List = new ArrayList<Outcome>(); //p1.sample(s1.numObservations, s1.isWithReplacement);
        ArrayList<Outcome> s2List = new ArrayList<Outcome>();// p2.sample(s2.numObservations, s2.isWithReplacement);
        boolean s1Go = true;
        boolean s2Go = true;
        int oneCount = 0;
        int twoCount = 0;
        while (!checkForStop(s1List, s2List)) {

            Outcome o1 = p1.sample(s1.isWithReplacement());
            s1List.add(o1);
            if (o1.outcomeName.equals(category)) {

                oneCount++;

                s2Go = checkSampleForStop(s1List, s1);

            }

            Outcome o2 = p2.sample(s2.isWithReplacement());
            s2List.add(o2);
            if (o2.outcomeName.equals(category)) {
                twoCount++;
            }

            s2Go = checkSampleForStop(s2List, s2);

        }

        double response = Math.abs((double) oneCount / numObservations - (double) twoCount / numObservations);

        trials.add(new TwoPopTrial(id, s1List, s2List, response + ""));


    }

    /**
     * Computes the difference in means between samples drawn from 2 populations.
     * @param id
     * @throws NumberFormatException 
     */
    public void runTwoPopNumericalTrial(int id) throws NumberFormatException {
 
        DescriptiveStatistics stats1 = new DescriptiveStatistics();
        DescriptiveStatistics stats2 = new DescriptiveStatistics();


        ArrayList<Outcome> s1List = p1.sample(s1.numObservations, s1.isWithReplacement);
        ArrayList<Outcome> s2List = p2.sample(s2.numObservations, s2.isWithReplacement);
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < s1.numObservations; i++) {
   
            stats1.addValue(Double.parseDouble(s1List.get(i).outcomeName));
            stats2.addValue(Double.parseDouble(s2List.get(i).outcomeName));

        }

        double result = 0;
        if (diffGroup == 1) {
            result = stats1.getMean() - stats2.getMean();

        } else {
            result = stats2.getMean() - stats1.getMean();

        }
        trials.add(new TwoPopTrial(id, s1List, s2List, result + ""));


    }

    /**
     * Computes the difference in means of two populations.  Thsi method will run
     * until the sotp condition returns true.
     * @param id
     * @throws NumberFormatException 
     */
    public void runVariableTwoPopNumericalTrial(int id) throws NumberFormatException {

        DescriptiveStatistics stats1 = new DescriptiveStatistics();
        DescriptiveStatistics stats2 = new DescriptiveStatistics();

        ArrayList<Outcome> s1List = new ArrayList<Outcome>();
        ArrayList<Outcome> s2List = new ArrayList<Outcome>();
        int oneCount = 0;
        int twoCount = 0;
        boolean s1Go = true;
        boolean s2Go = true;
        while (!checkForStop(s1List, s2List)) {

            Outcome o1 = p1.sample(s1.isWithReplacement());
            s1List.add(o1);
            stats1.addValue(Double.parseDouble(o1.outcomeName));
            s1Go = checkSampleForStop(s1List, s1);

            Outcome o2 = p2.sample(s2.isWithReplacement());

            s2List.add(o2);
            stats2.addValue(Double.parseDouble(o2.outcomeName));

            s2Go = checkSampleForStop(s2List, s2);
        }
        p1.endSample();
        p2.endSample();

        double result = 0;
        if (diffGroup == 1) {
            result = stats1.getMean() - stats2.getMean();

        } else {
            result = stats2.getMean() - stats1.getMean();

        }
        trials.add(new TwoPopTrial(id, s1List, s2List, result + ""));
    }

    /**
     *  Returns the # of matches in observations between 2 populations. 
     * @param id 
     */
    public void runMatchDrawTrial(int id) {
        ArrayList<Outcome> s1 = new ArrayList<Outcome>();
        ArrayList<Outcome> s2 = new ArrayList<Outcome>();
        int matchCount = 0;
        int n = Math.min(this.s1.numObservations, this.s2.numObservations);
        for (int j = 0; j < n; j++) {
            Outcome obs1 = drawOneFromPop(1);

            Outcome obs2 = drawOneFromPop(2);

            String response = "";
            if (obs1.outcomeName.equals(obs2.outcomeName)) {
                matchCount++;
            }

            s1.add(obs1);
            s2.add(obs2);
        }

        trials.add(new TwoPopTrial(id, s1, s2, matchCount + ""));
        p1.endSample();
        p2.endSample();

    }

    /**
     * Returns the # of matches in observations between 2 populations. 
     * Used for trials with no fixed outcomes.  
     * @param id 
     */
    public void runVariableMatchDrawTrial(int id) {
        ArrayList<Outcome> s1List = new ArrayList<Outcome>();
        ArrayList<Outcome> s2List = new ArrayList<Outcome>();
        int matchCount = 0;

        while (!checkForStop(s1List, s2List)) {
            Outcome obs1 = drawOneFromPop(1);

            Outcome obs2 = drawOneFromPop(2);

            String response = "";
            if (obs1.outcomeName.equals(obs2.outcomeName)) {
                matchCount++;
            }
            s1List.add(obs1);
            s2List.add(obs2);
        }

        trials.add(new TwoPopTrial(id, s1List, s2List, matchCount + ""));
        p1.endSample();
        p2.endSample();
    }

    /**
     * Returns the mean of the differences in observations between two populations. 
     * @param id 
     */
    public void runMeanDrawTrial(int id) {
        ArrayList<Outcome> s1List = new ArrayList<Outcome>();
        ArrayList<Outcome> s2List = new ArrayList<Outcome>();
        int matchCount = 0;
        DescriptiveStatistics stats1 = new DescriptiveStatistics();
        int n = Math.min(s1.numObservations, s2.numObservations);
        for (int j = 0; j < s1.numObservations; j++) {
            Outcome obs1 = drawOneFromPop(1);
            Outcome obs2 = drawOneFromPop(2);

            System.out.println("Observation 1: " + obs1);
            System.out.println("Population 21: " + p1);
            System.out.println("List 1: " + p1.outcomeDrawList.get(0));
            System.out.println("Observation 2: " + obs2);
            System.out.println("Population 2: " + p2);
            System.out.println("List 2: " + p2.outcomeDrawList.get(0));

            double response = 0;
            if (diffGroup == 1) {
                response = Double.parseDouble(obs1.outcomeName) - Double.parseDouble(obs2.outcomeName);

            } else {
                response = Double.parseDouble(obs2.outcomeName) - Double.parseDouble(obs1.outcomeName);

            }


            stats1.addValue(response);

            s1List.add(obs1);
            s2List.add(obs2);
        }
        trials.add(new TwoPopTrial(id, s1List, s2List, stats1.getMean() + ""));
        p1.endSample();
        p2.endSample();
    }

    /**
     *  Returns the mean of the differences in observations between two populations. 
     * Used for trials with no fixed outcomea. 
     * @param id 
     */
    public void runVariableMeanDrawTrial(int id) {
        ArrayList<Outcome> s1List = new ArrayList<Outcome>();
        ArrayList<Outcome> s2List = new ArrayList<Outcome>();
        int matchCount = 0;
        DescriptiveStatistics stats1 = new DescriptiveStatistics();

        while (!checkForStop(s1List, s2List)) {
            Outcome obs1 = drawOneFromPop(1);
            Outcome obs2 = drawOneFromPop(2);
            double response = Math.abs(Double.parseDouble(obs1.outcomeName) - Double.parseDouble(obs2.outcomeName));
            stats1.addValue(response);

            s1List.add(obs1);
            s2List.add(obs2);
        }
        trials.add(new TwoPopTrial(id, s1List, s2List, stats1.getMean() + ""));
        p1.endSample();
        p2.endSample();
    }

    /**
     * Runs a fixed number of trials where the proportion of 1 category is compared across 2 groups.
     * @param id 
     */
    public void runCompareTwoCategoriesTrial(int id) throws NumberFormatException {


        if (s1.isWithReplacement && s2.isWithReplacement) {
            ArrayList<Outcome> groupSample1 = p1.sample(s1.numObservations, true);
            ArrayList<Outcome> groupSample2 = p1.sample(s2.numObservations, true);
            int oneCount = 0;
            int twoCount = 0;
    
            compareTwoCategories(id, groupSample1, groupSample2);


        } else if (!s1.isWithReplacement && !s2.isWithReplacement) {
            ArrayList<Outcome> g1 = new ArrayList<Outcome>();
            ArrayList<Outcome> g2 = new ArrayList<Outcome>();
            Random rand = new Random();
            System.out.println("Outcome Draw List: " + p1.outcomeDrawList.size());
            int[] rands = new int[p1.outcomeDrawList.size()];
            for (int i = 0; i < rands.length; i++) {
                rands[i] = i;
            }

            for (int i = 0; i < rands.length; i++) {
                swap(rands, i, rand.nextInt(rands.length));
            }

            for (int i = 0; i < s1.numObservations; i++) {
                Outcome o = new Outcome(p1.outcomeDrawList.get(rands[i]));
                g1.add(o);
            }

            for (int i = s1.numObservations; i < s1.numObservations + s2.numObservations; i++) {
                Outcome o = new Outcome(p1.outcomeDrawList.get(rands[i]));
                g2.add(o);
            }


            Population gP1 = new Population(g1, true);
            Population gP2 = new Population(g2, true);

            ArrayList<Outcome> groupSample1 = gP1.sample(s1.numObservations, false);
            ArrayList<Outcome> groupSample2 = gP2.sample(s2.numObservations, false);
            int oneCount = 0;
            int twoCount = 0;
            compareTwoCategories(id, groupSample1, groupSample2);
        } else {
            throw new NumberFormatException("Cannot Preform a with / without replacement trial");
        }
    }

    private void compareTwoCategories(int id, ArrayList<Outcome> groupSample1, ArrayList<Outcome> groupSample2) {
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < groupSample1.size(); i++) {

            if (groupSample1.get(i).outcomeName.trim().equals(category.trim())) {

                oneCount++;
            }

        }

        for (int i = 0; i < groupSample2.size(); i++) {

            if (groupSample2.get(i).outcomeName.trim().equals(category.trim())) {

                twoCount++;
            }

        }

        double response = 0;
        if (diffGroup == 1) {
            response = (double) oneCount / groupSample1.size() - (double) twoCount / groupSample2.size();

        } else {
            response = (double) twoCount / groupSample2.size() - (double) oneCount / groupSample1.size();

        }
        trials.add(new TwoPopTrial(id, groupSample1, groupSample2, response + ""));
    }

    //case 4. 
    /**
     *  Computes the difference in means between two groups drawn from the same population.
     * @param id 
     */
    public void runCompareTwoNumericTrial(int id) throws NumberFormatException {


        if (s1.isWithReplacement && s2.isWithReplacement) {

            ArrayList<Outcome> groupSample1 = p1.sample(s1.numObservations, true);
            ArrayList<Outcome> groupSample2 = p1.sample(s2.numObservations, true);

        } else if (!s1.isWithReplacement && !s2.isWithReplacement) {
            ArrayList<Outcome> g1 = new ArrayList<Outcome>();
            ArrayList<Outcome> g2 = new ArrayList<Outcome>();
            Random rand = new Random();


            int[] rands = new int[p1.outcomeDrawList.size()];
            for (int i = 0; i < rands.length; i++) {
                rands[i] = i;
            }
            // randomize the list
            for (int i = 0; i < rands.length; i++) {
                swap(rands, i, rand.nextInt(rands.length));
            }

            // create the first group.  s1.numObservations is the number of outcomes in group
            for (int i = 0; i < s1.numObservations; i++) {
                g1.add(p1.outcomeDrawList.get(rands[i]));
            }

            // create second group.  
            for (int i = s1.numObservations; i < s1.numObservations + s2.numObservations; i++) {
                g2.add(p1.outcomeDrawList.get(rands[i]));
            }


            System.out.println("Num observations in group 1:" + g1.size());
            System.out.println("Num observations in group 2:" + g2.size());

            System.out.println("Before creating new pops...");
            Population gP1 = new Population(g1, true);
            Population gP2 = new Population(g2, true);
            System.out.println("After creating new pops...");

            ArrayList<Outcome> groupSample1 = gP1.sample(s1.numObservations, false);
            ArrayList<Outcome> groupSample2 = gP2.sample(s2.numObservations, false);

            compareNumeric(id, groupSample1, groupSample2);
        } else {
            throw new NumberFormatException("Can't calculate mixed replacement / non-replacement groups");
        }
    }

    private void compareNumeric(int id, ArrayList<Outcome> groupSample1, ArrayList<Outcome> groupSample2) {
        DescriptiveStatistics stats1 = new DescriptiveStatistics();
        DescriptiveStatistics stats2 = new DescriptiveStatistics();
        for (Outcome o : groupSample1) {
            stats1.addValue(Double.parseDouble(o.outcomeName));
        }
        for (Outcome o : groupSample2) {
            stats2.addValue(Double.parseDouble(o.outcomeName));
        }
        System.out.println("After creating stats...");


        double response = 0;
        if (diffGroup == 1) {
            response = stats1.getMean() - stats2.getMean();

        } else {
            response = stats2.getMean() - stats1.getMean();

        }
        trials.add(new TwoPopTrial(id, groupSample1, groupSample2, response + ""));

    }

    /**
     *  Samples a single outcome from one of the 2 populations. 
     * @param popNum
     * @return 
     */
    public Outcome drawOneFromPop(int popNum) {
        if (popNum == 1) {
            return p1.sample(s1.isWithReplacement);
        } else {
            return p2.sample(s2.isWithReplacement);
        }


    }

    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     *  Returns the statistics for the response variables of the current trial set. 
     * @return 
     */
    public String getDescriptiveStats() {
        double ttlMean = 0;
        double ttlStd = 0;
        double ttlMin = 0;
        double ttlMax = 0;
        String desc = "";
        System.out.println("Real Average: " + realAvg/ trials.size());
        DescriptiveStatistics descStat = new DescriptiveStatistics();
        for (TwoPopTrial trial : trials) {
            double res = Double.parseDouble(trial.response);
            if (res != -1) {
                descStat.addValue(res);
            }
        }
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        System.out.println("Actual mean: " + descStat.getMeanImpl());
        System.out.println("Actual mean: " + descStat.getMean());

        System.out.println("Length of stats: " + descStat.getValues().length);
        desc = "Mean:" + Double.valueOf(twoDForm.format(descStat.getMean())) + "\nStandard Deviation: " + Double.valueOf(twoDForm.format(descStat.getStandardDeviation())) + "\n\nMinimum: " + Double.valueOf(twoDForm.format(descStat.getMin()))
                + "\nQ1: " + Double.valueOf(twoDForm.format(descStat.getPercentile(25))) + "\nMedian: " + Double.valueOf(twoDForm.format(descStat.getPercentile(50))) + "\nQ3: " + Double.valueOf(twoDForm.format(descStat.getPercentile(75))) + "\nMaximum: " + Double.valueOf(twoDForm.format(descStat.getMax()));
        return desc;
    }

    /**
     *  Finds the frequency of each response variable
     * @return 
     */
    public String getDistribution() {
        double ttlMean = 0;

        double ttlStd = 0;
        double ttlMin = 0;
        double ttlMax = 0;
        String dist = "Value      Frequency    Relative Frequency\n";
        Frequency freq = new Frequency();
        for (TwoPopTrial trial : trials) {
            double res = Double.parseDouble(trial.response);

            freq.addValue(res);
        }

        DecimalFormat twoDForm = new DecimalFormat("#.####");

        Iterator freqItr = freq.valuesIterator();
        double dispVal;
        while (freqItr.hasNext()) {
            Double val = (Double) freqItr.next();
            dispVal = Double.valueOf(twoDForm.format(val));

            dist += dispVal + "               " + freq.getCount(val) + "                " + freq.getPct(val) + "\n";

        }
         return dist;
    }

    public StringBuffer displayRandNumbers() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);

        System.out.println("Number of Trials: " + trials.size());
        for (TwoPopTrial t : trials) {
            if (t.values1 != null) {
                for (int i = 0; i < t.values1.length; i++) {
                    disp.append((int) t.values1[i] + ", ");
                }
                disp.append("        ");
            }
            if (t.values2 != null) {
                for (int i = 0; i < t.values2.length; i++) {
                    disp.append((int) t.values2[i] + ", ");
                }
            }
            if (t.sample1 != null) {
                for (int i = 0; i < t.sample1.size(); i++) {

                    disp.append((int) t.sample1.get(i).randNum + ", ");
                }
                disp.append("      ");

            }
            if (t.sample2 != null) {

                for (int i = 0; i < t.sample2.size(); i++) {
                    disp.append((((int) t.sample2.get(i).randNum) + ", "));
                }
            } else {
                disp.append("Nothing to Display" + '\n');
            }

            disp.append("\n");


        }
        return disp;
    }

    public StringBuffer displayOutcomes() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);
        for (TwoPopTrial t : trials) {
            if (t.sample1 != null) {
                for (int i = 0; i < t.sample1.size(); i++) {
                    disp.append(t.sample1.get(i).outcomeName + ",");
                }
            }
            disp.append("       ");
            if (t.sample2 != null) {
                for (int i = 0; i < t.sample2.size(); i++) {
                    disp.append(t.sample2.get(i).outcomeName + ", ");
                }
            } else {
                disp.append("Nothing to Display");
            }
            disp.append("\n");

        }
        return disp;

    }

    public StringBuffer displayResponseVariables() {
        StringBuffer disp = new StringBuffer(trials.size() * numObservations);

        for (TwoPopTrial t : trials) {
            if (t.response != null) {
                double r = Double.parseDouble(t.response);
                DecimalFormat twoDForm = new DecimalFormat("#.####");
                double d = Double.valueOf(twoDForm.format(r));
                disp.append(d + "\n");
            } else {
                disp.append("Nothing to Display" + '\n');
            }
        }
        return disp;
    }

    private double parseResponse(String response) {

        if (variableType.equals("mean+std")) {
            return Double.parseDouble(response.split(" ")[0]);

        } else if (variableType.equals("containsSuccess")) {
            if (response.equalsIgnoreCase("true")) {
                return 1;
            } else {
                return 0;
            }
        } else {
            double r = 0;
            try {
                r = Double.parseDouble(response);
            } catch (NumberFormatException e) {
            }
            return -1;
        }
    }

    public boolean checkForStop(ArrayList<Outcome> sample1, ArrayList<Outcome> sample2) {
        if (this.ANDOR.equals("and")) {
            return checkSampleForStop(sample1, s1) && checkSampleForStop(sample2, s2);
        } else {
            return checkSampleForStop(sample1, s1) || checkSampleForStop(sample2, s2);
        }
    }

    public boolean checkSampleForStop(ArrayList<Outcome> sample1, Sample s) {
        boolean match;

        if (s.stopConditionType.equals("someOrder")) {
            System.out.println("Stop Cond Outcome Len: " + s.stopCondOutcomes.size());
            for (int i = 0; i < sample1.size() - (s.stopCondOutcomes.size() - 1); i++) {
                match = true;
                System.out.println("Sample at i: " + sample1.get(i).outcomeName);

                for (int j = 0; j < s.stopCondOutcomes.size(); j++) {
                    System.out.println("Sample at i + j: " + sample1.get(i + j).outcomeName);

                    if (!sample1.get(i + j).outcomeName.equals(s.stopCondOutcomes.get(j).outcomeName)) {    // potential match
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
        } else if (s.stopConditionType.equals("someSet")) {
            HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
            for (Outcome o : sample1) {
                if (s.stopOutcomeSet.containsKey(o.outcomeName)) {
                    if (sampleSet.containsKey(o.outcomeName)) {
                        int val = sampleSet.get(o.outcomeName) + 1;
                        sampleSet.put(o.outcomeName, val);
                    } else {
                        sampleSet.put(o.outcomeName, 1);

                    }
                }
            }
            for (Map.Entry<String, Integer> entry : s.stopOutcomeSet.entrySet()) {
                if (sampleSet.containsKey(entry.getKey()) && sampleSet.get(entry.getKey()) >= entry.getValue()) {
                    return true;

                }
            }


            return false;
        } else if (this.stopConditionType.equals("allSet")) {

            System.out.println("\n");
            HashMap<String, Integer> sampleSet = new HashMap<String, Integer>();
            for (Outcome o : sample1) {
                if (s.stopOutcomeSet.containsKey(o.outcomeName)) {
                    if (sampleSet.containsKey(o.outcomeName)) {
                        int val = sampleSet.get(o.outcomeName) + 1;
                        sampleSet.put(o.outcomeName, val);
                    } else {
                        sampleSet.put(o.outcomeName, 1);

                    }
                }
            }
            for (Map.Entry<String, Integer> entry : s.stopOutcomeSet.entrySet()) {
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
}
