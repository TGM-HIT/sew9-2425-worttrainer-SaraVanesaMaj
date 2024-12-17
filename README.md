# 📝 Worttrainer-Projekt

Dieses Projekt ist ein **Rechtschreibtrainer für Volksschulkinder**, der es ihnen ermöglicht, spielerisch Wörter zu lernen. Zu jedem angezeigten Bild soll das passende Wort eingegeben werden. Die Eingaben werden überprüft und Statistiken erfasst.

---

## 🚀 **Projektbeschreibung**

- Der Worttrainer zeigt ein **Bild** an (z. B. eines Hundes).
- Die Kinder tippen das entsprechende **Wort** ein.
- Die Eingabe wird mit der richtigen Schreibweise verglichen.
- Eine **Rückmeldung** wird gegeben:
   - ✅ Richtig → Das nächste Bild erscheint.  
   - ❌ Falsch → Das Kind bekommt eine Rückmeldung und kann es erneut versuchen.  
- Das Programm erfasst Statistiken:
   - Anzahl der insgesamt geratenen Wörter
   - Anzahl der richtigen und falschen Eingaben

---

## 🛠️ **Technische Details**

- **Programmiersprache**: Java 17
- **Build-Tool**: Gradle
- **GUI**: JOptionPane (für eine einfache Benutzeroberfläche)
- **Persistenz**: Speicherung der Daten in einem frei wählbaren Format (JSON, XML, oder SQLite)

---

## 📂 **Projektstruktur**

Die Ordnerstruktur des Projekts sieht folgendermaßen aus:

```plaintext
worttrainer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── at.tgm.worttrainer/
│   │   │       ├── Main.java        # Einstiegspunkt des Programms
│   │   │       ├── Word.java        # Klasse für die Wort-Bild-Paare
│   │   │       └── Trainer.java     # Hauptlogik des Trainers
│   ├── test/                       # Testfälle für das Projekt
├── build.gradle.kts                 # Gradle Build-File
├── settings.gradle.kts              # Gradle Projekt-Einstellungen
└── README.md                        # Projektbeschreibung
