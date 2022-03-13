package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.PostgreSQLHighScoreFactory;
import java.beans.PropertyChangeSupport;

public class Play {
    private @NotNull Player player;
    private @NotNull HighScoreFactory highScoreFactory;
    private @NotNull HighScore highScore;
    private @NotNull PropertyChangeSupport support;

    public Play() {
        highScoreFactory = new PostgreSQLHighScoreFactory();
        highScore = highScoreFactory.make();
        highScore.load();
        player = new Player();
        support = new PropertyChangeSupport(this);
    }

    public void start() {
        support.firePropertyChange(PlayEvent.NEW_PLAY.name(), null, null);
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}