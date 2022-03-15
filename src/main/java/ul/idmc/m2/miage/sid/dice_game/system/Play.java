package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.Score;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.HighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.PostgreSQLHighScoreFactory;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;
import java.util.ArrayList;
import java.util.List;

public class Play extends Observable implements Reinitializable {
    private @NotNull Player player;
    private @NotNull Integer numberTurn;
    private @NotNull HighScoreFactory highScoreFactory;
    private @NotNull HighScore highScore;

    public Play() {
        super();

        numberTurn = 0;

        highScoreFactory = new PostgreSQLHighScoreFactory();

        highScore = highScoreFactory.make();
        highScore.load();

        if(highScore.getScores().isEmpty()) {
            player = new Player();
        } else {
            List<Score> scores = highScore.getScores();
            player = new Player(scores.get(scores.size() - 1).getPlayerName());
        }
    }

    @Override
    public void reinitialize() {
        numberTurn = 0;

        player.reinitialize();
    }

    public void setUp() {
        getSupport().firePropertyChange(PlayEvent.NEW_SETTINGS.name(), null, null);
    }

    public void start() {
        reinitialize();
        getSupport().firePropertyChange(PlayEvent.NEW_PLAY.name(), null, null);
    }

    public void end() {
        if (ended()) {
            List<Score> oldScores = (ArrayList<Score>) ((ArrayList<Score>) highScore.getScores()).clone();
            highScore.addScore(player);
            highScore.save();
            getSupport().firePropertyChange(PlayEvent.END_PLAY.name(), oldScores, highScore.getScores());
        }
    }

    public void stop() {
        System.exit(0);
    }

    public boolean ended() {
        return numberTurn == 10;
    }

    public void incrementNumberTurn() {
        if (!ended()) {
            Integer oldNumberTurn = numberTurn;
            numberTurn++;
            getSupport().firePropertyChange(PlayEvent.NEW_TURN.name(), oldNumberTurn, numberTurn);
        }
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

        List<Score> oldScores = highScore.getScores();
        highScore = highScoreFactory.make();
        highScore.load();

        List<Score> scores = highScore.getScores();
        Boolean oldPlayerNameWasDerivedFromOldLoad = !oldScores.isEmpty() &&
                                                      oldScores.get(oldScores.size() - 1)
                                                               .getPlayerName()
                                                               .equals(player.getName());
        if(!scores.isEmpty() && (player.getName().isEmpty() || oldPlayerNameWasDerivedFromOldLoad)) {
            player = new Player(scores.get(scores.size() - 1).getPlayerName());
        } else {
            if(oldPlayerNameWasDerivedFromOldLoad) {
                player = new Player();
            }
        }
    }
}