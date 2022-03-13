package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import ul.idmc.m2.miage.sid.dice_game.system.Dice;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.system.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DiceView extends JPanel implements PropertyChangeListener, Theme {
    private @NotNull Player player;
    private @NotNull Dice dice;
    private PlayEvent playEvent;

    public DiceView(@NotNull Player player, @NotNull Dice dice) {
        this.player = player;
        this.dice = dice;
        playEvent = PlayEvent.END_TURN;

        player.getSupport().addPropertyChangeListener(this);
        dice.getSupport().addPropertyChangeListener(this);

        setBackground(SKY_BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 199, 199);
        g.drawRect(1, 1, 197, 197);
        String imagePath = null;
        switch (playEvent) {
            case NEW_TURN -> {
                imagePath = "./icon/dices_roll.png";
            }
            case DICE_HAVE_ROLLED -> {
                imagePath = "./icon/dice_face_" + dice.getResult() + ".png";

            }
            case END_TURN -> {
                imagePath = "./icon/dice_2.png";
            }
        }
        Image image = null;
        try {
            image = ImageIO.read(Main.class.getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        g.drawImage(image, 25, 25, 150, 150, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        playEvent = PlayEvent.valueOf(evt.getPropertyName());
        repaint();
    }
}
