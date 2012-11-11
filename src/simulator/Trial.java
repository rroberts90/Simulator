/*
 * Trial.java
 */
package simulator;

import java.util.ArrayList;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Richard
 */
public class Trial {
    int id;
    ArrayList<Outcome> outcomes;
    double[] randoms;
    DescriptiveStatistics stats;
    String response;
    public Trial(int id, ArrayList<Outcome> outcomes, double[] randoms, String response,  DescriptiveStatistics stats){
        this.id = id;
        this.outcomes = outcomes;
        this.randoms = randoms;
        this.response = response;
        this.stats = stats;
    }
}
