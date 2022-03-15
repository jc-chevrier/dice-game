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

public class BestScoreView extends ScoreView implements Theme {
    public BestScoreView(@NotNull Score score) {
        super(score, new Color(255, 202, 24), "star_more.png");
    }
}
