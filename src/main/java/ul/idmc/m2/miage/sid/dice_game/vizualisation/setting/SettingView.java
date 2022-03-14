package ul.idmc.m2.miage.sid.dice_game.vizualisation.setting;

import org.jetbrains.annotations.NotNull;
import ul.idmc.m2.miage.sid.dice_game.persistence.high_score_factory.*;
import ul.idmc.m2.miage.sid.dice_game.system.Play;
import ul.idmc.m2.miage.sid.dice_game.vizualisation.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class SettingView extends JPanel implements Theme {
    protected @NotNull Play play;
    protected @NotNull JTextField playerNameInput;
    protected @NotNull JComboBox<String> saveSupportComboBox;

    public SettingView(@NotNull Play play) {
        this.play = play;

        paintChildComponents();

        JPanel voidView = new JPanel();
        Integer childComponentsHeight = Arrays.asList(getComponents())
                                              .stream()
                                              .map(component -> (int) component.getPreferredSize().getHeight())
                                              .reduce((height, acc) -> acc + height)
                                              .get();
        voidView.setPreferredSize(new Dimension(400, (375 - childComponentsHeight - 70) / 2));
        add(voidView, 0);

        setPreferredSize(new Dimension(400, 375));
    }

    protected void paintChildComponents()  {
        JLabel playerNameLabel = new JLabel("Nom de joueur", JLabel.CENTER);
        playerNameLabel.setFont(new Font(playerNameLabel.getFont().getName(), Font.BOLD, 13));
        playerNameLabel.setPreferredSize(new Dimension(150, 20));
        add(playerNameLabel);

        JPanel voidView2 = new JPanel();
        voidView2.setPreferredSize(new Dimension(400, 1));
        add(voidView2);

        playerNameInput = new JTextField(play.getPlayer().getName());
        playerNameInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                play.getPlayer().setName(playerNameInput.getText());//TODO à améliorer.
            }
        });
        playerNameInput.setPreferredSize(new Dimension(150, 25));
        add(playerNameInput);

        JPanel voidView3 = new JPanel();
        voidView3.setPreferredSize(new Dimension(400, 15));
        add(voidView3);

        JLabel labelSaveSupport = new JLabel("Espace de sauvegarde", JLabel.CENTER);
        labelSaveSupport.setFont(new Font(labelSaveSupport.getFont().getName(), Font.BOLD, 13));
        labelSaveSupport.setPreferredSize(new Dimension(400, 20));
        add(labelSaveSupport);

        JPanel voidView4 = new JPanel();
        voidView4.setPreferredSize(new Dimension(400, 1));
        add(voidView4);

        String[] options = {"PostgreSQL", "MySQL", "H2", "S\u00e9rialisation",};
        saveSupportComboBox = new JComboBox<String>(options);
        saveSupportComboBox.addItemListener((e) -> {
            HighScoreFactory highScoreFactory = null;
            switch (options[saveSupportComboBox.getSelectedIndex()]) {
                case "PostgreSQL" -> highScoreFactory = new PostgreSQLHighScoreFactory();
                case "MySQL" -> highScoreFactory = new MySQLHighScoreFactory();
                case "H2" -> highScoreFactory = new H2HighScoreFactory();
                case "S\u00e9rialisation" -> highScoreFactory = new SerializationHighScoreFactory();
            }
            play.setHighScoreFactory(highScoreFactory);
            playerNameInput.setText(play.getPlayer().getName());
        });
        String selectedOption = play.getHighScoreFactory()
                                    .getClass()
                                    .getSimpleName()
                                    .replace("HighScoreFactory", "")
                                    .replace("Serialization", "S\u00e9rialisation");
        saveSupportComboBox.setSelectedIndex(Arrays.asList(options).indexOf(selectedOption));
        saveSupportComboBox.setPreferredSize(new Dimension(150, 25));
        saveSupportComboBox.setBackground(Color.WHITE);
        add(saveSupportComboBox);
    }
}