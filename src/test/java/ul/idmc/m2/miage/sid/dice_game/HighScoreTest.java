package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class HighScoreTest {
    public static void main(String[] args) {
        System.out.println("PostgreSQL - Test");
        HighScoreKit highScoreKit = new PostgreSQLHighScoreKit();
        HighScore highScore = highScoreKit.make();
        highScore.load();
        System.out.println("First load");
        highScore.getScores().stream().forEach(System.out::println);
        if(highScore.getScores().isEmpty())
            highScore.addScore("eve", 5);
        highScore.save();
        highScore.load();
        System.out.println("Second load - After save");
        highScore.getScores().stream().forEach(System.out::println);

        System.out.println("\nMySQL - Test");
        HighScoreKit highScoreKit2 = new MySQLHighScoreKit();
        HighScore highScore2 = highScoreKit2.make();
        highScore2.load();
        System.out.println("First load");
        highScore2.getScores().stream().forEach(System.out::println);
        if(highScore2.getScores().isEmpty())
            highScore.getScores().stream().forEach(score -> highScore2.addScore(score.getPlayerName(), score.getScore()));
        highScore2.save();
        highScore2.load();
        System.out.println("Second load - After save");
        highScore2.getScores().stream().forEach(System.out::println);

        System.out.println("\nH2 - Test");
        HighScoreKit highScoreKit3 = new H2HighScoreKit();
        HighScore highScore3 = highScoreKit3.make();
        highScore3.load();
        System.out.println("First load");
        highScore3.getScores().stream().forEach(System.out::println);
        if(highScore3.getScores().isEmpty())
            highScore.getScores().stream().forEach(score -> highScore3.addScore(score.getPlayerName(), score.getScore()));
        highScore3.save();
        highScore3.load();
        System.out.println("Second load - After save");
        highScore3.getScores().stream().forEach(System.out::println);

        System.out.println("\nSerialization - Test");
        HighScoreKit highScoreKit4 = new SerializationHighScoreKit();
        HighScore highScore4 = highScoreKit4.make();
        Properties configuration = new Properties();
        try {
            configuration.load(HighScoreTest.class.getResourceAsStream("./configuration/serialization.properties"));
        } catch (IOException e) {
            System.err.println("Erreur ! Probl??me au cours du chargement de la configuration de la s??rialisation !");
            e.printStackTrace();
            System.exit(1);
        }
        if(new File(configuration.getProperty("dataFilename")).exists())
            highScore4.load();
        System.out.println("First load");
        highScore4.getScores().stream().forEach(System.out::println);
        if(highScore4.getScores().isEmpty())
            highScore.getScores().stream().forEach(score -> highScore4.addScore(score.getPlayerName(), score.getScore()));
        highScore4.save();
        highScore4.load();
        System.out.println("Second load - After save");
        highScore4.getScores().stream().forEach(System.out::println);
    }
}
