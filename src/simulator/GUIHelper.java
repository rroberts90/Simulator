
package simulator;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Contains functions that make dynamic changes to the GUI
 * 
 * @author Richard
 */
public class GUIHelper {

    final static int LIST_OFFSET = 150;
    final static int OUTCOME_SEQUENCE_OFFSET = 200;
    final static int OUTCOME_SET_OFFSET = 200;
    final static int LIST_PANEL_WIDTH = 441;

    /**
     *  Adds or removes data fields from the gui.  
     *  Parameters: p - the fields' parent panel
     *              list - the list holding the fields.  
     *              numOutcomes - the number of fields that should be displayed when finished executing
     * 
     * type - 0: outcomeList, 1: ordered sequence, 2: set of outcome / values
     *   Preconditions: the zero field is already loaded into the arraylist.  
     * @param 
     * @param f
     * @param type 
     */
    public static void dataFieldLoader(JPanel p, ArrayList<FieldStruct> list, int numOutcomes, int type) {
        int xSpacing = 2;
        int ySpacing = 2;
        int height = (list.get(0).one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;

        if (type == 0) {
            System.out.println("\n**************update called******************");

            int i = 0;
            int start = 0;
            try {
                boolean add = true;

                if (list.size() > numOutcomes) {
                    int numRemove = list.size() - numOutcomes;
                    for (i = 0; i < numRemove; i++) {
                        FieldStruct f = list.remove(list.size() - 1);

                        p.remove(f.one);
                        p.remove(f.two);

                        p.remove(f.three);
                        p.remove(f.prbLabel);

                        f.one.setVisible(false);
                        f.two.setVisible(false);
                        f.three.setVisible(false);
                        f.prbLabel.setVisible(false);

                    }
                    p.validate();

                    p.setVisible(false);
                    p.setVisible(true);
                    add = false;
                    height = (list.get(0).one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;
                } else if (list.size() < numOutcomes) {
                    start = list.size();
                    add = true;

                } else if (list.size() == numOutcomes) {
                    add = false;
                }
                if (add) {

                    FieldStruct one = list.get(list.size() - 1);
                    int nameX = one.one.getX(); //+ outcomeNameField1.getHeight() + xSpacing;
                    int lowerX = one.two.getX();
                    int upperX = one.three.getX();
                    int prbLabelX = one.prbLabel.getX();

                    int nameY = one.one.getY() + one.one.getHeight() + ySpacing;
                    int lowerY = one.two.getY() + one.two.getHeight() + ySpacing;
                    int upperY = one.three.getY() + one.three.getHeight() + ySpacing;
                    int prbLabelY = one.prbLabel.getY() + one.one.getHeight() + ySpacing;
                    System.out.println("processed init stuff");
                    for (i = start; i < numOutcomes; i++) {

                        JTextField anyn1 = new JTextField();
                        anyn1.setSize(one.one.getWidth(), one.one.getHeight());
                        anyn1.setLocation(nameX, nameY);
                        p.add(anyn1);

                        JTextField anyn2 = new JTextField();
                        anyn2.setSize(one.two.getWidth(), one.two.getHeight());
                        anyn2.setLocation(lowerX, lowerY);
                        p.add(anyn2);

                        JTextField anyn3 = new JTextField();
                        anyn3.setSize(one.three.getWidth(), one.three.getHeight());
                        anyn3.setLocation(upperX, upperY);
                        p.add(anyn3);

                        JLabel anyn4 = new JLabel();
                        anyn4.setSize(one.prbLabel.getWidth(), one.prbLabel.getHeight());
                        anyn4.setLocation(prbLabelX, prbLabelY);
                        p.add(anyn4);
                        anyn4.setVisible(false);

                        FieldStruct struct = new FieldStruct();

                        list.add(struct);
                        struct.one = anyn1;
                        struct.two = anyn2;
                        struct.three = anyn3;
                        struct.prbLabel = anyn4;

                        System.out.println("processed button ");
                        nameY = nameY + one.one.getHeight() + ySpacing;
                        lowerY = lowerY + one.two.getHeight() + ySpacing;
                        upperY = upperY + one.three.getHeight() + ySpacing;
                        prbLabelY = prbLabelY + one.one.getHeight() + ySpacing;

                    }
                    FieldStruct last = list.get(list.size() - 1);

                    height = (last.one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;
                    System.out.println("Panel height: " + height);
                    
                    System.out.println("Last height:  " + last.one.getHeight());
                    p.revalidate();                    // nameY = nameY - one.one.getY();

                    System.out.println("finished post processing");
                    System.out.println("List Size:" + list.size());
                }



            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading data fields", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Entered a non integer");
            }
            p.setPreferredSize(new Dimension(p.getWidth(), height));

            p.validate();
            p.setVisible(false);
            p.setVisible(true);

        } else if (type == 1) {
            System.out.println("\n*********************** order update called***************************");

            try {



                int i = 0;
                int start = 0;

                System.out.println("Number of Outcomes:" + numOutcomes);
                boolean add = true;

                if (list.size() > numOutcomes) {
                    int numRemove = list.size() - numOutcomes;
                    for (i = 0; i < numRemove; i++) {
                        FieldStruct f = list.remove(list.size() - 1);

                        p.remove(f.one);
                        p.remove(f.label);

                        f.one.setVisible(false);
                        f.label.setVisible(false);

                    }
                    System.out.println("Case 1: Trimmed box list " + list.size());
                    p.validate();
                    add = false;
                    height = (list.get(0).one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;

                } else if (list.size() < numOutcomes) {
                    System.out.println("Case 2: ");
                    start = list.size();
                    add = true;

                } else if (list.size() == numOutcomes) {
                    add = false;
                }
                if (add) {
                    System.out.println("Starting Position: " + start);

                    xSpacing = 2;
                    ySpacing = 2;


                    FieldStruct one = list.get(list.size() - 1);
                    int nameX = one.label.getX(); //+ outcomeNameField1.getHeight() + xSpacing;
                    int name2X = one.one.getX();

                    int nameY = one.label.getY() + one.label.getHeight() + ySpacing;
                    int name2Y = one.one.getY() + one.one.getHeight() + ySpacing;

                    System.out.println("preprocessing done");
                    for (i = start; i < numOutcomes; i++) {

                        JLabel anyn1 = new JLabel();
                        anyn1.setSize(one.label.getWidth(), one.label.getHeight());
                        anyn1.setLocation(nameX, nameY);
                        p.add(anyn1);
                        anyn1.setText((i + 1) + "");
                        System.out.println("Missing Label Text: " + anyn1.getText());

                        JTextField anyn2 = new JTextField();
                        anyn2.setSize(one.one.getWidth(), one.one.getHeight());
                        anyn2.setLocation(name2X, name2Y);
                        p.add(anyn2);

                        nameY = nameY + one.one.getHeight() + ySpacing;
                        name2Y = name2Y + one.one.getHeight() + ySpacing;

                        FieldStruct struct = new FieldStruct();

                        list.add(struct);
                        struct.label = anyn1;
                        struct.one = anyn2;
                        System.out.println("button processed");


                    }

                    FieldStruct last = list.get(list.size() - 1);
                    height = (last.one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;


                    System.out.println("List Size: " + list.size());
                    System.out.println("postprocessing done");
                    System.out.println("***********************update finished***************************");


                }
            } catch (Exception e) {
                System.out.println("Entered a non integer");
            }
            p.setPreferredSize(new Dimension(LIST_PANEL_WIDTH, height));
            p.validate();

        } else {
            System.out.println("\n***********************update called***************************");
            xSpacing = 2;
            ySpacing = 2;
            int i = 0;
            int start = 0;
            try {
                System.out.println("Number of Outcomes:" + numOutcomes);
                System.out.println("List Size:" + list.size());

                boolean add = true;
   
                if (list.size() > numOutcomes) {
                    int numRemove = list.size() - numOutcomes;
                    for (i = 0; i < numRemove; i++) {
                        FieldStruct f = list.remove(list.size() - 1);

                        p.remove(f.one);
                        p.remove(f.two);

                        f.one.setVisible(false);
                        f.two.setVisible(false);

                    }
                    System.out.println("Case 1: Trimmed box list " + list.size());
                    p.validate();
                    p.setVisible(false);
                    p.setVisible(true);
                    add = false;
                    height = (list.get(0).one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;

                } else if (list.size() < numOutcomes) {
                    start = list.size();
                    add = true;

                } else if (list.size() == numOutcomes) {
                    add = false;
                }
                if (add) {
                    System.out.println("Starting Position: " + start);
       
                    FieldStruct one = list.get(list.size() - 1);
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
                        p.add(anyn1);

                        JTextField anyn2 = new JTextField();
                        anyn2.setSize(one.two.getWidth(), one.two.getHeight());
                        anyn2.setLocation(lowerX, lowerY);
                        p.add(anyn2);

                        nameY = nameY + one.one.getHeight() + ySpacing;
                        lowerY = lowerY + one.two.getHeight() + ySpacing;

                        FieldStruct struct = new FieldStruct();

                        list.add(struct);
                        struct.one = anyn1;
                        struct.two = anyn2;
                        System.out.println("processed button ");

                    }

                    FieldStruct last = list.get(list.size() - 1);
                    height = (last.one.getHeight() + ySpacing) * list.size() + LIST_OFFSET;

                    System.out.println("finished post processing");
                    System.out.println("List Size:" + list.size());
                    System.out.println("***********************update finished***************************");

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading data fields. Check that the entered number is an integer", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println("Entered a non integer");
            }
            p.setPreferredSize(new Dimension(p.getWidth(), height));
            p.validate();
            p.setVisible(false);
            p.setVisible(true);
        }
    }

    public static void clearDataFields(JPanel p, ArrayList<FieldStruct> fieldList, int type) {
        System.out.println("FieldList Size0: " + fieldList.size());
        int heightOffset = 0;
        if (fieldList.size() > 1) {
            int n = fieldList.size();
            if (type == 0) {
                heightOffset = LIST_OFFSET;
                FieldStruct zero = fieldList.get(0);
                zero.one.setText("");
                zero.two.setText("");
                zero.three.setText("");
                zero.prbLabel.setText("");

                for (int i = 1; i < n; i++) {
                    System.out.println("Removing field at position ..." + (fieldList.size() - 1));
                    FieldStruct f = fieldList.remove(fieldList.size() - 1);

                    p.remove(f.one);
                    p.remove(f.two);

                    p.remove(f.three);
                    p.remove(f.prbLabel);

                    f.one.setVisible(false);
                    f.two.setVisible(false);
                    f.three.setVisible(false);
                    f.prbLabel.setVisible(false);
                }

            } else if (type == 1) {
                heightOffset = OUTCOME_SEQUENCE_OFFSET;
                FieldStruct zero = fieldList.get(0);
                zero.one.setText("");

                for (int i = 1; i < n; i++) {
                    FieldStruct f = fieldList.remove(fieldList.size() - 1);

                    p.remove(f.one);
                    p.remove(f.label);

                    f.one.setVisible(false);
                    f.label.setVisible(false);

                }
                System.out.println("Case 1: Trimmed box list " + fieldList.size());
            } else {
                heightOffset = OUTCOME_SET_OFFSET;

                FieldStruct zero = fieldList.get(0);
                zero.one.setText("");
                zero.two.setText("");

                for (int i = 1; i < n; i++) {
                    FieldStruct f = fieldList.remove(fieldList.size() - 1);//fieldList.remove(fieldList.get(0));

                    p.remove(f.one);
                    p.remove(f.two);

                    f.one.setVisible(false);
                    f.two.setVisible(false);

                }
                System.out.println("Case 1: Trimmed box list " + fieldList.size());
            }
            p.setVisible(false);
            p.setVisible(true);
            int height = (fieldList.get(0).one.getHeight() + 2) * fieldList.size() + heightOffset;
            System.out.println("Height: " + height);
            p.setPreferredSize(new Dimension(p.getWidth(), height));

            p.revalidate();
            p.setVisible(false);
            p.setVisible(true);
        }
    }

    public static void dataListLoader(ArrayList<FieldStruct> fieldList, ArrayList<Outcome> dataList) {
        System.out.println("\n***** Data List Loader ****");
        System.out.println("List Size: " + fieldList.size());

        for (FieldStruct f : fieldList) {
            Outcome o = new Outcome();
            System.out.println("Outcome Name: " + f.one.getText());
            o.outcomeName = f.one.getText();
            o.count = Integer.parseInt(f.three.getText()) - Integer.parseInt(f.two.getText()) + 1;
            System.out.println("Outcome count: " + o.count);
            dataList.add(o);
        }
        System.out.println("Field List  check: " + fieldList.size());
        System.out.println("data list check: " + dataList.size());


    }

    public static void displayPrb(ArrayList<FieldStruct> fieldList, ArrayList<Outcome> list, JPanel p) {
        String txt = "";
        Population pop = new Population(list);
        System.out.println("\n**** Display Probability ****");
        System.out.println("List: " + list.size());
        DecimalFormat twoDForm = new DecimalFormat("#.####");

        for (int i = 0; i < fieldList.size(); i++) {
            Outcome o = pop.outcomes.get(i);
            FieldStruct f = fieldList.get(i);
            if (o.prb != 0) {
                double d = Double.valueOf(twoDForm.format(o.prb));
                System.out.println("Outcome: " + o.outcomeName);
                System.out.println("Prb: " + d + "\n");


                f.prbLabel.setText(d + "");
                f.prbLabel.setVisible(true);


            }
        }

        p.revalidate();
    }
}
