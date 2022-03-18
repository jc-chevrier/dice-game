package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.H2HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;

public class H2HighScoreKit implements HighScoreKit {
    @Override
    public @NotNull HighScore make() {
        return H2HighScore.getInstance();
    }
}