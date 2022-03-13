package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;

import javax.swing.*;
import java.awt.*;

public class PlayView extends JPanel implements Theme {
    private @NotNull Play play;

    public PlayView(@NotNull Play play) {
        this.play = play;

        Integer width = Window.WIDTH;
        Integer height =  Window.HEIGHT - Window.BAR_HEIGHT;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        JPanel dicesView = new JPanel();
        GridLayout gridLayout2 = new GridLayout(1, 2);
        dicesView.setLayout(gridLayout2);
        DiceView diceView1 = new DiceView(play.getPlayer(), play.getPlayer().getDice1());
        dicesView.add(diceView1);
        DiceView diceView2 = new DiceView(play.getPlayer(), play.getPlayer().getDice2());
        dicesView.add(diceView2);
        dicesView.setPreferredSize(new Dimension(width, 200));
        dicesView.setBackground(LIGHT_GRAY);
        add(dicesView);

        JPanel datasView = new JPanel();
        datasView.setPreferredSize(new Dimension(width, height - 200));
        datasView.setBackground(LIGHT_GRAY);
        add(datasView);

        setBackground(LIGHT_GRAY);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
