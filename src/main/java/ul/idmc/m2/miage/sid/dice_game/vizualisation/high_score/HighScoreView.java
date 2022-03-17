package ul.idmc.m2.miage.sid.dice_game.vizualisation.high_score;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.HighScore;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score.Score;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreView extends JPanel implements Theme {
    private @NotNull HighScore highScore;

    public HighScoreView(@NotNull HighScore highScore) {
        this.highScore = highScore;

        List<Score> scores = (ArrayList<Score>) ((ArrayList<Score>) highScore.getScores()).clone();
        if(!scores.isEmpty()) {
            Collections.sort(scores, (score1, score2) -> score2.getScore().compareTo(score1.getScore()));
            scores = scores.subList(0, scores.size() > 10 ? 10 : scores.size());
        }

        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            ScoreView scoreView = null;
            if (i < 3) {
                scoreView = new BestScoreView(score);
            } else {
                scoreView = new ScoreView(score);
            }
            add(scoreView);

            JPanel voidView = new JPanel();
            voidView.setPreferredSize(new Dimension(400, 1));
            add(voidView);
        }

        if (scores.size() < 10) {
            JPanel voidView = new JPanel();
            voidView.setPreferredSize(new Dimension(400, (10 - scores.size()) * (375 / 10)));
            add(voidView);
        }

        LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        setPreferredSize(new Dimension(400, 375));
    }
}
