
/*
 * MainFrame.java
 *
 * Created on Aug 15, 2011, 11:46:24 PM
 */
package simulator;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import org.jfree.chart.ChartPanel;
import org.jdesktop.layout.GroupLayout;

/**
 *
 * @author Richard
 */
public class MainFrame extends javax.swing.JFrame {

    // 
    final int CHART_HEIGHT = 600;
    final int CHART_WIDTH = 600;
    Population sl;
    ArrayList<Outcome> dataOutcomeList = new ArrayList<Outcome>();
    ArrayList<Outcome> dataOutcomeList1 = new ArrayList<Outcome>();
    ArrayList<Outcome> dataOutcomeList2 = new ArrayList<Outcome>();
    ArrayList<FieldStruct> dataOutcomeFields1 = new ArrayList<FieldStruct>(); // generic package for list of outcome fields of indeterminate length
    ArrayList<FieldStruct> dataOutcomeFields2 = new ArrayList<FieldStruct>(); // generic package for list of outcome fields of indeterminate length
    HashMap<String, Integer> trialStopCondSet = new HashMap<String, Integer>();
    ArrayList<Outcome> trialStopCondList = new ArrayList<Outcome>();
    ArrayList<Outcome> varSuccessList = new ArrayList<Outcome>();
    HashMap<String, Integer> varSuccessCondSet = new HashMap<String, Integer>();
    ArrayList<FieldStruct> dataOutcomeFields = new ArrayList<FieldStruct>(); // generic package for list of outcome fields of indeterminate length
    ArrayList<FieldStruct> trialInOrderOutcomeFields = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> trialInSetOutcomeFields = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInOrderOutcomeFields = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInSetOutcomeFields = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> trialInOrderOutcomeFields1 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> trialInSetOutcomeFields1 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInOrderOutcomeFields1 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInSetOutcomeFields1 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> trialInOrderOutcomeFields2 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> trialInSetOutcomeFields2 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInOrderOutcomeFields2 = new ArrayList<FieldStruct>();
    ArrayList<FieldStruct> varInSetOutcomeFields2 = new ArrayList<FieldStruct>();
    File external;
    TrialRunner t;
    TwoPopTrialRunner tp;
    FieldStruct dataZero = new FieldStruct();
    FieldStruct trialInOrderZero = new FieldStruct();
    FieldStruct trialInSetZero = new FieldStruct();
    FieldStruct varInOrderZero = new FieldStruct();
    FieldStruct varInSetZero = new FieldStruct();
    FieldStruct dataZero1 = new FieldStruct();
    FieldStruct trialInOrderZero1 = new FieldStruct();
    FieldStruct trialInSetZero1 = new FieldStruct();
    FieldStruct varInOrderZero1 = new FieldStruct();
    FieldStruct varInSetZero1 = new FieldStruct();
    FieldStruct dataZero2 = new FieldStruct();
    FieldStruct trialInOrderZero2 = new FieldStruct();
    FieldStruct trialInSetZero2 = new FieldStruct();
    FieldStruct varInOrderZero2 = new FieldStruct();
    FieldStruct varInSetZero2 = new FieldStruct();
    int binCount = 10;
    ArrayList<Double> binVals;
    ChartType currentChartType;

    public MainFrame() {
        currentChartType = ChartType.BARCHART;
        t = new TrialRunner();
        initComponents();
        restoreDefaults();
        binButton.setVisible(false);
        tabsFrame.remove(dataTwoPopPanel);
        tabsFrame.remove(twoPopTrialPanel);
        tabsFrame.remove(twoPopVarPanel);
        dataExternalFilePanel.setLocation(dataButtonPanel.getX(), dataButtonPanel.getY() + 5 + dataButtonPanel.getHeight());
        trialFixedOutcomePanel.setLocation(trialButtonPanel.getX(), trialButtonPanel.getY() + 5 + trialButtonPanel.getHeight());



        // Initializing lists
        trialInSetZero = new FieldStruct();
        trialInSetZero.one = trialOutcomeValueTextField1;
        trialInSetZero.two = trialNumTimesTextField1;
        trialInSetOutcomeFields = new ArrayList<FieldStruct>();

        trialInSetOutcomeFields.add(trialInSetZero);
        trialInOrderZero = new FieldStruct();
        trialInOrderZero.one = trialOutcomeTextField1;
        trialInOrderZero.label = trialOrderValueLabel1;
        trialInOrderOutcomeFields = new ArrayList<FieldStruct>();
        trialInOrderOutcomeFields.add(trialInOrderZero);


        varInSetZero = new FieldStruct();
        varInSetZero.one = trialOutcomeValueTextField2;
        varInSetZero.two = trialNumTimesTextField2;
        varInSetOutcomeFields = new ArrayList<FieldStruct>();
        varInSetOutcomeFields.add(varInSetZero);

        varInOrderZero = new FieldStruct();
        varInOrderZero.one = outcomeTextField1v2;
        varInOrderZero.label = orderValueLabel1v2;
        varInOrderOutcomeFields = new ArrayList<FieldStruct>();
        varInOrderOutcomeFields.add(varInOrderZero);


        //  two pop stuff  THIS ALL NEEDS TO BE RENAMED
        trialInSetZero1 = new FieldStruct();
        trialInSetZero1.one = trialOutcomeValueTextField6;
        trialInSetZero1.two = trialNumTimesTextField6;
        trialInSetOutcomeFields1 = new ArrayList<FieldStruct>();
        trialInSetOutcomeFields1.add(trialInSetZero1);

        trialInOrderZero1 = new FieldStruct();
        trialInOrderZero1.one = trialOutcomeTextField4;
        trialInOrderZero1.label = trialOrderValueLabel4;
        trialInOrderOutcomeFields1 = new ArrayList<FieldStruct>();
        trialInOrderOutcomeFields1.add(trialInOrderZero1);


        varInSetZero1 = new FieldStruct();
        varInSetZero1.one = trialOutcomeValueTextField2;
        varInSetZero1.two = trialNumTimesTextField2;
        varInSetOutcomeFields1 = new ArrayList<FieldStruct>();
        varInSetOutcomeFields1.add(varInSetZero1);

        varInOrderZero1 = new FieldStruct();
        varInOrderZero1.one = outcomeTextField1v2;
        varInOrderZero1.label = orderValueLabel1v2;
        varInOrderOutcomeFields1 = new ArrayList<FieldStruct>();
        varInOrderOutcomeFields1.add(varInOrderZero1);

        trialInSetZero2 = new FieldStruct();
        trialInSetZero2.one = trialOutcomeValueTextField8;
        trialInSetZero2.two = trialNumTimesTextField8;
        trialInSetOutcomeFields2 = new ArrayList<FieldStruct>();
        trialInSetOutcomeFields2.add(trialInSetZero2);

        trialInOrderZero2 = new FieldStruct();
        trialInOrderZero2.one = trialOutcomeTextField5;
        trialInOrderZero2.label = trialOrderValueLabel5;
        trialInOrderOutcomeFields2 = new ArrayList<FieldStruct>();
        trialInOrderOutcomeFields2.add(trialInOrderZero2);


        varInSetZero2 = new FieldStruct();
        varInSetZero2.one = trialOutcomeValueTextField2;
        varInSetZero2.two = trialNumTimesTextField2;
        varInSetOutcomeFields2 = new ArrayList<FieldStruct>();
        varInSetOutcomeFields2.add(varInSetZero2);

        varInOrderZero2 = new FieldStruct();
        varInOrderZero2.one = outcomeTextField1v2;
        varInOrderZero2.label = orderValueLabel1v2;
        varInOrderOutcomeFields2 = new ArrayList<FieldStruct>();
        varInOrderOutcomeFields2.add(varInOrderZero2);

        categoryPanel.setVisible(false);
        // onePopButton1.setSelected(true);
        trialFixedOutcomePanel.setVisible(false);
        System.out.println("initialized all variables");
        progressPanel.setVisible(false);
        jList1.setVisible(false);
        jScrollPane4.setVisible(false);

        trialFixedOutcomePanel4.setVisible(false);
        trialFixedOutcomePanel3.setVisible(false);

        trialStopConditionPanel3.setVisible(false);
        trialStopConditionPanel4.setVisible(false);

        // Set Default Values for text fields=
        numOutcomeField.setValue(1);
        numOutcomeField1.setValue(1);
        numOutcomeField2.setValue(1);

        numObservationTextField.setValue(1);
        trialNumObservationTextField1.setValue(1);
        trialNumObservationTextField2.setValue(1);
        numDistinctOutcomeTextField.setValue(1);
        numDistinctOutcomeTextField5.setValue(1);


        trialSequenceField1.setValue(1);
        trialSetField2.setValue(1);
        trialInOrderField2.setValue(1);
        numTrialField.setValue(1);
        trialNumOutcomesTextField.setValue(1);
        variableNumOutcomesTextField.setValue(1);


        List<String> spinnerList = new ArrayList<String>();
        spinnerList.add("Bar Chart");
        spinnerList.add("Dot Plot");
        spinnerList.add("Histogram");
        spinnerList.add("Histogram with fit");
        spinnerList.add("Box Plot");
        spinnerList.add("Pie Chart");
        SpinnerModel snd = new SpinnerListModel(spinnerList);


        graphTypeButton.setModel(snd);
        graphTypeButton.setVisible(false);

        trialStopConditionPanel3.setVisible(false);
        trialStopConditionPanel4.setVisible(false);
        trialFixedOutcomePanel4.setVisible(false);
        trialFixedOutcomePanel3.setVisible(false);
        graphTypePopUp.setVisible(false);
        setBinSpinner.setValue(1);
        setBinSpinner.setVisible(false);
        setGraphTypeButton2.setComponentPopupMenu(graphTypePopUp);

        binButton.setComponentPopupMenu(setBinPopup);

        dataListScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dataListScrollPane1.setMinimumSize(new Dimension(480, 210));
        dataListScrollPane1.setMaximumSize(new Dimension(485, 211));
        dataListScrollPane1.setAutoscrolls(true);
        dataListEnclosePanel1.setMinimumSize(new Dimension(480, 210));

        dataListScrollPane1.setMaximumSize(new Dimension(485, 211));

        dataListEnclosePanel1.setMaximumSize(new Dimension(485, 212));

        diffPanel.setVisible(false);

        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sampleButtonGroup = new javax.swing.ButtonGroup();
        sampleTypeButtonGroup = new javax.swing.ButtonGroup();
        trialButtonGroup = new javax.swing.ButtonGroup();
        variableButtonGroup = new javax.swing.ButtonGroup();
        successButtonGroup = new javax.swing.ButtonGroup();
        populationButtonGroup = new javax.swing.ButtonGroup();
        stopConditionButtonGroup = new javax.swing.ButtonGroup();
        distributionButtonGroup = new javax.swing.ButtonGroup();
        chartFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        dataPopupFrame = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        populationButtonGroup2 = new javax.swing.ButtonGroup();
        trialTwoPopPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nextButton5 = new javax.swing.JButton();
        trialButtonPanel1 = new javax.swing.JPanel();
        trialTypeButton1 = new javax.swing.JLabel();
        withoutReplacementButton1 = new javax.swing.JRadioButton();
        sampleTypeLabel1 = new javax.swing.JLabel();
        fixedOutcomeButton1 = new javax.swing.JRadioButton();
        withReplacementButton1 = new javax.swing.JRadioButton();
        conditionButton1 = new javax.swing.JRadioButton();
        trialButtonPanel2 = new javax.swing.JPanel();
        trialTypeButton2 = new javax.swing.JLabel();
        withoutReplacementButton2 = new javax.swing.JRadioButton();
        sampleTypeLabel2 = new javax.swing.JLabel();
        fixedOutcomeButton2 = new javax.swing.JRadioButton();
        withReplacementButton2 = new javax.swing.JRadioButton();
        conditionButton2 = new javax.swing.JRadioButton();
        trialStopConditionPanel2 = new javax.swing.JPanel();
        trialAllOutcomesInSetButton2 = new javax.swing.JRadioButton();
        stopConditionLabel2 = new javax.swing.JLabel();
        trialSomeOutcomesInSetButton2 = new javax.swing.JRadioButton();
        trialSomeOutcomesInOrderButton2 = new javax.swing.JRadioButton();
        trialVariableOutcomesInSetPanel2 = new javax.swing.JPanel();
        trialNumTimesLabel2 = new javax.swing.JLabel();
        numDistinctOutcomeLabel3 = new javax.swing.JLabel();
        numDistinctOutcomeTextField3 = new javax.swing.JTextField();
        trialOutcomeLabel5 = new javax.swing.JLabel();
        trialOutcomeValueTextField4 = new javax.swing.JTextField();
        trialNumTimesTextField4 = new javax.swing.JTextField();
        twoPopTrialDistinctUpdateButton1 = new javax.swing.JButton();
        trialVariableOutcomesInOrderPanel2 = new javax.swing.JPanel();
        trialNumOutcomesLabel2 = new javax.swing.JLabel();
        trialNumOutcomesTextField2 = new javax.swing.JTextField();
        trialOrderLabel2 = new javax.swing.JLabel();
        trialOutcomeLabel6 = new javax.swing.JLabel();
        trialOutcomeTextField3 = new javax.swing.JTextField();
        trialOrderValueLabel3 = new javax.swing.JLabel();
        twoPopTrialSequenceUpdateButton2 = new javax.swing.JButton();
        trialStopConditionPanel1 = new javax.swing.JPanel();
        trialAllOutcomesInSetButton1 = new javax.swing.JRadioButton();
        stopConditionLabel1 = new javax.swing.JLabel();
        trialSomeOutcomesInSetButton1 = new javax.swing.JRadioButton();
        trialSomeOutcomesInOrderButton1 = new javax.swing.JRadioButton();
        trialVariableOutcomesInSetPanel1 = new javax.swing.JPanel();
        trialNumTimesLabel1 = new javax.swing.JLabel();
        numDistinctOutcomeLabel2 = new javax.swing.JLabel();
        numDistinctOutcomeTextField2 = new javax.swing.JTextField();
        trialOutcomeLabel3 = new javax.swing.JLabel();
        trialOutcomeValueTextField3 = new javax.swing.JTextField();
        trialNumTimesTextField3 = new javax.swing.JTextField();
        twoPopTrialDistinctUpdateButton2 = new javax.swing.JButton();
        trialVariableOutcomesInOrderPanel1 = new javax.swing.JPanel();
        trialNumOutcomesLabel1 = new javax.swing.JLabel();
        trialNumOutcomesTextField1 = new javax.swing.JTextField();
        trialOrderLabel1 = new javax.swing.JLabel();
        trialOutcomeLabel4 = new javax.swing.JLabel();
        trialOutcomeTextField2 = new javax.swing.JTextField();
        trialOrderValueLabel2 = new javax.swing.JLabel();
        twoPopTrialSequenceUpdateButton1 = new javax.swing.JButton();
        trialFixedOutcomePanel1 = new javax.swing.JPanel();
        numObservationLabel1 = new javax.swing.JLabel();
        numObservationTextField1 = new javax.swing.JTextField();
        trialFixedOutcomePanel2 = new javax.swing.JPanel();
        numObservationLabel2 = new javax.swing.JLabel();
        numObservationTextField2 = new javax.swing.JTextField();
        variableTwoPopPanel = new javax.swing.JPanel();
        variableButtons1 = new javax.swing.JPanel();
        variableMeasureLabel1 = new javax.swing.JLabel();
        varDiffMeanButton = new javax.swing.JRadioButton();
        varPrcSuccessInEachButton = new javax.swing.JRadioButton();
        varDiffPrcSuccessButton = new javax.swing.JRadioButton();
        varMeanInEachButton = new javax.swing.JRadioButton();
        variableSuccessConditionPanel1 = new javax.swing.JPanel();
        someOutcomesInSetButton1 = new javax.swing.JRadioButton();
        allOutcomesInSetButton1 = new javax.swing.JRadioButton();
        someOutcomesInOrderButton1 = new javax.swing.JRadioButton();
        successConditionLabel1 = new javax.swing.JLabel();
        variableOutcomesInSetPanel1 = new javax.swing.JPanel();
        variableNumTimesLabel2 = new javax.swing.JLabel();
        numDistinctOutcomeLabel4 = new javax.swing.JLabel();
        numDistinctOutcomeTextField4 = new javax.swing.JTextField();
        trialOutcomeLabel7 = new javax.swing.JLabel();
        trialOutcomeValueTextField5 = new javax.swing.JTextField();
        trialNumTimesTextField5 = new javax.swing.JTextField();
        nextButton6 = new javax.swing.JButton();
        variableOutcomesInOrderPanel1 = new javax.swing.JPanel();
        orderValueLabel1v3 = new javax.swing.JLabel();
        variableOutcomeLabel1 = new javax.swing.JLabel();
        variableOrderLabel1 = new javax.swing.JLabel();
        variablenumOutcomesLabel1 = new javax.swing.JLabel();
        outcomeTextField1v3 = new javax.swing.JTextField();
        variableNumOutcomesTextField1 = new javax.swing.JTextField();
        variableSuccessConditionPanel2 = new javax.swing.JPanel();
        someOutcomesInSetButton2 = new javax.swing.JRadioButton();
        allOutcomesInSetButton2 = new javax.swing.JRadioButton();
        someOutcomesInOrderButton2 = new javax.swing.JRadioButton();
        successConditionLabel2 = new javax.swing.JLabel();
        variableOutcomesInSetPanel2 = new javax.swing.JPanel();
        variableNumTimesLabel4 = new javax.swing.JLabel();
        numDistinctOutcomeLabel6 = new javax.swing.JLabel();
        numDistinctOutcomeTextField6 = new javax.swing.JTextField();
        trialOutcomeLabel9 = new javax.swing.JLabel();
        trialOutcomeValueTextField7 = new javax.swing.JTextField();
        trialNumTimesTextField7 = new javax.swing.JTextField();
        variableOutcomesInOrderPanel2 = new javax.swing.JPanel();
        orderValueLabel1v4 = new javax.swing.JLabel();
        variableOutcomeLabel2 = new javax.swing.JLabel();
        variableOrderLabel2 = new javax.swing.JLabel();
        variablenumOutcomesLabel2 = new javax.swing.JLabel();
        outcomeTextField1v4 = new javax.swing.JTextField();
        variableNumOutcomesTextField2 = new javax.swing.JTextField();
        binPanel = new javax.swing.JPanel();
        defaultBinButton = new javax.swing.JRadioButton();
        setBinNumberButton = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        sampleTypeButtonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        sampleButtonGroup2 = new javax.swing.ButtonGroup();
        distributionButtonGroup2 = new javax.swing.ButtonGroup();
        sampleTypeButtonGrouup2 = new javax.swing.ButtonGroup();
        trialTypeButtonGroup2 = new javax.swing.ButtonGroup();
        stopCondButtonGroup2 = new javax.swing.ButtonGroup();
        ANDORButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        graphTypePopUp = new javax.swing.JPopupMenu();
        barChartItem = new javax.swing.JMenuItem();
        dotPlotItem = new javax.swing.JMenuItem();
        binnedDotPlotItem = new javax.swing.JMenuItem();
        histogramItem = new javax.swing.JMenuItem();
        histogramWithFitItem = new javax.swing.JMenuItem();
        boxPlotItem = new javax.swing.JMenuItem();
        pieChartItem = new javax.swing.JMenuItem();
        jPanel14 = new javax.swing.JPanel();
        setBinPopup = new javax.swing.JPopupMenu();
        defaultBinMenuItem = new javax.swing.JMenuItem();
        customBinMenuItem = new javax.swing.JMenuItem();
        differenceSettingsButtonGroup = new javax.swing.ButtonGroup();
        tabsFrame = new javax.swing.JTabbedPane();
        introPanel = new javax.swing.JPanel();
        twoGroupButton = new javax.swing.JRadioButton();
        twoPopButton = new javax.swing.JRadioButton();
        onePopButton1 = new javax.swing.JRadioButton();
        identicalPopButton = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nextButton0 = new javax.swing.JButton();
        dataPanel = new javax.swing.JPanel();
        dataButtonPanel = new javax.swing.JPanel();
        listOutcomeButton = new javax.swing.JRadioButton();
        externalListButton = new javax.swing.JRadioButton();
        distributionButton = new javax.swing.JRadioButton();
        dataDistPanel = new javax.swing.JPanel();
        expButton = new javax.swing.JRadioButton();
        normalDistButton = new javax.swing.JRadioButton();
        uniformDistButton = new javax.swing.JRadioButton();
        binomialButton = new javax.swing.JRadioButton();
        distributionLabel = new javax.swing.JLabel();
        dataDistLowerBoundLabel = new javax.swing.JLabel();
        dataDistUpperBoundLabel = new javax.swing.JLabel();
        dataDistUpperBoundTextField = new javax.swing.JTextField();
        dataDistLowerBoundTextField = new javax.swing.JTextField();
        dataExternalFilePanel = new javax.swing.JPanel();
        selectExternalFileButton = new javax.swing.JButton();
        displayHistogramButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        nextButton1 = new javax.swing.JButton();
        dataListEnclosePanel = new javax.swing.JPanel();
        dataOutcomeListScrollPane = new javax.swing.JScrollPane();
        dataOutcomeListPanel = new javax.swing.JPanel();
        lowerBoundLabel = new javax.swing.JLabel();
        upperBoundLabel = new javax.swing.JLabel();
        probabilityLabel = new javax.swing.JLabel();
        upperBoundField1 = new javax.swing.JTextField();
        outcomeNameField1 = new javax.swing.JTextField();
        lowerBoundField1 = new javax.swing.JTextField();
        probabilityLabel1 = new javax.swing.JLabel();
        displayBarButton = new javax.swing.JButton();
        numOutcomeLabel = new javax.swing.JLabel();
        outcomeNameLabel = new javax.swing.JLabel();
        dataUpdateOutcomeButton = new javax.swing.JButton();
        dataDisplayPrbButton = new javax.swing.JButton();
        numOutcomeField = new javax.swing.JSpinner();
        dataTwoPopPanel = new javax.swing.JPanel();
        dataPop1Panel = new javax.swing.JPanel();
        dataExternalFilePanel1 = new javax.swing.JPanel();
        selectExternalFileButton1 = new javax.swing.JButton();
        displayHistogramButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        externalListButton1 = new javax.swing.JRadioButton();
        distributionButton1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        listOutcomeButton1 = new javax.swing.JRadioButton();
        dataDistPanel1 = new javax.swing.JPanel();
        expButton1 = new javax.swing.JRadioButton();
        normalDistButton1 = new javax.swing.JRadioButton();
        uniformDistButton1 = new javax.swing.JRadioButton();
        binomialButton1 = new javax.swing.JRadioButton();
        distributionLabel1 = new javax.swing.JLabel();
        dataDistLowerBoundLabel1 = new javax.swing.JLabel();
        dataDistUpperBoundLabel1 = new javax.swing.JLabel();
        dataDistUpperBoundTextField1 = new javax.swing.JTextField();
        dataDistLowerBoundTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        dataTwoPopNextButton = new javax.swing.JButton();
        dataListEnclosePanel1 = new javax.swing.JPanel();
        dataListScrollPane1 = new javax.swing.JScrollPane();
        dataOutcomeListPanel1 = new javax.swing.JPanel();
        lowerBoundLabel1 = new javax.swing.JLabel();
        upperBoundLabel1 = new javax.swing.JLabel();
        probabilityLabel2 = new javax.swing.JLabel();
        upperBoundField2 = new javax.swing.JTextField();
        outcomeNameField2 = new javax.swing.JTextField();
        lowerBoundField2 = new javax.swing.JTextField();
        displayBarButton1 = new javax.swing.JButton();
        numOutcomeLabel1 = new javax.swing.JLabel();
        outcomeNameLabel1 = new javax.swing.JLabel();
        dataTwoPopUpdateButton1 = new javax.swing.JButton();
        dataTwoPopPrbDisplay1 = new javax.swing.JButton();
        numOutcomeField1 = new javax.swing.JSpinner();
        dataTwoPopPrbLabel1 = new javax.swing.JLabel();
        dataPop2Panel = new javax.swing.JPanel();
        dataDistPanel2 = new javax.swing.JPanel();
        expButton2 = new javax.swing.JRadioButton();
        normalDistButton2 = new javax.swing.JRadioButton();
        uniformDistButton2 = new javax.swing.JRadioButton();
        binomialButton2 = new javax.swing.JRadioButton();
        distributionLabel2 = new javax.swing.JLabel();
        dataDistLowerBoundLabel2 = new javax.swing.JLabel();
        dataDistUpperBoundLabel2 = new javax.swing.JLabel();
        dataDistUpperBoundTextField2 = new javax.swing.JTextField();
        dataDistLowerBoundTextField2 = new javax.swing.JTextField();
        dataExternalFilePanel2 = new javax.swing.JPanel();
        selectExternalFileButton2 = new javax.swing.JButton();
        displayHistogramButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        externalListButton2 = new javax.swing.JRadioButton();
        distributionButton2 = new javax.swing.JRadioButton();
        listOutcomeButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        dataListEnclosePanel2 = new javax.swing.JPanel();
        dataOutcomeListPanel2ScrollPane = new javax.swing.JScrollPane();
        dataOutcomeListPanel2 = new javax.swing.JPanel();
        lowerBoundLabel2 = new javax.swing.JLabel();
        upperBoundLabel2 = new javax.swing.JLabel();
        probabilityLabel4 = new javax.swing.JLabel();
        upperBoundField3 = new javax.swing.JTextField();
        outcomeNameField3 = new javax.swing.JTextField();
        lowerBoundField3 = new javax.swing.JTextField();
        numOutcomeLabel2 = new javax.swing.JLabel();
        outcomeNameLabel2 = new javax.swing.JLabel();
        dataTwoPopPrbDisplay2 = new javax.swing.JButton();
        dataTwoPopUpdateButton2 = new javax.swing.JButton();
        numOutcomeField2 = new javax.swing.JSpinner();
        dataTwoPopPrbLabel2 = new javax.swing.JLabel();
        trialPanel = new javax.swing.JPanel();
        trialButtonPanel = new javax.swing.JPanel();
        trialTypeButton = new javax.swing.JLabel();
        withoutReplacementButton = new javax.swing.JRadioButton();
        sampleTypeLabel = new javax.swing.JLabel();
        fixedOutcomeButton = new javax.swing.JRadioButton();
        withReplacementButton = new javax.swing.JRadioButton();
        conditionButton = new javax.swing.JRadioButton();
        trialStopConditionPanel = new javax.swing.JPanel();
        trialAllOutcomesInSetButton = new javax.swing.JRadioButton();
        stopConditionLabel = new javax.swing.JLabel();
        trialSomeOutcomesInSetButton = new javax.swing.JRadioButton();
        trialSomeOutcomesInOrderButton = new javax.swing.JRadioButton();
        trialVariableOutcomesInSetPanel = new javax.swing.JPanel();
        trialNumTimesLabel = new javax.swing.JLabel();
        numDistinctOutcomeLabel = new javax.swing.JLabel();
        trialOutcomeLabel = new javax.swing.JLabel();
        trialOutcomeValueTextField1 = new javax.swing.JTextField();
        trialNumTimesTextField1 = new javax.swing.JTextField();
        trialUpdateButton = new javax.swing.JButton();
        numDistinctOutcomeTextField = new javax.swing.JSpinner();
        trialVariableOutcomesInOrderPanel = new javax.swing.JPanel();
        trialNumOutcomesLabel = new javax.swing.JLabel();
        trialOrderLabel = new javax.swing.JLabel();
        trialOutcomeLabel1 = new javax.swing.JLabel();
        trialOutcomeTextField1 = new javax.swing.JTextField();
        trialOrderValueLabel1 = new javax.swing.JLabel();
        trialUpdateButton1 = new javax.swing.JButton();
        trialNumOutcomesTextField = new javax.swing.JSpinner();
        trialFixedOutcomePanel = new javax.swing.JPanel();
        numObservationLabel = new javax.swing.JLabel();
        numObservationTextField = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        nextButton2 = new javax.swing.JButton();
        twoPopTrialPanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        trialTwoPopNextButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        trialAndButton = new javax.swing.JRadioButton();
        trialOrButton = new javax.swing.JRadioButton();
        trialPop1Panel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        trialStopConditionPanel3 = new javax.swing.JPanel();
        trialAllOutcomesInSetButton3 = new javax.swing.JRadioButton();
        stopConditionLabel3 = new javax.swing.JLabel();
        trialSomeOutcomesInSetButton3 = new javax.swing.JRadioButton();
        trialSomeOutcomesInOrderButton3 = new javax.swing.JRadioButton();
        trialVariableOutcomesInSetPanel3 = new javax.swing.JPanel();
        trialNumTimesLabel3 = new javax.swing.JLabel();
        numDistinctOutcomeLabel5 = new javax.swing.JLabel();
        trialOutcomeLabel8 = new javax.swing.JLabel();
        trialOutcomeValueTextField6 = new javax.swing.JTextField();
        trialNumTimesTextField6 = new javax.swing.JTextField();
        twoPopTrialDistinctUpdateButton3 = new javax.swing.JButton();
        numDistinctOutcomeTextField5 = new javax.swing.JSpinner();
        trialVariableOutcomesInOrderPanel3 = new javax.swing.JPanel();
        trialNumOutcomesLabel3 = new javax.swing.JLabel();
        trialOrderLabel3 = new javax.swing.JLabel();
        trialOutcomeLabel10 = new javax.swing.JLabel();
        trialOutcomeTextField4 = new javax.swing.JTextField();
        trialOrderValueLabel4 = new javax.swing.JLabel();
        twoPopTrialSequenceUpdateButton3 = new javax.swing.JButton();
        trialSequenceField1 = new javax.swing.JSpinner();
        trialButtonPanel3 = new javax.swing.JPanel();
        withoutReplacementButton3 = new javax.swing.JRadioButton();
        sampleTypeLabel3 = new javax.swing.JLabel();
        fixedOutcomeButton3 = new javax.swing.JRadioButton();
        withReplacementButton3 = new javax.swing.JRadioButton();
        conditionButton3 = new javax.swing.JRadioButton();
        trialTypeLabel3 = new javax.swing.JLabel();
        trialFixedOutcomePanel3 = new javax.swing.JPanel();
        numObservationLabel4 = new javax.swing.JLabel();
        trialNumObservationTextField1 = new javax.swing.JSpinner();
        trialPop2Panel = new javax.swing.JPanel();
        trialButtonPanel4 = new javax.swing.JPanel();
        trialTypeLabel4 = new javax.swing.JLabel();
        withoutReplacementButton4 = new javax.swing.JRadioButton();
        sampleTypeLabel4 = new javax.swing.JLabel();
        fixedOutcomeButton4 = new javax.swing.JRadioButton();
        withReplacementButton4 = new javax.swing.JRadioButton();
        conditionButton4 = new javax.swing.JRadioButton();
        trialStopConditionPanel4 = new javax.swing.JPanel();
        trialAllOutcomesInSetButton4 = new javax.swing.JRadioButton();
        stopConditionLabel4 = new javax.swing.JLabel();
        trialSomeOutcomesInSetButton4 = new javax.swing.JRadioButton();
        trialSomeOutcomesInOrderButton4 = new javax.swing.JRadioButton();
        trialVariableOutcomesInSetPanel4 = new javax.swing.JPanel();
        trialNumTimesLabel4 = new javax.swing.JLabel();
        numDistinctOutcomeLabel7 = new javax.swing.JLabel();
        trialOutcomeLabel11 = new javax.swing.JLabel();
        trialOutcomeValueTextField8 = new javax.swing.JTextField();
        trialNumTimesTextField8 = new javax.swing.JTextField();
        twoPopTrialDistinctUpdateButton4 = new javax.swing.JButton();
        trialSetField2 = new javax.swing.JSpinner();
        trialVariableOutcomesInOrderPanel4 = new javax.swing.JPanel();
        trialNumOutcomesLabel4 = new javax.swing.JLabel();
        trialOrderLabel4 = new javax.swing.JLabel();
        trialOutcomeLabel12 = new javax.swing.JLabel();
        trialOutcomeTextField5 = new javax.swing.JTextField();
        trialOrderValueLabel5 = new javax.swing.JLabel();
        twoPopTrialSequenceUpdateButton4 = new javax.swing.JButton();
        trialInOrderField2 = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        trialFixedOutcomePanel4 = new javax.swing.JPanel();
        numObservationLabel3 = new javax.swing.JLabel();
        trialNumObservationTextField2 = new javax.swing.JSpinner();
        twoPopVarPanel = new javax.swing.JPanel();
        categoryPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        varSelecteCategoryTextField = new javax.swing.JTextField();
        variableButtons2 = new javax.swing.JPanel();
        variableMeasureLabel2 = new javax.swing.JLabel();
        categoreyDiffButton = new javax.swing.JRadioButton();
        varObsDiffMeanButton = new javax.swing.JRadioButton();
        varSampleMeanDiffButton = new javax.swing.JRadioButton();
        meanDiffGroupButton = new javax.swing.JRadioButton();
        varNumMatchButton = new javax.swing.JRadioButton();
        categoreyPopDiffButton = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        varNextButton = new javax.swing.JButton();
        diffPanel = new javax.swing.JPanel();
        setDifferenceButton = new javax.swing.JLabel();
        diffOption1 = new javax.swing.JRadioButton();
        diffOption2 = new javax.swing.JRadioButton();
        variablePanel = new javax.swing.JPanel();
        variableButtons = new javax.swing.JPanel();
        meanWithSTDButton = new javax.swing.JRadioButton();
        variableMeasureLabel = new javax.swing.JLabel();
        trialLengthButton = new javax.swing.JRadioButton();
        meanButton = new javax.swing.JRadioButton();
        containsSuccessButton = new javax.swing.JRadioButton();
        medianButton = new javax.swing.JRadioButton();
        sumButton = new javax.swing.JRadioButton();
        numSuccessButton = new javax.swing.JRadioButton();
        percentSuccessButton = new javax.swing.JRadioButton();
        modeButton = new javax.swing.JRadioButton();
        modeSizeButton = new javax.swing.JRadioButton();
        variableSuccessConditionPanel = new javax.swing.JPanel();
        someOutcomesInSetButton = new javax.swing.JRadioButton();
        allOutcomesInSetButton = new javax.swing.JRadioButton();
        someOutcomesInOrderButton = new javax.swing.JRadioButton();
        successConditionLabel = new javax.swing.JLabel();
        variableOutcomesInSetPanel = new javax.swing.JPanel();
        variableNumTimesLabel1 = new javax.swing.JLabel();
        numDistinctOutcomeLabel1 = new javax.swing.JLabel();
        trialOutcomeLabel2 = new javax.swing.JLabel();
        trialOutcomeValueTextField2 = new javax.swing.JTextField();
        trialNumTimesTextField2 = new javax.swing.JTextField();
        varSetUpdateButton = new javax.swing.JButton();
        numDistinctOutcomesTextField1 = new javax.swing.JSpinner();
        variableOutcomesInOrderPanel = new javax.swing.JPanel();
        orderValueLabel1v2 = new javax.swing.JLabel();
        variableOutcomeLabel = new javax.swing.JLabel();
        variableOrderLabel = new javax.swing.JLabel();
        variablenumOutcomesLabel = new javax.swing.JLabel();
        outcomeTextField1v2 = new javax.swing.JTextField();
        varOrderUpdateButton = new javax.swing.JButton();
        variableNumOutcomesTextField = new javax.swing.JSpinner();
        jPanel10 = new javax.swing.JPanel();
        nextButton3 = new javax.swing.JButton();
        runPanel = new javax.swing.JPanel();
        runDisplayPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        randIntTextArea = new javax.swing.JTextArea();
        runTrialsButton = new javax.swing.JButton();
        numTrialLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outcomeTextArea = new javax.swing.JTextArea();
        saveIntegersButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        responseVariableTextArea = new javax.swing.JTextArea();
        randomIntegersLabel = new javax.swing.JLabel();
        saveResponsesButton = new javax.swing.JButton();
        saveOutcomesButton = new javax.swing.JButton();
        responseVariableValues = new javax.swing.JLabel();
        TrialOutcomesLabel = new javax.swing.JLabel();
        numTrialField = new javax.swing.JSpinner();
        runTrialsInfoPanel = new javax.swing.JPanel();
        displayGraphButton = new javax.swing.JButton();
        displayDescriptiveStatsButton = new javax.swing.JButton();
        displayDistributionButton = new javax.swing.JButton();
        binButton = new javax.swing.JButton();
        setGraphTypeButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        setBinSpinner = new javax.swing.JSpinner();
        setBinPanel = new javax.swing.JPanel();
        graphTypeButton = new javax.swing.JSpinner();
        titleLabel = new javax.swing.JLabel();
        progressPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout chartFrameLayout = new org.jdesktop.layout.GroupLayout(chartFrame.getContentPane());
        chartFrame.getContentPane().setLayout(chartFrameLayout);
        chartFrameLayout.setHorizontalGroup(
            chartFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
            .add(chartFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(chartFrameLayout.createSequentialGroup()
                    .add(0, 150, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 150, Short.MAX_VALUE)))
        );
        chartFrameLayout.setVerticalGroup(
            chartFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
            .add(chartFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(chartFrameLayout.createSequentialGroup()
                    .add(0, 100, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 100, Short.MAX_VALUE)))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout dataPopupFrameLayout = new org.jdesktop.layout.GroupLayout(dataPopupFrame.getContentPane());
        dataPopupFrame.getContentPane().setLayout(dataPopupFrameLayout);
        dataPopupFrameLayout.setHorizontalGroup(
            dataPopupFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
            .add(dataPopupFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(dataPopupFrameLayout.createSequentialGroup()
                    .add(0, 150, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 150, Short.MAX_VALUE)))
        );
        dataPopupFrameLayout.setVerticalGroup(
            dataPopupFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
            .add(dataPopupFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(dataPopupFrameLayout.createSequentialGroup()
                    .add(0, 100, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 100, Short.MAX_VALUE)))
        );

        trialTwoPopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Population 1");
        trialTwoPopPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, -1));

