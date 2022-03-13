package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.PostgreSQLHighScoreFactory;
import java.beans.PropertyChangeSupport;

public class Play {
    private @NotNull Player player;
    private @NotNull Integer numberTurn;
    private @NotNull HighScoreFactory highScoreFactory;
    private @NotNull HighScore highScore;
    private @NotNull PropertyChangeSupport support;

    public Play() {
        player = new Player("Tom");
        numberTurn = 0;
        highScoreFactory = new PostgreSQLHighScoreFactory();
        highScore = highScoreFactory.make();
        highScore.load();
        support = new PropertyChangeSupport(this);
    }

    public void start() {
        support.firePropertyChange(PlayEvent.NEW_PLAY.name(), null, null);
    }

    public void end() {
        if(ended()) {
            support.firePropertyChange(PlayEvent.END_PLAY.name(), null, null);
            highScore.addScore(player);
            System.exit(0);
        }
    }

    public void cancel() {
        if(!ended()) {
            support.firePropertyChange(PlayEvent.END_PLAY.name(), null, null);
            System.exit(0);
        }
    }

    public void incrementNumberTurn() {
        if(!ended()) {
            numberTurn++;
            support.firePropertyChange(PlayEvent.NEW_TURN.name(), null, null);
        }
    }

    public boolean ended() {
        return numberTurn == 10;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getNumberTurn() {
        return numberTurn;
    }

    public HighScore getHighScore() {
        return highScore;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}