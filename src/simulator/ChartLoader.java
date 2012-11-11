/*
 * ChartLoader.java
 */
package simulator;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;

import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.*; //category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import org.apache.commons.math.stat.Frequency;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

/**
 *
 * @author Richard Roberts
 */


public class ChartLoader {

    private ChartType chartType;
    private int binCount;
    boolean defaultBins;
    private ArrayList<Double> binCutoffs;

    /**
     *  Constructor.  chartType is either Bar Chart, Dot Plot, Binned Dot Plot, Box Plot, Histogram, or Histogram with fit. 
     * These are taken directly from the setGraphType button.
     * @param chartType 
     */
    public ChartLoader(ChartType chartType) {
        this.chartType = chartType;
        binCount = 10;
        defaultBins = true;
    }

    /*  private HashSet<String> getFreqs(ArrayList<Trial> trials) {
    
    
    
    return check;
    }*/
    public int getBinCount() {
        return binCount;
    }

    public void setBinCount(int bins) {
        this.binCount = bins;
    }

    public void setBinCutoffs(ArrayList<Double> cutoffs) {
        this.binCutoffs = cutoffs;
    }

    /**
     * Creates a chart displaying the different outcomes in a population.
     * @param p
     * @return 
     */
    public ChartPanel loadChart(Population p) {
        ChartPanel chartPanel;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        java.util.Collections.sort(p.outcomes);


        for (Outcome o : p.outcomes) {
            dataset.addValue(o.count, o.outcomeName, "0");

        }
        System.out.println("bar chart loading ...");
        JFreeChart chart = ChartFactory.createBarChart(
                "Population 1", // chart title
                "Response", // domain axis label
                "Frequency", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        chart.setBackgroundPaint(Color.white);

        chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    /**
     * Creates a chart displaying the different outcomes in two populations.
     * @param p1
     * @param p2
     * @return 
     */
    public ChartPanel loadChart(Population p1, Population p2) {
        ChartPanel chartPanel;
        java.util.Collections.sort(p1.outcomes);
        java.util.Collections.sort(p2.outcomes);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Outcome o : p1.outcomes) {
            dataset.addValue(o.count, o.outcomeName, "Population 1");

        }
        for (Outcome o : p2.outcomes) {
            dataset.addValue(o.count, o.outcomeName, "Population 2");

        }
        System.out.println("bar chart loading ...");
        JFreeChart chart = ChartFactory.createBarChart(
                "Population 1  Population 2", // chart title
                "Response", // domain axis label
                "Frequency", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        chart.setBackgroundPaint(Color.white);


        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        this.styleBarChart(chart, 2);

        chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    /**
     * Creates a chart of chartType containing all the response variable data contained in trials. 
     * @param trials
     * @return 
     */
    public ChartPanel loadChart(ArrayList<Trial> trials) {
        JFreeChart chart = null;
        HashSet<String> check = new HashSet<String>();

        Frequency fList = new Frequency();
        for (Trial t : trials) {
            fList.addValue(t.response);
            check.add(t.response);
        }
        if (chartType == ChartType.BARCHART) {
           chart = createBarChart(check, fList);
        } else if (chartType == ChartType.PIECHART) {

            chart  = createPieChart(check, fList);

        } else if (chartType == ChartType.BOXPLOT) {

            ArrayList<Double> values = new ArrayList<Double>();//[fList.getUniqueCount()];
            int i = 0;
            for (Trial t : trials) {
                double r = Double.parseDouble(t.response);
                values.add(r);
            }
  
            chart = createBoxPlot(values);

        } else if (chartType== ChartType.HISTOGRAM) {
            System.out.println(trials.size());
            double[] values = new double[trials.size()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (Trial t : trials) {
                double r = Double.parseDouble(t.response);
                values[i] = r;
                stats.addValue(r);

            }
            
            chart = createHistogram(check, stats, values);
        } else if (chartType == ChartType.NORMAL_PROBABILITY_DIST) {
            Function2D normal = new NormalDistributionFunction2D(0.0, 1.0);
            XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, -5.0, 5.0, 100, "Normal");
            chart = ChartFactory.createXYLineChart(
                    "NPD",
                    "X",
                    "Y",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);

        } else if (chartType == ChartType.DOTPLOT) {
            chart = createDotPlot(check, fList, trials.size());
         
        } else if (chartType == ChartType.DOTPLOT_BINNED) {

            double[] statValues = new double[trials.size()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (Trial t : trials) {
                double r = 0;
                if (t.response.equalsIgnoreCase("true")) {
                    r = 1;
                } else if (t.response.equalsIgnoreCase("false")) {
                    r = 0;
                } else {
                    r = Double.parseDouble(t.response);
                }
                statValues[i] = r;
                stats.addValue(r);
            }

            chart = createBinnedDotplot(stats);

        } else if (chartType == ChartType.HISTOGRAM_FIT) {
            System.out.println(trials.size());
            double[] values = new double[trials.size()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (Trial t : trials) {
                double r = 0;
                if (t.response.equalsIgnoreCase("true")) {
                    r = 1;
                } else if (t.response.equalsIgnoreCase("false")) {
                    r = 0;
                } else {
                    r = Double.parseDouble(t.response);
                }
                values[i] = r;
                stats.addValue(r);
     
            }
            chart = createHistogramWithFit(check, stats, values);

        }

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setRangeZoomable(false);
            chartPanel.setDomainZoomable(false);
            chartPanel.setFillZoomRectangle(false);
            chartPanel.setMouseWheelEnabled(false);
            chartPanel.setPreferredSize(new Dimension(500, 270));

        Dimension d = new Dimension();
        return chartPanel;

    }

    /**
     * Saves chart to a file.
     * @param cp
     * @param file
     * @return 
     */
    public int saveChart(ChartPanel cp, File file) {
        try {
            ChartUtilities.saveChartAsJPEG(file, cp.getChart(), cp.getWidth(), cp.getHeight());
            return 1;
        } catch (Exception ie) {
            return 0;
        }
    }

    /**
     * Loads charts for two population trials. 
     * @param trials
     * @param diff
     * @return 
     */
    public ChartPanel loadChart(ArrayList<TwoPopTrial> trials, boolean diff) {
        JFreeChart chart = null;
        HashSet<String> check = new HashSet<String>();

        Frequency fList = new Frequency();
        for (TwoPopTrial t : trials) {
            fList.addValue(t.response);
            if (t.response.equals("0")) {
                System.out.println("ZERO ZERO ZERO");
            }
            check.add(t.response);
        }
        if (chartType == ChartType.BARCHART) {
            System.out.println("\n*** Loading Bar Chart ***");
            chart = createBarChart(check, fList);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();


            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

       

            this.styleBarChart(chart, 1);


        } else if (chartType == ChartType.PIECHART) {

            DefaultPieDataset dataset = new DefaultPieDataset();
            Iterator fItr = check.iterator();
            while (fItr.hasNext()) {

                String t = (String) fItr.next();
                dataset.setValue(t, fList.getCount(t));
            }

            chart = createPieChart(check, fList);


        } else if (chartType == ChartType.BOXPLOT) {
                  ArrayList<Double> values = new ArrayList<Double>();//[fList.getUniqueCount()];
            int i = 0;
            for (TwoPopTrial t : trials) {
                double r = Double.parseDouble(t.response);
                values.add(r);
            }
            chart = createBoxPlot(values);




        } else if (chartType == ChartType.HISTOGRAM) {
            System.out.println("****** loading histogram ******");
            Iterator fItr = check.iterator();
            SimpleHistogramDataset dataset = new SimpleHistogramDataset(0);
            //dataset.setType(HistogramType.RELATIVE_FREQUENCY);
            System.out.println(trials.size());
            double[] values = new double[trials.size()];//[fList.getUniqueCount()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (TwoPopTrial t : trials) {
                double r = Double.parseDouble(t.response);
                values[i] = r;
                stats.addValue(r);
       
            }

           chart = createHistogram(check, stats, values);
        }  else if (chartType.equals("Normal Probability Distribution")) {
            Function2D normal = new NormalDistributionFunction2D(0.0, 1.0);
            XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, -5.0, 5.0, 100, "Normal");
            chart = ChartFactory.createXYLineChart(
                    "NPD",
                    "X",
                    "Y",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);

        } else if (chartType == ChartType.DOTPLOT) {

              chart = createDotPlot(check,fList,  trials.size());


        } else if (chartType == ChartType.DOTPLOT_BINNED) {
            double[] statValues = new double[trials.size()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (TwoPopTrial t : trials) {
                double r = 0;
                if (t.response.equalsIgnoreCase("true")) {
                    r = 1;
                } else if (t.response.equalsIgnoreCase("false")) {
                    r = 0;
                } else {
                    r = Double.parseDouble(t.response);
                }
                statValues[i] = r;
                stats.addValue(r);
            }
            chart = createBinnedDotplot(stats);




        } else if (chartType == ChartType.HISTOGRAM_FIT) {
            System.out.println("****** loading histogram with fit ******");
            Iterator fItr = check.iterator();
            HistogramDataset dataset = new HistogramDataset();
            System.out.println(trials.size());
            double[] values = new double[trials.size()];//[fList.getUniqueCount()];
            int i = 0;
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (TwoPopTrial t : trials) {
                double r = 0;
                if (t.response.equalsIgnoreCase("true")) {
                    r = 1;
                } else if (t.response.equalsIgnoreCase("false")) {
                    r = 0;
                } else {
                    r = Double.parseDouble(t.response);
                }
                values[i] = r;
                stats.addValue(r);
        
            }
            chart = createHistogramWithFit(check, stats, values);
        }
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setRangeZoomable(false);
            chartPanel.setDomainZoomable(false);
            chartPanel.setFillZoomRectangle(false);
            chartPanel.setMouseWheelEnabled(false);
            chartPanel.setPreferredSize(new Dimension(500, 270));
        return chartPanel;

    }

    /**
     * Sets the look and feel of a chart.
     * @param chart
     * @param popNum 
     */
    public void styleBarChart(JFreeChart chart, int popNum) {
        System.out.println("\n*** Styling Bar Chart ***");
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setDrawBarOutline(false);
        barRenderer.setOutlinePaint(Color.blue);
        barRenderer.setOutlineStroke(new BasicStroke(1.1f, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_BEVEL));

        barRenderer.setSeriesPaint(0, Color.blue);

        

        if (popNum == 2) {
            barRenderer.setSeriesPaint(1, Color.blue);
        }        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelPaint(Color.white);
        domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 12));
        domainAxis.setLabelPaint(Color.white);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelPaint(Color.WHITE);
        rangeAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 12));
        rangeAxis.setLabelPaint(Color.white);
    }


private JFreeChart createBarChart(HashSet<String> check, Frequency fList){
   
           System.out.println("\n*** Loading Bar Chart ***");
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            Iterator fItr = check.iterator();
            ArrayList<String> sorted = new ArrayList<String>();
            while (fItr.hasNext()) {
                String tmp = (String) fItr.next();
                sorted.add(tmp);

            }
            java.util.Collections.sort(sorted);


            for (String s : sorted) {
                dataset.addValue(fList.getCount(s), s, "0");
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Bar Chart", // chart title
                    "Response", // domain axis label
                    "Frequency", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips?
                    false // URLs?
                    );

            chart.setBackgroundPaint(Color.white);


            CategoryPlot plot = (CategoryPlot) chart.getPlot();


            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            this.styleBarChart(chart, 1);
            return chart;
}

