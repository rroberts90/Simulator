package simulator;

/**
 * A Simple class to describe a single outcome of a trial.
 * @author Richard Roberts
 */
public class Outcome implements Comparable{
    String outcomeName;
    int count;
    double prb;
    double randNum; // only used in returning value to trial
    int occurCount; // used to count distinct outcomes
    
    public Outcome(){
        this.occurCount = 0;
        this.outcomeName = null;
        this.prb = 0;
        this.randNum = 0;
    }
    
    // copy constructor.
    public Outcome(Outcome c){
        this.outcomeName = c.outcomeName;
        this.count = c.count;
        this.prb = c.prb;
        this.randNum = c.randNum;
        this.occurCount = c.occurCount;
        if (this.count > 1){
        System.out.println("GOTCHA New Outcome count: " + this.count);
        }
    }
    public Outcome(String oN, int cnt, double prb, double randNum, int oC){
        this.outcomeName = oN;
        this.count = cnt;
        this.prb = prb;
        this.randNum = randNum;
        this.occurCount = oC;
        
    }
    public Outcome(String oN, int cnt){
         this.outcomeName = oN;
        this.count = cnt;
    }
public Outcome(String oN, int cnt, double prb){
         this.outcomeName = oN;
        this.count = cnt;
        this.prb = prb;
    }

    @Override
    public int compareTo(Object t) {
        Outcome o = (Outcome) t;
        return this.outcomeName.compareTo(o.outcomeName);

    }
}
