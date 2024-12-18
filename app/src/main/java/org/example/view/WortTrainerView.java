package org.example.view;

import javax.swing.*;
import java.awt.*;

public class WortTrainerView {
    private final JFrame frame;
    private final JTextField inputField;
    private final JLabel correctLabel, attemptsLabel, imageLabel;
    private final JButton resetButton, addButton, saveButton, loadButton;

    public WortTrainerView() {
        frame = new JFrame("Wort-Trainer"); //erstellt Hauptfenster
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet die Anwendung beim Schließen des Fensters
        frame.setLayout(new BorderLayout()); //legt das Layout für das Hauptfenster fest

        //Top Panel: Enthält Eingabeaufforderung und Eingabefeld
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel questionLabel = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überpruefen)?", SwingConstants.CENTER);
        inputField = new JTextField(); //erstellt das Eingabefeld
        topPanel.add(questionLabel, BorderLayout.NORTH); //fügt die Frage oben hinzu
        topPanel.add(inputField, BorderLayout.SOUTH); //fügt das Eingabefeld unten hinzu

        //Center Image: Zeigt das Bild des aktuellen Wortes an
        imageLabel = new JLabel("", SwingConstants.CENTER); //erstellt Label für das Bild.
        imageLabel.setPreferredSize(new Dimension(400, 300)); //skaliert

        //Bottom Panel: Enthält Informationen zu richtigen Wörtern und Versuchen
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        correctLabel = new JLabel("Richtige Woerter: 0");
        attemptsLabel = new JLabel("Anzahl Woerter: 0");
        resetButton = new JButton("Zuruecksetzen");
        addButton = new JButton("Wort hinzufuegen");
        saveButton = new JButton("Speichern");
        loadButton = new JButton("Laden");
        bottomPanel.add(correctLabel);
        bottomPanel.add(attemptsLabel);

        //Button Panel: Enthält die Steuerungsbuttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(resetButton);
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

// Frame Assembly: Fügt alle Panels zum Hauptfenster hinzu
        frame.add(topPanel, BorderLayout.NORTH); // Fügt das Top-Panel oben hinzu
        frame.add(imageLabel, BorderLayout.CENTER); // Fügt das Bild in der Mitte hinzu
        frame.add(buttonPanel, BorderLayout.SOUTH); // Fügt das Button-Panel unten hinzu
        frame.add(bottomPanel, BorderLayout.WEST); // Fügt das Bottom-Panel links hinzu
        frame.pack(); //Passt die Größe des Fensters an den Inhalt an
        frame.setSize(800, 600); //Setzt die Standardgröße des Fensters
        frame.setLocationRelativeTo(null); //Zentriert das Fenster auf dem Bildschirm
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

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
