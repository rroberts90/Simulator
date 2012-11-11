package simulator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.math.*;
import org.apache.commons.math.distribution.*;
import org.apache.commons.math.distribution.ExponentialDistributionImpl;
import org.apache.commons.math.distribution.NormalDistributionImpl;
import org.apache.commons.math.random.UniformRandomGenerator;
import org.apache.commons.math.random.RandomGenerator;
import org.apache.commons.math.distribution.ContinuousDistribution;
import java.util.Random;

/**
 *
 * @author Richard 
 */
public class Population {

    /**
     * Precondition: len is equal to the sum of all the counts in the outcomeList
     * @param outcomeList
     * @param len
     * @return 
     */

    Random rand;
    private int popType;
    public ArrayList<Outcome> outcomeDrawList;
    public ArrayList<Outcome> outcomes;
    private ArrayList<Outcome> tmpList;

    /**
     * Copy constructor - copies population p
     * @param p 
     */
    public Population(Population p) {
        this.rand = new Random();

        this.tmpList = new ArrayList<Outcome>();
        this.outcomeDrawList = new ArrayList<Outcome>();
        this.outcomes = new ArrayList<Outcome>();

        for (Outcome o : p.outcomes) {
            this.outcomes.add(new Outcome(o));

        }

        for (Outcome o : p.outcomeDrawList) {
            this.outcomeDrawList.add(new Outcome(o));
        }
    }

    /**
     *  Constructor that loads population from a list of outcomes and ranges 
     * @param outcomeList 
     */
    public Population(ArrayList<Outcome> outcomeList) {
        tmpList = new ArrayList<Outcome>();
        int randNdx = 1;
        rand = new Random();
        outcomes = outcomeList;
        popType = 0;
        int len = 0;
        for (Outcome o : outcomes) {
            len += o.count;
        }

        outcomeDrawList = new ArrayList<Outcome>();
        for (int i = 0; i < outcomes.size(); i++) {
            Outcome o = outcomes.get(i);
            for (int j = 0; j < o.count; j++) {
                Outcome drawOutcome = new Outcome(o);
                drawOutcome.randNum = randNdx;
                randNdx +=1;
                outcomeDrawList.add(drawOutcome);
            }
             o.prb = (double) o.count / len;
        }

    }

    /**
     * Population Constructor used by the 2 group functions.  
     * @param outcomeDrawList
     * @param fake 
     */
    public Population(ArrayList<Outcome> outcomeDrawList, boolean fake) {
       tmpList = new ArrayList<Outcome>();

        rand = new Random();
        this.outcomeDrawList = outcomeDrawList;
        popType = 0;
        int len = 0;

    }

    /**
     *  Loads a population from a list of integers in an external file.
     * @param fileName 
     */
    public Population(File fileName) {
        tmpList = new ArrayList<Outcome>();

        rand = new Random();
        popType = 0;
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            outcomes = new ArrayList<Outcome>();
            
            Outcome o;
            int len = 0;
            int randNdx = 1;
            String[] spliter;
            while ((line = br.readLine()) != null) {
                
                if (line.trim().length() > 0) {
                    spliter = line.split("\\s*,\\s*");
                    for (int i = 0; i < spliter.length; i++) {
                        o = new Outcome();
                        o.outcomeName = spliter[i];
                        o.count = 1;
                        o.randNum = randNdx;
                        outcomes.add(o);
                        randNdx++;
                    }
                }

            }
            
            outcomeDrawList = new ArrayList<Outcome>();
            for (int i = 0; i < outcomes.size(); i++) {
                Outcome num = new Outcome(outcomes.get(i));
                outcomeDrawList.add(num);
                System.out.println("why? : " + num.outcomeName);
            }


        } catch (IOException e) {
            System.out.println("Error reading in file.  Trigger popup exception here.");


        }
    }

    public Population(ArrayList<Outcome> outcomeList, int placeholder) {
        tmpList = new ArrayList<Outcome>();

        rand = new Random();
        outcomes = outcomeList;
        popType = 0;
        int len = 0;
        for (Outcome o : outcomes) {
            len += o.count;
        }

        outcomeDrawList = new ArrayList<Outcome>();
        int i = 0;
        for (Outcome o : outcomeList) {
                outcomeDrawList.add(o);
            
        }

    }

    /**
     *  Samples sampleSize observations from the population, with or without replacement.  
     * @param sampleSize
     * @param replace
     * @return 
     */
    public ArrayList<Outcome> sample(int sampleSize, boolean replace) {
        ArrayList<Outcome> sample = new ArrayList<Outcome>();       
            Outcome o;
            if (replace) {
                for (int i = 0; i < sampleSize; i++) {
                    int index = rand.nextInt(outcomeDrawList.size());                    
                    o = outcomeDrawList.get(index);
                    sample.add(o);
                }
            } else {
                this.shuffleOutcomes();
                for (int i = 0; i < Math.min(sampleSize, outcomeDrawList.size()); i++) {
                    o = outcomeDrawList.get(i);
                    sample.add(o);
                }
            }
            return sample;
        
        }
    
    /**
     * Method for sampling from a population during a variable trial.  A single 
     * outcome is sampled with or without replacement.  
     * Note: in trials without replacement,endSample must be called at the end 
     * of each trial to ensure that the outcomeDrawList is repopulated.
     * @param replace
     * @return 
     */
    public Outcome sample(boolean replace) {
        Outcome sample = new Outcome();
        int index;
        if (outcomeDrawList.size() > 0) {
            index = rand.nextInt(outcomeDrawList.size());

            sample = outcomeDrawList.get(index);
            

            if (!replace) {
                tmpList.add(outcomeDrawList.remove(index));
            }
        } else {
            return null;
        }
        System.out.println("Sample name?: " + sample.outcomeName );
        return sample;
    }

// removes any values from the tmpList
   public void endSample() {
        while (!tmpList.isEmpty()) {
            outcomeDrawList.add(tmpList.remove(0));
        }
        tmpList = new ArrayList<Outcome>();
    }
    
       
    private void shuffleOutcomes() {
        java.util.Collections.shuffle(this.outcomeDrawList);

    }
}
