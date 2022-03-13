package ul.idmc.m2.miage.sid.dice_game.vizualisation.play;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.Player;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.IconManager;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;

import javax.swing.*;
import java.awt.*;

public class OptionsView extends JPanel implements Theme {
    private @NotNull Play play;
    private @NotNull Player player;

    public OptionsView(@NotNull Play play) {
        this.play = play;
        this.player = play.getPlayer();

        final JButton playButton = new JButton("Jouer");
        playButton.setIcon(new ImageIcon(IconManager.getIcon("play_arrow.png")
                           .getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        playButton.addActionListener((e) -> {
            new Thread(() -> {
                playButton.setEnabled(false);
                play.incrementNumberTurn();
                player.play();
                playButton.setEnabled(true);
            }).start();
        });
        add(playButton);

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(133, 175));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Integer height = getHeight();
        Integer width = getWidth();

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(1, 1, width - 3, height - 3);
    }
}