        jLabel10.setText("Population 2");
        trialTwoPopPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 25, -1, -1));

        nextButton5.setText("next");
        nextButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButton5MouseClicked(evt);
            }
        });
        trialTwoPopPanel.add(nextButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1192, 20, -1, -1));

        trialTypeButton1.setText("Trial Type: ");

        sampleTypeButtonGroup.add(withoutReplacementButton1);
        withoutReplacementButton1.setText("Sample Without replacement");
        withoutReplacementButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutReplacementButton1ActionPerformed(evt);
            }
        });

        sampleTypeLabel1.setText("Type of Sampling:");

        trialButtonGroup.add(fixedOutcomeButton1);
        fixedOutcomeButton1.setText("Generate a fixerd number of outcomes");
        fixedOutcomeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fixedOutcomeButton1MouseClicked(evt);
            }
        });
        fixedOutcomeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixedOutcomeButton1ActionPerformed(evt);
            }
        });

        sampleTypeButtonGroup.add(withReplacementButton1);
        withReplacementButton1.setText("Sample With replacement");

        trialButtonGroup.add(conditionButton1);
        conditionButton1.setText("Generate outcomes until a condition has been met");
        conditionButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionButton1MouseClicked(evt);
            }
        });
        conditionButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conditionButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialButtonPanel1Layout = new org.jdesktop.layout.GroupLayout(trialButtonPanel1);
        trialButtonPanel1.setLayout(trialButtonPanel1Layout);
        trialButtonPanel1Layout.setHorizontalGroup(
            trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel1Layout.createSequentialGroup()
                .add(trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialButtonPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialTypeButton1)
                            .add(sampleTypeLabel1))
                        .add(65, 65, 65)
                        .add(trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(withoutReplacementButton1)
                            .add(withReplacementButton1)
                            .add(fixedOutcomeButton1))
                        .add(75, 75, 75))
                    .add(trialButtonPanel1Layout.createSequentialGroup()
                        .add(198, 198, 198)
                        .add(conditionButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)))
                .addContainerGap())
        );
        trialButtonPanel1Layout.setVerticalGroup(
            trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(withReplacementButton1)
                    .add(sampleTypeLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(withoutReplacementButton1)
                .add(37, 37, 37)
                .add(trialButtonPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialTypeButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fixedOutcomeButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(conditionButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        trialTwoPopPanel.add(trialButtonPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 67, -1, -1));

        trialTypeButton2.setText("Trial Type: ");

        sampleTypeButtonGroup.add(withoutReplacementButton2);
        withoutReplacementButton2.setText("Sample Without replacement");
        withoutReplacementButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutReplacementButton2ActionPerformed(evt);
            }
        });

        sampleTypeLabel2.setText("Type of Sampling:");

        trialButtonGroup.add(fixedOutcomeButton2);
        fixedOutcomeButton2.setText("Generate a fixerd number of outcomes");
        fixedOutcomeButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fixedOutcomeButton2MouseClicked(evt);
            }
        });
        fixedOutcomeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixedOutcomeButton2ActionPerformed(evt);
            }
        });

        sampleTypeButtonGroup.add(withReplacementButton2);
        withReplacementButton2.setText("Sample With replacement");

        trialButtonGroup.add(conditionButton2);
        conditionButton2.setText("Generate outcomes until a condition has been met");
        conditionButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionButton2MouseClicked(evt);
            }
        });
        conditionButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conditionButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialButtonPanel2Layout = new org.jdesktop.layout.GroupLayout(trialButtonPanel2);
        trialButtonPanel2.setLayout(trialButtonPanel2Layout);
        trialButtonPanel2Layout.setHorizontalGroup(
            trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel2Layout.createSequentialGroup()
                .add(trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialButtonPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialTypeButton2)
                            .add(sampleTypeLabel2))
                        .add(65, 65, 65)
                        .add(trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(withoutReplacementButton2)
                            .add(withReplacementButton2)
                            .add(fixedOutcomeButton2))
                        .add(75, 75, 75))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, trialButtonPanel2Layout.createSequentialGroup()
                        .add(198, 198, 198)
                        .add(conditionButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        trialButtonPanel2Layout.setVerticalGroup(
            trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(withReplacementButton2)
                    .add(sampleTypeLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(withoutReplacementButton2)
                .add(37, 37, 37)
                .add(trialButtonPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialTypeButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fixedOutcomeButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(conditionButton2)
                .addContainerGap())
        );

        trialTwoPopPanel.add(trialButtonPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 67, -1, -1));

        stopConditionButtonGroup.add(trialAllOutcomesInSetButton2);
        trialAllOutcomesInSetButton2.setText("All outcomes in a specified set ");
        trialAllOutcomesInSetButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialAllOutcomesInSetButton2MouseClicked(evt);
            }
        });

        stopConditionLabel2.setText("Stop-Condition:");

        stopConditionButtonGroup.add(trialSomeOutcomesInSetButton2);
        trialSomeOutcomesInSetButton2.setText("At least one of he outcomes in a specified set ");
        trialSomeOutcomesInSetButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInSetButton2MouseClicked(evt);
            }
        });
        trialSomeOutcomesInSetButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialSomeOutcomesInSetButton2ActionPerformed(evt);
            }
        });

        stopConditionButtonGroup.add(trialSomeOutcomesInOrderButton2);
        trialSomeOutcomesInOrderButton2.setText("One or more outcomes in a specific order");
        trialSomeOutcomesInOrderButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInOrderButton2MouseClicked(evt);
            }
        });

        trialNumTimesLabel2.setText("Number of times it must appear ");

        numDistinctOutcomeLabel3.setText("Number of Distinct Outcomes:");

        numDistinctOutcomeTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numDistinctOutcomeTextField3none(evt);
            }
        });
        numDistinctOutcomeTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDistinctOutcomeTextField3ActionPerformed(evt);
            }
        });
        numDistinctOutcomeTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numDistinctOutcomeTextField3KeyTyped(evt);
            }
        });

        trialOutcomeLabel5.setText("Outcome");

        twoPopTrialDistinctUpdateButton1.setText("Update");
        twoPopTrialDistinctUpdateButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialDistinctUpdateButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInSetPanel2Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInSetPanel2);
        trialVariableOutcomesInSetPanel2.setLayout(trialVariableOutcomesInSetPanel2Layout);
        trialVariableOutcomesInSetPanel2Layout.setHorizontalGroup(
            trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanel2Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialNumTimesLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInSetPanel2Layout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numDistinctOutcomeTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(twoPopTrialDistinctUpdateButton1)))
                .addContainerGap())
        );
        trialVariableOutcomesInSetPanel2Layout.setVerticalGroup(
            trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numDistinctOutcomeLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numDistinctOutcomeTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialDistinctUpdateButton1))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel5)
                    .add(trialNumTimesLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        trialNumOutcomesLabel2.setText("Number of Outcomes in Sequence:");

        trialNumOutcomesTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialNumOutcomesTextField2MouseClicked(evt);
            }
        });
        trialNumOutcomesTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialNumOutcomesTextField2ActionPerformed(evt);
            }
        });
        trialNumOutcomesTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                trialNumOutcomesTextField2KeyTyped(evt);
            }
        });

        trialOrderLabel2.setText("Order");

        trialOutcomeLabel6.setText("Outcome");

        trialOrderValueLabel3.setText("1");

        twoPopTrialSequenceUpdateButton2.setText("Update");
        twoPopTrialSequenceUpdateButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialSequenceUpdateButton2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInOrderPanel2Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInOrderPanel2);
        trialVariableOutcomesInOrderPanel2.setLayout(trialVariableOutcomesInOrderPanel2Layout);
        trialVariableOutcomesInOrderPanel2Layout.setHorizontalGroup(
            trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanel2Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOrderLabel2)
                            .add(trialOrderValueLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOutcomeLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOutcomeTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInOrderPanel2Layout.createSequentialGroup()
                        .add(trialNumOutcomesLabel2)
                        .add(18, 18, 18)
                        .add(trialNumOutcomesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(twoPopTrialSequenceUpdateButton2)))
                .addContainerGap())
        );
        trialVariableOutcomesInOrderPanel2Layout.setVerticalGroup(
            trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialNumOutcomesLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumOutcomesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialSequenceUpdateButton2))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOrderLabel2)
                    .add(trialOutcomeLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialOrderValueLabel3))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout trialStopConditionPanel2Layout = new org.jdesktop.layout.GroupLayout(trialStopConditionPanel2);
        trialStopConditionPanel2.setLayout(trialStopConditionPanel2Layout);
        trialStopConditionPanel2Layout.setHorizontalGroup(
            trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel2Layout.createSequentialGroup()
                .add(trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(stopConditionLabel2)
                        .add(76, 76, 76)
                        .add(trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialAllOutcomesInSetButton2)
                            .add(trialSomeOutcomesInSetButton2)
                            .add(trialSomeOutcomesInOrderButton2)))
                    .add(trialStopConditionPanel2Layout.createSequentialGroup()
                        .add(115, 115, 115)
                        .add(trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialVariableOutcomesInOrderPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                            .add(trialVariableOutcomesInSetPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trialStopConditionPanel2Layout.setVerticalGroup(
            trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel2Layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(trialStopConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialStopConditionPanel2Layout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(stopConditionLabel2))
                            .add(trialAllOutcomesInSetButton2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialSomeOutcomesInSetButton2))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, trialStopConditionPanel2Layout.createSequentialGroup()
                        .add(trialSomeOutcomesInOrderButton2)
                        .add(54, 54, 54)))
                .add(24, 24, 24)
                .add(trialVariableOutcomesInSetPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(33, 33, 33))
        );

        trialTwoPopPanel.add(trialStopConditionPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 244, -1, -1));

        stopConditionButtonGroup.add(trialAllOutcomesInSetButton1);
        trialAllOutcomesInSetButton1.setText("All outcomes in a specified set ");
        trialAllOutcomesInSetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialAllOutcomesInSetButton1MouseClicked(evt);
            }
        });

        stopConditionLabel1.setText("Stop-Condition:");

        stopConditionButtonGroup.add(trialSomeOutcomesInSetButton1);
        trialSomeOutcomesInSetButton1.setText("At least one of he outcomes in a specified set ");
        trialSomeOutcomesInSetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInSetButton1MouseClicked(evt);
            }
        });
        trialSomeOutcomesInSetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialSomeOutcomesInSetButton1ActionPerformed(evt);
            }
        });

        stopConditionButtonGroup.add(trialSomeOutcomesInOrderButton1);
        trialSomeOutcomesInOrderButton1.setText("One or more outcomes in a specific order");
        trialSomeOutcomesInOrderButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInOrderButton1MouseClicked(evt);
            }
        });
        trialSomeOutcomesInOrderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialSomeOutcomesInOrderButton1ActionPerformed(evt);
            }
        });

        trialNumTimesLabel1.setText("Number of times it must appear ");

        numDistinctOutcomeLabel2.setText("Number of Distinct Outcomes:");

        numDistinctOutcomeTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numDistinctOutcomeTextField2none(evt);
            }
        });
        numDistinctOutcomeTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDistinctOutcomeTextField2ActionPerformed(evt);
            }
        });
        numDistinctOutcomeTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numDistinctOutcomeTextField2KeyTyped(evt);
            }
        });

        trialOutcomeLabel3.setText("Outcome");

        twoPopTrialDistinctUpdateButton2.setText("Update");
        twoPopTrialDistinctUpdateButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialDistinctUpdateButton2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInSetPanel1Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInSetPanel1);
        trialVariableOutcomesInSetPanel1.setLayout(trialVariableOutcomesInSetPanel1Layout);
        trialVariableOutcomesInSetPanel1Layout.setHorizontalGroup(
            trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialNumTimesLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numDistinctOutcomeTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                    .addContainerGap(317, Short.MAX_VALUE)
                    .add(twoPopTrialDistinctUpdateButton2)
                    .add(98, 98, 98)))
        );
        trialVariableOutcomesInSetPanel1Layout.setVerticalGroup(
            trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numDistinctOutcomeLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numDistinctOutcomeTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel3)
                    .add(trialNumTimesLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .add(trialVariableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(trialVariableOutcomesInSetPanel1Layout.createSequentialGroup()
                    .add(31, 31, 31)
                    .add(twoPopTrialDistinctUpdateButton2)
                    .addContainerGap(80, Short.MAX_VALUE)))
        );

        trialNumOutcomesLabel1.setText("Number of Outcomes in Sequence:");

        trialNumOutcomesTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialNumOutcomesTextField1MouseClicked(evt);
            }
        });
        trialNumOutcomesTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialNumOutcomesTextField1ActionPerformed(evt);
            }
        });
        trialNumOutcomesTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                trialNumOutcomesTextField1KeyTyped(evt);
            }
        });

        trialOrderLabel1.setText("Order");

        trialOutcomeLabel4.setText("Outcome");

        trialOrderValueLabel2.setText("1");

        twoPopTrialSequenceUpdateButton1.setText("Update");
        twoPopTrialSequenceUpdateButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialSequenceUpdateButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInOrderPanel1Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInOrderPanel1);
        trialVariableOutcomesInOrderPanel1.setLayout(trialVariableOutcomesInOrderPanel1Layout);
        trialVariableOutcomesInOrderPanel1Layout.setHorizontalGroup(
            trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanel1Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOrderLabel1)
                            .add(trialOrderValueLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOutcomeLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOutcomeTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInOrderPanel1Layout.createSequentialGroup()
                        .add(trialNumOutcomesLabel1)
                        .add(18, 18, 18)
                        .add(trialNumOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(twoPopTrialSequenceUpdateButton1)))
                .addContainerGap())
        );
        trialVariableOutcomesInOrderPanel1Layout.setVerticalGroup(
            trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialNumOutcomesLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialSequenceUpdateButton1))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOrderLabel1)
                    .add(trialOutcomeLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialOrderValueLabel2))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout trialStopConditionPanel1Layout = new org.jdesktop.layout.GroupLayout(trialStopConditionPanel1);
        trialStopConditionPanel1.setLayout(trialStopConditionPanel1Layout);
        trialStopConditionPanel1Layout.setHorizontalGroup(
            trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel1Layout.createSequentialGroup()
                .add(trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(stopConditionLabel1)
                        .add(76, 76, 76)
                        .add(trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialAllOutcomesInSetButton1)
                            .add(trialSomeOutcomesInOrderButton1)
                            .add(trialSomeOutcomesInSetButton1)))
                    .add(trialStopConditionPanel1Layout.createSequentialGroup()
                        .add(115, 115, 115)
                        .add(trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialVariableOutcomesInOrderPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                            .add(trialVariableOutcomesInSetPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trialStopConditionPanel1Layout.setVerticalGroup(
            trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel1Layout.createSequentialGroup()
                        .add(30, 30, 30)
                        .add(trialStopConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialStopConditionPanel1Layout.createSequentialGroup()
                                .add(4, 4, 4)
                                .add(stopConditionLabel1))
                            .add(trialAllOutcomesInSetButton1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialSomeOutcomesInSetButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, trialStopConditionPanel1Layout.createSequentialGroup()
                        .add(trialSomeOutcomesInOrderButton1)
                        .add(54, 54, 54)))
                .add(24, 24, 24)
                .add(trialVariableOutcomesInSetPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(73, 73, 73))
        );

        trialTwoPopPanel.add(trialStopConditionPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 244, 595, -1));

        numObservationLabel1.setText("Number of Observations:");

        numObservationTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numObservationTextField1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialFixedOutcomePanel1Layout = new org.jdesktop.layout.GroupLayout(trialFixedOutcomePanel1);
        trialFixedOutcomePanel1.setLayout(trialFixedOutcomePanel1Layout);
        trialFixedOutcomePanel1Layout.setHorizontalGroup(
            trialFixedOutcomePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(numObservationLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numObservationTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        trialFixedOutcomePanel1Layout.setVerticalGroup(
            trialFixedOutcomePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialFixedOutcomePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numObservationLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numObservationTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        trialTwoPopPanel.add(trialFixedOutcomePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 715, -1, -1));

        numObservationLabel2.setText("Number of Observations:");

        numObservationTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numObservationTextField2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialFixedOutcomePanel2Layout = new org.jdesktop.layout.GroupLayout(trialFixedOutcomePanel2);
        trialFixedOutcomePanel2.setLayout(trialFixedOutcomePanel2Layout);
        trialFixedOutcomePanel2Layout.setHorizontalGroup(
            trialFixedOutcomePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(numObservationLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numObservationTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        trialFixedOutcomePanel2Layout.setVerticalGroup(
            trialFixedOutcomePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialFixedOutcomePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numObservationLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numObservationTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        trialTwoPopPanel.add(trialFixedOutcomePanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 715, -1, -1));

        variableTwoPopPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableTwoPopPanelvariableConditionButtonMouseClicked(evt);
            }
        });
        variableTwoPopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        variableMeasureLabel1.setText("The variable to be measured: ");

        variableButtonGroup.add(varDiffMeanButton);
        varDiffMeanButton.setText("Difference in the mean of each population");

        variableButtonGroup.add(varPrcSuccessInEachButton);
        varPrcSuccessInEachButton.setText("% of Successes in each Population");
        varPrcSuccessInEachButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varPrcSuccessInEachButtonsuccessButtonMouseClicked(evt);
            }
        });
        varPrcSuccessInEachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varPrcSuccessInEachButtonActionPerformed(evt);
            }
        });

        variableButtonGroup.add(varDiffPrcSuccessButton);
        varDiffPrcSuccessButton.setText("Difference in % of successes between populations");
        varDiffPrcSuccessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varDiffPrcSuccessButtonsuccessButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(varMeanInEachButton);
        varMeanInEachButton.setText("Mean of Each population");
        varMeanInEachButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varMeanInEachButtonsuccessButtonMouseClicked(evt);
            }
        });
        varMeanInEachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varMeanInEachButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableButtons1Layout = new org.jdesktop.layout.GroupLayout(variableButtons1);
        variableButtons1.setLayout(variableButtons1Layout);
        variableButtons1Layout.setHorizontalGroup(
            variableButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableButtons1Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableMeasureLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(variableButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(varPrcSuccessInEachButton)
                    .add(varDiffPrcSuccessButton)
                    .add(varMeanInEachButton)
                    .add(varDiffMeanButton))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        variableButtons1Layout.setVerticalGroup(
            variableButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableButtons1Layout.createSequentialGroup()
                .add(variableButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableButtons1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(varPrcSuccessInEachButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(varDiffPrcSuccessButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(varMeanInEachButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(varDiffMeanButton))
                    .add(variableButtons1Layout.createSequentialGroup()
                        .add(54, 54, 54)
                        .add(variableMeasureLabel1)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        variableTwoPopPanel.add(variableButtons1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 590, 130));

        successButtonGroup.add(someOutcomesInSetButton1);
        someOutcomesInSetButton1.setText("At least one of the outcomes in a specified set");
        someOutcomesInSetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                someOutcomesInSetButton1variableConditionButtonMouseClicked(evt);
            }
        });
        someOutcomesInSetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                someOutcomesInSetButton1ActionPerformed(evt);
            }
        });

        successButtonGroup.add(allOutcomesInSetButton1);
        allOutcomesInSetButton1.setText("All outcomes in a specified set");
        allOutcomesInSetButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allOutcomesInSetButton1variableConditionButtonMouseClicked(evt);
            }
        });

        successButtonGroup.add(someOutcomesInOrderButton1);
        someOutcomesInOrderButton1.setText("One or more outcomes in a specific order");
        someOutcomesInOrderButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                someOutcomesInOrderButton1variableConditionButtonMouseClicked(evt);
            }
        });

        successConditionLabel1.setText("Success Condition (Population 1):");

        org.jdesktop.layout.GroupLayout variableSuccessConditionPanel1Layout = new org.jdesktop.layout.GroupLayout(variableSuccessConditionPanel1);
        variableSuccessConditionPanel1.setLayout(variableSuccessConditionPanel1Layout);
        variableSuccessConditionPanel1Layout.setHorizontalGroup(
            variableSuccessConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableSuccessConditionPanel1Layout.createSequentialGroup()
                .add(variableSuccessConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableSuccessConditionPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(someOutcomesInOrderButton1))
                    .add(variableSuccessConditionPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(allOutcomesInSetButton1))
                    .add(variableSuccessConditionPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(someOutcomesInSetButton1))
                    .add(variableSuccessConditionPanel1Layout.createSequentialGroup()
                        .add(62, 62, 62)
                        .add(successConditionLabel1)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        variableSuccessConditionPanel1Layout.setVerticalGroup(
            variableSuccessConditionPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, variableSuccessConditionPanel1Layout.createSequentialGroup()
                .add(successConditionLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(someOutcomesInOrderButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(allOutcomesInSetButton1)
                .add(7, 7, 7)
                .add(someOutcomesInSetButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        variableTwoPopPanel.add(variableSuccessConditionPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 100));

        variableNumTimesLabel2.setText("Number of times it must appear ");

        numDistinctOutcomeLabel4.setText("Number of Distinct Outcomes:");

        numDistinctOutcomeTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numDistinctOutcomeTextField4none(evt);
            }
        });
        numDistinctOutcomeTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDistinctOutcomeTextField4ActionPerformed(evt);
            }
        });
        numDistinctOutcomeTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numDistinctOutcomeTextField4KeyTyped(evt);
            }
        });

        trialOutcomeLabel7.setText("Outcome");

        org.jdesktop.layout.GroupLayout variableOutcomesInSetPanel1Layout = new org.jdesktop.layout.GroupLayout(variableOutcomesInSetPanel1);
        variableOutcomesInSetPanel1.setLayout(variableOutcomesInSetPanel1Layout);
        variableOutcomesInSetPanel1Layout.setHorizontalGroup(
            variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInSetPanel1Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableNumTimesLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(variableOutcomesInSetPanel1Layout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numDistinctOutcomeTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        variableOutcomesInSetPanel1Layout.setVerticalGroup(
            variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numDistinctOutcomeLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numDistinctOutcomeTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel7)
                    .add(variableNumTimesLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInSetPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        variableTwoPopPanel.add(variableOutcomesInSetPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 460, 150));

        nextButton6.setText("next");
        nextButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButton6MouseClicked(evt);
            }
        });
        variableTwoPopPanel.add(nextButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, -1, -1));

        orderValueLabel1v3.setText("1");

        variableOutcomeLabel1.setText("Outcome");

        variableOrderLabel1.setText("Order");

        variablenumOutcomesLabel1.setText("Number of Outcomes in sequences");

        variableNumOutcomesTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                variableNumOutcomesTextField1KeyTyped(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableOutcomesInOrderPanel1Layout = new org.jdesktop.layout.GroupLayout(variableOutcomesInOrderPanel1);
        variableOutcomesInOrderPanel1.setLayout(variableOutcomesInOrderPanel1Layout);
        variableOutcomesInOrderPanel1Layout.setHorizontalGroup(
            variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanel1Layout.createSequentialGroup()
                        .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableOrderLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(orderValueLabel1v3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(24, 24, 24)
                        .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(outcomeTextField1v3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(variableOutcomeLabel1)))
                    .add(variableOutcomesInOrderPanel1Layout.createSequentialGroup()
                        .add(variablenumOutcomesLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableNumOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        variableOutcomesInOrderPanel1Layout.setVerticalGroup(
            variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(variablenumOutcomesLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(variableNumOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(variableOrderLabel1)
                    .add(variableOutcomeLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInOrderPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(outcomeTextField1v3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(orderValueLabel1v3))
                .addContainerGap())
        );

        variableTwoPopPanel.add(variableOutcomesInOrderPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        successButtonGroup.add(someOutcomesInSetButton2);
        someOutcomesInSetButton2.setText("At least one of the outcomes in a specified set");
        someOutcomesInSetButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                someOutcomesInSetButton2variableConditionButtonMouseClicked(evt);
            }
        });

        successButtonGroup.add(allOutcomesInSetButton2);
        allOutcomesInSetButton2.setText("All outcomes in a specified set");
        allOutcomesInSetButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allOutcomesInSetButton2variableConditionButtonMouseClicked(evt);
            }
        });

        successButtonGroup.add(someOutcomesInOrderButton2);
        someOutcomesInOrderButton2.setText("One or more outcomes in a specific order");
        someOutcomesInOrderButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                someOutcomesInOrderButton2variableConditionButtonMouseClicked(evt);
            }
        });

        successConditionLabel2.setText("Success Condition (Population 2):");

        org.jdesktop.layout.GroupLayout variableSuccessConditionPanel2Layout = new org.jdesktop.layout.GroupLayout(variableSuccessConditionPanel2);
        variableSuccessConditionPanel2.setLayout(variableSuccessConditionPanel2Layout);
        variableSuccessConditionPanel2Layout.setHorizontalGroup(
            variableSuccessConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableSuccessConditionPanel2Layout.createSequentialGroup()
                .add(variableSuccessConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableSuccessConditionPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(someOutcomesInOrderButton2))
                    .add(variableSuccessConditionPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(allOutcomesInSetButton2))
                    .add(variableSuccessConditionPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(someOutcomesInSetButton2))
                    .add(variableSuccessConditionPanel2Layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(successConditionLabel2)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        variableSuccessConditionPanel2Layout.setVerticalGroup(
            variableSuccessConditionPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, variableSuccessConditionPanel2Layout.createSequentialGroup()
                .add(successConditionLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(someOutcomesInOrderButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(allOutcomesInSetButton2)
                .add(7, 7, 7)
                .add(someOutcomesInSetButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        variableTwoPopPanel.add(variableSuccessConditionPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, 100));

        variableNumTimesLabel4.setText("Number of times it must appear ");

        numDistinctOutcomeLabel6.setText("Number of Distinct Outcomes:");

        numDistinctOutcomeTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numDistinctOutcomeTextField6none(evt);
            }
        });
        numDistinctOutcomeTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDistinctOutcomeTextField6ActionPerformed(evt);
            }
        });
        numDistinctOutcomeTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numDistinctOutcomeTextField6KeyTyped(evt);
            }
        });

        trialOutcomeLabel9.setText("Outcome");

        org.jdesktop.layout.GroupLayout variableOutcomesInSetPanel2Layout = new org.jdesktop.layout.GroupLayout(variableOutcomesInSetPanel2);
        variableOutcomesInSetPanel2.setLayout(variableOutcomesInSetPanel2Layout);
        variableOutcomesInSetPanel2Layout.setHorizontalGroup(
            variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInSetPanel2Layout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableNumTimesLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(variableOutcomesInSetPanel2Layout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(numDistinctOutcomeTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        variableOutcomesInSetPanel2Layout.setVerticalGroup(
            variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numDistinctOutcomeLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numDistinctOutcomeTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel9)
                    .add(variableNumTimesLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInSetPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        variableTwoPopPanel.add(variableOutcomesInSetPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, -1, -1));

        orderValueLabel1v4.setText("1");

        variableOutcomeLabel2.setText("Outcome");

        variableOrderLabel2.setText("Order");

        variablenumOutcomesLabel2.setText("Number of Outcomes in sequences");

        variableNumOutcomesTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                variableNumOutcomesTextField2KeyTyped(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableOutcomesInOrderPanel2Layout = new org.jdesktop.layout.GroupLayout(variableOutcomesInOrderPanel2);
        variableOutcomesInOrderPanel2.setLayout(variableOutcomesInOrderPanel2Layout);
        variableOutcomesInOrderPanel2Layout.setHorizontalGroup(
            variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanel2Layout.createSequentialGroup()
                        .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableOrderLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(orderValueLabel1v4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(24, 24, 24)
                        .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(outcomeTextField1v4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(variableOutcomeLabel2)))
                    .add(variableOutcomesInOrderPanel2Layout.createSequentialGroup()
                        .add(variablenumOutcomesLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableNumOutcomesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        variableOutcomesInOrderPanel2Layout.setVerticalGroup(
            variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanel2Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(variablenumOutcomesLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(variableNumOutcomesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(variableOrderLabel2)
                    .add(variableOutcomeLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInOrderPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(outcomeTextField1v4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(orderValueLabel1v4))
                .addContainerGap())
        );

        variableTwoPopPanel.add(variableOutcomesInOrderPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, -1, -1));

        defaultBinButton.setText("Use Default Bins");

        setBinNumberButton.setText("Set Number of Bins");

        jTextField2.setText("1");

        jRadioButton1.setText("Set Bin Cutpoints:");

        jTextField3.setText("Please enter a list of comma seperated values");

        jButton1.setText("Set Bins");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout binPanelLayout = new org.jdesktop.layout.GroupLayout(binPanel);
        binPanel.setLayout(binPanelLayout);
        binPanelLayout.setHorizontalGroup(
            binPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(binPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(binPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(defaultBinButton)
                    .add(binPanelLayout.createSequentialGroup()
                        .add(setBinNumberButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(binPanelLayout.createSequentialGroup()
                        .add(jRadioButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, binPanelLayout.createSequentialGroup()
                .addContainerGap(382, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );
        binPanelLayout.setVerticalGroup(
            binPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(binPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(defaultBinButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(binPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(setBinNumberButton)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(binPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton1)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Split Population By Sample Type (with or without replacement)");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Split Population by Categorey ( max 2)");

        jLabel11.setText("Group 1 Size:");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jRadioButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jRadioButton3))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(262, 262, 262)
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton2)
                    .add(jRadioButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField1)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel11)))
                .addContainerGap())
        );

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Bar Chart", "Dot Plot", "Histogram", "Histogram With Fit", "Box Plot", "Pie Chart" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        barChartItem.setText("Bar Chart");
        barChartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barChartItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(barChartItem);

        dotPlotItem.setText("Dot Plot");
        dotPlotItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotPlotItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(dotPlotItem);

        binnedDotPlotItem.setText("Binned Dotplot");
        binnedDotPlotItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binnedDotPlotItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(binnedDotPlotItem);

        histogramItem.setText("Histogram");
        histogramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogramItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(histogramItem);

        histogramWithFitItem.setText("Histogram With Fit");
        histogramWithFitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogramWithFitItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(histogramWithFitItem);

        boxPlotItem.setText("Box Plot");
        boxPlotItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPlotItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(boxPlotItem);

        pieChartItem.setText("Pie Chart");
        pieChartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pieChartItemActionPerformed(evt);
            }
        });
        graphTypePopUp.add(pieChartItem);

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        defaultBinMenuItem.setText("Default Bins");
        defaultBinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultBinMenuItemActionPerformed(evt);
            }
        });
        setBinPopup.add(defaultBinMenuItem);

        customBinMenuItem.setText("Custom Bins");
        customBinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customBinMenuItemActionPerformed(evt);
            }
        });
        setBinPopup.add(customBinMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabsFrame.setPreferredSize(new java.awt.Dimension(1300, 700));

        populationButtonGroup.add(twoGroupButton);
        twoGroupButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        twoGroupButton.setText("One Population, 2 Groups");
        twoGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoGroupButtonActionPerformed(evt);
            }
        });

        populationButtonGroup.add(twoPopButton);
        twoPopButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        twoPopButton.setText("Two Populations");
        twoPopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPopButtonActionPerformed(evt);
            }
        });

        populationButtonGroup.add(onePopButton1);
        onePopButton1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        onePopButton1.setText("A Single Population");
        onePopButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onePopButton1ActionPerformed(evt);
            }
        });

        populationButtonGroup.add(identicalPopButton);
        identicalPopButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        identicalPopButton.setText("Two Identical Populations");
        identicalPopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identicalPopButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("<html>\nDraw a sample from a single group or process.<br>\n(e.g. A bag of marbles, all adults in a country, <br>\na roll of a die, or throwing at a target)");

        jLabel13.setText("<html>\nPut individuals from a group into separate sub-groups.<br>\n(e.g. Randomly assign men/women into treatment/placebo groups)");

        jLabel14.setText("<html>\nDraw a sample from each of two groups or processes and compare or <br>\ncombine their values. (e.g. Compare results from a group of men and <br>\na group of women, or see how often the values from two groups match each other)");

        jLabel16.setText("Draw a sample from two populations that are identical to each other.");

        nextButton0.setText("Submit");
        nextButton0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButton0MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout introPanelLayout = new org.jdesktop.layout.GroupLayout(introPanel);
        introPanel.setLayout(introPanelLayout);
        introPanelLayout.setHorizontalGroup(
            introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(introPanelLayout.createSequentialGroup()
                .add(57, 57, 57)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(identicalPopButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(twoPopButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoGroupButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(onePopButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16)
                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(491, 491, 491))
            .add(introPanelLayout.createSequentialGroup()
                .add(344, 344, 344)
                .add(nextButton0)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        introPanelLayout.setVerticalGroup(
            introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(introPanelLayout.createSequentialGroup()
                .add(51, 51, 51)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(onePopButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(31, 31, 31)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoGroupButton))
                .add(40, 40, 40)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopButton))
                .add(40, 40, 40)
                .add(introPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel16)
                    .add(identicalPopButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(44, 44, 44)
                .add(nextButton0)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Choose Population Type", introPanel);

        sampleButtonGroup.add(listOutcomeButton);
        listOutcomeButton.setText("List each outcome and assign a range of integers");
        listOutcomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listOutcomeButtonMouseClicked(evt);
            }
        });

        sampleButtonGroup.add(externalListButton);
        externalListButton.setText("Select from values in an external list");
        externalListButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                externalListButtonMouseClicked(evt);
            }
        });

        sampleButtonGroup.add(distributionButton);
        distributionButton.setText("Use a theoretical distribution");
        distributionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distributionButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataButtonPanelLayout = new org.jdesktop.layout.GroupLayout(dataButtonPanel);
        dataButtonPanel.setLayout(dataButtonPanelLayout);
        dataButtonPanelLayout.setHorizontalGroup(
            dataButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(listOutcomeButton)
                    .add(distributionButton)
                    .add(externalListButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataButtonPanelLayout.setVerticalGroup(
            dataButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataButtonPanelLayout.createSequentialGroup()
                .add(listOutcomeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(distributionButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(externalListButton))
        );

        distributionButtonGroup.add(expButton);
        expButton.setText("Exponential");

        distributionButtonGroup.add(normalDistButton);
        normalDistButton.setText("Normal");
        normalDistButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalDistButtonMouseClicked(evt);
            }
        });

        distributionButtonGroup.add(uniformDistButton);
        uniformDistButton.setText("Uniform");
        uniformDistButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uniformDistButtonMouseClicked(evt);
            }
        });

        distributionButtonGroup.add(binomialButton);
        binomialButton.setText("Binomial");
        binomialButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                binomialButtonMouseClicked(evt);
            }
        });

        distributionLabel.setText("Distriubtion:");

        dataDistLowerBoundLabel.setText("Lower Bound: ");

        dataDistUpperBoundLabel.setText("Upper Bound: ");

        dataDistUpperBoundTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataDistUpperBoundTextFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataDistPanelLayout = new org.jdesktop.layout.GroupLayout(dataDistPanel);
        dataDistPanel.setLayout(dataDistPanelLayout);
        dataDistPanelLayout.setHorizontalGroup(
            dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistUpperBoundLabel)
                    .add(dataDistLowerBoundLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .add(distributionLabel))
                .add(18, 18, 18)
                .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistPanelLayout.createSequentialGroup()
                        .add(uniformDistButton)
                        .add(18, 18, 18)
                        .add(normalDistButton)
                        .add(29, 29, 29)
                        .add(binomialButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(expButton))
                    .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, dataDistUpperBoundTextField)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, dataDistLowerBoundTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
        );
        dataDistPanelLayout.setVerticalGroup(
            dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanelLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(uniformDistButton)
                    .add(normalDistButton)
                    .add(binomialButton)
                    .add(expButton)
                    .add(distributionLabel))
                .add(18, 18, 18)
                .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dataDistLowerBoundLabel)
                    .add(dataDistLowerBoundTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataDistPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dataDistUpperBoundLabel)
                    .add(dataDistUpperBoundTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        selectExternalFileButton.setText("Select File");
        selectExternalFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectExternalFileButtonMouseClicked(evt);
            }
        });

        displayHistogramButton.setText("Display Barchart");
        displayHistogramButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayHistogramButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataExternalFilePanelLayout = new org.jdesktop.layout.GroupLayout(dataExternalFilePanel);
        dataExternalFilePanel.setLayout(dataExternalFilePanelLayout);
        dataExternalFilePanelLayout.setHorizontalGroup(
            dataExternalFilePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataExternalFilePanelLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .add(dataExternalFilePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, dataExternalFilePanelLayout.createSequentialGroup()
                        .add(displayHistogramButton)
                        .add(74, 74, 74))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, dataExternalFilePanelLayout.createSequentialGroup()
                        .add(selectExternalFileButton)
                        .add(91, 91, 91))))
        );
        dataExternalFilePanelLayout.setVerticalGroup(
            dataExternalFilePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataExternalFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(selectExternalFileButton)
                .add(6, 6, 6)
                .add(displayHistogramButton)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        nextButton1.setText("Submit Model");
        nextButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(146, 146, 146)
                .add(nextButton1)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(nextButton1)
        );

        dataOutcomeListPanel.setPreferredSize(new java.awt.Dimension(451, 200));

        lowerBoundLabel.setText("<html> Lower Bound <br> of Range");

        upperBoundLabel.setText("<html>Upper Bound <br>of Range");

        probabilityLabel.setText("Probability");

        upperBoundField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperBoundField1ActionPerformed(evt);
            }
        });

        probabilityLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        probabilityLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        displayBarButton.setText("Display Bar Chart");
        displayBarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayBarButtonMouseClicked(evt);
            }
        });

        numOutcomeLabel.setText("Number of Outcomes: ");

        outcomeNameLabel.setText("Outcome Name:");

        dataUpdateOutcomeButton.setText("Update");
        dataUpdateOutcomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataUpdateOutcomeButtonMouseClicked(evt);
            }
        });

        dataDisplayPrbButton.setText("Display Probability");
        dataDisplayPrbButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataDisplayPrbButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataOutcomeListPanelLayout = new org.jdesktop.layout.GroupLayout(dataOutcomeListPanel);
        dataOutcomeListPanel.setLayout(dataOutcomeListPanelLayout);
        dataOutcomeListPanelLayout.setHorizontalGroup(
            dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataOutcomeListPanelLayout.createSequentialGroup()
                        .add(36, 36, 36)
                        .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                                .add(1, 1, 1)
                                .add(dataDisplayPrbButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(displayBarButton))
                            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                                .add(numOutcomeLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(numOutcomeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dataUpdateOutcomeButton))))
                    .add(dataOutcomeListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                                .add(outcomeNameLabel)
                                .add(22, 22, 22)
                                .add(lowerBoundLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(upperBoundLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                                .add(outcomeNameField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(10, 10, 10)
                                .add(lowerBoundField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(36, 36, 36)
                                .add(upperBoundField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(probabilityLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(probabilityLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                .addContainerGap())
        );
        dataOutcomeListPanelLayout.setVerticalGroup(
            dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataOutcomeListPanelLayout.createSequentialGroup()
                        .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                            .add(numOutcomeLabel)
                            .add(numOutcomeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dataDisplayPrbButton)
                            .add(displayBarButton)))
                    .add(dataUpdateOutcomeButton))
                .add(18, 18, 18)
                .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(outcomeNameLabel)
                    .add(lowerBoundLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(upperBoundLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(probabilityLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataOutcomeListPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(upperBoundField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(outcomeNameField1)
                        .add(lowerBoundField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(probabilityLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dataOutcomeListScrollPane.setViewportView(dataOutcomeListPanel);

        org.jdesktop.layout.GroupLayout dataListEnclosePanelLayout = new org.jdesktop.layout.GroupLayout(dataListEnclosePanel);
        dataListEnclosePanel.setLayout(dataListEnclosePanelLayout);
        dataListEnclosePanelLayout.setHorizontalGroup(
            dataListEnclosePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 489, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataListEnclosePanelLayout.setVerticalGroup(
            dataListEnclosePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 268, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout dataPanelLayout = new org.jdesktop.layout.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataPanelLayout.createSequentialGroup()
                .add(183, 183, 183)
                .add(dataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(dataListEnclosePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(362, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(dataPanelLayout.createSequentialGroup()
                .add(dataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataPanelLayout.createSequentialGroup()
                        .add(274, 274, 274)
                        .add(dataButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(dataPanelLayout.createSequentialGroup()
                        .add(290, 290, 290)
                        .add(dataExternalFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(dataPanelLayout.createSequentialGroup()
                        .add(218, 218, 218)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataListEnclosePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataDistPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataExternalFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Define a Model", dataPanel);

        selectExternalFileButton1.setText("Select File");
        selectExternalFileButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectExternalFileButton1MouseClicked(evt);
            }
        });

        displayHistogramButton1.setText("Display Histogram");
        displayHistogramButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayHistogramButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataExternalFilePanel1Layout = new org.jdesktop.layout.GroupLayout(dataExternalFilePanel1);
        dataExternalFilePanel1.setLayout(dataExternalFilePanel1Layout);
        dataExternalFilePanel1Layout.setHorizontalGroup(
            dataExternalFilePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataExternalFilePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataExternalFilePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, displayHistogramButton1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, dataExternalFilePanel1Layout.createSequentialGroup()
                        .add(selectExternalFileButton1)
                        .add(26, 26, 26)))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        dataExternalFilePanel1Layout.setVerticalGroup(
            dataExternalFilePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataExternalFilePanel1Layout.createSequentialGroup()
                .add(selectExternalFileButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(displayHistogramButton1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sampleButtonGroup.add(externalListButton1);
        externalListButton1.setText("Select from values in an external list ");
        externalListButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                externalListButton1MouseClicked(evt);
            }
        });

        sampleButtonGroup.add(distributionButton1);
        distributionButton1.setText("Use a theoretical distribution");
        distributionButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distributionButton1MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel7.setText("Population 1");

        sampleButtonGroup.add(listOutcomeButton1);
        listOutcomeButton1.setText("List each outcome and assign a range of integers");
        listOutcomeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPoplistOutcomeButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel12Layout.createSequentialGroup()
                        .add(137, 137, 137)
                        .add(jLabel7))
                    .add(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(listOutcomeButton1)
                            .add(distributionButton1)
                            .add(externalListButton1))))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jLabel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(listOutcomeButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(distributionButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(externalListButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        distributionButtonGroup.add(expButton1);
        expButton1.setText("Exponential");

        distributionButtonGroup.add(normalDistButton1);
        normalDistButton1.setText("Normal");
        normalDistButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalDistButton1MouseClicked(evt);
            }
        });

        distributionButtonGroup.add(uniformDistButton1);
        uniformDistButton1.setText("Uniform");
        uniformDistButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uniformDistButton1MouseClicked(evt);
            }
        });

        distributionButtonGroup.add(binomialButton1);
        binomialButton1.setText("Binomial");
        binomialButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                binomialButton1MouseClicked(evt);
            }
        });

        distributionLabel1.setText("Distriubtion:");

        dataDistLowerBoundLabel1.setText("Lower Bound: ");

        dataDistUpperBoundLabel1.setText("Upper Bound: ");

        org.jdesktop.layout.GroupLayout dataDistPanel1Layout = new org.jdesktop.layout.GroupLayout(dataDistPanel1);
        dataDistPanel1.setLayout(dataDistPanel1Layout);
        dataDistPanel1Layout.setHorizontalGroup(
            dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistPanel1Layout.createSequentialGroup()
                        .add(distributionLabel1)
                        .add(26, 26, 26)
                        .add(uniformDistButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(normalDistButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(binomialButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(expButton1))
                    .add(dataDistPanel1Layout.createSequentialGroup()
                        .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dataDistLowerBoundLabel1)
                            .add(dataDistUpperBoundLabel1))
                        .add(18, 18, 18)
                        .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(dataDistUpperBoundTextField1)
                            .add(dataDistLowerBoundTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        dataDistPanel1Layout.setVerticalGroup(
            dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanel1Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(normalDistButton1)
                    .add(binomialButton1)
                    .add(expButton1)
                    .add(uniformDistButton1)
                    .add(distributionLabel1))
                .add(18, 18, 18)
                .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dataDistLowerBoundLabel1)
                    .add(dataDistLowerBoundTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dataDistPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dataDistUpperBoundLabel1)
                    .add(dataDistUpperBoundTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        dataTwoPopNextButton.setText("Submit Models");
        dataTwoPopNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTwoPopNextButton1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(430, Short.MAX_VALUE)
                .add(dataTwoPopNextButton))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(dataTwoPopNextButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dataListScrollPane1.setViewportView(dataOutcomeListPanel1);

        dataOutcomeListPanel1.setPreferredSize(new java.awt.Dimension(451, 246));

        lowerBoundLabel1.setText("<html>Lower Bound<br>of Range");

        upperBoundLabel1.setText("<html>Upper Bound<br>of Range");

        probabilityLabel2.setText("Probability");

        upperBoundField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperBoundField2ActionPerformed(evt);
            }
        });

        displayBarButton1.setText("Display Bar Chart");
        displayBarButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayBarButton1MouseClicked(evt);
            }
        });

        numOutcomeLabel1.setText("Number of Outcomes: ");

        outcomeNameLabel1.setText("Outcome Name:");

        dataTwoPopUpdateButton1.setText("Update");
        dataTwoPopUpdateButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTwoPopUpdateButton1MouseClicked(evt);
            }
        });

        dataTwoPopPrbDisplay1.setText("Display Probabilities");
        dataTwoPopPrbDisplay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTwoPopPrbDisplay1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataOutcomeListPanel1Layout = new org.jdesktop.layout.GroupLayout(dataOutcomeListPanel1);
        dataOutcomeListPanel1.setLayout(dataOutcomeListPanel1Layout);
        dataOutcomeListPanel1Layout.setHorizontalGroup(
            dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                .add(numOutcomeLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numOutcomeField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataTwoPopUpdateButton1))
            .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataTwoPopPrbDisplay1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(displayBarButton1))
            .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                .add(1, 1, 1)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(outcomeNameLabel1)
                    .add(outcomeNameField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lowerBoundLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lowerBoundField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(upperBoundLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(upperBoundField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataTwoPopPrbLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(probabilityLabel2)))
        );
        dataOutcomeListPanel1Layout.setVerticalGroup(
            dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numOutcomeField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numOutcomeLabel1)
                    .add(dataTwoPopUpdateButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dataTwoPopPrbDisplay1)
                    .add(displayBarButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                            .add(lowerBoundLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(lowerBoundField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(outcomeNameField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(outcomeNameLabel1))
                    .add(dataOutcomeListPanel1Layout.createSequentialGroup()
                        .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(upperBoundLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(probabilityLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dataOutcomeListPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(upperBoundField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(dataTwoPopPrbLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        dataListScrollPane1.setViewportView(dataOutcomeListPanel1);

        org.jdesktop.layout.GroupLayout dataListEnclosePanel1Layout = new org.jdesktop.layout.GroupLayout(dataListEnclosePanel1);
        dataListEnclosePanel1.setLayout(dataListEnclosePanel1Layout);
        dataListEnclosePanel1Layout.setHorizontalGroup(
            dataListEnclosePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanel1Layout.createSequentialGroup()
                .add(dataListScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 494, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        dataListEnclosePanel1Layout.setVerticalGroup(
            dataListEnclosePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataListScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout dataPop1PanelLayout = new org.jdesktop.layout.GroupLayout(dataPop1Panel);
        dataPop1Panel.setLayout(dataPop1PanelLayout);
        dataPop1PanelLayout.setHorizontalGroup(
            dataPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataPop1PanelLayout.createSequentialGroup()
                .add(dataPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dataExternalFilePanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(dataDistPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .add(dataPop1PanelLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(dataListEnclosePanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPop1PanelLayout.setVerticalGroup(
            dataPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataPop1PanelLayout.createSequentialGroup()
                .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataListEnclosePanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataDistPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataExternalFilePanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(expButton2);
        expButton2.setText("Exponential");
        expButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(normalDistButton2);
        normalDistButton2.setText("Normal");
        normalDistButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalDistButton2MouseClicked(evt);
            }
        });

        buttonGroup1.add(uniformDistButton2);
        uniformDistButton2.setText("Uniform");
        uniformDistButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uniformDistButton2MouseClicked(evt);
            }
        });

        buttonGroup1.add(binomialButton2);
        binomialButton2.setText("Binomial");
        binomialButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                binomialButton2MouseClicked(evt);
            }
        });

        distributionLabel2.setText("Distriubtion:");

        dataDistLowerBoundLabel2.setText("Lower Bound: ");

        dataDistUpperBoundLabel2.setText("Upper Bound: ");

        org.jdesktop.layout.GroupLayout dataDistPanel2Layout = new org.jdesktop.layout.GroupLayout(dataDistPanel2);
        dataDistPanel2.setLayout(dataDistPanel2Layout);
        dataDistPanel2Layout.setHorizontalGroup(
            dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(distributionLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dataDistUpperBoundLabel2)
                    .add(dataDistLowerBoundLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(dataDistUpperBoundTextField2)
                    .add(dataDistLowerBoundTextField2)
                    .add(uniformDistButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(normalDistButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(binomialButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(expButton2)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        dataDistPanel2Layout.setVerticalGroup(
            dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataDistPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(distributionLabel2)
                        .add(normalDistButton2)
                        .add(binomialButton2)
                        .add(expButton2)
                        .add(uniformDistButton2))
                    .add(dataDistPanel2Layout.createSequentialGroup()
                        .add(34, 34, 34)
                        .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dataDistLowerBoundLabel2)
                            .add(dataDistLowerBoundTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dataDistPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dataDistUpperBoundLabel2)
                            .add(dataDistUpperBoundTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        selectExternalFileButton2.setText("Select File");
        selectExternalFileButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectExternalFileButton2MouseClicked(evt);
            }
        });

        displayHistogramButton2.setText("Display Histogram");
        displayHistogramButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayHistogramButton2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataExternalFilePanel2Layout = new org.jdesktop.layout.GroupLayout(dataExternalFilePanel2);
        dataExternalFilePanel2.setLayout(dataExternalFilePanel2Layout);
        dataExternalFilePanel2Layout.setHorizontalGroup(
            dataExternalFilePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, dataExternalFilePanel2Layout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .add(dataExternalFilePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(displayHistogramButton2)
                    .add(selectExternalFileButton2))
                .add(163, 163, 163))
        );
        dataExternalFilePanel2Layout.setVerticalGroup(
            dataExternalFilePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataExternalFilePanel2Layout.createSequentialGroup()
                .add(selectExternalFileButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(displayHistogramButton2)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sampleButtonGroup2.add(externalListButton2);
        externalListButton2.setText("Select from values in an external list");
        externalListButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                externalListButton2MouseClicked(evt);
            }
        });

        sampleButtonGroup2.add(distributionButton2);
        distributionButton2.setText("Use a theoretical distribution");
        distributionButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distributionButton2MouseClicked(evt);
            }
        });

        sampleButtonGroup2.add(listOutcomeButton2);
        listOutcomeButton2.setText("List each outcome and assign a range of integers");
        listOutcomeButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPoplistOutcomeButton2MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel8.setText("Population 2");

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(136, 136, 136)
                        .add(jLabel8))
                    .add(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(distributionButton2)
                            .add(listOutcomeButton2)
                            .add(externalListButton2))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(jLabel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(listOutcomeButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(distributionButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(externalListButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dataOutcomeListPanel2ScrollPane.setViewportView(dataOutcomeListPanel2);

        dataOutcomeListPanel2.setPreferredSize(new java.awt.Dimension(451, 246));

        lowerBoundLabel2.setText("<html>Lower Bound<br>of Range");

        upperBoundLabel2.setText("<html>Upper Bound<br>of Range");

        probabilityLabel4.setText("Probability");

        upperBoundField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperBoundField3ActionPerformed(evt);
            }
        });

        numOutcomeLabel2.setText("Number of Outcomes: ");

        outcomeNameLabel2.setText("Outcome Name:");

        dataTwoPopPrbDisplay2.setText("Display Probabilities");
        dataTwoPopPrbDisplay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTwoPopPrbDisplay2MouseClicked(evt);
            }
        });

        dataTwoPopUpdateButton2.setText("Update");
        dataTwoPopUpdateButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTwoPopUpdateButton2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dataOutcomeListPanel2Layout = new org.jdesktop.layout.GroupLayout(dataOutcomeListPanel2);
        dataOutcomeListPanel2.setLayout(dataOutcomeListPanel2Layout);
        dataOutcomeListPanel2Layout.setHorizontalGroup(
            dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                        .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                                .add(outcomeNameLabel2)
                                .add(29, 29, 29))
                            .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                                .add(outcomeNameField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(lowerBoundLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(lowerBoundField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(14, 14, 14)
                                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(upperBoundLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(upperBoundField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(dataTwoPopPrbLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(probabilityLabel4)))
                            .add(dataTwoPopPrbDisplay2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                        .add(numOutcomeLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(numOutcomeField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dataTwoPopUpdateButton2)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        dataOutcomeListPanel2Layout.setVerticalGroup(
            dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataOutcomeListPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numOutcomeField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numOutcomeLabel2)
                    .add(dataTwoPopUpdateButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dataTwoPopPrbDisplay2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(outcomeNameLabel2)
                    .add(lowerBoundLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(upperBoundLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(probabilityLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(outcomeNameField3)
                        .add(lowerBoundField3))
                    .add(dataOutcomeListPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(upperBoundField3)
                        .add(dataTwoPopPrbLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        dataOutcomeListPanel2ScrollPane.setViewportView(dataOutcomeListPanel2);

        org.jdesktop.layout.GroupLayout dataListEnclosePanel2Layout = new org.jdesktop.layout.GroupLayout(dataListEnclosePanel2);
        dataListEnclosePanel2.setLayout(dataListEnclosePanel2Layout);
        dataListEnclosePanel2Layout.setHorizontalGroup(
            dataListEnclosePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListPanel2ScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
        );
        dataListEnclosePanel2Layout.setVerticalGroup(
            dataListEnclosePanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataListEnclosePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dataOutcomeListPanel2ScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout dataPop2PanelLayout = new org.jdesktop.layout.GroupLayout(dataPop2Panel);
        dataPop2Panel.setLayout(dataPop2PanelLayout);
        dataPop2PanelLayout.setHorizontalGroup(
            dataPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, dataPop2PanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .add(dataListEnclosePanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(dataPop2PanelLayout.createSequentialGroup()
                .add(dataPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataDistPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, dataPop2PanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(dataExternalFilePanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dataPop2PanelLayout.setVerticalGroup(
            dataPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataPop2PanelLayout.createSequentialGroup()
                .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataListEnclosePanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(103, 103, 103)
                .add(dataDistPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataExternalFilePanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(147, 147, 147))
        );

        org.jdesktop.layout.GroupLayout dataTwoPopPanelLayout = new org.jdesktop.layout.GroupLayout(dataTwoPopPanel);
        dataTwoPopPanel.setLayout(dataTwoPopPanelLayout);
        dataTwoPopPanelLayout.setHorizontalGroup(
            dataTwoPopPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataTwoPopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataPop1Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dataPop2Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(159, 159, 159))
        );
        dataTwoPopPanelLayout.setVerticalGroup(
            dataTwoPopPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dataTwoPopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dataTwoPopPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dataPop2Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dataPop1Panel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabsFrame.addTab("Define a Model", dataTwoPopPanel);

        trialTypeButton.setText("Trial Type: ");

        sampleTypeButtonGroup.add(withoutReplacementButton);
        withoutReplacementButton.setText("Sample Without replacement");
        withoutReplacementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutReplacementButtonActionPerformed(evt);
            }
        });

        sampleTypeLabel.setText("Type of Sampling:");

        trialButtonGroup.add(fixedOutcomeButton);
        fixedOutcomeButton.setText("Generate a fixed number of outcomes");
        fixedOutcomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fixedOutcomeButtonMouseClicked(evt);
            }
        });

        sampleTypeButtonGroup.add(withReplacementButton);
        withReplacementButton.setText("Sample With replacement");

        trialButtonGroup.add(conditionButton);
        conditionButton.setText("Generate outcomes until a condition has been met");
        conditionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialButtonPanelLayout = new org.jdesktop.layout.GroupLayout(trialButtonPanel);
        trialButtonPanel.setLayout(trialButtonPanelLayout);
        trialButtonPanelLayout.setHorizontalGroup(
            trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialButtonPanelLayout.createSequentialGroup()
                        .add(sampleTypeLabel)
                        .add(18, 18, 18)
                        .add(trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(withoutReplacementButton)
                            .add(withReplacementButton)
                            .add(fixedOutcomeButton)
                            .add(conditionButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(trialTypeButton))
                .addContainerGap())
        );
        trialButtonPanelLayout.setVerticalGroup(
            trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanelLayout.createSequentialGroup()
                .add(8, 8, 8)
                .add(trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sampleTypeLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(withReplacementButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(withoutReplacementButton)
                .add(21, 21, 21)
                .add(trialButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialTypeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fixedOutcomeButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(conditionButton))
        );

        stopConditionButtonGroup.add(trialAllOutcomesInSetButton);
        trialAllOutcomesInSetButton.setText("All outcomes in a specified set ");
        trialAllOutcomesInSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialAllOutcomesInSetButtonMouseClicked(evt);
            }
        });

        stopConditionLabel.setText("Stop-Condition:");

        stopConditionButtonGroup.add(trialSomeOutcomesInSetButton);
        trialSomeOutcomesInSetButton.setText("At least one of he outcomes in a specified set ");
        trialSomeOutcomesInSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInSetButtonMouseClicked(evt);
            }
        });
        trialSomeOutcomesInSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialSomeOutcomesInSetButtonActionPerformed(evt);
            }
        });

        stopConditionButtonGroup.add(trialSomeOutcomesInOrderButton);
        trialSomeOutcomesInOrderButton.setText("One or more outcomes in a specific order");
        trialSomeOutcomesInOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInOrderButtonMouseClicked(evt);
            }
        });

        trialNumTimesLabel.setText("Number of times it must appear ");

        numDistinctOutcomeLabel.setText("Number of Distinct Outcomes:");

        trialOutcomeLabel.setText("Outcome");

        trialUpdateButton.setText("Update");
        trialUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialUpdateSetButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInSetPanelLayout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInSetPanel);
        trialVariableOutcomesInSetPanel.setLayout(trialVariableOutcomesInSetPanelLayout);
        trialVariableOutcomesInSetPanelLayout.setHorizontalGroup(
            trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                        .add(85, 85, 85)
                        .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialNumTimesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(numDistinctOutcomeLabel)
                        .add(31, 31, 31)
                        .add(numDistinctOutcomeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(trialUpdateButton)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        trialVariableOutcomesInSetPanelLayout.setVerticalGroup(
            trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                .add(9, 9, 9)
                .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(numDistinctOutcomeLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(trialUpdateButton))
                    .add(numDistinctOutcomeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(trialVariableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                        .add(trialOutcomeLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialOutcomeValueTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(trialVariableOutcomesInSetPanelLayout.createSequentialGroup()
                        .add(trialNumTimesLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialNumTimesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        trialNumOutcomesLabel.setText("Number of Outcomes in Sequence:");

        trialOrderLabel.setText("Place in Sequence");

        trialOutcomeLabel1.setText("Outcome");

        trialOrderValueLabel1.setText("1");

        trialUpdateButton1.setText("Update");
        trialUpdateButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialUpdateOrderButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInOrderPanelLayout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInOrderPanel);
        trialVariableOutcomesInOrderPanel.setLayout(trialVariableOutcomesInOrderPanelLayout);
        trialVariableOutcomesInOrderPanelLayout.setHorizontalGroup(
            trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanelLayout.createSequentialGroup()
                        .add(34, 34, 34)
                        .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(trialOrderLabel)
                            .add(trialOrderValueLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOutcomeTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOutcomeLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInOrderPanelLayout.createSequentialGroup()
                        .add(trialNumOutcomesLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(trialNumOutcomesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(trialUpdateButton1)))
                .addContainerGap())
        );
        trialVariableOutcomesInOrderPanelLayout.setVerticalGroup(
            trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(trialNumOutcomesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(trialUpdateButton1))
                    .add(trialNumOutcomesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOrderLabel)
                    .add(trialOutcomeLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialOrderValueLabel1))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout trialStopConditionPanelLayout = new org.jdesktop.layout.GroupLayout(trialStopConditionPanel);
        trialStopConditionPanel.setLayout(trialStopConditionPanelLayout);
        trialStopConditionPanelLayout.setHorizontalGroup(
            trialStopConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanelLayout.createSequentialGroup()
                .add(trialStopConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(stopConditionLabel)
                        .add(32, 32, 32)
                        .add(trialStopConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialSomeOutcomesInSetButton)
                            .add(trialSomeOutcomesInOrderButton)
                            .add(trialAllOutcomesInSetButton)))
                    .add(trialStopConditionPanelLayout.createSequentialGroup()
                        .add(36, 36, 36)
                        .add(trialVariableOutcomesInOrderPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(trialStopConditionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(trialVariableOutcomesInSetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trialStopConditionPanelLayout.setVerticalGroup(
            trialStopConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanelLayout.createSequentialGroup()
                .add(trialStopConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanelLayout.createSequentialGroup()
                        .add(40, 40, 40)
                        .add(stopConditionLabel))
                    .add(trialStopConditionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(trialSomeOutcomesInOrderButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialAllOutcomesInSetButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialSomeOutcomesInSetButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 8, Short.MAX_VALUE)
                .add(trialVariableOutcomesInSetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(83, 83, 83))
        );

        numObservationLabel.setText("Number of Observations:");

        org.jdesktop.layout.GroupLayout trialFixedOutcomePanelLayout = new org.jdesktop.layout.GroupLayout(trialFixedOutcomePanel);
        trialFixedOutcomePanel.setLayout(trialFixedOutcomePanelLayout);
        trialFixedOutcomePanelLayout.setHorizontalGroup(
            trialFixedOutcomePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanelLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(numObservationLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numObservationTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .add(24, 24, 24))
        );
        trialFixedOutcomePanelLayout.setVerticalGroup(
            trialFixedOutcomePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanelLayout.createSequentialGroup()
                .add(11, 11, 11)
                .add(trialFixedOutcomePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numObservationTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numObservationLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nextButton2.setText("Submit Trial Type");
        nextButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(nextButton2)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(nextButton2)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout trialPanelLayout = new org.jdesktop.layout.GroupLayout(trialPanel);
        trialPanel.setLayout(trialPanelLayout);
        trialPanelLayout.setHorizontalGroup(
            trialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPanelLayout.createSequentialGroup()
                .add(trialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialPanelLayout.createSequentialGroup()
                        .add(102, 102, 102)
                        .add(trialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialStopConditionPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialPanelLayout.createSequentialGroup()
                        .add(201, 201, 201)
                        .add(trialFixedOutcomePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(trialPanelLayout.createSequentialGroup()
                        .add(253, 253, 253)
                        .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(516, Short.MAX_VALUE))
        );
        trialPanelLayout.setVerticalGroup(
            trialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPanelLayout.createSequentialGroup()
                .add(trialButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialFixedOutcomePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialStopConditionPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Define a Trial", trialPanel);

        trialTwoPopNextButton2.setText("Submit Trial Type");
        trialTwoPopNextButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialTwoPopNextButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialTwoPopNextButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialTwoPopNextButton2)
                .addContainerGap())
        );

        ANDORButtonGroup.add(trialAndButton);
        trialAndButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        trialAndButton.setText("AND");

        ANDORButtonGroup.add(trialOrButton);
        trialOrButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        trialOrButton.setText("OR");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialOrButton)
            .add(trialAndButton)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(trialAndButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialOrButton))
        );

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel17.setText("Sample 1");

        stopConditionButtonGroup.add(trialAllOutcomesInSetButton3);
        trialAllOutcomesInSetButton3.setText("All outcomes in a specified set ");
        trialAllOutcomesInSetButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialAllOutcomesInSetButton3MouseClicked(evt);
            }
        });

        stopConditionLabel3.setText("Stop-Condition:");

        stopConditionButtonGroup.add(trialSomeOutcomesInSetButton3);
        trialSomeOutcomesInSetButton3.setText("At least one of he outcomes in a specified set ");
        trialSomeOutcomesInSetButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInSetButton3MouseClicked(evt);
            }
        });

        stopConditionButtonGroup.add(trialSomeOutcomesInOrderButton3);
        trialSomeOutcomesInOrderButton3.setText("One or more outcomes in a specific order");
        trialSomeOutcomesInOrderButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInOrderButton3MouseClicked(evt);
            }
        });

        trialNumTimesLabel3.setText("Number of times it must appear ");

        numDistinctOutcomeLabel5.setText("Number of Distinct Outcomes:");

        trialOutcomeLabel8.setText("Outcome");

        twoPopTrialDistinctUpdateButton3.setText("Update");
        twoPopTrialDistinctUpdateButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialDistinctUpdateButton3MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInSetPanel3Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInSetPanel3);
        trialVariableOutcomesInSetPanel3.setLayout(trialVariableOutcomesInSetPanel3Layout);
        trialVariableOutcomesInSetPanel3Layout.setHorizontalGroup(
            trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel3Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(numDistinctOutcomeLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numDistinctOutcomeTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(twoPopTrialDistinctUpdateButton3))
            .add(trialVariableOutcomesInSetPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(trialOutcomeLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(trialOutcomeValueTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(46, 46, 46)
                .add(trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialNumTimesLabel3)
                    .add(trialNumTimesTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        trialVariableOutcomesInSetPanel3Layout.setVerticalGroup(
            trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel3Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numDistinctOutcomeLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numDistinctOutcomeTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialDistinctUpdateButton3))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel8)
                    .add(trialNumTimesLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInSetPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        trialNumOutcomesLabel3.setText("Number of Outcomes in Sequence:");

        trialOrderLabel3.setText("Place In Sequence");

        trialOutcomeLabel10.setText("Outcome");

        trialOrderValueLabel4.setText("1");

        twoPopTrialSequenceUpdateButton3.setText("Update");
        twoPopTrialSequenceUpdateButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialSequenceUpdateButton3MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInOrderPanel3Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInOrderPanel3);
        trialVariableOutcomesInOrderPanel3.setLayout(trialVariableOutcomesInOrderPanel3Layout);
        trialVariableOutcomesInOrderPanel3Layout.setHorizontalGroup(
            trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                        .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                                .add(84, 84, 84)
                                .add(trialOrderValueLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                                .add(46, 46, 46)
                                .add(trialOrderLabel3)))
                        .add(37, 37, 37)
                        .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOutcomeTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOutcomeLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                        .add(trialNumOutcomesLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialSequenceField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(twoPopTrialSequenceUpdateButton3)))
                .add(20, 20, 20))
        );
        trialVariableOutcomesInOrderPanel3Layout.setVerticalGroup(
            trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(trialNumOutcomesLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialSequenceField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialSequenceUpdateButton3))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOrderLabel3)
                    .add(trialOutcomeLabel10))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOrderValueLabel4)
                    .add(trialOutcomeTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout trialStopConditionPanel3Layout = new org.jdesktop.layout.GroupLayout(trialStopConditionPanel3);
        trialStopConditionPanel3.setLayout(trialStopConditionPanel3Layout);
        trialStopConditionPanel3Layout.setHorizontalGroup(
            trialStopConditionPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel3Layout.createSequentialGroup()
                .add(trialStopConditionPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel3Layout.createSequentialGroup()
                        .add(stopConditionLabel3)
                        .add(18, 18, 18)
                        .add(trialStopConditionPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialSomeOutcomesInSetButton3)
                            .add(trialAllOutcomesInSetButton3)
                            .add(trialSomeOutcomesInOrderButton3)))
                    .add(trialStopConditionPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(trialVariableOutcomesInSetPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(trialVariableOutcomesInOrderPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        trialStopConditionPanel3Layout.setVerticalGroup(
            trialStopConditionPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel3Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(trialStopConditionPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(stopConditionLabel3)
                    .add(trialSomeOutcomesInOrderButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialAllOutcomesInSetButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialSomeOutcomesInSetButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(trialVariableOutcomesInSetPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sampleTypeButtonGroup.add(withoutReplacementButton3);
        withoutReplacementButton3.setText("Sample Without replacement");
        withoutReplacementButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutReplacementButton3ActionPerformed(evt);
            }
        });

        sampleTypeLabel3.setText("Type of Sampling:");

        trialButtonGroup.add(fixedOutcomeButton3);
        fixedOutcomeButton3.setText("Generate a fixed number of outcomes");
        fixedOutcomeButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fixedOutcomeButton3MouseClicked(evt);
            }
        });

        sampleTypeButtonGroup.add(withReplacementButton3);
        withReplacementButton3.setText("Sample With replacement");
        withReplacementButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                withReplacementButton3MouseClicked(evt);
            }
        });

        trialButtonGroup.add(conditionButton3);
        conditionButton3.setText("Generate outcomes until a condition has been met");
        conditionButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionButton3MouseClicked(evt);
            }
        });

        trialTypeLabel3.setText("Trial Type: ");

        org.jdesktop.layout.GroupLayout trialButtonPanel3Layout = new org.jdesktop.layout.GroupLayout(trialButtonPanel3);
        trialButtonPanel3.setLayout(trialButtonPanel3Layout);
        trialButtonPanel3Layout.setHorizontalGroup(
            trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel3Layout.createSequentialGroup()
                .add(trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sampleTypeLabel3)
                    .add(trialTypeLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(fixedOutcomeButton3)
                    .add(conditionButton3)
                    .add(withoutReplacementButton3)
                    .add(withReplacementButton3))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trialButtonPanel3Layout.setVerticalGroup(
            trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel3Layout.createSequentialGroup()
                .add(trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sampleTypeLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(withReplacementButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(withoutReplacementButton3)
                .add(18, 18, 18)
                .add(trialButtonPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialTypeLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fixedOutcomeButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(conditionButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        numObservationLabel4.setText("Number of Observations:");

        org.jdesktop.layout.GroupLayout trialFixedOutcomePanel3Layout = new org.jdesktop.layout.GroupLayout(trialFixedOutcomePanel3);
        trialFixedOutcomePanel3.setLayout(trialFixedOutcomePanel3Layout);
        trialFixedOutcomePanel3Layout.setHorizontalGroup(
            trialFixedOutcomePanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(numObservationLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialNumObservationTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trialFixedOutcomePanel3Layout.setVerticalGroup(
            trialFixedOutcomePanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialFixedOutcomePanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numObservationLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumObservationTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout trialPop1PanelLayout = new org.jdesktop.layout.GroupLayout(trialPop1Panel);
        trialPop1Panel.setLayout(trialPop1PanelLayout);
        trialPop1PanelLayout.setHorizontalGroup(
            trialPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPop1PanelLayout.createSequentialGroup()
                .add(trialPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialPop1PanelLayout.createSequentialGroup()
                        .add(206, 206, 206)
                        .add(jLabel17))
                    .add(trialPop1PanelLayout.createSequentialGroup()
                        .add(trialButtonPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(62, 62, 62))
                    .add(trialStopConditionPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialPop1PanelLayout.createSequentialGroup()
                        .add(97, 97, 97)
                        .add(trialFixedOutcomePanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        trialPop1PanelLayout.setVerticalGroup(
            trialPop1PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPop1PanelLayout.createSequentialGroup()
                .add(jLabel17)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialButtonPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialStopConditionPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialFixedOutcomePanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        trialButtonPanel4.setPreferredSize(new java.awt.Dimension(500, 127));

        trialTypeLabel4.setText("Trial Type: ");

        sampleTypeButtonGroup2.add(withoutReplacementButton4);
        withoutReplacementButton4.setText("Sample Without replacement");
        withoutReplacementButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withoutReplacementButton4ActionPerformed(evt);
            }
        });

        sampleTypeLabel4.setText("Type of Sampling:");

        trialTypeButtonGroup2.add(fixedOutcomeButton4);
        fixedOutcomeButton4.setText("Generate a fixed number of outcomes");
        fixedOutcomeButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fixedOutcomeButton4MouseClicked(evt);
            }
        });

        sampleTypeButtonGroup2.add(withReplacementButton4);
        withReplacementButton4.setText("Sample With replacement");

        trialTypeButtonGroup2.add(conditionButton4);
        conditionButton4.setText("Generate outcomes until a condition has been met");
        conditionButton4.setMinimumSize(new java.awt.Dimension(200, 23));
        conditionButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conditionButton4MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialButtonPanel4Layout = new org.jdesktop.layout.GroupLayout(trialButtonPanel4);
        trialButtonPanel4.setLayout(trialButtonPanel4Layout);
        trialButtonPanel4Layout.setHorizontalGroup(
            trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, trialButtonPanel4Layout.createSequentialGroup()
                .add(trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sampleTypeLabel4)
                    .add(trialTypeLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(fixedOutcomeButton4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(withoutReplacementButton4)
                    .add(withReplacementButton4)
                    .add(conditionButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        trialButtonPanel4Layout.setVerticalGroup(
            trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialButtonPanel4Layout.createSequentialGroup()
                .add(trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(withReplacementButton4)
                    .add(sampleTypeLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(withoutReplacementButton4)
                .add(18, 18, 18)
                .add(trialButtonPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(fixedOutcomeButton4)
                    .add(trialTypeLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(conditionButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stopCondButtonGroup2.add(trialAllOutcomesInSetButton4);
        trialAllOutcomesInSetButton4.setText("All outcomes in a specified set ");
        trialAllOutcomesInSetButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialAllOutcomesInSetButton4MouseClicked(evt);
            }
        });

        stopConditionLabel4.setText("Stop-Condition:");

        stopCondButtonGroup2.add(trialSomeOutcomesInSetButton4);
        trialSomeOutcomesInSetButton4.setText("At least one of he outcomes in a specified set ");
        trialSomeOutcomesInSetButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInSetButton4MouseClicked(evt);
            }
        });

        stopCondButtonGroup2.add(trialSomeOutcomesInOrderButton4);
        trialSomeOutcomesInOrderButton4.setText("One or more outcomes in a specific order");
        trialSomeOutcomesInOrderButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialSomeOutcomesInOrderButton4MouseClicked(evt);
            }
        });

        trialNumTimesLabel4.setText("Number of times it must appear ");

        numDistinctOutcomeLabel7.setText("Number of Distinct Outcomes:");

        trialOutcomeLabel11.setText("Outcome");

        twoPopTrialDistinctUpdateButton4.setText("Update");
        twoPopTrialDistinctUpdateButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialDistinctUpdateButton4MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInSetPanel4Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInSetPanel4);
        trialVariableOutcomesInSetPanel4.setLayout(trialVariableOutcomesInSetPanel4Layout);
        trialVariableOutcomesInSetPanel4Layout.setHorizontalGroup(
            trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInSetPanel4Layout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialSetField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(1, 1, 1)
                        .add(twoPopTrialDistinctUpdateButton4))
                    .add(trialVariableOutcomesInSetPanel4Layout.createSequentialGroup()
                        .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialNumTimesLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
        );
        trialVariableOutcomesInSetPanel4Layout.setVerticalGroup(
            trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInSetPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(numDistinctOutcomeLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialSetField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialDistinctUpdateButton4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel11)
                    .add(trialNumTimesLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInSetPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        trialNumOutcomesLabel4.setText("Number of Outcomes in Sequence:");

        trialOrderLabel4.setText("Place in Sequence");

        trialOutcomeLabel12.setText("Outcome");

        trialOrderValueLabel5.setText("1");

        twoPopTrialSequenceUpdateButton4.setText("Update");
        twoPopTrialSequenceUpdateButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoPopTrialSequenceUpdateButton4MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trialVariableOutcomesInOrderPanel4Layout = new org.jdesktop.layout.GroupLayout(trialVariableOutcomesInOrderPanel4);
        trialVariableOutcomesInOrderPanel4.setLayout(trialVariableOutcomesInOrderPanel4Layout);
        trialVariableOutcomesInOrderPanel4Layout.setHorizontalGroup(
            trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                        .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                                .add(84, 84, 84)
                                .add(trialOrderValueLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                                .add(39, 39, 39)
                                .add(trialOrderLabel4)))
                        .add(18, 18, 18)
                        .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialOutcomeTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOutcomeLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                        .add(trialNumOutcomesLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialInOrderField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(twoPopTrialSequenceUpdateButton4)))
                .addContainerGap())
        );
        trialVariableOutcomesInOrderPanel4Layout.setVerticalGroup(
            trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(trialNumOutcomesLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialInOrderField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopTrialSequenceUpdateButton4))
                .add(18, 18, 18)
                .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialVariableOutcomesInOrderPanel4Layout.createSequentialGroup()
                        .add(trialOrderLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialVariableOutcomesInOrderPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                            .add(trialOutcomeTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialOrderValueLabel5)))
                    .add(trialOutcomeLabel12))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout trialStopConditionPanel4Layout = new org.jdesktop.layout.GroupLayout(trialStopConditionPanel4);
        trialStopConditionPanel4.setLayout(trialStopConditionPanel4Layout);
        trialStopConditionPanel4Layout.setHorizontalGroup(
            trialStopConditionPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel4Layout.createSequentialGroup()
                .add(trialStopConditionPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialStopConditionPanel4Layout.createSequentialGroup()
                        .add(stopConditionLabel4)
                        .add(18, 18, 18)
                        .add(trialStopConditionPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialAllOutcomesInSetButton4)
                            .add(trialSomeOutcomesInOrderButton4)
                            .add(trialSomeOutcomesInSetButton4)))
                    .add(trialVariableOutcomesInSetPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialVariableOutcomesInOrderPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        trialStopConditionPanel4Layout.setVerticalGroup(
            trialStopConditionPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialStopConditionPanel4Layout.createSequentialGroup()
                .add(trialStopConditionPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(stopConditionLabel4)
                    .add(trialStopConditionPanel4Layout.createSequentialGroup()
                        .add(trialSomeOutcomesInOrderButton4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(trialAllOutcomesInSetButton4)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialSomeOutcomesInSetButton4)
                .add(5, 5, 5)
                .add(trialVariableOutcomesInSetPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialVariableOutcomesInOrderPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel18.setText("Sample 2");

        numObservationLabel3.setText("Number of Observations:");

        org.jdesktop.layout.GroupLayout trialFixedOutcomePanel4Layout = new org.jdesktop.layout.GroupLayout(trialFixedOutcomePanel4);
        trialFixedOutcomePanel4.setLayout(trialFixedOutcomePanel4Layout);
        trialFixedOutcomePanel4Layout.setHorizontalGroup(
            trialFixedOutcomePanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(numObservationLabel3)
                .add(1, 1, 1)
                .add(trialNumObservationTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(45, 45, 45))
        );
        trialFixedOutcomePanel4Layout.setVerticalGroup(
            trialFixedOutcomePanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialFixedOutcomePanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(trialFixedOutcomePanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(trialNumObservationTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numObservationLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout trialPop2PanelLayout = new org.jdesktop.layout.GroupLayout(trialPop2Panel);
        trialPop2Panel.setLayout(trialPop2PanelLayout);
        trialPop2PanelLayout.setHorizontalGroup(
            trialPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPop2PanelLayout.createSequentialGroup()
                .add(trialPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(trialPop2PanelLayout.createSequentialGroup()
                        .add(161, 161, 161)
                        .add(jLabel18))
                    .add(trialButtonPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 488, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialStopConditionPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialPop2PanelLayout.createSequentialGroup()
                        .add(111, 111, 111)
                        .add(trialFixedOutcomePanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        trialPop2PanelLayout.setVerticalGroup(
            trialPop2PanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(trialPop2PanelLayout.createSequentialGroup()
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialButtonPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialStopConditionPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(trialFixedOutcomePanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout twoPopTrialPanelLayout = new org.jdesktop.layout.GroupLayout(twoPopTrialPanel);
        twoPopTrialPanel.setLayout(twoPopTrialPanelLayout);
        twoPopTrialPanelLayout.setHorizontalGroup(
            twoPopTrialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(twoPopTrialPanelLayout.createSequentialGroup()
                .add(twoPopTrialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(twoPopTrialPanelLayout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(trialPop1Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(trialPop2Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(twoPopTrialPanelLayout.createSequentialGroup()
                        .add(445, 445, 445)
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        twoPopTrialPanelLayout.setVerticalGroup(
            twoPopTrialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(twoPopTrialPanelLayout.createSequentialGroup()
                .add(twoPopTrialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(twoPopTrialPanelLayout.createSequentialGroup()
                        .add(163, 163, 163)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(twoPopTrialPanelLayout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(twoPopTrialPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(trialPop2Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialPop1Panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Define a Trial", twoPopTrialPanel);

        twoPopVarPanel.setPreferredSize(new java.awt.Dimension(1200, 654));
        twoPopVarPanel.setRequestFocusEnabled(false);

        jLabel15.setText("Choose Category To Measure:");

        org.jdesktop.layout.GroupLayout categoryPanelLayout = new org.jdesktop.layout.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(categoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel15)
                .add(18, 18, 18)
                .add(varSelecteCategoryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(categoryPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(categoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(varSelecteCategoryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        variableMeasureLabel2.setText("The variable to be measured: ");

        variableButtonGroup.add(categoreyDiffButton);
        categoreyDiffButton.setText("Difference in the proportions of 1 category  of each group");
        categoreyDiffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoreyDiffButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(varObsDiffMeanButton);
        varObsDiffMeanButton.setText("Average of matched sample differences");
        varObsDiffMeanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varObsDiffMeanButtonsuccessButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(varSampleMeanDiffButton);
        varSampleMeanDiffButton.setText("Difference in Sample Means");
        varSampleMeanDiffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varSampleMeanDiffButtonMouseClicked(evt);
            }
        });
        varSampleMeanDiffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varSampleMeanDiffButtonActionPerformed(evt);
            }
        });

        variableButtonGroup.add(meanDiffGroupButton);
        meanDiffGroupButton.setText("Different In Mean of Each Group");
        meanDiffGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meanDiffGroupButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(varNumMatchButton);
        varNumMatchButton.setText("Number of Matches ");
        varNumMatchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varNumMatchButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(categoreyPopDiffButton);
        categoreyPopDiffButton.setText("Difference in the proportions of 1 category  of each population");
        categoreyPopDiffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoreyPopDiffButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableButtons2Layout = new org.jdesktop.layout.GroupLayout(variableButtons2);
        variableButtons2.setLayout(variableButtons2Layout);
        variableButtons2Layout.setHorizontalGroup(
            variableButtons2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableButtons2Layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(variableMeasureLabel2)
                .add(18, 18, 18)
                .add(variableButtons2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(categoreyPopDiffButton)
                    .add(varNumMatchButton)
                    .add(meanDiffGroupButton)
                    .add(varObsDiffMeanButton)
                    .add(varSampleMeanDiffButton)
                    .add(categoreyDiffButton))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        variableButtons2Layout.setVerticalGroup(
            variableButtons2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableButtons2Layout.createSequentialGroup()
                .add(variableButtons2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(variableMeasureLabel2)
                    .add(variableButtons2Layout.createSequentialGroup()
                        .add(varObsDiffMeanButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(varSampleMeanDiffButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(categoreyDiffButton)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(categoreyPopDiffButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(meanDiffGroupButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(varNumMatchButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        varNextButton.setText("Submit Variable");
        varNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varNextButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(varNextButton)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(varNextButton)
                .addContainerGap())
        );

        setDifferenceButton.setText("Specify Difference: ");

        differenceSettingsButtonGroup.add(diffOption1);
        diffOption1.setText("Group 1 - Group 2");
        diffOption1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diffOption1ActionPerformed(evt);
            }
        });

        differenceSettingsButtonGroup.add(diffOption2);
        diffOption2.setText("Group 2 - Group 1");
        diffOption2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diffOption2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout diffPanelLayout = new org.jdesktop.layout.GroupLayout(diffPanel);
        diffPanel.setLayout(diffPanelLayout);
        diffPanelLayout.setHorizontalGroup(
            diffPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(diffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(setDifferenceButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(diffPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(diffOption2)
                    .add(diffOption1))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        diffPanelLayout.setVerticalGroup(
            diffPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(diffPanelLayout.createSequentialGroup()
                .add(30, 30, 30)
                .add(diffPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(setDifferenceButton)
                    .add(diffOption1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(diffOption2)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout twoPopVarPanelLayout = new org.jdesktop.layout.GroupLayout(twoPopVarPanel);
        twoPopVarPanel.setLayout(twoPopVarPanelLayout);
        twoPopVarPanelLayout.setHorizontalGroup(
            twoPopVarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(twoPopVarPanelLayout.createSequentialGroup()
                .add(twoPopVarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableButtons2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(twoPopVarPanelLayout.createSequentialGroup()
                        .add(69, 69, 69)
                        .add(categoryPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(twoPopVarPanelLayout.createSequentialGroup()
                        .add(87, 87, 87)
                        .add(diffPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(twoPopVarPanelLayout.createSequentialGroup()
                        .add(99, 99, 99)
                        .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        twoPopVarPanelLayout.setVerticalGroup(
            twoPopVarPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(twoPopVarPanelLayout.createSequentialGroup()
                .add(variableButtons2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(categoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(diffPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(233, 233, 233))
        );

        tabsFrame.addTab("Define the Variable", twoPopVarPanel);

        variableButtonGroup.add(meanWithSTDButton);
        meanWithSTDButton.setText("Mean and Standard Deviation");
        meanWithSTDButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meanWithSTDButtonMouseClicked(evt);
            }
        });

        variableMeasureLabel.setText("The variable to be measured: ");

        variableButtonGroup.add(trialLengthButton);
        trialLengthButton.setText("Length of trial");
        trialLengthButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trialLengthButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(meanButton);
        meanButton.setText("Mean");
        meanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meanButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(containsSuccessButton);
        containsSuccessButton.setText("Whether Trial contains the 'success condition'");
        containsSuccessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                successButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(medianButton);
        medianButton.setText("Median");
        medianButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medianButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(sumButton);
        sumButton.setText("Sum");
        sumButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sumButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(numSuccessButton);
        numSuccessButton.setText("Number of successes in trial");
        numSuccessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                successButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(percentSuccessButton);
        percentSuccessButton.setText("Percent of trial that is a 'success'");
        percentSuccessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                successButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(modeButton);
        modeButton.setText("Mode");
        modeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modeButtonMouseClicked(evt);
            }
        });

        variableButtonGroup.add(modeSizeButton);
        modeSizeButton.setText("Mode size");
        modeSizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modeSizeButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableButtonsLayout = new org.jdesktop.layout.GroupLayout(variableButtons);
        variableButtons.setLayout(variableButtonsLayout);
        variableButtonsLayout.setHorizontalGroup(
            variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, variableButtonsLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(variableMeasureLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(containsSuccessButton)
                    .add(numSuccessButton)
                    .add(percentSuccessButton)
                    .add(trialLengthButton)
                    .add(sumButton))
                .add(18, 18, 18)
                .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(modeSizeButton)
                    .add(modeButton)
                    .add(medianButton)
                    .add(meanWithSTDButton)
                    .add(meanButton))
                .addContainerGap())
        );
        variableButtonsLayout.setVerticalGroup(
            variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableButtonsLayout.createSequentialGroup()
                .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableButtonsLayout.createSequentialGroup()
                        .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(containsSuccessButton)
                            .add(meanButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(meanWithSTDButton)
                            .add(numSuccessButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(medianButton)
                            .add(percentSuccessButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(trialLengthButton)
                            .add(modeButton)))
                    .add(variableButtonsLayout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(variableMeasureLabel)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sumButton)
                    .add(modeSizeButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        successButtonGroup.add(someOutcomesInSetButton);
        someOutcomesInSetButton.setText("At least one of the outcomes in a specified set");
        someOutcomesInSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableConditionButtonMouseClicked(evt);
            }
        });

        successButtonGroup.add(allOutcomesInSetButton);
        allOutcomesInSetButton.setText("All outcomes in a specified set");
        allOutcomesInSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableConditionButtonMouseClicked(evt);
            }
        });

        successButtonGroup.add(someOutcomesInOrderButton);
        someOutcomesInOrderButton.setText("One or more outcomes in a specific order");
        someOutcomesInOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableConditionButtonMouseClicked(evt);
            }
        });

        successConditionLabel.setText("Success Condition:");

        org.jdesktop.layout.GroupLayout variableSuccessConditionPanelLayout = new org.jdesktop.layout.GroupLayout(variableSuccessConditionPanel);
        variableSuccessConditionPanel.setLayout(variableSuccessConditionPanelLayout);
        variableSuccessConditionPanelLayout.setHorizontalGroup(
            variableSuccessConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, variableSuccessConditionPanelLayout.createSequentialGroup()
                .addContainerGap(209, Short.MAX_VALUE)
                .add(variableSuccessConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableSuccessConditionPanelLayout.createSequentialGroup()
                        .add(103, 103, 103)
                        .add(successConditionLabel))
                    .add(allOutcomesInSetButton)
                    .add(someOutcomesInSetButton)
                    .add(someOutcomesInOrderButton))
                .addContainerGap())
        );
        variableSuccessConditionPanelLayout.setVerticalGroup(
            variableSuccessConditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableSuccessConditionPanelLayout.createSequentialGroup()
                .add(successConditionLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(someOutcomesInOrderButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(allOutcomesInSetButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(someOutcomesInSetButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        variableNumTimesLabel1.setText("Number of times it must appear ");

        numDistinctOutcomeLabel1.setText("Number of Distinct Outcomes:");

        trialOutcomeLabel2.setText("Outcome");

        varSetUpdateButton.setText("Update");
        varSetUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varSetUpdateButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableOutcomesInSetPanelLayout = new org.jdesktop.layout.GroupLayout(variableOutcomesInSetPanel);
        variableOutcomesInSetPanel.setLayout(variableOutcomesInSetPanelLayout);
        variableOutcomesInSetPanelLayout.setHorizontalGroup(
            variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInSetPanelLayout.createSequentialGroup()
                        .add(84, 84, 84)
                        .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(trialOutcomeLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(trialOutcomeValueTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(46, 46, 46)
                        .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableNumTimesLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 233, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(trialNumTimesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(variableOutcomesInSetPanelLayout.createSequentialGroup()
                        .add(numDistinctOutcomeLabel1)
                        .add(18, 18, 18)
                        .add(numDistinctOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(varSetUpdateButton)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        variableOutcomesInSetPanelLayout.setVerticalGroup(
            variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInSetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(numDistinctOutcomeLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(varSetUpdateButton))
                    .add(numDistinctOutcomesTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeLabel2)
                    .add(variableNumTimesLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInSetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(trialOutcomeValueTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trialNumTimesTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        orderValueLabel1v2.setText("1");

        variableOutcomeLabel.setText("Outcome");

        variableOrderLabel.setText("Order");

        variablenumOutcomesLabel.setText("Number of Outcomes in sequences");

        varOrderUpdateButton.setText("Update");
        varOrderUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                varOrderUpdateButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout variableOutcomesInOrderPanelLayout = new org.jdesktop.layout.GroupLayout(variableOutcomesInOrderPanel);
        variableOutcomesInOrderPanel.setLayout(variableOutcomesInOrderPanelLayout);
        variableOutcomesInOrderPanelLayout.setHorizontalGroup(
            variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                        .add(variablenumOutcomesLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableNumOutcomesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(varOrderUpdateButton))
                    .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                        .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableOrderLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                                .add(9, 9, 9)
                                .add(orderValueLabel1v2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(24, 24, 24)
                        .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(outcomeTextField1v2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(variableOutcomeLabel))))
                .addContainerGap())
        );
        variableOutcomesInOrderPanelLayout.setVerticalGroup(
            variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableOutcomesInOrderPanelLayout.createSequentialGroup()
                        .add(variablenumOutcomesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(variableOrderLabel)
                            .add(variableOutcomeLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(outcomeTextField1v2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(orderValueLabel1v2)))
                    .add(variableOutcomesInOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(variableNumOutcomesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(varOrderUpdateButton)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        nextButton3.setText("Submit Variable");
        nextButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(nextButton3)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(nextButton3)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout variablePanelLayout = new org.jdesktop.layout.GroupLayout(variablePanel);
        variablePanel.setLayout(variablePanelLayout);
        variablePanelLayout.setHorizontalGroup(
            variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variablePanelLayout.createSequentialGroup()
                .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(variableButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(variableSuccessConditionPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(variablePanelLayout.createSequentialGroup()
                        .add(203, 203, 203)
                        .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(variableOutcomesInSetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(variableOutcomesInOrderPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(variablePanelLayout.createSequentialGroup()
                        .add(274, 274, 274)
                        .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        variablePanelLayout.setVerticalGroup(
            variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(variablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(variableButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableSuccessConditionPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInOrderPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(variableOutcomesInSetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Define the Variable", variablePanel);

        randIntTextArea.setColumns(20);
        randIntTextArea.setRows(5);
        jScrollPane1.setViewportView(randIntTextArea);

        runTrialsButton.setText("Run Trials");
        runTrialsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTrialsButtonActionPerformed(evt);
            }
        });

        numTrialLabel.setText("Number of trials to run: ");

        outcomeTextArea.setColumns(20);
        outcomeTextArea.setRows(5);
        jScrollPane2.setViewportView(outcomeTextArea);

        saveIntegersButton.setText("Save Integers To a file");
        saveIntegersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveIntegersButtonMouseClicked(evt);
            }
        });

        responseVariableTextArea.setColumns(20);
        responseVariableTextArea.setRows(5);
        jScrollPane3.setViewportView(responseVariableTextArea);

        randomIntegersLabel.setText("Random Integers");

        saveResponsesButton.setText("Save Response Variables to a File");
        saveResponsesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveResponsesButtonMouseClicked(evt);
            }
        });

        saveOutcomesButton.setText("Save Outcomes to a File");
        saveOutcomesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveOutcomesButtonMouseClicked(evt);
            }
        });

        responseVariableValues.setText("Response Variable Values");

        TrialOutcomesLabel.setText("Outcomes");

        runTrialsInfoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runTrialsInfoPanelMouseClicked(evt);
            }
        });
        runTrialsInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        displayGraphButton.setText("Display Graph");
        displayGraphButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayGraphButtonMouseClicked(evt);
            }
        });
        runTrialsInfoPanel.add(displayGraphButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        displayDescriptiveStatsButton.setText("Display Descriptive Stats");
        displayDescriptiveStatsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayDescriptiveStatsButtonMouseClicked(evt);
            }
        });
        runTrialsInfoPanel.add(displayDescriptiveStatsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        displayDistributionButton.setText("Display Distribution");
        displayDistributionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayDistributionButtonMouseClicked(evt);
            }
        });
        runTrialsInfoPanel.add(displayDistributionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        binButton.setText("Set Bins");
        binButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                binButtonMouseClicked(evt);
            }
        });
        runTrialsInfoPanel.add(binButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        setGraphTypeButton2.setText("Set Graph Type");
        setGraphTypeButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setGraphTypeButton2MouseClicked(evt);
            }
        });
        runTrialsInfoPanel.add(setGraphTypeButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 130, -1));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 113, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 30, Short.MAX_VALUE)
        );

        runTrialsInfoPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, 30));
        runTrialsInfoPanel.add(setBinSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 100, -1));

        org.jdesktop.layout.GroupLayout setBinPanelLayout = new org.jdesktop.layout.GroupLayout(setBinPanel);
        setBinPanel.setLayout(setBinPanelLayout);
        setBinPanelLayout.setHorizontalGroup(
            setBinPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        setBinPanelLayout.setVerticalGroup(
            setBinPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 30, Short.MAX_VALUE)
        );

        runTrialsInfoPanel.add(setBinPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, 30));

        graphTypeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graphTypeButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout runDisplayPanelLayout = new org.jdesktop.layout.GroupLayout(runDisplayPanel);
        runDisplayPanel.setLayout(runDisplayPanelLayout);
        runDisplayPanelLayout.setHorizontalGroup(
            runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(runDisplayPanelLayout.createSequentialGroup()
                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(runDisplayPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(runDisplayPanelLayout.createSequentialGroup()
                                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                                    .add(randomIntegersLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(runDisplayPanelLayout.createSequentialGroup()
                                        .add(21, 21, 21)
                                        .add(saveOutcomesButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(saveResponsesButton))
                                    .add(runDisplayPanelLayout.createSequentialGroup()
                                        .add(8, 8, 8)
                                        .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                                            .add(TrialOutcomesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                                            .add(responseVariableValues, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 181, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                            .add(runDisplayPanelLayout.createSequentialGroup()
                                .add(234, 234, 234)
                                .add(numTrialLabel)
                                .add(10, 10, 10)
                                .add(numTrialField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(runTrialsButton))
                            .add(runTrialsInfoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 766, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(runDisplayPanelLayout.createSequentialGroup()
                                .add(21, 21, 21)
                                .add(saveIntegersButton))))
                    .add(runDisplayPanelLayout.createSequentialGroup()
                        .add(355, 355, 355)
                        .add(graphTypeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        runDisplayPanelLayout.setVerticalGroup(
            runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(runDisplayPanelLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(runDisplayPanelLayout.createSequentialGroup()
                        .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(numTrialLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(runTrialsButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(responseVariableValues)
                            .add(TrialOutcomesLabel)
                            .add(randomIntegersLabel)))
                    .add(numTrialField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(runDisplayPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(saveIntegersButton)
                    .add(saveOutcomesButton)
                    .add(saveResponsesButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(runTrialsInfoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(graphTypeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(38, 38, 38))
        );

        org.jdesktop.layout.GroupLayout runPanelLayout = new org.jdesktop.layout.GroupLayout(runPanel);
        runPanel.setLayout(runPanelLayout);
        runPanelLayout.setHorizontalGroup(
            runPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(runPanelLayout.createSequentialGroup()
                .add(35, 35, 35)
                .add(runDisplayPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(438, 438, 438))
        );
        runPanelLayout.setVerticalGroup(
            runPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(runPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(runDisplayPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsFrame.addTab("Run Trials", runPanel);

        titleLabel.setText("Simulator!");

        jLabel1.setText("Population Info");

        jLabel2.setText("Variable Info");

        jLabel3.setText("jLabel1");

        jLabel4.setText("jLabel1");

        jLabel5.setText("Trial Summary");

        jLabel6.setText("Trial Info");

        org.jdesktop.layout.GroupLayout progressPanelLayout = new org.jdesktop.layout.GroupLayout(progressPanel);
        progressPanel.setLayout(progressPanelLayout);
        progressPanelLayout.setHorizontalGroup(
            progressPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(progressPanelLayout.createSequentialGroup()
                .add(progressPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(progressPanelLayout.createSequentialGroup()
                        .add(79, 79, 79)
                        .add(jLabel1)
                        .add(67, 67, 67)
                        .add(jLabel6)
                        .add(83, 83, 83)
                        .add(jLabel2)
                        .add(114, 114, 114)
                        .add(jLabel3)
                        .add(164, 164, 164)
                        .add(jLabel4))
                    .add(progressPanelLayout.createSequentialGroup()
                        .add(439, 439, 439)
                        .add(jLabel5)))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        progressPanelLayout.setVerticalGroup(
            progressPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(progressPanelLayout.createSequentialGroup()
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(progressPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel4)
                    .add(jLabel1)
                    .add(jLabel6)
                    .add(jLabel3))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(531, 531, 531)
                .add(titleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(tabsFrame, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE)
            .add(progressPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(titleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tabsFrame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 664, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(progressPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The Biggest Change.  This shifts the data tab into 2 columns and forces the next two to have different options.
     * @param evt   
     */
private void twoPopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPopButtonActionPerformed
    System.out.println("\n**** Two Populations ****");
    tabsFrame.remove(dataPanel);
    tabsFrame.remove(trialPanel);
    tabsFrame.remove(variablePanel);

    tabsFrame.insertTab("Define a Model", null, dataTwoPopPanel, null, 1);
    tabsFrame.insertTab("Define a Trial", null, twoPopTrialPanel, null, 2);

    tabsFrame.insertTab("Define the Variable ", null, twoPopVarPanel, null, 3);

    dataPop2Panel.setVisible(true);
    dataPop1Panel.setVisible(true);
    GUIHelper.clearDataFields(dataOutcomeListPanel1, dataOutcomeFields1, 0);
    GUIHelper.clearDataFields(dataOutcomeListPanel2, dataOutcomeFields2, 0);

    restoreDefaults();

    trialPop2Panel.setVisible(true);
    categoreyDiffButton.setVisible(false);
    meanDiffGroupButton.setVisible(false);

    varObsDiffMeanButton.setVisible(true);
    varSampleMeanDiffButton.setVisible(true);
    varNumMatchButton.setVisible(true);
    categoreyPopDiffButton.setVisible(true);

    conditionButton3.setVisible(true);
    conditionButton4.setVisible(true);
    trialStopConditionPanel3.setVisible(false);
    trialStopConditionPanel4.setVisible(false);
    trialFixedOutcomePanel4.setVisible(false);
    trialFixedOutcomePanel3.setVisible(false);


    fixedOutcomeButton3.setVisible(true);
    fixedOutcomeButton4.setVisible(true);
    trialTypeLabel3.setVisible(false);
    numObservationLabel4.setText("Num Observations");
    jLabel17.setText("Sample 1");
    jLabel18.setText("Sample 2");

    numObservationLabel4.setText("Number of Observations");
    dataTwoPopNextButton.setText("Submit Models");
    diffOption1.setText("Population 1 - Population 2");
    diffOption2.setText("Population 2 - Population 1");
    diffPanel.setVisible(false);

    trialAndButton.setVisible(true);
    trialOrButton.setVisible(true);
    jLabel7.setText("Population 1");
    numOutcomeField1.setValue(1);

    tp = new TwoPopTrialRunner();
}//GEN-LAST:event_twoPopButtonActionPerformed

private void listOutcomeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOutcomeButtonMouseClicked
    dataOutcomeListPanel.setVisible(true);
    dataListEnclosePanel.setVisible(true);

    dataExternalFilePanel.setVisible(false);
    dataDistPanel.setVisible(false);
    dataZero.one = outcomeNameField1;
    dataZero.two = lowerBoundField1;
    dataZero.three = upperBoundField1;
    dataZero.prbLabel = probabilityLabel1;
    dataOutcomeFields = new ArrayList<FieldStruct>();
    dataOutcomeFields.add(dataZero);

    withoutReplacementButton.setVisible(true);
    conditionButton.setVisible(true);
    fixedOutcomeButton.setSelected(false);
    withReplacementButton.setSelected(false);



}//GEN-LAST:event_listOutcomeButtonMouseClicked

private void distributionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distributionButtonMouseClicked

    dataOutcomeListPanel.setVisible(false);
    dataListEnclosePanel.setVisible(false);

    dataExternalFilePanel.setVisible(false);
    dataDistPanel.setVisible(true);
    conditionButton.setVisible(false);
    fixedOutcomeButton.setSelected(true);
    trialFixedOutcomePanel.setVisible(true);
    withoutReplacementButton.setVisible(false);
    withReplacementButton.setSelected(true);

}//GEN-LAST:event_distributionButtonMouseClicked

private void externalListButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_externalListButtonMouseClicked

    dataOutcomeListPanel.setVisible(false);
    dataListEnclosePanel.setVisible(false);

    dataExternalFilePanel.setVisible(true);
    dataDistPanel.setVisible(false);
    withoutReplacementButton.setVisible(true);
    conditionButton.setVisible(true);
    fixedOutcomeButton.setSelected(false);
    withReplacementButton.setSelected(false);


}//GEN-LAST:event_externalListButtonMouseClicked

private void displayBarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayBarButtonMouseClicked
    dataOutcomeList = new ArrayList<Outcome>();
    for (FieldStruct f : dataOutcomeFields) {
        Outcome o = new Outcome();
        System.out.println(f.one.getText());
        o.outcomeName = f.one.getText();
        o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
        dataOutcomeList.add(o);
    }
    Population p = new Population(dataOutcomeList);
    ChartLoader cL = new ChartLoader(ChartType.BARCHART);
    ChartPanel cP = cL.loadChart(p);
    JOptionPane.showMessageDialog(null, cP, "Bar Chart", 0);

}//GEN-LAST:event_displayBarButtonMouseClicked

    private boolean checkDataErrors() {
        try {

            if (listOutcomeButton.isSelected()) {
                int prev = Integer.parseInt(dataOutcomeFields.get(0).three.getText());
                for (int i = 1; i < dataOutcomeFields.size(); i++) {
                    FieldStruct f = dataOutcomeFields.get(i);

                    try {
                        System.out.println("Outcome: " + f.one.getText());
                        System.out.println("Lower Bound: " + f.two.getText());

                        System.out.println("Upper Bound: " + f.three.getText());
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Error loading data. check that all data fields are properly filled in");
                        return true;
                    }
                    int curr = Integer.parseInt(f.two.getText());
                    if (curr <= prev) {
                        JOptionPane.showMessageDialog(null, "Overlapping Data error.  Check that your ranges do not conflict (i.e. 1-2, 2-2) ");
                        return true;
                    } else {
                        prev = Integer.parseInt(f.three.getText());
                    }

                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number formatting error. Check that you entered whole numbers in every range text field.");

        }
        return false;
    }

    private boolean checkDataErrorsTwoPop() {
        try {

            if (listOutcomeButton1.isSelected()) {
                int prev = Integer.parseInt(dataOutcomeFields1.get(0).three.getText());
                for (int i = 1; i < dataOutcomeFields1.size(); i++) {
                    FieldStruct f = dataOutcomeFields1.get(i);

                    try {
                        System.out.println("Outcome: " + f.one.getText());
                        System.out.println("Lower Bound: " + f.two.getText());

                        System.out.println("Upper Bound: " + f.three.getText());
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Error loading data. check that all data fields are properly filled in");
                        return true;
                    }
                    int curr = Integer.parseInt(f.two.getText());
                    if (curr <= prev) {
                        JOptionPane.showMessageDialog(null, "Overlapping Data error.  Check that your ranges do not conflict (i.e. 1-2, 2-2) ");
                        return true;
                    } else {
                        prev = Integer.parseInt(f.three.getText());
                    }

                }
            }

            if (listOutcomeButton2.isSelected()) {
                int prev = Integer.parseInt(dataOutcomeFields2.get(0).three.getText());
                for (int i = 1; i < dataOutcomeFields2.size(); i++) {
                    FieldStruct f = dataOutcomeFields2.get(i);

                    try {
                        System.out.println("Outcome: " + f.one.getText());
                        System.out.println("Lower Bound: " + f.two.getText());

                        System.out.println("Upper Bound: " + f.three.getText());
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Error loading data. check that all data fields are properly filled in");
                        return true;
                    }
                    int curr = Integer.parseInt(f.two.getText());
                    if (curr <= prev) {
                        JOptionPane.showMessageDialog(null, "Overlapping Data error.  Check that your ranges do not conflict (i.e. 1-2, 2-2) ");
                        return true;
                    } else {
                        prev = Integer.parseInt(f.three.getText());
                    }

                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number formatting error. Check that you entered whole numbers in every range text field.");

        }
        return false;
    }

    private boolean checkTrialErrors() {
        if (withoutReplacementButton.isSelected()) {
            if (t.distType != null) {
                JOptionPane.showMessageDialog(null, "Cannot Sample without replacement from a continuous distribution", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        return false;
    }

    private boolean checkVariableErrors() {
        return false;
    }

private void successButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_successButtonMouseClicked

    if (containsSuccessButton.isSelected() == true) {
        variableSuccessConditionPanel.setVisible(true);
        variableOutcomesInSetPanel.setVisible(false);
        variableOutcomesInOrderPanel.setVisible(false);

    } else {
        variableSuccessConditionPanel.setVisible(false);
        variableOutcomesInOrderPanel.setVisible(true);
        orderValueLabel1v2.setVisible(true);

        variableOutcomesInSetPanel.setVisible(false);
    }


}//GEN-LAST:event_successButtonMouseClicked

    private void setAllTwoPopValues() {

        if (listOutcomeButton1.isSelected()) {
            System.out.println(dataOutcomeFields1.size());

            for (FieldStruct f : dataOutcomeFields1) {
                try {
                    System.out.println("Outcome: " + f.one.getText());
                    System.out.println("Lower Bound: " + f.two.getText());

                    System.out.println("Upper Bound: " + f.three.getText());
                } catch (NullPointerException e) {
                    System.out.println("data fields are empty");
                }
            }
            tp.distType1 = null;
            dataOutcomeList1 = new ArrayList<Outcome>();
            for (FieldStruct f : dataOutcomeFields1) {
                Outcome o = new Outcome();
                System.out.println(f.one);
                System.out.println(f.one.getText());
                o.outcomeName = f.one.getText();
                o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
                dataOutcomeList1.add(o);
            }
            Population p = new Population(dataOutcomeList1);
            tp.setPopulation1(p);
        } else if (externalListButton1.isSelected()) {
     
            System.out.println("loaded file");
      
            tp.distType1 = null;
        } else {
            dataOutcomeList1 = new ArrayList<Outcome>();

            String distType = "";
            tp.setDistVal1(Double.parseDouble(dataDistLowerBoundTextField1.getText()));
            tp.setDistVal2(Double.parseDouble(dataDistUpperBoundTextField1.getText()));
            if (uniformDistButton1.isSelected()) {
                distType = "uniform";
            } else if (binomialButton1.isSelected()) {
                distType = "binomial";
            } else if (normalDistButton1.isSelected()) {
                distType = "norm";
            } else if (expButton1.isSelected()) {
                distType = "exp";
            }
            tp.setDistType(distType);


        }

        if (twoPopButton.isSelected()) {
            if (listOutcomeButton2.isSelected()) {
                System.out.println(dataOutcomeFields2.size());


                for (FieldStruct f : dataOutcomeFields2) {
                    try {
                        System.out.println("Outcome: " + f.one.getText());
                        System.out.println("Lower Bound: " + f.two.getText());

                        System.out.println("Upper Bound: " + f.three.getText());
                    } catch (NullPointerException e) {
                        System.out.println("data fields are empty");
                    }

                }
                tp.distType = null;
                dataOutcomeList2 = new ArrayList<Outcome>();
                for (FieldStruct f : dataOutcomeFields2) {
                    Outcome o = new Outcome();
                    System.out.println(f.one.getText());
                    o.outcomeName = f.one.getText();
                    o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
                    dataOutcomeList2.add(o);
                }
                Population p = new Population(dataOutcomeList2);
                tp.setPopulation2(p);
            } else if (externalListButton2.isSelected()) {
   
                tp.distType = null;
            } else {
                dataOutcomeList2 = new ArrayList<Outcome>();

                String distType = "";
                tp.setDistVal1(Double.parseDouble(lowerBoundField3.getText()));
                tp.setDistVal2(Double.parseDouble(upperBoundField3.getText()));
                if (uniformDistButton1.isSelected()) {
                    distType = "uniform";
                } else if (binomialButton1.isSelected()) {
                    distType = "binomial";
                } else if (normalDistButton1.isSelected()) {
                    distType = "norm";
                } else if (expButton1.isSelected()) {
                    distType = "exp";
                }
                tp.setDistType(distType);
            }
        } else if (identicalPopButton.isSelected()) {
            tp.setPopulation2(new Population(tp.getPopulation1()));
        } else {
        }

        if (twoGroupButton.isSelected()) {
            System.out.println("Setting Two group vales...");
            tp.s1.isFixedOutcomes = true;
            tp.s1.setNumObservations((Integer) (trialNumObservationTextField1.getValue()));
            tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));

            if (withReplacementButton3.isSelected()) {
                tp.s1.setIsWithReplacement(true);
            } else {
                tp.s1.setIsWithReplacement(false);

            }

            if (withReplacementButton4.isSelected()) {
                tp.s2.setIsWithReplacement(true);
            } else {
                tp.s2.setIsWithReplacement(false);

            }
        } else if (!checkTwoPopTrialErrors()) {
            System.out.println("loading settings");
            tp.ANDOR = "and";
            if (withReplacementButton3.isSelected()) {
                tp.s1.setIsWithReplacement(true);
            } else {
                tp.s1.setIsWithReplacement(false);
            }
            if (fixedOutcomeButton3.isSelected()) {
                System.out.println("Fixed observations button selected");
                tp.s1.isFixedOutcomes = true; //isFixedOutcomes(true);
                tp.s1.setNumObservations((Integer) (trialNumObservationTextField1.getValue()));
            } else {
                tp.s1.isFixedOutcomes = false;

                String stopCond = "";
                if (trialSomeOutcomesInOrderButton3.isSelected()) {
                    stopCond = "someOrder";
                    trialStopCondList = new ArrayList<Outcome>();
                    for (FieldStruct f : trialInOrderOutcomeFields1) {
                        Outcome o = new Outcome();
                        o.outcomeName = f.one.getText();
                        trialStopCondList.add(o);
                    }
                    tp.s1.stopCondOutcomes = trialStopCondList;
                } else if (trialSomeOutcomesInSetButton3.isSelected()) {
                    trialStopCondSet = new HashMap<String, Integer>();

                    for (FieldStruct f : trialInSetOutcomeFields1) {
                        System.out.println(f.one.getText());

                        System.out.println(f.two.getText());
                    }
                    for (FieldStruct f : trialInSetOutcomeFields1) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    tp.s1.stopOutcomeSet = trialStopCondSet;
                    stopCond = "someSet";
                } else {
                    trialStopCondSet = new HashMap<String, Integer>();
                    for (FieldStruct f : trialInSetOutcomeFields1) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        System.out.println(f.one.getText());
                        System.out.println(f.two.getText());
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    tp.s1.stopOutcomeSet = trialStopCondSet;
                    stopCond = "allSet";
                }
                tp.s1.stopConditionType = stopCond;
            }

            if (withReplacementButton4.isSelected()) {
                tp.s2.setIsWithReplacement(true);
            } else {
                tp.s2.setIsWithReplacement(false);
            }
            if (fixedOutcomeButton4.isSelected()) {
                System.out.println("Fixed observations button selected");
                tp.s2.isFixedOutcomes = true; //isFixedOutcomes(true);
                tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));
            } else {
                tp.s2.isFixedOutcomes = false;

                String stopCond = "";
                if (trialSomeOutcomesInOrderButton4.isSelected()) {
                    stopCond = "someOrder";
                    trialStopCondList = new ArrayList<Outcome>();
                    for (FieldStruct f : trialInOrderOutcomeFields2) {
                        Outcome o = new Outcome();
                        o.outcomeName = f.one.getText();
                        trialStopCondList.add(o);
                    }
                    tp.s2.stopCondOutcomes = trialStopCondList; //setStopCondOutcomes(trialStopCondList);
                } else if (trialSomeOutcomesInSetButton4.isSelected()) {
                    trialStopCondSet = new HashMap<String, Integer>();

                    for (FieldStruct f : trialInSetOutcomeFields2) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    tp.s2.stopOutcomeSet = trialStopCondSet;
                    stopCond = "someSet";
                } else {
                    trialStopCondSet = new HashMap<String, Integer>();
                    for (FieldStruct f : trialInSetOutcomeFields2) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    tp.s2.stopOutcomeSet = trialStopCondSet;
                    stopCond = "allSet";
                }
                tp.s2.stopConditionType = stopCond;
            }

        } else {
        }


        if (withReplacementButton4.isSelected()) {
            tp.s2.setIsWithReplacement(true);
        } else {
            tp.s2.setIsWithReplacement(false);
        }
        if (fixedOutcomeButton4.isSelected()) {
            System.out.println("Fixed observations button selected");
                   tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));
        } else {
        }

        if (varNumMatchButton.isSelected()) {
            tp.varCase = 1;
        } else if (varObsDiffMeanButton.isSelected()) {
            tp.varCase = 8;
            tp.populationType = "categorical";
        } else if (varSampleMeanDiffButton.isSelected()) {
            tp.varCase = 7;
            tp.populationType = "numerical";
        } else if (categoreyDiffButton.isSelected()) {
            tp.populationType = "numerical";
            tp.varCase = 5;


        } else if (meanDiffGroupButton.isSelected()) {
            tp.varCase = 4;
        } else if (categoreyPopDiffButton.isSelected()) {
            tp.category = varSelecteCategoryTextField.getText();
            tp.varCase = 7;
            tp.populationType = "categorical";
        }

    }

    private void setAllValues() {
        System.out.println("\n**** Set All Values ****");
        if (onePopButton1.isSelected()) {
            t.setNumTrials((Integer) numTrialField.getValue());
            if (listOutcomeButton.isSelected()) {
                System.out.println(dataOutcomeFields.size());


                for (FieldStruct f : dataOutcomeFields) {
                    try {
                        System.out.println("Outcome: " + f.one.getText());
                        System.out.println("Lower Bound: " + f.two.getText());

                        System.out.println("Upper Bound: " + f.three.getText());
                    } catch (NullPointerException e) {
                        System.out.println("data fields are empty");
                    }

                }
                t.distType = null;
                dataOutcomeList = new ArrayList<Outcome>();
                for (FieldStruct f : dataOutcomeFields) {
                    Outcome o = new Outcome();
                    System.out.println(f.one.getText());
                    o.outcomeName = f.one.getText();
                    o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
                    dataOutcomeList.add(o);
                }
                Population p = new Population(dataOutcomeList);
                t.setPopulation(p);
            } else if (externalListButton.isSelected()) {
                System.out.println("loaded file");
                t.distType = null;
            } else {
                dataOutcomeList = new ArrayList<Outcome>();

                String distType = "";
                t.setDistVal1(Double.parseDouble(dataDistLowerBoundTextField.getText()));
                t.setDistVal2(Double.parseDouble(dataDistUpperBoundTextField.getText()));
                if (uniformDistButton.isSelected()) {
                    distType = "uniform";
                } else if (binomialButton.isSelected()) {
                    distType = "binomial";
                } else if (normalDistButton.isSelected()) {
                    distType = "norm";
                } else if (expButton.isSelected()) {
                    distType = "exp";
                }
                t.setDistType(distType);
            }
            if (withReplacementButton.isSelected()) {
                t.setIsWithReplacement(true);
            } else {
                t.setIsWithReplacement(false);
            }
            if (fixedOutcomeButton.isSelected()) {
                System.out.println("Fixed observations button selected");
                t.setIsFixedOutcomes(true);
                t.setNumObservations((Integer) (numObservationTextField.getValue()));
            } else {
                t.setIsFixedOutcomes(false);

                String stopCond = "";
                if (trialSomeOutcomesInOrderButton.isSelected()) {
                    stopCond = "someOrder";
                    trialStopCondList = new ArrayList<Outcome>();
                    for (FieldStruct f : trialInOrderOutcomeFields) {
                        Outcome o = new Outcome();
                        o.outcomeName = f.one.getText();
                        trialStopCondList.add(o);
                    }
                    t.setStopCondOutcomes(trialStopCondList);
                } else if (trialSomeOutcomesInSetButton.isSelected()) {
                    trialStopCondSet = new HashMap<String, Integer>();

                    for (FieldStruct f : trialInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    t.stopOutcomeSet = trialStopCondSet;
                    stopCond = "someSet";
                } else {
                    trialStopCondSet = new HashMap<String, Integer>();
                    for (FieldStruct f : trialInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        trialStopCondSet.put(name, num);
                    }
                    t.setStopCondSet(trialStopCondSet);
                    stopCond = "allSet";
                }
                t.setStopConditionType(stopCond);

            }
            String varType = "";
            if (containsSuccessButton.isSelected()) {
                String successCond = "";
                varSuccessList = new ArrayList<Outcome>();
                if (someOutcomesInOrderButton.isSelected()) {
                    successCond = "someOrder";
                    for (FieldStruct f : varInOrderOutcomeFields) {
                        Outcome o = new Outcome();
                        o.outcomeName = f.one.getText();
                        varSuccessList.add(o);
                    }
                    t.setSuccessOutcomes(varSuccessList);

                } else if (someOutcomesInSetButton.isSelected()) {
                    varSuccessCondSet = new HashMap<String, Integer>();

                    for (FieldStruct f : varInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        varSuccessCondSet.put(name, num);
                    }
                    t.setSuccessSet(varSuccessCondSet);
                    successCond = "someSet";
                } else {
                    varSuccessCondSet = new HashMap<String, Integer>();

                    for (FieldStruct f : varInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                        String name = f.one.getText();
                        int num = Integer.parseInt(f.two.getText());
                        varSuccessCondSet.put(name, num);
                    }
                    t.setSuccessSet(varSuccessCondSet);
                    successCond = "allSet";
                }
                t.setSuccessCondType(successCond);

                varType = "containsSuccess";
            } else if (numSuccessButton.isSelected()) {
                varSuccessList = new ArrayList<Outcome>();

                for (FieldStruct f : varInOrderOutcomeFields) {
                    Outcome o = new Outcome();

                    o.outcomeName = f.one.getText();
                    varSuccessList.add(o);
                }
                t.setSuccessOutcomes(varSuccessList);

                varType = "numSuccess";
            } else if (percentSuccessButton.isSelected()) {
                varSuccessList = new ArrayList<Outcome>();

                for (FieldStruct f : varInOrderOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    Outcome o = new Outcome();

                    o.outcomeName = f.one.getText();
                    varSuccessList.add(o);
                }
                t.setSuccessOutcomes(varSuccessList);
                varType = "percentSuccess";
            } else if (trialLengthButton.isSelected()) {
                varType = "length";
            } else if (meanButton.isSelected()) {
                varType = "mean";
            } else if (medianButton.isSelected()) {
                varType = "median";
            } else if (meanWithSTDButton.isSelected()) {
                varType = "mean+std";
            } else if (modeButton.isSelected()) {
                varType = "mode";
            } else if (modeSizeButton.isSelected()) {
                varType = "modeSize";
            } else if (sumButton.isSelected()) {
                varType = "sum";
            }


            t.setVariableType(varType);
        }
    }
private void saveIntegersButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveIntegersButtonMouseClicked
    JFileChooser fc = new JFileChooser();
    int val = fc.showSaveDialog(null);
    File selectedFile = fc.getSelectedFile();
    BufferedWriter out;
    try {
        out = new BufferedWriter(new FileWriter(selectedFile));
        if (onePopButton1.isSelected()) {
            out.write(t.displayRandNumbers().toString());
        } else {
            out.write(tp.displayRandNumbers().toString());
        }
        out.close();
    } catch (IOException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
    }


}//GEN-LAST:event_saveIntegersButtonMouseClicked

private void saveOutcomesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveOutcomesButtonMouseClicked
    JFileChooser fc = new JFileChooser();
    int val = fc.showSaveDialog(null);
    File selectedFile = fc.getSelectedFile();
    BufferedWriter out;
    try {
        out = new BufferedWriter(new FileWriter(selectedFile));
        if (onePopButton1.isSelected()) {
            out.write(t.displayOutcomes().toString());
        } else {
            out.write(tp.displayOutcomes().toString());
        }

        out.close();
    } catch (IOException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

}//GEN-LAST:event_saveOutcomesButtonMouseClicked

private void saveResponsesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveResponsesButtonMouseClicked
    JFileChooser fc = new JFileChooser();
    int val = fc.showSaveDialog(null);
    File selectedFile = fc.getSelectedFile();
    BufferedWriter out;
    try {
        out = new BufferedWriter(new FileWriter(selectedFile));
        if (onePopButton1.isSelected()) {
            out.write(t.displayResponseVariables().toString());
        } else {
            out.write(tp.displayResponseVariables().toString());
        }
        out.close();
    } catch (IOException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

}//GEN-LAST:event_saveResponsesButtonMouseClicked

private void variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_variableConditionButtonMouseClicked
    if (someOutcomesInOrderButton.isSelected() == true) {
        variableOutcomesInOrderPanel.setVisible(true);
        orderValueLabel1v2.setVisible(true);

        variableOutcomesInSetPanel.setVisible(false);
    } else {
        variableOutcomesInOrderPanel.setVisible(false);
        variableOutcomesInSetPanel.setVisible(true);

    }
}//GEN-LAST:event_variableConditionButtonMouseClicked

private void upperBoundField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperBoundField1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_upperBoundField1ActionPerformed

private void trialSomeOutcomesInOrderButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButtonMouseClicked
// TODO add your handling code here:
    trialVariableOutcomesInSetPanel.setVisible(false);
    trialVariableOutcomesInOrderPanel.setVisible(true);

}//GEN-LAST:event_trialSomeOutcomesInOrderButtonMouseClicked

private void trialSomeOutcomesInSetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButtonMouseClicked
    trialVariableOutcomesInSetPanel.setVisible(true);
    trialVariableOutcomesInOrderPanel.setVisible(false);



}//GEN-LAST:event_trialSomeOutcomesInSetButtonMouseClicked

private void trialAllOutcomesInSetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialAllOutcomesInSetButtonMouseClicked
    trialVariableOutcomesInSetPanel.setVisible(true);
    trialVariableOutcomesInOrderPanel.setVisible(false);



}//GEN-LAST:event_trialAllOutcomesInSetButtonMouseClicked

private void conditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionButtonMouseClicked

    trialFixedOutcomePanel.setVisible(false);
    trialStopConditionPanel.setVisible(true);

}//GEN-LAST:event_conditionButtonMouseClicked

private void fixedOutcomeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fixedOutcomeButtonMouseClicked
    trialFixedOutcomePanel.setVisible(true);
    //trialVariableOutcomesInSetPanel.setVisible(false);

    trialStopConditionPanel.setVisible(false);
    trialFixedOutcomePanel.setVisible(true);

}//GEN-LAST:event_fixedOutcomeButtonMouseClicked

private void withoutReplacementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutReplacementButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_withoutReplacementButtonActionPerformed

private void trialSomeOutcomesInSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialSomeOutcomesInSetButtonActionPerformed

private void selectExternalFileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectExternalFileButtonMouseClicked
    JFileChooser fc = new JFileChooser();
    int val = fc.showOpenDialog(null);
    File selectedFile = fc.getSelectedFile();
    Population p = new Population(selectedFile);
    t.setPopulation(p);

}//GEN-LAST:event_selectExternalFileButtonMouseClicked

private void displayHistogramButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayHistogramButtonMouseClicked

    ChartLoader cL = new ChartLoader(ChartType.BARCHART);
    ChartPanel cP = cL.loadChart(t.p);
    //JOptionPane.showMessageD
    JOptionPane.showMessageDialog(null, cP, "Bar Chart", 0);
}//GEN-LAST:event_displayHistogramButtonMouseClicked

private void twoPoplistOutcomeButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPoplistOutcomeButton1MouseClicked
    dataOutcomeListPanel1.setVisible(true);
    dataListEnclosePanel1.setVisible(true);

    dataDistPanel1.setVisible(false);
    dataExternalFilePanel1.setVisible(false);
    dataZero1.one = outcomeNameField2;
    dataZero1.two = lowerBoundField2;
    dataZero1.three = upperBoundField2;
    dataZero1.prbLabel = dataTwoPopPrbLabel1;
    dataOutcomeFields1 = new ArrayList<FieldStruct>();
    dataOutcomeFields1.add(dataZero1);
    //GUIHelper.clearDataFields(dataOutcomeListPanel1, dataOutcomeFields1, 0);

}//GEN-LAST:event_twoPoplistOutcomeButton1MouseClicked

private void distributionButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distributionButton1MouseClicked
    dataOutcomeListPanel1.setVisible(false);
    dataListEnclosePanel1.setVisible(false);

    dataDistPanel1.setVisible(true);
    dataExternalFilePanel1.setVisible(false);
}//GEN-LAST:event_distributionButton1MouseClicked

private void externalListButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_externalListButton1MouseClicked
    dataOutcomeListPanel1.setVisible(false);
    dataListEnclosePanel1.setVisible(false);

    dataDistPanel1.setVisible(false);
    dataExternalFilePanel1.setVisible(true);
}//GEN-LAST:event_externalListButton1MouseClicked

private void selectExternalFileButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectExternalFileButton1MouseClicked

    JFileChooser fc = new JFileChooser();
    int val = fc.showOpenDialog(null);
    File selectedFile = fc.getSelectedFile();
    Population p = new Population(selectedFile);
    tp.setPopulation1(p);

    if (identicalPopButton.isSelected()) {
        tp.setPopulation2(new Population(p));
    }

}//GEN-LAST:event_selectExternalFileButton1MouseClicked

private void displayHistogramButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayHistogramButton1MouseClicked
    ChartLoader cL = new ChartLoader(ChartType.BARCHART);
    ChartPanel cP = cL.loadChart(tp.p1);
    JOptionPane.showMessageDialog(null, cP, "Bar Chart", 0);
}//GEN-LAST:event_displayHistogramButton1MouseClicked

private void dataUpdateOutcomeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataUpdateOutcomeButtonMouseClicked
    System.out.println("\n**************update called******************");

    GUIHelper.dataFieldLoader(dataOutcomeListPanel, dataOutcomeFields, (Integer) numOutcomeField.getValue(), 0);
    for (FieldStruct f : dataOutcomeFields) {
        f.one.setVisible(true);
    }

    dataPanel.revalidate();

}//GEN-LAST:event_dataUpdateOutcomeButtonMouseClicked

private void dataDistUpperBoundTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataDistUpperBoundTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_dataDistUpperBoundTextFieldActionPerformed

private void twoPoplistOutcomeButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPoplistOutcomeButton2MouseClicked
    dataOutcomeListPanel2.setVisible(true);
    dataListEnclosePanel2.setVisible(true);

    dataDistPanel2.setVisible(false);
    dataExternalFilePanel2.setVisible(false);
    dataZero2.one = outcomeNameField3;
    dataZero2.two = lowerBoundField3;
    dataZero2.three = upperBoundField3;
    dataZero2.prbLabel = dataTwoPopPrbLabel2;
    //  GUIHelper.clearDataFields(dataOutcomeListPanel2, dataOutcomeFields2, 0);

    dataOutcomeFields2 = new ArrayList<FieldStruct>();
    dataOutcomeFields2.add(dataZero2);
}//GEN-LAST:event_twoPoplistOutcomeButton2MouseClicked

private void distributionButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distributionButton2MouseClicked
    dataOutcomeListPanel2.setVisible(false);
    dataListEnclosePanel2.setVisible(false);

    dataDistPanel2.setVisible(true);
    dataExternalFilePanel2.setVisible(false);
}//GEN-LAST:event_distributionButton2MouseClicked

private void externalListButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_externalListButton2MouseClicked
    dataOutcomeListPanel2.setVisible(false);
    dataListEnclosePanel2.setVisible(false);

    dataDistPanel2.setVisible(false);
    dataExternalFilePanel2.setVisible(true);
}//GEN-LAST:event_externalListButton2MouseClicked

private void upperBoundField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperBoundField3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_upperBoundField3ActionPerformed

private void expButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_expButton2ActionPerformed

private void nextButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton5MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_nextButton5MouseClicked

private void withoutReplacementButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutReplacementButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_withoutReplacementButton1ActionPerformed

private void fixedOutcomeButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fixedOutcomeButton1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_fixedOutcomeButton1MouseClicked

private void conditionButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionButton1MouseClicked
    trialStopConditionPanel1.setVisible(true);
    trialFixedOutcomePanel1.setVisible(false);
}//GEN-LAST:event_conditionButton1MouseClicked

private void conditionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_conditionButton1ActionPerformed

private void withoutReplacementButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutReplacementButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_withoutReplacementButton2ActionPerformed

private void fixedOutcomeButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fixedOutcomeButton2MouseClicked
    trialStopConditionPanel2.setVisible(false);
    trialFixedOutcomePanel2.setVisible(true);
}//GEN-LAST:event_fixedOutcomeButton2MouseClicked

private void conditionButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionButton2MouseClicked
    trialStopConditionPanel2.setVisible(true);
    trialFixedOutcomePanel2.setVisible(false);
}//GEN-LAST:event_conditionButton2MouseClicked

private void conditionButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_conditionButton2ActionPerformed

private void trialAllOutcomesInSetButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialAllOutcomesInSetButton1MouseClicked
    trialVariableOutcomesInOrderPanel1.setVisible(false);
    trialVariableOutcomesInSetPanel1.setVisible(true);

}//GEN-LAST:event_trialAllOutcomesInSetButton1MouseClicked

private void trialSomeOutcomesInSetButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton1MouseClicked
    trialVariableOutcomesInOrderPanel1.setVisible(false);
    trialVariableOutcomesInSetPanel1.setVisible(true);

}//GEN-LAST:event_trialSomeOutcomesInSetButton1MouseClicked

private void trialSomeOutcomesInSetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialSomeOutcomesInSetButton1ActionPerformed

private void trialSomeOutcomesInOrderButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButton1MouseClicked
    trialVariableOutcomesInOrderPanel1.setVisible(true);
    trialOrderValueLabel1.setVisible(true);
    trialOrderValueLabel1.validate();
    trialVariableOutcomesInSetPanel1.setVisible(false);
}//GEN-LAST:event_trialSomeOutcomesInOrderButton1MouseClicked

private void numDistinctOutcomeTextField2none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField2none
}//GEN-LAST:event_numDistinctOutcomeTextField2none

private void numDistinctOutcomeTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField2ActionPerformed

private void numDistinctOutcomeTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField2KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField2KeyTyped

private void trialNumOutcomesTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField1MouseClicked

private void trialNumOutcomesTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField1ActionPerformed

private void trialNumOutcomesTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField1KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField1KeyTyped

private void trialAllOutcomesInSetButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialAllOutcomesInSetButton2MouseClicked
// TODO add your handling code here:
    trialVariableOutcomesInOrderPanel2.setVisible(false);
    trialVariableOutcomesInSetPanel2.setVisible(true);

}//GEN-LAST:event_trialAllOutcomesInSetButton2MouseClicked

private void trialSomeOutcomesInSetButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton2MouseClicked
// TODO add your handling code here:
    trialVariableOutcomesInOrderPanel2.setVisible(false);
    trialVariableOutcomesInSetPanel2.setVisible(true);
}//GEN-LAST:event_trialSomeOutcomesInSetButton2MouseClicked

private void trialSomeOutcomesInSetButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialSomeOutcomesInSetButton2ActionPerformed

private void trialSomeOutcomesInOrderButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButton2MouseClicked
    trialVariableOutcomesInOrderPanel2.setVisible(true);
    trialVariableOutcomesInSetPanel2.setVisible(false);
}//GEN-LAST:event_trialSomeOutcomesInOrderButton2MouseClicked

private void numDistinctOutcomeTextField3none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField3none
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField3none

private void numDistinctOutcomeTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField3ActionPerformed

private void numDistinctOutcomeTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField3KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField3KeyTyped

private void trialNumOutcomesTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField2MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField2MouseClicked

private void trialNumOutcomesTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField2ActionPerformed

private void trialNumOutcomesTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trialNumOutcomesTextField2KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_trialNumOutcomesTextField2KeyTyped

private void varPrcSuccessInEachButtonsuccessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varPrcSuccessInEachButtonsuccessButtonMouseClicked
    variableSuccessConditionPanel1.setVisible(true);
    variableSuccessConditionPanel2.setVisible(true);

}//GEN-LAST:event_varPrcSuccessInEachButtonsuccessButtonMouseClicked

private void varDiffPrcSuccessButtonsuccessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varDiffPrcSuccessButtonsuccessButtonMouseClicked
    variableSuccessConditionPanel1.setVisible(true);
    variableSuccessConditionPanel2.setVisible(true);
}//GEN-LAST:event_varDiffPrcSuccessButtonsuccessButtonMouseClicked

private void varMeanInEachButtonsuccessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varMeanInEachButtonsuccessButtonMouseClicked
    variableSuccessConditionPanel1.setVisible(false);
    variableSuccessConditionPanel2.setVisible(false);
    variableOutcomesInSetPanel1.setVisible(false);
    variableOutcomesInSetPanel2.setVisible(false);
    variableOutcomesInOrderPanel1.setVisible(false);
    variableOutcomesInOrderPanel2.setVisible(false);
}//GEN-LAST:event_varMeanInEachButtonsuccessButtonMouseClicked

private void someOutcomesInSetButton1variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_someOutcomesInSetButton1variableConditionButtonMouseClicked
    variableOutcomesInSetPanel1.setVisible(true);
    variableOutcomesInOrderPanel1.setVisible(false);
}//GEN-LAST:event_someOutcomesInSetButton1variableConditionButtonMouseClicked

private void allOutcomesInSetButton1variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allOutcomesInSetButton1variableConditionButtonMouseClicked
    variableOutcomesInSetPanel1.setVisible(true);
    variableOutcomesInOrderPanel1.setVisible(false);
}//GEN-LAST:event_allOutcomesInSetButton1variableConditionButtonMouseClicked

private void someOutcomesInOrderButton1variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_someOutcomesInOrderButton1variableConditionButtonMouseClicked
// TODO add your handling code here:
    variableOutcomesInSetPanel1.setVisible(false);
    variableOutcomesInOrderPanel1.setVisible(true);
}//GEN-LAST:event_someOutcomesInOrderButton1variableConditionButtonMouseClicked

private void numDistinctOutcomeTextField4none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField4none
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField4none

private void numDistinctOutcomeTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField4ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField4ActionPerformed

private void numDistinctOutcomeTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField4KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField4KeyTyped

private void nextButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton6MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_nextButton6MouseClicked

private void variableNumOutcomesTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_variableNumOutcomesTextField1KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_variableNumOutcomesTextField1KeyTyped

private void variableTwoPopPanelvariableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_variableTwoPopPanelvariableConditionButtonMouseClicked
}//GEN-LAST:event_variableTwoPopPanelvariableConditionButtonMouseClicked

private void displayDescriptiveStatsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayDescriptiveStatsButtonMouseClicked
    String stats;
    if (onePopButton1.isSelected()) {
        stats = t.getDescriptiveStats();
    } else {
        stats = tp.getDescriptiveStats();
    }
    Object options[] = {"OK", "Save"};


    final JOptionPane pane = new JOptionPane(stats,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            options);

    final JFrame container = new JFrame();
    final JDialog dialog = new JDialog(container, "Descriptive Stats", false);
    dialog.setContentPane(pane);
    SaveTextPropertyListener scpl = new SaveTextPropertyListener(stats, dialog, pane);

    pane.addPropertyChangeListener(
            scpl);

    System.out.println("");
    dialog.pack();
    dialog.setVisible(true);
}//GEN-LAST:event_displayDescriptiveStatsButtonMouseClicked

private void displayDistributionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayDistributionButtonMouseClicked
    String stats;
    if (onePopButton1.isSelected()) {
        stats = t.getDistribution();
    } else {
        stats = tp.getDistribution();
    }
    Object options[] = {"OK", "Save"};

    JOptionPane pane = new JOptionPane(stats,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            options);

    final JFrame container = new JFrame();
    container.setPreferredSize(new Dimension(80, 150));
    JDialog dialog = new JDialog(container, "Descriptive Stats", false);

    dialog.setModalityType(ModalityType.MODELESS);


    JScrollPane jspane = new JScrollPane(pane,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jspane.getVerticalScrollBar().setBlockIncrement(5);
    jspane.getVerticalScrollBar().setAutoscrolls(true);




    dialog.setContentPane(jspane);
    SaveTextPropertyListener scpl = new SaveTextPropertyListener(stats, dialog, pane);
    pane.addPropertyChangeListener(
            scpl);
    System.out.println("");
    dialog.pack();
    dialog.setVisible(true);


}//GEN-LAST:event_displayDistributionButtonMouseClicked

private void trialUpdateSetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialUpdateSetButtonMouseClicked
    System.out.println("\n***********************update called***************************");
    int numOutcomes = 0;
    int xSpacing = 2;
    int ySpacing = 2;
    int i = 0;
    int start = 0;
    try {
        numOutcomes = (Integer) numDistinctOutcomeTextField.getValue();
        System.out.println("Number of Outcomes:" + numOutcomes);
        System.out.println("List Size:" + trialInSetOutcomeFields.size());

        boolean add = true;

        if (trialInSetOutcomeFields.size() > numOutcomes) {
            int numRemove = trialInSetOutcomeFields.size() - numOutcomes;
            for (i = 0; i < numRemove; i++) {
                FieldStruct f = trialInSetOutcomeFields.remove(trialInSetOutcomeFields.size() - 1);

                trialVariableOutcomesInSetPanel.remove(f.one);
                trialVariableOutcomesInSetPanel.remove(f.two);

                f.one.setVisible(false);
                f.two.setVisible(false);

            }
            System.out.println("Case 1: Trimmed box list " + trialInSetOutcomeFields.size());
            trialVariableOutcomesInSetPanel.validate();
            trialPanel.validate();
            add = false;

        } else if (trialInSetOutcomeFields.size() < numOutcomes) {
            start = trialInSetOutcomeFields.size();
            add = true;

        } else if (trialInSetOutcomeFields.size() == numOutcomes) {
            add = false;
        }
        if (add) {
            System.out.println("Starting Position: " + start);
         
            FieldStruct one = trialInSetOutcomeFields.get(trialInSetOutcomeFields.size() - 1);
            System.out.println("the first: " + one.one.getX());
            int nameX = one.one.getX(); //+ outcomeNameField1.getHeight() + xSpacing;
            int lowerX = one.two.getX();

            int nameY = one.one.getY() + one.one.getHeight() + ySpacing;
            int lowerY = one.two.getY() + one.two.getHeight() + ySpacing;

            System.out.println("processed init stuff");
            for (i = start; i < numOutcomes; i++) {

                JTextField anyn1 = new JTextField();
                anyn1.setSize(one.one.getWidth(), one.one.getHeight());
                anyn1.setLocation(nameX, nameY);
                trialVariableOutcomesInSetPanel.add(anyn1);

                JTextField anyn2 = new JTextField();
                anyn2.setSize(one.two.getWidth(), one.two.getHeight());
                anyn2.setLocation(lowerX, lowerY);
                trialVariableOutcomesInSetPanel.add(anyn2);


                nameY = nameY + one.one.getHeight() + ySpacing;
                lowerY = lowerY + one.two.getHeight() + ySpacing;

                FieldStruct struct = new FieldStruct();

                trialInSetOutcomeFields.add(struct);
                struct.one = anyn1;
                struct.two = anyn2;
                System.out.println("processed button ");

            }

            FieldStruct last = trialInSetOutcomeFields.get(trialInSetOutcomeFields.size() - 1);
            int height = (last.one.getHeight() + ySpacing) * trialInSetOutcomeFields.size() + 20;
            trialVariableOutcomesInSetPanel.setBounds(trialVariableOutcomesInSetPanel.getX(), trialVariableOutcomesInSetPanel.getY(), trialVariableOutcomesInSetPanel.getWidth(), height);

            trialVariableOutcomesInSetPanel.validate();
            trialVariableOutcomesInSetPanel.setVisible(false);
            trialVariableOutcomesInSetPanel.setVisible(true);
            trialVariableOutcomesInSetPanel.validate();

            trialStopConditionPanel.validate();
            trialStopConditionPanel.setVisible(false);
            trialStopConditionPanel.setVisible(true);

            trialPanel.validate();
            System.out.println("finished post processing");
            System.out.println("List Size:" + trialInSetOutcomeFields.size());
            System.out.println("***********************update finished***************************");

        }

    } catch (Exception e) {
        System.out.println("Entered a non integer");
    }


}//GEN-LAST:event_trialUpdateSetButtonMouseClicked

private void trialUpdateOrderButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialUpdateOrderButtonMouseClicked
    int numOutcomes = (Integer) trialNumOutcomesTextField.getValue();
    GUIHelper.dataFieldLoader(trialVariableOutcomesInOrderPanel, trialInOrderOutcomeFields, numOutcomes, 1);

    trialPanel.setVisible(false);
    trialPanel.setVisible(true);
    trialPanel.validate();
    trialPanel.setVisible(false);
    trialPanel.setVisible(true);

}//GEN-LAST:event_trialUpdateOrderButtonMouseClicked

private void uniformDistButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uniformDistButtonMouseClicked
    dataDistLowerBoundLabel.setText("Lower Bound:");
    dataDistUpperBoundLabel.setText("Upper Bound:");
}//GEN-LAST:event_uniformDistButtonMouseClicked

private void normalDistButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalDistButtonMouseClicked
    dataDistLowerBoundLabel.setText("Mean:");
    dataDistUpperBoundLabel.setText("SD:");

}//GEN-LAST:event_normalDistButtonMouseClicked

private void binomialButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_binomialButtonMouseClicked
    dataDistLowerBoundLabel.setText("Sample Size:");
    dataDistUpperBoundLabel.setText("Prob. of success:");

}//GEN-LAST:event_binomialButtonMouseClicked

private void displayGraphButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayGraphButtonMouseClicked
    ChartLoader cL = new ChartLoader(currentChartType);


    ChartPanel cP;
    if (onePopButton1.isSelected()) {
        cP = cL.loadChart(t.trials);
    } else {
        cP = null;
        cP = cL.loadChart(tp.trials, true);
    }
    cL.setBinCount(binCount);
    if (binVals != null) {
        cL.setBinCutoffs(binVals);
        cL.defaultBins = true;
    }

    Object[] vals = {"close", "Save"};
    final JOptionPane chartPane = new JOptionPane(cP,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            vals);

    final JFrame chartContainer = new JFrame();
    final JDialog chartDialog1 = new JDialog(chartContainer, setGraphTypeButton2.getText(), false);
    chartDialog1.setContentPane(chartPane);
    chartDialog1.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
    SaveChartPropertyListener scpl = new SaveChartPropertyListener(cL, cP, chartPane, chartDialog1);

    chartPane.addPropertyChangeListener(
            scpl);

    System.out.println("");
    chartDialog1.pack();
    chartDialog1.setVisible(true);



}//GEN-LAST:event_displayGraphButtonMouseClicked

    class SaveChartPropertyListener implements PropertyChangeListener {

        ChartLoader cL;
        JDialog d;
        ChartPanel cP;
        JOptionPane jop;

        public SaveChartPropertyListener(ChartLoader cL, ChartPanel cP, JDialog d) {
            super();
            this.cL = cL;
            this.d = d;
            this.cP = cP;
        }

        public SaveChartPropertyListener(ChartLoader cL, ChartPanel cP, JOptionPane jp) {
            super();
            this.cL = cL;
            this.cP = cP;
            this.jop = jp;
        }

        public SaveChartPropertyListener(ChartLoader cL, ChartPanel cP, JOptionPane jp, JDialog jD) {
            this(cL, cP, jp);
            this.cL = cL;
            this.cP = cP;
            this.d = jD;
            this.jop = jp;
        }

        /**
         * Called whenever the user presses a Button.
         */
        public void propertyChange(PropertyChangeEvent e) {
            String value = (String) jop.getValue();

            if (value.equals("Save")) {
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
                File f = fc.getSelectedFile();
                f.setWritable(true);
                f.setReadable(true);
                File rename = new File(f, f.getName() + ".jpeg");
                rename.setWritable(true);
                rename.setReadable(true);
                boolean isChanged = f.renameTo(rename);
                if (!isChanged) {
                    System.out.println("Why why why why");
                    System.out.println("Permissions: " + f.canWrite());
                    System.out.println("Permissions: " + f.canWrite());

                    System.out.println("Path1: " + f.getAbsolutePath());
                    System.out.println("Path2: " + rename.getAbsolutePath());
                }
                cL.saveChart(cP, f);
                d.setVisible(true);
                d.removePropertyChangeListener(this);
                d.addPropertyChangeListener(this);
            } else {
                d.setVisible(false);
            }
        }
    }

    /**
     * Action listener class for saving text to a file
     */
    class SaveTextPropertyListener implements PropertyChangeListener {

        String stats;
        JDialog d;
        JOptionPane p;

        public SaveTextPropertyListener(String stats, JDialog d, JOptionPane p) {
            this.stats = stats;
            this.d = d;
            this.p = p;
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String value = (String) p.getValue();

            if (value.equals("Save")) {
                JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
                File f = fc.getSelectedFile();
                ///File rename = new File(f);

                f.renameTo(new File(f, f.getName() + ".txt"));
                BufferedWriter out;
                try {
                    out = new BufferedWriter(new FileWriter(f));
                    if (onePopButton1.isSelected()) {
                        out.write(stats);
                    } else {
                        out.write(stats);
                    }
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                d.setVisible(true);
            } else {
                d.setVisible(false);
            }
        }
    }
    private static WindowListener closeWindow = new WindowAdapter() {

        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
        }
    };

private void runTrialsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runTrialsButtonActionPerformed
    System.out.println("\n**** Running Trials ****");
    runTrialsInfoPanel.setVisible(true);

    try {
        if (onePopButton1.isSelected()) {
            setAllValues();

            t.setupTrials();
            System.out.println("done with trials. random numbers ... ");
            randIntTextArea.setText(t.displayRandNumbers().toString());
            System.out.println("done with numbers. outcomes ... ");

            outcomeTextArea.setText(t.displayOutcomes().toString());

            responseVariableTextArea.setText(t.displayResponseVariables().toString());
        } else {


            setAllTwoPopValues();
            tp.setNumTrials((Integer) numTrialField.getValue());

            tp.runTrials();
            randIntTextArea.setText(tp.displayRandNumbers().toString());
            outcomeTextArea.setText(tp.displayOutcomes().toString());
            responseVariableTextArea.setText(tp.displayResponseVariables().toString());
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Number formatting error. ");
    }
}//GEN-LAST:event_runTrialsButtonActionPerformed

private void binButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_binButtonMouseClicked

    JPopupMenu j = binButton.getComponentPopupMenu();
    j.setVisible(true);
    j.show(runTrialsInfoPanel, setBinPanel.getX(), setBinPanel.getY());

}//GEN-LAST:event_binButtonMouseClicked

private void selectExternalFileButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectExternalFileButton2MouseClicked
    JFileChooser fc = new JFileChooser();
    int val = fc.showOpenDialog(null);
    File selectedFile = fc.getSelectedFile();
    Population p = new Population(selectedFile);
    tp.setPopulation2(p);

}//GEN-LAST:event_selectExternalFileButton2MouseClicked

private void displayHistogramButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayHistogramButton2MouseClicked
    ChartLoader cL = new ChartLoader(ChartType.BARCHART);
    ChartPanel cP = cL.loadChart(tp.p2);
    JOptionPane.showMessageDialog(null, cP, "Bar Chart", 0);
}//GEN-LAST:event_displayHistogramButton2MouseClicked

private void numObservationTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numObservationTextField1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_numObservationTextField1MouseClicked

private void numObservationTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numObservationTextField2MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_numObservationTextField2MouseClicked

private void fixedOutcomeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixedOutcomeButton1ActionPerformed
// TODO add your handling code here:
    trialStopConditionPanel1.setVisible(false);
    trialFixedOutcomePanel1.setVisible(true);
}//GEN-LAST:event_fixedOutcomeButton1ActionPerformed

private void fixedOutcomeButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixedOutcomeButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_fixedOutcomeButton2ActionPerformed

private void trialSomeOutcomesInOrderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_trialSomeOutcomesInOrderButton1ActionPerformed

private void twoPopTrialDistinctUpdateButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialDistinctUpdateButton2MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_twoPopTrialDistinctUpdateButton2MouseClicked

private void twoPopTrialDistinctUpdateButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialDistinctUpdateButton1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_twoPopTrialDistinctUpdateButton1MouseClicked

private void twoPopTrialSequenceUpdateButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialSequenceUpdateButton1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_twoPopTrialSequenceUpdateButton1MouseClicked

private void twoPopTrialSequenceUpdateButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialSequenceUpdateButton2MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_twoPopTrialSequenceUpdateButton2MouseClicked

private void someOutcomesInSetButton2variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_someOutcomesInSetButton2variableConditionButtonMouseClicked
    variableOutcomesInSetPanel2.setVisible(false);
    variableOutcomesInOrderPanel2.setVisible(true);
}//GEN-LAST:event_someOutcomesInSetButton2variableConditionButtonMouseClicked

private void allOutcomesInSetButton2variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allOutcomesInSetButton2variableConditionButtonMouseClicked
    variableOutcomesInSetPanel2.setVisible(false);
    variableOutcomesInOrderPanel2.setVisible(true);
}//GEN-LAST:event_allOutcomesInSetButton2variableConditionButtonMouseClicked

private void someOutcomesInOrderButton2variableConditionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_someOutcomesInOrderButton2variableConditionButtonMouseClicked
    variableOutcomesInSetPanel2.setVisible(true);
    variableOutcomesInOrderPanel2.setVisible(false);
}//GEN-LAST:event_someOutcomesInOrderButton2variableConditionButtonMouseClicked

private void varPrcSuccessInEachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varPrcSuccessInEachButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_varPrcSuccessInEachButtonActionPerformed

private void varMeanInEachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varMeanInEachButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_varMeanInEachButtonActionPerformed

private void someOutcomesInSetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_someOutcomesInSetButton1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_someOutcomesInSetButton1ActionPerformed

private void numDistinctOutcomeTextField6none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField6none
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField6none

private void numDistinctOutcomeTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField6ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField6ActionPerformed

private void numDistinctOutcomeTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numDistinctOutcomeTextField6KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_numDistinctOutcomeTextField6KeyTyped

private void variableNumOutcomesTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_variableNumOutcomesTextField2KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_variableNumOutcomesTextField2KeyTyped

private void identicalPopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identicalPopButtonActionPerformed

    System.out.println("\n**** Identical Populations ****");

    tabsFrame.remove(dataPanel);
    tabsFrame.remove(trialPanel);
    tabsFrame.remove(variablePanel);
  
    tabsFrame.insertTab("Define a model", null, dataTwoPopPanel, null, 1);
    tabsFrame.insertTab("Define a Trial", null, twoPopTrialPanel, null, 2);

    tabsFrame.insertTab("Define the Variable ", null, twoPopVarPanel, null, 3);

    GUIHelper.clearDataFields(dataOutcomeListPanel1, dataOutcomeFields1, 0);
    restoreDefaults();


    dataPop2Panel.setVisible(false);

    trialPop2Panel.setVisible(true);
    trialPop1Panel.setVisible(true);

    categoreyDiffButton.setVisible(false);
    meanDiffGroupButton.setVisible(false);
    dataPop2Panel.setVisible(false);
    varObsDiffMeanButton.setVisible(true);
    varSampleMeanDiffButton.setVisible(true);
    varNumMatchButton.setVisible(true);
    categoreyPopDiffButton.setVisible(true);
    conditionButton3.setVisible(true);
    conditionButton4.setVisible(true);
    trialStopConditionPanel3.setVisible(false);
    trialStopConditionPanel4.setVisible(false);
    trialFixedOutcomePanel4.setVisible(false);
    trialFixedOutcomePanel3.setVisible(false);
    fixedOutcomeButton3.setVisible(true);
    fixedOutcomeButton4.setVisible(true);
    trialTypeLabel3.setVisible(true);
    numOutcomeField1.setValue(1);

    jLabel17.setText("Sample 1");
    jLabel18.setText("Sample 2");
    
    numObservationLabel4.setText("Number of Observations");

    trialAndButton.setVisible(true);
    trialOrButton.setVisible(true);
    jLabel7.setText("Population 1");

    dataTwoPopNextButton.setText("Submit Models");
    diffPanel.setVisible(false);

    diffOption1.setText("Population 1 - Population 2");
    diffOption2.setText("Population 2 - Population 1");

    tp = new TwoPopTrialRunner();

}//GEN-LAST:event_identicalPopButtonActionPerformed

private void sumButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sumButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);

}//GEN-LAST:event_sumButtonMouseClicked

private void trialLengthButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialLengthButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_trialLengthButtonMouseClicked

private void meanButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meanButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_meanButtonMouseClicked

private void meanWithSTDButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meanWithSTDButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_meanWithSTDButtonMouseClicked

private void medianButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medianButtonMouseClicked
// TODO add your handling code here:
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_medianButtonMouseClicked

private void modeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modeButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_modeButtonMouseClicked

private void modeSizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modeSizeButtonMouseClicked
    variableSuccessConditionPanel.setVisible(false);
    variableOutcomesInSetPanel.setVisible(false);
    variableOutcomesInOrderPanel.setVisible(false);
}//GEN-LAST:event_modeSizeButtonMouseClicked

private void varSetUpdateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varSetUpdateButtonMouseClicked
    System.out.println("\n***********************var set update called***************************");
    int numOutcomes = 0;
    int xSpacing = 2;
    int ySpacing = 2;
    int i = 0;
    int start = 0;
    try {
        numOutcomes = (Integer) numDistinctOutcomesTextField1.getValue();
        System.out.println("Number of Outcomes:" + numOutcomes);
        System.out.println("List Size:" + varInSetOutcomeFields.size());

        boolean add = true;
  
        if (varInSetOutcomeFields.size() > numOutcomes) {
            int numRemove = varInSetOutcomeFields.size() - numOutcomes;
            for (i = 0; i < numRemove; i++) {
                FieldStruct f = varInSetOutcomeFields.remove(varInSetOutcomeFields.size() - 1);

                variableOutcomesInSetPanel.remove(f.one);
                variableOutcomesInSetPanel.remove(f.two);

                f.one.setVisible(false);
                f.two.setVisible(false);

            }
            System.out.println("Case 1: Trimmed box list " + varInSetOutcomeFields.size());
            variableOutcomesInSetPanel.validate();
            variablePanel.validate();
            add = false;

        } else if (varInSetOutcomeFields.size() < numOutcomes) {
            start = varInSetOutcomeFields.size();
            add = true;

        } else if (varInSetOutcomeFields.size() == numOutcomes) {
            add = false;
        }
        if (add) {
            System.out.println("Starting Position: " + start);

            FieldStruct one = varInSetOutcomeFields.get(varInSetOutcomeFields.size() - 1);
            System.out.println("the first: " + one.one.getX());
            int nameX = one.one.getX(); 
            int lowerX = one.two.getX();

            int nameY = one.one.getY() + one.one.getHeight() + ySpacing;
            int lowerY = one.two.getY() + one.two.getHeight() + ySpacing;

            System.out.println("processed init stuff");
            for (i = start; i < numOutcomes; i++) {

                JTextField anyn1 = new JTextField();
                anyn1.setSize(one.one.getWidth(), one.one.getHeight());
                anyn1.setLocation(nameX, nameY);
                variableOutcomesInSetPanel.add(anyn1);

                JTextField anyn2 = new JTextField();
                anyn2.setSize(one.two.getWidth(), one.two.getHeight());
                anyn2.setLocation(lowerX, lowerY);
                variableOutcomesInSetPanel.add(anyn2);


                nameY = nameY + one.one.getHeight() + ySpacing;
                lowerY = lowerY + one.two.getHeight() + ySpacing;
                FieldStruct struct = new FieldStruct();
                varInSetOutcomeFields.add(struct);
                struct.one = anyn1;
                struct.two = anyn2;
                System.out.println("processed button ");

            }

            FieldStruct last = varInSetOutcomeFields.get(varInSetOutcomeFields.size() - 1);
            int height = (last.one.getHeight() + ySpacing) * varInSetOutcomeFields.size() + 20;
            variableOutcomesInSetPanel.setBounds(variableOutcomesInSetPanel.getX(), variableOutcomesInSetPanel.getY(), variableOutcomesInSetPanel.getWidth(), height);

            variableOutcomesInSetPanel.validate();
            variablePanel.validate();
            System.out.println("finished post processing");
            System.out.println("List Size:" + varInSetOutcomeFields.size());
            System.out.println("***********************update finished***************************");

        }

    } catch (Exception e) {
        System.out.println("Entered a non integer");
    }
}//GEN-LAST:event_varSetUpdateButtonMouseClicked

private void varOrderUpdateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varOrderUpdateButtonMouseClicked
    System.out.println("*********************** var order update called***************************");

    int numOutcomes = (Integer) variableNumOutcomesTextField.getValue();
    GUIHelper.dataFieldLoader(variableOutcomesInOrderPanel, varInOrderOutcomeFields, numOutcomes, 1);
    variablePanel.setVisible(false);

    variablePanel.setVisible(true);
    variablePanel.validate();
    variablePanel.setVisible(false);

    variablePanel.setVisible(true);

 
}//GEN-LAST:event_varOrderUpdateButtonMouseClicked

private void runTrialsInfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runTrialsInfoPanelMouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_runTrialsInfoPanelMouseClicked

private void varObsDiffMeanButtonsuccessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varObsDiffMeanButtonsuccessButtonMouseClicked
    categoryPanel.setVisible(false);
    diffPanel.setVisible(true);

}//GEN-LAST:event_varObsDiffMeanButtonsuccessButtonMouseClicked

