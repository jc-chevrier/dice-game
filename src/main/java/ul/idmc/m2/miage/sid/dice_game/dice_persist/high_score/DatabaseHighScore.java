package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import ul.idmc.m2.miage.sid.dice_game.Main;

import java.io.IOException;
import java.util.Properties;

abstract class DatabaseHighScore<CONNECTION> extends HighScore {
    protected String configurationFilename;
    protected Properties configuration;
    protected CONNECTION connection;

    protected void loadConfiguration() {
        if(configuration == null) {
            try {
                configuration = new Properties();
                configuration.load(Main.class.getResourceAsStream(configurationFilename));
            } catch (IOException e) {
                System.err.println("Erreur ! Problème au cours du chargement de la configuration de la base de données PostgreSQL !");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    protected abstract void loadConnection();
}
