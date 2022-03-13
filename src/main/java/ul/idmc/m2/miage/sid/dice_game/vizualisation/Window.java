package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Window extends JFrame implements PropertyChangeListener {
    public Window(@NotNull Play play) {
        play.getSupport().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(PlayEvent.NEW_PLAY.equalsAsString(evt.getPropertyName())) {
            setVisible(true);
        }
    }
}
