package ul.idmc.m2.miage.sid.dice_game.vizualisation;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.system.PlayEvent;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.play.PlayView;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.user_add.UserAddView;

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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (PlayEvent.valueOf(evt.getPropertyName())) {
            case NEW_USER -> {
                setContentPane(new UserAddView());
                new Thread(() -> {
                    pack();
                    setVisible(true);
                }).start();
            }
            case NEW_PLAY -> {
                setContentPane(new PlayView(play));
                new Thread(() -> {
                    pack();
                    setVisible(true);
                }).start();
            }
            case END_PLAY -> {
                JOptionPane.showConfirmDialog(this,
                                              "Partie terminée !\nVous avez obtenu ce score : " +
                                              play.getPlayer().getScore() + " !",
                                              "Dice Game",
                                              JOptionPane.OK_CANCEL_OPTION,
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(IconManager.get("dice.png")
                                              .getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                Integer option = JOptionPane.showConfirmDialog(this,
                                                               "Commencer une nouvelle partie ?",
                                                               "Dice Game",
                                                               JOptionPane.YES_NO_OPTION,
                                                               JOptionPane.QUESTION_MESSAGE,
                                                               new ImageIcon(IconManager.get("dice.png")
                                                               .getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                if(option.equals(JOptionPane.YES_OPTION)) {
                    play.start();
                } else {
                    play.cancel();
                }
            }
        }
    }
}
