package org.example;

import org.example.controller.WortTrainerController;
import org.example.model.*;
import org.example.persistenz.*;
import org.example.view.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        //erstellt eine neue Instanz des WortTrainers mit einer leeren Wortliste
        WortTrainer trainer = new WortTrainer(new ArrayList<>());

        //fügt vordefinierte Wörter und zugehörige Bild-URLs hinzu
        trainer.addWord("Hund", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPbcC_F9soRYhsRQyrjT0y3hP04PLKYWR6fA&s");
        trainer.addWord("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg");
        trainer.addWord("Waschbär", "https://static.wixstatic.com/media/10d451_d9b595396cb541a2a36bdf9a3414ecef~mv2.png");
        trainer.addWord("Igel", "https://image.geo.de/30154986/t/gy/v3/w1440/r1/-/igel-geolino-shutterstock-jpg--88472-.jpg");
        trainer.addWord("TGM", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG/280px-Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG");

        //erstellt die Benutzeroberfläche für den WortTrainer
        WortTrainerView view = new WortTrainerView();

        //setzt die Persistenzstrategie auf JSON-basierte Speicherung
        PersistenzStrategie persistenzStrategie = new JsonPersistenzStrategie();

        //initialisiert den Controller mit Trainer, View und Persistenzstrategie
        WortTrainerController controller = new WortTrainerController(trainer, view, persistenzStrategie);

        //startet die Anwendung
        controller.startTraining();
    }
}
