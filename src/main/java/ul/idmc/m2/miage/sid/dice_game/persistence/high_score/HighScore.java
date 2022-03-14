package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class HighScore implements Serializable {
    protected @NotNull List<Score> scores;

    protected HighScore() {
        scores = new ArrayList<Score>();
    }

    public abstract void load();

    public abstract void save();

    public void addScore(@NotNull Score score) {
        scores.add(score);
        Collections.sort(scores, (score1, score2) -> score1.getDate().compareTo(score2.getDate()));
    }

    public void addScore(@NotNull String playerName, @NotNull Integer score) {
        addScore(new Score(playerName, score));
    }

    public void addScore(@NotNull Player player) {
        addScore(player.getName(), player.getScore());
    }

    public @NotNull List<Score> getScores() {
        return scores;
    }

    public void setScores(@NotNull List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "scores=" + scores +
                '}';
    }
}