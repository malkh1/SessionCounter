/*
 * Author: Mohammad Alkhaledi
 */
package SessionsCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Date;

public class Counter {

    public String getGreeting() {
        return "for testing purposes.";
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sessions Counter");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Sessions count:");
        JButton increaseCounter = new JButton("+");
        JButton decreaseCounter = new JButton("-");
        JButton clearCounter = new JButton("Clear");
        JRadioButton saveToFileRadioButton = new JRadioButton("Save to file");
        final int[] counter = {0};
        final boolean[] saveToFileBool = {true};
        JTextField counterDisplay = new JTextField(counter[0]);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(counterDisplay, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(increaseCounter);
        bottomPanel.add(decreaseCounter);
        bottomPanel.add(clearCounter);
        bottomPanel.add(saveToFileRadioButton);
        increaseCounter.addActionListener(e -> {
            ++counter[0];
            counterDisplay.setText(String.valueOf(counter[0]));
        });
        decreaseCounter.addActionListener(e -> {
            counter[0] = (counter[0] > 0) ? (--counter[0]) : (0);
            counterDisplay.setText(String.valueOf(counter[0]));
        });
        clearCounter.addActionListener(e -> {
            counter[0] = 0;
            counterDisplay.setText(String.valueOf(counter[0]));
        });
        saveToFileRadioButton.addActionListener(e -> {
            saveToFileBool[0] = !saveToFileBool[0];
        });
        saveToFileRadioButton.setSelected(true);
        counterDisplay.setText(String.valueOf(counter[0]));
        frame.add(mainPanel);
        frame.setSize(new Dimension(300, 140));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (saveToFileBool[0]) {
                    try {
                        try (FileWriter fileWriter = new FileWriter("SessionsLog.txt", true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            bufferedWriter.write(new Date() + " -> Sessions completed today: " + counter[0]);
                            bufferedWriter.newLine();
                        }
                    } catch (IOException e) {
                    }
                    
                }
                System.exit(0);
            }
        });

        frame.setVisible(true);

    }
}
