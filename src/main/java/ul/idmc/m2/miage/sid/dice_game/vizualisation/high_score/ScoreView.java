package ul.idmc.m2.miage.sid.dice_game.vizualisation.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.Score;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.IconManager;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;
import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ScoreView extends JPanel implements Theme {
    protected @NotNull Score score;
    protected @NotNull Color color;
    protected @NotNull String starImageName;

    public ScoreView(@NotNull Score score, @NotNull Color color, @NotNull String starImageName) {
        this.score = score;

        this.color = color;

        this.starImageName = starImageName;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 365 / 10));
    }

    public ScoreView(@NotNull Score score) {
        this(score, new Color(70, 130, 180), "star.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Integer width = getWidth();
        Integer height = getHeight();

        Font font = g.getFont();
        FontMetrics fontMetrics = g.getFontMetrics();

        Integer normalFontSize = 13;

        g.setColor(color);

        g.drawRect(0, 0, width - 1, height - 1);
        g.drawRect(1, 1, width - 3, height - 3);

        Image image = IconManager.getInstance().get(starImageName);
        g.drawImage(image, 15, (365 / 10 - 20) / 2, 20, 20, null);

        g.setFont(new Font(font.getFontName(), font.getStyle(), normalFontSize));
        fontMetrics = g.getFontMetrics();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                                                              .withLocale(Locale.FRANCE)
                                                              .withZone(ZoneId.of("Europe/Paris"));
        String string = dateTimeFormatter.format(score.getDate().toInstant());
        Integer stringX = (200 - (15 + 20) - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, 15 + 20 + stringX, 365 / 10 / 3 * 2);


        string = "|";
        g.drawString(string, 200, 365 / 10 / 3 * 2);

        string = score.getPlayerName();
        stringX = (100 - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, 200 + stringX, 365 / 10 / 3 * 2);


        string = "|";
        g.drawString(string, 300, 365 / 10 / 3 * 2);


        string = score.getScore().toString();
        stringX = (100 - fontMetrics.stringWidth(string)) / 2;
        g.drawString(string, 300 + stringX, 365 / 10 / 3 * 2);
    }
}
