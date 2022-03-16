package ul.idmc.m2.miage.sid.dice_game.system.dice;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import java.util.List;

public abstract class Dice extends Observable implements Reinitializable {
    protected @NotNull List<Integer> faces;
    protected Integer result;

    public Dice(@NotNull List<Integer> faces) {
        super();
        this.faces = faces;
        result = null;
    }

    @Override
    public void reinitialize() {
        result = null;
    }

    public void roll() {
        Integer oldResult = result;
        do {
            result = ((Long) Math.round(Math.random() * faces.get(faces.size() - 1))).intValue();
        } while (!faces.contains(result));
        getSupport().firePropertyChange(PlayEvent.DICE_ROLLED.name(), oldResult, result);
    }

    public List<Integer> getFaces() {
        return faces;
    }

    public Integer getResult() {
        return result;
    }
}