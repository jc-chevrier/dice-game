package ul.idmc.m2.miage.sid.dice_game.system;

import org.jetbrains.annotations.NotNull;
import java.beans.PropertyChangeSupport;

public class Dice {
    private @NotNull Integer result;
    private @NotNull PropertyChangeSupport support;

    public Dice() {
        result = 0;
        support = new PropertyChangeSupport(this);
    }

    public void roll() {
        Integer oldResult = result;
        do {
            result = ((Long) Math.round(Math.random() * 6)).intValue();
        } while (result == 0);
        support.firePropertyChange(PlayEvent.DICE_ROLLED.name(), oldResult, result);
    }

    public Integer getResult() {
        return result;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}