    private JFreeChart createPieChart(HashSet<String> check, Frequency fList){
               DefaultPieDataset dataset = new DefaultPieDataset();
            Iterator fItr = check.iterator();
            while (fItr.hasNext()) {

                String t = (String) fItr.next();
                dataset.setValue(t, fList.getCount(t));
            }

            JFreeChart chart = ChartFactory.createPieChart(
                    "Pie Chart Demo 1", // chart title
                    dataset, // data
                    true, // include legend
                    true,
                    false);

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionOutlinesVisible(false);
            plot.setNoDataMessage("No data available");
            return chart;
    }
    
    private JFreeChart createBoxPlot(ArrayList<Double> values){
                  DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

            dataset.add(values, "0 ", "0");
            BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
            renderer.setFillBox(false);
            renderer.setMeanVisible(false);
            CategoryAxis xAxis = new CategoryAxis("Type");
            xAxis.setLowerMargin(.3);
            xAxis.setUpperMargin(.3);


            NumberAxis yAxis = new NumberAxis("Outcome");
            CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

            JFreeChart chart = new JFreeChart(
                    "Box Plot",
                    new Font("SansSerif", Font.BOLD, 14),
                    plot,
                    true);
            
            return chart;
    }
    
    public JFreeChart createHistogram(HashSet<String> check, DescriptiveStatistics stats, double[] values){
                    System.out.println("****** loading histogram ******");
            Iterator fItr = check.iterator();
            SimpleHistogramDataset dataset = new SimpleHistogramDataset(0);


            String plotTitle = "Histogram";
            String xaxis = "number";
            String yaxis = "value";
            double binWidth = 0;
            System.out.println("Dataset size: " + stats.getValues().length);

            dataset.setAdjustForBinSize(false);
            if (defaultBins == true) {
                binWidth = 3.49 * stats.getStandardDeviation() / Math.pow(values.length, .3333333);
                System.out.println("bin width: " + binWidth);
                if (binWidth != 0) {

                    double lower, upper;
                    lower = 0;
                    upper = 0;
                    lower = stats.getMin();
                    upper = lower + binWidth;
                    dataset.addBin(new SimpleHistogramBin(lower - binWidth, lower, true, false));
                    
                    dataset.addBin(new SimpleHistogramBin(lower, upper, true, false));
                    for (double val : stats.getSortedValues()) {
                        System.out.println("Val: " + val);
                        if (val < upper) 
                            dataset.addObservation(val);
                        else {
                            lower =  upper;
                            System.out.println("Lower Bound: " + lower);
                            upper = Math.max(lower  + binWidth, val+ .000001);
                            System.out.println("Upper bound: " + upper);
                            dataset.addBin(new SimpleHistogramBin(lower, upper, true, false));
                            dataset.addObservation(val);

                        }
                    }
                    lower  =  upper;
                    upper = lower + binWidth;

                    dataset.addBin(new SimpleHistogramBin(lower, upper, true, false));
                } else {
                    dataset.addBin(new SimpleHistogramBin(stats.getMean() - 1, stats.getMean() + 1, true, false));
                    for (double val : stats.getValues()) {
                        dataset.addObservation(val);
                    }

                }
            } else if (binCutoffs == null) {
                binWidth = (stats.getMax() - stats.getMin()) / binCount;
            } else {
                for (int i = 0; i < binCutoffs.size() - 1; i++) {
                    dataset.addBin(new SimpleHistogramBin(binCutoffs.get(i), binCutoffs.get(i + 1)));
                }

            }
        
            System.out.println("number of values: " + dataset.getItemCount(0));

            PlotOrientation orientation = PlotOrientation.VERTICAL;
            boolean show = false;
            boolean toolTips = false;
            boolean urls = false;
            JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                    dataset, orientation, show, toolTips, urls);
            
