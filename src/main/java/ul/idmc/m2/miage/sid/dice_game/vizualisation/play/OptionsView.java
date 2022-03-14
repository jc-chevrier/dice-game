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

        JPanel voidView = new JPanel();
        voidView.setBackground(Color.LIGHT_GRAY);
        voidView.setPreferredSize(new Dimension(100, (175 - 100) / 2));
        add(voidView);

        JButton playButton = new JButton("");
        playButton.setMargin(new Insets(0, 0, 0, 0));
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setBackground(Color.LIGHT_GRAY);
        playButton.setIcon(new ImageIcon(IconManager.getInstance().get("play_arrow.png")
                           .getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        playButton.addActionListener((e) -> {
            new Thread(() -> {
                playButton.setEnabled(false);
                play.incrementNumberTurn();
                player.play();
                if(play.ended()) {
                    play.end();
                } else {
                    playButton.setEnabled(true);
                }
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
