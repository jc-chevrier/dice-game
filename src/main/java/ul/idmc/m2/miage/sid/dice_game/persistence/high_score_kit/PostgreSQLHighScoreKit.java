package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.PostgreSQLHighScore;

public class PostgreSQLHighScoreKit implements HighScoreKit {
    @Override
    public @NotNull HighScore make() {
        return PostgreSQLHighScore.getInstance();
    }
}
