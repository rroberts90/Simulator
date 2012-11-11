Simulator
=========

Developed for the Statiscs 216 course at Ithaca College. Users specify a theoretical model or a set of data and take repeated samples. Its overall design is based on the simulation methods used in De Veaux, Velleman, and Bock's statistics textbooks.

  The Simulator allows users to construct sampling distributions, compute empirical p-values, test-hypotheses, and graph results. Populations may be theoretical distributions (Bionomial, Exponential, Normal, or Uniform), external datasets, or manually inputed values. Samples can either contain a fixed number of outcomes, or run until a set of user-defined conditions are met. 

 Users can measure response variables including mode, mean, sample size and number of successes. Successes are measured by user-defined conditional requirements (i.e a success is a trial where a die rolls a six three times in a row). Results can be graphed with Histograms, BarCharts, PieCharts, and DotPlots. Users can compare samples from two distinct populations or two groups within a single population. 

Developed in Java with the Netbeans GUI Builder. Dependencies include JFreeChart and Apache Commons Math.

Getting Started
=========
To run the Simulator from the command line, go to the dist folder and
type the following:

java -jar "Simulator.jar" 

Examples
=========
Family Planning:
http://www.ithaca.edu/faculty/aweinberg/Simulator/2-Family_Planning.pdf

Turtles:
http://www.ithaca.edu/faculty/aweinberg/Simulator/4-Turtles.pdf