private void twoGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoGroupButtonActionPerformed
    System.out.println("\n**** Two Groups ****");


    tabsFrame.remove(dataPanel);
    tabsFrame.remove(trialPanel);
    tabsFrame.remove(variablePanel);
   
    tabsFrame.insertTab("Define a Model", null, dataTwoPopPanel, null, 1);
    tabsFrame.insertTab("Define a Trial", null, twoPopTrialPanel, null, 2);

    tabsFrame.insertTab("Define the Variable ", null, twoPopVarPanel, null, 3);


    GUIHelper.clearDataFields(dataOutcomeListPanel1, dataOutcomeFields1, 0);
    restoreDefaults();

    meanDiffGroupButton.setVisible(true);
    categoreyDiffButton.setVisible(true);
    dataPop2Panel.setVisible(false);
    trialPop2Panel.setVisible(true);

    varObsDiffMeanButton.setVisible(false);
    varSampleMeanDiffButton.setVisible(false);
    varNumMatchButton.setVisible(false);
    categoreyPopDiffButton.setVisible(false);

    conditionButton3.setVisible(false);
    conditionButton4.setVisible(false);

    trialStopConditionPanel3.setVisible(false);
    trialStopConditionPanel4.setVisible(false);

    trialFixedOutcomePanel3.setVisible(true);
    trialFixedOutcomePanel4.setVisible(true);

    trialTypeLabel3.setVisible(false);
    fixedOutcomeButton3.setVisible(false);
    trialTypeLabel4.setVisible(false);
    fixedOutcomeButton4.setVisible(false);
    conditionButton3.setVisible(false);
    conditionButton4.setVisible(false);

    jLabel17.setText("Group 1 Settings");
    jLabel18.setText("Group 2 Settings");

    dataTwoPopNextButton.setText("Submit Model");
    numObservationLabel4.setText("Number of Observations");
    trialAndButton.setVisible(false);
    trialOrButton.setVisible(false);
    jLabel7.setText("");

    diffOption1.setText("Group 1 - Group 2");
    diffOption2.setText("Group 2 - Group 1");
    diffPanel.setVisible(false);
    tp = new TwoPopTrialRunner();

    numOutcomeField1.setValue(1);
}//GEN-LAST:event_twoGroupButtonActionPerformed

