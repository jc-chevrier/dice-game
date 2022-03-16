package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SerializationHighScore extends HighScore {
    private final static @NotNull String CONFIGURATION_FILENAME = "configuration/serialization.properties";
    private static @NotNull Properties CONFIGURATION;
    private static @NotNull SerializationHighScore highScoreSingleton;

    static {
        try {
            CONFIGURATION = new Properties();
            CONFIGURATION.load(Main.class.getResourceAsStream(CONFIGURATION_FILENAME));
        } catch (IOException e) {
            System.err.println("Erreur ! Problème au cours du chargement de la configuration de la sérialisation !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private SerializationHighScore() {
        super();
    }

    public static @NotNull HighScore getInstance() {
        if (highScoreSingleton == null) {
            highScoreSingleton = new SerializationHighScore();
        }
        return highScoreSingleton;
    }

    @Override
    public void load() {
        try {
            ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(CONFIGURATION.getProperty("dataFilename")));
            scores = (List<Score>) ois.readObject();
            Collections.sort(scores, (score1, score2) -> score1.getDate().compareTo(score2.getDate()));
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur ! Problème au cours de la sérialisation !");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream oos  = new ObjectOutputStream(new FileOutputStream(CONFIGURATION.getProperty("dataFilename")));
            oos.writeObject(scores);
            oos.close();
        } catch (IOException e) {
            System.err.println("Erreur ! Problème au cours de la désérialisation !");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
