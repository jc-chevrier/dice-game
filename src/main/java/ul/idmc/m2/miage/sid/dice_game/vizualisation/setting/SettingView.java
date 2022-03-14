package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;

import javax.swing.*;
import java.awt.*;

public class SettingView extends JPanel implements Theme {
    private @NotNull Play play;

    public SettingView(@NotNull Play play) {
        this.play = play;

        LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel labelPlayerName = new JLabel("Nom de joueur");
        add(labelPlayerName);

        JTextField inputPlayerName = new JTextField("");
        add(inputPlayerName);

        JLabel labelSaveSupport = new JLabel("Support de sauvegarde");
        add(labelSaveSupport);

        String[] choices = {"PostgreSQL", "MySQL", "H2", "\u0053\u00e9\u0072\u0069\u0061\u006c\u0069\u0073\u0061\u0074\u0069\u006f\u006e",};
        JComboBox<String> comboBoxSaveSupport = new JComboBox<String>(choices);
        add(comboBoxSaveSupport);

        JButton buttonSave = new JButton("Enregistrer");
        buttonSave.addActionListener((e) -> {
            play.getPlayer().setName(inputPlayerName.getText());
            HighScoreFactory highScoreFactory = null;
            switch (choices[comboBoxSaveSupport.getSelectedIndex()]) {
                case "PostgreSQL" ->  highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" ->  highScoreFactory = new MySQLHighScoreFactory();
                case "H2" ->  highScoreFactory = new H2HighScoreFactory();
                case "\u0053\u00e9\u0072\u0069\u0061\u006c\u0069\u0073\u0061\u0074\u0069\u006f\u006e" ->  highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);
            play.start();
        });
        add(buttonSave);
    }
}