            return chart;
    }
    
    
private JFreeChart createDotPlot(HashSet<String> check, Frequency fList, int numTrials){
    
       DefaultXYDataset dataset = new DefaultXYDataset();
            double[][] values = new double[2][numTrials];
            Iterator fItr = check.iterator();
            int pointCount = 0;
            double maxX = 0;
            while (fItr.hasNext()) {
                String tmp = (String) fItr.next();
                double xCo = Double.parseDouble(tmp) ;
                if (xCo > maxX)
                    maxX = xCo;
                int count = (int) fList.getCount(tmp);

                System.out.println("Count: " + count);
                System.out.println("X coordinate: " + xCo);
                System.out.println("Point Count: " + pointCount);
                for (int i = 1; i <= count; i++) {
                    values[0][pointCount] = xCo;
                    values[1][pointCount] = i;
                    pointCount++;
                }

     
            }

            dataset.addSeries("0", values);
            System.out.println("dataset size: " + dataset.getItemCount(0));


            JFreeChart chart = ChartFactory.createScatterPlot(
                    "Dot Plot",
                    // chart title
                    "Response",
                    "Frequedncy", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips?
                    false // URLs?
                    );
            chart.setBackgroundPaint(Color.white);


            XYPlot plot = (XYPlot) chart.getPlot();

            GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                    0.0f, 0.0f, new Color(0, 0, 64));
            GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                    0.0f, 0.0f, new Color(0, 64, 0));
            GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                    0.0f, 0.0f, new Color(64, 0, 0));


            ValueAxis domainAxis = plot.getDomainAxis();
            ValueAxis rangeAxis = plot.getRangeAxis();


            domainAxis.setLowerMargin(.2);
            domainAxis.setUpperMargin(.2);



            return chart;

}

