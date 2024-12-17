package org.example.view;

import javax.swing.*;
import java.awt.*;

public class WortTrainerView {
    private JFrame frame;
    private JTextField inputField;
    private JLabel correctLabel, attemptsLabel, imageLabel;
    private JButton resetButton, addButton;

    public WortTrainerView() {
        frame = new JFrame("Wort-Trainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel questionLabel = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Ueberpruefen)?", SwingConstants.CENTER);
        inputField = new JTextField();
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(inputField, BorderLayout.SOUTH);

        // Center Image
        imageLabel = new JLabel("", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 400));

        // Left Bottom Panel (Statistiken)
        JPanel statsPanel = new JPanel(new GridLayout(2, 1));
        correctLabel = new JLabel("Richtige Woerter: 0", SwingConstants.LEFT);
        attemptsLabel = new JLabel("Anzahl Woerter: 0", SwingConstants.LEFT);
        statsPanel.add(correctLabel);
        statsPanel.add(attemptsLabel);

        // Right Bottom Panel (Buttons)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        resetButton = new JButton("Zuruecksetzen");
        addButton = new JButton("Wort hinzufuegen");
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);

        // Bottom Panel (combining left and right bottom)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statsPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        // Frame Assembly
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JLabel getCorrectLabel() {
        return correctLabel;
    }

    public JLabel getAttemptsLabel() {
        return attemptsLabel;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
