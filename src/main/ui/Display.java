package ui;

import javax.swing.*;

// display commitments and assignments gui
public class Display {

    //EFFECTS: generates the frame and commitments pane
    Display(StringBuilder names, String abst, String wrds) {
        JFrame frame = new JFrame();
        frame.setTitle(wrds + abst);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextPane commitmentsPane = new JTextPane();
        commitmentsPane.setContentType("text/html");
        commitmentsPane.setEditable(false);
        commitmentsPane.setText(names.toString());

        JScrollPane pane = new JScrollPane(commitmentsPane);
        frame.add(pane);
        frame.setSize(700, 200);
        frame.setVisible(true);
    }
}
