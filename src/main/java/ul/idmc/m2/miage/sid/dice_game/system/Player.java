package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;

public class Player {
    private @NotNull String name;
    private @NotNull Integer score;
    private @NotNull Dice dice1;
    private @NotNull Dice dice2;
    private @NotNull PropertyChangeSupport support;

    public Player(@NotNull String name, @NotNull Integer score) {
        this.name = name;
        this.score = score;
        dice1 = new Dice();
        dice2 = new Dice();
        support = new PropertyChangeSupport(this);
    }

    public Player(@NotNull String name) {
        this(name, 0);
    }

    public Player() {
        this("");
    }

    public void play() {
        support.firePropertyChange(PlayEvent.NEW_TURN.name(), null, null);
        dice1.roll();
        dice2.roll();
        support.firePropertyChange(PlayEvent.END_TURN.name(), null, null);
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull Integer getScore() {
        return score;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}
