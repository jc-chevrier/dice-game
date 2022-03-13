package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;

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
        JMenuItem item2 = new JMenuItem("abandonner la partie");
        item2.addActionListener((e) -> play.cancel());
        menu.add(item2);
        add(menu);

        setPreferredSize(new Dimension(400, 25));
    }
}
