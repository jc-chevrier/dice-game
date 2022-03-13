package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;

public interface HighScoreFactory {
    public @NotNull HighScore make();
}
