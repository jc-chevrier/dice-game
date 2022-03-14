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
        reinitialize();
        support = new PropertyChangeSupport(this);
    }

    private void reinitialize() {
        numberTurn = 0;

        if (highScoreFactory == null) {
            highScoreFactory = new PostgreSQLHighScoreFactory();
        }

        highScore = highScoreFactory.make();
        highScore.load();

        if(player == null) {
            if(highScore.getScores().isEmpty()) {
                player = new Player();
            } else {
                List<Score> scores = highScore.getScores();
                player = new Player(scores.get(scores.size() - 1).getPlayerName());
            }
        } else {
            player = new Player(player.getName());
        }
    }

    public void start() {
        reinitialize();
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

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull Integer getNumberTurn() {
        return numberTurn;
    }

    public @NotNull HighScore getHighScore() {
        return highScore;
    }

    public @NotNull HighScoreFactory getHighScoreFactory() {
        return highScoreFactory;
    }

    public void setHighScoreFactory(@NotNull HighScoreFactory highScoreFactory) {
        this.highScoreFactory = highScoreFactory;

        highScore = highScoreFactory.make();
        highScore.load();

        List<Score> scores = highScore.getScores();
        if(player.getName().isEmpty() && !scores.isEmpty()) {
            player = new Player(scores.get(scores.size() - 1).getPlayerName());
        }
    }

    public @NotNull PropertyChangeSupport getSupport() {
        return support;
    }
}