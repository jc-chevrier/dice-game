package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.PostgreSQLHighScoreFactory;
import java.beans.PropertyChangeSupport;

public class Play {
    private @NotNull Player player;
    private @NotNull Integer countTurns;
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

    public void end() {
        support.firePropertyChange(PlayEvent.END_PLAY.name(), null, null);
        System.exit(0);
    }

    public void incrementCountTurns() {
        if(countTurns < 10) {
            countTurns++;
            support.firePropertyChange(PlayEvent.NEW_TURN.name(), null, null);
        } else {
            support.firePropertyChange(PlayEvent.END_PLAY.name(), null, null);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getCountTurns() {
        return countTurns;
    }

    public HighScore getHighScore() {
        return highScore;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}