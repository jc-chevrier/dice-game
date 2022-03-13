package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.Score;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.PostgreSQLHighScoreFactory;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Play {
    private @NotNull Player player;
    private @NotNull Integer numberTurn;
    private @NotNull HighScoreFactory highScoreFactory;
    private @NotNull HighScore highScore;
    private @NotNull PropertyChangeSupport support;

    public Play() {
        initialize();
        support = new PropertyChangeSupport(this);
    }

    private void initialize() {
        numberTurn = 0;
        highScoreFactory = new PostgreSQLHighScoreFactory();
        highScore = highScoreFactory.make();
        highScore.load();
        if(highScore.getScores().isEmpty()) {
            player = new Player();
        } else {
            player = new Player(highScore.getScores().get(0).getPlayerName());
        }
    }

    public void start() {
        initialize();
        support.firePropertyChange((player.getName().isEmpty() ? PlayEvent.NEW_USER : PlayEvent.NEW_PLAY).name(), null, null);
    }

    public void end() {
        if(ended()) {
            List<Score> oldScores = (ArrayList<Score>) ((ArrayList<Score>) highScore.getScores()).clone();
            highScore.addScore(player);
            highScore.save();
            support.firePropertyChange(PlayEvent.END_PLAY.name(), oldScores, highScore.getScores());
        }
    }

    public void stop() {
        System.exit(0);
    }

    public void incrementNumberTurn() {
        if(!ended()) {
            Integer oldNumberTurn = numberTurn.intValue();
            numberTurn++;
            support.firePropertyChange(PlayEvent.NEW_TURN.name(), oldNumberTurn, numberTurn);
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