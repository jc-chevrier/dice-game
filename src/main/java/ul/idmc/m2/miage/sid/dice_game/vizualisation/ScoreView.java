package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.system.Player;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScoreView extends JPanel implements PropertyChangeListener, Theme {
    private @NotNull Play play;
    private @NotNull Player player;
    private PlayEvent playEvent;

    public ScoreView(@NotNull Play play) {
        this.play = play;
        this.player = play.getPlayer();

        play.getSupport().addPropertyChangeListener(this);
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

        Integer normalFontSize = 20;
        Integer bigFontSize = 30;

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(1, 1, width - 3, height - 3);

        g.setColor(Color.WHITE);
        String string = "Tour";
        g.setFont(new Font(font.getFontName(), font.getStyle(), normalFontSize));
        fontMetrics = g.getFontMetrics();
        Integer stringX = (width - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, stringX,40);

        string = play.getNumberTurn() + "";
        Boolean newTurn = playEvent == PlayEvent.NEW_TURN;
        g.setColor(newTurn ? LIGHT_BLUE : Color.WHITE);
        g.setFont(new Font(font.getFontName(), Font.BOLD, newTurn ? bigFontSize : normalFontSize));
        fontMetrics = g.getFontMetrics();
        stringX = (width - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, stringX,70);

        string = "Score";
        g.setColor(Color.WHITE);
        g.setFont(new Font(font.getFontName(), font.getStyle(), normalFontSize));
        fontMetrics = g.getFontMetrics();
        stringX = (width - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, stringX, 120);

        string = player.getScore() + "";
        Boolean playerWins = (playEvent == PlayEvent.DICE_ROLLED || playEvent == PlayEvent.NEW_SCORE) && player.wins();
        g.setColor(playerWins ? LIGHT_GREEN : Color.WHITE);
        g.setFont(new Font(font.getFontName(), Font.BOLD, playerWins ? bigFontSize : normalFontSize));
        fontMetrics = g.getFontMetrics();
        stringX = (width - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, stringX, 150);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        playEvent = PlayEvent.valueOf(evt.getPropertyName());
        repaint();
    }
}
