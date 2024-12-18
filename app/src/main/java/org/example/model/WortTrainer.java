package org.example.model;

import javax.swing.*;
import java.text.Normalizer;
import java.util.*;

public class WortTrainer {
    private List<WortEintrag> wortListe;
    private WortEintrag currentEntry;
    private int totalAttempts;
    private int correctAttempts;

    public WortTrainer(List<WortEintrag> wortListe) {
        this.wortListe = new ArrayList<>(wortListe);
        this.totalAttempts = 0;
        this.correctAttempts = 0;
    }

    public void selectRandomWord() {
        if (!wortListe.isEmpty()) {
            currentEntry = wortListe.get(new Random().nextInt(wortListe.size()));
        }
    }

    public WortEintrag getCurrentEntry() {
        return currentEntry;
    }

    /**
     * Überprüft die Eingabe des Benutzers gegen das aktuelle Wort.
     * @param input Die Benutzereingabe.
     * @return True, wenn die Eingabe korrekt ist, andernfalls false.
     */
    public boolean checkWord(String input) {
        totalAttempts++;

        if (currentEntry != null) {
            //normalisiert beide Strings, um Sonderzeichen zu handhaben
            String normalizedInput = normalizeString(input);
            String normalizedWord = normalizeString(currentEntry.getWort());

            if (normalizedWord.equalsIgnoreCase(normalizedInput)) {
                correctAttempts++;
                return true;
            }
        }
        return false;
    }

    //Hilfsmethode zur Normalisierung von Strings
    private String normalizeString(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFKC).replaceAll("\\p{M}", "");
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public void reset() {
        totalAttempts = 0;
        correctAttempts = 0;
        selectRandomWord();
    }

    /**
     * Fügt ein neues Wort mit zugehöriger URL zur Liste hinzu.
     * @param wort Das neue Wort.
     * @param url Die URL des zugehörigen Bildes.
     */
    public void addWord(String wort, String url) {
        while (url == null || url.isEmpty() || !url.startsWith("http")) {
            JOptionPane.showMessageDialog(null, "Es muss eine gültige URL angegeben werden!", "Ungültige URL", JOptionPane.ERROR_MESSAGE);
            url = JOptionPane.showInputDialog("URL des Bildes eingeben:");
        }
        wortListe.add(new WortEintrag(wort, url));
    }

    public List<WortEintrag> getWortListe() {
        return wortListe;
    }

    public void setWortListe(List<WortEintrag> wortListe) {
        this.wortListe = wortListe;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }
}
