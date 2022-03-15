package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.principle.Observer;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.play.PlayView;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.setting.StartSettingView;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class Window extends JFrame implements Observer, Theme {
    private @NotNull Play play;

    public Window(@NotNull Play play) {
        this.play = play;

        play.getSupport().addPropertyChangeListener(this);

        setTitle("Dice Game");
        setIconImage(IconManager.getInstance().get("dice.png"));
        setSize(new Dimension(400, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new TopBarView(play));
        pack();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (PlayEvent.valueOf(evt.getPropertyName())) {
            case NEW_SETTINGS -> {
                setContentPane(new StartSettingView(play));
                repaint();

                JMenu optionsMenu = getJMenuBar().getMenu(0);
                optionsMenu.getItem(0).setEnabled(false);
                optionsMenu.getItem(1).setEnabled(false);
                optionsMenu.getItem(2).setEnabled(false);

                pack();
                setVisible(true);
            }
            case NEW_PLAY -> {
                setContentPane(new PlayView(play));
                repaint();

                JMenu optionsMenu = getJMenuBar().getMenu(0);
                if (!optionsMenu.getItem(0).isEnabled()) {
                    optionsMenu.getItem(0).setEnabled(true);
                    optionsMenu.getItem(1).setEnabled(true);
                    optionsMenu.getItem(2).setEnabled(true);
                }

                pack();
                setVisible(true);
            }
            case END_PLAY -> {
                JOptionPane.showOptionDialog(this,
                                             "Partie termin\u00e9e !\nVous avez obtenu ce score : " +
                                             play.getPlayer().getScore() + " !",
                                             "Dice Game",
                                             JOptionPane.PLAIN_MESSAGE,
                                             JOptionPane.INFORMATION_MESSAGE,
                                             new ImageIcon(IconManager.getInstance().get("dice.png")
                                             .getScaledInstance(40, 40, Image.SCALE_SMOOTH)),
                                             new String[]{"Ok"},
                                             "Ok");
                Integer option = JOptionPane.showOptionDialog(this,
                                                             "Commencer une nouvelle partie ?",
                                                             "Dice Game",
                                                             JOptionPane.YES_NO_OPTION,
                                                             JOptionPane.QUESTION_MESSAGE,
                                                             new ImageIcon(IconManager.getInstance().get("dice.png")
                                                             .getScaledInstance(40, 40, Image.SCALE_SMOOTH)),
                                                             new String[]{"Oui", "Non"},
                                                             "Oui");
                if(option.equals(JOptionPane.YES_OPTION)) {
                    play.start();
                }
            }
        }
    }
}
