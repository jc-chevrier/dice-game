package ul.idmc.m2.miage.sid.dice_game.persistence.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Player;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Score implements Serializable {
    private @NotNull Date date;
    private @NotNull String playerName;
    private @NotNull Integer score;

    public Score(@NotNull Date date, @NotNull String playerName, @NotNull Integer score) {
        this.date = date;
        this.playerName = playerName;
        this.score = score;
    }

    public Score(@NotNull String playerName, @NotNull Integer score) {
        this(new Date(), playerName, score);
    }

    public Score(@NotNull Date date, @NotNull Player player, @NotNull Integer score) {
        this(date, player.getName(), score);
    }

    public Score(@NotNull Player player, @NotNull Integer score) {
        this(new Date(), player.getName(), score);
    }

    public @NotNull Boolean isFor(@NotNull String playerName) {
        return this.playerName.equals(playerName);
    }

    public @NotNull Boolean isFor(@NotNull Player player) {
        return isFor(player.getName());
    }

    public @NotNull Date getDate() {
        return date;
    }

    public @NotNull String getPlayerName() {
        return playerName;
    }

    public @NotNull Integer getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return date.equals(score1.date) && playerName.equals(score1.playerName) && score.equals(score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, playerName, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "date=" + date .toInstant() +
                ", playerName='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }
}
