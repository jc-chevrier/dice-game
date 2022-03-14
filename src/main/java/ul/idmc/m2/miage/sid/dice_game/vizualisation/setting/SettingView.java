package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SettingView extends JPanel implements Theme {
    private @NotNull Play play;

    public SettingView(@NotNull Play play) {
        this.play = play;

        JPanel voidView = new JPanel();
        voidView.setPreferredSize(new Dimension(400, (375 - 100 - 80) / 2));
        add(voidView);

        LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel labelPlayerName = new JLabel("Nom de joueur", JLabel.CENTER);
        labelPlayerName.setPreferredSize(new Dimension(400, 20));
        add(labelPlayerName);

        JPanel voidView2 = new JPanel();
        voidView2.setPreferredSize(new Dimension(400, 10));
        add(voidView2);

        JTextField inputPlayerName = new JTextField(play.getPlayer().getName());
        inputPlayerName.setPreferredSize(new Dimension(150, 20));
        add(inputPlayerName);

        JPanel voidView3 = new JPanel();
        voidView3.setPreferredSize(new Dimension(400, 30));
        add(voidView3);

        JLabel labelSaveSupport = new JLabel("Support de sauvegarde", JLabel.CENTER);
        labelSaveSupport.setFont(new Font(labelSaveSupport.getFont().getName(), Font.BOLD, 14));
        labelSaveSupport.setPreferredSize(new Dimension(400, 20));
        add(labelSaveSupport);

        JPanel voidView4 = new JPanel();
        voidView4.setPreferredSize(new Dimension(400, 10));
        add(voidView4);

        String[] options = {"PostgreSQL", "MySQL", "H2", "\u0053\u00e9\u0072\u0069\u0061\u006c\u0069\u0073\u0061\u0074\u0069\u006f\u006e",};
        JComboBox<String> comboBoxSaveSupport = new JComboBox<String>(options);
        String selectedOption = play.getHighScoreFactory().getClass().getSimpleName().replace("HighScoreFactory", "")
                                    .replace("Serialization", "\u0053\u00e9\u0072\u0069\u0061\u006c\u0069\u0073\u0061\u0074\u0069\u006f\u006e");
        comboBoxSaveSupport.setSelectedIndex(Arrays.asList(options).indexOf(selectedOption));
        comboBoxSaveSupport.setPreferredSize(new Dimension(150, 20));
        add(comboBoxSaveSupport);

        JPanel voidView5 = new JPanel();
        voidView5.setPreferredSize(new Dimension(400, 30));
        add(voidView5);

        JButton buttonSave = new JButton("Enregistrer");
        buttonSave.addActionListener((e) -> {
            play.getPlayer().setName(inputPlayerName.getText());
            HighScoreFactory highScoreFactory = null;
            switch (options[comboBoxSaveSupport.getSelectedIndex()]) {
                case "PostgreSQL" -> highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" -> highScoreFactory = new MySQLHighScoreFactory();
                case "H2" -> highScoreFactory = new H2HighScoreFactory();
                case "\u0053\u00e9\u0072\u0069\u0061\u006c\u0069\u0073\u0061\u0074\u0069\u006f\u006e" -> highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);//TODO
            play.start();
        });
        buttonSave.setPreferredSize(new Dimension(150, 20));
        add(buttonSave);

        JPanel voidView6 = new JPanel();
        voidView6.setPreferredSize(new Dimension(400, (375 - 100 - 80) / 2));
        add(voidView6);

        setPreferredSize(new Dimension(400, 375));
    }
}