private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    binCount = Integer.parseInt(jTextField2.getText());
    binVals = new ArrayList<Double>();
    String[] bins = jTextField3.getText().split(",");
    for (int i = 0; i < bins.length; i++) {
        binVals.add(Double.parseDouble(bins[i]));
    }


}//GEN-LAST:event_jButton1MouseClicked

private void dataTwoPopNextButton1MouseClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataTwoPopNextButton1MouseClicked


    if (listOutcomeButton1.isSelected()) {
        System.out.println(dataOutcomeFields1.size());

        for (FieldStruct f : dataOutcomeFields1) {
            try {
                System.out.println("Outcome: " + f.one.getText());
                System.out.println("Lower Bound: " + f.two.getText());

                System.out.println("Upper Bound: " + f.three.getText());
            } catch (NullPointerException e) {
                System.out.println("data fields are empty");
            }

        }
        tp.distType1 = null;
        dataOutcomeList1 = new ArrayList<Outcome>();
        for (FieldStruct f : dataOutcomeFields1) {
            Outcome o = new Outcome();
            System.out.println(f.one);
            System.out.println(f.one.getText());
            o.outcomeName = f.one.getText();
            o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
            dataOutcomeList1.add(o);
        }
        Population p = new Population(dataOutcomeList1);
        tp.setPopulation1(p);
    } else if (externalListButton1.isSelected()) {
        System.out.println("loaded file");
        tp.distType1 = null;
    } else {
        dataOutcomeList1 = new ArrayList<Outcome>();

        String distType = "";
        tp.setDistVal1(Double.parseDouble(dataDistLowerBoundTextField1.getText()));
        tp.setDistVal2(Double.parseDouble(dataDistUpperBoundTextField1.getText()));
        if (uniformDistButton1.isSelected()) {
            distType = "uniform";
        } else if (binomialButton1.isSelected()) {
            distType = "binomial";
        } else if (normalDistButton1.isSelected()) {
            distType = "norm";
        } else if (expButton1.isSelected()) {
            distType = "exp";
        }
        tp.setDistType(distType);


    }

    if (twoPopButton.isSelected()) {
        trialFixedOutcomePanel3.setVisible(false);

        if (listOutcomeButton2.isSelected()) {
            System.out.println(dataOutcomeFields2.size());


            for (FieldStruct f : dataOutcomeFields2) {
                try {
                    System.out.println("Outcome: " + f.one.getText());
                    System.out.println("Lower Bound: " + f.two.getText());

                    System.out.println("Upper Bound: " + f.three.getText());
                } catch (NullPointerException e) {
                    System.out.println("data fields are empty");
                }

            }
            tp.distType = null;
            dataOutcomeList2 = new ArrayList<Outcome>();
            for (FieldStruct f : dataOutcomeFields2) {
                Outcome o = new Outcome();
                System.out.println(f.one.getText());
                o.outcomeName = f.one.getText();
                o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
                dataOutcomeList2.add(o);
            }
            Population p = new Population(dataOutcomeList2);
            tp.setPopulation2(p);
        } else if (externalListButton2.isSelected()) {

            tp.distType = null;
        } else {
            dataOutcomeList2 = new ArrayList<Outcome>();

            String distType = "";
            tp.setDistVal1(Double.parseDouble(lowerBoundField3.getText()));
            tp.setDistVal2(Double.parseDouble(upperBoundField3.getText()));
            if (uniformDistButton1.isSelected()) {
                distType = "uniform";
            } else if (binomialButton1.isSelected()) {
                distType = "binomial";
            } else if (normalDistButton1.isSelected()) {
                distType = "norm";
            } else if (expButton1.isSelected()) {
                distType = "exp";
            }
            tp.setDistType(distType);
            trialFixedOutcomePanel3.setVisible(false);

        }
    } else if (identicalPopButton.isSelected()) {
        tp.setPopulation2(new Population(tp.getPopulation1()));
        trialFixedOutcomePanel3.setVisible(false);

    } else {
    }



    tabsFrame.setSelectedIndex(2);
}//GEN-LAST:event_dataTwoPopNextButton1MouseClicked

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField1ActionPerformed

