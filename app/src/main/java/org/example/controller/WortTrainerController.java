package org.example.controller;

import org.example.model.*;
import org.example.persistenz.PersistenzStrategie;
import org.example.view.*;

import javax.swing.*;
import java.awt.*;

public class WortTrainerController {
    private WortTrainer trainer;
    private WortTrainerView view;
    private PersistenzStrategie persistenzStrategie;

    public WortTrainerController(WortTrainer trainer, WortTrainerView view, PersistenzStrategie persistenzStrategie) {
        this.trainer = trainer;
        this.view = view;
        this.persistenzStrategie = persistenzStrategie;
    }

    public void startTraining() {
        view.getFrame().setVisible(true); //Hauptfenster sichtbar
        trainer.selectRandomWord(); //wählt zufälliges Wort aus Liste
        updateView();

        //Aktion für den Zurücksetzen-Button
        view.getResetButton().addActionListener(e -> {
            trainer.reset(); //setzt Versuche zurück.
            trainer.selectRandomWord();
            updateView();
        });

        // Aktion für Wort hinzufügen-Button
        view.getAddButton().addActionListener(e -> {
            String wort = JOptionPane.showInputDialog("Neues Wort eingeben:"); //fragt neues Wort ab
            String url = JOptionPane.showInputDialog("URL des Bildes eingeben:");
            if (wort != null && url != null) {
                trainer.addWord(wort, url);
                JOptionPane.showMessageDialog(view.getFrame(), "Wort hinzugefügt!");
                updateView();
            }
        });

        //Aktion für den Speichern-Button
        view.getSaveButton().addActionListener(e -> {
            persistenzStrategie.saveTrainer(trainer); //speichert aktuellen Zustand des Trainers
            JOptionPane.showMessageDialog(view.getFrame(), "Fortschritt wurde erfolgreich gespeichert!");
        });

        //Aktion für den Laden-Button
        view.getLoadButton().addActionListener(e -> {
            WortTrainer loadedTrainer = persistenzStrategie.loadTrainer(); //lädt gespeicherten Trainer
            if (loadedTrainer != null) {
                this.trainer = loadedTrainer; //setzt den geladenen Trainer.
                JOptionPane.showMessageDialog(view.getFrame(), "Fortschritt wurde erfolgreich geladen!");
                updateView();
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Keine gespeicherten Daten gefunden.");
            }
        });

        //Aktion für das Texteingabefeld
        view.getInputField().addActionListener(e -> {
            String input = view.getInputField().getText(); //holt Benutzereingabetext
            if (trainer.checkWord(input)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Richtig!");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Falsch! Versuche es nochmal.");
            }
            trainer.selectRandomWord();
            updateView();
        });
    }

    /**
     * Aktualisiert die Benutzeroberfläche basierend auf dem aktuellen Zustand des WortTrainers.
     */
    private void updateView() {
        view.getCorrectLabel().setText("Richtige Wörter: " + trainer.getCorrectAttempts());
        view.getAttemptsLabel().setText("Anzahl Wörter: " + trainer.getTotalAttempts()); //Aktualisiert Anzeige der Gesamtversuche
        WortEintrag current = trainer.getCurrentEntry(); //holt aktuelles Wort

        if (current != null) {
            try {
                java.net.URL imageUrl = new java.net.URL(current.getUrl()); //holt die Bild-URL
                ImageIcon originalIcon = new ImageIcon(imageUrl); //erstellt ein ImageIcon aus der URL
                Image scaledImage = originalIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                view.getImageLabel().setIcon(new ImageIcon(scaledImage)); //setzt das Bild in der View
                view.getImageLabel().setText(""); //entfernt Text aus Label
            } catch (Exception e) {
                view.getImageLabel().setText("Bild konnte nicht geladen werden!");
                view.getImageLabel().setIcon(null); //entfernt das Bild
            }
        }
        view.getInputField().setText(""); //leert Eingabefeld
    }
}
