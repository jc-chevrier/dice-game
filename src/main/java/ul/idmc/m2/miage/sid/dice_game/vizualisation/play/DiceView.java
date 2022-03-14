package ul.idmc.m2.miage.sid.dice_game.vizualisation.play;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Dice;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.system.Player;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.IconManager;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Integer height = getHeight();
        Integer width = getWidth();

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(1, 1, width - 3, height - 3);

        switch (playEvent) {
            case NEW_TURN, DICE_ROLLED, END_TURN -> {
                setBackground(SKY_BLUE);
            }
            case NEW_SCORE -> {
                if(player.wins()) {
                    setBackground(LIGHT_GREEN);
                } else {
                    setBackground(LIGHT_RED);
                }
            }
        }

        String imagePath = null;
        switch (playEvent) {
            case NEW_TURN -> {
                imagePath = "dices_roll.png";
            }
            case END_TURN -> {
                imagePath = "dice_2.png";
            }
            case DICE_ROLLED, NEW_SCORE -> {
                imagePath = "dice_face_" + dice.getResult() + ".png";
            }
        }
        Image image = IconManager.get(imagePath);
        g.drawImage(image, (width - 150) / 2, (height - 150) / 2, 150, 150, null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        playEvent = PlayEvent.valueOf(evt.getPropertyName());
        repaint();
    }
}
