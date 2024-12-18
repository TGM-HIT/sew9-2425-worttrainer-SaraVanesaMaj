package org.example.persistenz;

import org.example.model.WortTrainer;
import org.example.model.WortEintrag;
import org.json.*;
import java.io.*;
import java.util.*;

public class JsonPersistenzStrategie implements PersistenzStrategie {
    private static final String FILE_NAME = "worttrainer.json";

    /**
     * Speichert die Daten des WortTrainers in eine JSON-Datei.
     * @param trainer Der WortTrainer, dessen Zustand gespeichert werden soll.
     */
    @Override
    public void saveTrainer(WortTrainer trainer) {
        try (FileWriter file = new FileWriter(FILE_NAME)) { // Erstellt FileWriter für die Ausgabe der Datei.
            JSONObject json = new JSONObject(); // JSON-Objekt für die Speicherung der gesamten Datenstruktur.
            JSONArray wordArray = new JSONArray(); // JSON-Array zur Speicherung der Wortliste.

            //Wort-Liste serialisieren (jedes Wort wird in ein JSON-Objekt umgewandelt)
            for (WortEintrag entry : trainer.getWortListe()) {
                JSONObject wordObj = new JSONObject(); //JSON-Objekt für ein einzelnes Wort
                wordObj.put("wort", entry.getWort()); //Speichert das Wort
                wordObj.put("url", entry.getUrl());
                wordArray.put(wordObj); //Fügt das JSON-Objekt dem Array hinzu
            }

            json.put("words", wordArray); //gesamte Wortliste ins Json Hauptobjekt
            json.put("totalAttempts", trainer.getTotalAttempts());
            json.put("correctAttempts", trainer.getCorrectAttempts());

            file.write(json.toString(4));  //Schreibt das formatierte JSON in die Datei (4 Leerzeichen für Formatierung)
            System.out.println("Speichern erfolgreich!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt die gespeicherten Daten des WortTrainers aus einer JSON-Datei.
     * @return Ein geladenes WortTrainer-Objekt oder null, falls keine Daten vorhanden sind.
     */
    @Override
    public WortTrainer loadTrainer() {
        try (FileReader file = new FileReader(FILE_NAME)) {
            JSONObject json = new JSONObject(new JSONTokener(file)); //Parst die JSON-Daten aus der Datei
            JSONArray wordArray = json.getJSONArray("words"); //Holt die Wortliste als JSON-Array

            List<WortEintrag> wortListe = new ArrayList<>(); //Liste zur Speicherung der geladenen WortEinträge

            //Iteriert durch das JSON-Array und wandelt die Daten in WortEintrag-Objekte um.
            for (int i = 0; i < wordArray.length(); i++) {
                JSONObject wordObj = wordArray.getJSONObject(i);
                wortListe.add(new WortEintrag(wordObj.getString("wort"), wordObj.getString("url"))); //Erstellt WortEintrag
            }

            WortTrainer trainer = new WortTrainer(wortListe); //Erstellt einen WortTrainer mit der geladenen Wortliste
            trainer.setTotalAttempts(json.getInt("totalAttempts")); //Setzt die Anzahl der Versuche.
            trainer.setCorrectAttempts(json.getInt("correctAttempts"));
            System.out.println("Laden erfolgreich!");
            return trainer;

        } catch (IOException | JSONException e) {  //wenn Datei nicht existiert
            System.out.println("Keine gespeicherten Daten vorhanden oder Fehler beim Laden.");
            return null;
        }
    }
}