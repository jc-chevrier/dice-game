package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;

public interface HighScoreKit {
    public @NotNull HighScore make();
}
