package ul.idmc.m2.miage.sid.dice_game.dice_persist.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.dice_system.Player;

public class Score {
    private String playerName;
    private Integer scoreNumber;

    public Score(@NotNull String playerName, @NotNull Integer scoreNumber) {
        this.playerName = playerName;
        this.scoreNumber = scoreNumber;
    }

    public Score(@NotNull Player player, @NotNull Integer scoreNumber) {
        this(player.getName(), scoreNumber);
    }

    public void increment(@NotNull Integer scoreNumber) {
        this.scoreNumber += scoreNumber;
    }

    public @NotNull Boolean isFor(@NotNull String playerName) {
        return this.playerName.equals(playerName);
    }

    public @NotNull Boolean isFor(@NotNull Player player) {
        return isFor(player.getName());
    }

    public @NotNull String getPlayerName() {
        return playerName;
    }

    public @NotNull Integer getScoreNumber() {
        return scoreNumber;
    }

    @Override
    public String toString() {
        return "Score{" +
                "playerName='" + playerName + '\'' +
                ", scoreNumber=" + scoreNumber +
                '}';
    }
}
