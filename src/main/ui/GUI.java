package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


// Day planner gui
public class GUI implements ActionListener {
    private static final String JSON_STORE = "./data/dayPlanner.json";
    private DayPlanner dp;
    //private ArrayList<Assignment> assignmentList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JButton baddcom;
    private JButton bremcom;
    private JButton bshowcom;
    private JButton baddass;
    private JButton bremass;
    private JButton bshowass;
    private JButton save;
    private JButton load;
    private JButton funnihaha;

    //EFFECTS: runs the GUI
    public GUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runGUI();
    }

    //MODIFIES: this
    //EFFECTS: generates the frame and buttons
    private void runGUI() {

        initialization();

        frame = new JFrame();

        baddcom = new JButton("Add Commitment");
        baddcom.addActionListener(this);
        bremcom = new JButton("Remove Commitment");
        bremcom.addActionListener(this);
        bshowcom = new JButton("Display Commitments");
        bshowcom.addActionListener(this);
        baddass = new JButton("Add Assignment");
        baddass.addActionListener(this);
        bremass = new JButton("Remove Assignment");
        bremass.addActionListener(this);
        bshowass = new JButton("Display Assignments");
        bshowass.addActionListener(this);
        save = new JButton("Save");
        save.addActionListener(this);
        load = new JButton("Load");
        load.addActionListener(this);
        //funnihaha = new JButton("Image Requirement");
        //funnihaha.addActionListener(this);

        styling();

        frameAndPanelThings();
    }

    //MODIFIES: this
    //EFFECTS: sets and adds panel and frame elements
    private void frameAndPanelThings() {
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        panelStyle(panel);
        panelStyle(panel2);
        panelStyle(panel3);

        addButtons();

        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printEventLog();
                System.exit(0);
            }
        });
        frame.setTitle("Scheduling Application");
        frame.pack();
        frame.setVisible(true);
    }

    //MODIFIES: panel, panel2
    //EFFECTS: adds buttons to panel
    private void addButtons() {
        panel.add(baddcom);
        panel.add(bremcom);
        panel.add(bshowcom);
        panel3.add(baddass);
        panel3.add(bremass);
        panel3.add(bshowass);
        panel2.add(save);
        panel2.add(load);
        panel2.add(funnihaha);
    }

    //MODIFIES: this
    //EFFECTS: styles the panels
    private void panelStyle(JPanel panel) {
        panel.setSize(150, 150);
        panel.setBackground(new Color(248, 247, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(1, 3));
    }


    //MODIFIES: this
    //EFFECTS: styles the buttons
    private void styling() {

        Font f1 = new Font("Comic Sans MS", 1, 20);

        styleThis(baddcom);
        styleThis(bremcom);
        styleThis(bshowcom);
        styleThis(baddass);
        styleThis(bremass);
        styleThis(bshowass);
        styleThis(save);
        styleThis(load);

        //funnihaha.setBorderPainted(false);
        funnihaha.setBackground(new Color(182, 204, 254));
        funnihaha.setFont(f1);
        funnihaha.setFocusPainted(false);
    }

    //MODIFIES: this, button
    //EFFECTS: styles the given button
    private void styleThis(JButton button) {

        Font f1 = new Font("Estrangelo Edessa", 1, 20);

        //button.setBorderPainted(false);
        button.setBackground(new Color(182, 204, 254));
        button.setFont(f1);
        button.setFocusPainted(false);

    }

    //MODIFIES: this
    //EFFECTS: initializes the day planner
    private void initialization() {
        ArrayList<Commitment> arc = new ArrayList<>();
        dp = new DayPlanner(arc);
        //assignmentList = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == baddcom) {
            addCommitmentsPopup();
        } else if (src == bremcom) {
            removeCommitmentsPopup();
        } else if (src == bshowcom) {
            displayCommitments();
        } else if (src == save) {
            saveDayPlanner();
        } else if (src == load) {
            loadDayPlanner();
        } else if (src == funnihaha) {
            new ImagePlayer();
        } else if (src == baddass) {
            addAssignmentPopup();
        } else if (src == bremass) {
            removeAssignmentsPopup();
        } else if (src == bshowass) {
            displayAssignments();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds commitment to dp
    private void addCommitmentsPopup() {
        JTextField name = new JTextField();
        JTextField time = new JTextField();
        JTextField location = new JTextField();
        JTextField date = new JTextField();
        Object[] reqs = {
                "Commitment Name: ", name,
                "Commitment Time: ", time,
                "Commitment Location: ", location,
                "Commitment Date (mm/dd/yy): ", date
        };
        ArrayList<Assignment> assignments = new ArrayList<>();
        int x = JOptionPane.showConfirmDialog(null, reqs, "Add Commitment",
                JOptionPane.OK_CANCEL_OPTION);
        if (x == JOptionPane.OK_OPTION) {
            dp.addCommitment(name.getText(), Integer.parseInt(time.getText()), location.getText(), date.getText(),
                    assignments);
            successPopup();
        }
    }

    //MODIFIES: this
    //EFFECTS: creates an input name popup utilizing the given elements
    private String inputNamePopup(String msg, String title) {
        JTextField name = new JTextField();
        Object[] reqs = {
                msg, name
        };

        int x = JOptionPane.showConfirmDialog(null, reqs, title,
                JOptionPane.OK_CANCEL_OPTION);

        if (x == JOptionPane.OK_OPTION) {
            return name.getText();
        } else {
            return null;
        }

    }

    //EFFECTS: presents success popup
    private void successPopup() {
        JOptionPane.showMessageDialog(frame, "Success",
                "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }

    //EFFECTS: presents error popup
    private void errorPopup() {
        JOptionPane.showMessageDialog(frame, "ERROR",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: removes commitment with given name from dp
    private void removeCommitmentsPopup() {

        String msg = "Commitment Name to be Removed: ";
        String title = "Remove Commitment";

        String result = inputNamePopup(msg, title);

        if (result != null) {
            dp.removeCommitment(result);
            successPopup();
        }
    }

    // EFFECTS: displays commitments for given date
    private void displayCommitments() {
        JTextField date = new JTextField();
        StringBuilder names = new StringBuilder();
        ArrayList<Commitment> commitments = dp.getCommitments();
        Object[] reqs = {
                "Day to be displayed (mm/dd/yy) or (all) for all commitments:", date,
        };

        int x = JOptionPane.showConfirmDialog(null, reqs, "Display Commitments on date",
                JOptionPane.OK_CANCEL_OPTION);

        if (x == JOptionPane.OK_OPTION) {
            if (date.getText().equalsIgnoreCase("all")) {
                for (int i = 0; i < commitments.size(); i++) {
                    addNames(i, names, commitments);
                }
            } else {
                for (int i = 0; i < dp.getCommitments().size(); i++) {
                    if (Objects.equals(date.getText(), commitments.get(i).getCommitmentDate())) {
                        addNames(i, names, commitments);
                    }
                }
            }
            new Display(names, date.getText(), "Commitments on date: ");
        }
    }

    //MODIFIES: names
    //EFFECTS: appends string to names
    private void addNames(int i, StringBuilder names, ArrayList<Commitment> commitments) {
        names.append("Commitment: " + commitments.get(i).getCommitmentName()
                + " --- Time: " + commitments.get(i).getCommitmentTime()
                + " --- Location: " + commitments.get(i).getCommitmentLocation() + "<br>");
    }


    // EFFECTS: saves the day planner to file
    private void saveDayPlanner() {
        try {
            jsonWriter.open();
            jsonWriter.write(dp);
            jsonWriter.close();
            successPopup();
        } catch (FileNotFoundException e) {
            errorPopup();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads day planner from file
    private void loadDayPlanner() {
        try {
            dp = jsonReader.read();
            successPopup();
        } catch (IOException e) {
            errorPopup();
        }
    }

    //MODIFIES: this
    //EFFECTS: searches for given commitment and if it exists, passes its index to helper
    private void addAssignmentPopup() {

        String msg = "Commitment Name to have assignment added: ";
        String title = "Add Assignment";

        String result = inputNamePopup(msg, title);

        if (result != null) {
            for (int i = 0; i < dp.getCommitments2().size(); i++) {
                if (Objects.equals(result, dp.getCommitments2().get(i).getCommitmentName())) {
                    addAssignmentHelper(i);
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds assignment to commitment with given index
    private void addAssignmentHelper(int i) {

        JTextField name = new JTextField();
        JTextField time = new JTextField();
        JTextField date = new JTextField();

        Object[] reqs = {
                "Assignment Name: ", name,
                "Assignment Date (mm/dd/yy): ", date,
                "Assignment Time : ", time
        };

        int x = JOptionPane.showConfirmDialog(null, reqs, "Add Assignment",
                JOptionPane.OK_CANCEL_OPTION);

        if (x == JOptionPane.OK_OPTION) {
            dp.getCommitments2().get(i).addAssignment(name.getText(), date.getText(), Integer.parseInt(time.getText()));
            successPopup();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes commitment with given name from dp
    private void removeAssignmentsPopup() {

        ArrayList<Commitment> commitments = dp.getCommitments2();

        String msg = "Name of Commitment Containing Assignment to be Removed: ";
        String title = "Remove Assignment";

        String msg2 = "Name of Assignment to be Removed: ";
        String title2 = "Remove Assignment";

        String result = inputNamePopup(msg, title);

        if (result != null) {
            for (int i = 0; i < commitments.size(); i++) {
                if (Objects.equals(result, commitments.get(i).getCommitmentName())) {
                    String result2 = inputNamePopup(msg2, title2);
                    if (result2 != null) {
                        commitments.get(i).removeAssignment(result2);
                        successPopup();
                    }
                }
            }
        }
    }

    //EFFECTS: displays assignments for given commitment
    private void displayAssignments() {
        StringBuilder names = new StringBuilder();
        String msg = "Name of Commitment to Display Attached Assignments: ";
        String title = "Display Assignment";
        ArrayList<Commitment> commitments = dp.getCommitments2();

        String result = inputNamePopup(msg, title);

        if (result != null) {
            for (int i = 0; i < commitments.size(); i++) {
                if (Objects.equals(result, commitments.get(i).getCommitmentName())) {
                    ArrayList<Assignment> assignments = commitments.get(i).getCommitmentAssignments();
                    for (int x = 0; x < assignments.size(); x++) {
                        names.append("Assignment: "
                                + assignments.get(x).getAssignmentName()
                                + " --- Date: "
                                + assignments.get(x).getAssignmentDate()
                                + " --- Time: "
                                + assignments.get(x).getAssignmentTime()
                                + "<br>");
                    }
                }
            }
        }
        new Display(names, result, "Assignments for Commitment: ");
    }

    // EFFECTS: prints commitments to screen
    private void printEventLog() {
        EventLog log = EventLog.getInstance();
        System.out.println("Event Log: ");
        for (Event next: log) {
            System.out.println(next.toString());
        }
    }
}



