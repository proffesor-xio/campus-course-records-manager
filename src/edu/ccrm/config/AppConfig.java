package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConfig {

    private static AppConfig instance;

    private final Path dataPath;

    // Private constructor for Singleton
    private AppConfig() {
        this.dataPath = Paths.get("data"); // Default data folder
    }

    // Thread-safe Singleton instance getter
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public Path getDataPath() {
        return dataPath;
    }

    public void setDataPath(Path dataPath) {
        throw new UnsupportedOperationException("Data path is immutable in this version.");
    }
}
