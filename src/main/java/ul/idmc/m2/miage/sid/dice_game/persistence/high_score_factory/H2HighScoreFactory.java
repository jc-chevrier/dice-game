package ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.H2HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;

public class H2HighScoreFactory implements HighScoreFactory {
    @Override
    public @NotNull HighScore make() {
        return new H2HighScore();
    }
}