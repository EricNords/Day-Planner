package ui;

import javax.swing.*;

// Image player gui
public class ImagePlayer {

    private JFrame frame;
    private JLabel label;

    // EFFECTS: generates the frame and label
    public ImagePlayer() {

        frame = new JFrame();
        label = new JLabel();
        frame.setLayout(null);
        frame.setBounds(0, 0, 746, 746);

        label = new JLabel();
        label.setIcon(new ImageIcon("./data/backbyimage.jpg"));
        label.setBounds(0, 0, 746, 746);

        frame.getContentPane().add(label);
        frame.setVisible(true);
    }

}
