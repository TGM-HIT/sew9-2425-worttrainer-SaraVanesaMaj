package org.example;

import org.example.model.*;
import org.example.persistenz.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WortTrainerTest {
    private WortTrainer trainer;

    @BeforeEach
    void setUp() {
        List<WortEintrag> wortListe = new ArrayList<>();
        wortListe.add(new WortEintrag("Hund", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPbcC_F9soRYhsRQyrjT0y3hP04PLKYWR6fA&s"));
        wortListe.add(new WortEintrag("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg"));
        wortListe.add(new WortEintrag("Waschbär", "https://static.wixstatic.com/media/10d451_d9b595396cb541a2a36bdf9a3414ecef~mv2.png"));
        wortListe.add(new WortEintrag("Igel", "https://image.geo.de/30154986/t/gy/v3/w1440/r1/-/igel-geolino-shutterstock-jpg--88472-.jpg"));
        trainer = new WortTrainer(wortListe);
    }

    @Test
    void testSelectRandomWord() {
        trainer.selectRandomWord();
        assertNotNull(trainer.getCurrentEntry(), "Es sollte ein Wort ausgewählt sein."); //wurde ein Wort gewählt? (! null)
    }

    @Test
    void testCheckWordCorrect() {
        trainer.selectRandomWord();
        WortEintrag currentEntry = trainer.getCurrentEntry();
        assertTrue(trainer.checkWord(currentEntry.getWort()), "Das eingegebene Wort sollte korrekt sein.");
        assertEquals(1, trainer.getCorrectAttempts(), "Die Anzahl der richtigen Versuche sollte 1 sein.");
    }

    @Test
    void testCheckWordIncorrect() {
        trainer.selectRandomWord();
        assertFalse(trainer.checkWord("FalschesWort"), "Das eingegebene Wort sollte falsch sein.");
        assertEquals(1, trainer.getTotalAttempts(), "Die Anzahl der Gesamtversuche sollte 1 sein.");
    }

    @Test
    void testAddWord() {
        int initialSize = trainer.getWortListe().size();
        trainer.addWord("Vogel", "https://example.com/vogel.jpg");
        assertEquals(initialSize + 1, trainer.getWortListe().size(), "Die Wortliste sollte um eins wachsen.");
    }

    @Test
    void testPersistenceSaveAndLoad() {
        PersistenzStrategie persistenzStrategie = new JsonPersistenzStrategie();
        persistenzStrategie.saveTrainer(trainer);
        WortTrainer loadedTrainer = persistenzStrategie.loadTrainer();
        assertNotNull(loadedTrainer, "Der geladene Trainer sollte nicht null sein.");
        assertEquals(trainer.getWortListe().size(), loadedTrainer.getWortListe().size(), "Die geladene Wortliste sollte die gleiche Größe haben.");
        assertEquals(trainer.getTotalAttempts(), loadedTrainer.getTotalAttempts(), "Die Anzahl der Versuche sollte übereinstimmen.");
    }
}
