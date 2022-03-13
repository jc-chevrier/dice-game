package ul.idmc.m2.miage.sid.dice_game.dice_system;

import org.jetbrains.annotations.NotNull;

public class Player {
    private String name;
    private Integer score;

    public Player(@NotNull String name, @NotNull Integer score) {
        this.name = name;
        this.score = score;
    }

    public Player(@NotNull String name) {
        this(name, 0);
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Integer getScore() {
        return score;
    }
}
