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

        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setPreferredSize(new Dimension(60, 20));
        add(optionsMenu);

        JMenuItem startItem = new JMenuItem("Nouvelle partie");
        startItem.addActionListener((e) -> play.start());
        optionsMenu.add(startItem);

        JMenuItem highScoresItem = new JMenuItem("Meilleurs scores");
        highScoresItem.addActionListener((e) -> {
            Window window = ((Window) SwingUtilities.getWindowAncestor(this));
            window.setContentPane(new HighScoreView(play.getHighScore()));
            window.repaint();
            window.pack();
            window.setVisible(true);
        });
        optionsMenu.add(highScoresItem);

        JMenuItem settingsItem = new JMenuItem("Param\u00e8tres");
        settingsItem.addActionListener((e) -> {
            Window window = ((Window) SwingUtilities.getWindowAncestor(this));
            window.setContentPane(new SettingView(play));
            window.repaint();
            window.pack();
            window.setVisible(true);
        });
        optionsMenu.add(settingsItem);

        JMenuItem stopItem = new JMenuItem("Arr\u00eat");
        stopItem.addActionListener((e) -> play.stop());
        optionsMenu.add(stopItem);

        setPreferredSize(new Dimension(400, 25));
    }
}
