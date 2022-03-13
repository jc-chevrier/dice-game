package ul.idmc.m2.miage.sid.dice_game.vizualisation.play;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayView extends JPanel implements Theme {
    private @NotNull Play play;

    public PlayView(@NotNull Play play) {
        this.play = play;

        Integer width = 400;
        Integer height =  375;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        JPanel dicesView = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(dicesView, BoxLayout.X_AXIS);
        dicesView.setLayout(boxLayout2);
        DiceView diceView1 = new DiceView(play.getPlayer(), play.getPlayer().getDice1());
        dicesView.add(diceView1);
        DiceView diceView2 = new DiceView(play.getPlayer(), play.getPlayer().getDice2());
        dicesView.add(diceView2);
        dicesView.setPreferredSize(new Dimension(width, 200));
        dicesView.setBackground(LIGHT_GRAY);
        add(dicesView);

        JPanel datasView = new JPanel();
        BoxLayout boxLayout3 = new BoxLayout(datasView, BoxLayout.X_AXIS);
        datasView.setLayout(boxLayout3);
        datasView.setPreferredSize(new Dimension(width, height - 200));
        datasView.setBackground(LIGHT_GRAY);
        PlayerView playerView = new PlayerView(play.getPlayer());
        datasView.add(playerView);
        ScoreView scoreView = new ScoreView(play);
        datasView.add(scoreView);
        OptionsView optionsView = new OptionsView(play);
        datasView.add(optionsView);
        add(datasView);


        setBackground(LIGHT_GRAY);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
