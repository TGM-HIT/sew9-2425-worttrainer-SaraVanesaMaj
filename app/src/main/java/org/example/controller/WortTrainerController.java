package org.example.controller;

import org.example.model.*;
import org.example.view.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;

public class WortTrainerController {
    private WortTrainer trainer;
    private WortTrainerView view;

    public WortTrainerController(WortTrainer trainer, WortTrainerView view) {
        this.trainer = trainer;
        this.view = view;
    }

    public void startTraining() {
        view.getFrame().setVisible(true);
        trainer.selectRandomWord();
        updateView();

        view.getResetButton().addActionListener(e -> {
            trainer.reset();
            trainer.selectRandomWord();
            updateView();
        });

        view.getAddButton().addActionListener(e -> {
            String wort = JOptionPane.showInputDialog("Neues Wort eingeben:");
            String url = JOptionPane.showInputDialog("URL des Bildes eingeben:");
            if (wort != null && url != null) {
                trainer.addWord(wort, url);
                JOptionPane.showMessageDialog(view.getFrame(), "Wort hinzugefügt!");
                updateView();
            }
        });

        view.getInputField().addActionListener(e -> {
            String input = view.getInputField().getText();
            if (trainer.checkWord(input)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Richtig!");
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), "Falsch! Versuche es nochmal.");
            }
            trainer.selectRandomWord();
            updateView();
        });
    }

    private void updateView() {
        view.getCorrectLabel().setText("Richtige Woerter: " + trainer.getCorrectAttempts());
        view.getAttemptsLabel().setText("Anzahl Woerter: " + trainer.getTotalAttempts());
        WortEintrag current = trainer.getCurrentEntry();

        if (current != null) {
            try {
                // Lade und skaliere das Bild
                java.net.URL imageUrl = new java.net.URL(current.getUrl());
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image scaledImage = originalIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                view.getImageLabel().setIcon(new ImageIcon(scaledImage));
                view.getImageLabel().setText(""); // Entferne Text
            } catch (Exception e) {
                view.getImageLabel().setText("Bild konnte nicht geladen werden!");
                view.getImageLabel().setIcon(null);
            }
        } else {
            view.getImageLabel().setIcon(null);
            view.getImageLabel().setText("Keine weiteren Wörter vorhanden!");
        }
        view.getInputField().setText("");
    }


}
