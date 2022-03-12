package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import ul.idmc.m2.miage.sid.dice_game.Main;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class SerializationHighScore extends HighScore {
    private final static String CONFIGURATION_FILENAME = "./configuration/serialization.properties";
    protected static Properties CONFIGURATION;

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

    @Override
    public void load() {
        try {
            ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(CONFIGURATION.getProperty("dataFilename")));
            scores = (List<Score>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            ObjectOutputStream oos  = new ObjectOutputStream(new FileOutputStream(CONFIGURATION.getProperty("dataFilename")));
            oos.writeObject(scores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
