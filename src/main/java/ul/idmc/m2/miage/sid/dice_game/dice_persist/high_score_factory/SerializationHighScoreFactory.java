package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score_factory;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score.SerializationHighScore;

public class SerializationHighScoreFactory implements HighScoreFactory {
    @Override
    public @NotNull HighScore make() {
        return new SerializationHighScore();
    }
}