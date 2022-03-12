package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.dice_system.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class HighScore {
    protected List<Score> scores;
    protected static HighScore highScoreSingleton;

    public HighScore() {
        scores = new ArrayList<Score>();
    }

    public abstract void load();

    public abstract void save();

    public void addScore(@NotNull String playerName, @NotNull Integer scoreNumber) {
        scores.stream()
              .filter(score -> score.isFor(playerName))
              .findFirst()
              .ifPresentOrElse((score) -> score.increment(scoreNumber), () -> scores.add(new Score(playerName, scoreNumber)));
    }

    public void addScore(@NotNull Player player, @NotNull Integer scoreNumber) {
        addScore(player.getName(), scoreNumber);
    }

    public List<Score> getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "scores=" + scores +
                '}';
    }
}