private void categoreyDiffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoreyDiffButtonMouseClicked
    categoryPanel.setVisible(true);
    diffPanel.setVisible(true);


}//GEN-LAST:event_categoreyDiffButtonMouseClicked

private void categoreyPopDiffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoreyPopDiffButtonMouseClicked
    categoryPanel.setVisible(true);
    diffPanel.setVisible(true);


}//GEN-LAST:event_categoreyPopDiffButtonMouseClicked

private void onePopButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onePopButton1ActionPerformed
    System.out.println("\n**** One Population ****");

    tabsFrame.remove(dataTwoPopPanel);
    tabsFrame.remove(twoPopTrialPanel);
    tabsFrame.remove(twoPopVarPanel);
    //  }
    //  tabsFrame.insertT
    tabsFrame.insertTab("Define a model", null, dataPanel, null, 1);
    tabsFrame.insertTab("Define a Trial", null, trialPanel, null, 2);

    tabsFrame.insertTab("Define the Variable ", null, variablePanel, null, 3);


    withoutReplacementButton3.setVisible(true);
    conditionButton3.setVisible(true);
    trialFixedOutcomePanel3.setVisible(false);
    GUIHelper.clearDataFields(dataOutcomeListPanel, dataOutcomeFields, 0);
    GUIHelper.clearDataFields(trialVariableOutcomesInSetPanel, trialInSetOutcomeFields, 2);

    GUIHelper.clearDataFields(trialVariableOutcomesInOrderPanel, trialInOrderOutcomeFields, 1);

    GUIHelper.clearDataFields(variableOutcomesInOrderPanel, varInOrderOutcomeFields, 1);
    GUIHelper.clearDataFields(variableOutcomesInSetPanel, varInSetOutcomeFields, 2);

    restoreDefaults();
}//GEN-LAST:event_onePopButton1ActionPerformed

