package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.high_score.HighScoreView;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.setting.SettingView;

import javax.swing.*;
import java.awt.*;

public class TopBarView extends JMenuBar implements Theme {
    private @NotNull Play play;

    public TopBarView(@NotNull Play play) {
        this.play = play;

        UIManager.put("MenuBar.background", LIGHT_BLUE);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.selectionBackground", SKY_BLUE);
        UIManager.put("Menu.selectionForeground", Color.BLACK);
        UIManager.put("MenuItem.background", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.BLACK);
        UIManager.put("MenuItem.selectionBackground", SKY_BLUE);
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);

        JMenu menu = new JMenu("options");
        menu.setPreferredSize(new Dimension(60, 20));

        JMenuItem item1 = new JMenuItem("nouvelle partie");
        item1.addActionListener((e) -> play.start());
        menu.add(item1);

        JMenuItem item2 = new JMenuItem("meilleurs scores");
        item2.addActionListener((e) -> {
            Window window = ((Window) SwingUtilities.getWindowAncestor(this));
            window.setContentPane(new HighScoreView(play));
            window.repaint();
            window.pack();
            window.setVisible(true);
        });
        menu.add(item2);

        JMenuItem item3 = new JMenuItem("\u0070\u0061\u0072\u0061\u006d\u00e8\u0074\u0072\u0065\u0073");
        item3.addActionListener((e) -> {
            Window window = ((Window) SwingUtilities.getWindowAncestor(this));
            window.setContentPane(new SettingView(play));
            window.repaint();
            window.pack();
            window.setVisible(true);
        });
        menu.add(item3);

        JMenuItem item4 = new JMenuItem("\u0061\u0072\u0072\u00ea\u0074");
        item4.addActionListener((e) -> play.stop());
        menu.add(item4);
        add(menu);

        setPreferredSize(new Dimension(400, 25));
    }
}