private JFreeChart createHistogramWithFit(HashSet<String> check, DescriptiveStatistics stats, double[] values){
                System.out.println("****** loading histogram with fit ******");
            Iterator fItr = check.iterator();
            SimpleHistogramDataset dataset = new SimpleHistogramDataset(0);


            String plotTitle = "Histogram With Fit";
            String xaxis = "number";
            String yaxis = "value";
            double binWidth = 0;
            System.out.println("Dataset size: " + stats.getValues().length);

            dataset.setAdjustForBinSize(false);
            if (defaultBins == true) {
                binWidth = 3.49 * stats.getStandardDeviation() / Math.pow(values.length, .3333333);
                System.out.println("bin width: " + binWidth);
                if (binWidth != 0) {


                    double lb = stats.getMin();
                    dataset.addBin(new SimpleHistogramBin(lb - binWidth, lb, true, false));

                    dataset.addBin(new SimpleHistogramBin(lb, lb + binWidth, true, false));
                    for (double val : stats.getValues()) {
                        System.out.println("Val: " + val);
                        if (val < lb + binWidth) {
                            dataset.addObservation(val);
                        } else {
                            lb = lb + binWidth;
                            dataset.addBin(new SimpleHistogramBin(lb, lb + binWidth, true, false));
                            dataset.addObservation(val);

                        }
                    }
                    lb = lb + binWidth;

                    dataset.addBin(new SimpleHistogramBin(lb, lb + binWidth, true, false));
                } else {
                    dataset.addBin(new SimpleHistogramBin(stats.getMean() - 1, stats.getMean() + 1, true, false));
                    for (double val : stats.getValues()) {
                        dataset.addObservation(val);
                    }

                }
            } else if (binCutoffs == null) {
                binWidth = (stats.getMax() - stats.getMin()) / binCount;
            } else {
                
                for (int i = 0; i < binCutoffs.size() - 1; i++) {
                    dataset.addBin(new SimpleHistogramBin(binCutoffs.get(i), binCutoffs.get(i + 1)));
                }

            }
    
            System.out.println("number of values: " + dataset.getItemCount(0));

            PlotOrientation orientation = PlotOrientation.VERTICAL;

            Function2D normal = new NormalDistributionFunction2D(stats.getMean(), stats.getStandardDeviation());
            XYDataset lineDataset = DatasetUtilities.sampleFunction2D(normal, stats.getMin() - 2, stats.getMax() + 2, 100, "Normal");

            boolean show = false;
            boolean toolTips = false;
            boolean urls = false;
            JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                    null, orientation, show, toolTips, urls);
            XYPlot plot = chart.getXYPlot();
            XYLineAndShapeRenderer xyLineRenderer = new XYLineAndShapeRenderer();
            xyLineRenderer.setSeriesShape(0, new Line2D.Double(0.0, 0.0, 0.0, 0.0));
            xyLineRenderer.setSeriesStroke(0, new BasicStroke(4.5f));
            plot.setDataset(0, dataset);
            plot.setDataset(1, lineDataset);
            plot.setRenderer(1, xyLineRenderer);
            plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
            
            return chart;
}


