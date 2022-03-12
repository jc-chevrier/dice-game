package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.MySQLHighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.PostgreSQLHighScoreFactory;

public class Main {
    public static void main(String[] args) {
        HighScoreFactory highScoreFactory = new PostgreSQLHighScoreFactory();
        HighScore highScore = highScoreFactory.make();
        highScore.load();
        highScore.getScores().stream().forEach(System.out::println);

        HighScoreFactory highScoreFactory2 = new MySQLHighScoreFactory();
        HighScore highScore2 = highScoreFactory2.make();
        highScore2.load();
        highScore2.getScores().stream().forEach(System.out::println);
        highScore.getScores().stream().forEach(score -> highScore2.addScore(score.getPlayerName(), score.getScoreNumber()));
         highScore2.save();
        highScore2.load();
        highScore2.getScores().stream().forEach(System.out::println);
    }
}
