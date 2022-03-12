package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import redis.clients.jedis.Connection;
import ul.idmc.m2.miage.sid.dice_game.Main;
import java.io.IOException;
import java.util.Properties;

public class RedisHighScore extends DatabaseHighScore<Connection> {
    private final static String CONFIGURATION_FILENAME = "./configuration/redis.properties";
    private static Properties CONFIGURATION;

    static {
        try {
            CONFIGURATION = new Properties();
            CONFIGURATION.load(Main.class.getResourceAsStream(CONFIGURATION_FILENAME));
        } catch (IOException e) {
            System.err.println("Erreur ! Connexion impossible à la base de données Redis !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    protected void loadConnection() {

    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
