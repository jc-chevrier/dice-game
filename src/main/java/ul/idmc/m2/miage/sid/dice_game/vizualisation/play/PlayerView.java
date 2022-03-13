package ul.idmc.m2.miage.sid.dice_game.vizualisation.play;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.system.Player;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.IconManager;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends JPanel implements PropertyChangeListener, Theme {
    private @NotNull Player player;
    private PlayEvent playEvent;

    public PlayerView(@NotNull Player player) {
        this.player = player;

        player.getSupport().addPropertyChangeListener(this);

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(133, 175));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Integer height = getHeight();
        Integer width = getWidth();

        Font font = g.getFont();
        FontMetrics fontMetrics = g.getFontMetrics();

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(1, 1, width - 3, height - 3);

        Image image = IconManager.getIcon("player.png");
        g.drawImage(image, (width - 100) / 2,(height - 100) / 3, 100, 100, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font(font.getFontName(), font.getStyle(),18));
        fontMetrics = g.getFontMetrics();
        int stringX = (width - fontMetrics.stringWidth(player.getName())) / 2;
        g.drawString(player.getName(), stringX, 100 + ((height - 100) / 3) + ((height - 100) / 3));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        playEvent = PlayEvent.valueOf(evt.getPropertyName());
        repaint();
    }
}
