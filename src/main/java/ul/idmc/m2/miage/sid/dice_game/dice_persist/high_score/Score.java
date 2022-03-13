package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.dice_system.Player;
import java.io.Serializable;
import java.util.Date;

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

    public void increment(@NotNull Integer score) {
        this.score += score;
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
    public String toString() {
        return "Score{" +
                "date=" + date .toInstant() +
                ", playerName='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }
}
