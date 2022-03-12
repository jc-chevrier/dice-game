package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.PostgreSQLHighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.SerializationHighScoreFactory;

public class Main {
    public static void main(String[] args) {
        HighScoreFactory highScoreFactory = new PostgreSQLHighScoreFactory();
        HighScore highScore = highScoreFactory.make();
        highScore.load();
        highScore.getScores().stream().forEach(System.out::println);

        HighScoreFactory highScoreFactory3 = new SerializationHighScoreFactory();
        HighScore highScore3 = highScoreFactory3.make();
        highScore3.load();
        highScore3.getScores().stream().forEach(System.out::println);
    }
}