private void nextButton0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton0MouseClicked

    tabsFrame.setSelectedIndex(1);
}//GEN-LAST:event_nextButton0MouseClicked

private void withoutReplacementButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutReplacementButton3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_withoutReplacementButton3ActionPerformed

private void fixedOutcomeButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fixedOutcomeButton3MouseClicked
    trialStopConditionPanel3.setVisible(false);
    trialFixedOutcomePanel3.setVisible(true);
    System.out.println("Shown fixed outcome panel \n");
}//GEN-LAST:event_fixedOutcomeButton3MouseClicked

private void conditionButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionButton3MouseClicked
    trialStopConditionPanel3.setVisible(true);
    trialFixedOutcomePanel3.setVisible(false);
    trialVariableOutcomesInSetPanel3.setVisible(false);
    trialVariableOutcomesInOrderPanel3.setVisible(false);

}//GEN-LAST:event_conditionButton3MouseClicked

private void trialAllOutcomesInSetButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialAllOutcomesInSetButton3MouseClicked
    trialVariableOutcomesInOrderPanel3.setVisible(false);
    trialVariableOutcomesInSetPanel3.setVisible(true);
}//GEN-LAST:event_trialAllOutcomesInSetButton3MouseClicked

private void trialSomeOutcomesInSetButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton3MouseClicked
    trialVariableOutcomesInOrderPanel3.setVisible(false);
    trialVariableOutcomesInSetPanel3.setVisible(true);
}//GEN-LAST:event_trialSomeOutcomesInSetButton3MouseClicked

private void trialSomeOutcomesInOrderButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButton3MouseClicked
    trialVariableOutcomesInOrderPanel3.setVisible(true);
    trialOrderValueLabel1.setVisible(true);
    trialVariableOutcomesInSetPanel3.setVisible(false);
}//GEN-LAST:event_trialSomeOutcomesInOrderButton3MouseClicked

private void twoPopTrialDistinctUpdateButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialDistinctUpdateButton3MouseClicked
    int numOutcomes = (Integer) numDistinctOutcomeTextField5.getValue();
    GUIHelper.dataFieldLoader(trialVariableOutcomesInSetPanel3, trialInSetOutcomeFields1, numOutcomes, 2);
    twoPopTrialPanel.validate();
}//GEN-LAST:event_twoPopTrialDistinctUpdateButton3MouseClicked

private void twoPopTrialSequenceUpdateButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialSequenceUpdateButton3MouseClicked
    int numOutcomes = (Integer) trialSequenceField1.getValue();
    GUIHelper.dataFieldLoader(trialVariableOutcomesInOrderPanel3, trialInOrderOutcomeFields1, numOutcomes, 1);
    twoPopTrialPanel.validate();
}//GEN-LAST:event_twoPopTrialSequenceUpdateButton3MouseClicked

private void withoutReplacementButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withoutReplacementButton4ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_withoutReplacementButton4ActionPerformed

private void fixedOutcomeButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fixedOutcomeButton4MouseClicked
    trialStopConditionPanel4.setVisible(false);
    trialFixedOutcomePanel4.setVisible(true);

}//GEN-LAST:event_fixedOutcomeButton4MouseClicked

private void conditionButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conditionButton4MouseClicked
    trialStopConditionPanel4.setVisible(true);
    trialFixedOutcomePanel4.setVisible(false);
    trialVariableOutcomesInSetPanel4.setVisible(false);
    trialVariableOutcomesInOrderPanel4.setVisible(false);
}//GEN-LAST:event_conditionButton4MouseClicked

private void trialAllOutcomesInSetButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialAllOutcomesInSetButton4MouseClicked
    trialVariableOutcomesInOrderPanel4.setVisible(false);
    trialVariableOutcomesInSetPanel4.setVisible(true);
}//GEN-LAST:event_trialAllOutcomesInSetButton4MouseClicked

private void trialSomeOutcomesInSetButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInSetButton4MouseClicked
    trialVariableOutcomesInOrderPanel4.setVisible(false);
    trialVariableOutcomesInSetPanel4.setVisible(true);
}//GEN-LAST:event_trialSomeOutcomesInSetButton4MouseClicked

private void trialSomeOutcomesInOrderButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trialSomeOutcomesInOrderButton4MouseClicked
    trialVariableOutcomesInOrderPanel4.setVisible(true);
    trialVariableOutcomesInSetPanel4.setVisible(false);
}//GEN-LAST:event_trialSomeOutcomesInOrderButton4MouseClicked

private void twoPopTrialDistinctUpdateButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialDistinctUpdateButton4MouseClicked

    int numOutcomes = (Integer) trialSetField2.getValue();
    GUIHelper.dataFieldLoader(trialVariableOutcomesInSetPanel4, trialInSetOutcomeFields2, numOutcomes, 2);
    twoPopTrialPanel.validate();
}//GEN-LAST:event_twoPopTrialDistinctUpdateButton4MouseClicked

private void twoPopTrialSequenceUpdateButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twoPopTrialSequenceUpdateButton4MouseClicked
    int numOutcomes = (Integer) trialInOrderField2.getValue();
    GUIHelper.dataFieldLoader(trialVariableOutcomesInOrderPanel4, trialInOrderOutcomeFields2, numOutcomes, 1);
    twoPopTrialPanel.validate();
}//GEN-LAST:event_twoPopTrialSequenceUpdateButton4MouseClicked

private void dataTwoPopUpdateButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTwoPopUpdateButton2MouseClicked

    int numOutcomes = (Integer) numOutcomeField2.getValue();
    GUIHelper.dataFieldLoader(dataOutcomeListPanel2, dataOutcomeFields2, numOutcomes, 0);
    for (FieldStruct f : dataOutcomeFields2) {
        f.one.setVisible(true);
    }

    dataTwoPopPanel.revalidate();
}//GEN-LAST:event_dataTwoPopUpdateButton2MouseClicked

private void dataTwoPopPrbDisplay2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTwoPopPrbDisplay2MouseClicked
    System.out.println("\n*** Population 2  Display Probability ***");

    dataOutcomeList2 = new ArrayList<Outcome>();

    GUIHelper.dataListLoader(dataOutcomeFields2, dataOutcomeList2);
    GUIHelper.displayPrb(dataOutcomeFields2, dataOutcomeList2, dataOutcomeListPanel2);

}//GEN-LAST:event_dataTwoPopPrbDisplay2MouseClicked

private void dataDisplayPrbButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataDisplayPrbButtonMouseClicked
    System.out.println("\n*** Single Population Display Probability ***");
    if (!checkDataErrors()) {

        dataOutcomeList = new ArrayList<Outcome>();
        System.out.println("List: " + dataOutcomeFields.size());


        GUIHelper.dataListLoader(dataOutcomeFields, dataOutcomeList);
        GUIHelper.displayPrb(dataOutcomeFields, dataOutcomeList, dataOutcomeListPanel);
    }
}//GEN-LAST:event_dataDisplayPrbButtonMouseClicked

private void nextButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButton3ActionPerformed
    if (!checkVariableErrors()) {
        String varType = "";
        if (containsSuccessButton.isSelected()) {
            String successCond = "";
            varSuccessList = new ArrayList<Outcome>();
            if (someOutcomesInOrderButton.isSelected()) {
                successCond = "someOrder";
                for (FieldStruct f : varInOrderOutcomeFields) {
                    Outcome o = new Outcome();
                    o.outcomeName = f.one.getText();
                    varSuccessList.add(o);
                }
                t.setSuccessOutcomes(varSuccessList);

            } else if (someOutcomesInSetButton.isSelected()) {
                varSuccessCondSet = new HashMap<String, Integer>();

                for (FieldStruct f : varInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    //Outcome o = new Outcome();
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    varSuccessCondSet.put(name, num);
                }
                t.setSuccessSet(varSuccessCondSet);
                successCond = "someSet";
            } else {
                varSuccessCondSet = new HashMap<String, Integer>();

                for (FieldStruct f : varInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    varSuccessCondSet.put(name, num);
                }
                t.setSuccessSet(varSuccessCondSet);
                successCond = "allSet";
            }
            t.setSuccessCondType(successCond);

            varType = "containsSuccess";
        } else if (numSuccessButton.isSelected()) {
            varSuccessList = new ArrayList<Outcome>();

            for (FieldStruct f : varInOrderOutcomeFields) {
                Outcome o = new Outcome();

                o.outcomeName = f.one.getText();
                varSuccessList.add(o);
            }
            t.setSuccessOutcomes(varSuccessList);

            varType = "numSuccess";
        } else if (percentSuccessButton.isSelected()) {
            varSuccessList = new ArrayList<Outcome>();

            for (FieldStruct f : varInOrderOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                Outcome o = new Outcome();

                o.outcomeName = f.one.getText();
                varSuccessList.add(o);
            }
            t.setSuccessOutcomes(varSuccessList);

            varType = "percentSuccess";
        } else if (trialLengthButton.isSelected()) {
            varType = "length";
        } else if (meanButton.isSelected()) {
            varType = "mean";
        } else if (medianButton.isSelected()) {
            varType = "median";
        } else if (meanWithSTDButton.isSelected()) {
            varType = "mean+std";
        } else if (modeButton.isSelected()) {
            varType = "mode";
        } else if (modeSizeButton.isSelected()) {
            varType = "modeSize";
        } else if (sumButton.isSelected()) {
            varType = "sum";
        }
        System.out.println("sum Selected? " + sumButton.isSelected());
        t.setVariableType(varType);

        tabsFrame.setSelectedIndex(4);
    } else {
    }
}//GEN-LAST:event_nextButton3ActionPerformed

private void uniformDistButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uniformDistButton1MouseClicked
    dataDistLowerBoundLabel1.setText("Lower Bound:");
    dataDistUpperBoundLabel1.setText("Upper Bound:");
}//GEN-LAST:event_uniformDistButton1MouseClicked

private void normalDistButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalDistButton1MouseClicked
    dataDistLowerBoundLabel1.setText("Mean:");
    dataDistUpperBoundLabel1.setText("SD:");
}//GEN-LAST:event_normalDistButton1MouseClicked

private void binomialButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_binomialButton1MouseClicked
    dataDistLowerBoundLabel1.setText("Sample Size:");
    dataDistUpperBoundLabel1.setText("Probability of Success:");
}//GEN-LAST:event_binomialButton1MouseClicked

private void uniformDistButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uniformDistButton2MouseClicked
    dataDistLowerBoundLabel2.setText("Lower Bound:");
    dataDistUpperBoundLabel2.setText("Upper Bound:");
}//GEN-LAST:event_uniformDistButton2MouseClicked

private void normalDistButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalDistButton2MouseClicked
    dataDistLowerBoundLabel2.setText("Mean:");
    dataDistUpperBoundLabel2.setText("SD:");
}//GEN-LAST:event_normalDistButton2MouseClicked

private void binomialButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_binomialButton2MouseClicked
    dataDistLowerBoundLabel1.setText("Sample Size:");
    dataDistUpperBoundLabel1.setText("Probability of Success:");
}//GEN-LAST:event_binomialButton2MouseClicked

private void withReplacementButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_withReplacementButton3MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_withReplacementButton3MouseClicked

private void varSampleMeanDiffButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varSampleMeanDiffButtonMouseClicked
    categoryPanel.setVisible(false);
    diffPanel.setVisible(true);


}//GEN-LAST:event_varSampleMeanDiffButtonMouseClicked

private void meanDiffGroupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meanDiffGroupButtonMouseClicked
    categoryPanel.setVisible(false);
    diffPanel.setVisible(true);

}//GEN-LAST:event_meanDiffGroupButtonMouseClicked

private void varNumMatchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_varNumMatchButtonMouseClicked
    categoryPanel.setVisible(false);
    diffPanel.setVisible(false);

}//GEN-LAST:event_varNumMatchButtonMouseClicked

    private void setTwoGroupFields() {
    }

private void trialTwoPopNextButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialTwoPopNextButton2ActionPerformed
    System.out.println("\n\n****loading new tab****");

    if (twoGroupButton.isSelected()) {
        tp.s1.isFixedOutcomes = true;
        tp.s1.setNumObservations((Integer) (trialNumObservationTextField1.getValue()));
        tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));

        if (withReplacementButton3.isSelected()) {
            tp.s1.setIsWithReplacement(true);
        } else {
            tp.s1.setIsWithReplacement(false);

        }

        if (withReplacementButton4.isSelected()) {
            tp.s2.setIsWithReplacement(true);
        } else {
            tp.s2.setIsWithReplacement(false);

        }
    } else if (!checkTwoPopTrialErrors()) {
        System.out.println("loading settings");
        tp.ANDOR = "and";
        if (withReplacementButton3.isSelected()) {
            tp.s1.setIsWithReplacement(true);
        } else {
            tp.s1.setIsWithReplacement(false);
        }
        if (fixedOutcomeButton3.isSelected()) {
            System.out.println("Fixed observations button selected");
            tp.s1.isFixedOutcomes = true; //isFixedOutcomes(true);
            tp.s1.setNumObservations((Integer) (trialNumObservationTextField1.getValue()));
        } else {
            tp.s1.isFixedOutcomes = false;

            String stopCond = "";
            if (trialSomeOutcomesInOrderButton3.isSelected()) {
                stopCond = "someOrder";
                trialStopCondList = new ArrayList<Outcome>();
                for (FieldStruct f : trialInOrderOutcomeFields1) {
                    Outcome o = new Outcome();
                    o.outcomeName = f.one.getText();
                    trialStopCondList.add(o);
                }
                tp.s1.stopCondOutcomes = trialStopCondList;
            } else if (trialSomeOutcomesInSetButton3.isSelected()) {
                trialStopCondSet = new HashMap<String, Integer>();

                for (FieldStruct f : trialInSetOutcomeFields1) {
                    System.out.println(f.one.getText());

                    System.out.println(f.two.getText());
                }
                for (FieldStruct f : trialInSetOutcomeFields1) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    //Outcome o = new Outcome();
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                tp.s1.stopOutcomeSet = trialStopCondSet;
                stopCond = "someSet";
            } else {
                trialStopCondSet = new HashMap<String, Integer>();
                for (FieldStruct f : trialInSetOutcomeFields1) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    //Outcome o = new Outcome();
                    System.out.println(f.one.getText());
                    System.out.println(f.two.getText());
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                tp.s1.stopOutcomeSet = trialStopCondSet;
                stopCond = "allSet";
            }
            tp.s1.stopConditionType = stopCond;
        }

        if (withReplacementButton4.isSelected()) {
            tp.s2.setIsWithReplacement(true);
        } else {
            tp.s2.setIsWithReplacement(false);
        }
        if (fixedOutcomeButton4.isSelected()) {
            System.out.println("Fixed observations button selected");
            tp.s2.isFixedOutcomes = true; //isFixedOutcomes(true);
            tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));
        } else {
            tp.s2.isFixedOutcomes = false;

            String stopCond = "";
            if (trialSomeOutcomesInOrderButton4.isSelected()) {
                stopCond = "someOrder";
                trialStopCondList = new ArrayList<Outcome>();
                for (FieldStruct f : trialInOrderOutcomeFields2) {
                    Outcome o = new Outcome();
                    o.outcomeName = f.one.getText();
                    trialStopCondList.add(o);
                }
                tp.s2.stopCondOutcomes = trialStopCondList; //setStopCondOutcomes(trialStopCondList);
            } else if (trialSomeOutcomesInSetButton4.isSelected()) {
                trialStopCondSet = new HashMap<String, Integer>();

                for (FieldStruct f : trialInSetOutcomeFields2) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    //Outcome o = new Outcome();
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                tp.s2.stopOutcomeSet = trialStopCondSet;
                stopCond = "someSet";
            } else {
                trialStopCondSet = new HashMap<String, Integer>();
                for (FieldStruct f : trialInSetOutcomeFields2) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    //Outcome o = new Outcome();
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                tp.s2.stopOutcomeSet = trialStopCondSet;
                stopCond = "allSet";
            }
            tp.s2.stopConditionType = stopCond;
        }

    } else {
    }


    if (withReplacementButton4.isSelected()) {
        tp.s2.setIsWithReplacement(true);
    } else {
        tp.s2.setIsWithReplacement(false);
    }
    if (fixedOutcomeButton4.isSelected()) {
        System.out.println("Fixed observations button selected");
        tp.s2.setNumObservations((Integer) (trialNumObservationTextField2.getValue()));
    } else {
    }


    tabsFrame.setSelectedIndex(3);

}//GEN-LAST:event_trialTwoPopNextButton2ActionPerformed

private void graphTypeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphTypeButtonMouseClicked
    System.out.println("\n***Graph Type Button Mouse Clicked***");
    JPopupMenu j = graphTypeButton.getComponentPopupMenu();
    j.setVisible(false);
    j.setLocation(new Point(graphTypeButton.getX(), graphTypeButton.getY()));

}//GEN-LAST:event_graphTypeButtonMouseClicked

private void setGraphTypeButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setGraphTypeButton2MouseClicked
    System.out.println("\n***Graph Type Button2 Mouse Clicked***");

    JPopupMenu j = setGraphTypeButton2.getComponentPopupMenu();

    j.setVisible(true);
    j.show(runTrialsInfoPanel, jPanel4.getX(), jPanel4.getY());
    
}//GEN-LAST:event_setGraphTypeButton2MouseClicked

private void barChartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barChartItemActionPerformed
    System.out.println("Bar Chart pressed");

    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Bar Chart");
    currentChartType = ChartType.BARCHART;

    binButton.setVisible(false);
    setBinSpinner.setVisible(false);
}//GEN-LAST:event_barChartItemActionPerformed

private void dotPlotItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotPlotItemActionPerformed
    System.out.println("Dot Chart pressed");

    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Dot Plot");

    binButton.setVisible(false);
    setBinSpinner.setVisible(false);
    currentChartType = ChartType.DOTPLOT;
}//GEN-LAST:event_dotPlotItemActionPerformed

private void binnedDotPlotItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binnedDotPlotItemActionPerformed
    System.out.println("Binned dotplot pressed");
    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Binned Dot Plot");
    binButton.setVisible(false);
    setBinSpinner.setVisible(false);
    currentChartType = ChartType.DOTPLOT_BINNED;
}//GEN-LAST:event_binnedDotPlotItemActionPerformed

private void histogramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histogramItemActionPerformed
    binButton.setVisible(true);
    setBinSpinner.setVisible(false);

    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Histogram");
    currentChartType = ChartType.HISTOGRAM;

}//GEN-LAST:event_histogramItemActionPerformed

private void histogramWithFitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histogramWithFitItemActionPerformed
    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Histogram with fit");

    binButton.setVisible(true);
    setBinSpinner.setVisible(false);
    currentChartType = ChartType.HISTOGRAM_FIT;
}//GEN-LAST:event_histogramWithFitItemActionPerformed

private void boxPlotItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPlotItemActionPerformed
    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Box Plot");

    binButton.setVisible(false);
    setBinSpinner.setVisible(false);
    currentChartType = ChartType.BOXPLOT;
}//GEN-LAST:event_boxPlotItemActionPerformed

private void pieChartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pieChartItemActionPerformed
    graphTypePopUp.setVisible(false);
    setGraphTypeButton2.setText("Pie Chart");

    binButton.setVisible(false);
    setBinSpinner.setVisible(false);
    currentChartType = ChartType.PIECHART;
}//GEN-LAST:event_pieChartItemActionPerformed

private void nextButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButton1ActionPerformed
    if (!checkDataErrors()) {
        // THIS IS WHERE MOST ERROR HANDLING IS DONE
        if (listOutcomeButton.isSelected()) {
            System.out.println("List size: " + dataOutcomeFields.size());


            for (FieldStruct f : dataOutcomeFields) {
                try {
                    System.out.println("Outcome: " + f.one.getText());
                    System.out.println("Lower Bound: " + f.two.getText());

                    System.out.println("Upper Bound: " + f.three.getText() + "\n");
                } catch (NullPointerException e) {
                    System.out.println("data fields are empty");
                }

            }
            t.distType = null;
            dataOutcomeList = new ArrayList<Outcome>();
            for (FieldStruct f : dataOutcomeFields) {
                Outcome o = new Outcome();
                System.out.println(f.one.getText());
                o.outcomeName = f.one.getText();
                o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
                dataOutcomeList.add(o);
            }
            Population p = new Population(dataOutcomeList);
            t.setPopulation(p);
        } else if (externalListButton.isSelected()) {
      
            System.out.println("loaded file");
            t.distType = null;
        } else {
            dataOutcomeList = new ArrayList<Outcome>();

            String distType = "";
            t.setDistVal1(Double.parseDouble(dataDistLowerBoundTextField.getText()));
            t.setDistVal2(Double.parseDouble(dataDistUpperBoundTextField.getText()));
            if (uniformDistButton.isSelected()) {
                distType = "uniform";
            } else if (binomialButton.isSelected()) {
                distType = "binomial";
            } else if (normalDistButton.isSelected()) {
                distType = "norm";
            } else if (expButton.isSelected()) {
                distType = "exp";
            }
            t.setDistType(distType);


        }
        tabsFrame.setSelectedIndex(2);
    }
}//GEN-LAST:event_nextButton1ActionPerformed

private void nextButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButton2ActionPerformed
    if (!checkTrialErrors()) {
        if (withReplacementButton.isSelected()) {
            t.setIsWithReplacement(true);
        } else {
            t.setIsWithReplacement(false);
        }
        if (fixedOutcomeButton.isSelected()) {
            System.out.println("Fixed observations button selected");
            t.setIsFixedOutcomes(true);
            t.setNumObservations((Integer) (numObservationTextField.getValue()));
        } else {
            t.setIsFixedOutcomes(false);

            String stopCond = "";
            if (trialSomeOutcomesInOrderButton.isSelected()) {
                stopCond = "someOrder";
                trialStopCondList = new ArrayList<Outcome>();
                for (FieldStruct f : trialInOrderOutcomeFields) {
                    Outcome o = new Outcome();
                    o.outcomeName = f.one.getText();
                    trialStopCondList.add(o);
                }
                t.setStopCondOutcomes(trialStopCondList);
            } else if (trialSomeOutcomesInSetButton.isSelected()) {
                trialStopCondSet = new HashMap<String, Integer>();

                for (FieldStruct f : trialInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                t.stopOutcomeSet = trialStopCondSet;
                stopCond = "someSet";
            } else {
                trialStopCondSet = new HashMap<String, Integer>();
                for (FieldStruct f : trialInSetOutcomeFields) {  //  CHECK FOR MULTIPLE OUTCOMES IN LIST HAPPENING
                    String name = f.one.getText();
                    int num = Integer.parseInt(f.two.getText());
                    trialStopCondSet.put(name, num);
                }
                t.setStopCondSet(trialStopCondSet);
                stopCond = "allSet";
            }
            t.setStopConditionType(stopCond);

        }

        tabsFrame.setSelectedIndex(3);
    } else {
    }

}//GEN-LAST:event_nextButton2ActionPerformed

private void varNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varNextButtonActionPerformed
    if (varNumMatchButton.isSelected()) {
        tp.varCase = 1;
    } else if (varObsDiffMeanButton.isSelected()) {
        tp.varCase = 8;
        tp.populationType = "categorical";
    } else if (varSampleMeanDiffButton.isSelected()) {
        tp.varCase = 7;
        tp.populationType = "numerical";
    } else if (categoreyDiffButton.isSelected()) {
        tp.category = varSelecteCategoryTextField.getText();

        tp.varCase = 5;


    } else if (meanDiffGroupButton.isSelected()) {
        tp.varCase = 4;
    } else if (categoreyPopDiffButton.isSelected()) {
        tp.category = varSelecteCategoryTextField.getText();
        tp.varCase = 7;
        tp.populationType = "categorical";
    }

    tabsFrame.setSelectedIndex(4);
}//GEN-LAST:event_varNextButtonActionPerformed

