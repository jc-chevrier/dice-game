package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.SerializationHighScore;

public class SerializationHighScoreKit implements HighScoreKit {
    @Override
    public @NotNull HighScore make() {
        return SerializationHighScore.getInstance();
    }
}
