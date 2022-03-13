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
        result = ((Long) Math.round(Math.random() * 6)).intValue();
        support.firePropertyChange(PlayEvent.DICES_HAVE_ROLLED.name(), oldResult, result);
    }

    public Integer getResult() {
        return result;
    }
}