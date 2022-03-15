package ul.idmc.m2.miage.sid.dice_game.system;

import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;

public class Dice extends Observable implements Reinitializable {
    private Integer result;

    public Dice() {
        super();
        result = null;
    }

    @Override
    public void reinitialize() {
        result = null;
    }

    public void roll() {
        Integer oldResult = result;
        do {
            result = ((Long) Math.round(Math.random() * 6)).intValue();
        } while (result == 0);
        getSupport().firePropertyChange(PlayEvent.DICE_ROLLED.name(), oldResult, result);
    }

    public Integer getResult() {
        return result;
    }
}