package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.Main;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Window extends JFrame implements PropertyChangeListener, Theme {
    private @NotNull Play play;
    private @NotNull final static String TITLE = "Dice Game";
    protected @NotNull final static Integer WIDTH = 400;
    protected @NotNull final static Integer HEIGHT = 400;
    protected @NotNull final static Integer BAR_HEIGHT = 25;

    public Window(@NotNull Play play) {
        this.play = play;
        play.getSupport().addPropertyChangeListener(this);
    }

    public void configureBar() {
        UIManager.put("MenuBar.background", LIGHT_BLUE);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.selectionBackground", SKY_BLUE);
        UIManager.put("Menu.selectionForeground", Color.BLACK);
        UIManager.put("MenuItem.background", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.BLACK);
        UIManager.put("MenuItem.selectionBackground", SKY_BLUE);
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);
    }

    public @NotNull JMenuBar paintBar() {
        JMenuBar bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(WIDTH, BAR_HEIGHT));
        setJMenuBar(bar);
        JMenu menu = new JMenu("options");
        bar.add(menu);
        menu.setPreferredSize(new Dimension(60, 20));
        JMenuItem item1 = new JMenuItem("nouvelle partie");
        item1.addActionListener((e) -> play.start());
        menu.add(item1);
        JMenuItem item2 = new JMenuItem("arrêt");
        item2.addActionListener((e) -> play.end());
        menu.add(item2);

        return bar;
    }

    public void paint() {
        Image image = null;
        try {
            image = ImageIO.read(Main.class.getResourceAsStream("./icon/dice.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        setTitle(TITLE);
        setIconImage(image);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configureBar();
        setJMenuBar(paintBar());
        setContentPane(new PlayView(play));
        pack();
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (PlayEvent.valueOf(evt.getPropertyName())) {
            case NEW_PLAY -> paint();
        }
    }
}
