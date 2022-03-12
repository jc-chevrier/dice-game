package ul.idmc.m2.miage.sid.dice_game;

import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory.H2HighScoreFactory;
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
        highScore.getScores().stream().forEach(score -> highScore3.addScore(score.getPlayerName(), score.getScoreNumber()));
        highScore3.save();
        highScore3.load();
        highScore3.getScores().stream().forEach(System.out::println);

        HighScoreFactory highScoreFactory4 = new H2HighScoreFactory();
        HighScore highScore4 = highScoreFactory4.make();
        highScore4.load();
        highScore4.getScores().stream().forEach(System.out::println);
        highScore.getScores().stream().forEach(score -> highScore4.addScore(score.getPlayerName(), score.getScoreNumber()));
        highScore4.save();
        highScore4.load();
        highScore4.getScores().stream().forEach(System.out::println);
    }
}
