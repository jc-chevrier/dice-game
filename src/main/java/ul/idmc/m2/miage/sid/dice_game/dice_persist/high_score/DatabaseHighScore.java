package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import java.util.Properties;

abstract class DatabaseHighScore<CONNECTION> extends HighScore {
    protected static String CONFIGURATION_FILENAME;
    protected static Properties CONFIGURATION;

    protected CONNECTION connection;

    protected abstract void loadConfiguration();

    protected abstract void loadConnection();
}