private void defaultBinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultBinMenuItemActionPerformed
    setBinSpinner.setVisible(false);
}//GEN-LAST:event_defaultBinMenuItemActionPerformed

private void customBinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customBinMenuItemActionPerformed
    setBinSpinner.setVisible(true);
}//GEN-LAST:event_customBinMenuItemActionPerformed

private void dataTwoPopPrbDisplay1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTwoPopPrbDisplay1MouseClicked
    System.out.println("\n*** Population 2  Display Probability ***");

    dataOutcomeList1 = new ArrayList<Outcome>();
    GUIHelper.dataListLoader(dataOutcomeFields1, dataOutcomeList1);
    GUIHelper.displayPrb(dataOutcomeFields1, dataOutcomeList1, dataOutcomeListPanel1);
}//GEN-LAST:event_dataTwoPopPrbDisplay1MouseClicked

private void dataTwoPopUpdateButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTwoPopUpdateButton1MouseClicked

    int numOutcomes = (Integer) numOutcomeField1.getValue();
    GUIHelper.dataFieldLoader(dataOutcomeListPanel1, dataOutcomeFields1, numOutcomes, 0);

    dataListScrollPane1.setBounds(dataListScrollPane1.getX(), dataListScrollPane1.getY(), 485, 211);

    dataListEnclosePanel1.setBounds(dataListEnclosePanel1.getX(), dataListEnclosePanel1.getY(), 485, 212);


    for (FieldStruct f : dataOutcomeFields1) {
        f.one.setVisible(true);
    }

    dataTwoPopPanel.revalidate();

}//GEN-LAST:event_dataTwoPopUpdateButton1MouseClicked

private void displayBarButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayBarButton1MouseClicked
    ChartLoader cL;
    ChartPanel cP;
    cL = new ChartLoader(ChartType.BARCHART);

    dataOutcomeList1 = new ArrayList<Outcome>();
    for (FieldStruct f : dataOutcomeFields1) {
        Outcome o = new Outcome();
        System.out.println(f.one.getText());
        o.outcomeName = f.one.getText();
        o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
        dataOutcomeList1.add(o);
    }
    Population p1 = new Population(dataOutcomeList1);

    if (twoGroupButton.isSelected()) {
        cP = cL.loadChart(p1);
    } else if (identicalPopButton.isSelected()) {

        Population p2 = new Population(p1);

        cP = cL.loadChart(p1, p2);

    } else {

        dataOutcomeList2 = new ArrayList<Outcome>();
        for (FieldStruct f : dataOutcomeFields2) {
            Outcome o = new Outcome();
            System.out.println(f.one.getText());
            o.outcomeName = f.one.getText();
            o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
            dataOutcomeList2.add(o);
        }
        Population p2 = new Population(dataOutcomeList2);

        cP = cL.loadChart(p1, p2);
    }
    JOptionPane.showMessageDialog(null, cP, "Bar Chart", 0);
}//GEN-LAST:event_displayBarButton1MouseClicked

private void upperBoundField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperBoundField2ActionPerformed
}//GEN-LAST:event_upperBoundField2ActionPerformed

private void varSampleMeanDiffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varSampleMeanDiffButtonActionPerformed
}//GEN-LAST:event_varSampleMeanDiffButtonActionPerformed

private void diffOption1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diffOption1ActionPerformed
    tp.setDiffGroup(1);
}//GEN-LAST:event_diffOption1ActionPerformed

private void diffOption2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diffOption2ActionPerformed
    tp.setDiffGroup(2);
}//GEN-LAST:event_diffOption2ActionPerformed

    private void restoreDefaults() {
        runTrialsInfoPanel.setVisible(false);
        trialVariableOutcomesInSetPanel.setVisible(false);
        trialVariableOutcomesInOrderPanel.setVisible(false);
        variableOutcomesInOrderPanel.setVisible(false);
        variableOutcomesInSetPanel.setVisible(false);
        trialStopConditionPanel.setVisible(false);
        variableSuccessConditionPanel.setVisible(false);
        dataOutcomeListPanel.setVisible(false);
        dataListEnclosePanel.setVisible(false);
        dataExternalFilePanel.setVisible(false);
        dataDistPanel.setVisible(false);
        dataOutcomeListPanel1.setVisible(false);
        dataListEnclosePanel1.setVisible(false);

        dataExternalFilePanel1.setVisible(false);
        dataDistPanel1.setVisible(false);
        dataOutcomeListPanel2.setVisible(false);

        dataListEnclosePanel2.setVisible(false);

        dataExternalFilePanel2.setVisible(false);
        dataDistPanel2.setVisible(false);
        trialVariableOutcomesInSetPanel1.setVisible(false);
        trialVariableOutcomesInOrderPanel1.setVisible(false);
        variableOutcomesInOrderPanel1.setVisible(false);
        variableOutcomesInSetPanel1.setVisible(false);
        trialStopConditionPanel1.setVisible(false);
        variableSuccessConditionPanel1.setVisible(false);
        trialVariableOutcomesInSetPanel2.setVisible(false);
        trialVariableOutcomesInOrderPanel2.setVisible(false);
        variableOutcomesInOrderPanel2.setVisible(false);
        variableOutcomesInSetPanel2.setVisible(false);
        trialStopConditionPanel2.setVisible(false);
        variableSuccessConditionPanel2.setVisible(false);


        t = new TrialRunner();

        tp = new TwoPopTrialRunner();

        listOutcomeButton.setSelected(false);
        distributionButton.setSelected(false);
        externalListButton.setSelected(false);

        listOutcomeButton1.setSelected(false);
        distributionButton1.setSelected(false);
        externalListButton1.setSelected(false);

        withReplacementButton3.setSelected(false);
        withReplacementButton.setSelected(false);

        withReplacementButton4.setSelected(false);


        conditionButton.setSelected(false);
        withReplacementButton.setSelected(false);
        trialSomeOutcomesInOrderButton.setSelected(false);
        trialSomeOutcomesInSetButton.setSelected(false);
        trialAllOutcomesInSetButton.setSelected(false);


        conditionButton3.setSelected(false);
        withReplacementButton3.setSelected(false);
        trialSomeOutcomesInOrderButton3.setSelected(false);
        trialSomeOutcomesInSetButton3.setSelected(false);
        trialAllOutcomesInSetButton3.setSelected(false);


        conditionButton4.setSelected(false);
        withReplacementButton4.setSelected(false);
        trialSomeOutcomesInOrderButton4.setSelected(false);
        trialSomeOutcomesInSetButton4.setSelected(false);
        trialAllOutcomesInSetButton4.setSelected(false);

        numSuccessButton.setSelected(false);
        containsSuccessButton.setSelected(false);
        meanButton.setSelected(false);
        modeButton.setSelected(false);
        medianButton.setSelected(false);

        someOutcomesInOrderButton.setSelected(false);
        allOutcomesInSetButton.setSelected(false);
        someOutcomesInSetButton.setSelected(false);


        varNumMatchButton.setSelected(false);
        varObsDiffMeanButton.setSelected(false);
        varSampleMeanDiffButton.setSelected(false);
        varNumMatchButton.setSelected(false);
        meanDiffGroupButton.setSelected(false);
        categoreyDiffButton.setSelected(false);



        fixedOutcomeButton3.setVisible(true);
        fixedOutcomeButton4.setVisible(true);
        trialTypeLabel3.setVisible(true);

    }

    private void displayDataOutcomeList() {
    }

    private void displayTrialOutcomeList() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ANDORButtonGroup;
    private javax.swing.JLabel TrialOutcomesLabel;
    private javax.swing.JRadioButton allOutcomesInSetButton;
    private javax.swing.JRadioButton allOutcomesInSetButton1;
    private javax.swing.JRadioButton allOutcomesInSetButton2;
    private javax.swing.JMenuItem barChartItem;
    private javax.swing.JButton binButton;
    private javax.swing.JPanel binPanel;
    private javax.swing.JMenuItem binnedDotPlotItem;
    private javax.swing.JRadioButton binomialButton;
    private javax.swing.JRadioButton binomialButton1;
    private javax.swing.JRadioButton binomialButton2;
    private javax.swing.JMenuItem boxPlotItem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton categoreyDiffButton;
    private javax.swing.JRadioButton categoreyPopDiffButton;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JFrame chartFrame;
    private javax.swing.JRadioButton conditionButton;
    private javax.swing.JRadioButton conditionButton1;
    private javax.swing.JRadioButton conditionButton2;
    private javax.swing.JRadioButton conditionButton3;
    private javax.swing.JRadioButton conditionButton4;
    private javax.swing.JRadioButton containsSuccessButton;
    private javax.swing.JMenuItem customBinMenuItem;
    private javax.swing.JPanel dataButtonPanel;
    private javax.swing.JButton dataDisplayPrbButton;
    private javax.swing.JLabel dataDistLowerBoundLabel;
    private javax.swing.JLabel dataDistLowerBoundLabel1;
    private javax.swing.JLabel dataDistLowerBoundLabel2;
    private javax.swing.JTextField dataDistLowerBoundTextField;
    private javax.swing.JTextField dataDistLowerBoundTextField1;
    private javax.swing.JTextField dataDistLowerBoundTextField2;
    private javax.swing.JPanel dataDistPanel;
    private javax.swing.JPanel dataDistPanel1;
    private javax.swing.JPanel dataDistPanel2;
    private javax.swing.JLabel dataDistUpperBoundLabel;
    private javax.swing.JLabel dataDistUpperBoundLabel1;
    private javax.swing.JLabel dataDistUpperBoundLabel2;
    private javax.swing.JTextField dataDistUpperBoundTextField;
    private javax.swing.JTextField dataDistUpperBoundTextField1;
    private javax.swing.JTextField dataDistUpperBoundTextField2;
    private javax.swing.JPanel dataExternalFilePanel;
    private javax.swing.JPanel dataExternalFilePanel1;
    private javax.swing.JPanel dataExternalFilePanel2;
    private javax.swing.JPanel dataListEnclosePanel;
    private javax.swing.JPanel dataListEnclosePanel1;
    private javax.swing.JPanel dataListEnclosePanel2;
    private javax.swing.JScrollPane dataListScrollPane1;
    private javax.swing.JPanel dataOutcomeListPanel;
    private javax.swing.JPanel dataOutcomeListPanel1;
    private javax.swing.JPanel dataOutcomeListPanel2;
    private javax.swing.JScrollPane dataOutcomeListPanel2ScrollPane;
    private javax.swing.JScrollPane dataOutcomeListScrollPane;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel dataPop1Panel;
    private javax.swing.JPanel dataPop2Panel;
    private javax.swing.JFrame dataPopupFrame;
    private javax.swing.JButton dataTwoPopNextButton;
    private javax.swing.JPanel dataTwoPopPanel;
    private javax.swing.JButton dataTwoPopPrbDisplay1;
    private javax.swing.JButton dataTwoPopPrbDisplay2;
    private javax.swing.JLabel dataTwoPopPrbLabel1;
    private javax.swing.JLabel dataTwoPopPrbLabel2;
    private javax.swing.JButton dataTwoPopUpdateButton1;
    private javax.swing.JButton dataTwoPopUpdateButton2;
    private javax.swing.JButton dataUpdateOutcomeButton;
    private javax.swing.JRadioButton defaultBinButton;
    private javax.swing.JMenuItem defaultBinMenuItem;
    private javax.swing.JRadioButton diffOption1;
    private javax.swing.JRadioButton diffOption2;
    private javax.swing.JPanel diffPanel;
    private javax.swing.ButtonGroup differenceSettingsButtonGroup;
    private javax.swing.JButton displayBarButton;
    private javax.swing.JButton displayBarButton1;
    private javax.swing.JButton displayDescriptiveStatsButton;
    private javax.swing.JButton displayDistributionButton;
    private javax.swing.JButton displayGraphButton;
    private javax.swing.JButton displayHistogramButton;
    private javax.swing.JButton displayHistogramButton1;
    private javax.swing.JButton displayHistogramButton2;
    private javax.swing.JRadioButton distributionButton;
    private javax.swing.JRadioButton distributionButton1;
    private javax.swing.JRadioButton distributionButton2;
    private javax.swing.ButtonGroup distributionButtonGroup;
    private javax.swing.ButtonGroup distributionButtonGroup2;
    private javax.swing.JLabel distributionLabel;
    private javax.swing.JLabel distributionLabel1;
    private javax.swing.JLabel distributionLabel2;
    private javax.swing.JMenuItem dotPlotItem;
    private javax.swing.JRadioButton expButton;
    private javax.swing.JRadioButton expButton1;
    private javax.swing.JRadioButton expButton2;
    private javax.swing.JRadioButton externalListButton;
    private javax.swing.JRadioButton externalListButton1;
    private javax.swing.JRadioButton externalListButton2;
    private javax.swing.JRadioButton fixedOutcomeButton;
    private javax.swing.JRadioButton fixedOutcomeButton1;
    private javax.swing.JRadioButton fixedOutcomeButton2;
    private javax.swing.JRadioButton fixedOutcomeButton3;
    private javax.swing.JRadioButton fixedOutcomeButton4;
    private javax.swing.JSpinner graphTypeButton;
    private javax.swing.JPopupMenu graphTypePopUp;
    private javax.swing.JMenuItem histogramItem;
    private javax.swing.JMenuItem histogramWithFitItem;
    private javax.swing.JRadioButton identicalPopButton;
    private javax.swing.JPanel introPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton listOutcomeButton;
    private javax.swing.JRadioButton listOutcomeButton1;
    private javax.swing.JRadioButton listOutcomeButton2;
    private javax.swing.JTextField lowerBoundField1;
    private javax.swing.JTextField lowerBoundField2;
    private javax.swing.JTextField lowerBoundField3;
    private javax.swing.JLabel lowerBoundLabel;
    private javax.swing.JLabel lowerBoundLabel1;
    private javax.swing.JLabel lowerBoundLabel2;
    private javax.swing.JRadioButton meanButton;
    private javax.swing.JRadioButton meanDiffGroupButton;
    private javax.swing.JRadioButton meanWithSTDButton;
    private javax.swing.JRadioButton medianButton;
    private javax.swing.JRadioButton modeButton;
    private javax.swing.JRadioButton modeSizeButton;
    private javax.swing.JButton nextButton0;
    private javax.swing.JButton nextButton1;
    private javax.swing.JButton nextButton2;
    private javax.swing.JButton nextButton3;
    private javax.swing.JButton nextButton5;
    private javax.swing.JButton nextButton6;
    private javax.swing.JRadioButton normalDistButton;
    private javax.swing.JRadioButton normalDistButton1;
    private javax.swing.JRadioButton normalDistButton2;
    private javax.swing.JLabel numDistinctOutcomeLabel;
    private javax.swing.JLabel numDistinctOutcomeLabel1;
    private javax.swing.JLabel numDistinctOutcomeLabel2;
    private javax.swing.JLabel numDistinctOutcomeLabel3;
    private javax.swing.JLabel numDistinctOutcomeLabel4;
    private javax.swing.JLabel numDistinctOutcomeLabel5;
    private javax.swing.JLabel numDistinctOutcomeLabel6;
    private javax.swing.JLabel numDistinctOutcomeLabel7;
    private javax.swing.JSpinner numDistinctOutcomeTextField;
    private javax.swing.JTextField numDistinctOutcomeTextField2;
    private javax.swing.JTextField numDistinctOutcomeTextField3;
    private javax.swing.JTextField numDistinctOutcomeTextField4;
    private javax.swing.JSpinner numDistinctOutcomeTextField5;
    private javax.swing.JTextField numDistinctOutcomeTextField6;
    private javax.swing.JSpinner numDistinctOutcomesTextField1;
    private javax.swing.JLabel numObservationLabel;
    private javax.swing.JLabel numObservationLabel1;
    private javax.swing.JLabel numObservationLabel2;
    private javax.swing.JLabel numObservationLabel3;
    private javax.swing.JLabel numObservationLabel4;
    private javax.swing.JSpinner numObservationTextField;
    private javax.swing.JTextField numObservationTextField1;
    private javax.swing.JTextField numObservationTextField2;
    private javax.swing.JSpinner numOutcomeField;
    private javax.swing.JSpinner numOutcomeField1;
    private javax.swing.JSpinner numOutcomeField2;
    private javax.swing.JLabel numOutcomeLabel;
    private javax.swing.JLabel numOutcomeLabel1;
    private javax.swing.JLabel numOutcomeLabel2;
    private javax.swing.JRadioButton numSuccessButton;
    private javax.swing.JSpinner numTrialField;
    private javax.swing.JLabel numTrialLabel;
    private javax.swing.JRadioButton onePopButton1;
    private javax.swing.JLabel orderValueLabel1v2;
    private javax.swing.JLabel orderValueLabel1v3;
    private javax.swing.JLabel orderValueLabel1v4;
    private javax.swing.JTextField outcomeNameField1;
    private javax.swing.JTextField outcomeNameField2;
    private javax.swing.JTextField outcomeNameField3;
    private javax.swing.JLabel outcomeNameLabel;
    private javax.swing.JLabel outcomeNameLabel1;
    private javax.swing.JLabel outcomeNameLabel2;
    private javax.swing.JTextArea outcomeTextArea;
    private javax.swing.JTextField outcomeTextField1v2;
    private javax.swing.JTextField outcomeTextField1v3;
    private javax.swing.JTextField outcomeTextField1v4;
    private javax.swing.JRadioButton percentSuccessButton;
    private javax.swing.JMenuItem pieChartItem;
    private javax.swing.ButtonGroup populationButtonGroup;
    private javax.swing.ButtonGroup populationButtonGroup2;
    private javax.swing.JLabel probabilityLabel;
    private javax.swing.JLabel probabilityLabel1;
    private javax.swing.JLabel probabilityLabel2;
    private javax.swing.JLabel probabilityLabel4;
    private javax.swing.JPanel progressPanel;
    private javax.swing.JTextArea randIntTextArea;
    private javax.swing.JLabel randomIntegersLabel;
    private javax.swing.JTextArea responseVariableTextArea;
    private javax.swing.JLabel responseVariableValues;
    private javax.swing.JPanel runDisplayPanel;
    private javax.swing.JPanel runPanel;
    private javax.swing.JButton runTrialsButton;
    private javax.swing.JPanel runTrialsInfoPanel;
    private javax.swing.ButtonGroup sampleButtonGroup;
    private javax.swing.ButtonGroup sampleButtonGroup2;
    private javax.swing.ButtonGroup sampleTypeButtonGroup;
    private javax.swing.ButtonGroup sampleTypeButtonGroup2;
    private javax.swing.ButtonGroup sampleTypeButtonGrouup2;
    private javax.swing.JLabel sampleTypeLabel;
    private javax.swing.JLabel sampleTypeLabel1;
    private javax.swing.JLabel sampleTypeLabel2;
    private javax.swing.JLabel sampleTypeLabel3;
    private javax.swing.JLabel sampleTypeLabel4;
    private javax.swing.JButton saveIntegersButton;
    private javax.swing.JButton saveOutcomesButton;
    private javax.swing.JButton saveResponsesButton;
    private javax.swing.JButton selectExternalFileButton;
    private javax.swing.JButton selectExternalFileButton1;
    private javax.swing.JButton selectExternalFileButton2;
    private javax.swing.JRadioButton setBinNumberButton;
    private javax.swing.JPanel setBinPanel;
    private javax.swing.JPopupMenu setBinPopup;
    private javax.swing.JSpinner setBinSpinner;
    private javax.swing.JLabel setDifferenceButton;
    private javax.swing.JButton setGraphTypeButton2;
    private javax.swing.JRadioButton someOutcomesInOrderButton;
    private javax.swing.JRadioButton someOutcomesInOrderButton1;
    private javax.swing.JRadioButton someOutcomesInOrderButton2;
    private javax.swing.JRadioButton someOutcomesInSetButton;
    private javax.swing.JRadioButton someOutcomesInSetButton1;
    private javax.swing.JRadioButton someOutcomesInSetButton2;
    private javax.swing.ButtonGroup stopCondButtonGroup2;
    private javax.swing.ButtonGroup stopConditionButtonGroup;
    private javax.swing.JLabel stopConditionLabel;
    private javax.swing.JLabel stopConditionLabel1;
    private javax.swing.JLabel stopConditionLabel2;
    private javax.swing.JLabel stopConditionLabel3;
    private javax.swing.JLabel stopConditionLabel4;
    private javax.swing.ButtonGroup successButtonGroup;
    private javax.swing.JLabel successConditionLabel;
    private javax.swing.JLabel successConditionLabel1;
    private javax.swing.JLabel successConditionLabel2;
    private javax.swing.JRadioButton sumButton;
    private javax.swing.JTabbedPane tabsFrame;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JRadioButton trialAllOutcomesInSetButton;
    private javax.swing.JRadioButton trialAllOutcomesInSetButton1;
    private javax.swing.JRadioButton trialAllOutcomesInSetButton2;
    private javax.swing.JRadioButton trialAllOutcomesInSetButton3;
    private javax.swing.JRadioButton trialAllOutcomesInSetButton4;
    private javax.swing.JRadioButton trialAndButton;
    private javax.swing.ButtonGroup trialButtonGroup;
    private javax.swing.JPanel trialButtonPanel;
    private javax.swing.JPanel trialButtonPanel1;
    private javax.swing.JPanel trialButtonPanel2;
    private javax.swing.JPanel trialButtonPanel3;
    private javax.swing.JPanel trialButtonPanel4;
    private javax.swing.JPanel trialFixedOutcomePanel;
    private javax.swing.JPanel trialFixedOutcomePanel1;
    private javax.swing.JPanel trialFixedOutcomePanel2;
    private javax.swing.JPanel trialFixedOutcomePanel3;
    private javax.swing.JPanel trialFixedOutcomePanel4;
    private javax.swing.JSpinner trialInOrderField2;
    private javax.swing.JRadioButton trialLengthButton;
    private javax.swing.JSpinner trialNumObservationTextField1;
    private javax.swing.JSpinner trialNumObservationTextField2;
    private javax.swing.JLabel trialNumOutcomesLabel;
    private javax.swing.JLabel trialNumOutcomesLabel1;
    private javax.swing.JLabel trialNumOutcomesLabel2;
    private javax.swing.JLabel trialNumOutcomesLabel3;
    private javax.swing.JLabel trialNumOutcomesLabel4;
    private javax.swing.JSpinner trialNumOutcomesTextField;
    private javax.swing.JTextField trialNumOutcomesTextField1;
    private javax.swing.JTextField trialNumOutcomesTextField2;
    private javax.swing.JLabel trialNumTimesLabel;
    private javax.swing.JLabel trialNumTimesLabel1;
    private javax.swing.JLabel trialNumTimesLabel2;
    private javax.swing.JLabel trialNumTimesLabel3;
    private javax.swing.JLabel trialNumTimesLabel4;
    private javax.swing.JTextField trialNumTimesTextField1;
    private javax.swing.JTextField trialNumTimesTextField2;
    private javax.swing.JTextField trialNumTimesTextField3;
    private javax.swing.JTextField trialNumTimesTextField4;
    private javax.swing.JTextField trialNumTimesTextField5;
    private javax.swing.JTextField trialNumTimesTextField6;
    private javax.swing.JTextField trialNumTimesTextField7;
    private javax.swing.JTextField trialNumTimesTextField8;
    private javax.swing.JRadioButton trialOrButton;
    private javax.swing.JLabel trialOrderLabel;
    private javax.swing.JLabel trialOrderLabel1;
    private javax.swing.JLabel trialOrderLabel2;
    private javax.swing.JLabel trialOrderLabel3;
    private javax.swing.JLabel trialOrderLabel4;
    private javax.swing.JLabel trialOrderValueLabel1;
    private javax.swing.JLabel trialOrderValueLabel2;
    private javax.swing.JLabel trialOrderValueLabel3;
    private javax.swing.JLabel trialOrderValueLabel4;
    private javax.swing.JLabel trialOrderValueLabel5;
    private javax.swing.JLabel trialOutcomeLabel;
    private javax.swing.JLabel trialOutcomeLabel1;
    private javax.swing.JLabel trialOutcomeLabel10;
    private javax.swing.JLabel trialOutcomeLabel11;
    private javax.swing.JLabel trialOutcomeLabel12;
    private javax.swing.JLabel trialOutcomeLabel2;
    private javax.swing.JLabel trialOutcomeLabel3;
    private javax.swing.JLabel trialOutcomeLabel4;
    private javax.swing.JLabel trialOutcomeLabel5;
    private javax.swing.JLabel trialOutcomeLabel6;
    private javax.swing.JLabel trialOutcomeLabel7;
    private javax.swing.JLabel trialOutcomeLabel8;
    private javax.swing.JLabel trialOutcomeLabel9;
    private javax.swing.JTextField trialOutcomeTextField1;
    private javax.swing.JTextField trialOutcomeTextField2;
    private javax.swing.JTextField trialOutcomeTextField3;
    private javax.swing.JTextField trialOutcomeTextField4;
    private javax.swing.JTextField trialOutcomeTextField5;
    private javax.swing.JTextField trialOutcomeValueTextField1;
    private javax.swing.JTextField trialOutcomeValueTextField2;
    private javax.swing.JTextField trialOutcomeValueTextField3;
    private javax.swing.JTextField trialOutcomeValueTextField4;
    private javax.swing.JTextField trialOutcomeValueTextField5;
    private javax.swing.JTextField trialOutcomeValueTextField6;
    private javax.swing.JTextField trialOutcomeValueTextField7;
    private javax.swing.JTextField trialOutcomeValueTextField8;
    private javax.swing.JPanel trialPanel;
    private javax.swing.JPanel trialPop1Panel;
    private javax.swing.JPanel trialPop2Panel;
    private javax.swing.JSpinner trialSequenceField1;
    private javax.swing.JSpinner trialSetField2;
    private javax.swing.JRadioButton trialSomeOutcomesInOrderButton;
    private javax.swing.JRadioButton trialSomeOutcomesInOrderButton1;
    private javax.swing.JRadioButton trialSomeOutcomesInOrderButton2;
    private javax.swing.JRadioButton trialSomeOutcomesInOrderButton3;
    private javax.swing.JRadioButton trialSomeOutcomesInOrderButton4;
    private javax.swing.JRadioButton trialSomeOutcomesInSetButton;
    private javax.swing.JRadioButton trialSomeOutcomesInSetButton1;
    private javax.swing.JRadioButton trialSomeOutcomesInSetButton2;
    private javax.swing.JRadioButton trialSomeOutcomesInSetButton3;
    private javax.swing.JRadioButton trialSomeOutcomesInSetButton4;
    private javax.swing.JPanel trialStopConditionPanel;
    private javax.swing.JPanel trialStopConditionPanel1;
    private javax.swing.JPanel trialStopConditionPanel2;
    private javax.swing.JPanel trialStopConditionPanel3;
    private javax.swing.JPanel trialStopConditionPanel4;
    private javax.swing.JButton trialTwoPopNextButton2;
    private javax.swing.JPanel trialTwoPopPanel;
    private javax.swing.JLabel trialTypeButton;
    private javax.swing.JLabel trialTypeButton1;
    private javax.swing.JLabel trialTypeButton2;
    private javax.swing.ButtonGroup trialTypeButtonGroup2;
    private javax.swing.JLabel trialTypeLabel3;
    private javax.swing.JLabel trialTypeLabel4;
    private javax.swing.JButton trialUpdateButton;
    private javax.swing.JButton trialUpdateButton1;
    private javax.swing.JPanel trialVariableOutcomesInOrderPanel;
    private javax.swing.JPanel trialVariableOutcomesInOrderPanel1;
    private javax.swing.JPanel trialVariableOutcomesInOrderPanel2;
    private javax.swing.JPanel trialVariableOutcomesInOrderPanel3;
    private javax.swing.JPanel trialVariableOutcomesInOrderPanel4;
    private javax.swing.JPanel trialVariableOutcomesInSetPanel;
    private javax.swing.JPanel trialVariableOutcomesInSetPanel1;
    private javax.swing.JPanel trialVariableOutcomesInSetPanel2;
    private javax.swing.JPanel trialVariableOutcomesInSetPanel3;
    private javax.swing.JPanel trialVariableOutcomesInSetPanel4;
    private javax.swing.JRadioButton twoGroupButton;
    private javax.swing.JRadioButton twoPopButton;
    private javax.swing.JButton twoPopTrialDistinctUpdateButton1;
    private javax.swing.JButton twoPopTrialDistinctUpdateButton2;
    private javax.swing.JButton twoPopTrialDistinctUpdateButton3;
    private javax.swing.JButton twoPopTrialDistinctUpdateButton4;
    private javax.swing.JPanel twoPopTrialPanel;
    private javax.swing.JButton twoPopTrialSequenceUpdateButton1;
    private javax.swing.JButton twoPopTrialSequenceUpdateButton2;
    private javax.swing.JButton twoPopTrialSequenceUpdateButton3;
    private javax.swing.JButton twoPopTrialSequenceUpdateButton4;
    private javax.swing.JPanel twoPopVarPanel;
    private javax.swing.JRadioButton uniformDistButton;
    private javax.swing.JRadioButton uniformDistButton1;
    private javax.swing.JRadioButton uniformDistButton2;
    private javax.swing.JTextField upperBoundField1;
    private javax.swing.JTextField upperBoundField2;
    private javax.swing.JTextField upperBoundField3;
    private javax.swing.JLabel upperBoundLabel;
    private javax.swing.JLabel upperBoundLabel1;
    private javax.swing.JLabel upperBoundLabel2;
    private javax.swing.JRadioButton varDiffMeanButton;
    private javax.swing.JRadioButton varDiffPrcSuccessButton;
    private javax.swing.JRadioButton varMeanInEachButton;
    private javax.swing.JButton varNextButton;
    private javax.swing.JRadioButton varNumMatchButton;
    private javax.swing.JRadioButton varObsDiffMeanButton;
    private javax.swing.JButton varOrderUpdateButton;
    private javax.swing.JRadioButton varPrcSuccessInEachButton;
    private javax.swing.JRadioButton varSampleMeanDiffButton;
    private javax.swing.JTextField varSelecteCategoryTextField;
    private javax.swing.JButton varSetUpdateButton;
    private javax.swing.ButtonGroup variableButtonGroup;
    private javax.swing.JPanel variableButtons;
    private javax.swing.JPanel variableButtons1;
    private javax.swing.JPanel variableButtons2;
    private javax.swing.JLabel variableMeasureLabel;
    private javax.swing.JLabel variableMeasureLabel1;
    private javax.swing.JLabel variableMeasureLabel2;
    private javax.swing.JSpinner variableNumOutcomesTextField;
    private javax.swing.JTextField variableNumOutcomesTextField1;
    private javax.swing.JTextField variableNumOutcomesTextField2;
    private javax.swing.JLabel variableNumTimesLabel1;
    private javax.swing.JLabel variableNumTimesLabel2;
    private javax.swing.JLabel variableNumTimesLabel4;
    private javax.swing.JLabel variableOrderLabel;
    private javax.swing.JLabel variableOrderLabel1;
    private javax.swing.JLabel variableOrderLabel2;
    private javax.swing.JLabel variableOutcomeLabel;
    private javax.swing.JLabel variableOutcomeLabel1;
    private javax.swing.JLabel variableOutcomeLabel2;
    private javax.swing.JPanel variableOutcomesInOrderPanel;
    private javax.swing.JPanel variableOutcomesInOrderPanel1;
    private javax.swing.JPanel variableOutcomesInOrderPanel2;
    private javax.swing.JPanel variableOutcomesInSetPanel;
    private javax.swing.JPanel variableOutcomesInSetPanel1;
    private javax.swing.JPanel variableOutcomesInSetPanel2;
    private javax.swing.JPanel variablePanel;
    private javax.swing.JPanel variableSuccessConditionPanel;
    private javax.swing.JPanel variableSuccessConditionPanel1;
    private javax.swing.JPanel variableSuccessConditionPanel2;
    private javax.swing.JPanel variableTwoPopPanel;
    private javax.swing.JLabel variablenumOutcomesLabel;
    private javax.swing.JLabel variablenumOutcomesLabel1;
    private javax.swing.JLabel variablenumOutcomesLabel2;
    private javax.swing.JRadioButton withReplacementButton;
    private javax.swing.JRadioButton withReplacementButton1;
    private javax.swing.JRadioButton withReplacementButton2;
    private javax.swing.JRadioButton withReplacementButton3;
    private javax.swing.JRadioButton withReplacementButton4;
    private javax.swing.JRadioButton withoutReplacementButton;
    private javax.swing.JRadioButton withoutReplacementButton1;
    private javax.swing.JRadioButton withoutReplacementButton2;
    private javax.swing.JRadioButton withoutReplacementButton3;
    private javax.swing.JRadioButton withoutReplacementButton4;
    // End of variables declaration//GEN-END:variables

    private boolean checkTwoPopTrialErrors() {
        return false;

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
