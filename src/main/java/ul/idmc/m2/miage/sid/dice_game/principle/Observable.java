package ul.idmc.m2.miage.sid.dice_game.principle;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;

public abstract class Observable {
    private @NotNull PropertyChangeSupport support;

    public Observable() {
        support = new PropertyChangeSupport(this);
    }

    public @NotNull PropertyChangeSupport getSupport() {
        return support;
    }
}
