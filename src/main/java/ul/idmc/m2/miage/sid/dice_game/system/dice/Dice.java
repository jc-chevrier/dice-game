package ul.idmc.m2.miage.sid.dice_game.system.dice;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import java.util.List;

public abstract class Dice extends Observable implements Reinitializable {
    protected @NotNull List<Integer> faces;
    protected Integer currentFace;

    public Dice(@NotNull List<Integer> faces) {
        super();
        this.faces = faces;
        currentFace = null;
    }

    @Override
    public void reinitialize() {
        currentFace = null;
    }

    public void roll() {
        Integer oldCurrentFace = currentFace;
        do {
            currentFace = ((Long) Math.round(Math.random() * faces.get(faces.size() - 1))).intValue();
        } while (!faces.contains(currentFace));
        getSupport().firePropertyChange(PlayEvent.DICE_ROLLED.name(), oldCurrentFace, currentFace);
    }

    public List<Integer> getFaces() {
        return faces;
    }

    public Integer getCurrentFace() {
        return currentFace;
    }
}