package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.PostgreSQLHighScoreFactory;

public class Main {
    public static void main(String[] args) {
        HighScoreFactory highScoreFactory = new PostgreSQLHighScoreFactory();
        HighScore highScore = highScoreFactory.make();
        highScore.load();
        highScore.getScores().stream().forEach(System.out::println);
    }
}
