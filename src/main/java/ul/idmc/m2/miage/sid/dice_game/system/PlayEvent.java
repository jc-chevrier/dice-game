package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;

public enum PlayEvent {
    NEW_USER,
    NEW_PLAY,
    NEW_TURN,
    DICE_ROLLED,
    NEW_SCORE,
    END_TURN,
    END_PLAY;

    public Boolean equalsAsString(@NotNull String playEventAsString) {
        return this.name().equals(playEventAsString);
    }
}
