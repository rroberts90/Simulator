package simulator;

import java.util.ArrayList;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Richard 
 */
public class TwoPopTrial {

    ArrayList<Outcome> sample1;
    ArrayList<Outcome> sample2;

    double values1[];
    double values2[];
    String response;
    DescriptiveStatistics stats;
    int id;

    public TwoPopTrial(int id, ArrayList<Outcome> sample1, ArrayList<Outcome> sample2, String response) {
        this.sample1 = sample1;
        this.sample2 = sample2;
        this.response = response;
        this.id = id;
        
    }

    public TwoPopTrial(int id, double[] sample1, double[] sample2, String response) {
        this.values1 = sample1;
        this.values2 = sample2;
        this.response = response;
        this.id = id;

    }

    public TwoPopTrial(int id, ArrayList<Outcome> sample1, ArrayList<Outcome> sample2, String response, DescriptiveStatistics stats) {
        this.sample1 = sample1;
        this.sample2 = sample2;
        this.response = response;
        this.stats = stats;
        this.id = id;

    }

    public TwoPopTrial(int id, double[] sample1, double[] sample2, String response, DescriptiveStatistics stats) {
        this.values1 = sample1;
        this.values2 = sample2;
        this.response = response;
        this.stats = stats;
        this.id = id;

    }
}
