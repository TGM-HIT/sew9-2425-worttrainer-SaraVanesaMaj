package org.example.persistenz;

import org.example.model.WortTrainer;

public interface PersistenzStrategie {
    void saveTrainer(WortTrainer trainer);
    WortTrainer loadTrainer();
}