private JFreeChart createBinnedDotplot(DescriptiveStatistics stats){
            double[] trialValues = stats.getSortedValues();

            DefaultXYDataset dataset = new DefaultXYDataset();
            double[][] values = new double[2][trialValues.length];
            double binWidth = 3.49 * stats.getStandardDeviation() / Math.pow(trialValues.length, .3333333);
            System.out.println("bin width: " + binWidth);

            double lb = stats.getMin();
            int pointCount = 0;
            int freq  = 1;
            double ub = lb + binWidth;
            double displayNum = lb + (binWidth / 2);
            
            for(int j = 0; j < trialValues.length; j++) {
                double xCo = trialValues[j];
                
                System.out.println("X coordinate: " + xCo);
                System.out.println("Display Num: " + displayNum); 
                System.out.println();
                if (xCo < ub) {
                    
                        values[0][pointCount] = displayNum;
                        freq++;
                        values[1][pointCount] = freq;
                        pointCount++;
                    }
                else {
                    lb = ub;
                    ub = lb + binWidth;
                    freq = 1;
                    displayNum = displayNum + (binWidth);
                    values[0][pointCount] = displayNum;
                    values[1][pointCount] = freq;
                    pointCount++;
                    
                }


              }

            dataset.addSeries("0", values);
            System.out.println("dataset size: " + dataset.getItemCount(0));
            JFreeChart chart = ChartFactory.createScatterPlot(
                    "Dot Plot",
                    // chart title
                    "Response",
                    "Frequedncy", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips?
                    false // URLs?
                    );
            chart.setBackgroundPaint(Color.white);


            XYPlot plot = (XYPlot) chart.getPlot();

            GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                    0.0f, 0.0f, new Color(0, 0, 64));
            GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
                    0.0f, 0.0f, new Color(0, 64, 0));
            GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                    0.0f, 0.0f, new Color(64, 0, 0));
        

            ValueAxis domainAxis = plot.getDomainAxis();
            ValueAxis rangeAxis = plot.getRangeAxis();


            domainAxis.setLowerMargin(.2);
            domainAxis.setUpperMargin(.2);
            return chart;

}
}
