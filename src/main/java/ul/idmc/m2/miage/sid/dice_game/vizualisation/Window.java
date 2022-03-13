package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.play.PlayView;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.setting.SettingView;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Window extends JFrame implements PropertyChangeListener, Theme {
    private @NotNull Play play;

    public Window(@NotNull Play play) {
        this.play = play;

        play.getSupport().addPropertyChangeListener(this);

        setTitle("Dice Game");
        setIconImage(IconManager.get("dice.png"));
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
            case NEW_USER -> {
                setContentPane(new SettingView(play));
                repaint();
                pack();
                setVisible(true);
            }
            case NEW_PLAY -> {
                setContentPane(new PlayView(play));
                repaint();
                pack();
                setVisible(true);
            }
            case END_PLAY -> {
                JOptionPane.showOptionDialog(this,
                                             "Partie terminée !\nVous avez obtenu ce score : " +
                                             play.getPlayer().getScore() + " !",
                                             "Dice Game",
                                             JOptionPane.OK_CANCEL_OPTION,
                                             JOptionPane.INFORMATION_MESSAGE,
                                             new ImageIcon(IconManager.get("dice.png")
                                             .getScaledInstance(40, 40, Image.SCALE_SMOOTH)),
                                             new String[]{"Ok", "Annuler"},
                                             "Ok");
                Integer option = JOptionPane.showOptionDialog(this,
                                                             "Commencer une nouvelle partie ?",
                                                             "Dice Game",
                                                             JOptionPane.YES_NO_OPTION,
                                                             JOptionPane.QUESTION_MESSAGE,
                                                             new ImageIcon(IconManager.get("dice.png")
                                                             .getScaledInstance(40, 40, Image.SCALE_SMOOTH)),
                                                             new String[]{"Oui", "Non"},
                                                             "Oui");
                if(option.equals(JOptionPane.YES_OPTION)) {
                    play.start();
                } else {
                    play.stop();
                }
            }
        }
    }
}
