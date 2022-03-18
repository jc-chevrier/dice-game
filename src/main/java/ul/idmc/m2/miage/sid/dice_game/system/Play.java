package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.Score;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit.HighScoreKit;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_kit.PostgreSQLHighScoreKit;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;
import ul.idmc.m2.miage.sid.dice_game.system.dice.Dice6Faces;
import ul.idmc.m2.miage.sid.dice_game.system.rule.Rule;
import ul.idmc.m2.miage.sid.dice_game.system.rule.RuleSumFacesEquals7;
import java.util.ArrayList;
import java.util.List;

public class Play extends Observable implements Reinitializable {
    private @NotNull Rule rule;
    private @NotNull String typeDice;
    private @NotNull Player player;
    private @NotNull Integer numberTurn;
    private @NotNull HighScoreKit highScoreKit;
    private @NotNull HighScore highScore;

    public Play() {
        super();

        rule = new RuleSumFacesEquals7();

        typeDice = Dice6Faces.class.getSimpleName();

        numberTurn = 0;

        highScoreKit = new PostgreSQLHighScoreKit();

        highScore = highScoreKit.make();
        highScore.load();

        if(highScore.getScores().isEmpty()) {
            player = new Player(this);
        } else {
            List<Score> scores = highScore.getScores();
            player = new Player(this, scores.get(scores.size() - 1).getPlayerName());
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

    public @NotNull Rule getRule() {
        return rule;
    }

    public void setRule(@NotNull Rule rule) {
        this.rule = rule;
    }

    public @NotNull String getTypeDice() {
        return typeDice;
    }

    public void setTypeDice(@NotNull String typeDice) {
        this.typeDice = typeDice;
        player.reinitialize();
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull Integer getNumberTurn() {
        return numberTurn;
    }

    public @NotNull HighScoreKit getHighScoreKit() {
        return highScoreKit;
    }

    public void setHighScoreKit(@NotNull HighScoreKit highScoreKit) {
        this.highScoreKit = highScoreKit;

        List<Score> oldScores = highScore.getScores();
        highScore = highScoreKit.make();
        highScore.load();

        List<Score> scores = highScore.getScores();
        Boolean oldPlayerNameWasDerivedFromOldLoad = !oldScores.isEmpty() &&
                                                      oldScores.get(oldScores.size() - 1)
                                                               .getPlayerName()
                                                               .equals(player.getName());
        if(!scores.isEmpty() && (player.getName().isEmpty() || oldPlayerNameWasDerivedFromOldLoad)) {
            player = new Player(this, scores.get(scores.size() - 1).getPlayerName());
        } else {
            if(oldPlayerNameWasDerivedFromOldLoad) {
                player = new Player(this);
            }
        }
    }

    public @NotNull HighScore getHighScore() {
        return highScore;
    }
}