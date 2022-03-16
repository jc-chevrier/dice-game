package ul.idmc.m2.miage.sid.dice_game.system.dice;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.principle.Observable;
import ul.idmc.m2.miage.sid.dice_game.principle.Reinitializable;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import java.util.List;

public abstract class Dice extends Observable implements Reinitializable {
    protected @NotNull List<Integer> faces;
    protected Integer face;

    public Dice(@NotNull List<Integer> faces) {
        super();
        this.faces = faces;
        face = null;
    }

    @Override
    public void reinitialize() {
        face = null;
    }

    public void roll() {
        Integer oldFace = face;
        do {
            face = ((Long) Math.round(Math.random() * faces.get(faces.size() - 1))).intValue();
        } while (!faces.contains(face));
        getSupport().firePropertyChange(PlayEvent.DICE_ROLLED.name(), oldFace, face);
    }

    public List<Integer> getFaces() {
        return faces;
    }

    public Integer getFace() {
        return face;
    }
}