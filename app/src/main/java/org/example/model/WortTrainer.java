package org.example.model;

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
        } else {
            currentEntry = null;
        }
    }

    public WortEintrag getCurrentEntry() {
        return currentEntry;
    }

    public boolean checkWord(String input) {
        totalAttempts++;
        if (currentEntry != null && currentEntry.getWort().equalsIgnoreCase(input)) {
            correctAttempts++;
            wortListe.remove(currentEntry);
            currentEntry = null;
            return true;
        }
        return false;
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
    }

    public void addWord(String wort, String url) {
        wortListe.add(new WortEintrag(wort, url));
    }
}
