package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Window extends JFrame implements PropertyChangeListener, Theme {
    private @NotNull Play play;

    public Window(@NotNull Play play) {
        this.play = play;

        play.getSupport().addPropertyChangeListener(this);

        setTitle("Dice Game");
        setIconImage(IconManager.getIcon("dice.png"));
        setSize(new Dimension(400, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new TopBarView(play));
        setContentPane(new PlayView(play));
        pack();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (PlayEvent.valueOf(evt.getPropertyName())) {
            case NEW_PLAY -> new Thread(() -> setVisible(true)).start();
        }
    }
}
