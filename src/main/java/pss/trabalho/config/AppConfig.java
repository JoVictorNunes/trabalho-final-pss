package pss.trabalho.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    private static AppConfig instance;
    private final Dotenv env;

    private AppConfig() {
        this.env = Dotenv.load();
    }

    public String get(String key) {
        return env.get(key);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
}
