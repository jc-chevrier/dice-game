package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.MySQLHighScore;

public class MySQLHighScoreKit implements HighScoreKit {
    @Override
    public @NotNull HighScore make() {
        return MySQLHighScore.getInstance();
    }
}
